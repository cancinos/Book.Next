/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
import Classes.CStaticInfo;
import Classes.CUser;
import NaiveBayes.NaiveBayes;
import UI.HCard;
import UI.ListCards;
import UI.NavigationDrawer;
import UI.RatingStars;
import UI.giantCard;
import UI.mainToolbar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *  Stage used for see an specific book info.
 * @author jcdur
 */
public class BookDescriptionPage extends Stage{
    
    private BorderPane page = new BorderPane();
    private double width, height;
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private List<CBook> similarBooks;
    private CUser actUser;
    private int numUser;
    private giantCard infoCard;
    boolean saved = false, favorite = false;
    
    public BookDescriptionPage()
    {
        actUser = CStaticInfo.loggedUser;
        numUser = CStaticInfo.connection.getUserId(actUser.gerUsername());
        
        //doesThisRelationExists(int id, String ISBN)
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
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 0;", "Book Info");
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
    
 
    
    public void addComponents(CBook book)
    {
        boolean exists = CStaticInfo.connection.doesThisRelationExists(numUser, book.isbn);
        if (!exists) //means that this book-user relation is new
        {
            CStaticInfo.connection.setUserBook(numUser, book.isbn);
        } else
            CStaticInfo.connection.updateView(numUser, book.isbn); //view++
        
        saved = CStaticInfo.connection.isThisBookSaved(numUser, book.getBookId()); 
        favorite = CStaticInfo.connection.isThisBookFavorite(numUser, book.getBookId()); 
        infoCard = new giantCard(700,550);
        infoCard.createCard();
        infoCard.relocate(50, 50);
        
        // <editor-fold defaultstate="collapsed" desc="Book basic info">
        String strImage = book.getBook_image();
        ImageView iv1;
        if (strImage.compareTo("/Icons/No_Cover.jpg") == 0)
            iv1 = new ImageView(new Image(strImage)); else
            iv1 = new ImageView(new Image("/BookCovers/" + book.isbn + ".jpg"));
        iv1.setFitHeight(198);
        iv1.setFitWidth(128);
        iv1.relocate(20, 20);
        
        Icon favHeart;
        if (favorite == false)
            favHeart = new Icon("HEART_ALT", "3em"); else
            favHeart = new Icon("HEART", "3em");
        
        favHeart.setCursor(Cursor.HAND);
        favHeart.setTextFill(Color.web("#FF5252"));
        favHeart.relocate(610, 30);
        favHeart.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            favorite = !favorite;
            if (favorite)
            {
                favHeart.icon(AwesomeIcon.HEART);
                CStaticInfo.connection.updateLiked(numUser, book.isbn, 1);
            } else
            {
                favHeart.icon(AwesomeIcon.HEART_ALT);
                CStaticInfo.connection.updateLiked(numUser, book.isbn, 0);
            }
            
        }); 
        
        
        VBox infoBox = new VBox();
        infoBox.setMaxHeight(150);
        infoBox.setMinHeight(150);
        infoBox.setMinWidth(450);
        
        Label lblName = new Label(book.getBook_name());
        lblName.setStyle("-fx-font-size:25px; ");
        lblName.setWrapText(true);
        lblName.setMaxWidth(450);
        lblName.setMaxHeight(200);
        String authors = book.getBook_authorsStr();
        if (authors.endsWith(","))
            authors = authors.substring(0, authors.length() - 1);
        String info = authors + "\n" + book.getBook_publisher() + "\n" + book.getBook_publishYear();
        Label moreInfo = new Label("by " + info);
        moreInfo.setStyle("-fx-font-size:15px;");
        moreInfo.setWrapText(true);
        
        JFXButton btnSave = new JFXButton();
        
        if (saved) //Get if user has saved this book
        {
            btnSave.setText("SAVED"); 
            btnSave.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color:#4CAF50");
        } else
        {
            btnSave.setText("SAVE BOOK");
            btnSave.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color:#FF5252");
        }
        
        btnSave.getStyleClass().add("button-raised");
        btnSave.setMaxWidth(100);
        btnSave.relocate(565, 140);
        btnSave.setOnAction((ActionEvent actionEvent) -> {
            saved = !saved;
            if (!saved) //Until the user exit this page, we'll refresh all the data within the DB
            {
                CStaticInfo.connection.updateSaved(numUser, book.getBookId(), 0);
                btnSave.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color:#FF5252");
                btnSave.setText("SAVE BOOK");
            } else
            {
                CStaticInfo.connection.updateSaved(numUser, book.getBookId(), 1);
                btnSave.setText("SAVED");
                btnSave.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color:#4CAF50");
            }
        });
        
