/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
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
public class CategoriasDAO implements ICategoriasDAO {

    @Override
    public ArrayList<Categoria> obtenerCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        String sql="select * from categorias";
        Connection conexion = null;
         try {
             conexion = ConnectionFactory.getConnection();
             Statement statement = conexion.createStatement();
             ResultSet resultado = statement.executeQuery(sql);
             
             while(resultado.next()){
                 Categoria categoria = new Categoria();
                 categoria.setIdCategoria(resultado.getInt("idCategoria"));
                 categoria.setDenominacion(resultado.getString("denominacion"));
                 categoria.setImagen(resultado.getString("imagen"));
                 categorias.add(categoria);
             }
             
         } catch (SQLException ex) {
             ex.printStackTrace();
         }finally{
             ConnectionFactory.closeConnection();
         }
        return categorias; 
    }

    /*@Override
    public Boolean categoriaNueva(Categoria categoria) {
        Boolean retorno = true;
        String sql = "insert into categorias (denominacion, imagen) values (?,?)";
        Connection conexion = null;
        
        try{
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, categoria.getDenominacion());
            statement.setString(2, categoria.getImagen());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally {
            ConnectionFactory.closeConnection();
        }
        
        
        return retorno;
    }*/
        

}
