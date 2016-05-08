/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isbnfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONException;
import com.opencsv.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jcdur
 */
public class ISBNFilter extends Application {
    
    String allISBN = "";
    
    /**
     * This Function reads isbn.txt file and creates every single isbn to a
     * CBook object
     *
     * @param parent stage where is beeing called
     * @return strng whit isbn separated by commas
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String[]> readFile(Stage parent) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        String[] separated = null;
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(parent);
        List<String[]> everything = new ArrayList();
        CSVParser parser = new CSVParser(',', '"');
        String[] parsedLine;
        
        /*CSVReader reader = null;
        try
        {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(selectedFile),',','"');
            String [] nextLine;
            //Read one line at a time
            while ((nextLine = reader.readNext()) != null) 
            {
                everything.add(nextLine);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        
        if (selectedFile != null) {
            Scanner scanner = new Scanner(selectedFile);
            BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsoluteFile()));
            try {
                StringBuilder sb = new StringBuilder();
                br.readLine();
                String line = br.readLine();
                while (line != null) {
                    if (line.charAt(0) == '"') //Algunos traen " al inicio.. los que traen, se los quito   
                        line = line.substring(1);
                    
                    separated = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    //separated = line.split(","); //Separo todos los datos
                    
                    
                    everything.add(separated);
                    line = br.readLine();
                }
                //everything = sb.toString();
            } finally {
                br.close();
            }
            
            
        }

        return everything;

    }
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                try {
                    List<String[]> isbns = readFile(primaryStage); //Lee y mete todo en un string
                    ISBNConverter classISBN = new ISBNConverter();
                    classISBN.createNewFile(isbns);
                } catch (IOException | JSONException ex) {
                    Logger.getLogger(ISBNFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
