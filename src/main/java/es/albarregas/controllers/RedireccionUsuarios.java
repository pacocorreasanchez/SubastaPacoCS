/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IClientesDAO;
import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import java.io.IOException;
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
@WebServlet(name = "RedireccionUsuarios", urlPatterns = {"/RedireccionUsuarios"})
public class RedireccionUsuarios extends HttpServlet {

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
        String url = "";
        HttpSession sesion = request.getSession();

        if (request.getParameter("operacion").equals("accede") && !loginUsuario(request, response, sesion, url)) {
            request.setAttribute("error", "Usuario no registrado.");
        }

        request.getRequestDispatcher(url).forward(request, response);
    }



    public boolean loginUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession sesion, String url) throws ServletException, IOException {
        
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("contrasenia"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        Usuario usuarioLogeado = odao.getUsuarios(usuario);
        
        Cliente cliente = new Cliente();
        IClientesDAO odaoClient = daof.getClientesDAO();
        cliente = odaoClient.getCliente(usuario.getEmail());
        
        //si el usuario es null return false
        if(usuarioLogeado == null){
            return false;
        }
        
        
        if(cliente!=null){
            usuarioLogeado.setCliente(cliente);
        }
        

        if(usuarioLogeado != null){
            sesion.setAttribute("usuarioLogeado", usuarioLogeado);
        } else{
            usuarioLogeado = null;
        }
        
        
        if(usuarioLogeado.getTipoAcceso().equals("a")){
            url = "JSPAdministrador/principalAdministrador.jsp";
        } else{
            url = "jsp/usuarios.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
        return usuarioLogeado != null;
    }

}
