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
    public Connection connect() throws SQLException{
        
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
    
    public Statement makeStatement() throws SQLException{
        
        if(connection !=null){            
        statement = connection.createStatement();
        }
        return statement;
        
    } 
    // </editor-fold>
    
     
     
    
    // <editor-fold desc="User Querys">
    
    public boolean addNewUser(String user,String fullname,String birthday,String password,String imagen,String country) throws SQLException{
        
        boolean add=false;
        try { 
        if ( makeStatement() !=null){
            
          PreparedStatement query = null;
          query =connection.prepareStatement("insert into users(username,fullname,birthday,passwoord,imagen,country) values (?,?,?,?,?,?)");
          query.setString(1, user);
          query.setString(2, fullname);
          query.setString(3, birthday);
          query.setString(4, password);
          query.setString(5, imagen);
          query.setString(6, country);
          if(query.executeUpdate()>0){
          add= true;   
          }
        }
         } catch (SQLException sqlException) {  
            sqlException.printStackTrace();  
            close();  
        }
            
        return add;
    }
    
    private CUser getUser(String user) {
        CUser cuser = null;
        try {
            if ( makeStatement() !=null){
                
                PreparedStatement q = null;
                q =connection.prepareStatement("Select * from users where username = ?");
                q.setString(1, user);
                
                
                ResultSet result =null;
                result = q.executeQuery();
                
                if(result.next()){
                    int id          =    result.getInt("id");
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
        boolean exist =false;
        CUser cuser =null;    
        try {
            if ( makeStatement() !=null){
                
                PreparedStatement q = null;
                q =connection.prepareStatement("Select * from users where username = ?");
                q.setString(1, user);
                
                
                ResultSet result =null;
                result = q.executeQuery();
                
                if(result.next()){
                    exist= true;
                    cuser = getUser(user);
                }else{
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuser;
    }
    
    public boolean updateUser(String user,String fullname,String birthday,String password,String country) throws SQLException{
        
        boolean add=false;
        try { 
        if ( makeStatement() !=null){
            
          PreparedStatement query = null;
          query =connection.prepareStatement("update users set username = ?, fullname = ?, birthday = ? ,passwoord = ?, country = ?  where username = ?");
          query.setString(1, user);
          query.setString(2, fullname);
          query.setString(3, birthday);
          query.setString(4, password);
          query.setString(5, country);
          query.setString(6, user);
          
          if(query.executeUpdate()>0){
          add= true;   
          }
        }
         } catch (SQLException sqlException) {  
            sqlException.printStackTrace();  
            close();  
        }
            
        return add;
    }
    
    // </editor-fold>
    
    
    
    
    
    
    
    
}
