/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.Fotografia;
import java.util.ArrayList;

/**
 *
 * @author paco
 */
public interface IFotografiaDAO {
    public ArrayList<Fotografia> getFotografia();
    public Boolean insertarFoto(Fotografia fotografia, Articulo articulo);
}
