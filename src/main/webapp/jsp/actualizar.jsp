<%-- 
    Document   : actualizar
    Created on : 30 dic. 2018, 18:25:27
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" content="text/css" href="css/estilos.css">
        <title>Bienvenida-QuickBid</title>
    </head>
    <body>
        <form action="UsuariosYClientes" method="post">
            <ul>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Inicio"/></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Subir puja" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Pujas" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Actualizar datos" /></li>
                <li><input class="menuAdmin" type="submit" name="operacion" value="Salir" /></li>
                <img class="img-avatar" src="img/avatar/${cliente.avatar}" width="50px" height="auto" style="float: right; margin-right: 10px;"/>
                <h1 style="color: white; font-size: 30px; float: right; margin-right: 10px;">Hola <c:out value = "${sessionScope.usuarioLogeado.cliente.nombre}"/> <c:out value = "${sessionScope.usuarioLogeado.cliente.apellido1}"/></h1>
            </ul>
        </form>



        <form action="UsuariosYClientes" method="post" enctyte="multipart/form-data">
            <c:set var="actualizar" value="${usuarioLogeado}"/>
            <h1>Modifica tus datos</h1>
            <input type="hidden"  value="${actualizar.idUsuario}" name="idUsuario" maxlength=""/>
            <input type="hidden"  value="${actualizar.cliente.idCliente}" name="idCliente" maxlength=""/>

            <table>
                <tr>
                    <td><label>Cambia el nombre:</label></td>
                    <td><input type="text"  value="${actualizar.cliente.nombre}" name="nombreUser" maxlength=""/></td>
                </tr>
                <tr>
                    <td><label>Cambia el primer apellido:</label></td>
                    <td><input type="text"  value="${actualizar.cliente.apellido1}" name="apellido1User"  maxlength=""/></td>
                </tr>
                <tr>
                    <td><label>Cambia el segundo apellido:</label></td>
                    <td><input type="text"  value="${actualizar.cliente.apellido2}" name="apellido2User"  maxlength=""/></td>
                </tr>
                <tr>
                    <td><label>Cambia la dirección: </label></td>
                    <td><input type="text"  value="${actualizar.cliente.direccion}" name="direccionUser"  maxlength=""/></td>
                </tr>
                <tr>
                    <td><label>Cambia el telefono </label></td>
                    <td><input type="number"  value="${actualizar.cliente.telefono}" name="telefonoUser"  maxlength=""/></td>
                </tr>

                <tr>
                    <td><label>Cambia el email: </label></td>
                    <td><input type="text"  value="${actualizar.email}" name="emailUser"  maxlength=""/></td>
                </tr>

                <tr>
                    <td><label>Cambia la imagen: <small>(100kb max)</small>:</label></td>
                    <td><input type="file" name="file" accept="image/png"/></td><br>

                </tr>
            </table>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>
            <button id="insertar" type="submit" value="guardarCambios" name="operacion">Guardar cambios</button>
        </form>

        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
