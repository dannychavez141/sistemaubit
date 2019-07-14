/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaubit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dahepc
 */
public class conexion {
    
            Connection con;
    Statement st;
         String controlador = "com.mysql.jdbc.Driver";
         String driver = "jdbc:mysql://localhost/ubit";
         String user = "root";//viene por defecto
         String pass = "";
 
       public Connection conectar() 
       {try {
                    Class.forName(controlador);
                    try {
                        con = DriverManager.getConnection(driver, user, pass);
                    } catch (SQLException ex) {
                        Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (con != null) {
                        System.out.println("conecion okkkkkk");
                    }
                    
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
                } return con;
       }

               
          
}
