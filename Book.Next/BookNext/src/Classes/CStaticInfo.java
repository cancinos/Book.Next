/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;
import javafx.stage.Stage;

/**
 *
 * @author jcdur
 */
public class CStaticInfo {
    public static CUser loggedUser;
    public static List<CBook> usersBooks;
    public static List<CBook> allBooks;
    public static MysqlConnection connection;
    public static String mainColor = "#F44336";
    public static String darkColor = "#D32F2F";
    public static String accentColor = "#536DFE";
}
