
//mostrar folularios y informacion de las flores en BD
function floresFormulario() {
    $.ajax({
        url: "ControladorFlores",
        data: {
            intrucion: "mostarFormularioFlores"
        },
        success: function (result) {
            $("#window").html(result);
            floresDeLaBD();
        }
    });
}
//mostrar folularios y informacion de las fincas en BD
function fincasFormulario() {
    $.ajax({
        url: "ControladorFlores",
        data: {
            intrucion: "mostarFormularioFincas"
        },
        success: function (result) {
            $("#window").html(result);
            fincasDeLaBD(); 
            quitarBotones();
        }
    });
}
//mostrar folularios y informacion de las fincas en BD
function openDashboard() {
    $.ajax({
        url: "ControladorTablaPrincipal",
        data: {
            intrucion: "dashboard"
        },
        success: function (result) {
            $("#window").html(result); 
            quitarBotones();
            sd();
            openDashboardTablesEstimations();
            //despliegeSelection();
        }
    });
}
//mostrar folularios Clasificaciones y calibres
function calibreClasificaciones() {
    $.ajax({
        url: "ControladorFlores",
        data: {
            intrucion: "mostarFormularioCalibreClasificaciones"
        },
        success: function (result) {
            $("#window").html(result);
        }
    });
}
//formulario códiogos 
function formularioCodigos() {
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "mostarFormularioCodigos"
        },
        success: function (result) {
            $("#window").html(result);
            codigosDeLaBD(); 
            codigosDeLaBDGuardados();
            quitarBotones();
        }
    });
}
//formulario calibres
function formularioCalibres() {
    $.ajax({
        url: "controladorCalibres",
        data: {
            intrucion: "formularioCalibres"
        },
        success: function (result) {
            $("#window").html(result);
            calibresDeLaBD();
        }
    });
}
//mostrar folularios Siembra
function siembraFormulario() {
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "mostarFormularioSiembra"
        },
        success: function (result) {
            $("#window").html(result);
            selectFincas();
            ingresarSelect();
            botonesMenuSiembra();
            
        }
    });
}
//botones menu siembra
function botonesMenuSiembra(){
 
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "botonesMenuSiembra"

        },
        success: function (result) {
            $("#menuSiembraM").html(result);
        }
    });
}
//mostrar formularios de proveedores y usuarios 
function formularioProveedores() {
    $.ajax({
        url: "controladorProveedores",
        data: {
            intrucion: "formularioProveedores"
        },
        success: function (result) {
            $("#window").html(result); 
            proveedoresDeLaBD(); 
            quitarBotones();
        }
    });
}
//mostrar formulario ususuarios 
function formularioUsuarios() {
    $.ajax({
        url: "controladorUsuarios",
        data: {
            intrucion: "formularioUsuarios"
        },
        success: function (result) {
            $("#window").html(result); 
            usuariosDeLaBD(); 
            quitarBotones();
        }
    });
}
//mostrar folularios Corte
function corteFormulario(id) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "mostarFormularioCorte"
        },
        success: function (result) {
            $("#window").html(result);
            if(id > 0){
                buscarLoteCorteForm(id);
            }
        }
    });
}
//buacar lote form corte 
function buscarLoteCorteForm(id){
    $("#codigoLote").val(id);
    buscarLoteCorte();
}
//agregar calendar panel 
function addPanelCalendar() {
    document.querySelector('.add-calendar').classList.add('active');
}
