/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Articulo;
import es.albarregas.beans.Categoria;
import java.util.ArrayList;

/**
 *
 * @author paco
 */
public interface IArticulosDAO {
    public ArrayList <Articulo> getArticulosXCategorias(Categoria categoria);
    public ArrayList <Articulo> getArticulos();
    public Boolean newArticulo(Articulo articulo);
}
