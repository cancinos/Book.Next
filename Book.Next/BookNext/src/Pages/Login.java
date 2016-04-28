/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import UI.giantCard;
import UI.Button;
import UI.textField;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.util.Password;

/**
 *
 * @author Ingesis
 */
public class Login extends Stage{
        
    /*
    Components
    */
       private Pane page = new Pane();
       private giantCard  card;
       private JFXTabPane tabPane ;
       private Tab tab;
       private textField fields;
       private VBox logbox;
       private VBox userbox;
       private Label title;
       private ImageView background;
       private JFXToggleNode icon;
       private JFXToggleNode save_icon;
       private JFXToggleNode up_down;
       private JFXTextField user;
       private JFXPasswordField pass;
       private ScrollPane scrollpane;
       
    public void createView(){
        
        card = new giantCard(400,500);
        card.createCard();        
        card.setEffect(new DropShadow(10d, 5d, 0d, Color.web("#212121")));
        card.setStyle( "-fx-background-color:#FFFFFF; -fx-background-radius:1em");
        card.relocate(150, 100);             
        card.getChildren().add(background);
        card.getChildren().add(tabPane);   
        card.getChildren().add(icon);
        card.getChildren().add(save_icon);
        page.getChildren().add(card);
    }
    
    public void createLogin(){
        logbox =new VBox();
        logbox.setStyle("-fx-alignment: center");
        logbox.setSpacing(35);
        logbox.setPrefSize(400,250);
        fields = new textField();
        user = fields.validateTextField("Username","User name can't be empty","16");    
        pass = fields.PasswordField("Password","Password can't be empty","16");
        logbox.getChildren().add(user);
        logbox.getChildren().add(pass);
    }
    
    public void createUser(){
        //scrollpane
        scrollpane = new ScrollPane();
        scrollpane.setPrefSize(400,250);        
        scrollpane.setStyle("-fx-padding: 10 0 0 0; -fx-background-color:TRANSPARENT;");
        scrollpane.setBorder(Border.EMPTY);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        
        
        userbox =new VBox();
        userbox.setStyle("-fx-alignment: center; -fx-background-color:WHITE;");
        userbox.setSpacing(35);
        userbox.setPrefSize(400,250);
        fields = new textField();              
        JFXTextField name = fields.textField("Full Name", "16");
        JFXTextField user = fields.validateTextField("Username","User name can't be empty","15");    
        JFXPasswordField pass = fields.PasswordField("Password","Password can't be empty","15"); 
        JFXTextField country = fields.textField("Country","16");
        JFXDatePicker date = new JFXDatePicker();        
        createProfilePicture();
        userbox.getChildren().add(name);
        userbox.getChildren().add(user);
        userbox.getChildren().add(pass);
        userbox.getChildren().add(country);
        userbox.getChildren().add(date);
        userbox.getChildren().add(up_down);
        
        scrollpane.setContent(userbox);
    }
    
