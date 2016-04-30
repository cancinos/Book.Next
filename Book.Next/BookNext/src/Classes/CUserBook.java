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
public class CUserBook {
    private double userRating;
    private boolean userLiked;
    private int userViews;
    private boolean bookSaved;
    
    private long bookId;
    private int userId; 
    
    public CUserBook()
    {
        userRating = 0;
        userLiked = false;
        userViews = 0;
        bookSaved = false;
        bookId = 0;
        userId = 0;
    }
    
    
    public void setBook_Id(long num) { bookId = num; }
    public long getBook_Id(){ return bookId; }
    
    public void setUser_Id(int num) { userId = num; }
    public int getUser_Id() { return userId; }
    
    public void setUser_Rating(double val) { userRating = val; }
    public double getUser_Rating(){ return userRating; }
    
    public void setUser_Liked(boolean liked) { userLiked = liked; }
    public boolean getUser_Liked() { return userLiked; }
    
    public void addView() { userViews++; }
    public int getUser_Views() { return userViews; }
    
    public void setBook_Saved(boolean saved){ bookSaved = saved; }
    public boolean getBook_Saved() { return bookSaved; }
}
