/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import UI.NavigationDrawer;
import UI.mainToolbar;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcdur
 */
public class HomePage extends Stage{
    
    private BorderPane page = new BorderPane();
    private double width, height;
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
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 0;", "Book.Next");
            toolBar.createToolbar();
            //Setting onHamburgerClick
            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                            navDrawer.toggle(navDrawer.getSideMenu());
                    });
        // </editor-fold>
    }
    
    private void addFavoriteBooks()
    {
        favScroll = new ScrollPane();
        favScroll.setPrefSize(800,275);
        favScroll.setStyle("-fx-padding: 0 0 0 20; -fx-background-color:TRANSPARENT;");
        favScroll.setBorder(Border.EMPTY);
        
        HBox hbox = new HBox(30);
    }
    
    private void addSuggestedBooks()
    {
        
    }
    
    private void addComponents()
    {
        addFavoriteBooks();
        addSuggestedBooks();
    }
    
    public Stage getStage()
    {
        createView();
        this.initStyle(StageStyle.UNDECORATED); 
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, width, height);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        return this;
    }
    
}
