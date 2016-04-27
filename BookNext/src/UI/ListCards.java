/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author jcdur
 */
public class ListCards extends ScrollPane{
    
    private boolean horizontalPane;
    
    public ListCards()
    {
    
    }
    
    public void createVerticalList()
    {
        this.setPrefSize(260,550);
        this.setStyle("-fx-padding: 5 0 0 5; -fx-background-color:TRANSPARENT;");
        this.setBorder(Border.EMPTY);
        CBook newBook = new CBook();
        
        List<CBook> bookList;
        bookList = new ArrayList<>();
        bookList.add(newBook);
        bookList.add(newBook);
        bookList.add(newBook);
        bookList.add(newBook);
        bookList.add(newBook);
        
        VBox vbox = new VBox(10);
        HCard newCard;
        for (CBook bookList1 : bookList) {
            newCard = new HCard(240,115);
            newCard.createCard();
            vbox.getChildren().add(newCard);
        }
        
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setContent(vbox);
        this.relocate(800, 105);
    }
    
      public void createHorizontalList()
    {
        this.setPrefSize(800,275);
        this.setStyle("-fx-padding: 0 0 0 20; -fx-background-color:TRANSPARENT;");
        
        this.setBorder(Border.EMPTY);
        CBook newBook = new CBook();
        
        List<CBook> bookList;
        bookList = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            
        bookList.add(newBook);
        }
        
        HBox hbox = new HBox(30);
        VCard newCard;
        
        for (CBook bookList1 : bookList) {
            newCard = new VCard(128,250);
            newCard.createCard();
            hbox.getChildren().add(newCard);
        }
        
        
        for (int i = 0; i < 5; i++) {
            
        bookList.add(newBook);
        }
        
        for (int i = 0; i < 5; i++) {
            newCard = new VCard(128,250);
            newCard.createCard2();
            hbox.getChildren().add(newCard);
        }
        
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setContent(hbox);
    }
    
    
    
    public ScrollPane getList()
    {
        
        return this;
    }
}
