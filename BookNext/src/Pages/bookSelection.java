/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import UI.ListCards;
import UI.VCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ingesis
 */
public class bookSelection extends Stage {
    
    private BorderPane page = new BorderPane();
    private mainToolbar toolBar;
    private VCard vcard;
    private Pane centralPage = new Pane();
    private  ListCards list;
    public static int selected = 0; 
    public static JFXToggleNode next;
    
    public bookSelection(){
        
        // <editor-fold defaultstate="collapsed" desc="Toolbar Creation">
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 10;", "Welcome ");
            toolBar.createToolbar();
            toolBar.getChildren().remove(toolBar.getHamburger());
            //Setting onHamburgerClick
            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                         //   navDrawer.toggle(navDrawer.getSideMenu());
                    });
        // </editor-fold>
        
    }
    
   
    
    public void createView(){
        list = new ListCards();
        list.createHorizontalList();  
        list.relocate(100, 50);      
        
        centralPage.getChildren().add(list.getList());
        list = new ListCards(); 
        list.createHorizontalList();     
        list.relocate(100, 350);
        
        next = new JFXToggleNode();
        Icon value = new Icon("ARROW_RIGHT", "2em");
        value.setAlignment(Pos.CENTER);
        value.setTextFill(Color.WHITE);
        next.setGraphic(value);
        next.setStyle("-fx-background-radius: 4em;" + toolBar.getStyle()+";");
        next.setPrefSize(60, 60);
        next.relocate(930,550);
        
        next.setVisible(false);
        centralPage.getChildren().add(list.getList());
        centralPage.getChildren().add(next);
    }
    
  
    public Stage getStage(){
         this.initStyle(StageStyle.UNDECORATED);             
       
        createView();
        page.setTop(this.toolBar);
        page.setCenter(centralPage);
        Scene scene = new Scene(page, 1000, 700);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        
        return this;
    }
}
