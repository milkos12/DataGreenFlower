<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio de Sesión</title>
        <meta charset="utf-8">
        <meta Http-Equiv="Cache-Control" Content="no-cache">
        <meta Http-Equiv="Pragma" Content="no-cache">
        <meta Http-Equiv="Expires" Content="0">
        <link rel="stylesheet" type="text/css" href="estilos/Estilos_.css">
        <link rel="shortcut icon" type="image/jpg" href="imagenes/favicon.ico"/>
        <script type = "text/javascript" >
            function changeHashOnLoad() {
                window.location.href += "#";
                setTimeout("changeHashAgain()", "50");
            }

            function changeHashAgain() {
                window.location.href += "1";
            }

            var storedHash = window.location.hash;
            window.setInterval(function () {
                if (wi ndow.location.hash != storedHash) {
                    window.location.hash = storedHash;
                }
            }, 50);


        </script>
        <!-- <m eta http-equiv="refresh" content="1">-->

    </head>
    <body background="imagenes/bagraun.png" onload="changeHashOnLoad();">


        <form id="menu" method="post" action="controladorLogin"  name="inicio">

            <div id="inicio_superior">
                <!--<a href="">Misión y Visión</a>-->
            </div>
            <div id="ingreso">
                <h2>Acceso</h2>


                <input type="number" id="input" name="usuario" placeholder="Usuario" required="">
                <img src="imagenes/candado.png" class="icon_olvide">
                <input type="password"class="contraseña" id="input" name="contra" placeholder="Contraseña" required="">
                <input type="hidden" name="instruccion" value="listar">
                <input type="submit"  id="boton_inicio" value="Entrar">
                <c:set var="Estado" value="${Estado}"></c:set>
                <c:if test="${Estado == 0}">
                        <h6 id="mesaje">Tu contraseña o usuario son incorrectos</h6>
                </c:if>

                <a id="olvide" href="#">¿Olvidaste tu contraseña? Pídele al administrador que la restablezca</a>
            </div>
            <img id="imagen_fondo" src="imagenes/plantas_fondo.jpg">

        </form>

</html>