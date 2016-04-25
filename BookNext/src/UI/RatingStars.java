/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import de.jensd.fx.fontawesome.Icon;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jcdur
 */
public class RatingStars extends HBox{
    
    private String starSize;
    
    public RatingStars(String size)
    {
        starSize = size;
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
            fullStar = new Icon("STAR", "1em");
            fullStar.setTextFill(Color.web("#727272"));
            this.getChildren().add(fullStar);
        }
        
        if (halfCount == 1) //Adds empty star if needed
            this.getChildren().add(halfEmpty);
        
        for (int i = 0; i < emptyCount; i++) { //Adds empty stars
            emptyStar = new Icon("STAR_ALT", "1em");
            emptyStar.setTextFill(Color.web("#727272"));
            emptyStar.setAlignment(Pos.CENTER);
            this.getChildren().add(emptyStar);
        }
        this.relocate(85, 90);
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
}
