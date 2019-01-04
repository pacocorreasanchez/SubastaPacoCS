<%-- 
    Document   : vehiculos
    Created on : 3 ene. 2019, 18:23:46
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" content="text/css" href="css/estilos.css">
        <title>Pujas-Vehículos</title>
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


            <c:if test = "${articulosXcategorias != null}">
                <div class="categorias">
                    <c:forEach var="art" items="${articulosXcategorias}">
                        <div class="div-img" >
                            <img class="img" src="img/arte.jpg" title="Arte" alt="Arte">
                            <input style="font-size: 15px !important;" type="submit" class="text" value="${art.descripcionCorta}" name="operacion"/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

        </form>
        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
