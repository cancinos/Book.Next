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
        
        //remove Border
        this.setBorder(Border.EMPTY);
        
        CBook newBook = new CBook();        
        newBook.setBook_name("Divergent");
        List<String> authors = new ArrayList<>();
        authors.add("Veronica Roth");
        newBook.setBook_authors(authors);
        newBook.setBook_image("https://books.google.com.gt/books/content?id=nv9lZM_0RI4C&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE721Uleu1ZcOyJ0R-IY74iLoirdPChX7O9UOvu7n_L6vG30BDwo659QHvZJTcijsv7YR5KQonzT5X1TkxE93kcYEOjgnSLHcHN9uvlfYo8oH2E3Ovsa4W4iqobbanb1wa71xYYsh");
        
        
        List<CBook> bookList;
        bookList = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            
        bookList.add(newBook);
        }
        
        //30 = padding
        HBox hbox = new HBox(30);
        VCard newCard;
        
        for (CBook bookList1 : bookList) {
            newCard = new VCard(128,250);
            newCard.createCard(bookList1);
            hbox.getChildren().add(newCard);
        }
        
        
        
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setContent(hbox);
    }
    
    /**
     * This method shows rows and columns of books
     * @param bookList
     * @return 
     */
    public void createMatrixList(int booksPerRow, List<CBook> bookList)
    {
        //giantCard infoCard = new giantCard(750,550);
        HBox firstRow = new HBox();
        FlowPane bookMatrix = new FlowPane();
        bookMatrix.setPrefSize(700,500);
        bookMatrix.setStyle("-fx-padding: 20 20 20 20; -fx-background-color:TRANSPARENT;");

          CBook newBook = new CBook();        
        newBook.setBook_name("Divergent");
        List<String> authors = new ArrayList<>();
        authors.add("Veronica Roth");
        newBook.setBook_authors(authors);
        newBook.setBook_image("https://books.google.com.gt/books/content?id=nv9lZM_0RI4C&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE721Uleu1ZcOyJ0R-IY74iLoirdPChX7O9UOvu7n_L6vG30BDwo659QHvZJTcijsv7YR5KQonzT5X1TkxE93kcYEOjgnSLHcHN9uvlfYo8oH2E3Ovsa4W4iqobbanb1wa71xYYsh");
        
        
        
        VCard newCard;
        
        for (CBook book : bookList) {
            newCard = new VCard(128,250);
            newCard.createCard(book);
            bookMatrix.getChildren().add(newCard);
        }
        
        //this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        bookMatrix.setMaxHeight(450);
    }
    
    
    public ScrollPane getList()
    {
        
        return this;
    }
}
