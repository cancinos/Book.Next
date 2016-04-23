/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author jcdur
 */
public class giantCard extends Pane {
    
    private double width, height;
    
    public giantCard(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void createCard()
    {
        this.setPrefSize(width, height);
        this.setEffect(new DropShadow(5d, 0d, 0d, Color.web("#B6B6B6")));
        this.setStyle("-fx-background-color:WHITE;");
    }
    
}
