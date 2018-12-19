/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author paco
 */
public class ConnectionFactory {

    private static DataSource data = null;

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            Context initialContext = new InitialContext();
            data = (DataSource) initialContext.lookup("java:comp/env/jdbc/subastas");
            conexion = data.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexion != null) {
                try {
                    return data.getConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public static void closeConnection(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getData() {
        return data;
    }

    public static void setData(DataSource data) {
        ConnectionFactory.data = data;
    }
}
