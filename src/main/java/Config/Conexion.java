/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *Aqui se hace la conexion, se hace con el localhost y con clever cloud
 * Se pone como comentario la de clever cloud, debido a fallo en las peticiones.
 * @author EMANUEL ORTIZ
 * @author ASUS
 */
public class Conexion {

        Connection con;
//          String url = "jdbc:mysql://localhost:3306/domi";
//            String Driver = "com.mysql.jdbc.Driver";
//            String user = "root";
//            String pass = "";

   
  
    String url = "jdbc:mysql://bwwfslugrk1aszg2nflb-mysql.services.clever-cloud.com:3306/bwwfslugrk1aszg2nflb";
    String Driver = "com.mysql.jdbc.Driver";
    String user = "ufouweuqwopzudsm";
    String pass = "RdxOZ0fyl2oGktU1iSUj";

    public Connection getConnection() {
        try {

            if (this.con == null) {
                Class.forName(Driver);
                con = DriverManager.getConnection(url, user, pass);
                
            }

        } catch (Exception e) {
            System.out.println("The exception raised is:" + e);
            return null;
        }
        return con;
    }
    
    

}
