/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author paco
 */
public class Usuario implements Serializable{
    
    private int idUsuario;
    private String email;
    private String password;
    private Date ultimoAcceso;
    private String tipoAcceso;
    private String bloqueado;
    private int valorMas;
    private int valorMenos;
    private Cliente cliente;

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the ultimoAcceso
     */
    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    /**
     * @param ultimoAcceso the ultimoAcceso to set
     */
    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    /**
     * @return the tipoAcceso
     */
    public String getTipoAcceso() {
        return tipoAcceso;
    }

    /**
     * @param tipoAcceso the tipoAcceso to set
     */
    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }

    /**
     * @return the bloqueado
     */
    public String getBloqueado() {
        return bloqueado;
    }

    /**
     * @param bloqueado the bloqueado to set
     */
    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * @return the valorMas
     */
    public int getValorMas() {
        return valorMas;
    }

    /**
     * @param valorMas the valorMas to set
     */
    public void setValorMas(int valorMas) {
        this.valorMas = valorMas;
    }

    /**
     * @return the valorMenos
     */
    public int getValorMenos() {
        return valorMenos;
    }

    /**
     * @param valorMenos the valorMenos to set
     */
    public void setValorMenos(int valorMenos) {
        this.valorMenos = valorMenos;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
