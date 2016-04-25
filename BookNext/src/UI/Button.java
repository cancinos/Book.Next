/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ingesis
 */
public class Button {
    JFXButton button;
          
     public Button(String Title,String Color, String Background){
     button = new JFXButton(Title);
     button.setStyle("-fx-background-color:"+Background+";"+"-fx-text-fill:"+Color+";"+ "-fx-font-style:Roboto;");      
     button.getStyleClass().add("button-raised");
     }
     

  /**
 *  This is a test to create a Floating Button
 * @author lcano
 */
     public void Floating(){
        Circle circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);    
     }
     
     
     
  /**
 *  This create a Button with title, text color and background 
 * @author lcano
 */
     public JFXButton getButton(){
         return this.button;
     }
     
}
