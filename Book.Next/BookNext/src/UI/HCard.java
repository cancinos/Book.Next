/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import Classes.CStaticInfo;
import Pages.BookDescriptionPage;
import de.jensd.fx.fontawesome.Icon;
import java.util.List;
import java.util.Random;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    
    public void createCard(CBook book)
    {
        this.setPrefSize(width, height);
        this.setEffect(new DropShadow(5d, 0d, 0d, Color.web("#B6B6B6")));
        this.setStyle("-fx-background-color:WHITE;");
        
        VBox name_author = new VBox();
        String strImage = book.getBook_image();
        ImageView iv1;
        if (strImage.compareTo("/Icons/No_Cover.jpg") == 0)
            iv1 = new ImageView(new Image(strImage)); else
            iv1 = new ImageView(new Image("/BookCovers/" + book.isbn + ".jpg"));
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
        lblAuthor.setWrapText(true);
        lblAuthor.setMaxWidth(155);
        lblAuthor.setMaxHeight(50);
        name_author.getChildren().add(lblName);
        name_author.getChildren().add(lblAuthor);
        name_author.relocate(85, 5);
        name_author.setSpacing(5);
        
        RatingStars stars = new RatingStars("1em");
        stars.relocate(85, 90);
        double bookRating = CStaticInfo.connection.getBookRating(book.isbn);
        this.setCursor(Cursor.HAND);
        
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            BookDescriptionPage bookDescript = new BookDescriptionPage(); //Creating new Stage
            bookDescript.setSize(1100, 700); //Resizing
            Stage actBookStage = bookDescript.getStage(book);
            actBookStage.show();
        }); 
        
        this.getChildren().addAll(iv1, name_author, stars.showRating(bookRating)); //Change rating with original book rating
    }
    
    
    
}
