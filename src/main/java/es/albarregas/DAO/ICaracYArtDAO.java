/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.CaracYArt;
import es.albarregas.beans.Articulo;
import es.albarregas.beans.Caracteristica;
import java.util.ArrayList;

/**
 *
 * @author paco
 */
public interface ICaracYArtDAO {
    public ArrayList<CaracYArt> obtenerCaracYArt();
    public Boolean newCaractYArt(CaracYArt caracYArt, Articulo articulo, Caracteristica caracteristica);
}
