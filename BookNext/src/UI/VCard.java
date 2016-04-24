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
public class VCard extends Pane{
    
    private double width, height;
    
    public VCard(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void createCard()
    {
        this.setPrefSize(width, height);
        this.setEffect(new DropShadow(5d, 0d, 0d, Color.web("#B6B6B6")));
        this.setStyle("-fx-background-color:WHITE;");
        ImageView background = new ImageView(new Image("https://books.google.com.gt/books/content?id=nv9lZM_0RI4C&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE721Uleu1ZcOyJ0R-IY74iLoirdPChX7O9UOvu7n_L6vG30BDwo659QHvZJTcijsv7YR5KQonzT5X1TkxE93kcYEOjgnSLHcHN9uvlfYo8oH2E3Ovsa4W4iqobbanb1wa71xYYsh"));
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        this.getChildren().add(background);
    }
    
}
