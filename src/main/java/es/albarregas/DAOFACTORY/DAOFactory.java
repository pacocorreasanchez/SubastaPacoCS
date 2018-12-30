/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAOFACTORY;

import es.albarregas.DAO.IArticulosDAO;
import es.albarregas.DAO.ICaracYArtDAO;
import es.albarregas.DAO.ICaracteristicasDAO;
import es.albarregas.DAO.ICategoriasDAO;
import es.albarregas.DAO.IClientesDAO;
import es.albarregas.DAO.IFotografiaDAO;
import es.albarregas.DAO.IPujasDAO;
import es.albarregas.DAO.IUsuariosDAO;



/**
 *
 * @author paco
 */
public abstract class DAOFactory {

    public static final int MYSQL = 1;
    
    public abstract IArticulosDAO getArticulosDAO();
    public abstract ICategoriasDAO getCategoriasDAO();
    public abstract IClientesDAO getClientesDAO();
    public abstract IPujasDAO getPujasDAO();
    public abstract IUsuariosDAO getUsuarioDAO();
    public abstract IFotografiaDAO getFotografiaDAO();
    public abstract ICaracYArtDAO getCaracYArtDAO();
    public abstract ICaracteristicasDAO getCaracteristicasDAO();

    public static DAOFactory getDAOFactory(int tipo) {
        DAOFactory daof = null;

        switch (tipo) {
            case MYSQL:
                daof = new MySQLDAOFactory();
                break;
        }
        return daof;
    }
}
