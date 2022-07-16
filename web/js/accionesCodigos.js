
//mostrar datos para agregar codigos
function codigosDeLaBD() {
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "mostraCodigosDeLaBD"
        },
        success: function (result) {
            $("#contenedorCodigos").html(result);
        }
    });
}
function codigosDeLaBDGuardados() {
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "mostraCodigosDeLaBDGuardados"
        },
        success: function (result) {
            $("#contenedorTablaCodigos").html(result);
        }
    });
}
//traer varidades 
function codigosVariedadesDeLaBD() {
    var flor = $("#florSeleccionada").val();
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "mostrarVariedadesParaCodigos",
            flor: flor
        },
        success: function (result) {
            $("#varidadesCodigosSelect").html(result);
        }
    });
}
//traer referencias
function codigosReferenciaDeLaBD() {
    var color = $("#coloresSelectCodigos").val();
    var variedad = $("#variedadesListas").val();
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "mostrarReferenciaParaCodigos",
            color: color, 
            variedad: variedad
        },
        success: function (result) {
            $("#referenciasCodigosSelect").html(result);
        } 
    });
}
//codigos referencia editar------------------------
function codigosReferenciaDeLaBDEditar() {
    var color = $("#coloresSelectCodigos").val();
    var variedad = $("#florSeleccionada").val();
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "mostrarReferenciaParaCodigos",
            color: color, 
            variedad: variedad
        },
        success: function (result) {
            $("#referenciasCodigosEditados").html(result);
        }
    });
}
//verificar si un codigo existe
function verificarCodigo() {
    var codigo = $("#codigoNuevo").val();
    $.ajax({
        url: "controladorCodigos",
        data: {
            intrucion: "verificarQElcodigonoExista",
            codigo: codigo
        },
        success: function (result) {
            $("#respuestasCodigos").html(result);
        }
    });
}
//agregar codigos en la bd
function codigosAgregarALaBD() {
    
    try {
        
        var color = $("#coloresSelectCodigos").val();
        var referencia = $("#referenciaListas").val();
        var flor = $("#florSeleccionada").val();
        var variedad = $("#variedadesListas").val();
        var calibre = $("#calibresListas").val();
        var codigo = $("#codigoNuevo").val();


        $.ajax({
            url: "controladorCodigos",
            data: {
                intrucion: "agregarCodigoALaBD",
                color: color,
                referencia: referencia,
                flor: flor,
                variedad: variedad,
                calibre: calibre,
                codigo: codigo
            },
            success: function (result) {
                $("#respuestasCodigos").html(result);
            }
        });
    } catch (exception) {
       
    }
}
//editar codigo 
function editarCodigosEnLaBD() {
        var codigo = $(".codigoEditado:checked").val();
        var codigoEditado = $("#codioEditado"+codigo).val();
        var color = $("#selectColordEditadoCodigo"+codigo).val();
        var referencia = $("#selectReferenciaEditadoCodigo"+codigo).val();
        var flor = $("#selectFlorEditadoCodigo"+codigo).val();
        var variedad = $("#selectVariedadEditadoCodigo"+codigo).val();
        var calibre = $("#selectCalibreEditadoCodigo"+codigo).val();
        console.log("codigo"+codigo+"codigoEditado"+codigoEditado+"color"+color+"referencia"+referencia+"flor"+flor+"variedad"+variedad+"calibre"+calibre);
        $.ajax({
            url: "controladorCodigos",
            data: {
                
                intrucion: "editarCodigoALaBD",
                color: color,
                referencia: referencia,
                flor: flor,
                variedad: variedad,
                calibre: calibre,
                codigo: codigo,
                codigoEditado: codigoEditado
            },
            success: function (result) {
                $("#respuestasCodigos").html(result);
            }
        });   
}