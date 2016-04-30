/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.sql.*;
import com.mysql.jdbc.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void addNewUser(String user,String fullname,Date birthday,String password,String imagen,String country) throws SQLException{
        
        if ( makeStatement() !=null){
            
         statement.execute("insert into users(username,fullname,birthday,passwoord,imagen,country) values("+"'"+user+"',"+"'"+fullname+"',"+"'"+birthday+"',"+"'"+password+"',"+"'"+imagen+"',"+"'"+country+"',"+")");
        
        }
        
    }
    
    public void getUser(String user) throws SQLException{
        
        if ( makeStatement() !=null){
            
         statement.execute("Select * from users" );
        
        }
        
    }
    
    // </editor-fold>
    
    
    
    
    
    
    
    
}
