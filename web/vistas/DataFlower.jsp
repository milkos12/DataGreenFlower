<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,Servlet.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script><!-- comment -->
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" type="text/css" href="estilos/style.css">
        <link rel="stylesheet" type="text/css" href="estilos/stylesBoxInfo.css">
        <link rel="stylesheet" type="text/css" href="estilos/titleStile.css">
        <link rel="shortcut icon" type="image/jpg" href="imagenes/favicon.ico"/>
        <title>DataGreenFlower</title>
    </head>
    <body onload="mostrarTablaPrincipal();" onsubmit="return false;">

        <form id="myForm" action="" method="get">
            <div class="page-wrapper">
                <input type="checkbox" name="" checked id="menu-toggle">
                <div class="sidebar">
                    <div class="brand">
                        <h3>
                            <span class="las la-spa"></span>
                            DataGreen
                        </h3>

                    </div>
                    <div class="profile-card">
                        <div class="profile-img"></div>
                        <div class="profile-info">
                            <h2>Gestión del sistema</h2>
                            <small>panel</small>
                        </div>
                        <div class="profile-action">
                            <a href="" class="btn btn-block btn-white">
                                <span class="las la-home"></span>
                                Inicio
                            </a>
                        </div>
                        <div class="profile-icons">
                            <span class="las la-user" onclick="formularioUsuarios();" style="cursor: pointer"></span>
                            <span class="las la-sign-out-alt"></span>
                            <span class="las la-leaf" onclick="floresFormulario(); quitarBotones();" style="cursor: pointer"></span>
                        </div>
                    </div>
                    <div class="sidebar-menu">

                        <div class="menu-item">
                            <a href="#" onclick="formularioProveedores();">
                                <span class="las la-users" ></span>
                                <span>Proveedores</span>
                            </a>
                        </div>

                        <div class="menu-item">
                            <a href="#" onclick="formularioCodigos();">
                                <span class="las la-sliders-h"></span>
                                <span>Códigos</span>
                            </a>
                        </div>

                        <div class="menu-item">
                            <a href="#">
                                <span class="las la-chart-bar"></span>
                                <span>Dashboard</span>
                            </a>
                        </div>

                        <div class="menu-item">
                            <a href="#" onclick="fincasFormulario();">
                                <span class="las la-tree"></span>
                                <span>Fincas</span>
                            </a>
                        </div>
                        <div class="menu-item">
                            <a href="#" onclick="formularioCalibres();">
                                <span class="las la-ruler-combined"></span>
                                <span>Calibres</span>
                            </a>
                        </div>
                    </div>

                    <div class="sidebar-card">
                        <h2>¡Advertencia!</h2>
                        <p>Aún seguimos trabajando en las funciones y es posible que se presenten errores</p>
                        <a href="#" class="btn btn-block btn-white"><span class="las la-exclamation-circle"></span> </a>
                    </div>
                </div>
                <div class="main-content">
                    <header>
                        <label for="menu-toggle">
                            <span class="las la-bars" style="cursor: pointer"></span>
                        </label>

                        <div class="header-icons">
                            <span onclick="mostrarTablaPrincipal(); quitarBotones();" style="cursor: pointer"><ion-icon name="home-outline"></ion-icon></span>
                            <span onclick="corteFormulario(); quitarBotones();" style="cursor: pointer"><ion-icon name="cut-outline"></ion-icon></span>
                            <span onclick="siembraFormulario();" style="cursor: pointer"><ion-icon name="leaf-outline"></ion-icon></span>
                            <span onclick="openDashboard();" style="cursor: pointer"><ion-icon name="pie-chart-outline"></ion-icon></span>
                            <span onclick="addPanelCalendar();" style="cursor: pointer"><ion-icon name="calendar-number-outline"></ion-icon></span>

                        </div>
                    </header>
                    <main>

                        <div class="grid-2" id="window">

                        </div>
                        <div id="menuSiembraM"></div>
                    </main>
                </div>

            </div>
            <div class="cookies-box">

                <span class="close-btn">&times;</span>

                <h3>Ups...</h3>

                <p>El saldo disponible para este cotenido se agoto</p>

            </div>
            <%--
            etos div son para poder agregar datos basicos a la base de datos 
            --%>
            <%--add colores--%>
            <div class="add-colors">

                <span class="close-btn" onclick="remove(1)">&times;</span>

                <h3>Agregar color</h3>
                <h2>🎨</h2>
                <h5>⚠Verifique antes de ingresar que el color no exista .</h5>
                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <small id="respuestaAgregarColor">Respuesta</small>
                        </div>
                    </div>
                </div>
                <input class='input_text_agregar' type='text' placeholder='Nombre del color' id='nombreColor'>
                <input class="button-agregar" type='button' value='Agregar' onclick='agregarColor(); ColoresDeLaBD();'>

            </div>
            <%--add referencia--%>
            <div class="add-referencia">

                <span class="close-btn" onclick="remove(2)">&times;</span>

                <h3>Referencia </h3>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h2>⚠</h2>
                            <h5>⚠️Tenga en cuenta que esta referencia se va a guardar con base a los datos seleccionados en la ultima fila, los datos serán los siguientes: </h5>
                            <h5 id="description_add_referencia"></h5>
                            <small id="respuestaAgregarDegradado">Respuesta</small>
                        </div>
                    </div>
                </div>
                <input class='input_text_agregar' type='text' placeholder='Nombre del la referencia' id='nombreDegradado'>
                <input class="button-agregar" type='button' value='Agregar' onclick='warningReferencia()'>

            </div>
            <%--add viedada--%>
            <div class="add-variedad">

                <span class="close-btn" onclick="remove(3)">&times;</span>

                <h3>Variedad </h3>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h2>⚠</h2>
                            <h5>⚠️Tenga en cuenta que esta variedad se va a guardar con base a los datos seleccionados en la ultima fila, los datos serán los siguientes: </h5>
                            <h5 id="description_add_variedad"></h5>
                            <small id="respuestaAgregarVariedades">Respuesta</small>
                        </div>
                    </div>
                </div>
                <input class='input_text_agregar' type='text' placeholder='Nombre del la variedad' id='nombreVariedad'>
                <input class="button-agregar" type='button' value='Agregar' onclick='warningVariedad()'>

            </div>
            <%--add flor--%>
            <div class="add-flor">

                <span class="close-btn" onclick="remove(4)">&times;</span>

                <h3>Flor </h3>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h2>🌺</h2>
                            <h5>⚠️Tenga en cuenta digitar bien el nombre de la nueva flor para poder hacer una validación más acertada  : </h5>

                            <small id="respuestaAgregarFlor">Respuesta</small>
                        </div>
                    </div>
                </div>
                <input class='input_text_agregar' type='text' placeholder='Nombre del la flor' id='nombreFlor'>
                <input class="button-agregar" type='button' value='Agregar' onclick='warningFlor()'>

            </div>
            <%--add Proveedor--%>
            <div class="add-proveedor">

                <span class="close-btn" onclick="remove(5)">&times;</span>

                <h3>Proveedor</h3>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h2>👩‍?</h2>
                            <h5>⚠️Tenga en cuenta digitar bien el nombre del nuevo proveedor para poder hacer una validación más acertada  : </h5>

                            <small id="respuestaPoveedores">Respuesta</small>
                        </div>
                    </div>
                </div>
                <small>Requerido*</small>
                <input class='input_text_agregar' type='text' placeholder='Nombre del proveedor' id='nombreProveedor'>
                <small>Requerido*</small>
                <input class='input_text_agregar' type='text' placeholder='Celular del proveedor' id='celularProveedor'>
                <br><small>_Opcional_</small><br>
                <input class='input_text_agregar' type='text' placeholder='Documento del proveedor' id='documentoProveedor'>
                <input class="button-agregar" type='button' value='Agregar' onclick='warningProveedor()'>

            </div>
            <%--add flor--%>
            <div class="add-calibre">

                <span class="close-btn" onclick="remove(6)">&times;</span>

                <h3>Calibre</h3>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h2>📐</h2>
                            <h5>⚠️Tenga en cuenta digitar bien el nuveo calibre para poder hacer una validación más acertada  : </h5>

                            <small id="respuestasCalibres">Respuesta</small>
                        </div>
                    </div>
                </div>
                <input class='input_text_agregar' type='text' placeholder='Nombre del proveedor' id='calibre' value='0/0'>

                <input class="button-agregar" type='button' value='Agregar' onclick='warningCalibre()'>

            </div>
            <%--add Calendar--%>
            <div class="add-calendar">

                <span class="close-btn" onclick="remove(7)">&times;</span>

                <h3>Calendar</h3>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h1><ion-icon name="logo-google"></ion-icon></h1>
                            <h6 style="color:red">⚠Estamos trabajando (No disponible).⚠</h6>
                            <h5>📅 Hemos integrado Google Calendar para el manejo de cronogramas por finca: "Para integrar el calendario correspondiente a Google Calendar seleccione el nombre de la finca y luego será redireccionado a la página Calendar y deberá oprimir añadir para vincular el calendario y recibir notificaciones programadas por el sistema." </h5>

                            <small id="">URL</small>
                        </div>
                    </div>
                </div>
                <p><a href="https://calendar.google.com/calendar/u/0?cid=ZGVla2xyNGRlbXF1cTF2MjVxZGQ1amo3MG9AZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ" target="_blank" rel="noopener noreferrer">FINCA JYM</a>.</p>
                <p><a href="https://calendar.google.com/calendar/u/0?cid=Z3ZtMnVoMWwxbGRlcTljNnYzaTE2aHE0ZmdAZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ" target="_blank" rel="noopener noreferrer">VILLA ANA</a>.</p>
                <p><a href="https://calendar.google.com/calendar/u/0?cid=MG5iZjJmdW4yOXFobjZuamdmbnExdnYyazBAZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ" target="_blank" rel="noopener noreferrer">SAN ISIDRO</a>.</p>
                <p><a href="https://calendar.google.com/calendar/u/0?cid=Z3ZtMnVoMWwxbGRlcTljNnYzaTE2aHE0ZmdAZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ" target="_blank" rel="noopener noreferrer">LOS CRISTALES</a>.</p>
            </div>
            <%--details--%>
            <div class="add-details">

                <span class="close-btn" onclick="remove(8)">&times;</span>

                <div class="card-head">
                    <div class="team-head">
                        <div>
                            <h2 id="title_week_deatils">Detalle</h2>
                            <h5></h5>

                            <small id="detail_week">Respuesta</small>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <canvas id="myChart" width="4" height="4"></canvas>
                </div>

            </div>
            <%--
            ------------------------------- fin ------------------------------------------
            --%>
            <button class="open-btn" style="display: none;" id="activadorVentanaCorte">show popup</button>
            <div id="alert"></div>

        </form>

        <script type="text/javascript">


            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.cookies-box').classList.remove('active');
            };
            //panel colores
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-colors').classList.remove('active');
            };
            //panel referencias
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-referencia').classList.remove('active');
            };
            //panel variedad 
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-variedad').classList.remove('active');
            };
            //panel flor  
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-flor').classList.remove('active');
            };
            //panel proveedor
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-proveedor').classList.remove('active');
            };
            //panel calibre
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-calibre').classList.remove('active');
            };
            //panel calibre
            document.querySelector('.close-btn').onclick = () => {
                document.querySelector('.add-calendar').classList.remove('active');
            };
        </script>

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
        <script src="js/boxInfoLote.js"></script>
        <script src="js/chartsBoard.js"></script>
        <script src="js/mainInfoDash.js"></script>
        <script src="js/accionesDashBoard.js"></script>
        <script src="js/accionesClasifiacionesCalibres.js"></script>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script type="module" src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>

    </body>
</html>