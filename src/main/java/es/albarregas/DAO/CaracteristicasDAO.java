/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author paco
 */
public class CaracteristicasDAO implements ICaracteristicasDAO{
    
    @Override
    public ArrayList<Caracteristica> getCaracteristicas(Categoria categoria) {
    ArrayList<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
        //String sql = "select * from caracteristicas where idCategoria=?";
        String sql = "select * from caracteristicas";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                Caracteristica c = new Caracteristica();
                c.setIdCaracteristica(resultado.getInt("idCaracteristica"));
                c.setIdCategoria(resultado.getInt("idCategoria"));
                c.setDenominacion(resultado.getString("denominacion"));
                caracteristicas.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return caracteristicas;
    }
    
}
