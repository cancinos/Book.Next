/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import UI.ListCards;
import UI.NavigationDrawer;
import UI.giantCard;
import UI.mainToolbar;
import UI.textField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Container;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author jcdur
 */
public class EditProfile extends Stage {
    
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private BorderPane page = new BorderPane();
    private double width, height;
    
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
            //Setting onHamburgerClick
            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                            navDrawer.toggle(navDrawer.getSideMenu());
                    });
        // </editor-fold>
    }
    
    /**
     * This method is used for asign stage size
     * @param width stage prefered width
     * @param height stage prefered height
     */
    public void setSize(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    private void addComponents()
    {
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
        view.relocate(95, 40);
        // </editor-fold>
                      
        VBox vbox = new VBox();
        vbox.setMinHeight(350);
        vbox.setMinWidth(150);
        vbox.setStyle("-fx-background-color:WHITE;");
        vbox.setSpacing(15);
        Label lblBooks = new Label("My books");
        lblBooks.setStyle("-fx-font-size:22");
        lblBooks.relocate(335,45);
        showUserLibrary();
        
        
        
        // <editor-fold defaultstate="collapsed" desc="User's info">
        
        JFXTextField txtUsername;
        txtUsername = new textField().validateTextField("Username", "User name can't be empty", "18");
        txtUsername.setText("@pinaconda30");
        txtUsername.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        
        JFXTextField txtFullname;
        txtFullname = new textField().validateTextField("Full name", "Full name can't be empty", "18");
        txtFullname.setText("Juan Carlos Durini");
        txtFullname.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        
        
        
        JFXDatePicker DatePicker = new JFXDatePicker();
        DatePicker.setPromptText("pick a date");
        DatePicker.setStyle("-fx-font-size: 18;");
        DatePicker.setDefaultColor(Color.web("#F44336"));
        DatePicker.setEditable(false);
        DatePicker.setValue(LocalDate.of(1994, 9, 30));
//        JFXPasswordField txtPassword;
//        txtPassword = new textField().PasswordField("Password","Password can't be empty","18");
//        //txtPassword.setText("durini1230");
//        txtPassword.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        // </editor-fold>
        
        JFXButton btnLogOut = new JFXButton("LOG OUT");
        btnLogOut.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: #F44336;");
        btnLogOut.relocate(120, 557);

        
        vbox.getChildren().addAll(txtUsername, txtFullname, DatePicker);
        vbox.relocate(40, 200);
        addComponent(vbox);
        addComponent(btnLogOut);
        addComponent(lblBooks);
        addComponent(view);
    }
    
    private void showUserLibrary()
    {
        List<CBook> myBooks;
        myBooks = new ArrayList<>();
        
        CBook ejemplo = new CBook();
        ejemplo.setBook_description("");
        ejemplo.setBook_image("https://books.google.com.gt/books/content?id=lJiXBgAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api");
        ejemplo.setBook_name("Harry Potter and the Deadly Hallows");
        ejemplo.setBook_publishYear("2009");
        ejemplo.setBook_publisher("Editorial el pato");
        
        
        
        for (int i = 0; i < 7; i++) {
            myBooks.add(ejemplo);
        }
        
        ListCards list = new ListCards();
        list.createMatrixList(myBooks);
        list.relocate(320, 70);
        addComponent(list);
        //infoCard.getChildren().add(list.getList());
    }
    
    /**
     * This method is used for adding an specific element to page content
     * @param element added element
     */
    private void addComponent(Node element)
    {
        navDrawer.getContent().getChildren().add(element);
    }
    
    /**
     * This function creates the view, add all its components and create
     * the final stage
     * @return Styled beautifull stage
     */
    public Stage getStage()
    {
        createView();
        addComponents();
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, width, height);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        return this;
    }
    
    public Pane getContent()
    {
        createView();
        addComponents();
        return navDrawer.getContent();
    }
}
