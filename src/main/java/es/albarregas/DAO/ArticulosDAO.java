/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paco
 */
public class ArticulosDAO implements IArticulosDAO{
    
    @Override
    public ArrayList<Articulo> getArticulosXCategorias(Categoria categoria) {
        ArrayList<Articulo> lista = new ArrayList();
        String sql = "select * from articulos where idCategoria = (select idCategoria from categorias where denominacion = '"+categoria.getDenominacion()+"')";
        
        try{
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
             while (resultado.next()){
                 Articulo articulo = new Articulo();
                 articulo.setIdArticulo(resultado.getInt("idArticulo"));
                 articulo.setDescripcionCorta(resultado.getString("descripcionCorta"));
                 articulo.setDescripcion(resultado.getString("descripcion"));
                 articulo.setIdCategoria(resultado.getInt("idCategoria"));
                 articulo.setIdCliente(resultado.getInt("idCliente"));
                 articulo.setFechaInicio(resultado.getDate("fechaInicio"));
                 articulo.setFechaFin(resultado.getDate("fechaFin"));
                 articulo.setImporteSalida(resultado.getDouble("importeSalida"));
                 lista.add(articulo);
             }
             System.out.println(sql);
             resultado.close();
             
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection();
        }
        
        return lista;
    }

    @Override
    public ArrayList<Articulo> getArticulos() {
        ArrayList<Articulo> lista = new ArrayList();
        String sql = "select * from articulos";
        
        try{
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
             while (resultado.next()){
                 Articulo articulo = new Articulo();
                 articulo.setIdArticulo(resultado.getInt("idArticulo"));
                 articulo.setDescripcionCorta(resultado.getString("descripcionCorta"));
                 articulo.setDescripcion(resultado.getString("descripcion"));
                 articulo.setIdCategoria(resultado.getInt("idCategoria"));
                 articulo.setIdCliente(resultado.getInt("idCliente"));
                 articulo.setFechaInicio(resultado.getDate("fechaInicio"));
                 articulo.setFechaFin(resultado.getDate("fechaFin"));
                 articulo.setImporteSalida(resultado.getDouble("importeSalida"));
                 lista.add(articulo);
             }
             resultado.close();
             
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection();
        }
        
        return lista;
    }

    @Override
    public Boolean newArticulo(Articulo articulo) {
        Boolean retorno = true;
        String sql = "insert into articulos (descripcionCorta, descripcion, idCategoria, idCliente, fechaInicio, fechaFin, importeSalida) values (?,?,?,?,?,?,?)";
        Connection conexion = null;
        
        try{
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);
            
            statement.setString(1, articulo.getDescripcionCorta());
            statement.setString(2, articulo.getDescripcion());
            statement.setInt(3, articulo.getIdCategoria());
            statement.setInt(4, articulo.getIdCliente());
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            String fechaInicio = sdf.format(articulo.getFechaInicio());
            statement.setString(5, fechaInicio);
            
            String fechaFin = sdf.format(articulo.getFechaFin());
            statement.setString(6, fechaFin);
            
            
            statement.setDouble(7, articulo.getImporteSalida());
            System.out.println(statement);
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
