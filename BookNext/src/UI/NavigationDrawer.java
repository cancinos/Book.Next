/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author jcdur
 */
public class NavigationDrawer extends JFXDrawersStack{
    
    private double width;
    private JFXDrawer leftDrawer;
    private StackPane leftDrawerPane;
    private Pane content;
    
    public NavigationDrawer(double width)
    {
        this.content = new Pane();
        this.width = width;
        this.leftDrawer = new JFXDrawer();
        this.leftDrawerPane = new StackPane();
    }
    
    public Pane getContent()
    {
        return content;
    }
    
    public void refreshContent(Pane newContent)
    {
        this.setContent(content);
    }
    public void createNavDrawer()
    {
        JFXToggleNode node2 = new JFXToggleNode();
//        
//        Icon value2 = new Icon("KEY", "2em", ";", "icon");
//        node2.setGraphic(value2);
//        node2.setStyle("-fx-background-radius: 4em; -fx-background-color:TRANSPARENT;");
        
        content = new Pane();
        //content.getChildren().add(node2);
        content.setStyle("-fx-background-color: -fx-background-color: WHITE;");

        
        leftDrawer.setSidePane(leftDrawerPane);
        leftDrawer.setDefaultDrawerSize(220);
        leftDrawer.setId("LEFT");
        leftDrawer.setOverLayVisible(true);
        setNavDrawerList();
        this.setContent(content);
    }
    
    private void createHeader()
    {
        String book = "Book";
        String next = "Next";
    }
    
    private void setNavDrawerList()
    {
        JFXListView<Label> menuList = new JFXListView<Label>();
        menuList.setStyle("-fx-padding: 10 10 10 10;");
        Label lblAct = new Label("    Home");
        lblAct.setStyle("-fx-font-size: 14; -fx-font-weight: LIGHT;");
        Image imgUser = new Image("/Icons/home.png");
        lblAct.setGraphic(new ImageView(imgUser));
        lblAct.setTextAlignment(TextAlignment.CENTER);
        menuList.getItems().add(lblAct);
        
        lblAct = new Label("    Profile");
        lblAct.setStyle("-fx-font-size: 14;");
        imgUser = new Image("/Icons/profile.png");
        lblAct.setGraphic(new ImageView(imgUser));
        lblAct.setTextAlignment(TextAlignment.CENTER);
        menuList.getItems().add(lblAct);
        
        
        this.leftDrawerPane.getChildren().add(menuList);
    }
    
    public JFXDrawer getSideMenu()
    {
        return leftDrawer;
    }
    
}
