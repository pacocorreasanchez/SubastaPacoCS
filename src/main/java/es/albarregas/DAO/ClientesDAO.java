/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author paco
 */
public class ClientesDAO implements IClientesDAO {

    @Override
    public Cliente getCliente(String email) {
        Cliente cliente = null;
        Connection conexion = null;
        String sql = "select * from clientes where idCliente=(select idUsuario from usuarios where email=?)";

        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, email);
            System.out.println(statement);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("idCliente"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido1(resultado.getString("apellido1"));
                cliente.setApellido2(resultado.getString("apellido2"));
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setNif(resultado.getString("nif"));
                cliente.setAvatar(resultado.getString("avatar"));
                resultado.close();
                return cliente;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    @Override
    public Boolean insertCliente(Cliente cliente) {
        Connection conexion = null;
        String sql = "insert into clientes (nombre, apellido1, apellido2, nif, direccion, telefono, avatar, idCliente) values(?,?,?,?,?,?,?,?)";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido1());
            statement.setString(3, cliente.getApellido2());
            statement.setString(4, cliente.getNif());
            statement.setString(5, cliente.getDireccion());
            statement.setString(6, cliente.getTelefono());
            statement.setString(7, cliente.getAvatar());
            statement.setInt(8, cliente.getIdCliente());
            System.out.println(statement);
            statement.executeUpdate();

        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection();
        }

        return true;
    }

    @Override
    public Boolean actualizarDatosCliente(Cliente cliente) {
        Connection conexion = null;
        String sql = "update clientes set nombre=?, apellido1=?, apellido2=?, direccion=?, telefono=?, avatar=? where idCliente=?";
        try {

            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido1());
            statement.setString(3, cliente.getApellido2());
            statement.setString(4, cliente.getDireccion());
            statement.setString(5, cliente.getTelefono());
            statement.setString(6, cliente.getAvatar());
            statement.setInt(7, cliente.getIdCliente());
            System.out.println(statement);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection();
        }

        return true;
    }

}
