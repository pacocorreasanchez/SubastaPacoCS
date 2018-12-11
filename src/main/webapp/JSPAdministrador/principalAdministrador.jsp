<%-- 
    Document   : principalAdministrador
    Created on : 11 dic. 2018, 16:35:51
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" content="text/css" href="../css/estilos.css">
        <link rel="stylesheet" content="text/css" href="../css/estilosAdministrador.css">
        <title>Administrador-Inicio</title>
    </head>
    <body>
        <form action="AccionesAdministrador" method="post">
            <ul>
                <li><a type="submit" name="operacion" value="" href="#">Inicio</a></li>
                <li class="dropdown">
                    <a type="submit" name="operacion" value="" href="#" class="dropbtn">Usuarios</a>
                    <div class="dropdown-content">
                        <a type="submit" name="operacion" value="" href="#">Bloquear</a>
                        <a type="submit" name="operacion" value="" href="#">Desbloquear</a>
                    </div>
                </li>
                <li><a type="submit" name="operacion" value="" href="#">Copias de seguridad</a></li>
                <li><a type="submit" name="operacion" value="" href="#">Añadir categoría</a></li>
                <li><a type="submit" name="operacion" value="salir" href="index.jsp">Salir</a></li>
            </ul>
        </form>


        <!--FOOTER-->
        <%@include file="../includes/footer_1.jsp" %>
    </body>
</html>