    public void createTabs(){
      
        //Create Tab with log in box
      createLogin();  
      tabPane = new JFXTabPane();
      tabPane.setStyle("-fx-background-color:WHITE;");      
      title = new Label("Sing in");     
      title.setTextFill(Color.WHITE);
      title.setStyle("-fx-font-size:15px;");
      tab = new Tab();      
      tab.setStyle("-fx-background-color:TRANSPARENT; -fx-alignment:center; -fx-font-weight: bold");
      tab.setContent(logbox);    
      tab.setText("");
      tab.setGraphic(title);
      
      tabPane.getTabs().add(tab);
      
      
      // Create Tab with sing up box
       createUser();      
      title = new Label("Sing up");      
      title.setTextFill(Color.WHITE);     
      title.setStyle("-fx-font-size:15px;");
      tab = new Tab();      
      tab.setStyle("-fx-background-color:TRANSPARENT; -fx-alignment:center; -fx-font-weight: bold");
      tab.setContent(scrollpane);    
      tab.setText("");  
      tab.setGraphic(title);
      
      tabPane.getTabs().add(tab);
      tabPane.relocate(0, 200);
      
      
      tabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            if(tabPane.getSelectionModel().isSelected(0)==true){
                icon.setVisible(true);
                save_icon.setVisible(false);
            }else{
                save_icon.setVisible(true);
                icon.setVisible(false);
            }
        });
    }
  
    
    public void loginPic(){
              
        background = new ImageView(new Image("Icons/login.jpg"));        
        background.setFitHeight(200);
        background.setFitWidth(400);
        background.relocate(0, 0); 
        background.setStyle("-fx-background-radius:1em");
        
    }   
    
    public void selectPic(){
        
    }
    
    public void createProfilePicture(){
            // <editor-fold defaultstate="collapsed" desc="Circle Image">
        
        ImageView view = (new ImageView(new Image("/Icons/durini.jpg")));
        Rectangle square = new Rectangle();
        square.setWidth(150);
        square.setHeight(150);
        view.setClip(square);
        Circle clip = new Circle();
        clip.setCenterX(75);
        clip.setCenterY(75);
        clip.setRadius(75);
        view.fitWidthProperty().bind(square.widthProperty());
        view.fitHeightProperty().bind(square.heightProperty());
        view.setClip(clip);
        
        
        
        
        userbox.getChildren().add(view);
        // </editor-fold>
        
    }
    
    public void createToggle(){
        
        // <editor-fold desc="Validate Icon">
        icon = new JFXToggleNode();
        Icon value = new Icon("ARROW_RIGHT", "2em");
        value.setAlignment(Pos.CENTER);
        value.setTextFill(Color.WHITE);
        icon.setGraphic(value);
        icon.setStyle("-fx-background-radius: 4em; -fx-background-color:#03A9F4;");
        icon.setPrefSize(60, 60);
        icon.relocate(375,415);  
        
        icon.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {            
                validateLogin();     
                }
            });
        
        
        
        // </editor-fold>
        
        // <editor-fold desc="Save icon">
        save_icon = new JFXToggleNode();
        Icon value2 = new Icon("SAVE", "2em");
        value2.setAlignment(Pos.CENTER);
        value2.setTextFill(Color.WHITE);
        save_icon.setGraphic(value2);
        save_icon.setStyle("-fx-background-radius: 4em; -fx-background-color:#03A9F4;");
        save_icon.setPrefSize(60, 60);
        save_icon.relocate(375,415);
        save_icon.setVisible(false);
        
         save_icon.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
            
          //      validateLogin();     
                }
            });
         // </editor-fold>
        
        // <editor-fold desc="UP Down Icon">
         up_down = new JFXToggleNode();
        Icon value3 = new Icon("LONG_ARROW_DOWN", "2em");
        value3.setAlignment(Pos.CENTER);
        value3.setTextFill(Color.GRAY);
        up_down.setGraphic(value3);
        up_down.setStyle("-fx-background-color:#TRANSPARENT;");
        up_down.setPrefSize(60, 60);
        up_down.relocate(150,500);
        up_down.setVisible(true);
        
         up_down.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
            
          //      validateLogin();     
                }
            });
         
         // </editor-fold>
         
         
    }
    
    public void validateLogin(){
        
        if(user.getText().length()>4 & pass.getText().length()>5){            
            //Connect to Database
                bookSelection book = new bookSelection();
                Stage loginStage = book.getStage();
                loginStage.show();
                getScene().getWindow().hide();
               
        }else{
            
            if(user.getText().length()<4){
                user.validate();
            }else{
                if(pass.getText().length()<4){
                    pass.validate();
                }
            }
        }        
    }
  
    public Stage getStage(){
        this.initStyle(StageStyle.UNDECORATED);       
       page.setStyle("-fx-background-color:#455A64");
       
       loginPic();
       createToggle();
       createTabs();       
       createView(); //Contains all the components
       Scene  scene = new Scene(page, 700, 700);       
       scene.getStylesheets().add("/style/jfoenix-components.css");
       this.setScene(scene);
       this.setTitle("FXML is Simple");
       
       return this;
    }
}
