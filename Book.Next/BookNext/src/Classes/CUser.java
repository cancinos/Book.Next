/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author jcdur
 */
public class CUser {
    private String username;
    private String user_country;
    private String user_fullName;
    private String user_birthday;
    private String user_image;
    private String user_password;
    
    public CUser(String usrname,  String full, String pass, String birth, String img,String country)
    {
        username = usrname;
        user_country = country;
        user_fullName = full;
        user_birthday = birth;
        user_image = img;
        user_password = pass;
    }
    
    public String gerUsername() { return username; }

    public String getUser_country() { return user_country; }
    
    public String getUser_fullName() { return user_fullName; }
    
    public String getUser_birthday() { return user_birthday; }
    
    public String getUser_image() { return user_image; }
    
    public String getUser_password() { return user_password; }
    
    public boolean comparePassword(String newPass) { return newPass.compareTo(user_password) == 0; }
        
    
    
}
