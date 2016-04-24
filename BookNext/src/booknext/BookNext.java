/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booknext;

import Classes.ISBNConverter;
import Pages.BookDescriptionPage;
import Pages.Login;
import UI.Button;
import UI.NavigationDrawer;
import UI.giantCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONException;

/**
 *
 * @author jcdur
 */
public class BookNext extends Application {
    
    private mainToolbar toolBar;
    private NavigationDrawer navDrawer;
    Scene scene;
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
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        
        // <editor-fold defaultstate="collapsed" desc="Nav Drawer">
            createView();
            
            JFXButton button = new JFXButton("Exit".toUpperCase());
            button.getStyleClass().add("button-raised");
            button.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE;");
            button.relocate(445, 320);
            
            JFXButton button2 = new JFXButton("Open book".toUpperCase());
            button2.getStyleClass().add("button-raised");
            button2.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE;");
            button2.relocate(445, 370);
            
            JFXButton button3 = new Button("Sing In".toUpperCase(),"WHITE","").getButton();
             button3.relocate(445, 420);
            //Closing Program
            button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            
            //For opening new page...
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
//                    ISBNConverter converter = new ISBNConverter();
//                    try {
//                        converter.readJSON();
//                    } catch (IOException ex) {
//                        Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (JSONException ex) {
//                        Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    BookDescriptionPage bookDescript = new BookDescriptionPage(); //Creating new Stage
                    bookDescript.setSize(1100, 700); //Resizing
                    Stage bookDescriptStage = bookDescript.getStage(); //Getting Stage
                    bookDescriptStage.show(); //Showing Stage
                    stage.getScene().getWindow().hide(); //Hiding old Stage
                }
            });
            
            //For opening sing in
            button3.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
                Login login = new Login();
                Stage loginStage = login.getStage();
                loginStage.show();
                stage.getScene().getWindow().hide();
           });    
            
            
            
            navDrawer.getContent().getChildren().addAll(button, button2,button3);

            BorderPane page = new BorderPane();
            page.setCenter(navDrawer);
            page.setTop(toolBar);
            scene = new Scene(page, 1110, 700);
            scene.getStylesheets().add("/style/jfoenix-components.css");
            stage.setScene(scene);
            stage.setTitle("FXML is Simple");
            stage.show();    
            // </editor-fold>
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
