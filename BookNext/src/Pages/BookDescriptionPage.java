/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import UI.NavigationDrawer;
import UI.giantCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *  Stage used for see an specific book info.
 * @author jcdur
 */
public class BookDescriptionPage extends Stage{
    
    private BorderPane page = new BorderPane();
    private double width, height;
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    
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
    
    /**
     * This method is used for asign stage size
     * @param width stage prefered width
     * @param height stage prefered height
     */
    public void setSize(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    /**
     * This method is used for adding every component within the stage
     */
    private void addComponents()
    {
        giantCard infoCard = new giantCard(700,550);
        infoCard.createCard();
        infoCard.relocate(50, 50);
        addComponent(infoCard);
    }
    
    /**
     * This method is used for adding an specific element to page content
     * @param element added element
     */
    private void addComponent(Node element)
    {
        navDrawer.getContent().getChildren().add(element);
    }
    
    /**
     * This function creates the view, add all its components and create
     * the final stage
     * @return Styled beautifull stage
     */
    public Stage getStage()
    {
        createView();
        addComponents();
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, width, height);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        return this;
    }
}
