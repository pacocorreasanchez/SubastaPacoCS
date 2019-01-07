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
 * 
 * A este servlet vienen las peticiones de JSPAdministrador:
 *                                                          -bloquearUnUsuario.jsp
 *                                                          -desbloquearUnUsuario.jsp
 *                                                          -principalAdministrador.jsp
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

        //Cuando el administrador le da a salir
        if (request.getParameter("operacion").equals("salir")) {
            url = "index.jsp";
            sesion.invalidate();//cuando el administrador le da a salir, se invalida su sesión
        }
        //Cuando el administrador le da a bloquear, redirige a la vista para introducir el usuario a bloquear
        if (request.getParameter("operacion").equals("Bloquear")) {
            url = "JSPAdministrador/bloquearUnUsuario.jsp";
        }
        //Cuando el administrador le da a desbloquear, redirige a la vista para introducir el usuario a desbloquear
        if (request.getParameter("operacion").equals("Desbloquear")) {
            url = "JSPAdministrador/desbloquearUnUsuario.jsp";
        }
        //Cuando el administrador está en la vista bloquearUnUsuario.jsp introduce el mail y se ejecuta el método
        if (request.getParameter("operacion").equals("bloquearUser")) {
            bloquearUser(request, response, url);
        }
    //Cuando el administrador está en la vista desbloquearUnUsuario.jsp introduce el mail y se ejecuta el método
        if (request.getParameter("operacion").equals("desbloquearUser")) {
            desBloquearUser(request, response, url);
        }

        //Esto vale para crear categorías nuevas
        /*if (request.getParameter("operacion").startsWith("Añadir")) {
            url = "JSPAdministrador/anadirCategoria.jsp";
        }
        if (request.getParameter("operacion").equals("realizar")) {
            anadirCategoria(request, response, url);
        }*/
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @param url
     * @throws ServletException
     * @throws IOException 
     * Este método permite bloquear a un usuario introduciendo el mail del mismo, lo que no le permitirá
     * entrar en el menú de los usuarios/clientes comunes
     */
    public void bloquearUser(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("mailBlo"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        odao.bloquearUsuario(usuario);

        url = "JSPAdministrador/principalAdministrador.jsp";

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @param url
     * @throws ServletException
     * @throws IOException 
     * Este método desbloquea a un usuario que estubiera bloqueado introduciendo el mail.
     */
    public void desBloquearUser(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("mailDesblo"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        odao.desbloquearUsuario(usuario);

        url = "JSPAdministrador/principalAdministrador.jsp";

        request.getRequestDispatcher(url).forward(request, response);
    }

    //Con este método se pueden crear categorías nuevas
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
