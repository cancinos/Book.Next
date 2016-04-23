/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;

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
    private int book_publishYear;
    private List<String> book_categories;
    private List<String> key_words;
    
    private int num_ratings = 0;
    private double book_rating;
    public CBook()
    {
    }
    
    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; } 
    
    public List<String> getBook_authors() { return book_authors; }
    public void setBook_authors(List<String> book_authors) { this.book_authors = book_authors; }
    
    public String getBook_image() { return book_image; }
    public void setBook_image(String book_image) { this.book_image = book_image; }
    
    public String getBook_description() { return this.book_description; }
    public void setBook_description(String descr) { this.book_description = descr; }
    
    public int getBook_publishYear() { return this.book_publishYear; }
    public void setBook_publishYear(int year) { this.book_publishYear = year; }
    
    public String getBook_publisher() { return this.book_publisher; }
    public void setBook_publisher(String publisher) { this.book_publisher = publisher; }
    
    public double getBook_rating() { return book_rating; }
    
    
    
    private void newRating(double rating)
    {
        book_rating *= num_ratings;
        book_rating += rating;
        book_rating /= num_ratings;
        num_ratings++;
    }
    
}