        Rectangle separator = new Rectangle(500,1);
        separator.relocate(165, 213);
        separator.setFill(Color.web("#B6B6B6"));
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Book description">
        String emptyStr = "                                ";
        String strDesc = book.getBook_description();
        if (strDesc.isEmpty())
                strDesc = "\n\n\n" + emptyStr + emptyStr + "Description not available";
        Label description = new Label(strDesc);
        description.setTextAlignment(TextAlignment.JUSTIFY);
        description.setStyle("-fx-font-size:15px;");
        description.setWrapText(true);
        description.setMaxWidth(625);
        
        ScrollPane infoPane = new ScrollPane();
        infoPane.setStyle("-fx-background-color:TRANSPARENT;");
        infoPane.setBorder(Border.EMPTY);
        infoPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        infoPane.setContent(description);
        infoPane.relocate(20, 380);
        infoPane.setPrefSize(648, 150);
        
        Rectangle separator2 = new Rectangle(500,1);
        separator2.relocate(165, 363);
        separator2.setFill(Color.web("#B6B6B6"));
        // </editor-fold>
        
        showRating(book); //Shows total users rating
        
        // <editor-fold defaultstate="collapsed" desc="Actuser's rating">
        int rating = CStaticInfo.connection.getUserRating(book.getBookId(), numUser);
        RatingStars rateStars = new RatingStars("4em");
        HBox stars = rateStars.showRatingStars(rating);//we need to Get actual user rating... if haven't rating, shows 0 stars
        stars.relocate(400, 290);
        stars.setDisable(true);
        
