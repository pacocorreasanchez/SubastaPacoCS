/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author paco
 */
public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public Usuario getUsuarios(Usuario usuario) {
        Connection conexion = null;
        String sql = "select * from usuarios where email=? and password=md5(?) and bloqueado='n'";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getPassword());
            System.out.println(statement);
            ResultSet resultado = statement.executeQuery();
            Usuario usuarioLogin = null;
            if (resultado.next()) {
                usuarioLogin = new Usuario();
                usuarioLogin.setIdUsuario(resultado.getInt("idUsuario"));
                usuarioLogin.setEmail(resultado.getString("email"));
                usuarioLogin.setTipoAcceso(resultado.getString("tipoAcceso"));
                usuarioLogin.setBloqueado(resultado.getString("bloqueado"));
                usuarioLogin.setUltimoAcceso(resultado.getDate("ultimoAcceso"));
                usuarioLogin.setValorMas(resultado.getInt("valorMas"));
                usuarioLogin.setValorMenos(resultado.getInt("valorMenos"));
                resultado.close();
                System.out.println(usuario.getEmail());
                return usuarioLogin;
            }
            resultado.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    @Override
    public Boolean insertUsuario(Usuario usuario) {
        Connection conexion = null;
        String sql = "insert into usuarios (email, password, ultimoAcceso) values(?,md5(?), now())";
        try {

            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getPassword());
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

    @Override
    public Usuario getUsuariosID(Usuario usuario) {
        Connection conexion = null;
        String sql = "select idUsuario from usuarios where email=?";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());

            ResultSet resultado = statement.executeQuery();
            Usuario usuarioLogin = null;
            if (resultado.next()) {
                usuarioLogin = new Usuario();
                usuarioLogin.setIdUsuario(resultado.getInt("idUsuario"));
                resultado.close();
                return usuarioLogin;
            }
            resultado.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    @Override
    public Boolean bloquearUsuario(Usuario usuario) {
        Connection conexion = null;
        String sql = "update usuarios set bloqueado='s' where email=? and bloqueado='n' and tipoAcceso='u'";
        try {

            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
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

    @Override
    public Boolean desbloquearUsuario(Usuario usuario) {
        Connection conexion = null;
        String sql = "update usuarios set bloqueado='n' where email=? and bloqueado='s' and tipoAcceso='u'";
        try {

            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
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

    @Override
    public Boolean actualizarDatosUsuario(Usuario usuario) {
        Connection conexion = null;

        String sql = "update usuarios set email=?, password=md5(?) where idUsuario=? and bloqueado='n' and tipoAcceso='u'";
        try {

            conexion = ConnectionFactory.getConnection();
            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getPassword());
            statement.setInt(3, usuario.getIdUsuario());
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
