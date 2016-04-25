/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import UI.giantCard;
import UI.Button;
import UI.textField;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
       private giantCard  card;
       private TabPane tabPane ;
       private Tab tab;
       private textField fields;
       private VBox logbox;
       private VBox userbox;
       private Label title;
       private ImageView background;
       private JFXToggleNode icon;
       
    public void createView(){
        
        card = new giantCard(400,500);
        card.createCard();        
        card.setEffect(new DropShadow(10d, 5d, 0d, Color.web("#212121")));
        card.setStyle( "-fx-background-color:#FFFFFF; -fx-background-radius:1em");
        card.relocate(300, 100);
        card.getChildren().add(tabPane);        
        card.getChildren().add(background);
        card.getChildren().add(icon);
        page.getChildren().add(card);
    }
    
    public void createLogin(){
        
        logbox =new VBox();
        logbox.setStyle("-fx-alignment: center");
        logbox.setPadding(new Insets(0,0,0,0));
        logbox.setSpacing(10);
        logbox.setPrefSize(400,250);
        fields = new textField();
        JFXTextField user = fields.validateTextField("Username","User name can't be empty","14");    
        JFXPasswordField pass = fields.PasswordField("Password","Password can't be empty","14");
        logbox.getChildren().add(user);
        logbox.getChildren().add(pass);
    }
    
    public void createUser(){
        userbox =new VBox();
        userbox.setStyle("-fx-alignment: center");
        logbox.setPadding(new Insets(0,0,0,0));
        userbox.setSpacing(10);
        userbox.setPrefSize(400,250);
        fields = new textField();              
        JFXTextField name = fields.textField("Name", "14");
        JFXTextField user = fields.validateTextField("Username","User name can't be empty","14");    
        JFXPasswordField pass = fields.PasswordField("Password","Password can't be empty","14"); 
        userbox.getChildren().add(name);
        userbox.getChildren().add(user);
        userbox.getChildren().add(pass);
    }
    
    public void createTabs(){
      tabPane = new TabPane();
      tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
      tabPane.getStylesheets().add("/style/jfoenix-components.css");
      
      title = new Label("Sing in");
      title.setTextFill(Color.web("#E91E63"));      
      title.setStyle("-fx-font-size:15px;");
      tab = new Tab();      
      tab.setStyle("-fx-background-color:TRANSPARENT; -fx-alignment:center; -fx-font-weight: bold");
      tab.setContent(logbox);    
      tab.setText("");
      tab.setGraphic(title);
      
      tabPane.getTabs().add(tab);
      
      title = new Label("Sing up");
      title.setTextFill(Color.web("#E91E63"));     
      title.setStyle("-fx-font-size:15px;");
      tab = new Tab();      
      tab.setStyle("-fx-background-color:TRANSPARENT; -fx-alignment:center; -fx-font-weight: bold");
      tab.setContent(userbox);    
      tab.setText("");  
      tab.setGraphic(title);
      
      tabPane.getTabs().add(tab);
      tabPane.relocate(0, 200);
    }
  
    public void createPic(){
       
        background = new ImageView(new Image("http://cdn.slashgear.com/wp-content/uploads/2014/10/unnamed-6.jpg"));
        background.setFitHeight(200);
        background.setFitWidth(400);
        background.relocate(0, 0); 
        background.setStyle("-fx-background-radius:1em");
        
    }   
    
    public void createToggle(){
        icon = new JFXToggleNode();
        Icon value = new Icon("ARROW_RIGHT", "2em");
        value.setAlignment(Pos.CENTER);
        value.setTextFill(Color.WHITE);
        icon.setGraphic(value);
        icon.setStyle("-fx-background-radius: 4em; -fx-background-color:#03A9F4;");
        icon.setPrefSize(60, 60);
        icon.relocate(375,415);               
    }
    
    public Stage getStage(){
        this.initStyle(StageStyle.UNDECORATED);
       
       page.setStyle("-fx-background-color:#455A64");
       createLogin();
       createUser();
       createTabs(); 
       createPic();
       createToggle();
       createView();
       Scene  scene = new Scene(page, 1000, 700);       
       scene.getStylesheets().add("/style/jfoenix-components.css");
       this.setScene(scene);
       this.setTitle("FXML is Simple");
       
       return this;
    }
}
