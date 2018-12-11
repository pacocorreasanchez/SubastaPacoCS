/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paco
 */
@WebServlet(name = "RedireccionAdministrador", urlPatterns = {"/RedireccionAdministrador"})
public class RedireccionAdministrador extends HttpServlet {



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
        String email = request.getParameter("email");
        String contrasenia = request.getParameter("contrasenia");
        String url = "";

        if (request.getParameter("operacion").equals("accede")) {
            if (email.equals("admin") && contrasenia.equals("admin")) {
                url="JSPAdministrador/principalAdministrador.jsp";
            } else { 
                request.setAttribute("error", "Usuario no registrado.");
                url="index.jsp";
            }
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }


}
