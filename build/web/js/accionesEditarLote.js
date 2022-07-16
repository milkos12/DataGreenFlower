//fecha del lote q se va editar 
function fechaEditarLote(lote) {
    var lote = lote;
    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "fechaDelLoteAEditar",
            lote: lote
        },
        success: function (result) {
            document.getElementById("fecha").value = result;
            fincaEditarLote(lote);
            calcularSemana();

        }
    });
}
//codigo de finca de lote a editar 
function fincaEditarLote(lote) {
    var lote = lote;
    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "ficaDelLoteAEditar",
            lote: lote
        },
        success: function (result) {
            document.getElementById("codigoFinca").value = result;
            selectFincas();
            remisionEditarLote(lote);
            contenidoEditarLoteCont(lote);
            actulizarAgregado();


        }
    });
}
//codigo de remision de lote a editar 
function remisionEditarLote(lote) {
    var lote = lote;
    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "remisionDelLoteAEditar",
            lote: lote
        },
        success: function (result) {
            document.getElementById("codRmision").value = result;

        }
    });
}
//buscar lote
function contenidoEditarLoteCont(lote) {
    var lote = lote;
    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "contenidoDelLoteAEditar",
            lote: lote
        },
        success: function (result) {
            $("#contend").html(result);
            actulaizarContador(lote);
            traerInformacionEliminar(lote);
            calcularAreaTotalTabalEdit();
        }
    });

}
//aculizar el contador y los totales de la tabla 
function actulaizarContador(lote) {
    var lote = lote;
    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "actualizarContador",
            lote: lote
        },
        success: function (result) {

            document.getElementById("contadorUpdate").value = parseInt(result);
            document.getElementById("agregados").value = parseInt(result);

            var canastilla = $("#contadorUpdate").val();

            for (var i = 1; i <= canastilla; i++) {
                cantidadBulbosPorCanasta(i);
                calcualarArea(i);
                calcularAreaTotalTabal();
                estimadoIndependiente(canastilla);
                document.getElementById("contador").value = i + 1;
                if (i === canastilla) {
                    canastilla++;
                }
            }


        }
    });


}
//menu lote
function menuBotonesEditar() {
    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "menuBotonesEditar"
        },
        success: function (result) {
            $("#menuSiembraM").html(result);
        }
    });
}
//select referencia  por codigo 
function enviarDatosEditadoLote() {
    var opcion =  window.confirm("Recuerda esta acción no es reversible");
    if (opcion === true) {
        document.getElementById("myForm").submit();
        alert("Los Datos Se Editaron con Exito");
    } else {
        mensaje = "Acción cancelada";
    }


}
//eliminar y actulizar espesificacion de eliminacion de filas 
function eliminarRegistroEspesifico(numeroFila, contenidoId) {
    var agregados = $("#agregados").val();
    agregados = agregados - 1;

    if (agregados >= 1) {
        document.getElementById("agregados").value = agregados;
    }
    $("#Siembralote" + numeroFila).remove();
    document.getElementById("canastillaEliminar" + numeroFila).value = contenidoId;



}

//base para saber q filas se deben eliminar 
function traerInformacionEliminar(lote) {

    $.ajax({
        url: "controladorLotes",
        data: {
            intrucion: "imprimirEsquemaParaEliminarCanastillas",
            lote: lote

        },
        success: function (result) {
            $("#conEliminar").html(result);
        }
    });
}
function calcularAreaTotalTabalEdit() {

    var suma = 0;
    $('.sumaAreas').each(function () {
        suma += parseFloat($(this).val());
    });

    document.getElementById("totalArea").value = suma;
    
        var suma = 0;
    $('.sumaBulbosTotal').each(function () {
        suma += parseFloat($(this).val());
    });
    
    document.getElementById("totalBubos").value = suma;
    
    
        var suma = 0;
    $('.sumaCanastillasE').each(function () {
        suma += parseFloat($(this).val());
    });
    
    document.getElementById("totalCanastillas").value = suma;

}
//mostrar codigo
function mostrarCodiogLoteFinca(){
    var finca = $("#codLoteFinca").val();
    document.getElementById("bucarPorLotes").value = finca;
}


