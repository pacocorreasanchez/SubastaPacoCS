/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Usuario;

/**
 *
 * @author paco
 */
public interface IUsuariosDAO {
    public Usuario getUsuarioAdministrador(Usuario usuario);
    public Usuario getUsuarioEstandar(Usuario usuario);
}
