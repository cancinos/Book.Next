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

    private int genreToInt(String genre) {
        ISBNConverter ic = new ISBNConverter();
        for (int i = 0; i < ic.genres.length; i++) {
            if (genre.equals(ic.genres[i])) {
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
        if(description == null)
            description = "";
        List<String> descriptionWords = tm.tokenize(description);
        /*List<String> nameWords = tm.tokenize(name);
        List<String> authorsWords = tm.tokenize(authors);
        List<String> publisherWords = tm.tokenize(publisher);*/

        String[] genres = book.getBook_genre().split(",");
        
        for (String descriptionWord : descriptionWords) {
            descriptionWord = descriptionWord.toLowerCase();
            for (String genre : genres) {
                connection.addWordToBayes(genreToInt(genre), descriptionWord, tableDescriptions);
            }
        }
    }
    
    public void classifyBook(CBook book, MysqlConnection connection){
        TextManagement tm = new TextManagement();
        int[] likelihood = genresLikelihoodForWords(tm.tokenize(book.getBook_description()), connection, tableDescriptions);
    }
    
    private int[] genresLikelihoodForWords(List<String> words, MysqlConnection connection, String table){
        int[] genres = {0, 0, 0, 0, 0};
        int[] totals = connection.getTotalsForGenre(tableDescriptions);
        for (String word : words) {
            int[] wordGenres = connection.getGenresForWord(word, table);
            for (int i = 0; i < genres.length; i++) {
                genres[i] += wordGenres[i];
            }
        }
        return genres;
    }
}
