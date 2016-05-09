/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayes;

import Classes.*;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class NaiveBayes {

    //Tables used for each content classification
    String tableDescriptions = "description_bayes";
    String tableNames = "names_bayes";
    String tableAuthors = "authors_bayes";
    String tablePublishers = "publishers_bayes";
    List<String> usedGenres = new ArrayList();

    public NaiveBayes() {
        usedGenres.add("History");
        usedGenres.add("Biography");
        usedGenres.add("Juvenile fiction");
        usedGenres.add("Social life and customs");
        usedGenres.add("Women");
    }

    private int genreToInt(String genre) {
        for (int i = 0; i < usedGenres.size(); i++) {
            if (genre.equals(usedGenres.get(i))) {
                return i;
            }
        }
        return 0;
    }

    public void train(CBook book, MysqlConnection connection) {
        String description = book.getBook_description();
        String name = book.getBook_name();
        String authors = book.getBook_authorsStr();
        String publisher = book.getBook_publisher();

        TextManagement tm = new TextManagement();
        if (description == null) {
            description = "";
        }
        List<String> descriptionWords = tm.tokenize(description);
        List<String> nameWords = tm.tokenize(name);
        List<String> authorsWords = tm.tokenize(authors);
        List<String> publisherWords = tm.tokenize(publisher);

        String[] genres = book.getBook_genre().split(",");
        List<String> usefulGenres = new ArrayList();

        for (String genre : genres) {
            if (usedGenres.contains(genre)) {
                usefulGenres.add(genre);
            }
        }

        iterateTrain(descriptionWords, usefulGenres, tableDescriptions, connection);
        System.out.println("Finished training descriptions");
        iterateTrain(nameWords, usefulGenres, tableNames, connection);
        System.out.println("Finished training names");
        iterateTrain(authorsWords, usefulGenres, tableAuthors, connection);
        System.out.println("Finished training authors");
        iterateTrain(publisherWords, usefulGenres, tablePublishers, connection);
        System.out.println("Finished training publishers");
    }

    private void iterateTrain(List<String> words, List<String> usefulGenres, String table, MysqlConnection connection) {
        for (String descriptionWord : words) {
            descriptionWord = descriptionWord.toLowerCase();
            for (String genre : usefulGenres) {
                connection.addWordToBayes(genreToInt(genre), descriptionWord, table);
            }
        }
    }

    public List<CBook> recommend(CBook book, MysqlConnection connection, int howMany) {
        TextManagement tm = new TextManagement();
        List<double[]> allLikelihoods = new ArrayList();

        List<String> authorsWords = tm.tokenize(book.getBook_authorsStr());
        if (authorsWords.size() > 0) {
            double[] likelihoodAuthors = genresLikelihoodForWords(authorsWords, connection, tableAuthors);
            allLikelihoods.add(likelihoodAuthors);
        }

        List<String> descriptionWords = tm.tokenize(book.getBook_description());
        if (authorsWords.size() > 0) {
            double[] likelihoodDescription = genresLikelihoodForWords(descriptionWords, connection, tableDescriptions);
            allLikelihoods.add(likelihoodDescription);
        }

        List<String> publisherWords = tm.tokenize(book.getBook_publisher());
        if (authorsWords.size() > 0) {
            double[] likelihoodPublisher = genresLikelihoodForWords(publisherWords, connection, tablePublishers);
            allLikelihoods.add(likelihoodPublisher);
        }

        List<String> nameWords = tm.tokenize(book.getBook_name());
        {
            double[] likelihoodNames = genresLikelihoodForWords(nameWords, connection, tableNames);
            allLikelihoods.add(likelihoodNames);
        }
        
        double[] globalLikelihood = allLikelihoods.get(0);
        for (double[] allLikelihood : allLikelihoods) {
            for (int i = 1; i < allLikelihood.length; i++) {
                globalLikelihood[i] = (double)globalLikelihood[i] * (double)allLikelihood[i];
            }
        }
        
        double[] finalProbabilities = new double[5];
        double totalLikelihood = (double)0;
        for (int i = 0; i < globalLikelihood.length; i++) {
            totalLikelihood += globalLikelihood[i];
        }
        for (int i = 0; i < finalProbabilities.length; i++) {
            finalProbabilities[i] = globalLikelihood[i] / totalLikelihood;
        }
        
        List<String> topGenres = BubbleSort(finalProbabilities);
        List<CBook> recommended = new ArrayList();
        //Sends 75% of the same genre and 25% of the next most probable genre
        int numSameGenre = howMany * 3 / 4;
        if (numSameGenre == howMany)
            numSameGenre--;
        int nextGenre = howMany - numSameGenre;
        
        List<CBook> sameGenres = connection.getBooksByGenre(topGenres.get(0));
        List<CBook> differentGenres = connection.getBooksByGenre(topGenres.get(1));
        for (int i = 0; i < numSameGenre; i++) {
            int randIndex = new Random().nextInt(sameGenres.size());
            recommended.add(sameGenres.get(randIndex));
        }
        for (int i = 0; i < nextGenre; i++) {
            int randIndex = new Random().nextInt(differentGenres.size());
            recommended.add(differentGenres.get(randIndex));
        }
        return recommended;
    }
    
    private List<String> BubbleSort(double[] num) {
        List<String> words = new ArrayList();
        words.add("History");
        words.add("Biography");
        words.add("Juvenile fiction");
        words.add("Social life and customs");
        words.add("Women");
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        double temp;   //holding variable
        String temps;

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < num.length - 1; j++) {
                if (num[j] < num[j + 1]) // change to > for ascending sort
                {
                    temp = num[j];
                    temps = words.get(j); //swap elements

                    num[j] = num[j + 1];
                    words.set(j, words.get(j + 1));

                    num[j + 1] = temp;
                    words.set(j + 1, temps);

                    flag = true;              //shows a swap occurred  
                }
            }
        }
        return words;
    }

    private double[] genresLikelihoodForWords(List<String> words, MysqlConnection connection, String table) {
        int[] genres = {0, 0, 0, 0, 0};
        int[] totals = connection.getTotalsForGenre(table);
        int vocabularyTotal = connection.getVocabularyTotal(table);
        for (String word : words) {
            word = word.toLowerCase();
            int[] wordGenres = connection.getGenresForWord(word, table);
            for (int i = 0; i < genres.length; i++) {
                genres[i] += wordGenres[i];
            }
        }
        double[] probabilities = new double[5];
        for (int i = 0; i < probabilities.length; i++) {
            if (genres[i] == 0) { //Laplace smoothing
                probabilities[i] = (double)(genres[i] + 1) / (double)(totals[i] + vocabularyTotal);
            } else {
                probabilities[i] = (double)genres[i] / (double)totals[i];
            }
        }
        return probabilities;
    }
}
