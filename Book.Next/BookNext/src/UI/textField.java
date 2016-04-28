/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import javafx.scene.control.TextField;

/**
 *
 * @author Ingesis
 */
public class textField extends TextField {
    
    JFXTextField textField;
    JFXPasswordField passwordField;
    RequiredFieldValidator validator;
   
   public JFXTextField validateTextField(String title,String error,String font){
        textField = new JFXTextField();
        textField.setPromptText(title);
        textField.setLabelFloat(true);
        textField.setStyle("-fx-font-size:"+font +";");
        validator = new RequiredFieldValidator();
        validator.setMessage(error);
        validator.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));
        validator.setStyle("-fx-font-size:10;");
        textField.getValidators().add(validator);
		textField.focusedProperty().addListener((o,oldVal,newVal)->{
			if(!newVal) textField.validate(); 
		});
                
       return textField;
   }
   
   public JFXPasswordField PasswordField(String title,String error,String font){
                passwordField	 = new JFXPasswordField();
                passwordField.setLabelFloat(true);
		passwordField.setPromptText(title);             
                passwordField.setStyle("-fx-font-size:"+font+";");
                
		validator = new RequiredFieldValidator();
		validator.setMessage(error);
		validator.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));
		
                passwordField.getValidators().add(validator);
		passwordField.focusedProperty().addListener((o,oldVal,newVal)->{
			if(!newVal) passwordField.validate();
		});
                
                return passwordField;
   }
   
   public JFXTextField textField(String title,String font){
       
       textField = new JFXTextField();
       textField.setLabelFloat(true);
       textField.setPromptText(title);
       textField.setStyle("-fx-font-size:"+font+";");
                
	return textField;
   }
   
}
