<%-- 
    Document   : index
    Created on : 8 dic. 2018, 17:23:18
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
        <script src="js/formLogin.js"></script>
        <script src="js/formRegistro.js"></script>
        <script src="js/errores.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <title>Inicio-QuickBid</title>
    </head>
    <body>
        
        <!--ERROR DE ACCESO-->
        <c:set var = "email" value = "${error}"/>
        <c:if test = "${error != null}">

            <div class="formLogin">
                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" style="padding:35px 50px;">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4><span class="glyphicon glyphicon-lock"></span> <c:out value = "${error}"/></h4>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                                <p>¿No eres miembro? <a id="myBtn3" type="button" href="#">Registrate</a></p>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </c:if>
        <c:if test = "${userNoRegistrado != null}">

            <div class="formLogin">
                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" style="padding:35px 50px;">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4><span class="glyphicon glyphicon-lock"></span> <c:out value = "${userNoRegistrado}"/></h4>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                                <p>¿No eres miembro? <a id="myBtn3" type="button" href="#">Registrate</a></p>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </c:if>
        
        
        <!--ACCESO CORRECTO-->
        <c:set var = "email" value = "${registroCorrecto}"/>
        <c:if test = "${registroCorrecto != null}">

            <div class="formLogin">
                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" style="padding:35px 50px;">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4><span class="glyphicon glyphicon-lock"></span> <c:out value = "${registroCorrecto}"/></h4>
                            </div>
                            <div class="modal-footer">
                                <button style="background-color: #01CCA9" type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span ></span> Aceptar</button>
                                
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </c:if>

        <div class="header">
            <a href="#" class="logo"><img id="logoQB" src="img/logosinfondo.png" /></a>

            <div class="header-right">
                <a type="button" id="myBtn1" class="nonactive" href="#">Accede</a>
                <a type="button" id="myBtn2" class="active" href="#">Registro</a>
            </div>
        </div>

        
        <div class="categorias">
                <c:forEach var="c" items="${applicationScope.categorias}">
                    <div class="div-img" >
                        <img class="img" src="img/imagen-${c.idCategoria}.jpg" title="img/imagen-${c.idCategoria}" alt="img/imagen-${c.idCategoria}">
                        <input type="submit" class="text" value="${c.denominacion}" />
                    </div>
                </c:forEach>
            </div>

        <!--FORMULARIO LOGIN-->
        <div class="formLogin">

            <!-- Modal -->
            <div class="modal fade" id="myModal1" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" style="padding:35px 50px;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4><span class="glyphicon glyphicon-lock"></span> Accede</h4>
                        </div>
                        <div class="modal-body" style="padding:40px 50px;">
                            <form role="form" action="RedireccionUsuarios" method="post">
                                <div class="form-group">
                                    <label for="usrname"><span class="glyphicon glyphicon-user"></span> Usuario</label>
                                    <input type="text" name="email" class="form-control" id="usrname" placeholder="Introduce email">
                                </div>
                                <div class="form-group">
                                    <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Contraseña</label>
                                    <input type="password" name="contrasenia" class="form-control" id="psw" placeholder="Introduce contraseña">
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" value="" checked>Recuerdame</label>
                                </div>
                                <button name="operacion" value="accede" type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Accede</button>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                            <p>¿No eres miembro? <a id="myBtn3" type="button" href="#">Registrate</a></p>
                        </div>
                    </div>
                </div>
            </div> 
        </div>





        <!--FORMULARIO REGRISTRO-->
        <div class="formLogin">

            <!-- Modal -->
            <div class="modal fade" id="myModal2" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" style="padding:35px 50px;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4><span class="glyphicon glyphicon-lock"></span> Registro</h4>
                        </div>
                        <div class="modal-body" style="padding:40px 50px;">
                            <form action="UsuariosYClientes" method="post" role="form">
                                <div class="form-group">
                                    <label for="nombre"></span> *Nombre</label>
                                    <input type="text" name="nombreRegistro" class="form-control" id="nombre" placeholder="nombre">
                                </div>
                                <div class="form-group">
                                    <label for="ape1"></span> *Primer apellido</label>
                                    <input type="text" name="ape1Registro" class="form-control" id="ape1" placeholder="apellido 1">
                                </div>
                                <div class="form-group">
                                    <label for="ape2"></span> *Segundo apellido</label>
                                    <input type="text" name="ape2Registro" class="form-control" id="ap2" placeholder="apellido 2">
                                </div>
                                <div class="form-group">
                                    <label for="email"></span> *Email</label>
                                    <input type="text" name="emailRegistro" class="form-control" id="email" placeholder="email">
                                </div>
                                <div class="form-group">
                                    <label for="psw"></span> *Contraseña</label>
                                    <input type="password" name="contraseniaResgistro" class="form-control" id="psw" placeholder="contraseña">
                                </div>
                                <div class="form-group">
                                    <label for=""></span> *NIF</label>
                                    <input type="text" name="nif" class="form-control"  placeholder="nif">
                                </div>
                                <div class="form-group">
                                    <label for=""></span> *Dirección</label>
                                    <input type="text" name="direccion" class="form-control"  placeholder="dirección">
                                </div>
                                <div class="form-group">
                                    <label for=""></span> Teléfono</label>
                                    <input type="number" name="telefono" class="form-control"  placeholder="teléfono">
                                </div>
                                <button type="submit" name="operacion" value="registrar" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Registrar</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                            <p>¿Tienes cuenta? <a id="myBtn4" type="button" href="#">Accede</a></p>
                        </div>
                    </div>
                </div>
            </div> 
        </div>

        <!--FOOTER-->
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
