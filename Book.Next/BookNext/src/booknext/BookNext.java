package booknext;

import javafx.application.Application;
import javafx.stage.Stage;
import Pages.LandingPage;

/**
 *
 * @author jcdur
 */
public class BookNext extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        LandingPage logPage = new LandingPage();
        Stage newStage = logPage.getStage();
        newStage.show();
        primaryStage.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    

}
