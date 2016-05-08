/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.*;
import com.mysql.jdbc.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Classes.CUser;

/**
 *
 * @author Ingesis
 */
public class MysqlConnection {

    private Connection connection;
    private Statement statement;

    // <editor-fold desc="Connections & Statements">
    public Connection connect() throws SQLException {

        if (connection == null) {

            new Driver();
            // buat koneksi
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/booknext",
                    "booknext",
                    "book");

        }
        return connection;

    }

    public void close() throws SQLException {

        if (connection != null) {

            connection.close();

        }

    }

    public Statement makeStatement() throws SQLException {

        if (connection != null) {
            statement = connection.createStatement();
        }
        return statement;

    }
    // </editor-fold>

    // <editor-fold desc="User Querys">
    public boolean addNewUser(String user, String fullname, String birthday, String password, String imagen, String country) throws SQLException {

        boolean add = false;
        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;
                query = connection.prepareStatement("insert into users(username,fullname,birthday,passwoord,imagen,country) values (?,?,?,?,?,?)");
                query.setString(1, user);
                query.setString(2, fullname);
                query.setString(3, birthday);
                query.setString(4, password);
                query.setString(5, imagen);
                query.setString(6, country);
                if (query.executeUpdate() > 0) {
                    add = true;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }

        return add;
    }

    public boolean addWordToBayes(int genre, String word, String table) {
        boolean add = false;
        try {
            if (makeStatement() != null) {
                String squery = "SELECT * FROM " + table + " WHERE word = '" + word + "'";
                PreparedStatement q = connection.prepareStatement(squery);
                ResultSet result = q.executeQuery(squery);
                if (!result.next()) //The word is new
                {
                    PreparedStatement query = null;
                    query = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?)");
                    int[] gs = {0, 0, 0, 0, 0};
                    gs[genre] = 1;
                    query.setString(1, word);
                    query.setInt(2, gs[0]);
                    query.setInt(3, gs[1]);
                    query.setInt(4, gs[2]);
                    query.setInt(5, gs[3]);
                    query.setInt(6, gs[4]);
                    if (query.executeUpdate()> 0) {
                        add = true;
                    }
                }
                else
                {
                    PreparedStatement query = null;
                    query = connection.prepareStatement("UPDATE " + table + " SET genre1 = genre1 + ?,"
                            + "genre2 = genre2 + ?, genre3 = genre3 + ?, genre4 = genre4 + ?, genre5 = genre5 + ? WHERE word = '" + word + "'");
                    int[] gs = {0, 0, 0, 0, 0};
                    gs[genre] = 1;
                    for (int i = 0; i < gs.length; i++)
                        query.setInt(i + 1, gs[i]);
                    if (query.executeUpdate()> 0) {
                        add = true;
                    }
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return add;
    }

    public int[] getGenresForWord(String word, String table){
        String squery = "SELECT * FROM " + table + " WHERE word = '" + word + "'";
        PreparedStatement q;
        int[] genres = {0, 0, 0, 0, 0};
        try {
            q = connection.prepareStatement(squery);
            ResultSet result = q.executeQuery(squery);
            if (!result.next()){
                result.previous();
                for (int i = 0; i < genres.length; i++)
                    genres[i] = result.getInt(i + 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genres;
    }
    
    public int[] getTotalsForGenre(String table)
    {
        String squery = "SELECT SUM(genre1) as g1, SUM(genre2) as g2, SUM(genre3) as g3, SUM(genre4) as g4, SUM(genre5) as g5 FROM " + table;
        PreparedStatement q;
        int[] genres = {0, 0, 0, 0, 0};
        try {
            q = connection.prepareStatement(squery);
            ResultSet result = q.executeQuery(squery);
            if (!result.next()){
                result.previous();
                for (int i = 0; i < genres.length; i++)
                    genres[i] = result.getInt(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genres;
    }
    
    private CUser getUser(String user) {
        CUser cuser = null;
        try {
            if (makeStatement() != null) {

                PreparedStatement q = null;
                q = connection.prepareStatement("Select * from users where username = ?");
                q.setString(1, user);

                ResultSet result = null;
                result = q.executeQuery();

                if (result.next()) {
                    int id = result.getInt("id");
                    cuser = new CUser(
                            result.getString("username"),
                            result.getString("fullname"),
                            result.getString("passwoord"),
                            result.getString("birthday"),
                            result.getString("imagen"),
                            result.getString("country")
                    );
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuser;
    }

    public CUser consultUser(String user) {
        boolean exist = false;
        CUser cuser = null;
        try {
            if (makeStatement() != null) {

                PreparedStatement q = null;
                q = connection.prepareStatement("Select * from users where username = ?");
                q.setString(1, user);

                ResultSet result = null;
                result = q.executeQuery();

                if (result.next()) {
                    exist = true;
                    cuser = getUser(user);
                } else {
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuser;
    }

    public int getUserId(String user) {
        int id = 0;
        CUser cuser = null;
        try {
            if (makeStatement() != null) {

                PreparedStatement q = null;
                q = connection.prepareStatement("Select * from users where username = ?");
                q.setString(1, user);

                ResultSet result = null;
                result = q.executeQuery();

                if (result.next()) {
                    id = result.getInt("id");
                } else {
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public boolean updateUser(String user, String fullname, String birthday, String password, String country) throws SQLException {

        boolean add = false;
        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;
                query = connection.prepareStatement("update users set username = ?, fullname = ?, birthday = ? ,passwoord = ?, country = ?  where username = ?");
                query.setString(1, user);
                query.setString(2, fullname);
                query.setString(3, birthday);
                query.setString(4, password);
                query.setString(5, country);
                query.setString(6, user);

                if (query.executeUpdate() > 0) {
                    add = true;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }

        return add;
    }

    // </editor-fold>
    //<editor-fold desc="Books Querys">
    public List<CBook> getBooks() {

        List<CBook> books = null;

        CBook cbook = null;
        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query =connection.prepareStatement("Select * from book");
                                
                ResultSet result =null;
                result = query.executeQuery();
                
                books = new ArrayList<CBook>();
                while(result.next()){
                    cbook = new CBook();
                    cbook.fillCBook(
                    result.getString("isbn"),
                    result.getString("book_name"),
                    result.getString("author"),                            
                    result.getString("imagen"),
                    result.getString("publish_date"),
                    result.getString("publisher"),
                    result.getString("rating_average"),
                    result.getString("description"),
                    result.getString("genre")                           
                    );
                    books.add(cbook);
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return books;
    } 
    
    public List<CBook> getBooksByGenre(String genre) {

        List<CBook> books = null;

        CBook cbook = null;
        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query =connection.prepareStatement("Select * from book where genre = ?");
                                
                
                query.setString(1, genre);
                ResultSet result =null;
                result = query.executeQuery();
                books = new ArrayList<CBook>();
                while(result.next()){
                    cbook = new CBook();
                    cbook.fillCBook(
                    result.getString("isbn"),
                    result.getString("book_name"),
                    result.getString("author"),                            
                    result.getString("imagen"),
                    result.getString("publish_date"),
                    result.getString("publisher"),
                    result.getString("rating_average"),
                    result.getString("description"),
                    result.getString("genre")                           
                    );
                    books.add(cbook);
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return books;
    } 
    
    
     public List<CBook> getUserBooks(int id) {

        List<CBook> books = null;

        CBook cbook = null;
        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query =connection.prepareStatement("Select book.* from book JOIN user_book on book.isbn = user_book.isbn AND user_book.id = ?");
                query.setInt(1, id);
                ResultSet result =null;
                result = query.executeQuery();
                
                books = new ArrayList<CBook>();
                while(result.next()){
                    cbook = new CBook();
                    cbook.fillCBook(
                    result.getString("isbn"),
                    result.getString("book_name"),
                    result.getString("author"),                            
                    result.getString("imagen"),
                    result.getString("publish_date"),
                    result.getString("publisher"),
                    result.getString("rating_average"),
                    result.getString("description"),
                    result.getString("genre")                           
                    );
                    books.add(cbook);
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return books;
    } 
    
    public boolean addBook(CBook cbook){
        boolean add = false;
        
        try {
            if ( makeStatement() !=null){
                
                PreparedStatement query = null;
                query =connection.prepareStatement("insert ignore into book(isbn,book_name,author,imagen,publish_date,publisher,rating_average,description,genre) values (?,?,?,?,?,?,?,?,?)");
                
                query.setString(1, cbook.getId_String());
                query.setString(2, cbook.getBook_name());
                query.setString(3, cbook.getBook_authorsStr());
                query.setString(4, cbook.getBook_image());
                query.setString(5, cbook.getBook_publishYear());
                query.setString(6, cbook.getBook_publisher());
                query.setString(7, cbook.getRating_String());
                query.setString(8, cbook.getBook_description());
                query.setString(9, cbook.getBook_genre());
                
                if(query.executeUpdate()>0){
                add= true;   
                }
             
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return add;
    }
    
    public long countBooks(){
        
         long id =-1;
            
        try {
            if ( makeStatement() !=null){
                
                PreparedStatement q = null;
                q =connection.prepareStatement("Select count(*) from book");
                
                
                ResultSet result =null;
                result = q.executeQuery();
                
                if(result.next()){
                    id = result.getLong("count(*)");
                }else{
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        
        
    }
    
    //</editor-fold>
    //<editor-fold desc="User_Books Querys">
    public boolean setUserBook(int id, String ISBN) {
        boolean add = false;
        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;
                query = connection.prepareStatement("Insert into user_book(isbn,id,user_rating,user_liked,user_view,user_saved) values (?,?,0,0,0,0)");

                query.setString(1, ISBN);
                query.setInt(2, id);;
                if (query.executeUpdate() > 0) {
                    add = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            return add;
    } 
    
    public boolean updateRating(int id,String ISBN,int rating){
       boolean add = false;

        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query=connection.prepareStatement("UPDATE user_book SET user_rating = ? Where id = ? AND isbn = ?");
                
                query.setInt(1, rating);
                query.setInt(2, id);
                query.setString(3, ISBN);
                if(query.executeUpdate()>0){
                    add= true;

            }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            return add;
    } 
    
    public boolean updateLiked(int id,String ISBN,int liked){
       boolean add = false;

        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query=connection.prepareStatement("UPDATE user_book SET user_liked = ? Where id = ? AND isbn = ?");
                
                query.setInt(1, liked);
                query.setInt(2, id);
                query.setString(3, ISBN);
                if(query.executeUpdate()>0){
                    add= true;

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            return add;
    } 
    
    public boolean updateView(int id,String ISBN , int view){
       boolean add = false;

        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query=connection.prepareStatement("UPDATE user_book SET user_view = ? Where id = ? AND isbn = ?");
                
                query.setInt(1, view);
                query.setInt(2, id);
                query.setString(3, ISBN);
                if(query.executeUpdate()>0){
                    add= true;

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            return add;
    } 
    
    public boolean updateSaved(int id,String ISBN,int saved ){
       boolean add = false;

        try {
            if (makeStatement() != null) {

                PreparedStatement query = null;

                query=connection.prepareStatement("UPDATE user_book SET user_saved = ? Where id = ? AND isbn = ?");
                
                query.setInt(1, saved);
                query.setInt(2, id);
                query.setString(3, ISBN);
                if(query.executeUpdate()>0){
                    add= true;

            }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return add;
    }
    //</editor-fold> 

      public int getRating(String isbn, String range) {
        int id = 0;
        try {
            if (makeStatement() != null) {

                PreparedStatement q = null;
                q = connection.prepareStatement("Select COUNT(*) from user_book where isbn = ? AND user_rating = ?");
                q.setString(1, isbn);
                q.setString(2, range);
                ResultSet result = null;
                result = q.executeQuery();

                if (result.next()) {
                    id = result.getInt("count(*)");
                } else {
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
      //<editor-fold desc="ratings">
//    public int getRating2(String isbn) {
//        int id = 0;
//        try {
//            if (makeStatement() != null) {
//
//                PreparedStatement q = null;
//                q = connection.prepareStatement("Select COUNT(*) from user_book where isbn = ? AND user_rating = 2");
//                q.setString(1, isbn);
//
//                ResultSet result = null;
//                result = q.executeQuery();
//
//                if (result.next()) {
//                    id = result.getInt("count(*)");
//                } else {
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
//
//    public int getRating3(String isbn) {
//        int id = 0;
//        try {
//            if (makeStatement() != null) {
//
//                PreparedStatement q = null;
//                q = connection.prepareStatement("Select COUNT(*) from user_book where isbn = ? AND user_rating = 3");
//                q.setString(1, isbn);
//
//                ResultSet result = null;
//                result = q.executeQuery();
//
//                if (result.next()) {
//                    id = result.getInt("count(*)");
//                } else {
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
//    
//    public int getRating4(String isbn) {
//        int id = 0;
//        try {
//            if (makeStatement() != null) {
//
//                PreparedStatement q = null;
//                q = connection.prepareStatement("Select COUNT(*) from user_book where isbn = ? AND user_rating = 4");
//                q.setString(1, isbn);
//
//                ResultSet result = null;
//                result = q.executeQuery();
//
//                if (result.next()) {
//                    id = result.getInt("count(*)");
//                } else {
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
//    
//    public int getRating5(String isbn) {
//        int id = 0;
//        try {
//            if (makeStatement() != null) {
//
//                PreparedStatement q = null;
//                q = connection.prepareStatement("Select COUNT(*) from user_book where isbn = ? AND user_rating = 5");
//                q.setString(1, isbn);
//
//                ResultSet result = null;
//                result = q.executeQuery();
//
//                if (result.next()) {
//                    id = result.getInt("count(*)");
//                } else {
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
    //</editor-fold >
      
    public double getCategoryPorcent(int uid, String genre) {
        double id = 0;
        try {
            if (makeStatement() != null) {

                PreparedStatement query_0 = null;
                PreparedStatement query_1 = null;
                PreparedStatement query_2 = null;
                
                //Return how many book the user has viwed
                query_0 = connection.prepareStatement("SELECT COUNT(*) FROM user_book WHERE id  = ?");
                query_0.setInt(1, uid);
                
                //Return how many book the user has liked
                query_1 = connection.prepareStatement("SELECT COUNT(*) FROM user_book JOIN book ON user_book.isbn = book.isbn" +
"			 WHERE user_book.id = ? AND user_book.user_liked = 1 AND book.genre LIKE ('%'+'?'+'%')");
                query_1.setInt(1, uid);
                query_1.setString(2, genre);
                
                String ugenre = "%"+genre+"%";
                //Return how many book the user has saved
                query_2 = connection.prepareStatement("SELECT COUNT(*) FROM user_book JOIN book ON user_book.isbn = book.isbn" +
"			 WHERE user_book.id = ? AND user_book.user_saved = 1 AND book.genre LIKE "+ ugenre);
                query_2.setInt(1, uid);
                
                                
                ResultSet result_0 = null;                
                ResultSet result_1 = null;
                ResultSet result_2 = null;
                result_0 = query_0.executeQuery();
                result_1 = query_0.executeQuery();
                result_2 = query_0.executeQuery();

                if (result_0.next() & result_1.next() & result_2.next()) {
                    id = result_1.getInt("count(*)") + result_2.getInt("count(*)");
                    id = id /result_0.getInt("count(*)");
                     
                } else {
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
}
