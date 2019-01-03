/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IArticulosDAO;
import es.albarregas.DAO.ICaracteristicasDAO;
import es.albarregas.DAO.IClientesDAO;
import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Articulo;
import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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

        String url = "";

        if (request.getParameter("operacion").equals("registrar")) {
            insertarUsuario(request, response, url);
        }
        
        if (request.getParameter("operacion").equals("Salir")) {
            url="index.jsp";
        }
        
        if (request.getParameter("operacion").equals("Inicio")) {
            url="jsp/usuarios.jsp";
        }
        
        if (request.getParameter("operacion").startsWith("Subir")) {
            url="jsp/crearPuja.jsp";
        }
        
        if (request.getParameter("operacion").equals("subirPuja")) {
            insertarArticulo(request, response, url);
        }
        
        
        if (request.getParameter("operacion").startsWith("Actualizar")) {
            url="jsp/actualizar.jsp";
        }
        

        request.getRequestDispatcher(url).forward(request, response);
    }

    public void insertarUsuario(HttpServletRequest request, HttpServletResponse response, String url) {
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
        if (request.getParameter("avatar") == null || request.getParameter("avatar").length() < 1) {
            usuario.getCliente().setAvatar("avatar.png");
        } else {
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

        //notificar al usuario que se ha completado el registro
        if (usuario != null && cliente != null) {
            request.setAttribute("registroCorrecto", "Se ha efectuado correctamente el registro");
        }
    }

    public void insertarArticulo(HttpServletRequest request, HttpServletResponse response,  String url) {
        Articulo articulo = new Articulo();
        Cliente cliente = new Cliente();
        Date fechaActual = new Date();
        Categoria categoria = new Categoria();
        HttpSession sesion = null;
        
        
        categoria.setIdCategoria(Integer.parseInt(request.getParameter("opcion")));
        categoria.setDenominacion(request.getParameter("denominacion"));
        
        articulo.setDescripcionCorta(request.getParameter("desCorta"));
        articulo.setDescripcion(request.getParameter("descripcion"));
        articulo.setIdCategoria(9);
        articulo.setFechaInicio(fechaActual);
        articulo.setFechaFin(fechaActual);//formatear para fechaFin
        articulo.setImporteSalida(Double.parseDouble(request.getParameter("importe")));
        
        sesion.getAttribute("cliente");
        articulo.setIdCliente(cliente.getIdCliente());
        
        
        DAOFactory daof = DAOFactory.getDAOFactory(1);

        IArticulosDAO odao = daof.getArticulosDAO();
        odao.newArticulo(articulo);
        
        url="jsp/usuarios.jsp";
        
    }
}
