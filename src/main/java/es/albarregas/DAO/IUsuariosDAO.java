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
    public Usuario getUsuarios(Usuario usuario);
    public Usuario getUsuariosID(Usuario usuario);
    public Boolean insertUsuario(Usuario usuario);
    public Boolean bloquearUsuario(Usuario usuario);
    public Boolean desbloquearUsuario(Usuario usuario);
}
