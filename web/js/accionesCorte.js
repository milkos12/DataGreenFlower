//buscar lote q se va a buscar 
function buscarLoteCorte() {
    var lote = $("#codigoLote").val();
    fincaCorte(lote);
}
//
//traer finca 
function fincaCorte(lote) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "fincaDelLote",
            lote: lote

        },
        success: function (result) {
            $("#nombreFincaCorte").html(result);
            codigosCorte(lote);

        }
    });
}
//traer la codigos de simbra 
function codigosCorte(lote) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "codigosContenidosLote",
            lote: lote

        },
        success: function (result) {
            $("#selectCodgios").html(result);
            nombreEspecie(lote);
            codigosSelectVariedades(lote);
        }
    });
}
//nombre de especie 
function nombreEspecie(lote) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "nombreEspecie",
            lote: lote

        },
        success: function (result) {
            $("#respuestaEspecieLote").html(result);
            contenidosUrls(lote);
        }
    });
}
//listado de contenidos para el centro de la tabla 
function contenidosUrls(lote) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "contenidosUrlCentroTabla",
            lote: lote

        },
        success: function (result) {
            $("#contenidos").html(result);
            fechaSiembra(lote);
        }
    });
}
//traer las variedades de la siembra
function fechaSiembra(lote) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "fechaSiembra",
            lote: lote

        },
        success: function (result) {
            $("#fechaSiembra").html(result);
            traerSemnaSiembra(lote);

        }
    });
}
//traer semana de siembra
function traerSemnaSiembra(lote) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "semanaSiembra",
            lote: lote

        },
        success: function (result) {
            $("#semanaSiembra").html(result);

        }
    });
}
//establecer las clasificaciones en la tabla y demas informacion relacionada con la seleccion
function corteCod(lote, referencia, id_referencia, especie, id_contenido, numeroBulbos, saldo) {

    document.getElementById("contend").innerHTML = "";
    document.getElementById("contador").value = 1;
    $("#respuestaReferencia").html(referencia);
    document.getElementById("numeroDebulbos").value = saldo;
    // $("#saldo").html(saldo);
    document.getElementById("saldo").value = saldo;
    document.getElementById("saldoInput").value = saldo;
    headerTablaCorte(especie);
    bodyTablaCorte(lote, id_contenido, especie);
    menuSimbra();
    actualizarCondorCorte(id_contenido);
    actualizarCondorCantidadClasificaciones(especie);
    document.getElementById("codContenido").value = id_contenido;
    document.getElementById("codEspecie").value = especie;
    var contador = $("#contador").val();
    establecerContenidosActualizar(id_contenido);
   


}
//actualizar 
function establecerContenidosActualizar(id_contenido){
    $.ajax({
        url:"controladorCorte",
        data:{
            intrucion: "cortesActulaizar",
            id_contenido: id_contenido
        },
        success: function(result){
            $("#conteidosCorteActualizar").html(result);
        }
    });
}
//establecer las clasificaciones con el historial del corte 
function headerTablaCorte(especie) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "headerTable",
            especie: especie
        },
        success: function (result) {
            $("#head-tabla-corte").html(result);

        }
    });
}
//traer historial de corte 
function bodyTablaCorte(lote, id_contenido, especie) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "historialCorte",
            lote: lote,
            contenido: id_contenido,
            especie: especie
        },
        success: function (result) {
            $("#contend").append(result);
            footerTablaCorte(lote, id_contenido, especie);



        }
    });
}
//pie de tabla
function footerTablaCorte(lote, id_contenido, especie) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "pieTabla",
            lote: lote,
            contenido: id_contenido,
            especie: especie
        },
        success: function (result) {
            $("#foot").html(result);
            diasPromedio();
            totalesBajas();
            totalesNacionales();
            totalesFila();

        }
    });
}
//botones menu corte
function menuSimbra() {

    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "botonesMenuCorte"

        },
        success: function (result) {
            $("#menuSiembraM").html(result);
        }
    });
}
//botones menu corte
function diasCorte(fila, comant) {
    var fechaSeleccionada = "";
    // estblecer los dias de la primera fecha
    if (1 === comant) {
        var hoy = new Date();

        var year =hoy.getFullYear();
        var day = parseInt(hoy.getDate());
     
        var month = parseInt(hoy.getMonth() + 1);
        if(10 > month){
            
           month = 0 +""+month;
           
        }
        if(10 > day){
            day = 0+""+day;
        }
        fechaSeleccionada = year + "-" + month +"-"+day;
        
    } else {
        fechaSeleccionada = $("#contenidoFecha" + fila).val();
    }
    var ldContenido = $("#codContenido").val();
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "cacularDiasHastaLaFecha",
            fecha: fechaSeleccionada,
            idContenido: ldContenido

        },
        success: function (result) {
            document.getElementById("diasContenido" + fila).value = result;
            diasPromedio();
        }
    });
}
//actualizar cotador 
function actualizarCondorCorte(id_contenido) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "actulaizarContador",
            contenido: id_contenido

        },
        success: function (result) {
            document.getElementById("contador").value = result;
            diasCorte(result, 1);
            
        }
    });
}
//actualizar cotador 
function actualizarCondorCantidadClasificaciones(especie) {
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "actulaizarContadorClasificaciones",
            especie: especie

        },
        success: function (result) {
            document.getElementById("cantidadClasificaciones").value = result;
        }
    });
    var contador = $("#contador").val();

}
//agregar Fila
function agregarFilaCorte() {
    var saldo = $("#saldoInput").val();
    if (saldo > 0) {
        var y = window.scrollY;//matener scroll
        y = y + 900;
        var contador = parseInt($("#contador").val());
        contador++;
        $("#contend").append("<tr id='fila" + contador + "'>");
        document.getElementById("contador").value = contador;
        window.scrollTo(0, y);
        contenidoNuevatabla();
    } else {
        document.querySelector('.cookies-box').classList.remove('active');
        document.querySelector('.cookies-box').classList.add('active');
    }
}
//contenido de la nueva fila 
function contenidoNuevatabla() {
    var contenido = $("#codContenido").val();
    var lote = $("#codigoLote").val();
    var contador = $("#contador").val();
    var especie = $("#codEspecie").val();
    $.ajax({
        url: "controladorCorte",
        data: {
            intrucion: "contenidodNuevaFila",
            contenido: contenido,
            lote: lote,
            contador: contador,
            especie: especie

        },
        success: function (result) {
            $("#fila" + contador).append(result);
            diasCorte(contador);
            diasPromedio();
        }
    });
}
//eliminar fila 
function eliminarFilaCortar() {
    var contador = parseInt($("#contador").val());
    $("#fila" + contador).remove();
    document.getElementById("contador").value = contador - 1;
    diasPromedio();
    totales();
    totalesFila();

}
// días promedio 
function diasPromedio() {
    var suma = 0;
    var cantidadDeRegistros = document.getElementsByClassName("dias").length;
    $('.dias').each(function () {
        suma += parseInt($(this).val());
    });
    suma = suma / cantidadDeRegistros;
    document.getElementById("diasPromedio").value = suma;
    diaMaximo();
    diaMinimio();
    diaModa();
    totales();


}
//dia máximo 
function diaMaximo() {
    var maximo = 0;
    var maxTem = 0;
    maximo = Math.max(maximo);
    $('.dias').each(function () {
        maxTem = parseInt($(this).val());
        if (maxTem > maximo) {
            maximo = maxTem;
        }
    });
    document.getElementById("diaMax").value = maximo;
}
//dia mínimo 
function diaMinimio() {
    var minimo = 0;
    var minTem = 0;
    var estado = 0;
    $('.dias').each(function () {
        minTem = parseInt($(this).val());
        if (estado === 0) {
            minimo = minTem;
            estado = 1;
        }
        if (minTem < minimo) {
            minimo = minTem;
        }
    });
    document.getElementById("diaMin").value = minimo;
}
//dia moda 
function diaModa() {

    var numTem = 0;
    var conteo = 1;
    var numeroModa = 0;
    var cantidadDeDatos = document.getElementsByClassName("dias").length;
    var numeroDeRepeticiones = 0;
    let repeticiones = new Array(cantidadDeDatos);
    let data = new Array(cantidadDeDatos);

    //repeticiones de un número 
    $('.dias').each(function () {
        numeroModa = parseInt($(this).val());
        data[conteo] = numeroModa;
        $('.dias').each(function () {
            numTem = parseInt($(this).val());

            if (numTem === numeroModa) {
                numeroDeRepeticiones++;
                repeticiones[conteo] = numeroDeRepeticiones;
            }
        });
        conteo++;
        numeroDeRepeticiones = 0;
    });

    var maximo = 0;
    var maxTem = 0;

    //sacar la repeticion mas alta y buscar el la posiccion en le array de datos para traer el numero q sera la mda 
    maximo = Math.max(maximo);
    for (var b = 1; b <= cantidadDeDatos; b++) {
        maxTem = repeticiones[b];
        if (maxTem > maximo) {
            maximo = maxTem;
        }
    }

    var existemciaModa = 0;
    //varificar q si todas la repeticiones son igulaes a 1 entonces no hay moda 
    for (var c = 1; c <= cantidadDeDatos; c++) {
        if (1 === repeticiones[c]) {
            existemciaModa++;
        }
    }

    if (existemciaModa === cantidadDeDatos) {
        document.getElementById("diaModa").value = 0;
    } else {
        document.getElementById("diaModa").value = data[maximo];
    }
}
//totales generales   
function totales() {
    var numeroClasificaciones = $("#cantidadClasificaciones").val();
    var contador = 1;
    var suma = 0;
    for (var i = 1; i <= numeroClasificaciones; i++) {

        $('.clasificacion' + i).each(function () {
            suma += parseInt($(this).val());
        });
        document.getElementById("totalClasificacion" + i).value = suma;
        console.log(suma);
        suma = 0;
    }
    var y = window.scrollY;//matener scroll
    y = y + 900;
    window.scrollTo(0, y);

}
//totales nacionales
function totalesNacionales() {

    var suma = 0;

    $('.nacionalesColum').each(function () {
        suma += parseInt($(this).val());
    });
    document.getElementById("totalTalllosNacional").value = suma;
    console.log(suma);
}
//totales bajas
function totalesBajas() {

    var suma = 0;

    $('.bajasColum').each(function () {
        suma += parseInt($(this).val());
    });
    document.getElementById("totalTalllosBaja").value = suma;
    console.log(suma);
}
//totales de cada fila 
function totalesFila(idInput) {


    var valorInput = $("#" + idInput + "").val();


    var cantidadDeFilas = $("#contador").val();
    var suma = 0;
    for (var i = 1; i <= cantidadDeFilas; i++) {
        try {
            $('.valoresColum' + i).each(function () {
                suma += parseInt($(this).val());
            });
            document.getElementById("totalTallosContenido" + i).value = suma;
            suma = 0;
        } catch (exception) {

        }

    }



    $('.totalesTallos').each(function () {
        suma += parseInt($(this).val());
    });

    document.getElementById("totalTalllosTabla").value = suma;
    var cantidadDeClasificaciones = $("#cantidadClasificaciones").val();
    var porcentaje = 0;
    for (var i = 1; i <= cantidadDeClasificaciones; i++) {
        try {
            porcentaje = ($("#totalClasificacion" + i).val() / suma) * 100;
            document.getElementById("porcentajeClasificacion" + i).value = porcentaje;
            porcentaje = 0;
        } catch (exception) {

        }

    }
    var cantidadNacionales = document.getElementById("totalTalllosNacional").value;
    var cantidadBajas = document.getElementById("totalTalllosBaja").value;
    var porcentajeNacionales = cantidadNacionales / suma * 100;
    var porcentajeBajas = cantidadBajas / suma * 100;
    document.getElementById("porcentajeNacional").value = porcentajeNacionales;
    document.getElementById("porcentajeBajas").value = porcentajeBajas;
    var saldoComprobacion = $("#saldoInput").val();





//saldo
    var saldo = $("#numeroDebulbos").val();
    var saldo = saldo - suma;
    document.getElementById("saldoInput").value = saldo;


    if (saldo <= 0) {
        document.querySelector('.cookies-box').classList.add('active');
        if (saldo < 0) {//sacar el exedente en caso de q el valor ingresado sea sea mayor al saldo disponible 
            var valorExcenete = 0;
            var valorExcenete = ((saldo * saldo) ** 0.5);
            valorExcenete = valorInput - valorExcenete;
            document.getElementById(idInput).value = valorExcenete;
            totales();
            totalesNacionales();
            totalesBajas();
            totalesFila(idInput);

        }
    } else {
        document.querySelector('.cookies-box').classList.remove('active');
    }


}
// guardar datos de corte 
function guardatDatosCorte() {
    var form = document.getElementById("myForm");
    var idLote = $("#codigoLote").val();
    form.action = "controladorCorte";
    document.getElementById("myForm").submit();
    alert("Los Datos Se guardaron con Exito");
   
}
//eliminar contenido+
function eliminarContenidoCorte(contador, id) {
    $("#fila" + contador).remove();
    var contadorEliminar = parseInt($("#contadorEliminar").val());
    document.getElementById("contadorEliminar").value = contadorEliminar + 1;
    $("#conEliminar").append("<input type='hidden' name='eliminar" + contadorEliminar + "' value='" + id + "'>");
    diasPromedio();
    totales();
    totalesBajas();
    totalesNacionales();
    contadorEliminar = contadorEliminar + 1;

    var suma = 0;

    $('.totalesTallos').each(function () {
        suma += parseInt($(this).val());
    });

    document.getElementById("totalTalllosTabla").value = suma;
    var cantidadDeClasificaciones = $("#cantidadClasificaciones").val();
    var porcentaje = 0;
    for (var i = 1; i <= cantidadDeClasificaciones; i++) {
        porcentaje = ($("#totalClasificacion" + i).val() / suma) * 100;
        document.getElementById("porcentajeClasificacion" + i).value = porcentaje;
        porcentaje = 0;
    }
    var cantidadNacionales = document.getElementById("totalTalllosNacional").value;
    var cantidadBajas = document.getElementById("totalTalllosBaja").value;
    var porcentajeNacionales = cantidadNacionales / suma * 100;
    var porcentajeBajas = cantidadBajas / suma * 100;
    document.getElementById("porcentajeNacional").value = porcentajeNacionales;
    document.getElementById("porcentajeBajas").value = porcentajeBajas;
    var saldoComprobacion = $("#saldoInput").val();





    //saldo
    var saldo = $("#numeroDebulbos").val();
    var saldo = saldo - suma;
    document.getElementById("saldoInput").value = saldo;
}
function lipiarContenidosEliminar() {
    document.getElementById("conEliminar").innerHTML = "";
    document.getElementById("contadorEliminar").value = 1;
}
function lipiarContenidosTablaCor() {
    document.getElementById("contend").innerHTML = "";

}

