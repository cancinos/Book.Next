/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Classes.CBook;
import Classes.ISBNConverter;
import Pages.BookDescriptionPage;
import Pages.EditProfile;
import booknext.BookNext;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONException;

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
        JFXToggleNode node2 = new JFXToggleNode();
//        
//        Icon value2 = new Icon("KEY", "2em", ";", "icon");
//        node2.setGraphic(value2);
//        node2.setStyle("-fx-background-radius: 4em; -fx-background-color:TRANSPARENT;");
        
        content = new Pane();
        content.setStyle("-fx-background-color: WHITE;");

        
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
        // <editor-fold defaultstate="collapsed" desc="Mouse event creation">
        EventHandler<MouseEvent> goToHome = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                BookDescriptionPage bookDescript = new BookDescriptionPage(); //Creating new Stage
                bookDescript.setSize(1100, 700); //Resizing
                content = bookDescript.getContent();
                //Stage bookDescriptStage = bookDescript.getStage(); //Getting Stage
                //bookDescriptStage.show(); //Showing Stage
                //stage.getScene().getWindow().hide(); //Hiding old Stage
            }
        };
        
        EventHandler<MouseEvent> goToProfile = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                    BookDescriptionPage bookDescript = new BookDescriptionPage(); //Creating new Stage
                bookDescript.setSize(1100, 700); //Resizing
                content = bookDescript.getContent();
                //Stage bookDescriptStage = bookDescript.getStage(); //Getting Stage
                //bookDescriptStage.show(); //Showing Stage
                //stage.getScene().getWindow().hide(); //Hiding old Stage
            }
        };
        // </editor-fold>
        
        JFXListView<Label> menuList = new JFXListView<Label>();
        menuList.setStyle("-fx-padding: 10 10 10 10;");
        
        Label lblHome = new Label("    Home");
        lblHome.setStyle("-fx-font-size: 14; -fx-font-weight: LIGHT;");
        Image imgUser = new Image("/Icons/home.png");
        lblHome.setGraphic(new ImageView(imgUser));
        lblHome.setTextAlignment(TextAlignment.CENTER);
        menuList.getItems().add(lblHome);
        lblHome.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{ //Goes to book description (later will be modified
			BookDescriptionPage bookDescript = new BookDescriptionPage(); //Creating new Stage
                        bookDescript.setSize(1100, 700); //Resizing
                        content = bookDescript.getContent();
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
            
//            EditProfile profile = new EditProfile(allBooks);
//            profile.setSize(1100, 700); //Resizing
//            content = profile.getContent();
//            this.setContent(content);
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
