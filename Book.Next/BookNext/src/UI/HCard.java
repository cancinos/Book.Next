/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import de.jensd.fx.fontawesome.Icon;
import java.util.List;
import java.util.Random;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        
        VBox name_author = new VBox();
        
        ImageView iv1 = new ImageView(new Image("https://books.google.com.gt/books/content?id=lJiXBgAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"));
        iv1.setFitHeight(115);
        iv1.setFitWidth(75);
        iv1.relocate(0, 0);
        
        Label lblName = new Label("Harry Potter and the Deathly Hallows");
        lblName.setStyle("-fx-font-size:15px;");
        lblName.setWrapText(true);
        lblName.setMaxWidth(155);
        lblName.setMaxHeight(50);
        //lblName.relocate(85, 5);
        
        Label lblAuthor = new Label("J.K.Rowling");
        lblAuthor.setStyle("-fx-font-size:11px;");
        lblAuthor.setMaxWidth(155);
        
        name_author.getChildren().add(lblName);
        name_author.getChildren().add(lblAuthor);
        name_author.relocate(85, 5);
        name_author.setSpacing(5);
        
        RatingStars stars = new RatingStars("1em");
        stars.relocate(85, 90);
        Random  rnd = new Random();
        double rating = rnd.nextDouble() * 5;
        this.setCursor(Cursor.HAND);
        this.getChildren().addAll(iv1, name_author,stars.showRating(rating));
    }
    
    public void createCard(CBook book)
    {
        this.setPrefSize(width, height);
        this.setEffect(new DropShadow(5d, 0d, 0d, Color.web("#B6B6B6")));
        this.setStyle("-fx-background-color:WHITE;");
        
        VBox name_author = new VBox();
        
        ImageView iv1 = new ImageView(new Image(book.getBook_image()));
        iv1.setFitHeight(115);
        iv1.setFitWidth(75);
        iv1.relocate(0, 0);
        
        Label lblName = new Label(book.getBook_name());
        lblName.setStyle("-fx-font-size:15px;");
        lblName.setWrapText(true);
        lblName.setMaxWidth(155);
        lblName.setMaxHeight(50);
        
        Label lblAuthor = new Label(book.getBook_authorsStr());
        lblAuthor.setStyle("-fx-font-size:11px;");
        
        name_author.getChildren().add(lblName);
        name_author.getChildren().add(lblAuthor);
        name_author.relocate(85, 5);
        name_author.setSpacing(5);
        
        RatingStars stars = new RatingStars("1em");
        stars.relocate(85, 90);
        this.setCursor(Cursor.HAND);
        this.getChildren().addAll(iv1, name_author, stars.showRating(4.3)); //Change rating with original book rating
    }
    
    
    
}
