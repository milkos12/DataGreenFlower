<%-- 
    Document   : CorteFlores
    Created on : 23-oct-2021, 7:49:53
    Author     : ANDRES
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,Servlet.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/jpg" href="imagenes/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="estilos/estilos.css">
        <link rel="stylesheet" type="text/css" href="estilos/ventana.css">
        <link rel="stylesheet" type="text/css" href="estilos/titleStile.css">
        <title>Inicio</title>
    </head>
    <body onload="mostrarTablaPrincipal();">
        <form id="myForm" action="" method="get">
            <c:url var="linkCerrarSesion" value="ControladorFlores">
                <c:param name="intrucion" value="cerrarSesion"></c:param>  
            </c:url>
            <div class="nav">
                <ul>
                    <li class="list active">
                        <b></b>
                        <b></b>
                        <a href="#" onclick="mostrarTablaPrincipal(); quitarBotones();"><%--LISTSO--%>
                            
                            <spna class="ico"><ion-icon name="grid-outline"></ion-icon></spna>
                            <span class="title">Lotes</span>
                        </a>
                    </li>
                    <li class="list" >
                        <b></b>
                        <b></b>
                        <a href="#" onclick="corteFormulario(); quitarBotones();">
                            <spna class="ico"><ion-icon name="cut-outline"></ion-icon></ion-icon></spna>
                            <span class="title">Corte</span>
                        </a>
                    </li>
                    <li class="list" >
                        <b></b>
                        <b></b>
                        <a href="#" onclick="mostrarTablaPrincipal(); siembraFormulario(); selectFincas(); ingresarSelect(); menuSimbra(); quitarBotones();"><%--LISTSO--%>
                            <spna class="ico"><ion-icon name="leaf-outline"></ion-icon></ion-icon></spna>
                            <span class="title">Siembra</span>
                        </a>
                    </li>
                    <li class="list">
                        <b></b>
                        <b></b>                    
                        <a href="#" onclick="floresFormulario();floresDeLaBD(); quitarBotones();">
                            <spna class="ico"><ion-icon name="flower-outline"></ion-icon></spna>
                            <span class="title">Producto</span>
                        </a>
                    </li>
                    <li class="list">
                        <b></b>
                        <b></b>
                        <a href="#" onclick="fincasFormulario(); fincasDeLaBD(); quitarBotones();">
                            <spna class="ico"><ion-icon name="calendar-outline"></ion-icon></spna>
                            <span class="title">Nueva finca</span>
                        </a>
                    </li> 
                    <li class="list">
                        <b></b>
                        <b></b>
                        <a href="#" onclick="calibreClasificaciones(); clasificacionesDeLaBD(); calibresDeLaBD(); codigosDeLaBD(); codigosDeLaBDGuardados();quitarBotones();">
                            <spna class="ico"><ion-icon name="clipboard-outline"></ion-icon></spna>
                            <span class="title">Clasificaciones y Calibres</span>
                        </a>
                    </li>
                    <li class="list">
                        <b></b>
                        <b></b>
                        <a href="#" onclick="proveedoresUsuarios(); usuariosDeLaBD(); proveedoresDeLaBD(); quitarBotones();">
                            <spna class="ico"><ion-icon name="people-outline"></ion-icon></spna>
                            <span class="title">Proveedores y Usuarios</span>
                        </a>
                    </li>
                    <li class="list" href="${linkCerrarSesion}">
                        <b></b>
                        <b></b>
                        <a href="${linkCerrarSesion}">
                            <spna class="ico"><ion-icon name="log-out-outline"></ion-icon></spna>
                            <span class="title">Salir</span>
                        </a>
                    </li>

                </ul>
            </div>
            <div id="window" class="pantalla">
                <h1>Hola</h1>
            </div>

            <div class="toggle"><ion-icon name="menu-outline" class="open"></ion-icon>
                <ion-icon name="close-outline" class="close"></ion-icon>
            </div>
            <div id="menuSiembraM">
                
            </div>


            <script src="js/jquery-3.6.0.min.js"></script>
            <script src="js/cerrarSesion.js"></script>
            <script src="js/accionesBarraNav.js"></script>
            <script src="js/proveedoresUsuarios.js"></script>
            <script src="js/accionesColoresDegradados.js"></script>
            <script src="js/accionesFloresVariedades.js"></script>
            <script src="js/accionesFincas.js"></script>
            <script src="js/accionesSiembra.js"></script>
            <script src="js/accionesBarraTablaPrincipal.js"></script>
            <script src="js/accionesCodigos.js"></script>
            <script src="js/accionesEditarLote.js"></script>
            <script src="js/accionesCorte.js"></script>
            <script src="js/accionesClasifiacionesCalibres.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
            <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
            <script src="js/barraNav.js"></script>
            <script type="text/javascript">


        </form>
    </body>
</html>
