/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author jcdur
 */
public class HCard extends Pane{
    
    private double width, height;
    
    public HCard(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void createCard()
    {
        this.setPrefSize(width, height);
        this.setEffect(new DropShadow(5d, 0d, 0d, Color.web("#B6B6B6")));
        this.setStyle("-fx-background-color:WHITE;");
        ImageView iv1 = new ImageView(new Image("https://books.google.com.gt/books/content?id=lJiXBgAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"));
        iv1.setFitHeight(115);
        iv1.setFitWidth(75);
        iv1.relocate(0, 0);
        this.getChildren().add(iv1);
    }
    
}
