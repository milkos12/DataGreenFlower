
//agregar flor a la base de datos
function agregarFlor() {
    var nombreFlor = $("#nombreFlor").val();
    var parametro = $("#parametro2").val();
    $.ajax({
        url: "controladorEspecies",
        data: {
            intrucion: "agregarBD",
            nombre: nombreFlor, 
            parametroAgregar: parametro
        },
        success: function (result) {
            $("#respuestaAgregarFlor").html(result);
        }
    });
}
//mostrar flores en la tabla 
function floresDeLaBD() {
    $.ajax({
        url: "controladorEspecies",
        data: {
            intrucion: "mostarFloresDeBd",
        },
        success: function (result) {
            $("#contenedorTablaflores").html(result);
        }
    });
}
// editar flores en la BD
function editarFloresEnLaBD() {
   
    var id = $('.EditarFlor:checked').val();
    var nombreEditado = $('#NombreDeFlorEditado'+id).val();
    var parametro = $('#parametro2').val();

    $.ajax({
        url: "controladorEspecies",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestaAgregarFlor").html(result);
        }
   });
}

//mostrar formulario para agregar variedades
function formularioAgregarVariedad() {
    $.ajax({
        url: "controladorVariedades",
        data: {
            intrucion: "formularioAgreagarVariedad",
        },
        success: function (result) {
            $("#variedad").html(result);
            variedadesDeLaBD();
        }
    });
}
//mostrar variedades en la tabla 
function variedadesDeLaBD() {
    var idFlor = $('.varidadesFlor:checked').val();
    $.ajax({
        url: "controladorVariedades",
        data: {
            intrucion: "mostraVariedadesDeLaBD",
            flor: idFlor
        },
        success: function (result) {
            $("#contenedorTablaVariedades").html(result);
        }
    });
}
//agregar variedades en la base de datos 
function agregarVaridadEnLaBD() {
    var idFlor = $('#idFLorAgregarVariedad').val();
    var nombreVaridad = $('#nombreVariedad').val();
    var parametro = $('#parametro3').val();
    $.ajax({
        url: "controladorVariedades",
        data: {
            intrucion: "agregarBD",
            color: idFlor,
            nombre: nombreVaridad, 
            parametroAgregar: parametro
        },
        success: function (result) {
            $("#respuestaAgregarVariedades").html(result);
        }
   });
}
// editar variedades en la BD
function editarVaridadesEnLaBD() {
   
    var id = $('.editarVariedad:checked').val();
    var nombreEditado = $('#nombreEditadoVaridad'+id).val();
    var parametro = $('#parametro3').val();

    $.ajax({
        url: "controladorVariedades",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestaAgregarVariedades").html(result);
        }
   });
}

