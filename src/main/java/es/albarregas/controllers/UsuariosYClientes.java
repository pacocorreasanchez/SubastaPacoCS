/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IArticulosDAO;
import es.albarregas.DAO.ICaracteristicasDAO;
import es.albarregas.DAO.IClientesDAO;
import es.albarregas.DAO.IPujasDAO;
import es.albarregas.DAO.IUsuariosDAO;
import es.albarregas.DAOFACTORY.DAOFactory;
import es.albarregas.beans.Articulo;
import es.albarregas.beans.CaracYArt;
import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.Cliente;
import es.albarregas.beans.Puja;
import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

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
        System.out.println("hola");
        HttpSession sesion = request.getSession();
        String url = "";
        
        if (request.getParameter("operacion") != null) {
            if (request.getParameter("operacion").equals("registrar")) {
                insertarUsuario(request, response, url);
            }
            
            if (request.getParameter("operacion").equals("Salir")) {
                url = "index.jsp";
                sesion.invalidate();
            }
            
            if (request.getParameter("operacion").equals("Inicio")) {
                url = "jsp/usuarios.jsp";
            }
            
            if (request.getParameter("operacion").startsWith("Subir")) {
                url = "jsp/crearPuja.jsp";
            }
            
            if (request.getParameter("operacion").equals("subirPuja")) {
                insertarArticulo(request, response, url);
                
            }
            
            if (request.getParameter("operacion").startsWith("Actualizar")) {
                url = "jsp/actualizar.jsp";
            }
            
            if (request.getParameter("operacion").equals("Pujas")) {
                url = "jsp/pujas.jsp";
                obtenerArticulos(request, response, url);
            }
            if (request.getParameter("operacion").equals("guardarCambios")) {
                actualizarDatos(request, response);
                url = "jsp/usuarios.jsp";
            }
            if (request.getParameter("operacion").equals("pujar")) {
                pujar(request, response);
                getMaxPuja(request, response);
                //url = "jsp/pujas.jsp";
            }
        }
        
        if (request.getParameter("redireccionCategorias") != null) {
            url = "jsp/" + request.getParameter("redireccionCategorias").toLowerCase() + ".jsp";
            url = StringUtils.stripAccents(url);
            obtenerArticulosXCategorias(request, response, url);
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
    
    public void insertarArticulo(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        Articulo articulo = new Articulo();
        Cliente cliente = new Cliente();
        Date fechaActual = new Date();
        HttpSession sesion = request.getSession();
        try {
            String s = request.getParameter("fechaFin");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = sdf.parse(s);
            
            articulo.setDescripcionCorta(request.getParameter("desCorta"));
            articulo.setDescripcion(request.getParameter("descripcion"));
            articulo.setIdCategoria(Integer.parseInt(request.getParameter("opcion")));
            //articulo.setFechaInicio(fechaActual);
            articulo.setFechaFin(date);
            articulo.setImporteSalida(Double.parseDouble(request.getParameter("importe")));
            
            cliente = (Cliente) sesion.getAttribute("cliente");
            articulo.setIdCliente(cliente.getIdCliente());
            
            ArrayList<CaracYArt> caracYArts = new ArrayList();
            Map<String, String[]> caracteristicas = request.getParameterMap();
            for (Map.Entry<String, String[]> caracteristica : caracteristicas.entrySet()) {
                if (caracteristica.getKey().startsWith("caracteristica-")) {
                    //System.out.println(request.getParameter(caracteristica.getKey()));
                    CaracYArt car = new CaracYArt();
                    //car.setIdArticulo(0);
                    car.setIdCaracteristica(Integer.parseInt(caracteristica.getKey().split("-")[1]));
                    car.setValor(request.getParameter(caracteristica.getKey()));
                    car.setDenominacion(caracteristica.getKey().split("-")[2]);
                    caracYArts.add(car);
                }
            }
            articulo.setCaracteristicas(caracYArts);
            
            DAOFactory daof = DAOFactory.getDAOFactory(1);
            
            IArticulosDAO odao = daof.getArticulosDAO();
            odao.newArticulo(articulo);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        if (articulo != null) {
            
            url = "jsp/usuarios.jsp";
            request.setAttribute("pujasubida", "Se ha subido correctamente un nuevo artículo");
        }
        
        request.getRequestDispatcher(url).forward(request, response);
        
    }
    
    public void obtenerArticulos(HttpServletRequest request, HttpServletResponse response, String url) {
        HttpSession sesion = request.getSession();
        
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IArticulosDAO odao = daof.getArticulosDAO();
        ArrayList<Articulo> articulos = odao.getArticulos();
        
        sesion.setAttribute("articulos", articulos);
        
    }
    
    public void obtenerArticulosXCategorias(HttpServletRequest request, HttpServletResponse response, String url) {
        HttpSession sesion = request.getSession();
        
        Categoria categoria = new Categoria();
        categoria.setDenominacion(request.getParameter("redireccionCategorias"));
        
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IArticulosDAO odao = daof.getArticulosDAO();
        ArrayList<Articulo> articulosXcategorias = odao.getArticulosXCategorias(categoria);
        
        sesion.setAttribute("articulosXcategorias", articulosXcategorias);
    }
    
    public void actualizarDatos(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        HttpSession sesion = request.getSession();
        
        usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        usuario.setEmail(request.getParameter("emailUser"));
        
        cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        cliente.setNombre(request.getParameter("nombreUser"));
        cliente.setApellido1(request.getParameter("apellido1User"));
        cliente.setApellido2(request.getParameter("apellido2User"));
        cliente.setDireccion(request.getParameter("direccionUser"));
        cliente.setTelefono(request.getParameter("telefonoUser"));
        cliente.setAvatar(request.getParameter("avatarUser"));
        
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO odao = daof.getUsuarioDAO();
        odao.actualizarDatosUsuario(usuario);
        
        IClientesDAO odaoC = daof.getClientesDAO();
        odaoC.actualizarDatosCliente(cliente);
        
    }
    
    public void pujar(HttpServletRequest request, HttpServletResponse response) {
        Puja puja = new Puja();
        Cliente cliente = new Cliente();
        Articulo articulo = new Articulo();
        Date fechaActual = new Date();
        HttpSession sesion = request.getSession();
        
        cliente = (Cliente) sesion.getAttribute("cliente");
        
        puja.setIdArticulo(Integer.parseInt(request.getParameter("idArticulo")));
        puja.setIdCliente(cliente.getIdCliente());
        puja.setFecha(fechaActual);
        puja.setImporte(Double.parseDouble(request.getParameter("precioPujado")));
        
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPujasDAO odao = daof.getPujasDAO();
        odao.newPuja(puja, cliente);
    }
    
    public void obtenerPujas(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPujasDAO odao = daof.getPujasDAO();
        ArrayList<Puja> pujasActivas = odao.obtenerPujas();
        
        request.setAttribute("pujasActivas", pujasActivas);
    }
    
    private void getMaxPuja(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPujasDAO odao = daof.getPujasDAO();
        Puja puja = new Puja();
        Articulo articulo = new Articulo();
        articulo.setIdArticulo(Integer.parseInt(request.getParameter("idArticulo")));
        puja = odao.getMaxPuja(articulo);
        
        System.out.println(puja.getImporte());
    }
    
}
