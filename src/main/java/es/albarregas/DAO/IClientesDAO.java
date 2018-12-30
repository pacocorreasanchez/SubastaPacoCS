/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Cliente;

/**
 *
 * @author paco
 */
public interface IClientesDAO {
    public Cliente getCliente(String email);
    public Boolean insertCliente(Cliente cliente);
}
