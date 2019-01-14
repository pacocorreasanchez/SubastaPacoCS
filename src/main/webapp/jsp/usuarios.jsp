<%-- 
    Document   : usuarios
    Created on : 25 dic. 2018, 17:51:27
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

            <c:if test = "${pujasubida != null}">
                <h1>${pujasubida}</h1>
            </c:if>


            <div class="categorias">
                <c:forEach var="c" items="${applicationScope.categorias}">
                    <div class="div-img" >
                        <img class="img" src="img/imagen-${c.idCategoria}.jpg" title="img/imagen-${c.idCategoria}" alt="img/imagen-${c.idCategoria}">
                        <input style="font-size: 15px !important;" type="submit" class="text" value="${c.denominacion}" name="redireccionCategorias"/>
                    </div>
                </c:forEach>
            </div>
        </form>



        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
