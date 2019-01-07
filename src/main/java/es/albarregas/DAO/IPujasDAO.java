/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.Cliente;
import es.albarregas.beans.Puja;
import java.util.ArrayList;

/**
 *
 * @author paco
 */
public interface IPujasDAO {
    public ArrayList<Puja> obtenerPujas();
    public Boolean newPuja(Puja puja, Cliente cliente);
    public Puja getMaxPuja(Articulo articulo);
}
