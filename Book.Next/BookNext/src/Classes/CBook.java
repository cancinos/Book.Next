/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
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
    public String isbn = "";
    private String book_publisher;
    private String book_publishYear;
    private String book_genres;
    private List<String> key_words;
    private ImageView book_img;
    private int num_ratings = 0;
    private String bookId; //ISBN
    private int autoIncId = 0;
    private float book_rating;
    
    
    public void fillCBook(String isbn,String book_name, String authors, String imagen, String publishDate, String publisher,String ratingAverage,String description,String genre){
        this.bookId = isbn;
        this.isbn = isbn;
        this.book_name = book_name;
        this.book_authors = setBook_authorList(authors);
        this.book_image = imagen;
        this.book_publishYear = publishDate;
        this.book_publisher=publisher;
        this.book_rating = Float.parseFloat(ratingAverage);
        this.book_description = description;
        this.book_genres = genre;        
    }
    public int getAutoInc() { return autoIncId; }
    
    
    public String getBookId() { return bookId; }
    public void setBookId(String id) { bookId = id; autoIncId++; }
    
    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; } 
    
    public List<String> getBook_authors() { return book_authors; }
    public void setBook_authors(List<String> book_authors) { this.book_authors = book_authors; }
    
    public String getBook_image() { return book_image; }
    public void setBook_image(String book_image) { 
        this.book_image = book_image; 
        //this.book_img = new ImageView(new Image(book_image));
    } 
    public ImageView getBook_viewImage() { return book_img; }
    
    public String getBook_description() { return this.book_description; }
    public void setBook_description(String descr) { this.book_description = descr; }
    
    public String getBook_publishYear() { return this.book_publishYear; }
    public void setBook_publishYear(String year) { this.book_publishYear = year; }
    
    public String getBook_publisher() { return this.book_publisher; }
    public void setBook_publisher(String publisher) { this.book_publisher = publisher; }
    
    public float getBook_rating() { return book_rating; }
    
    public void setBook_genre(String allGenre) {  
        /*if (allGenre.contains(","))
        {            
            String aux = "";
            String[] separated = allGenre.split(",");
            for (String separated1 : separated) {
                separated1 = separated1.toLowerCase();
                if (separated1.compareTo("women") == 0 || separated1.compareTo("history") == 0 ||
                    separated1.compareTo("biography") == 0 || separated1.compareTo("juvenile fiction") == 0 ||
                    separated1.compareTo("social life and customs") == 0)
                        aux += separated1 + ",";
            }
            book_genres  = aux.substring(0, aux.length() - 1); 
        }*/
        book_genres = allGenre; 
    }
    public String getBook_genre() { return book_genres; }
    
    private void newRating(double rating)
    {
        num_ratings++;
        book_rating *= num_ratings;
        book_rating += rating;
        book_rating /= num_ratings;
    }
    
    public String getBook_authorsStr()
    {
        if (book_authors != null){
            String finalString = "";
            for (int i = 0; i < book_authors.size(); i++) {
                finalString += book_authors.get(i)+ ", ";
            }
            return finalString.substring(0, finalString.length() - 1);
        }
        return "";
    }
    
        public List<String> setBook_authorList(String authors)
        {
            List<String> books;
            books = new ArrayList<String>();
            
            String[] splitAuthors = authors.split(", ");
            
            for (int i = 0; i < splitAuthors.length; i++) {
                books.add(splitAuthors[i]);
            }
            return books;
        }
        
        public String getId_String(){ return bookId;}
        
        public String getRating_String(){ return Float.toString(book_rating);}
}
