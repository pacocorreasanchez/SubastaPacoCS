<%-- 
    Document   : crearPuja
    Created on : 2 ene. 2019, 11:17:46
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" content="text/css" href="css/estilos.css">
        <script src="js/jQueryJavaScriptLibraryv3.3.1.js"></script>
        <script src="js/caracteristicas.js"></script>
        <title>Nueva subasta</title>
    </head>
    <body>
        <form action="UsuariosYClientes" method="post">
            <ul>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Inicio"/></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Subir puja" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Pujas" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Actualizar datos" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Salir" /></li>
                <h1 style="color: white; font-size: 30px; float: right; margin-right: 10px;">Hola <c:out value = "${sessionScope.usuarioLogeado.cliente.nombre}"/> <c:out value = "${sessionScope.usuarioLogeado.cliente.apellido1}"/></h1>
            </ul>
        </form>
        <form action="UsuariosYClientes" method="post">
            <fieldset style="padding-left: 20px;">
                <legend>Nueva puja</legend>
                <label>Descripción corta:</label>
                <input type="text" name="desCorta" required="required"/>
                <br>
                <label>Descripción:</label>
                <input type="text" name="descripcion"/>
                <br>
                <select name="opcion">
                    <option disabled selected>Selecciona una categoría</option>
                    <c:forEach var="c" items="${applicationScope.categorias}">
                        <option  value="${c.idCategoria}">${c.denominacion}</option>
                    </c:forEach>
                </select>
                <br>
                <div class="caracteristicas">
                </div>
                <br>
                <label>¿Cúando quieres que acabe tu subasta?:</label>
                <input type="date" name="fechaFin"/><input type="time" />
                <br>
                <label>Fotos (png y como máximo 100 KB): </label>
                <input type="file" name="fotos" multiple />
                <br>
                <label>Importe de salida:</label>
                <input type="number" placeholder="0.00" name="importe" required="required"/>
            </fieldset>
            <br>
            <button type="submit" value="subirPuja" name="operacion">Añadir puja</button>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>
        </form>
            
            
            
        <div class="caracteristicasOcultas">
            <c:forEach var="caract" items="${applicationScope.caracteristicas}">
                <div class="caracteristica" idCategoria="${caract.idCategoria}">
                    <label >${caract.denominacion}</label>
                    <input  type="text" name="${caract.idCaracteristica}"/>
                    <br>
                </div>
            </c:forEach>
        </div>
            
            
        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
