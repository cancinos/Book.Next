/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booknext;

import UI.NavigationDrawer;
import UI.giantCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcdur
 */
public class bookDescription extends Application {
    
    private mainToolbar toolBar;
    private NavigationDrawer navDrawer;
    
    
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
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // <editor-fold defaultstate="collapsed" desc="Nav Drawer">
            createView();
            giantCard card = new giantCard(200,200);
            //card.setPrefSize(100, 100);
            card.createCard();
            card.relocate(10, 10);
            BorderPane page = new BorderPane();

            
            JFXButton button = new JFXButton("Exit".toUpperCase());
            button.getStyleClass().add("button-raised");
            button.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE;");
            button.relocate(445, 320);
            
            button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            
            navDrawer.getContent().getChildren().addAll(button, card);
            page.setCenter(navDrawer);
            page.setTop(toolBar);
            //page.relocate(5, 5);
            
            Scene scene = new Scene(page, 1110, 700);
            
            scene.getStylesheets().add("/style/jfoenix-components.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            primaryStage.show();    
            // </editor-fold>
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
