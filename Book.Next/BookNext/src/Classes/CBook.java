/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jcdur
 */
public class CBook {
    private String book_name;
    private List<String> book_authors;
    private String book_image;
    private String book_description;
    private String book_publisher;
    private String book_publishYear;
    private List<String> book_categories;
    private List<String> key_words;
    private ImageView book_img;
    private int num_ratings = 0;
    private long bookId;
    private int autoIncId = 0;
    private double book_rating;
    
    
    public int getAutoInc() { return autoIncId; }
    
    public long getBookId() { return bookId; }
    public void setBookId(long id) { bookId = id; autoIncId++; }
    
    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; } 
    
    public List<String> getBook_authors() { return book_authors; }
    public void setBook_authors(List<String> book_authors) { this.book_authors = book_authors; }
    
    public String getBook_image() { return book_image; }
    public void setBook_image(String book_image) { 
        this.book_image = book_image; 
        this.book_img = new ImageView(new Image(book_image));
    } 
    public ImageView getBook_viewImage() { return book_img; }
    
    public String getBook_description() { return this.book_description; }
    public void setBook_description(String descr) { this.book_description = descr; }
    
    public String getBook_publishYear() { return this.book_publishYear; }
    public void setBook_publishYear(String year) { this.book_publishYear = year; }
    
    public String getBook_publisher() { return this.book_publisher; }
    public void setBook_publisher(String publisher) { this.book_publisher = publisher; }
    
    public double getBook_rating() { return book_rating; }
    
    private void newRating(double rating)
    {
        num_ratings++;
        book_rating *= num_ratings;
        book_rating += rating;
        book_rating /= num_ratings;
    }
    
    public String getBook_authorsStr()
    {
        String finalString = "";
        for (int i = 0; i < book_authors.size(); i++) {
            finalString += book_authors.get(i)+ ", ";
        }
        return finalString.substring(0, finalString.length() - 1);
    }
    
}
