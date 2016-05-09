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

    public Book(CBook newBook) {
            this.isbn = new SimpleStringProperty(newBook.isbn) ;
            this.book_name = new SimpleStringProperty(newBook.getBook_name());
            this.authors = new SimpleStringProperty(newBook.getBook_authorsStr());
            this.genres = new SimpleStringProperty(newBook.getBook_genre());
            this.average = new SimpleStringProperty(newBook.getBook_StrRating());
    }

}
