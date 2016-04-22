/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jcdur
 */
public class mainToolbar extends HBox{
    
    double width, height;
    String style, title;
    private JFXToggleNode hamburger;
    
    public mainToolbar(double width, double height, String style, String title){
        this.width = width;
        this.height = height;
        this.style = style;
        this.title = title;
        this.hamburger = new JFXToggleNode();
        //this.relocate(0,0);
    }
    
    public void createToolbar()
    {
        this.setWidth(width);
        this.setHeight(height);
        this.setStyle(style);
        this.setEffect(new DropShadow(5d, 0d, +2d, Color.GRAY));
        setHamburger();
        setTitle();
    }
    
    public void setHamburger()
    {
        Icon value = new Icon("BARS", "2em", ";", "icon");
        hamburger.setGraphic(value);
        hamburger.setStyle("-fx-background-radius: 4em; -fx-background-color:TRANSPARENT;");
        this.getChildren().add(hamburger);
    }
    
    public void setTitle()
    {
        Label lblTitle = new Label(title);
        lblTitle.setTextFill(Color.web("#FFFFFF"));
        lblTitle.setStyle("-fx-font-size: 22; -fx-padding: 12 0 0 0; -fx-font-family: Roboto;");
        this.getChildren().add(lblTitle);
    }
    
    public JFXToggleNode getHamburger()
    {
        return this.hamburger;
    }
    
}
