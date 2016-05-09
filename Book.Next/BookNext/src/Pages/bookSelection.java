/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import Classes.CStaticInfo;
import Classes.CUser;
import UI.ListCards;
import UI.NavigationDrawer;
import UI.VCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light.Point;
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
    
    private List<CBook> allBooks;
    public static int selected = 0;
    public static JFXToggleNode save_icon = new JFXToggleNode();; 
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private final BorderPane page = new BorderPane();
    public CUser loggedUser;
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
        // </editor-fold>
    }
    
    
    public bookSelection()
    {
        CStaticInfo.top3Books = new ArrayList();
        loggedUser = CStaticInfo.loggedUser;
        allBooks = randomList(CStaticInfo.connection.getBooks());
        page.setPrefSize(1000, 720);
    }
    
    private List<CBook> randomList(List<CBook> list)
    {
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
        Collections.shuffle(list, new Random(seed));
        return list;
    }
    
    private void showUserLibrary()
    {
        ListCards cards = new ListCards();
        cards.createMatrixList(allBooks, true);
        cards.relocate(160, 65);
        
        Label lblSelect = new Label("Choose your 3 favorite books");
        lblSelect.setStyle("-fx-font-size:22px");
        lblSelect.relocate(170, 35);
        
        Icon value2 = new Icon("ARROW_RIGHT", "2em");
        value2.setAlignment(Pos.CENTER);
        value2.setTextFill(Color.WHITE);
        save_icon.setGraphic(value2);
        save_icon.setStyle("-fx-background-radius: 4em; -fx-background-color:#03A9F4;");
        save_icon.setEffect(new DropShadow(8d, 0d, 0d, Color.web("#727272")));
        save_icon.setPrefSize(60, 60);
        save_icon.relocate(1000,550);
        save_icon.setVisible(false);
        save_icon.setOnAction((ActionEvent actionEvent) -> {
            List<CBook> top3 = CStaticInfo.top3Books;
            top3.stream().forEach((book) -> {
                int id = CStaticInfo.connection.getUserId(loggedUser.gerUsername());
                CStaticInfo.connection.setUserBook(id, book.isbn);
            });
            EditProfile mainPage = new EditProfile(); //Verificar que sea la lista de sus libros
            mainPage.setSize(1100, 700);
            Stage profileStage = mainPage.getStage();
            profileStage.show();
            //*** ANN ***
            this.getScene().getWindow().hide();  
        });
        
        navDrawer.getContent().getChildren().addAll(cards, lblSelect, save_icon);
    }
    
    
    public Stage getStage(){
        this.initStyle(StageStyle.UNDECORATED); 
        createView();
        showUserLibrary();
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, 1100, 700);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        return this;
    }

}
