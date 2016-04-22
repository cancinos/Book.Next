/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author jcdur
 */
public class CBook {
    private String book_name;
    private String book_author;
    private String book_image;
    private double book_rating;
    private int num_ratings = 0;
    
    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; } 
    
    public String getBook_author() { return book_author; }
    public void setBook_author(String book_author) { this.book_author = book_author; }
    
    public String getBook_image() { return book_image; }
    public void setBook_image(String book_image) { this.book_image = book_image; }
    
    public double getBook_rating() { return book_rating; }
    
    private void newRating(double rating)
    {
        book_rating *= num_ratings;
        book_rating += rating;
        book_rating /= num_ratings;
    }
    
}
