/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author jcdur
 */
public class ISBNConverter {
    
    private CBook newBook = new CBook();
    
    private static String readAll(Reader rd) throws IOException 
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
        }
        return sb.toString();
  }

  private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }


  public CBook isbnToBook(String isbn, int id) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);
    createBookObj(json, id);
    return  newBook;
  }
  
  private void createBookObj(JSONObject json, int id ) throws IOException, JSONException
  {
       JSONArray obj1 = json.getJSONArray("items");
       JSONObject items = obj1.getJSONObject(0);
       JSONObject volumeInfo = items.getJSONObject("volumeInfo");
       JSONArray authors;
       newBook.setBookId(id);
       if (!volumeInfo.isNull("title"))
           newBook.setBook_name(volumeInfo.getString("title"));
       if (!volumeInfo.isNull("publishedDate"))
        newBook.setBook_publishYear(volumeInfo.getString("publishedDate"));
       if (!volumeInfo.isNull("description"))
        newBook.setBook_description(volumeInfo.getString("description"));
       if (!volumeInfo.isNull("publisher"))
        newBook.setBook_publisher(volumeInfo.getString("publisher"));
       if (!volumeInfo.isNull("authors"))
       {
           authors  =  volumeInfo.getJSONArray("authors");
           newBook.setBook_authors(convertToString(authors));
       }
       if (!volumeInfo.isNull("imageLinks"))
           newBook.setBook_image(volumeInfo.getJSONObject("imageLinks").getString("thumbnail")); else
           newBook.setBook_image("/Icons/No_Cover.jpg");
      /**
       * Historia
       * Literatura
       * Ciencia ficción
       * Drama
       * Terror
       */
      
  }
  
  
  /**
   * This Function is used to convert a JSONArray to a List<String>
   * @param the_array
   * @return
   * @throws JSONException 
   */
  private List<String> convertToString(JSONArray the_array) throws JSONException
  {
      int cont = the_array.length();
      ArrayList<String> the_list ;
        the_list = new ArrayList<>();
        
      for (int i = 0; i < cont; i++) {
          the_list.add(the_array.getString(i));
      }
      return the_list;
  }
    
}
