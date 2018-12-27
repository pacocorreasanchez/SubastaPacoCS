<%-- 
    Document   : anadirCategoria
    Created on : 26 dic. 2018, 16:19:07
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
        <title>Añadir categoríra</title>
    </head>
    <body>
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
                <li><input class="menuAdmin" type="submit" name="operacion" value="Añadir categoría" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Salir" /></li>
            </ul>
        </form>

        <h1>Añade una nueva categoría</h1>
        <form action="AccionesAdministrador" method="post">
            <fieldset style="padding-left: 20px;">
                <legend>Datos del artículo</legend>
                <label for="deno">Denominación:</label>
                <input type="text" id="deno" name="denominacion" required="required" class="form-control"/>
                <br>
                <label for="ft">Fotos (png y como máximo 100 KB): </label>
                <input id="ft" type="file" name="fotos[]" multiple  class="form-control">
            </fieldset>
            <br>
            <button type="submit" value="realizar" name="operacion">Añadir</button>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>


        </form>


        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
