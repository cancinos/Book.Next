package booknext;

import ANN.ANN;
import Classes.CBook;
import Classes.CStaticInfo;
import Classes.CUser;
import Classes.ISBNConverter;
import Classes.MysqlConnection;
import Pages.BookDescriptionPage;
import Pages.EditProfile;
import Pages.bookSelection;
import UI.Button;
import UI.NavigationDrawer;
import UI.giantCard;
import UI.mainToolbar;
import UI.textField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.Icon;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import NaiveBayes.*;
import Pages.HomePage;
import booknext.DatabaseSetup;
import java.util.Random;
import org.json.JSONException;

/**
 *
 * @author jcdur
 */
public class BookNext extends Application {

    // <editor-fold defaultstate="collapsed" desc="Global variables">
    private Desktop desktop = Desktop.getDesktop();
    private Pane page = new Pane();
    private giantCard card;
    private JFXTabPane tabPane;
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
    private ImageView profile_pic;
    private JFXTextField new_name;
    private JFXTextField new_user;
    private JFXPasswordField new_pass;
    private JFXTextField country;
    private JFXDatePicker date;
    private Image image_user;
    private List<CBook> allBooks = new ArrayList();
    private String imageURL = "/Icons/user.PNG";
    MysqlConnection connection;
    NaiveBayes naive = new NaiveBayes();
    private Stage thisStage;
    boolean shouldTrainBayes = false;
    boolean shouldTestBayes = false;
    boolean shouldDownload = false;
    boolean shouldLoadBooks = false;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Other methods">
    /**
     * This Function reads isbn.txt file and creates every single isbn to a
     * CBook object
     *
     * @param parent stage where is beeing called
     * @return strng whit isbn separated by commas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String readFile(Stage parent) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(parent);
        String everything = "";
        if (selectedFile != null) {
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
            } finally {
                br.close();
            }
        }

        return everything;

    }

    public List<String> readFileLines(Stage parent) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(parent);
        String everything = "";
        List<String> allLines = new ArrayList();
        if (selectedFile != null) {
            BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsoluteFile()));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(",");
                    allLines.add(line);
                    line = br.readLine();
                }
                everything = sb.toString();
            } finally {
                br.close();
            }
        }

        return allLines;

    }

    public void convertAllBooks(String allIsbn) {
        allIsbn = allIsbn.substring(0, allIsbn.length() - 1);
        String[] separated = allIsbn.split(",");
        CBook actBook;
        int cont = 0;
        for (String str : separated) //
        {
            System.out.print(cont + " - " + str);
            try {
                actBook = new ISBNConverter().isbnToBook(str, cont);
                //connection.addBook(actBook);
                allBooks.add(actBook);
                cont++;
            } catch (IOException | JSONException ex) {
                Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
                cont++;
                System.out.print(" not finished\n");
            }
        }
        CStaticInfo.allBooks = allBooks;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Data base methods">
    private void getUserBooks() {
        try {
            convertAllBooks(readFile(thisStage));
        } catch (IOException ex) {
            Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
        }
        CStaticInfo.usersBooks = allBooks; // -OJO- debe tomar los libros del usuario...
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="View methods">
    //Create Card with Components   
    public void createView() {

        card = new giantCard(400, 500);
        card.createCard();
        card.setEffect(new DropShadow(10d, 5d, 0d, Color.web("#212121")));
        card.setStyle("-fx-background-color:#FFFFFF; -fx-background-radius:1em");
        card.relocate(150, 100);
        card.getChildren().add(background);
        card.getChildren().add(tabPane);
        card.getChildren().add(icon);
        card.getChildren().add(save_icon);
        page.getChildren().add(card);
    }

    public void createLogin() {
        logbox = new VBox();
        logbox.setStyle("-fx-alignment: center");
        logbox.setSpacing(35);
        logbox.setPrefSize(400, 250);
        fields = new textField();
        user = fields.validateTextField("Username", "User name can't be empty", "16");
        pass = fields.PasswordField("Password", "Password can't be empty", "16");
        logbox.getChildren().add(user);
        logbox.getChildren().add(pass);
    }

    //Create components for new User
    public void createUser(Stage theStage) {
        //scrollpane
        scrollpane = new ScrollPane();
        scrollpane.setPrefSize(400, 250);
        scrollpane.setStyle("-fx-padding: 10 0 0 0; -fx-background-color:TRANSPARENT;");
        scrollpane.setBorder(Border.EMPTY);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //User box with components
        userbox = new VBox();
        userbox.setStyle("-fx-alignment: center; -fx-background-color:WHITE;");
        userbox.setSpacing(35);
        userbox.setPrefSize(400, 250);
        //Text fields
        fields = new textField();
        new_name = fields.textField("Full Name", "16");
        new_user = fields.validateTextField("Username", "User name can't be empty", "15");
        new_pass = fields.PasswordField("Password", "Password can't be empty", "15");
        country = fields.textField("Country", "16");
        date = new JFXDatePicker();
        date.setEditable(false);
        profilePicture("/Icons/user.PNG", theStage);

        userbox.getChildren().add(new_name);
        userbox.getChildren().add(new_user);
        userbox.getChildren().add(new_pass);
        userbox.getChildren().add(country);
        userbox.getChildren().add(date);

        scrollpane.setContent(userbox);
    }

    public void createTabs(Stage theStage) {

        //Create Tab with log in box
        createLogin();
        tabPane = new JFXTabPane();
        tabPane.setStyle("-fx-background-color:WHITE;");
        title = new Label("Sign in");
        title.setTextFill(Color.WHITE);
        title.setStyle("-fx-font-size:15px;");
        tab = new Tab();
        tab.setStyle("-fx-background-color:TRANSPARENT; -fx-alignment:center; -fx-font-weight: bold");
        tab.setContent(logbox);
        tab.setText("");
        tab.setGraphic(title);

        tabPane.getTabs().add(tab);

        // Create Tab with sign up box
        createUser(theStage);
        title = new Label("Sign up");
        title.setTextFill(Color.WHITE);
        title.setStyle("-fx-font-size:15px;");
        tab = new Tab();
        tab.setStyle("-fx-background-color:TRANSPARENT; -fx-alignment:center; -fx-font-weight: bold");
        tab.setContent(scrollpane);
        tab.setText("");
        tab.setGraphic(title);

        tabPane.getTabs().add(tab);
        tabPane.relocate(0, 200);

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            if (tabPane.getSelectionModel().isSelected(0) == true) {
                icon.setVisible(true);
                save_icon.setVisible(false);
            } else {
                save_icon.setVisible(true);
                icon.setVisible(false);
            }
        });
    }

    public void loginPic() {

        image_user = new Image("Icons/login.jpg");
        background = new ImageView(image_user);
        background.setFitHeight(200);
        background.setFitWidth(400);
        background.relocate(0, 0);
        background.setStyle("-fx-background-radius:1em");

    }

    public void createProfilePicture(Stage theStage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(theStage);

        if (file != null) {

            image_user = new Image(file.toURI().toString());
            imageURL = file.toURI().toString();
            profile_pic.setImage(image_user);
        }

    }

    public void profilePicture(String picture, Stage theStage) {
        // <editor-fold defaultstate="collapsed" desc="Circle Image">

        profile_pic = (new ImageView(new Image(picture)));
        Rectangle square = new Rectangle();
        square.setWidth(150);
        square.setHeight(150);
        profile_pic.setClip(square);
        Circle clip = new Circle();
        clip.setCenterX(75);
        clip.setCenterY(75);
        clip.setRadius(75);
        profile_pic.fitWidthProperty().bind(square.widthProperty());
        profile_pic.fitHeightProperty().bind(square.heightProperty());
        profile_pic.setClip(clip);

        profile_pic.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                createProfilePicture(theStage);
            }

        });

        userbox.getChildren().add(profile_pic);
        // </editor-fold>  
    }

    public void createToggle(Stage theStage) {

        // <editor-fold desc="Validate Icon">
        icon = new JFXToggleNode();
        Icon value = new Icon("ARROW_RIGHT", "2em");
        value.setAlignment(Pos.CENTER);
        value.setTextFill(Color.WHITE);
        icon.setGraphic(value);
        icon.setStyle("-fx-background-radius: 4em; -fx-background-color:#03A9F4;");
        icon.setPrefSize(60, 60);
        icon.relocate(375, 415);

        icon.setOnAction((ActionEvent actionEvent) -> {
            validateLogin(theStage);
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
        save_icon.relocate(375, 450);
        save_icon.setVisible(false);

        save_icon.setOnAction((ActionEvent actionEvent) -> {
            try {
                validateNewUser(theStage);
            } catch (SQLException ex) {
                Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // </editor-fold>

    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                //   new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(BookNext.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Bayes methods">
    public void trainBayes() {

        List<CBook> books = connection.getBooks();
        for (CBook book : books) {
            naive.train(book, connection);
        }
    }

    public void testBayes() {
        List<CBook> books = connection.getBooks();
        int randIndex = new Random().nextInt(books.size());
        CBook trainingBook = books.get(randIndex);
        naive.recommend(trainingBook, connection, 4);
    }

    public void loadBooks(Stage primaryStage) {
        try {
            List<String> lines = readFileLines(primaryStage);
            lines.remove(0);
            new DatabaseSetup().linesToBooks(lines, connection);
        } catch (IOException ex) {
            Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Log In, Sign Up">
    public void validateLogin(Stage theStage) {
        if (shouldTrainBayes) {
            trainBayes();
        }

        if (shouldTestBayes) {
            testBayes();
        }

        if (shouldLoadBooks) {
            loadBooks(theStage);
        }

        if (user.getText().length() > 3 & pass.getText().length() > 4) {
            CUser uss = connection.consultUser(user.getText());
            if (uss != null && pass.getText().equals(uss.getUser_password())) { try {
                //Entering here means that the user was succesfully logged
                // try {
                CStaticInfo.loggedUser = uss;
                ANN a = new ANN();
                a.getRecommendations(2);
                EditProfile mainPage = new EditProfile();
                Stage loginStage = mainPage.getStage();
                loginStage.show();
                thisStage.getScene().getWindow().hide();
                //} catch (SQLException ex) {
                //  Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
                //}
                } catch (SQLException ex) {
                    Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Incorrect Password or UserName");
                alert.setContentText("Please enter a existing user name with correct password");
                alert.showAndWait();
            }
        } else {
            if (user.getText().length() < 4) {
                user.validate();
            } else {
                if (pass.getText().length() < 4) {
                    pass.validate();
                } else {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Error");
                    alert.setHeaderText("User form not complete");
                    alert.setContentText("Please complete user form");
                    alert.showAndWait();
                }
            }
        }
    }

    public void validateNewUser(Stage theStage) throws SQLException {

        if (new_user.getText().length() > 3 & new_pass.getText().length() > 5 & new_name.getText().length() > 4 & country.getLength() > 4) {
            CUser uss = connection.consultUser(new_user.getText());

            if (uss != null) { //If uss !=null means that the username is already registered

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("User Already Exist");
                alert.setContentText("Please select another user");
                alert.showAndWait();

            } else {
                uss = new CUser(new_user.getText(), new_name.getText(), new_pass.getText(), date.getValue().toString().replace('-', '/'), imageURL, country.getText());
                boolean validate = connection.addNewUser(uss.gerUsername(), uss.getUser_fullName(), uss.getUser_birthday(), uss.getUser_password(),
                        uss.getUser_image(), uss.getUser_country());
                validate = true;
                if (validate = true) //if this is true, means that all the fields are correct.
                {
                    CStaticInfo.loggedUser = uss;
                    CStaticInfo.usersBooks = new ArrayList(); //New user, empty book list 
                    bookSelection book = new bookSelection();
                    Stage loginStage = book.getStage();
                    loginStage.show();
                    theStage.getScene().getWindow().hide();

                } else {
                    if (new_user.getText().length() < 4) {
                        new_user.validate();
                    } else {
                        if (new_pass.getText().length() < 4) {
                            new_pass.validate();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setHeaderText("User form not complete");
                            alert.setContentText("Please complete user form");
                            alert.showAndWait();
                        }
                    }
                }
            }
        }
    }

    // </editor-fold>
    
    @Override
    public void start(Stage primaryStage) {
        thisStage = primaryStage;
        try {

            connection = new MysqlConnection();
            connection.connect();
            CStaticInfo.connection = connection;

            primaryStage.initStyle(StageStyle.UNDECORATED);
            page.setStyle("-fx-background-color:#455A64");
            Scene scene = new Scene(page, 700, 700);
            scene.getStylesheets().add("/style/jfoenix-components.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            loginPic();
            createToggle(primaryStage);
            createTabs(primaryStage);
            createView(); //Contains all the components
            primaryStage.show();
        } catch (SQLException ex) {
            Logger.getLogger(BookNext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private String dateToString(LocalDate date)
    {
        String fixedDate;
        fixedDate = String.valueOf(date.getDayOfMonth());
        fixedDate += "/" + String.valueOf(date.getMonthValue());
        fixedDate += "/" + String.valueOf(date.getYear());
        return fixedDate;
    }
    
    private LocalDate stringToDate(String strDate) {
        String[] nums = strDate.split("/");
        return LocalDate.of(Integer.parseInt(nums[2]), Integer.parseInt(nums[1]), Integer.parseInt(nums[0]));
    }

}
