/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pages;

import ANN.ANN;
import Classes.Book;
import Classes.CBook;
import Classes.CStaticInfo;
import Pages.LandingPage;
import UI.NavigationDrawer;
import UI.mainToolbar;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.fontawesome.Icon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 *
 * @author jcdur
 */
public class DiscoverPage extends Stage {
    
    private NavigationDrawer navDrawer;
    private mainToolbar toolBar;
    private final BorderPane page = new BorderPane();
    
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
            toolBar = new mainToolbar(1100, 60, "-fx-background-color: #F44336; -fx-padding: 0 0 0 0;", "Discover");
            toolBar.createToolbar();
            //Setting onHamburgerClick
            toolBar.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                            navDrawer.toggle(navDrawer.getSideMenu());
                    });
        // </editor-fold>
    }
    
    
    private Pane showBooksMatrix(List<CBook> booksToShow)
    {        
        try {

                JFXTreeTableColumn<Book, String> isbnColumn = new JFXTreeTableColumn<>("ISBN");
                isbnColumn.setPrefWidth(150);
                isbnColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
                        if(isbnColumn.validateValue(param)) return param.getValue().getValue().isbn;
                        else return isbnColumn.getComputedValue(param);
                });

                JFXTreeTableColumn<Book, String> nameColumn = new JFXTreeTableColumn<>("Book name");
                nameColumn.setPrefWidth(150);
                nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
                        if(nameColumn.validateValue(param)) return param.getValue().getValue().book_name;
                        else return nameColumn.getComputedValue(param);
                });

                JFXTreeTableColumn<Book, String> authorsColumn = new JFXTreeTableColumn<>("Authors");
                authorsColumn.setPrefWidth(150);
                authorsColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
                        if(authorsColumn.validateValue(param)) return param.getValue().getValue().authors;
                        else return authorsColumn.getComputedValue(param);
                });

                JFXTreeTableColumn<Book, String> genreColumn = new JFXTreeTableColumn<>("Genre");
                genreColumn.setPrefWidth(200);
                genreColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
                        if(authorsColumn.validateValue(param)) return param.getValue().getValue().genres;
                        else return authorsColumn.getComputedValue(param);
                });

                JFXTreeTableColumn<Book, String> avgColumn = new JFXTreeTableColumn<>("Rating");
                avgColumn.setPrefWidth(100);
                avgColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Book, String> param) ->{
                        if(avgColumn.validateValue(param)) return param.getValue().getValue().average;
                        else return avgColumn.getComputedValue(param);
                });       

                isbnColumn.setCellFactory((TreeTableColumn<Book, String> param) -> new GenericEditableTreeTableCell<Book, String>(new TextFieldEditorBuilder()));
                isbnColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<Book, String> t)->{
                        ((Book) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).isbn.set(t.getNewValue());
                });

                nameColumn.setCellFactory((TreeTableColumn<Book, String> param) -> new GenericEditableTreeTableCell<Book, String>(new TextFieldEditorBuilder()));
                nameColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<Book, String> t)->{
                        ((Book) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).book_name.set(t.getNewValue());
                });

                authorsColumn.setCellFactory((TreeTableColumn<Book, String> param) -> new GenericEditableTreeTableCell<Book, String>(new TextFieldEditorBuilder()));
                authorsColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<Book, String> t)->{
                        ((Book) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).authors.set(t.getNewValue());
                });

                genreColumn.setCellFactory((TreeTableColumn<Book, String> param) -> new GenericEditableTreeTableCell<Book, String>(new TextFieldEditorBuilder()));
                genreColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<Book, String> t)->{
                        ((Book) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).genres.set(t.getNewValue());
                });

                avgColumn.setCellFactory((TreeTableColumn<Book, String> param) -> new GenericEditableTreeTableCell<Book, String>(new TextFieldEditorBuilder()));
                avgColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<Book, String> t)->{
                        ((Book) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).average.set(t.getNewValue());
                });



                // data
                ObservableList<Book> books = FXCollections.observableArrayList();
                for (CBook book : booksToShow) {
                    books.add(new Book(book));
                }


                // build tree
                final TreeItem<Book> root = new RecursiveTreeItem<Book>(books, RecursiveTreeObject::getChildren);

                JFXTreeTableView<Book> treeView = new JFXTreeTableView<Book>(root, books);
                treeView.setShowRoot(false);
                treeView.setEditable(true);
                treeView.getColumns().setAll(isbnColumn, nameColumn, authorsColumn, genreColumn, avgColumn);
                treeView.relocate(0, 0);
                
                Pane main = new Pane();
                main.setPadding(new Insets(10));
                main.getChildren().add(treeView);


                Icon searchIcon = new Icon("SEARCH", "2em");
                searchIcon.relocate(200, 450);
                JFXTextField filterField = new JFXTextField();
                filterField.setPrefWidth(200);
                filterField.relocate(-10, 450);
                filterField.setStyle("-fx-font-size:15px; -fx-background-color: TRANSPARENT;");
                filterField.setPromptText("Book name");
                main.getChildren().addAll(filterField, searchIcon);

                Label size = new Label();
                size.setStyle("-fx-font-size: 20px;");
                size.relocate(0, 420);
                filterField.textProperty().addListener((o,oldVal,newVal)->{
                        treeView.setPredicate(book -> book.getValue().book_name.get().contains(newVal));
                });

                size.textProperty().bind(Bindings.createStringBinding(()->"Book count: " + treeView.getCurrentItemsCount()+"", treeView.currentItemsCountProperty()));
                main.getChildren().add(size);

                return main;

