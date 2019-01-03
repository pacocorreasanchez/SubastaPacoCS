<%-- 
    Document   : bloquearUnUsuario
    Created on : 3 ene. 2019, 14:07:36
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" content="text/css" href="../css/estilos.css">
        <link rel="stylesheet" content="text/css" href="../css/estilosAdministrador.css">
        <title>Bloquear usuario</title>
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
        
        <form action="AccionesAdministrador" method="post">
            <fieldset style="padding-left: 20px;">
                <legend>Introduce el mail del usuario que deseas bloquear:</legend>
                <label >Email:</label>
                <input type="text" name="mailBlo" />
                <br>
            </fieldset>
            <br>
            <button type="submit" value="bloquearUser" name="operacion">Bloquear</button>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>
        </form>
        
        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
