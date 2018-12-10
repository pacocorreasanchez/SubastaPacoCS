/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;

/**
 *
 * @author paco
 */
public class Fotografia implements Serializable{
    private int idFotografia;
    private int idArticulo;
    private String fotografia;

    /**
     * @return the idFotografia
     */
    public int getIdFotografia() {
        return idFotografia;
    }

    /**
     * @param idFotografia the idFotografia to set
     */
    public void setIdFotografia(int idFotografia) {
        this.idFotografia = idFotografia;
    }

    /**
     * @return the idArticulo
     */
    public int getIdArticulo() {
        return idArticulo;
    }

    /**
     * @param idArticulo the idArticulo to set
     */
    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    /**
     * @return the fotografia
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
    
    
}
