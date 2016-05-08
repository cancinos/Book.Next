/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
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
        
        VBox vbox = new VBox(10);
        HCard newCard;
        for (CBook bookList1 : bookList) {
            newCard = new HCard(240,115);
            newCard.createCard();
            vbox.getChildren().add(newCard);
        }
        
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setContent(vbox);
        this.relocate(800, 105);
    }
    
    /**
     * This method is used to show a vertical list of books (4 books) -OJO- every book needs to have a handler
     * @param books list of books
     */
    public void createVerticalList(List<CBook> books)
    {
        this.setPrefSize(260,550);
        this.setStyle("-fx-padding: 5 0 0 5; -fx-background-color:TRANSPARENT;");
        this.setBorder(Border.EMPTY);
        
        VBox vbox = new VBox(10);
        HCard newCard;
        for (CBook bookList1 : books) {
            newCard = new HCard(240,115);
            newCard.createCard();
            vbox.getChildren().add(newCard);
        }
        
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setContent(vbox);
        this.relocate(800, 105);
    }
    
    
    /**
     * This method shows rows and columns of books
     * @param bookList user's book list
     * @param willBeSelected if true, meands that will be used for 3 book selection
     */
    public void createMatrixList(List<CBook> bookList, boolean willBeSelected)
    {
        FlowPane cardsMatrix = new FlowPane();
        cardsMatrix.setVgap(25);
        cardsMatrix.setHgap(15);
        cardsMatrix.setPrefWrapLength(700);
        cardsMatrix.setMaxHeight(550);
        cardsMatrix.setPadding(new Insets(15,15,15,15));
        cardsMatrix.setStyle("-fx-background-color:TRANSPARENT;");
                
        VCard newCard;
        
        if (bookList.size() >= 50)
        {
            CBook book;
            for (int i = 0; i < 50; i++) {
                book = bookList.get(i);
                newCard = new VCard(128,250);
                if (willBeSelected)
                    newCard.createCardsToSelect(book); else
                    newCard.createSimpleCard(book);
                cardsMatrix.getChildren().add(newCard);
            }
        } else
        {
            for (CBook book : bookList) {
            newCard = new VCard(128,250);
            if (willBeSelected)
                newCard.createCardsToSelect(book); else
                newCard.createSimpleCard(book);
            cardsMatrix.getChildren().add(newCard);
            }
        }
        
        
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        //firstRow.relocate(0,0);
        this.setContent(cardsMatrix);
        this.setStyle(" -fx-background-color:TRANSPARENT;");
        this.relocate(5, 0);
        this.setMinSize(745, 560);
        this.setMaxSize(745, 560);
    }
    
    
    public ScrollPane getList()
    {
        
        return this;
    }
}
