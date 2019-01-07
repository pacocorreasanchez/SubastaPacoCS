/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.CaracYArt;
import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paco
 */
public class ArticulosDAO implements IArticulosDAO {

    @Override
    public ArrayList<Articulo> getArticulosXCategorias(Categoria categoria) {
        ArrayList<Articulo> lista = new ArrayList();
        String sql = "select * from articulos where idCategoria = (select idCategoria from categorias where denominacion = '" + categoria.getDenominacion() + "')";

        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    @Override
    public ArrayList<Articulo> getArticulos() {
        ArrayList<Articulo> lista = new ArrayList();
        String sql = "select * from articulos";

        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            int idArticulo = -1;
            while (resultado.next()) {
                Articulo articulo = new Articulo();
                articulo.setIdArticulo(resultado.getInt("idArticulo"));
                idArticulo = resultado.getInt("idArticulo");
                articulo.setDescripcionCorta(resultado.getString("descripcionCorta"));
                articulo.setDescripcion(resultado.getString("descripcion"));
                articulo.setIdCategoria(resultado.getInt("idCategoria"));
                articulo.setIdCliente(resultado.getInt("idCliente"));
                articulo.setFechaInicio(resultado.getDate("fechaInicio"));
                articulo.setFechaFin(resultado.getDate("fechaFin"));
                articulo.setImporteSalida(resultado.getDouble("importeSalida"));
                

                /*sql = "select * from caracyart as c, caracteristicas as ca where idArticulo=" + idArticulo + " and c.idCaracteristica = ca.idCaracteristica;";
                System.out.println(sql);
                Statement sentencia2 = ConnectionFactory.getConnection().createStatement();
                ResultSet resultado2 = sentencia2.executeQuery(sql);
                ArrayList<CaracYArt> caracYArts = new ArrayList();
                while (resultado2.next()) {
                    CaracYArt car = new CaracYArt();
                    car.setIdArticulo(resultado2.getInt("idArticulo"));
                    car.setIdCaracteristica(resultado2.getInt("idCaracteristica"));
                    car.setValor(resultado2.getString("valor"));
                    car.setDenominacion(resultado2.getString("denominacion"));
                    caracYArts.add(car);
                }
                resultado2.close();
                
                articulo.setCaracteristicas(caracYArts);*/
                
                lista.add(articulo);
            }
            resultado.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    @Override
    public Boolean newArticulo(Articulo articulo) {
        Boolean retorno = true;
        String sql = "insert into articulos (descripcionCorta, descripcion, idCategoria, idCliente, fechaInicio, fechaFin, importeSalida) values (?,?,?,?,now(),?,?)";
        Connection conexion = null;

        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, articulo.getDescripcionCorta());
            statement.setString(2, articulo.getDescripcion());
            statement.setInt(3, articulo.getIdCategoria());
            statement.setInt(4, articulo.getIdCliente());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            /*String fechaInicio = sdf.format(articulo.getFechaInicio());
            statement.setString(5, fechaInicio);*/

            String fechaFin = sdf.format(articulo.getFechaFin());
            statement.setString(5, fechaFin);

            statement.setDouble(6, articulo.getImporteSalida());
            System.out.println(statement);
            statement.executeUpdate();

            ResultSet rset = statement.getGeneratedKeys();
            rset.next();
            int idArticulo = rset.getInt(1);

            ArrayList<CaracYArt> caracYart = articulo.getCaracteristicas();
            String sqlCarArt = "";
            for (CaracYArt c : caracYart) {
                sqlCarArt = "insert into caracyart (idArticulo, idCaracteristica, valor) values(" + idArticulo + ",'" + c.getIdCaracteristica() + "','" + c.getValor() + "')";
                statement = conexion.prepareStatement(sqlCarArt);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally {
            ConnectionFactory.closeConnection();
        }
        return retorno;
    }

}
