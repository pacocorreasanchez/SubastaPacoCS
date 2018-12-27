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
@WebServlet(name = "UsuariosYClientes", urlPatterns = {"/UsuariosYClientes"})
public class UsuariosYClientes extends HttpServlet {



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
        
        String url="";
        
        if (request.getParameter("operacion").equals("registrar")) {
            insertarUsuario(request, response, url);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    public void insertarUsuario(HttpServletRequest request, HttpServletResponse response, String url){
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        
        usuario.setEmail(request.getParameter("emailRegistro"));
        usuario.setPassword(request.getParameter("contraseniaResgistro"));
        
        
        cliente.setNombre(request.getParameter("nombreRegistro"));
        cliente.setApellido1(request.getParameter("ape1Registro"));
        cliente.setApellido2(request.getParameter("ape2Registro"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setNif(request.getParameter("nif"));
        cliente.setTelefono(request.getParameter("telefono"));
        
        usuario.setCliente(cliente);
        
        
        //si no viene avatar por avatar.png
        if(request.getParameter("avatar") == null || request.getParameter("avatar").length() < 1){
            usuario.getCliente().setAvatar("avatar.png");
        }else{
            usuario.getCliente().setAvatar(request.getParameter("avatar"));
        }
        
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        
        IUsuariosDAO odaoUser = daof.getUsuarioDAO();
        odaoUser.insertUsuario(usuario);
        
        
        
        Usuario us = null;
        us = odaoUser.getUsuariosID(usuario);
        
        cliente.setIdCliente(us.getIdUsuario());
        
        IClientesDAO odaoClient = daof.getClientesDAO();
        odaoClient.insertCliente(cliente);
        
        
        
        
        //notificar al usuario que se ha completado
    }

}
