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
        <title>Administrador-Inicio</title>
    </head>
    <body>
        <!--<ul>
            <li><a name="operacion" value="" href="JSPAdministrador/principalAdministrador.jsp">Inicio</a></li>
            <li class="dropdown">
                <a  name="operacion" value="" href="#" class="dropbtn">Usuarios</a>
                <div class="dropdown-content">
                    <a  name="operacion" value="" href="#">Bloquear</a>
                    <a  name="operacion" value="" href="#">Desbloquear</a>
                </div>
            </li>
            <li><a  name="operacion" value="" href="#">Copias de seguridad</a></li>
            <li><a  name="operacion" value="anadirCategoria" href="JSPAdministrador/anadirCategoria.jsp">Añadir categoría</a></li>
            <li><a  name="operacion" value="salir" href="index.jsp">Salir</a></li>
        </ul>-->
        <form action="AccionesAdministrador" method="post">
            <ul>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Inicio"/></li>
                <li class="dropdown">
                    <input class="menuAdmin" type="submit" name="operacion" value="Usuarios" class="dropbtn"/>
                    <div class="dropdown-content">
                        <input class="menuAdmin" type="submit" name="operacion" value="Bloquear" />
                        <input class="menuAdmin" type="submit" name="operacion" value="Desbloquear" />
                    </div>
                </li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Copias de seguridad" /></li>
                <!--<li><input class="menuAdmin" type="submit" name="operacion" value="Añadir categoría" /></li>-->
                <li><input class="menuAdmin" type="submit" name="operacion" value="Salir" /></li>
            </ul>
        </form>


        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
