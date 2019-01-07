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
 * Este servlet sirve para redirigir al menú de usuario administrador o usuario común
 * Recibe las peticiones del index.jsp del formulario de acceso.
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

        //Si el usuario intenta acceder pero no está registrado, aparece un mensaje de error
        if (request.getParameter("operacion").equals("accede") && !loginUsuario(request, response, sesion, url)) {
            request.setAttribute("error", "Usuario no registrado.");
        }
    }

    /**
     * 
     * @param request
     * @param response
     * @param sesion
     * @param url
     * @return
     * @throws ServletException
     * @throws IOException
     * Este método recoge los parámetros del formulario de acceso y dependiendo de el tipo de usuario
     * entra en una vista o en otra. Si el usuario es administrador, entra en las vistas de administrador
     * y si es un usuario común, entra en otras vistas diferentes diseñadas para ese tipo de usuarios.
     */
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

        if (cliente != null) {
            usuarioLogeado.setCliente(cliente);
        }

        if (usuarioLogeado != null) {
            sesion.setAttribute("usuarioLogeado", usuarioLogeado);
            sesion.setAttribute("cliente", cliente);

            if (usuarioLogeado.getTipoAcceso().equals("a")) {
                url = "JSPAdministrador/principalAdministrador.jsp";
            } else if (usuarioLogeado.getTipoAcceso().equals("u")) {
                url = "jsp/usuarios.jsp";
            }
            
        } else {
            url = "index.jsp";
            request.setAttribute("userNoRegistrado", "El usuario no está registrado o es incorrecto");
        }

        request.getRequestDispatcher(url).forward(request, response);
        return usuarioLogeado != null;
    }

}
