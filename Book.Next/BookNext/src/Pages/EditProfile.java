/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import Classes.CStaticInfo;
import Classes.CUser;
import UI.ListCards;
import UI.NavigationDrawer;
import UI.mainToolbar;
import UI.textField;
import booknext.BookNext;
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
import javafx.stage.StageStyle;

/**
 *
 * @author jcdur
 */
public class EditProfile extends Stage {
    
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private final BorderPane page = new BorderPane();
    private double width, height;
    private final CUser actUser;
    Boolean editDisabled = true;
    private final List<CBook> allBooks;
    private String accentColor = CStaticInfo.accentColor;
    
    public EditProfile()
    {
        actUser = CStaticInfo.loggedUser;
        int id = CStaticInfo.connection.getUserId(CStaticInfo.loggedUser.gerUsername());
        CStaticInfo.usersBooks = CStaticInfo.connection.getUserSavedBooks(id);
        allBooks = CStaticInfo.usersBooks;
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
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 0;", "My Profile");
            toolBar.createToolbar();
            //Setting onHamburgerClick
            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                            navDrawer.toggle(navDrawer.getSideMenu());
                    });
            toolBar.getClose().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                            this.getScene().getWindow().hide();
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
        txtUsername.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: " + accentColor + "; -fx-font-size: 18;");
        
        JFXTextField txtFullname;
        txtFullname = new textField().validateTextField("Full name", "Full name can't be empty", "18");
        txtFullname.setText(actUser.getUser_fullName());
        txtFullname.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: " + accentColor + ";-fx-font-size: 18;");
        
        JFXTextField txtCountry;
        txtCountry = new textField().validateTextField("Country", "Country can't be empty", "18");
        txtCountry.setText(actUser.getUser_country());
        txtCountry.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: " + accentColor + ";-fx-font-size: 18;");
        
        JFXDatePicker DatePicker = new JFXDatePicker();
        DatePicker.setPromptText("pick a date");
        DatePicker.setStyle("-fx-font-size: 18;");
        DatePicker.setDefaultColor(Color.web("#F44336"));
        DatePicker.setEditable(false);
        DatePicker.setValue(stringToDate(actUser.getUser_birthday()));
        
        JFXPasswordField txtPassword;
        txtPassword = new textField().PasswordField("Password","Password can't be empty","18");
        txtPassword.setText(actUser.getUser_password());
        txtPassword.setStyle("-fx-background-color:TRANSPARENT; -fx-focus-color: " + accentColor + ";-fx-font-size: 18;");
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Buttons">
        JFXButton btnEdit = new JFXButton("EDIT");
        EventHandler<ActionEvent> editHandler = (ActionEvent actionEvent) -> {
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
        };
        btnEdit.setOnAction(editHandler);
        JFXButton btnLogOut = new JFXButton("LOG OUT");
        btnLogOut.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: " + accentColor + ";");
        btnLogOut.relocate(180, 570);
        EventHandler<ActionEvent> logOutHandler = (ActionEvent actionEvent) -> {
            LandingPage logPage = new LandingPage();
            Stage logStage = logPage.getStage();
            logStage.show();
            this.getScene().getWindow().hide();
        };
        btnLogOut.setOnAction(logOutHandler);
        
        Icon editIcon = new Icon("PENCIL", "1em");
        editIcon.setTextFill(Color.WHITE);
        editIcon.setPadding(new Insets(0,10,0,0));
        
        btnEdit.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: "  + accentColor + ";");
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
        int year, month, day;
        year = Integer.parseInt(nums[0]);
        day = Integer.parseInt(nums[1]);
        month = Integer.parseInt(nums[2]);
        return LocalDate.of(year, month, day);
    }
    
    private void showUserLibrary()
    {
        if (allBooks != null && allBooks.size() > 0 )
        {  
            ListCards list = new ListCards();
            list.createMatrixList(allBooks, false);
            list.relocate(320, 70);
            addComponent(list);
        //infoCard.getChildren().add(list.getList());
        } else
        {
            ImageView noLibrary = new ImageView(new Image("/Icons/noSaved.png"));
            noLibrary.relocate(320, 80);
            addComponent(noLibrary);
            
            JFXButton btnAdd = new JFXButton("EXPLORE");
            btnAdd.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: " + accentColor + ";");
            btnAdd.relocate(623, 480);
            EventHandler<ActionEvent> editHandler = (ActionEvent actionEvent) -> {
                HomePage home = new HomePage();
                Stage homeStage = home.getStage();
                homeStage.show();
                this.getScene().getWindow().hide();
            };
            btnAdd.setOnAction(editHandler);
            
            addComponent(btnAdd);
        }
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
        this.initStyle(StageStyle.UNDECORATED); 
        this.setSize(1110, 700);
        createView();
        addComponents();
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, width, height);
        scene.getStylesheets().add("/style/jfoenix-components.css");
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
