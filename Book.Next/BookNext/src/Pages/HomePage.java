/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import ANN.ANN;
import Classes.CBook;
import Classes.CStaticInfo;
import UI.ListCards;
import UI.NavigationDrawer;
import UI.VCard;
import UI.mainToolbar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcdur
 */
public class HomePage extends Stage{
    
    private BorderPane page = new BorderPane();
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private List<CBook> favBooks, suggestedBooks;
    private ScrollPane parentScroll = new ScrollPane();
    
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
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 0;", "Home");
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
    
    private void addFavoriteBooks()
    {
        // <editor-fold defaultstate="collapsed" desc="Favorite books">
//        Label lblFav = new Label("Favorite books");
//        lblFav.setStyle("-fx-font-size:24px;");
//        lblFav.relocate(150, 10);
//        addComponent(lblFav);
//        HBox favHBox = new HBox(30);
//        VCard newCard;
//        
//        if (favBooks == null || favBooks.isEmpty())
//        {
//            ImageView noBooks = new ImageView(new Image("/Icons/noBooks.png"));
//            noBooks.relocate(150, 55);
//            addComponent(noBooks);
//        } else
//        {
//            favScroll = new ScrollPane();
//            favScroll.setPrefSize(800,275);
//            favScroll.setStyle("-fx-padding: 0 0 0 20; -fx-background-color:TRANSPARENT;");
//            favScroll.setBorder(Border.EMPTY);
//            favScroll.relocate(150,42);
//            for (CBook book : favBooks) { //Debería ser el top 10...
//                newCard = new VCard(128,250);
//                newCard.createSimpleCard(book);
//                favHBox.getChildren().add(newCard);
//            }
//            favScroll.setContent(favHBox);
//            addComponent(favScroll);
//        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Suggested books">
        //Label lblSug = new Label ("Recommended for you");
//        lblSug.setStyle("-fx-font-size:24px;");
//        lblSug.relocate(150, 330);
//        addComponent(lblSug);
//        HBox sugHBox = new HBox(30);
//        suggestedScroll = new ScrollPane();
//        suggestedScroll.setPrefSize(800,275);
//        suggestedScroll.setStyle("-fx-padding: 0 0 0 20; -fx-background-color:TRANSPARENT;");
//        suggestedScroll.setBorder(Border.EMPTY);
//        suggestedScroll.relocate(150, 363);
//        for (CBook book : suggestedBooks) { //Debería ser el top 10...
//                newCard = new VCard(128,250);
//                newCard.createSimpleCard(book);
//                sugHBox.getChildren().add(newCard);
//            }
//        suggestedScroll.setContent(sugHBox);
//        addComponent(suggestedScroll);
        
        // </editor-fold>
    }
    
    private void addSuggestedBooks()
    {
        
    }
    

    
    private ScrollPane addGenreBooks(String genre)
    {
        ListCards actScroll = new ListCards();
        List<CBook> actBooks = randomList(CStaticInfo.connection.getBooksByGenre(genre));
        actScroll.createHorizontalList(actBooks);
        return actScroll;
    }
    
    private ScrollPane addRecommended(List<CBook> books)
    {
        ListCards actScroll = new ListCards();
        actScroll.createHorizontalList(books);
        return actScroll;
    }
    
    /**
     * This method is used for adding an specific element to page content
     * @param element added element
     */
    private void addComponent(Node element)
    {
        navDrawer.getContent().getChildren().add(element);
    }
    
       /*
    Popular categorie: Fiction
Popular categorie: History
Popular categorie: Biography
Popular categorie: Juvenile fiction
Popular categorie: Social life and customs

    */
    
    private List<CBook> randomList(List<CBook> list)
    {
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
        Collections.shuffle(list, new Random(seed));
        return list;
    }
    
    private void addComponents() throws SQLException
    {
        /**
         * Recommended for you
         */
        
        ANN recommended = new ANN();
        int userId = CStaticInfo.connection.getUserId(CStaticInfo.loggedUser.gerUsername());
        List<String> recommendedISBN = recommended.getRecommendations(userId); //Returns recommeded books (just isbn)
        List<CBook> recommendedBooks = new ArrayList();
        for (String recommendedBook : recommendedISBN) { //Converts isbn to books
            CBook act = CStaticInfo.connection.getBookFromISBN(recommendedBook);
            recommendedBooks.add(act); 
        }
        VBox allScrolls = new VBox(1);
        parentScroll.setPrefSize(980, 650);
        
        // Adds recommended books--------------------------------
        Pane recPane = new Pane();
        recPane.setPrefSize(980,310);
        recPane.setStyle("-fx-background-color: TRANSPARENT;");
        Label lblRec = new Label("Recommended for you") ;
        lblRec.setStyle("-fx-font-size:20px");
        lblRec.relocate(5, 5);
        ScrollPane recScroll = addRecommended(randomList(recommendedBooks));
        recScroll.relocate(5, 35);
        recPane.getChildren().addAll(lblRec, recScroll);
        allScrolls.getChildren().add(recPane);
        //-------------------------------------------------------
        
        String[] allGenres = {"History", "Biography", "Juvenile fiction", "Social life and customs", "Women"};
        for (int i = 0; i < 5; i++) {
            Pane actPane = new Pane();
            actPane.setPrefSize(980,310);
            actPane.setStyle("-fx-background-color: TRANSPARENT;");
            Label lblGenre = new Label(allGenres[i] + " books") ;
            lblGenre.setStyle("-fx-font-size:20px");
            lblGenre.relocate(5, 5);
            ScrollPane actScroll = addGenreBooks(allGenres[i].toLowerCase());
            actScroll.relocate(5, 35);
            actPane.getChildren().addAll(lblGenre, actScroll);
            allScrolls.getChildren().add(actPane);
        }
        parentScroll.setContent(allScrolls);
        parentScroll.setStyle("-fx-background-color: TRANSPARENT;");
        parentScroll.setBorder(Border.EMPTY);
        parentScroll.relocate(80, 10);
        
        //addSuggestedBooks();
        addComponent(parentScroll);
    }
    
    public Stage getStage()
    {
        createView();
        try {
            addComponents();
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.initStyle(StageStyle.UNDECORATED); 
        this.page.setCenter(this.navDrawer);
        this.page.setTop(this.toolBar);
        Scene scene = new Scene(this.page, 1100, 700);
        scene.getStylesheets().add("/style/jfoenix-components.css");
        this.setScene(scene);
        return this;
    }
    
    public Pane getContent()
    {
        createView();
        try {
            addComponents();
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return navDrawer.getContent();
    }
    
}
