/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import UI.giantCard;
import UI.Button;
import UI.VCard;
import UI.textField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ingesis
 */
public class Login extends Stage{
        
       private Pane page = new Pane();
       private  giantCard card;
       private HBox hbox;
       private VBox vbox;
       private Label title;
       private textField fields;
       private JFXTextField user;
       private JFXPasswordField pass;
       private Button Login;
       private JFXButton button; 
       private JFXButton singup; 
       private JFXToggleNode hamburger;
       private  giantCard card_u;
       JFXPopup popup;
       
       
       //This componet creates the card where si going to contain the log in
    private void  createView(){
        
        card = new giantCard(400,700);
        card.createCard();        
        card.setEffect(new DropShadow(10d, 0d, 0d, Color.web("#B6B6B6")));
        card.setStyle( "-fx-background-color:#F5F5F5;");
        card.relocate(0, 0);
    }
    
    //Create Login view
    private void createComponents(){
        
        //HBOX contiene el LABEL title
        hbox = new HBox();
        hbox.setStyle("-fx-padding-vertical:30; -fx-alignment: left");        
        hbox.setPadding(new Insets(30, 0, 30, 0));     
        
        title = new Label(" LOGIN");
        title.setStyle("-fx-text-fill: #E91E63; -fx-label-float:true; -fx-font-size:30; -fx-font-weight: BOLD; -fx-padding: 10 0 0 0;" );
        
        //VBOX encargado de los TEXTFIELD
        vbox = new VBox();
        vbox.setStyle("-fx-padding:30; -fx-alignment: center");
        vbox.setSpacing(30);
        vbox.setPrefSize(400,450);
        
        fields = new textField();
        user = fields.validateTextField("Username","User name can't be empty","15");    
        pass = fields.PasswordField("Password","Password can't be empty","15");
        Login = new Button("Log in","#FFFFFF","#536DFE");
        button = Login.getButton();
        button.setPrefHeight(40);
        button.setPrefWidth(150);
       
        hbox.setPrefSize(400,450);
        
        hamburger = HAM("PLUS_CIRCLE","#E91E63");
	hamburger.setPadding(new Insets(10,5,10,5));

        ImageView background = new ImageView(new Image("https://static-secure.guim.co.uk/sys-images/Observer/Pix/pictures/2013/12/23/1387823611665/Library-with-a-book-ladde-014.jpg"));
        background.setFitHeight(700);
        background.setFitWidth(1000);
        background.relocate(0, 0);
        
        page.getChildren().add(background);
        
    }
    
    //Create new User card
    private void createUser(){
      
       HBox hbox = new HBox();
        hbox.setStyle("-fx-padding-vertical:30; -fx-alignment: left");        
        hbox.setPadding(new Insets(30, 0, 30, 0));     
        
        //VBOX encargado de los TEXTFIELD
        VBox vbox = new VBox();
        vbox.setStyle("-fx-padding:30; -fx-alignment: center");
        vbox.setSpacing(30);
        vbox.setPrefSize(400,500);        
        
         card_u = new giantCard(400,500);
         
         card_u.createCard();
         card_u.setStyle("-fx-background-color: #E91E63;");
         card_u.relocate(25, 100);        
         Label title = new Label("REGISTER");
         title.setStyle("-fx-text-fill: BLACK; -fx-label-float:true; -fx-font-size:30; -fx-font-weight: BOLD; -fx-padding: 10 0 0 0;" );
         hbox.getChildren().add(title);       
        
         
          JFXTextField user = fields.validateTextField("Username","User name can't be empty","15");    
         JFXPasswordField pass = fields.PasswordField("Password","Password can't be empty","15");
        JFXTextField  name = fields.validateTextField("Name","Please enter your first name","15");    
         JFXTextField  city = fields.validateTextField("City","Please enter your city","15");    
 
        
        
        Button Login = new Button("Sing Up","#FFFFFF","#536DFE");
         button = Login.getButton();

         
         vbox.getChildren().add(name);
         vbox.getChildren().add(user);
         vbox.getChildren().add(city);
         vbox.getChildren().add(pass);
         vbox.getChildren().add(button);
         button.setPrefHeight(40);
         button.setPrefWidth(150);
         
        card_u.getChildren().add(hbox);
        card_u.getChildren().add(vbox);
        
    }
   
    //Create icon Toggle
    private JFXToggleNode HAM(String icon, String color){
        
        JFXToggleNode hamburger = new JFXToggleNode();
        Icon value = new Icon(icon,"6.5em");
        value.setTextFill(Color.web(color));
        hamburger.setGraphic(value);
        hamburger.setStyle("-fx-background-radius: 4em; -fx-background-color:TRANSPARENT;");        
        hamburger.relocate(275, 550);
        
        return hamburger;
    }
    
    //Add all components of Login to page
    private void addComponents(){
            
        hbox.getChildren().add(title);
        vbox.getChildren().add(user);
        vbox.getChildren().add(pass);
        vbox.getChildren().add(button);        
        card.getChildren().add(hbox);
        card.getChildren().add(vbox); 
        page.getChildren().add(card);
	page.getChildren().add(hamburger);
        
    }
    
    
    //Create the events for components
    private void createEvents(){
        //Button Login        
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            button.setStyle("-fx-background-color:#E91E63;"+"-fx-text-fill:WHITE;");
           });        
              
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            changeUser();
           });        
     
         
        
    }
    
    //Change Card from login to user <->
    private void changeUser(){
                
        if(page.getChildren().contains(card) ==false){
         page.getChildren().remove(card_u);       
         page.getChildren().remove(hamburger); 
         page.getChildren().add(card); 
        hamburger = HAM("PLUS_CIRCLE","#E91E63");        
         page.getChildren().add(hamburger); 
         
        }else{
         page.getChildren().remove(card);
         page.getChildren().remove(hamburger); 
         page.getChildren().add(card_u); 
         hamburger = HAM("CHECK_CIRCLE","#FFFFFF"); 
         hamburger.relocate(325, 500);
         page.getChildren().add(hamburger);
        }
        createEvents();
    
        
        
    }
    
    
    //Return stage to show
    public Stage getStage(){       
        this.initStyle(StageStyle.UNDECORATED);             
       createView(); 
       createComponents();
       addComponents();
       createUser();
       createEvents();
       Scene  scene = new Scene(page, 1000, 700);
       scene.getStylesheets().add("/style/jfoenix-components.css");
       this.setScene(scene);
       this.setTitle("FXML is Simple");
       
       return this;
    }
}
