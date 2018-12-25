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

/**
 *
 * @author paco
 */
public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public Usuario getUsuarioAdministrador(Usuario usuario) {
        String sql = "select * from usuarios where email='admin@admin.com' and password=md5('admin') and tipoAcceso='a'";
        try {
            PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            Usuario usuarioLogin = null;
            if (resultado.next()) {
                usuarioLogin = new Usuario();
                usuarioLogin.setIdUsuario(resultado.getInt("idUsuario"));
                usuarioLogin.setEmail(resultado.getString("email"));
                resultado.close();
                return usuarioLogin;
            }
            resultado.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Usuario getUsuarioEstandar(Usuario usuario) {
        String sql = "select * from usuarios where email=? and password=md5(?) and bloqueado='n' and tipoAcceso='u'";
        
        try {
            PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
            statement.setString(1,usuario.getEmail());
	   statement.setString(2,usuario.getPassword());
           
            ResultSet resultado = statement.executeQuery();
            Usuario usuarioLogin = null;
            if (resultado.next()) {
                usuarioLogin = new Usuario();
                usuarioLogin.setIdUsuario(resultado.getInt("idUsuario"));
                usuarioLogin.setEmail(resultado.getString("email"));
                resultado.close();
                return usuarioLogin;
            }
            resultado.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return null;
    }

}
