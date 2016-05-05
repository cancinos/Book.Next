/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
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
       
       newBook.setBookId(id); //Set isbn as ID
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
       
       System.out.print(" finished\n");
       
      /**
       * Historia
       * Literatura
       * Ciencia ficción
       * Drama
       * Terror
       */
      
  }
  
  
  private void setGenre(String isbn)
  {
      String book4Life = ",9781633532090,9780345535283,9781451648546,0446677450,"
              + "0446677450,9780553296983,9780060883287,9780061122415,9781582701707,"
              + "9780312278670,9780025045415,9781592802609,9780199573202,"
              + "9781405091886,9780470482551,0937539562,9780671646783,9781591842941,"
              + "0316113514,0804139296,9781482075144,1505339111,1617201782,1455611808,"
              + "9780743234801,0399535403,9780071808552,9780964729230,";
      String scienceFiction = "9780439023481,9781594135866,9781908435699,"
              + ",9780385737951,9780385738750,9780375896125,9780804139021,"
              + "9781587673078,9780062217080,1442468351,9780751565355,"
              + "9780099518471,9780486406534,9780312450151,9780743273565,"
              + "9780060838676,978189830914,9781603090674,9781891830877,"
              + "9781603090186,";
      String fantasy = ",9788700631625,9780747560777,9780747560722,9780195799163,"
              + "9788498383621,9788498386998,9780605039070,9780316007443,"
              + "9781410413550,0316027650,9780316067935,9780099284635,"
              + "9780135862728,0553593714,9780007447831,0002245868,0553801503,"
              + "9780553801477,0395082544,9780375725845,9780142425039,0439295017,"
              + "0547572484,1442468351,0062310666,9780751565355,0679602860,"
              + "9780547978840,9780804169127,";
      String romance = ",9780316007443,9781410413550,0316027650,9780316067935,"
              + "9780385537674,9781612130590,9788804623250,9780141345659,"
              + "0753819929,0547572484,9780812988406,9780312569815,9780446531337,"
              + "9780312328627,9780312347765,";
      String suspense = ",0385504209,9780099435488,9780375725845,9780062217080,"
              + "9780552160896,0062310666,1484715772,9780553383805,"
              + "9780345426277,9780307264893,9780345373779,9781420101843,"
              + "9780312946432,9780312609160,9781603091008,9781603090360,"
              + "9780958578349,9781603090704,9781891830969,";
      String allGenres = "";
      if (book4Life.contains("," + isbn + ","))
          allGenres += isbn + ",";
      if (scienceFiction.contains("," + isbn + ","))
          allGenres += isbn + ",";
      if (fantasy.contains("," + isbn + ","))
          allGenres += isbn + ",";
      if (romance.contains("," + isbn + ","))
          allGenres += isbn + ",";
      if (suspense.contains("," + isbn + ","))
          allGenres += isbn;
      newBook.setBook_genre(allGenres);
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
