/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.Cliente;
import es.albarregas.beans.Puja;
import java.sql.Connection;
import java.sql.Date;
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
public class PujasDAO implements IPujasDAO {

    @Override
    public ArrayList<Puja> obtenerPujas() {
        ArrayList<Puja> pujas = new ArrayList<Puja>();
        String sql = "select * from pujas";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                Puja p = new Puja();
                p.setIdArticulo(resultado.getInt("idArticulo"));
                p.setIdCliente(resultado.getInt("idCliente"));
                p.setFecha(resultado.getDate("fecha"));
                p.setImporte(resultado.getDouble("importe"));
                pujas.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pujas;
    }

    @Override
    public Boolean newPuja(Puja puja, Cliente cliente, Articulo articulo) {
        Boolean retorno = true;
        String sql = "insert into pujas (idCliente, idArticulo, fecha, importe) values ((select idCliente from clientes where idCliente="+cliente.getIdCliente()+"),(select idArticulo from articulos where idArticulo="+articulo.getIdArticulo()+"),?,?)";
        Connection conexion = null;

        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setDate(1, (Date) puja.getFecha());
            statement.setDouble(2, puja.getImporte());

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
