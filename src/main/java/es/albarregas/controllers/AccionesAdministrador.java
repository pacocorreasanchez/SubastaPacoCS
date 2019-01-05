/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.ICategoriasDAO;
import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author paco
 */
@WebServlet(name = "AccionesAdministrador", urlPatterns = {"/AccionesAdministrador"})
public class AccionesAdministrador extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String url = "";

        if (request.getParameter("operacion").equals("salir")) {
            url = "index.jsp";
            sesion.invalidate();
        }
        if (request.getParameter("operacion").equals("Bloquear")) {
            url = "JSPAdministrador/bloquearUnUsuario.jsp";
        }
        if (request.getParameter("operacion").equals("Desbloquear")) {
            url = "JSPAdministrador/desbloquearUnUsuario.jsp";
        }

        if (request.getParameter("operacion").equals("bloquearUser")) {
            bloquearUser(request, response, url);
        }

        if (request.getParameter("operacion").equals("desbloquearUser")) {
            desBloquearUser(request, response, url);
        }

        if (request.getParameter("operacion").startsWith("Copias")) {

        }

        /*if (request.getParameter("operacion").startsWith("Añadir")) {
            url = "JSPAdministrador/anadirCategoria.jsp";
        }
        if (request.getParameter("operacion").equals("realizar")) {
            anadirCategoria(request, response, url);
        }*/
        request.getRequestDispatcher(url).forward(request, response);
    }

    public void bloquearUser(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("mailBlo"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        odao.bloquearUsuario(usuario);

        url = "JSPAdministrador/principalAdministrador.jsp";

        request.getRequestDispatcher(url).forward(request, response);
    }

    public void desBloquearUser(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("mailDesblo"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        odao.desbloquearUsuario(usuario);

        url = "JSPAdministrador/principalAdministrador.jsp";

        request.getRequestDispatcher(url).forward(request, response);
    }

    /*public void anadirCategoria(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        Categoria categoria = new Categoria();
        
        categoria.setDenominacion(request.getParameter("denominacion"));
        
        if(request.getParameter("fotos[]")){
            
        }
        categoria.setImagen(request.getParameter(""));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        ICategoriasDAO odao = daof.getCategoriasDAO();
        odao.categoriaNueva(categoria);
        

        url = "index.jsp";
        
        request.getRequestDispatcher(url).forward(request, response);
        
        
    }*/
}
