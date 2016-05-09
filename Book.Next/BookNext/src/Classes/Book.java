/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jcdur
 */
public class Book extends RecursiveTreeObject<Book>{
		
    public SimpleStringProperty isbn, book_name, authors, genres, average;

    /**
     * Popular category: Women (1)
     * Popular category: History (2)
     * Popular category: Biography (3)
     * Popular category: Juvenile fiction (4)
     * Popular category: Social life and customs (5)
     * 
     */
    private String deleteWirdGenres(String genres)
    {
        while (genres.endsWith(","))
        {
            genres = genres.substring(0, genres.length() - 1);
        }
        
        String[] separated = genres.split(", ");
        String allGenres = "";
        for (String oneBook : separated)
        {
            switch (oneBook.toLowerCase())
            {
                case "women": 
                case "history":
                case "biography":
                case "juvenile fiction":
                case "social life and customs":
                    allGenres += oneBook + ",";
            }
                    
        }
        return allGenres.substring(0, allGenres.length() - 1);
    }
    
    public Book(CBook newBook) {
            this.isbn = new SimpleStringProperty(newBook.isbn) ;
            this.book_name = new SimpleStringProperty(newBook.getBook_name());
            this.authors = new SimpleStringProperty(newBook.getBook_authorsStr());
            this.genres = new SimpleStringProperty(deleteWirdGenres(newBook.getBook_genre()));
            this.average = new SimpleStringProperty(newBook.getBook_StrRating());
    }

}
