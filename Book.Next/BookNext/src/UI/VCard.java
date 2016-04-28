/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Pages.bookSelection;
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
    public VCard(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public void createCard()
    {
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        ImageView background = new ImageView(new Image("https://books.google.com.gt/books/content?id=nv9lZM_0RI4C&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE721Uleu1ZcOyJ0R-IY74iLoirdPChX7O9UOvu7n_L6vG30BDwo659QHvZJTcijsv7YR5KQonzT5X1TkxE93kcYEOjgnSLHcHN9uvlfYo8oH2E3Ovsa4W4iqobbanb1wa71xYYsh"));
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        
        Label title = new Label("Divergent");
        Label author = new Label("Veronica Roth");
        
        title.setStyle("-fx-font-size:15px;");
        title.setWrapText(true);       
        title.relocate(0, 205);
        
        author.relocate(0, 225);
        title.setStyle("-fx-font-size:11px;");
        title.setWrapText(true);       
        author.setTextFill(Color.GRAY);
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
        createEvent();
    }
    
   
    
    public void createCard2()
    {
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        ImageView background = new ImageView(new Image("https://books.google.com.gt/books/content?id=lJiXBgAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"));
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        
        Label title = new Label("Divergent");
        Label author = new Label("Veronica Roth");
        
        title.setStyle("-fx-font-size:15px;");
        title.setWrapText(true);       
        title.relocate(0, 205);
        
        author.relocate(0, 225);
        title.setStyle("-fx-font-size:11px;");
        title.setWrapText(true);       
        author.setTextFill(Color.GRAY);
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
        createEvent();
    }
    
    public void createSimpleCard()
    {
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color:WHITE;");
        this.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#607D8B")));
        ImageView background = new ImageView(new Image("https://books.google.com.gt/books/content?id=nv9lZM_0RI4C&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE721Uleu1ZcOyJ0R-IY74iLoirdPChX7O9UOvu7n_L6vG30BDwo659QHvZJTcijsv7YR5KQonzT5X1TkxE93kcYEOjgnSLHcHN9uvlfYo8oH2E3Ovsa4W4iqobbanb1wa71xYYsh"));
        background.setFitHeight(198);
        background.setFitWidth(128);
        background.relocate(0, 0);
        
        Label title = new Label("Divergent");
        Label author = new Label("Veronica Roth");
        
        title.setStyle("-fx-font-size:15px;");
        title.setWrapText(true);       
        title.relocate(0, 205);
        
        author.relocate(0, 225);
        title.setStyle("-fx-font-size:11px;");
        title.setWrapText(true);       
        author.setTextFill(Color.GRAY);
        
        this.getChildren().add(background);
        this.getChildren().add(title);
        this.getChildren().add(author);
        
        this.setCursor(Cursor.HAND);
    }
    
    boolean isclicked =false;
    public void createEvent(){
        
        
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
        this.setEffect(new DropShadow(20d, 0d, 0d, Color.web("#727272")));    
           }
        });     
    }
    
}
