/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import Classes.CUser;
import UI.ListCards;
import UI.NavigationDrawer;
import UI.mainToolbar;
import UI.textField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.fontawesome.Icon;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jcdur
 */
public class EditProfile extends Stage {
    
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private final BorderPane page = new BorderPane();
    private double width, height;
    private final CUser actUser = new CUser("pinaconda30", "Guatemala", "Juan Carlos Durini", "30/09/1994", "/Icons/durini.jpg", "lalalolo");
    Boolean editDisabled = true;
    private final List<CBook> allBooks;
    
    public EditProfile(List<CBook> myBooks)
    {
        allBooks = myBooks;
    }
    
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
        ImageView view = (new ImageView(new Image(actUser.getUser_image())));
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
        view.relocate(90, 40);
        // </editor-fold>
        
        VBox vbox = new VBox();
        vbox.setMinHeight(350);
        vbox.setMinWidth(150);
        vbox.setDisable(true);
        vbox.setStyle("-fx-background-color:WHITE;");
        vbox.setEffect(new DropShadow(5d, 0d, 0d, Color.web("#B6B6B6")));
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(10,10, 10, 10));
        Label lblBooks = new Label("My books");
        lblBooks.setStyle("-fx-font-size:22");
        lblBooks.relocate(335,45);
        showUserLibrary();
             
        // <editor-fold defaultstate="collapsed" desc="User's info">
        
        JFXTextField txtUsername;
        txtUsername = new textField().validateTextField("Username", "User name can't be empty", "18");
        txtUsername.setText("@" + actUser.gerUsername());
        txtUsername.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        
        JFXTextField txtFullname;
        txtFullname = new textField().validateTextField("Full name", "Full name can't be empty", "18");
        txtFullname.setText(actUser.getUser_fullName());
        txtFullname.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        
        JFXTextField txtCountry;
        txtCountry = new textField().validateTextField("Country", "Country can't be empty", "18");
        txtCountry.setText(actUser.getUser_country());
        txtCountry.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        
        JFXDatePicker DatePicker = new JFXDatePicker();
        DatePicker.setPromptText("pick a date");
        DatePicker.setStyle("-fx-font-size: 18;");
        DatePicker.setDefaultColor(Color.web("#F44336"));
        DatePicker.setEditable(false);
        DatePicker.setValue(stringToDate(actUser.getUser_birthday()));
        
        JFXPasswordField txtPassword;
        txtPassword = new textField().PasswordField("Password","Password can't be empty","18");
        txtPassword.setText("durini1230");
        txtPassword.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: #FFC107; -fx-font-size: 18;");
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Buttons">
        JFXButton btnEdit = new JFXButton("EDIT");
        EventHandler<ActionEvent> editHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    editDisabled = !editDisabled;
                    vbox.setDisable(editDisabled);
                    if (editDisabled == true) //Update user's info
                    {
                        Icon editIcon = new Icon("PENCIL", "1em");
                        editIcon.setTextFill(Color.WHITE);
                        editIcon.setPadding(new Insets(0,10,0,0));
                        btnEdit.setGraphic(editIcon);
                        btnEdit.setText("Edit");
                    } else
                    {
                        Icon saveIcon = new Icon("SAVE", "1em");
                        saveIcon.setTextFill(Color.WHITE);
                        saveIcon.setPadding(new Insets(0,10,0,0));
                        btnEdit.setGraphic(saveIcon);
                        btnEdit.setText("Save");
                    }
                }
        };
        btnEdit.setOnAction(editHandler);
        JFXButton btnLogOut = new JFXButton("Log Out");
        btnLogOut.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: #F44336;");
        btnLogOut.relocate(180, 570);
        
        Icon editIcon = new Icon("PENCIL", "1em");
        editIcon.setTextFill(Color.WHITE);
        editIcon.setPadding(new Insets(0,10,0,0));
        
        btnEdit.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: #F44336;");
        btnEdit.setMinWidth(75);
        btnEdit.setMaxWidth(75);
        btnEdit.relocate(100, 570);
        btnEdit.setGraphic(editIcon);
        // </editor-fold>
        
        vbox.getChildren().addAll(txtUsername, txtFullname, DatePicker, txtCountry, txtPassword);
        vbox.relocate(35, 200);
        addComponent(vbox);
        addComponent(btnLogOut);
        addComponent(btnEdit);
        addComponent(lblBooks);
        addComponent(view);
    }
    
    private LocalDate stringToDate(String strDate)
    {
        String[] nums = strDate.split("/");
        return LocalDate.of(Integer.parseInt(nums[2]), Integer.parseInt(nums[1]), Integer.parseInt(nums[0]));
    }
    
    private void showUserLibrary()
    {
        ListCards list = new ListCards();
        list.createMatrixList(allBooks);
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
