<%-- 
    Document   : index
    Created on : 8 dic. 2018, 17:23:18
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" content="text/css" href="css/estilos.css">
        <title>Inicio-QuickBid</title>
    </head>
    <body>
        
        <div class="header">
            <a href="#" class="logo"><img id="logoQB" src="img/logosinfondo.png" /></a>
            <div class="header-right">
                <a class="nonactive" href="#">Login</a>
                <a class="active" href="#">Registro</a>
            </div>
        </div>
        
        <div class="categorias">
            <div>ARTE</div>
            <div>EMBARCACIONES</div>
            <div>VEHICULOS</div>
        </div>
        
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
