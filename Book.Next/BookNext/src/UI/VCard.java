/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
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

/**
 *
 * @author jcdur
 */
public class VCard extends Pane{
    
    private double width, height;
   public bookSelection book = new bookSelection(); 
   public boolean isclicked =false;
    public VCard(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void createCard(CBook book)
    {
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        ImageView background = new ImageView(new Image(book.getBook_image()));
        
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        
//        
//        Label title = new Label("Divergent");
//        Label author = new Label("Veronica Roth");
        Label title = new Label(book.getBook_name());
        Label author = new Label(book.getBook_authorsStr());
        
        title.setStyle("-fx-font-size:15px;");
        title.setMaxWidth(116);
        title.setPadding(new Insets(0,0,0,10));
        title.relocate(0, 205);
        
        author.relocate(0, 225);
        author.setStyle("-fx-font-size:12px;");
        author.setPadding(new Insets(0,0,0,10));
        author.setMaxWidth(116);    
        author.setTextFill(Color.GRAY);
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
        createEvent();
    }
    
    public void createSimpleCard(CBook book)
    {
        /*
            NOTA MENTAL:
                Para que las imagenes no se tarden mucho, guardarlas en una lista de Images, y luego
                sólo ir a traerlas. Haciendo esto, solo al inicio será lo tardado.
        */
        String strImage, strName, strAuthor;
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        strImage = book.getBook_image();
        strName = book.getBook_name();
        strAuthor = book.getBook_authorsStr();
        strAuthor = strAuthor.substring(0, strAuthor.length() - 1);
        ImageView background = new ImageView(new Image(strImage));
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
        //title.setWrapText(true);       
        author.setTextFill(Color.GRAY);
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
    }
    
    public void createEvent(){
        
        book.selected =0;
       
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{                
        
            if(isclicked ==true){
                isclicked =false;
                book.selected--;
            if(book.selected <3){
               book.next.setVisible(false);                        
                    }
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        }else{
            isclicked =true;            
            book.selected++;
            if(book.selected >=3){
               book.next.setVisible(true);                        
                    }
        //this.setEffect(new DropShadow(20d, 0d, 0d, Color.web("#727272")));    
        this.setEffect(new DropShadow(10d, 10d, 3d, Color.web("#727272")));    
           }
        });     
    }
    
}
