/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayes;
import Classes.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class NaiveBayes {
    //Used genres
    String genre1 = "Aprendizaje y vida";
    String genre2 = "Ciencia ficción";
    String genre3 = "Fantasía";
    String genre4 = "Romance";
    String genre5 = "Suspenso";
    
    //Tables used for each content classification
    String tableDescriptions = "description_bayes";
    String tableNames = "names_bayes";
    String tableAuthors = "authors_bayes";
    String tablePublishers = "publishers_bayes";
    
    private int genreToInt(String genre){
        if (genre.equals(genre1))
            return 1;
        if (genre.equals(genre2))
            return 2;
        if (genre.equals(genre3))
            return 3;
        if (genre.equals(genre4))
            return 4;
        if (genre.equals(genre5))
            return 5;
        return 0;
    }
    
    public void train(List<String> genres, CBook book)
    {
        String description = book.getBook_description();
        String name = book.getBook_name();
        String authors = book.getBook_authorsStr();
        String publisher = book.getBook_publisher();
        
        TextManagement tm = new TextManagement();
        List<String> descriptionWords = tm.tokenize(description);
        List<String> nameWords = tm.tokenize(name);
        List<String> authorsWords = tm.tokenize(authors);
        List<String> publisherWords = tm.tokenize(publisher);
        
        MysqlConnection conn = new MysqlConnection();
        
        for (String descriptionWord : descriptionWords) {
            for (String genre : genres) {
                conn.addWordToBayes(genreToInt(genre), descriptionWord, tableDescriptions);
            }
        }
    }
}
