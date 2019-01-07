<%-- 
    Document   : pujas
    Created on : 3 ene. 2019, 18:07:46
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
        <!--<script>
            $(document).ready(function () {
                $('div.categorias > div > input[id^=articulo-]').on('click', function () {
                    $(this).parent().addClass('modal');
                    $(this).parent().find('.oculta').show();
                });
                $('#btncerrar').on('click', function(){
                    console.log(2);
                   $('div.categorias > div > input[id^=articulo-]').removeClass('.modal');
                });
            });
        </script>-->
        <script>
            $(document).ready(function () {
                $('.sendPuja').on('click', function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                    let idArticulo = $(this).attr('data-idArticulo');
                    let parent = $(this).parent(); 
                    let importe= $(parent).find('[name=precioPujado-'+idArticulo+']').val();
                    $.ajax({
                        url: 'UsuariosYClientes',
                        type: 'POST',
                        data: jQuery.param({operacion:'pujar', idArticulo:idArticulo, precioPujado:importe}),
                        success: function (respuesta) {
                                $('#precioActual').html(respuesta);
                        }});
                    
                });
            });
        </script>
        <title>Pujas-QuickBid</title>
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




        <div class="categorias">
            <c:forEach var="art" items="${articulos}">

                    <input type="hidden" value="${art.idArticulo}" name="idArticuloPujado"/>
                    <div class="div-img" >
                        <img class="img" src="img/arte.jpg" title="Arte" alt="Arte">
                        <input id="articulo-${art.idArticulo}" style="font-size: 15px !important;" type="submit" class="text" value="${art.descripcionCorta}" name="operacion"/>
                        <br>
                        <span class="oculta">Descripción: ${art.descripcion}</span><br>
                        <span class="oculta">Fecha de inicio: ${art.fechaInicio}</span><br>
                        <span class="oculta">Fecha fin de puja: ${art.fechaFin}</span><br>
                        <span class="oculta">Importe de salida: ${art.importeSalida}€</span><br>
                        <span class="oculta" id="precioActual">Precio actual: €</span><br>
                        <label>Introduce una nueva puja: </label><input type="number" name="precioPujado-${art.idArticulo}" placeholder=""/><br>

                        <!--<button class="oculta" name="operacion" value="pujar">Pujar</button><br>-->
                        <button class="sendPuja" data-idArticulo="${art.idArticulo}">Pujar</button><br>
                    </div>
                
            </c:forEach>
        </div>




        <!--FOOTER-->
        <%@include file="../includes/footer.jsp" %>
    </body>
</html>
