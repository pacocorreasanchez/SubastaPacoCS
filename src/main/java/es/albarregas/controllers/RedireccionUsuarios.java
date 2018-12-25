/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
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
        String url = "index.jsp";
        HttpSession sesion = request.getSession();

        if (request.getParameter("operacion").equals("accede") && loginAdministrador(request, sesion)) {
            url = "JSPAdministrador/principalAdministrador.jsp";
        } else {
            request.setAttribute("error", "Usuario no registrado.");
            url = "index.jsp";
        }

        if (request.getParameter("operacion").equals("accede") && loginUsuarios(request, sesion)) {
            url = "jsp/usuarios.jsp";
        } else {
            request.setAttribute("error", "Usuario no registrado.");
            url = "index.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }



    public boolean loginAdministrador(HttpServletRequest request, HttpSession sesion) {

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("contrasenia"));
        usuario.setTipoAcceso(request.getParameter("a"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        Usuario adminUsuario = odao.getUsuarioAdministrador(usuario);

        sesion.setAttribute("adminUsuario", adminUsuario);
        return adminUsuario != null;
    }

    public boolean loginUsuarios(HttpServletRequest request, HttpSession sesion) {

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("contrasenia"));
        usuario.setTipoAcceso(request.getParameter("u"));
        usuario.setBloqueado(request.getParameter("n"));

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        Usuario usuarioEstandar = odao.getUsuarioEstandar(usuario);

        sesion.setAttribute("usuarioEstandar", usuarioEstandar);
        return usuarioEstandar != null;
    }
}
