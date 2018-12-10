/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author paco
 */
public class ConnectionFactory {
    static Connection conexion = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //DAR NOMBRE A LA BBDD CUANDO ESTE CREADA
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/NOMBRESUBASTA", "java2019", "2019");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conexion;
    }
    
    public static void closeConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        
    }
}
