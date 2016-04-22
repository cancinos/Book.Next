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
import javafx.scene.layout.VBox;

/**
 *
 * @author jcdur
 */
public class ListCards extends ScrollPane{
    
    private boolean horizontalPane;
    
    public ListCards(boolean isHorizontal)
    {
        this.horizontalPane = isHorizontal;
    }
    
    public void createVerticalList()
    {
        this.setPrefSize(240,550);
        this.setStyle("-fx-padding: 5 0 0 5; -fx-background-color:TRANSPARENT;");
        this.setBorder(Border.EMPTY);
        CBook newBook = new CBook();
        newBook.setBook_name("Harry Potter");
        newBook.setBook_author("J. K. Rowling");
        newBook.setBook_image("asdf");
        
        List<CBook> bookList;
        bookList = new ArrayList<>();
        bookList.add(newBook);
        bookList.add(newBook);
        bookList.add(newBook);
        bookList.add(newBook);
        
        VBox vbox = new VBox(10);
        HCard newCard;
        for (CBook bookList1 : bookList) {
            newCard = new HCard(220,115);
            newCard.createCard();
            vbox.getChildren().add(newCard);
        }
        
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setContent(vbox);
        this.relocate(800, 105);
    }
    
    public ScrollPane getList()
    {
        
        return this;
    }
}