//						ScenicView.show(scene);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return new Pane();
    }
    
    private void addComponents()
    {
        
        
        Pane contenido = new Pane();
        contenido.setPrefSize(1100, 640);

        JFXComboBox<Label> cboxTypes = new JFXComboBox<>();
        cboxTypes.getItems().add(new Label("All books"));
        cboxTypes.getItems().add(new Label("Recommended for you"));
        cboxTypes.getItems().add(new Label("Favorites"));
        cboxTypes.getItems().add(new Label("Women"));
        cboxTypes.getItems().add(new Label("Juvenile fiction"));
        cboxTypes.getItems().add(new Label("History"));
        cboxTypes.getItems().add(new Label("Biography"));
        cboxTypes.getItems().add(new Label("Social life and customs"));
        cboxTypes.setEditable(false);
        cboxTypes.setPromptText("Choose one...");
        EventHandler<ActionEvent> editHandler2 = (ActionEvent actionEvent) -> {
            int prueba = contenido.getChildren().size();
            contenido.getChildren().remove(1); //Removes table
            List<CBook> books = new ArrayList();
            int idUser = CStaticInfo.connection.getUserId(CStaticInfo.loggedUser.gerUsername());
            switch (cboxTypes.getValue().getText())
            {
                case "All books":
                    books = CStaticInfo.connection.getBooks();
                    break;
                case "Recommended for you":
                    ANN recommended;
                    try {
                        recommended = new ANN();
                        int userId = CStaticInfo.connection.getUserId(CStaticInfo.loggedUser.gerUsername());
                        List<String> recommendedISBN = recommended.getRecommendations(userId); //Returns recommeded books (just isbn)
                        books.clear();
                        for (String recommendedBook : recommendedISBN) { //Converts isbn to books
                        CBook act = CStaticInfo.connection.getBookFromISBN(recommendedBook);
                        books.add(act); 
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(DiscoverPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    
                case "Favorites":
                    books = CStaticInfo.connection.getUserFavBooks(idUser);
                    break;
                    
                case "Women":
                    books = CStaticInfo.connection.getBooksByGenre("Women");
                    break;
                    
                case "Juvenile fiction":
                    books = CStaticInfo.connection.getBooksByGenre("Juvenile fiction");
                    break;
                    
                case "History":
                    books = CStaticInfo.connection.getBooksByGenre("History");
                    break;
                    
                case "Biography":
                    books = CStaticInfo.connection.getBooksByGenre("Biography");
                    break;
                        
                case "Social life and customs":
                    books = CStaticInfo.connection.getBooksByGenre("Social life and customs");
                    break;
            }
            Pane main = showBooksMatrix(books);
            main.relocate(180, 110);
            contenido.getChildren().add(main);
        };
        
        cboxTypes.relocate(450, 15);
        cboxTypes.setStyle("-fx-font-size:15px;");
        cboxTypes.setOnAction(editHandler2);
        contenido.getChildren().add(cboxTypes);
        addComponent(contenido);
        
        Pane main = showBooksMatrix(new ArrayList());
        main.relocate(180, 110);
        contenido.getChildren().add(main);
        
        
        
    }
    
    
    /**
     * This method is used for adding an specific element to page content
     * @param element added element
     */
    private void addComponent(Node element)
    {
        navDrawer.getContent().getChildren().add(element);
    }
    
    public Stage getStage()
    {
        this.initStyle(StageStyle.UNDECORATED);        
        createView();
        addComponents();
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
        addComponents();
        return navDrawer.getContent();
    }
    
    
    
    
    
    
    
}


