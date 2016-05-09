/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import Classes.CStaticInfo;
import Pages.BookDescriptionPage;
import Pages.bookSelection;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jcdur
 */
public class VCard extends Pane{
    
    private double width, height;
    public bookSelection selectionClass; 
    public boolean isclicked =false;
    
    public VCard(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void setBookClass(bookSelection theClass)
    {
        selectionClass = theClass;
    }
    
    public void createSimpleCard(CBook book)
    {
        String strImage, strName, strAuthor;
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        
        strImage = book.getBook_image();
        strName = book.getBook_name();
        strAuthor = book.getBook_authorsStr();
        strAuthor = strAuthor.substring(0, strAuthor.length() - 1);
        ImageView background;
        if (strImage.compareTo("/Icons/No_Cover.jpg") == 0)
            background = new ImageView(new Image(strImage)); else
            background = new ImageView(new Image("/BookCovers/" + book.isbn + ".jpg"));
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        
        Label title = new Label(strName);
        Label author = new Label(strAuthor);
        
        title.setStyle("-fx-font-size:14px;");
        title.setMaxWidth(116);
        title.setPadding(new Insets(0,0,0,10));
        title.relocate(0, 205);
        
        author.relocate(0, 225);
        author.setMaxWidth(116);
        author.setStyle("-fx-font-size:12px;");
        author.setPadding(new Insets(0,0,0,10));
        author.setTextFill(Color.GRAY); 
        
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            BookDescriptionPage bookDescript = new BookDescriptionPage(); //Creating new Stage
            bookDescript.setSize(1100, 700); //Resizing
            Stage actBookStage = bookDescript.getStage(book);
            actBookStage.show();
        }); 
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
    }
    
    ImageView selectedIcon;
    public void createCardsToSelect(CBook book)
    {
        String strImage, strName, strAuthor;
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        strImage = book.getBook_image();
        strName = book.getBook_name();
        strAuthor = book.getBook_authorsStr();
        strAuthor = strAuthor.substring(0, strAuthor.length() - 1);
        ImageView background;
        if (strImage.compareTo("/Icons/No_Cover.jpg") == 0)
            background = new ImageView(new Image(strImage)); else
            background = new ImageView(new Image("/BookCovers/" + book.isbn + ".jpg"));
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        String prueba = background.toString();
        
        Label title = new Label(strName);
        Label author = new Label(strAuthor);
        
        title.setStyle("-fx-font-size:14px;");
        title.setMaxWidth(116);
        title.setPadding(new Insets(0,0,0,10));
        title.relocate(0, 205);
        
        author.relocate(0, 225);
        author.setMaxWidth(116);
        author.setStyle("-fx-font-size:12px;");
        author.setPadding(new Insets(0,0,0,10));
        author.setTextFill(Color.GRAY);
        
        
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            
        if(isclicked ==true)
        {
            isclicked =false;
            bookSelection.selected--;
            bookSelection.save_icon.setVisible(false);
            this.getChildren().remove(selectedIcon);
            this.setEffect(new DropShadow(8d, 0d, 0d, Color.web("#727272")));
            CStaticInfo.top3Books.remove(book);
        }else
        {            
            if(bookSelection.selected < 3 ) //2 cards are already selected, and now, its the 3rd one
            {
                isclicked =true;
                bookSelection.selected++;
                this.setEffect(new DropShadow(28d, 0d, 0d, Color.BLACK)); 
                selectedIcon = new ImageView(new Image("/Icons/choosen.png"));
                selectedIcon.relocate(10, 10);
                this.getChildren().add(selectedIcon);   
                CStaticInfo.top3Books.add(book);
                if (bookSelection.selected == 3)
                    bookSelection.save_icon.setVisible(true);                      
               
            } else
            {
                
            }
        //this.setEffect(new DropShadow(20d, 0d, 0d, Color.web("#727272")));
           }
        }); 
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
    }
    
    
 
    
}
