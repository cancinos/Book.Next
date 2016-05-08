/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isbnfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author jcdur
 */
public class ISBNConverter {
    
    String goodJSONS = "";
    List<String> theISBN = new ArrayList();
    boolean quit = false;
    
    // <editor-fold defaultstate="collapsed" desc="Funciones ya hechas (no tocar)">
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
    // </editor-fold>  
    

  public void createNewFile(List<String[]> isbns) throws IOException, JSONException
  {
        int contCorrectos = 0, contIncorrectos = 0, cont = 1;
        String finalLine = "ISBN|-|TITULO|-|AUTORES|-|DESCRIPCION|-|GENEROS|-|IMAGEN|-|ANIO|-|PUBLISHER";
        List<String> finalLines = new ArrayList();
        finalLines.add(finalLine);
        for (int i = 0; i < isbns.size(); i++) {
        
            if (cont % 1000 == 0) //Para que avise cada X cantidad
            {
                System.out.println("van " + cont + " isbns");
            }
            
            JSONObject jobj = getJSON(isbns.get(i)[0], cont);
            if (jobj.length() != 0)
            {
                if(i == 11) {
                    i = i + 1;
                    i = i - 1;
                }
                try {
                    String[] fileValues = isbns.get(i);
                    contCorrectos++;
                    String line = isbns.get(i)[0] + "|-|";

                    JSONObject objobj = jobj.getJSONObject("ISBN:" + isbns.get(i)[0]);

                    if (!objobj.isNull("title")){
                        String title = objobj.getString("title");
                        line += title;
                    }
                    else{
                        //line += "NOTITLE";
                        line += fileValues[1];
                    }
                    
                    line += "|-|";

                    if (!objobj.isNull("authors")){
                        List<String> authors = new ArrayList();
                        JSONArray arrayAuthors = objobj.getJSONArray("authors");
                        for (int j = 0; j < arrayAuthors.length(); j++) {
                            JSONObject subjectObj = arrayAuthors.getJSONObject(j);
                            authors.add(subjectObj.getString("name"));
                        }
                        for (String author : authors) {
                            line += author + "|";
                        }
                        line = line.substring(0, line.length() - 1);
                    }
                    else{
                        //line += "NOAUTHOR";
                        line += fileValues[2];
                    }
                    
                    line += "|-|";
                    
                    if (!objobj.isNull("excerpts")){
                        List<String> authors = new ArrayList();
                        JSONArray arrayAuthors = objobj.getJSONArray("excerpts");
                        for (int j = 0; j < arrayAuthors.length(); j++) {
                            JSONObject subjectObj = arrayAuthors.getJSONObject(j);
                            authors.add(subjectObj.getString("text"));
                        }
                        for (String author : authors) {
                            line += author + "|";
                        }
                        line = line.substring(0, line.length() - 1);
                    }
                    else{
                        line += "NODESCRIPTION";
                    }
                    
                    line += "|-|";
                    
                    if (!objobj.isNull("subjects")){
                        List<String> genres = new ArrayList();
                        JSONArray arraySubjects = objobj.getJSONArray("subjects");
                        for (int j = 0; j < arraySubjects.length(); j++) {
                            JSONObject subjectObj = arraySubjects.getJSONObject(j);
                            genres.add(subjectObj.getString("name"));
                        }

                        for (String genre : genres) {
                            line += genre + "|";
                        }
                        line = line.substring(0, line.length() - 1);
                    }
                    else {
                        line += "NOGENRE";
                    }
                    
                    line += "|-|";
                    
                    if (!objobj.isNull("cover")){
                        String img = "NOIMAGE";
                        JSONObject covers = objobj.getJSONObject("cover");
                        if(!covers.isNull("medium"))
                            img = covers.getString("medium");
                        else{
                            if(!covers.isNull("large"))
                                img = covers.getString("large");
                            else{
                                if(!covers.isNull("small"))
                                    img = covers.getString("small");
                            }
                        }
                        line += img;
                    }
                    else
                        line += "NOIMAGE";
                    
                    line += "|-|";
                    line += fileValues[3];
                    line += "|-|";
                    line += fileValues[4];
                    
                    finalLines.add(line);
                }
                catch(Exception e){
                    System.out.println("algo raro pasó");
                }
            } else
            {
                contIncorrectos++;
            }
            cont++;
            if (cont > 10000)
                    i = isbns.size();
        }
        //De aquí para abajo imprime los ISBN (sólo eso imprime)
        PrintWriter writer = new PrintWriter("C:\\Users\\jcdur\\Desktop\\ISBN\\siSirven.txt", "UTF-8"); //Cambiar path
        for (String theISBN1 : finalLines) {
            writer.println(theISBN1);
        }
        writer.close();
        System.out.println("terminado");
        System.out.println("correctos: " + contCorrectos + "/" + cont);
        System.out.println("incorrectos: " + contIncorrectos + "/" + cont);
  }

  
  private JSONObject getJSON(String isbn, int elcont) 
  {
    try {
        //JSONObject json = readJsonFromUrl("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);
        //https://openlibrary.org/api/books?bibkeys=ISBN:9780980200447&jscmd=data&format=json
        JSONObject json = readJsonFromUrl("https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&jscmd=data&format=json");
        return json;
    } catch (IOException | JSONException ex) {
        System.out.println(elcont + " broken json ");
        quit = true;
        return null; //Meands that this isbn is wrong
    }
      
      
  }

   
    
}
