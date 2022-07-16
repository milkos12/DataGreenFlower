//mostrar variedades en la tabla 
function fincasDeLaBD() {
    $.ajax({
        url: "controladorFincas",
        data: {
            intrucion: "mostraFincasDeLaBD"
        },
        success: function (result) {
            $("#contenedorTablafincas").html(result);
        }
    });
}
//agregar degradodo en la base de datos 
function agregarFincaEnLaBD() {
    var nombreFinca = $('#nombreFinca').val();
    var extencion = $('#extencionFinca').val();
    $.ajax({
        url: "controladorFincas",
        data: {
            intrucion: "agregarFincaBD",
            finca: nombreFinca,
            extencion: extencion
            
        },
        success: function (result) {
            $("#respuestaAgregarFincas").html(result);
            fincasDeLaBD();
        }
   });
}

// editar variedades en la BD
function editarFincasEnLaBD() {
   
    var id = $('.EditarFinca:checked').val();
    var nombreEditado = $('#nombreEditadoFinca'+id).val();
    var extension = $('#extensionEditadoFinca'+id).val();
    var parametro = $('#parametro4').val();

    $.ajax({
        url: "controladorFincas",
        data: {
            intrucion: "editraBD",
            id: id,
            nombreActualizado: nombreEditado,
            extension: extension,
            parametroEditar:parametro 
            
        },
        success: function (result) {
            $("#respuestaAgregarFincas").html(result);
            fincasDeLaBD();
        }
   });
}
//mostrar flores en la tabla 
function floresDeLaBDFinca() {
    $.ajax({
        url: "controladorFincas",
        data: {
            intrucion: "mostarFloresDeBdFincas",
        },
        success: function (result) {
            $("#floresSm").html(result);
        }
    });
}
//mostrar variedades en la tabla 
function varidadesDeLaBDFinca() {
    var idFlor = $('.varidadesFlor:checked').val();     
    var idFinca = $('.fincasSemanas:checked').val();
    $.ajax({
        url: "controladorFincas",
        data: {
            intrucion: "mostarVariedadesDeBdFincas",
            flor : idFlor,
            finca: idFinca
        },
        success: function (result) {
            $("#variedadesSm").html(result);
        }
    });
}
//actulaizar y/o agregar semanas 
function AgregarSemanaAvaridadesDeLaBDFinca() {
    var idVariedad = $('.anadirSemanas:checked').val();     
    var idFinca = $('.fincasSemanas:checked').val();
    var semanasVaridad = $('#semasPorVaridad'+idVariedad).val();
    $.ajax({
        url: "controladorFincas",
        data: {
            intrucion: "modificarSemanas",
            variedad : idVariedad,
            finca: idFinca,
            semanas : semanasVaridad
        },
        success: function (result) {
            $("#respuestaSemanas").html(result);
        }
    });
}
