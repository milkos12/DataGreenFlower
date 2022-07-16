//mostrar folularios y informacion de los colores en BD
function coloresFormulario() {
    $.ajax({
        url: "controladoColores",
        data: {
            intrucion: "agregarColores"
        },
        success: function (result) {
            $("#contenerAccionesColores").html(result);
            ColoresDeLaBD();
        }
    });
}
//mostrar colores en la tabla 
function ColoresDeLaBD() {
    $.ajax({
        url: "controladoColores",
        data: {
            intrucion: "mostraColoreDeLaBD",
        },
        success: function (result) {
            $("#contenedorTablaColores").html(result);
        }
    });
}
//agregar color a la base de datos
function agregarColor() {
    var nombreColor = $("#nombreColor").val();
    var parametro = $("#parametro5").val();
    $.ajax({
        url: "controladoColores",
        data: {
            intrucion: "agregarBD",
            nombre: nombreColor, 
            parametroAgregar: parametro
        },
        success: function (result) {
            $("#respuestaAgregarColor").html(result);
            try{
                refreshColors();
            }catch (e){
                
            }
        }
    });
}
//editar COLORES
function editarColorEnLaBD() {
    var id = $('.EditarColor:checked').val();
    var nombreEditado = $('#nombreEditadoColor'+id).val();
    var parametro = $('#parametro5').val();

    $.ajax({
        url: "controladoColores",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestaAgregarColor").html(result);
        }
   });    
}

//mostrar formulario agregar degradados
function formularioAgregarDegradado() {
    $.ajax({
        url: "controladorReferencia",
        data: {
            intrucion: "formularioAgreagarDegradado",
        },
        success: function (result) {
            $("#degradado").html(result);
            degradadosDeLaBD();
        }
    });
}

//mostrar degradados en la tabla 
function degradadosDeLaBD() {
    var idVariedad = $('.coloresVariedad:checked').val();
    var idColor = $('.degradadoCol:checked').val();
    $.ajax({
        url: "controladorReferencia",
        data: {
            intrucion: "mostraDegradadosDeLaBD",
            variedad: idVariedad,
            color: idColor
        },
        success: function (result) {
            $("#contenedorTablaDegradado").html(result);
        }
    });
}
//agregar degradodo en la base de datos 
function agregarDegradadosDeLaBD() {
    var idVariedad = $('.coloresVariedad:checked').val();
    var idColor = $('.degradadoCol:checked').val();
    var nombreDegradado = $('#nombreDegradado').val();
    var parametro = $('#parametro1').val();
    $.ajax({
        url: "controladorReferencia",
        data: {
            intrucion: "agregarBD",
            color: idColor,
            nombre: nombreDegradado, 
            variedad: idVariedad,
            parametroAgregar: parametro
            
        },
        success: function (result) {
            $("#respuestaAgregarDegradado").html(result);
        }
   });
}
//editar DEGRADADOS
function editarDegradadoEnLaBD() {
   
    var id = $('.editarDegradado:checked').val();
    var nombreEditado = $('#idEditar'+id).val();
    var parametro = $('#parametro1').val();

    $.ajax({
        url: "controladorReferencia",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestaAgregarDegradado").html(result);
        }
   });
}
