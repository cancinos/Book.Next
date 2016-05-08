/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booknext;

import Classes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
            book.setBookId(cont);
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

            if (shouldInsert) {
                usedBooks.add(book);
                String[] genresForBook = book.getBook_genre().split(", ");
                for (String genreForBook : genresForBook) {
                    int index = genres.indexOf(genreForBook);
                    if (index == -1) {
                        genres.add(genreForBook);
                        genresPoints.add(1);
                    } else {
                        genresPoints.set(index, genresPoints.get(index) + 1);
                    }
                }
                inserted++;
            }

            cont++;
        }

        genres = BubbleSort(genresPoints, genres);
        int size = 5;
        List<String> finalGenres = new ArrayList();
        for (int i = 0; i < size; i++) {
            finalGenres.add(genres.get(i));
        }

        for (CBook usedBook : usedBooks) {
            String[] genresForBook = usedBook.getBook_genre().split(", ");
            boolean used = false;
            for (String genreForBook : genresForBook) {
                if(!used && finalGenres.contains(genreForBook)){
                    connection.addBook(usedBook);
                    used = true;
                }
            }
        }

        System.out.println("Inserted: " + String.valueOf(inserted) + "/" + String.valueOf(cont));
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
}
