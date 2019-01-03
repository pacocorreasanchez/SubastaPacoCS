/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.applications;

import es.albarregas.DAO.ICaracteristicasDAO;
import es.albarregas.DAO.ICategoriasDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author paco
 */
@WebListener //IMPORTANTE PONER ESTA ANOTACION, SI NO, NO FUNCIONA
public class CategoriasApplication implements ServletContextListener {

    /**
     * Metemos a las categorías en el ámbito de la aplicación para que siempre
     * estén disponibles
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        ICategoriasDAO odao = daof.getCategoriasDAO();
        ArrayList<Categoria> categorias = odao.obtenerCategorias();
        
        Categoria categoria = new Categoria();
        
        ICaracteristicasDAO odaoCarac = daof.getCaracteristicasDAO();
        ArrayList<Caracteristica> caracteristicas = odaoCarac.getCaracteristicas(categoria);
        synchronized (application) {
            application.setAttribute("categorias", categorias);
            application.setAttribute("caracteristicas", caracteristicas);
        }

    }

    /**
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        application.removeAttribute("categorias");
        application.removeAttribute("caracteristicas");
    }

}
