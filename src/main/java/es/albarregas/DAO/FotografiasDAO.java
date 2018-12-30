/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.Fotografia;
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
public class FotografiasDAO implements IFotografiaDAO {

    @Override
    public ArrayList<Fotografia> getFotografia() {
        ArrayList<Fotografia> fotos = new ArrayList<Fotografia>();
        String sql = "select * from fotografias";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                Fotografia f = new Fotografia();
                f.setIdFotografia(resultado.getInt("idFotografia"));
                f.setIdArticulo(resultado.getInt("idArticulos"));
                f.setFotografia(resultado.getString("fotografia"));
                fotos.add(f);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return fotos;
    }

    @Override
    public Boolean insertarFoto(Fotografia fotografia, Articulo articulo) {
        Boolean retorno = true;
        String sql = "insert into fotografias (idArticulo, fotografia) values ((select idArticulo from articulos where idArticulo="+articulo.getIdArticulo()+"),?)";
        Connection conexion = null;

        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, fotografia.getFotografia());

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
