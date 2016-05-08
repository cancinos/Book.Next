/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import UI.NavigationDrawer;
import UI.VCard;
import UI.mainToolbar;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcdur
 */
public class HomePage extends Stage{
    
    private BorderPane page = new BorderPane();
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private List<CBook> favBooks, suggestedBooks;
    private ScrollPane favScroll, suggestedScroll;
    
    /**
     * This method creates stage's navigation Drawer & toolbar
     */
    private void createView()
    {
        // <editor-fold defaultstate="collapsed" desc="Navigation Drawer Creation">
            navDrawer = new NavigationDrawer(300);
            navDrawer.createNavDrawer();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Toolbar Creation">
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 0;", "Home");
            toolBar.createToolbar();
            //Setting onHamburgerClick
            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                            navDrawer.toggle(navDrawer.getSideMenu());
                    });
        // </editor-fold>
    }
    
    private void addFavoriteBooks()
    {
        // <editor-fold defaultstate="collapsed" desc="Favorite books">
        Label lblFav = new Label("Favorite books");
        lblFav.setStyle("-fx-font-size:24px;");
        lblFav.relocate(150, 10);
        addComponent(lblFav);
        HBox favHBox = new HBox(30);
        VCard newCard;
        
        if (favBooks == null || favBooks.isEmpty())
        {
            ImageView noBooks = new ImageView(new Image("/Icons/noBooks.png"));
            noBooks.relocate(150, 55);
            addComponent(noBooks);
        } else
        {
            favScroll = new ScrollPane();
            favScroll.setPrefSize(800,275);
            favScroll.setStyle("-fx-padding: 0 0 0 20; -fx-background-color:TRANSPARENT;");
            favScroll.setBorder(Border.EMPTY);
            favScroll.relocate(150,42);
            for (CBook book : favBooks) { //Debería ser el top 10...
                newCard = new VCard(128,250);
                newCard.createSimpleCard(book);
                favHBox.getChildren().add(newCard);
            }
            favScroll.setContent(favHBox);
            addComponent(favScroll);
        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Suggested books">
        Label lblSug = new Label ("Recommended for you");
        lblSug.setStyle("-fx-font-size:24px;");
        lblSug.relocate(150, 330);
        addComponent(lblSug);
        HBox sugHBox = new HBox(30);
        suggestedScroll = new ScrollPane();
        suggestedScroll.setPrefSize(800,275);
        suggestedScroll.setStyle("-fx-padding: 0 0 0 20; -fx-background-color:TRANSPARENT;");
        suggestedScroll.setBorder(Border.EMPTY);
        suggestedScroll.relocate(150, 363);
        for (CBook book : suggestedBooks) { //Debería ser el top 10...
                newCard = new VCard(128,250);
                newCard.createSimpleCard(book);
                sugHBox.getChildren().add(newCard);
            }
        suggestedScroll.setContent(sugHBox);
        addComponent(suggestedScroll);
        
        // </editor-fold>
    }
    
    private void addSuggestedBooks()
    {
        
    }
    
    /**
     * This method is used for adding an specific element to page content
     * @param element added element
     */
    private void addComponent(Node element)
    {
        navDrawer.getContent().getChildren().add(element);
    }
    
    private void addComponents()
    {
        
        addFavoriteBooks();
        //addSuggestedBooks();
    }
    
    public Stage getStage(List<CBook> fav, List<CBook> suggested)
    {
        favBooks = fav;
        suggestedBooks = suggested;
        createView();
        addComponents();
        this.initStyle(StageStyle.UNDECORATED); 
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, 1100, 700);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        return this;
    }
    
    public Pane getContent(List<CBook> fav, List<CBook> suggested)
    {
        favBooks = fav;
        suggestedBooks = suggested;
        createView();
        addComponents();
        return navDrawer.getContent();
    }
    
}
