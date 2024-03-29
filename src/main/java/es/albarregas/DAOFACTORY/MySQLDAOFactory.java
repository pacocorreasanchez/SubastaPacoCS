/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAOFACTORY;

import es.albarregas.DAO.ArticulosDAO;
import es.albarregas.DAO.CaracYArtDAO;
import es.albarregas.DAO.CaracteristicasDAO;
import es.albarregas.DAO.CategoriasDAO;
import es.albarregas.DAO.ClientesDAO;
import es.albarregas.DAO.FotografiasDAO;
import es.albarregas.DAO.IArticulosDAO;
import es.albarregas.DAO.ICaracYArtDAO;
import es.albarregas.DAO.ICaracteristicasDAO;
import es.albarregas.DAO.ICategoriasDAO;
import es.albarregas.DAO.IClientesDAO;
import es.albarregas.DAO.IFotografiaDAO;
import es.albarregas.DAO.IPujasDAO;
import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAO.PujasDAO;
import es.albarregas.DAO.UsuariosDAO;
import es.albarregas.beans.Caracteristica;


/**
 *
 * @author paco
 */
public class MySQLDAOFactory extends DAOFactory {

    @Override
    public IArticulosDAO getArticulosDAO() {
        return new ArticulosDAO();
    }

    @Override
    public ICategoriasDAO getCategoriasDAO() {
        return new CategoriasDAO();
    }

    @Override
    public IClientesDAO getClientesDAO() {
        return new ClientesDAO();
    }

    @Override
    public IPujasDAO getPujasDAO() {
        return new PujasDAO();
    }

    @Override
    public IUsuariosDAO getUsuarioDAO() {
        return new UsuariosDAO();
    }

    @Override
    public IFotografiaDAO getFotografiaDAO() {
        return new FotografiasDAO();
    }

    @Override
    public ICaracYArtDAO getCaracYArtDAO() {
        return new CaracYArtDAO();
    }

    @Override
    public ICaracteristicasDAO getCaracteristicasDAO() {
        return new CaracteristicasDAO();
    }


}
