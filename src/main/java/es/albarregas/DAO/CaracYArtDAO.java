/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.CaracYArt;
import es.albarregas.beans.Caracteristica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paco
 */
public class CaracYArtDAO implements ICaracYArtDAO{
    
    @Override
    public ArrayList<CaracYArt> obtenerCaracYArt() {
        ArrayList<CaracYArt> carac = new ArrayList<CaracYArt>();
        String sql = "select * from caracyart";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                CaracYArt c = new CaracYArt();
                c.setId(resultado.getInt("id"));
                c.setIdArticulo(resultado.getInt("idArticulo"));
                c.setIdCaracteristica(resultado.getInt("IdCaracteristica"));
                c.setValor(resultado.getString("valor"));
                carac.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return carac;
    }

    @Override
    public Boolean newCaractYArt(CaracYArt caracYArt, Articulo articulo, Caracteristica caracteristica) {
    Boolean retorno = true;
        String sql = "insert into caracyart (idArticulo, idCaracteristica, valor) values ((select idArticulo from articulos where idArticulo="+articulo.getIdArticulo()+"),(select idCaracteristica from caracteristicas where idCaracteristica="+caracteristica.getIdCaracteristica()+"),?)";
        Connection conexion = null;

        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, caracYArt.getValor());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally {
            ConnectionFactory.closeConnection();
        }

        return retorno;
    }
    
}
