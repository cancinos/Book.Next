/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import Pages.DiscoverPage;
import Pages.EditProfile;
import Pages.HomePage;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author jcdur
 */
public class NavigationDrawer extends JFXDrawersStack{
    
    private final double width;
    private final JFXDrawer leftDrawer;
    private final StackPane leftDrawerPane;
    private Pane content;
    private Stage contentStage;
    private List<CBook> allBooks = new ArrayList<>();
    
    public NavigationDrawer(double width)
    {
        this.content = new Pane();
        this.width = width;
        this.leftDrawer = new JFXDrawer();
        this.leftDrawerPane = new StackPane();
    }
    
    public void setStage(Stage act)
    {
        contentStage = act;
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
        content = new Pane();
        content.setStyle("-fx-background-color: #F4F4F4;");

        
        leftDrawer.setSidePane(leftDrawerPane);
        leftDrawer.setDefaultDrawerSize(220);
        leftDrawer.setId("LEFT");
        leftDrawer.setOverLayVisible(true);
        setNavDrawerList();
        this.setContent(content);
    }
    
    
    private void setNavDrawerList()
    {   
        JFXListView<Label> menuList = new JFXListView<>();
        menuList.setStyle("-fx-padding: 10 10 10 10;");
        
        Label lblHome = new Label("    Home");
        lblHome.setStyle("-fx-font-size: 14; -fx-font-weight: LIGHT;");
        Image imgUser = new Image("/Icons/home.png");
        lblHome.setGraphic(new ImageView(imgUser));
        lblHome.setTextAlignment(TextAlignment.CENTER);
        menuList.getItems().add(lblHome);
        lblHome.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{ //Goes to book description (later will be modified
                        mainToolbar.title = "Home";
                        mainToolbar.lblTitle.setText("Home");
                        HomePage home = new HomePage();
                        content = home.getContent();
                        this.setContent(content);
		});
        //lblHome.setOnMouseClicked(goToHome);
        CBook newBook;
        Label lblProfile = new Label("    Profile");
        lblProfile.setStyle("-fx-font-size: 14;");
        imgUser = new Image("/Icons/profile.png");
        lblProfile.setGraphic(new ImageView(imgUser));
        lblProfile.setTextAlignment(TextAlignment.CENTER);
        menuList.getItems().add(lblProfile);
        lblProfile.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{ //Goes to actUser profile
            mainToolbar.title = "My profile";
            mainToolbar.lblTitle.setText("My profile");
            EditProfile profile = new EditProfile();
            profile.setSize(1100, 700); //Resizing
            content = profile.getContent();
            this.setContent(content);
        });
        
        Label lblDiscover = new Label("    Discover");
        lblDiscover.setStyle("-fx-font-size: 14; -fx-font-weight: LIGHT;");
        Image imgBook = new Image("/Icons/book.png");
        lblDiscover.setGraphic(new ImageView(imgBook));
        lblDiscover.setTextAlignment(TextAlignment.CENTER);
        menuList.getItems().add(lblDiscover);
        lblDiscover.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{ 
                        mainToolbar.title = "Discover";
                        mainToolbar.lblTitle.setText("Discover");
                        DiscoverPage discover = new DiscoverPage();
                        content = discover.getContent();
                        this.setContent(content);
		});
        
        this.leftDrawerPane.getChildren().add(menuList);
    }
    
    public JFXDrawer getSideMenu()
    {
        return leftDrawer;
    }
    
    
    
    public String readFile(Stage parent) throws FileNotFoundException, IOException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(parent);
        String everything = "";
        if (selectedFile != null) 
        {
            BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsoluteFile()));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(",");
                    line = br.readLine();
                }
                everything = sb.toString();
                System.out.println(everything);
            } finally {
                br.close();
            }
        }

        return everything; 
      
    }
    
}
