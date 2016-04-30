/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import UI.ListCards;
import UI.VCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    
    private Pane page = new Pane();
    private final List<CBook> allBooks;
    public static int selected = 0;
    
    public bookSelection()
    {
        allBooks = new ArrayList();
        page.setPrefSize(1000, 720);
    }
    
//    public bookSelection(){
//        
//        // <editor-fold defaultstate="collapsed" desc="Toolbar Creation">
//            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 10;", "Welcome ");
//            toolBar.createToolbar();
//            toolBar.getChildren().remove(toolBar.getHamburger());
//            //Setting onHamburgerClick
//            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
//                         //   navDrawer.toggle(navDrawer.getSideMenu());
//                    });
//        // </editor-fold>
//        
//    }
//    
//    public void createView(){
//        
//        list = new ListCards();
//        list.createHorizontalList();  
//        list.relocate(100, 50);      
//        
//        centralPage.getChildren().add(list.getList());
//        
//        list2 = new ListCards(); 
//        list2.createHorizontalList();     
//        list2.relocate(100, 350);
//        
////       Label title = new Label("SELECT 3 BOOKS TO CONTINUE...");
////       title.setStyle("-fx-font-size:20; -fx-font-color:BLACK; -fx-font-weight: bold; -fx-label-float:true;");
////       title.relocate(5, 5);
////        centralPage.getChildren().add(title);
//        
//        centralPage.getChildren().add(list2.getList());
//    }
//    
//    public void createIcon(){
//        
//         next = new JFXToggleNode();
//        value = new Icon("ARROW_RIGHT", "2em");
//        value.setAlignment(Pos.CENTER);
//        value.setTextFill(Color.WHITE);
//        next.setGraphic(value);
//        next.setStyle("-fx-background-radius: 4em;" + toolBar.getStyle()+";");
//        next.setPrefSize(60, 60);
//        next.relocate(920,550);
//        next.setVisible(false);
//        centralPage.getChildren().add(next);
//        
//           next.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent actionEvent) {
//               BookDescriptionPage book = new BookDescriptionPage();
//               book.setSize(1100,700);
//                Stage loginStage = book.getStage();
//                loginStage.show();               
//                getScene().getWindow().hide();
//                }
//            });
//        
//           
//    }
//    
//    public void createNextButtons(){
//       
//       // <editor-fold desc="next1"> 
//        next1 = new JFXToggleNode();
//        value = new Icon("ARROW_RIGHT", "2em");
//        
//        value.setAlignment(Pos.CENTER);
//        value.setTextFill(Color.BLACK);
//        next1.setStyle("-fx-background-radius: 4em; -fx-background-color:Transparent;");
//        next1.setGraphic(value);
//       next1.setPrefSize(50, 50);
//        next1.relocate(900,150);
//        next1.setVisible(true);
//        centralPage.getChildren().add(next1);
//       
//            //Closing Program
//            next1.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                   // list.getList().relocate(list.getList().getLayoutX()+10, list.getList().getLayoutY());
//                 if(list.getList().getHvalue() >= list.getList().getHmax()){
//                        next1.setVisible(false);
//                        back1.setVisible(true);
//                    }else{
//                    list.getList().setHvalue(list.getList().getHvalue()+0.1);
//                    }    
//                }
//            });
//        
//       // </editor-fold>
//            
//            
//       // <editor-fold desc="next2"> 
//        next2 = new JFXToggleNode();
//        value = new Icon("ARROW_RIGHT", "2em");
//        
//        value.setAlignment(Pos.CENTER);
//        value.setTextFill(Color.BLACK);
//        next2.setStyle("-fx-background-radius: 4em; -fx-background-color:Transparent;");
//        next2.setGraphic(value);
//       next2.setPrefSize(50, 50);
//        next2.relocate(900,450);
//        next2.setVisible(true);
//        centralPage.getChildren().add(next2);
//       
//            //Closing Program
//            next2.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                   
//                    if(list2.getList().getHvalue() == list2.getList().getHmax()){
//                        next2.setVisible(false);
//                        back2.setVisible(true);
//                    }else{                        
//                    list2.getList().setHvalue(list2.getList().getHvalue()+0.1);
//                    }
//                }
//            });
//        
//       // </editor-fold>
//            
//            
//        
//    }    
//    
//    public void createBackButtons(){
//       
//       // <editor-fold desc="back1"> 
//        back1 = new JFXToggleNode();
//        value = new Icon("ARROW_LEFT", "2em");
//        
//        value.setAlignment(Pos.CENTER);
//        value.setTextFill(Color.BLACK);
//        back1.setStyle("-fx-background-radius: 4em; -fx-background-color:Transparent;");
//        back1.setGraphic(value);
//       back1.setPrefSize(50, 50);
//        back1.relocate(50,150);
//        back1.setVisible(false);
//        centralPage.getChildren().add(back1);
//       
//            //Closing Program
//            back1.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                   // list.getList().relocate(list.getList().getLayoutX()+10, list.getList().getLayoutY());
//                 if(list.getList().getHvalue() == list.getList().getHmin()){
//                     next1.setVisible(true);
//                     back1.setVisible(false);
//                    }else{
//                    list.getList().setHvalue(list.getList().getHvalue()-0.1);
//                    }    
//                }
//            });
//        
//       // </editor-fold>
//            
//            
//       // <editor-fold desc="back2"> 
//        back2 = new JFXToggleNode();
//        value = new Icon("ARROW_LEFT", "2em");
//        
//        value.setAlignment(Pos.CENTER);
//        value.setTextFill(Color.BLACK);
//        back2.setStyle("-fx-background-radius: 4em; -fx-background-color:Transparent;");
//        back2.setGraphic(value);
//       back2.setPrefSize(50, 50);
//        back2.relocate(50,450);
//        back2.setVisible(false);
//        centralPage.getChildren().add(back2);
//       
//            //Closing Program
//            back2.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                   
//                    if(list2.getList().getHvalue() <= list2.getList().getHmin()){
//                        next2.setVisible(true);
//                     back2.setVisible(false);
//                    }else{
//                    list2.getList().setHvalue(list2.getList().getHvalue()-0.1);
//                    }
//                }
//            });
//        
//       // </editor-fold>
//            
//            
//        
//    }
    
    
    private void showUserLibrary()
    {
        ListCards cards = new ListCards();
        cards.createMatrixList(allBooks);
        cards.relocate(320, 70);
        cards.relocate(100, 100);
        page.getChildren().add(cards);
        //infoCard.getChildren().add(list.getList());
    }
    
    
    public Stage getStage(){
         this.initStyle(StageStyle.UNDECORATED);             
        Scene scene = new Scene(page, 1000, 720);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        
        return this;
    }
}
