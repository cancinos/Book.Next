/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booknext;

import Classes.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pablo
 */
public class DatabaseSetup {

    public void linesToBooks(List<String> lines, MysqlConnection connection) {
        int cont = 0, inserted = 0;
        List<CBook> usedBooks = new ArrayList();
        List<String> genres = new ArrayList();
        List<Integer> genresPoints = new ArrayList();
        for (String line : lines) {
            //ISBN|-|TITULO|-|AUTORES|-|DESCRIPCION|-|GENEROS|-|IMAGEN|-|ANIO|-|PUBLISHER
            CBook book = new CBook();
            List<String> tokens = new ArrayList();
            StringTokenizer token = new StringTokenizer(line, "\t");
            while (token.hasMoreTokens()) {
                tokens.add(token.nextToken());
            }
            String[] contents = new String[tokens.size()];
            for (int i = 0; i < tokens.size(); i++) {
                contents[i] = tokens.get(i);
            }
            //String[] contents = line.split("\\|-|");
            book.setBookId(contents[0]);
            book.isbn = contents[0];
            book.setBook_name(contents[1]);

            List<String> lAuthors = new ArrayList();
            StringTokenizer token2 = new StringTokenizer(contents[2], "|");
            while (token2.hasMoreTokens()) {
                lAuthors.add(token2.nextToken());
            }
            book.setBook_authors(lAuthors);

            contents[3] = contents[3].replace("|", " | ");
            if (contents[3].equals("NODESCRIPTION")) {
                book.setBook_description("");
            } else {
                book.setBook_description(contents[3]);
            }

            boolean shouldInsert = true;
            contents[4] = contents[4].replace("|", ", ");
            if (contents[4].equals("NOGENRE")) {
                shouldInsert = false;
            } else {
                book.setBook_genre(contents[4]);
            }

            if (!contents[5].equals("NOIMAGE")) {
                book.setBook_image(contents[5]);
            } else {
                book.setBook_image("/Icons/No_Cover.jpg");
            }

            book.setBook_publishYear(contents[6]);

            book.setBook_publisher(contents[7]);

            List<String> ignoredGenres = new ArrayList();
            ignoredGenres.add("Protected DAISY");
            ignoredGenres.add("Accessible book");
            ignoredGenres.add("In library");
            ignoredGenres.add("OverDrive");
            ignoredGenres.add("Large type books");
            ignoredGenres.add("Popular Print Disabled Books");
            ignoredGenres.add("Fiction");

            if (shouldInsert) {
                usedBooks.add(book);
                String[] genresForBook = book.getBook_genre().split(", ");
                for (String genreForBook : genresForBook) {
                    int index = genres.indexOf(genreForBook);
                    if (index == -1) {
                        if (!ignoredGenres.contains(genreForBook)) {
                            genres.add(genreForBook);
                            genresPoints.add(1);
                        }
                    } else {
                        genresPoints.set(index, genresPoints.get(index) + 1);
                    }
                }
                inserted++;
                System.out.println("Inserted " + String.valueOf(cont) + " - " + book.getBook_name());
            }

            cont++;
        }

        System.out.println("Starting to filter by popular categories");

        genres = BubbleSort(genresPoints, genres);
        int size = 5;
        List<String> finalGenres = new ArrayList();
        for (int i = 0; i < size; i++) {
            finalGenres.add(genres.get(i));
            System.out.println("Popular categorie: " + genres.get(i));
        }

        List<CBook> finalBooks = new ArrayList();

        for (CBook usedBook : usedBooks) {
            String[] genresForBook = usedBook.getBook_genre().split(", ");
            boolean used = false;
            for (String genreForBook : genresForBook) {
                if (!used && finalGenres.contains(genreForBook)) {
                    finalBooks.add(usedBook);
                    connection.addBook(usedBook);
                    used = true;
                }
            }
        }
        System.out.println("Inserted: " + String.valueOf(inserted) + "/" + String.valueOf(cont));
        System.out.println("Fixing descriptions");
        fixDescriptions(finalBooks, connection);
    }

    public List<String> BubbleSort(List<Integer> num, List<String> words) {
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable
        String temps;

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < num.size() - 1; j++) {
                if (num.get(j) < num.get(j + 1)) // change to > for ascending sort
                {
                    temp = num.get(j);
                    temps = words.get(j); //swap elements

                    num.set(j, num.get(j + 1));
                    words.set(j, words.get(j + 1));

                    num.set(j + 1, temp);
                    words.set(j + 1, temps);

                    flag = true;              //shows a swap occurred  
                }
            }
        }
        return words;
    }

    private void fixDescriptions(List<CBook> books, MysqlConnection connection) {
        int contFixed = 0;
        for (CBook book : books) {
            try {
                JSONObject jobj = getJSON(book.isbn).getJSONObject("ISBN:" + book.isbn);
                JSONObject obj2 = jobj.getJSONObject("details");
                if (!obj2.isNull("description")) {
                    try {
                        JSONObject obj3 = obj2.getJSONObject("description");
                        if (!obj3.isNull("value")) {
                            String desc = obj3.getString("value");
                            desc = desc.replace("\'", "");
                            connection.updateDescriptionBook(book.isbn, desc);
                            contFixed++;
                        }
                    } catch (JSONException jex) {
                        String desc = obj2.getString("description");
                        desc = desc.replace("\'", "");
                        connection.updateDescriptionBook(book.isbn, desc);
                        contFixed++;
                    }
                }
            } catch (JSONException ex) {
                Logger.getLogger(DatabaseSetup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Fixed descriptions: " + String.valueOf(contFixed) + "/" + String.valueOf(books.size()));
    }

    private JSONObject getJSON(String isbn) {
        try {
            //JSONObject json = readJsonFromUrl("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);
            //https://openlibrary.org/api/books?bibkeys=ISBN:9780980200447&jscmd=data&format=json
            JSONObject json = readJsonFromUrl("https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&jscmd=details&format=json");
            return json;
        } catch (IOException | JSONException ex) {
            return null; //Meands that this isbn is wrong
        }

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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
