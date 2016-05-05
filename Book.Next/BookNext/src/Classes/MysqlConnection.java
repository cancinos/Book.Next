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
                PreparedStatement selectQuery = null;
                ResultSet result = selectQuery.executeQuery("SELECT * FROM " + table + " WHERE word = '" + word + "'");
                if (result.isBeforeFirst()) //The word is new
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
                    close();
                }
                else
                {
                    PreparedStatement query = null;
                    query = connection.prepareStatement("UPDATE " + table + " SET genre1 = genre1 + ?,"
                            + "genre2 = genre2 + ?, genre3 = genre3 + ?, genre4 = genre4 + ?, genre5 = genre5 + ?");
                    int[] gs = {0, 0, 0, 0, 0};
                    gs[genre] = 1;
                    for (int i = 0; i < gs.length; i++)
                        query.setInt(i + 1, gs[i]);
                    if (query.executeUpdate()> 0) {
                        add = true;
                    }
                    close();
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return add;
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
                    
                     setUserBook(getUserId("Ludwing"),Long.toString(cbook.getBookId()));
                     setUserBook(getUserId("Durini"),Long.toString(cbook.getBookId()));
                     updateRating(getUserId("Durini"),Long.toString(cbook.getBookId()),4);
                      updateView(getUserId("Ludwing"),Long.toString(cbook.getBookId()),2);
                      updateSaved(getUserId("Durini"),Long.toString(cbook.getBookId()),3);
                      updateLiked(getUserId("Ludwing"),Long.toString(cbook.getBookId()),1);
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

}
