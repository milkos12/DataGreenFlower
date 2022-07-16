
//mostrar variedades en la tabla 
function clasificacionesDeLaBD() {
    $.ajax({
        url: "controladorClasificaciones",
        data: {
            intrucion: "mostraClasificacionesDeLaBD"
        },
        success: function (result) {
            $("#contenedorTablaClasificaciones").html(result);
        }
    });
}

//mostrar CALIBRES en la tabla 
function calibresDeLaBD() {
    $.ajax({
        url: "controladorCalibres",
        data: {
            intrucion: "mostarCalibresDeBD",
        },
        success: function (result) {
            $("#contenedorTablaClasifiaciones").html(result);
        }
    });
}
//agregar flor a la base de datos
function agregarClasificacion() {
    var nombreClasificacion = $("#nombreClasificacion").val();
    var parametro = $("#parametro7").val();
    $.ajax({
        url: "controladorClasificaciones",
        data: {
            intrucion: "agregarBD",
            nombre: nombreClasificacion, 
            parametroAgregar: parametro
        },
        success: function (result) {
            $("#respuestaClasificaciones").html(result);
        }
    });
}
//agregar flor a la base de datos
function agregarCalibre() {
    var nombreCalibre = $("#calibre").val();
    var parametro = $("#parametro6").val();
    $.ajax({
        url: "controladorCalibres",
        data: {
            intrucion: "agregarBD",
            nombre: nombreCalibre, 
            parametroAgregar: parametro
        },
        success: function (result) {
            $("#respuestasCalibres").html(result);
        }
    });
}
// editar calibre en la BD
function editarCalibreEnLaBD() {
   
    var id = $('.anadirCalibre:checked').val();
    var nombreEditado = $('#nombreEditadoCalibre'+id).val();
    var parametro = $('#parametro6').val();

    $.ajax({
        url: "controladorCalibres",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestasCalibres").html(result);
        }
   });
}
// editar CLASIFICACION en la BD
function editarClasificacionesEnLaBD() {
   
    var id = $('.ditarClasificacion:checked').val();
    var nombreEditado = $('#nombreEditadoClasificacion'+id).val();
    var parametro = $('#parametro7').val();

    $.ajax({
        url: "controladorClasificaciones",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestaClasificaciones").html(result);
        }
   });
}

