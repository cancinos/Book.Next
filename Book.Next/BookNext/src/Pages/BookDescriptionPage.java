/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import Classes.CBook;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    boolean saved = false, favorite = false;
 
    
    public void addComponents(CBook book)
    {
        giantCard infoCard = new giantCard(700,550);
        infoCard.createCard();
        infoCard.relocate(50, 50);
        
        // <editor-fold defaultstate="collapsed" desc="Book basic info">
        ImageView iv1 = new ImageView(new Image(book.getBook_image()));
        iv1.relocate(20, 20);
        
        Icon favHeart;
        if (favorite == false) // -OJO- tomar de bdd este dato
            favHeart = new Icon("HEART_ALT", "3em"); else
            favHeart = new Icon("HEART", "3em");
        
        favHeart.setCursor(Cursor.HAND);
        favHeart.setTextFill(Color.web("#FF5252"));
        favHeart.relocate(610, 30);
        favHeart.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{    
            // -OJO- cambiar a bdd este dato
            favorite = !favorite;
            if (favorite)
                favHeart.icon(AwesomeIcon.HEART); else
                favHeart.icon(AwesomeIcon.HEART_ALT);

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
        
        String info = book.getBook_authorsStr() + "\n" + book.getBook_publisher() + "\n" + book.getBook_publishYear();
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
        btnSave.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    saved = !saved;
                    if (!saved) //Until the user exit this page, we'll refresh all the data within the DB
                    {
                        // -OJO- update en bdd que ya no tiene guardado el libro
                        btnSave.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color:#FF5252");
                        btnSave.setText("SAVE BOOK");
                    } else
                    {
                        // -OJO- update en bdd que ya tiene guardado el libro
                        btnSave.setText("SAVED"); 
                        btnSave.setStyle("-fx-font-size: 14; -fx-text-fill:WHITE; -fx-background-color:#4CAF50");
                    }
                    
                }
            });
        
        Rectangle separator = new Rectangle(500,1);
        separator.relocate(165, 213);
        separator.setFill(Color.web("#B6B6B6"));
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Book description">
        Label description = new Label(book.getBook_description());
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
        
        showRating(infoCard); //Shows total users rating
        
        // <editor-fold defaultstate="collapsed" desc="Actuser's rating">
        RatingStars rateStars = new RatingStars("4em");
        HBox stars = rateStars.showEmptyStars(3);//we need to Get actual user rating... if haven't rating, shows 0 stars
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
                // -OJO- guardar rating en base de datos
            }
           });  
        // </editor-fold>
        
        HBox keyWords = addKeyWords();
        
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
        
        //similarBooks = getSimilarBooks(actBook); 
        ListCards list = new ListCards();
        list.createVerticalList();
        //list.createVerticalList(similarBooks); -OJO- tomar los libros similares
        addComponent(list.getList());
    }
    
    private HBox addKeyWords()
    {
        HBox hbox = new HBox(10);
        List<String> keyWords = new ArrayList();
        keyWords.add("gunter");
        keyWords.add("glieben");
        keyWords.add("glauchen");
        keyWords.add("globen");
        
        Label newHashtag;
        for (String keyWord : keyWords) {
            newHashtag = new Label("#" + keyWord);
            newHashtag.setStyle("-fx-background-color: #C4DCFF; -fx-text-fill: #5E69FF; -fx-font-size:16px;");
            hbox.getChildren().add(newHashtag);
        }
        return hbox;
    }
    
    private void showRating(giantCard infoCard)
    {
        // -OJO- tomar cuántos votos tiene de cada estrella el libro...
        int num5 = 3;
        int num4 = 8;
        int num3 = 3;
        int num2 = 0;
        int num1 = 1;
        int numRating = num5+num4+num3+num2+num1;
        double avg = getAverage(num5, num4, num3, num2, num1, numRating);
        
        Label lblAv = new Label(String.valueOf(avg));
        lblAv.setStyle("-fx-font-size:55px;");
        lblAv.relocate(170, 250);
        
        Label lbl1 = new Label();
        lbl1.setText(String.valueOf(num1));
        lbl1.setStyle("-fx-font-size:11px;");
        lbl1.relocate(330, 322);
       
        Label lbl2 = new Label();
        lbl2.setText(String.valueOf(num2));
        lbl2.setStyle("-fx-font-size:11px;");
        lbl2.relocate(330, 302);
        
        Label lbl3 = new Label();
        lbl3.setText(String.valueOf(num3));
        lbl3.setStyle("-fx-font-size:11px;");
        lbl3.relocate(330, 283);
        
        Label lbl4 = new Label();
        lbl4.setText(String.valueOf(num4));
        lbl4.setStyle("-fx-font-size:11px;");
        lbl4.relocate(330, 264);
        
        Label lbl5 = new Label();
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
        infoCard.getChildren().addAll(lblAv,stars, lbl1, lbl2, lbl3, lbl4, lbl5);
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
