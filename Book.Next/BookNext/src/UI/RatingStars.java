/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import de.jensd.fx.fontawesome.Icon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jcdur
 */
public class RatingStars extends HBox{
    
    private final String starSize;
    private int rating;
    
    public RatingStars(String size)
    {
        starSize = size;
    }
    
    /**
     * This function shows @param stars, depending on users rating.
     * It creates onMouseClicked handlers too, for future rating
     * @param userRating 1,2,3,4 or 5 stars assigned to this book
     * @return HBox with rating view
     */
    public HBox showRatingStars(int userRating)
    {
        
        HBox actBox = new HBox();
        
        actBox.setMaxSize(240, 60);
        actBox.setMinSize(240, 60);
        
        // <editor-fold defaultstate="collapsed" desc="Components init">
        Icon myEmptyStar1, myEmptyStar2, myEmptyStar3, myEmptyStar4, myEmptyStar5;
        myEmptyStar1 = new Icon("STAR_ALT", starSize); myEmptyStar1.setTextFill(Color.web("#727272"));
        myEmptyStar2 = new Icon("STAR_ALT", starSize); myEmptyStar2.setTextFill(Color.web("#727272"));
        myEmptyStar3 = new Icon("STAR_ALT", starSize); myEmptyStar3.setTextFill(Color.web("#727272"));
        myEmptyStar4 = new Icon("STAR_ALT", starSize); myEmptyStar4.setTextFill(Color.web("#727272"));
        myEmptyStar5 = new Icon("STAR_ALT", starSize); myEmptyStar5.setTextFill(Color.web("#727272"));
        
        Icon fullStar0 = new Icon("STAR", starSize); fullStar0.setTextFill(Color.web("#FFC107"));
        Icon fullStar1 = new Icon("STAR", starSize); fullStar1.setTextFill(Color.web("#FFC107"));
        Icon fullStar2 = new Icon("STAR", starSize); fullStar2.setTextFill(Color.web("#FFC107"));
        Icon fullStar3 = new Icon("STAR", starSize); fullStar3.setTextFill(Color.web("#FFC107"));
        Icon fullStar4 = new Icon("STAR", starSize); fullStar4.setTextFill(Color.web("#FFC107"));
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Mouse event creation">
        EventHandler<MouseEvent> stars1 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                actBox.getChildren().remove(0,5);
                actBox.getChildren().add(0, fullStar0);
                actBox.getChildren().add(1, myEmptyStar2);
                actBox.getChildren().add(2, myEmptyStar3);
                actBox.getChildren().add(3, myEmptyStar4);
                actBox.getChildren().add(4, myEmptyStar5);
                rating = 1;
            }
        };
                
        EventHandler<MouseEvent> stars2 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                actBox.getChildren().remove(0,5);
                actBox.getChildren().add(0, fullStar0);
                actBox.getChildren().add(1, fullStar1);
                actBox.getChildren().add(2, myEmptyStar3);
                actBox.getChildren().add(3, myEmptyStar4);
                actBox.getChildren().add(4, myEmptyStar5);
                rating = 2;
            }
        };
 
        EventHandler<MouseEvent> stars3 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                actBox.getChildren().remove(0,5);
                actBox.getChildren().add(0, fullStar0);
                actBox.getChildren().add(1, fullStar1);
                actBox.getChildren().add(2, fullStar2);
                actBox.getChildren().add(3, myEmptyStar4);
                actBox.getChildren().add(4, myEmptyStar5);
                rating = 3;
            }
        };       
        
        EventHandler<MouseEvent> stars4 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                actBox.getChildren().remove(0,5);
                actBox.getChildren().add(0, fullStar0);
                actBox.getChildren().add(1, fullStar1);
                actBox.getChildren().add(2, fullStar2);
                actBox.getChildren().add(3, fullStar3);
                actBox.getChildren().add(4, myEmptyStar5);
                rating = 4;
            }
        };
        
        EventHandler<MouseEvent> stars5 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                actBox.getChildren().remove(0,5);
                actBox.getChildren().add(0, fullStar0);
                actBox.getChildren().add(1, fullStar1);
                actBox.getChildren().add(2, fullStar2);
                actBox.getChildren().add(3, fullStar3);
                actBox.getChildren().add(4, fullStar4);
                rating = 5;
            }
        };
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Mouse event assigment">
        myEmptyStar1.setOnMouseClicked(stars1); fullStar0.setOnMouseClicked(stars1);
        myEmptyStar2.setOnMouseClicked(stars2); fullStar1.setOnMouseClicked(stars2);
        myEmptyStar3.setOnMouseClicked(stars3); fullStar2.setOnMouseClicked(stars3);
        myEmptyStar4.setOnMouseClicked(stars4); fullStar3.setOnMouseClicked(stars4);
        myEmptyStar5.setOnMouseClicked(stars5); fullStar4.setOnMouseClicked(stars5);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Showing user's actual rating">
        switch (userRating)
        {
            case 0: 
                actBox.getChildren().addAll(myEmptyStar1,myEmptyStar2,myEmptyStar3,myEmptyStar4,myEmptyStar5);
            break;
                
            case 1: 
               actBox.getChildren().addAll(fullStar0,myEmptyStar2,myEmptyStar3,myEmptyStar4,myEmptyStar5); 
            break;
            
            case 2: 
               actBox.getChildren().addAll(fullStar0, fullStar1 ,myEmptyStar3,myEmptyStar4,myEmptyStar5); 
            break;
            
            case 3: 
               actBox.getChildren().addAll(fullStar0, fullStar1 , fullStar2,myEmptyStar4,myEmptyStar5); 
            break;
            
            case 4: 
              actBox.getChildren().addAll(fullStar0, fullStar1 , fullStar2, fullStar3,myEmptyStar5); 
            break;
            
            case 5: 
              actBox.getChildren().addAll(fullStar0, fullStar1 , fullStar2, fullStar3, fullStar4); 
            break;
        }
        // </editor-fold>
        
        return actBox;
    }

    public HBox showRating(double rating)
    {
            this.setMaxSize(70, 20);
            this.setMinSize(70, 20);
                    
                
        //double 
        Icon fullStar, emptyStar;
        Icon halfEmpty = new Icon("STAR_HALF_FULL", starSize);
        halfEmpty.setTextFill(Color.web("#727272"));
        
        rating = aproxRating(rating);
        int intRating = (int)(rating * 10);
        int halfCount = ((intRating % 10) == 5) ? 1 : 0;
        int fullCount = intRating / 10;
        int emptyCount = 5 - fullCount - halfCount;
        
        for (int i = 0; i < fullCount; i++) { //Ads full stars
            fullStar = new Icon("STAR", starSize);
            fullStar.setTextFill(Color.web("#727272"));
            this.getChildren().add(fullStar);
        }
        
        if (halfCount == 1) //Adds empty star if needed
            this.getChildren().add(halfEmpty);
        
        for (int i = 0; i < emptyCount; i++) { //Adds empty stars
            emptyStar = new Icon("STAR_ALT", starSize);
            emptyStar.setTextFill(Color.web("#727272"));
            emptyStar.setAlignment(Pos.CENTER);
            this.getChildren().add(emptyStar);
        }
        return this;
    }
    
    private double aproxRating(double rating)
    {
        rating *= 100;
        rating = Math.round(rating);
        rating /= 100;
        double intValue = Math.floor(rating);
        double decimals = rating - intValue;
        if (decimals > 0.8)
            return intValue + 1; else
            if (decimals < 0.2)
                return intValue; else
                return intValue + 0.5;
            
    }
    
    public int getRating()
    {
        return rating;
    }
}