        Icon editIcon = new Icon("PENCIL", "1.5em");
        editIcon.setTextFill(Color.web("#727272"));
        JFXButton btnEdit = new JFXButton("Edit rating");
        btnEdit.setGraphic(editIcon);
        btnEdit.relocate(465, 240);
        btnEdit.setStyle("-fx-text-fill:BLACK; -fx-backgroud-color:WHITE;");
        btnEdit.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            if (stars.isDisable())
            {
                stars.setDisable(false); 
                Icon saveIcon = new Icon("SAVE", "1.5em");
                btnEdit.setGraphic(saveIcon);
                btnEdit.setText("Save rating");
                
            } else
            {
                stars.setDisable(true);
                Icon editIcon2 = new Icon("PENCIL", "1.5em");
                btnEdit.setGraphic(editIcon2);
                btnEdit.setText("Edit rating");
                CStaticInfo.connection.updateRating(numUser, book.getBookId(), rateStars.getRating());
                editRating(book);
            }
           });  
        // </editor-fold>
        
        FlowPane keyWords = addKeyWords(book);
        
        infoBox.getChildren().addAll(lblName, moreInfo, keyWords);
        infoBox.setSpacing(10);
        infoBox.relocate(180, 30);
        infoCard.getChildren().addAll(iv1, infoBox, btnSave, favHeart, separator, infoPane, separator2, stars, btnEdit);
        addComponent(infoCard);
        addSimilarRecomendation(book);

    }
    
    private void addSimilarRecomendation(CBook actBook)
    {
        Label moreLabel = new Label("Similar");
        moreLabel.setStyle("-fx-font-size:22px;");
        moreLabel.relocate(810, 60);
        addComponent(moreLabel);
        
        JFXButton btnMore = new JFXButton("More");
        //btnMore.getStyleClass().add("button-raised");
        btnMore.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color: #FFC107;");
        btnMore.setMaxSize(150, 38);
        btnMore.setPrefSize(55, 38);
        btnMore.setMinSize(20, 38);
        btnMore.relocate(990, 55);
        //addComponent(btnMore); --> -OJO- por el momento, no es necesario
        NaiveBayes naive = new NaiveBayes();
        similarBooks = naive.recommend(actBook, CStaticInfo.connection, 4);
        ListCards list = new ListCards();
        //list.createVerticalList();
        list.createVerticalList(similarBooks);
        addComponent(list.getList());
    }
    
    private String deleteWirdGenres(String genres)
    {
        while (genres.endsWith(","))
        {
            genres = genres.substring(0, genres.length() - 1);
        }
        
        String[] separated = genres.split(", ");
        String allGenres = "";
        for (String oneBook : separated)
        {
            switch (oneBook.toLowerCase())
            {
                case "women": 
                case "history":
                case "biography":
                case "juvenile fiction":
                case "social life and customs":
                    allGenres += oneBook + ",";
            }
                    
        }
        return allGenres.substring(0, allGenres.length() - 1);
    }
    
    private FlowPane addKeyWords(CBook book)
    {
        FlowPane hbox = new FlowPane();
        hbox.setMaxSize(400, 150);
        hbox.setVgap(10);
        hbox.setHgap(5);
        String categories = deleteWirdGenres(book.getBook_genre()); 
        String[] keyWords = categories.split(",");
        
        Label newHashtag;
        for (String keyWord : keyWords) {
            newHashtag = new Label("#" + keyWord);
            newHashtag.setStyle("-fx-background-color: #C4DCFF; -fx-text-fill: #5E69FF; -fx-font-size:16px;");
            hbox.getChildren().add(newHashtag);
        }
        
        return hbox;
    }
    
    Label lblAv, lbl1, lbl2, lbl3, lbl4, lbl5;
    
    /**
     * This method is used for showing for the first time user's rating
     * @param book 
     */
    private void showRating(CBook book)
    {
        int num5 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "5");
        int num4 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "4");
        int num3 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "3");
        int num2 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "2");
        int num1 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "1");
        int numRating = num5+num4+num3+num2+num1;
        double avg = getAverage(num5, num4, num3, num2, num1, numRating);
        
        lblAv = new Label(String.valueOf(avg));
        lblAv.setStyle("-fx-font-size:55px;");
        lblAv.relocate(170, 250);
        
        lbl1 = new Label();
        lbl1.setText(String.valueOf(num1));
        lbl1.setStyle("-fx-font-size:11px;");
        lbl1.relocate(330, 322);
       
        lbl2 = new Label();
        lbl2.setText(String.valueOf(num2));
        lbl2.setStyle("-fx-font-size:11px;");
        lbl2.relocate(330, 302);
        
        lbl3 = new Label();
        lbl3.setText(String.valueOf(num3));
        lbl3.setStyle("-fx-font-size:11px;");
        lbl3.relocate(330, 283);
        
        lbl4 = new Label();
        lbl4.setText(String.valueOf(num4));
        lbl4.setStyle("-fx-font-size:11px;");
        lbl4.relocate(330, 264);
        
        lbl5 = new Label();
        lbl5.setText(String.valueOf(num5));
        lbl5.setStyle("-fx-font-size:11px;");
        lbl5.relocate(330, 245);
        
        VBox stars = new VBox();
        stars.getChildren().add(new ImageView(new Image("/Icons/5stars.png")));
        stars.getChildren().add(new ImageView(new Image("/Icons/4stars.png")));
        stars.getChildren().add(new ImageView(new Image("/Icons/3stars.png")));
        stars.getChildren().add(new ImageView(new Image("/Icons/2stars.png")));
        stars.getChildren().add(new ImageView(new Image("/Icons/1stars.png")));
        stars.relocate(250, 245);
        
        infoCard.getChildren().addAll(lblAv, stars, lbl1, lbl2, lbl3, lbl4, lbl5);
    }
    
    /**
     * This method is used after clicking on the "Save rating" button.
     * It upgrades all the rating labels.
     * @param book 
     */
    private void editRating(CBook book)
    {
        int num5 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "5");
        int num4 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "4");
        int num3 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "3");
        int num2 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "2");
        int num1 = CStaticInfo.connection.getGlobalRating(book.getBookId(), "1");
        int numRating = num5+num4+num3+num2+num1;
        double avg = getAverage(num5, num4, num3, num2, num1, numRating);
        
        lblAv.setText(String.valueOf(avg));
        lbl1.setText(String.valueOf(num1));
        lbl2.setText(String.valueOf(num2));
        lbl3.setText(String.valueOf(num3));
        lbl4.setText(String.valueOf(num4));
        lbl5.setText(String.valueOf(num5));
        
        CStaticInfo.connection.setBookRating(book.isbn, Double.toString(avg));
    }
    
        
    private double getAverage(double s5, double s4, double s3, double s2, double s1, double cont)
    {
        double average = (5*s5 + 4*s4 + 3*s3 + 2*s2 + 1*s1)/cont;
        average *= 10;
        average = Math.round(average);
        average /= 10;
        return average;
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
     * @param book
     * @return Styled beautifull stage
     */
    public Stage getStage(CBook book)
    {
        createView();
        this.initStyle(StageStyle.UNDECORATED); 
        similarBooks = new ArrayList(); //Aqui mando a llamar el método de quemé
        addComponents(book);
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, width, height);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        this.setScene(scene);
        return this;
    }
    
}
