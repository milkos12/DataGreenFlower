//cargar select(Fincas, Flores)
function ingresarSelect() {
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "cargarSelectSiembra"
        },
        success: function (result) {
            $("#selectSiembra").html(result);
        }
    });
}
//Consultar estimiado
function consularEstimado() {
    var Fecha;
    Fecha = $("#fecha").val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            //intrucion: "agregarBD",
            //nombre: nombreColor, 
            parametroAgregar: Fecha
        },
        success: function (result) {
            $("#respuestaAgregarColor").html(result);
        }
    });
}

//calcular semana
function calcularSemana() {
    var fehca = $("#fecha").val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "calcularSemana",
            fecha: fehca

        },
        success: function (result) {
            $("#respuestaSemanas").html(result);
        }
    });
}
//mostrar el numer de la finca 
function selectFincas() {

    var codigoFinca = $("#codigoFinca").val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "selecFinca",
            codigoFinca: codigoFinca

        },
        success: function (result) {
            $("#selectFinca").html(result);
            areaDisponible();
            areaUsada();
        }
    });
}
//codigo de la finca seleccionada
function codigoFincaSelectSiembras() {

    var codigoFinca = $("#FincaSelecionada").val();
    document.getElementById("codigoFinca").value = codigoFinca;
}

//traer fincas de base de datos
function tablaContenido() {
    var variedadId = $("#VariedadSeleccionada").val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "tabalaContenido",
            variedad: variedadId
        },
        success: function (result) {
            $("#datosCorte").html(result);
        }
    });
}
//nueva fila en tabla 
function a(edit) {

    var contador = $("#contador").val();
    var codigoFincaC = $("#FincaSelecionada").val();
    var fecha = $("#fecha").val();
    var remision = $("#codRmision").val();

    if (codigoFincaC >= 1 & fecha !== "" & remision >= 1) {
        if (edit === 1) {
            $("#contend").append("<tr id='Siembralote" + contador + "'></tr>");
            $("#nuevosRegistros").append("<input type='hidden' id='nuevoRegistro" + contador + "' value='" + contador + "'>");
            
        } else {
            
            $("#siembreTABLA").append("<tr id='Siembralote" + contador + "'></tr>");
            $('.alert').addClass("hide");
            $('alert').removeClass("show");
            $("#alert").html("");

        }
        $("#comenzar").html("<ion-icon name='checkmark-outline' id='iconoBoton'></ion-icon>");
        agregarC(edit);
    } else {
        //mostrar alert 
        //$("#alert").html("<div class='alert show'><span class='fas fa-exclamation-circle'></span><span class='msg'>Revisa los campos fecha, finca y remision</span><sapn class='close-btn'><span class='fas fa-times'></span></span></div>");
        //setTimeout(function () {
        //$('.alert').addClass("hide");
        //$('alert').removeClass("show");
        //}, 5000);
        existenceCodLote();
            setTimeout(alertAlreadyExisLote(), 2000);
    }


}
//funcion aviso si el se ingreso la remisión de últiomo
function avisioRemision() {
    $("#alert").html("<div class='alert alertRemision show' style='backgrount'><span class='fas fa-exclamation-circle'></span><span class='msg'>Si ingresaste la remisión de últimas presiona Enter para continuar</span><sapn class='close-btn'><span class='fas fa-times'></span></span></div>");
    setTimeout(function () {
        $('.alert').addClass("hide");
        $('alert').removeClass("show");
    }, 10000);
}
//funcion aviso de faltantes finca 

//agregar canastillas a la tabla 
function agregarC(edit) {
    var y = window.scrollY;


    var fehca = $("#fecha").val();
    var contador = $("#contador").val();
    var finca = $("#FincaSelecionada").val();
    var codigoDeFinido;
    var flor;
    var color;
    var variedad;
    var referencia;
    var calibre;
    var proveedor;
    var desidad;
    var area;
    var bulbos;
    var totalCanastillas;
    var fechaEstimada;
    var cantidadCanastillas;
    if (edit === 1) {
        var update = parseInt($("#contadorUpdate").val());
        for (var r = 1; r < update; r++) {
            var campo = parseInt($("#canastillaEliminar" + r).val());
            if (campo === 0) {
                codigoDeFinido = parseInt($("#codigoGenarado" + r).val());
                flor = $("#florCanastilla" + r).val();
                variedad = $("#variedadesCanastilla" + r).val();
                color = $("#coloresCanastilla" + r).val();
                referencia = $("#referenciasCanastilla" + r).val();
                calibre = $("#calibresCanastilla" + r).val();
                proveedor = $("#proveedoresCanastilla" + r).val();
                desidad = $("#desidadCanastilla" + r).val();
                area = parseInt($("#areaCanastillas" + r).val());
                bulbos = $("#cantidadBulbosPorCanastillas" + r).val();
                totalCanastillas = $("#cantidadCanastillas" + r).val();
                fechaEstimada = $("#fechaEstimadaCo" + r).val();
                cantidadCanastillas = $("#cantidadCanastillas" + r).val();
                r = update;
            }
        }
    } else {
        if (contador > 1) {
            var parametroParaBuscar = contador - 1;
            codigoDeFinido = parseInt($("#codigoGenarado" + parametroParaBuscar).val());
            flor = $("#florCanastilla" + parametroParaBuscar).val();
            variedad = $("#variedadesCanastilla" + parametroParaBuscar).val();
            color = $("#coloresCanastilla" + parametroParaBuscar).val();
            referencia = $("#referenciasCanastilla" + parametroParaBuscar).val();
            calibre = $("#calibresCanastilla" + parametroParaBuscar).val();
            proveedor = $("#proveedoresCanastilla" + parametroParaBuscar).val();
            desidad = $("#desidadCanastilla" + parametroParaBuscar).val();
            area = parseInt($("#areaCanastillas" + parametroParaBuscar).val());
            bulbos = $("#cantidadBulbosPorCanastillas" + parametroParaBuscar).val();
            totalCanastillas = $("#cantidadCanastillas" + parametroParaBuscar).val();
            fechaEstimada = $("#fechaEstimadaCo" + parametroParaBuscar).val();
            cantidadCanastillas = $("#cantidadCanastillas" + parametroParaBuscar).val();
            if (codigoDeFinido >= 1) {
                if (flor >= 1) {
                    if (variedad >= 1) {
                        if (color >= 1) {
                            if (referencia >= 1) {
                                if (calibre >= 1) {
                                    if (proveedor >= 1) {
                                        if (desidad >= 1) {
                                            if (area >= 1) {
                                                if (bulbos >= 1) {
                                                    if (cantidadCanastillas >= 1) {

                                                    } else {
                                                        alert("No difiniste Canastillas y no podemos continuar");
                                                        return;
                                                    }

                                                } else {
                                                    alert("No difiniste Bulbos y no podemos continuar");
                                                    return;
                                                }

                                            } else {
                                                alert("No difiniste Un area y no podemos continuar");
                                                return;
                                            }

                                        } else {
                                            alert("No difiniste Una Densidad y no podemos continuar");
                                            return;
                                        }

                                    } else {
                                        alert("No difiniste Un proveedor y no podemos continuar");
                                        return;
                                    }

                                } else {
                                    alert("No difiniste Un calibre y no podemos continuar");
                                    return;
                                }

                            } else {
                                alert("No difiniste Una referenicia y no podemos continuar");
                                return;
                            }

                        } else {
                            alert("No difiniste Un color y no podemos continuar");
                            return;
                        }

                    } else {
                        alert("No difiniste Una varidad y no podemos continuar");
                        return;
                    }

                } else {
                    alert("No difiniste Una flor y no podemos continuar");
                    return;
                }
            } else {
                alert("No difiniste Un código y no podemos continuar");
                return;
            }
        }
    }
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "agreagarCanastilla",
            fecha: fehca,
            contador: contador,
            codigoDefinido: codigoDeFinido,
            flor: flor,
            varidad: variedad,
            color: color,
            referencia: referencia,
            calibre: calibre,
            proveedor: proveedor,
            densidad: desidad,
            area: area,
            bulbos: bulbos,
            fechaESTIMADA: fechaEstimada,
            cantidadCANASTILLAS: cantidadCanastillas,
            finca: finca


        },
        success: function (result) {
            alert(result)
            $("#Siembralote" + contador).html(result);
            actualizarContador();
            cantidadBulbosPorCanasta(contador);
            calcualarArea(contador);
            if (edit === 1) {
                calcularAreaTotalTabalEdit();

            }
        }
    });
    window.scrollTo(0, 10000);
    //if(contador>=2){
 
}

//calcular area para siembra ---
function areaDisponible() {

    var finca = $("#FincaSelecionada").val();
    var parametro = 0;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "areaDisponible",
            finca: finca,
            parameter: parametro
        },
        success: function (result) {
            $("#areaDisponible").html(result);
        }
    });
}
//calcular area usuada ---
function areaUsada() {

    var finca = $("#FincaSelecionada").val();
    var parametro = 1;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "areaDisponible",
            finca: finca,
            parameter: parametro
        },
        success: function (result) {
            $("#areaUsada").html(result);

        }
    });
}
function actualizarContador() {
    var contadaoorL = parseInt($("#contador").val());
    document.getElementById("contador").value = contadaoorL + 1;
}
function suma() {
    var cantidadCanastillas = $("#contadoCanastillas").val();
    const cantidadDatosPorCanastilla = Array.from(document.getElementsByClassName("canastila1"));
    console.log(cantidadDatosPorCanastilla);
    for (var i = 1; cantidadCanastillas >= i; i++) {
        for (var e = 1; cantidadDatosPorCanastilla >= e; e++) {
            console.log(e);
        }

    }
}
//existencia del codigo
function existenciaCodigo(canastilla) {
    var codigo = $("#buscarCodigo" + canastilla).val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "existencicodigo",
            codigo: codigo,
            canastilla: canastilla

        },
        success: function (result) {

            $("#actualizarEstadoDeBusqueda" + canastilla).html(result);
            nuevoLote(canastilla);
        }
    });
}
//
//select flores siembra 
function nuevoLote(canastilla) {
    var estadobuqueda = $("#estadoDeBusqueda" + canastilla).val();
    if (estadobuqueda >= 1) {
        var codigo = $("#buscarCodigo" + canastilla).val();
        var parametro = 1;
        var contador = canastilla;
        var fechaA = $("#fecha").val();
        $.ajax({
            url: "controladorSiembra",
            data: {
                intrucion: "codigoParaTablaDeSiembra",
                codigo: codigo,
                parametro: parametro,
                contador: contador,
                fecha: fechaA
            },
            success: function (result) {

                $("#florSelectSimbra" + contador).html(result);
                variedadSiembra(contador);
            }
        });
    }

}
//selec variedades codigo 
function variedadSiembra(contador) {
    var parametro = 2;
    var contador = contador;
    var codigo = $("#buscarCodigo" + contador).val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "codigoParaTablaDeSiembra",
            codigo: codigo,
            parametro: parametro,
            contador: contador
        },
        success: function (result) {
            $("#variedadSelectReferencia" + contador).html(result);
            colorSiembra(contador);
            estimadoIndependiente(contador);
        }
    });
}
//select color codigo 
function colorSiembra(contador) {
    var parametro = 3;
    var contador = contador;
    var codigo = $("#buscarCodigo" + contador).val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "codigoParaTablaDeSiembra",
            codigo: codigo,
            parametro: parametro,
            contador: contador
        },
        success: function (result) {
            $("#ColorSelectSiembra" + contador).html(result);
            referenciaSiembra(contador);
            variedadSiembra(contador);
        }
    });
}
//select referenca codigo 
function referenciaSiembra(contador) {
    var parametro = 4;
    var contador = contador;
    var codigo = $("#buscarCodigo" + contador).val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "codigoParaTablaDeSiembra",
            codigo: codigo,
            parametro: parametro,
            contador: contador
        },
        success: function (result) {
            $("#referernciaSelectSimbra" + contador).html(result);
            calibresSiembra(contador);
        }
    });
}
//select calibres codigo 
function calibresSiembra(contador) {
    var parametro = 5;
    var contador = contador;
    var codigo = $("#buscarCodigo" + contador).val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "codigoParaTablaDeSiembra",
            codigo: codigo,
            parametro: parametro,
            contador: contador
        },
        success: function (result) {
            $("#calibresCanastilla" + contador).html(result);
        }
    });
}
//establecer codigo en input flor
function codigoFlorCanastilla(canastilla) {
    var codigo = $("#florCanastilla" + canastilla).val();
    console.log(codigo);
    document.getElementById("florCanastillaCodigo" + canastilla).value = codigo;
    variedadSelect(canastilla);
    codigoCalibreCanastilla(canastilla);
    autoRellenarCantidad(canastilla);
}
//establecer codigo en input color
function codigoColorCanastilla(canastilla) {
    var codigo = $("#coloresCanastilla" + canastilla).val();
    console.log(codigo);
    document.getElementById("colorCanastillaCodigo" + canastilla).value = codigo;
}
//establecer codigo en input calibre
function codigoCalibreCanastilla(canastilla) {
    var codigo = $("#calibresCanastilla" + canastilla).val();
    console.log(codigo);
    document.getElementById("calibreCanastillaCodigo" + canastilla).value = codigo;
}
//establecer codigo en input variedad
function codigoVariedadCanastilla(canastilla) {
    var codigo = $("#variedadesCanastilla" + canastilla).val();
    console.log(codigo);
    document.getElementById("variedadCanastillaCodigo" + canastilla).value = codigo;
    referenciaNewFucntion(canastilla);
}
//establecer codigo en input referencia
function codigoReferenciaCanastilla(canastilla) {
    var codigo = $("#referenciasCanastilla" + canastilla).val();
    document.getElementById("referenciaCanastillaCodigo" + canastilla).value = codigo;
    selectedColor(canastilla);
}
//establecer codigo en input proveedores
function codigoProveedoresiaCanastilla(canastilla) {
    var codigo = $("#proveedoresCanastilla" + canastilla).val();
    console.log(codigo);
    document.getElementById("proveedorCanastillaCodigo" + canastilla).value = codigo;
}
//select flor por codigo 
function selectFlorPorCodigo(canastilla) {
    var codigo = $("#florCanastillaCodigo" + canastilla).val();
    var canastilla = canastilla;
    var parametro = 1;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionDeFlorPorCodigo",
            codigo: codigo,
            canastilla: canastilla,
            parametro: parametro
        },
        success: function (result) {
            $("#establecerFlorSelectPorCodigoCanastilla" + canastilla).html(result);
            variedadSelect(canastilla);
        }
    });
}
//select color por codigo 
function selectColorPorCodigo(canastilla) {
    var codigo = $("#colorCanastillaCodigo" + canastilla).val();
    var canastilla = canastilla;
    var parametro = 2; //-parametro
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionDeFlorPorCodigo",
            codigo: codigo,
            canastilla: canastilla,
            parametro: parametro
        },
        success: function (result) {
            $("#establecerSelectColorPorCodigoCanastilla" + canastilla).html(result); //-div

        }
    });
}
//select calibre  por codigo 
function selectCaibrePorCodigo(canastilla) {
    var codigo = $("#calibreCanastillaCodigo" + canastilla).val();
    var canastilla = canastilla;
    var parametro = 3; //-parametro
    console.log(codigo + " " + canastilla + " " + parametro);
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionDeFlorPorCodigo",
            codigo: codigo,
            canastilla: canastilla,
            parametro: parametro
        },
        success: function (result) {
            $("#establecerSelectCalibrePorCodigoCanastilla" + canastilla).html(result); //-div

        }
    });
}
//select proveedores  por codigo
function selectProveedorPorCodigo(canastilla) {
    var codigo = $("#proveedorCanastillaCodigo" + canastilla).val();
    var canastilla = canastilla;
    var parametro = 4; //-parametro
    console.log(codigo + " " + canastilla + " " + parametro);
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionDeFlorPorCodigo",
            codigo: codigo,
            canastilla: canastilla,
            parametro: parametro
        },
        success: function (result) {
            $("#establecerSelectProveedorPorCodigoCanastilla" + canastilla).html(result); //-div

        }
    });
}
//rellenar select variedades
function variedadSelect(canastilla) {
    var canastilla = canastilla;
    var florId = $("#florCanastilla" + canastilla).val();
    var parametro = 1;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "cargarSelectDependientes",
            codigo: florId,
            canastilla: canastilla,
            parametro: parametro
        },
        success: function (result) {
            $("#variedadSelectReferencia" + canastilla).html(result); //----------listo
            estimadoIndependiente(canastilla);
            codigoVariedadCanastilla(canastilla);


        }
    });
}
//select variedades  por codigo 
function selectVariedadPorCodigo(canastilla) {
    var codigo = $("#variedadCanastillaCodigo" + canastilla).val();
    var flor = $("#florCanastilla" + canastilla).val();
    var canastilla = canastilla;
    var parametro = 1; //-parametro
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionDeDependientesPorCodigo",
            codigo: codigo,
            canastilla: canastilla,
            parametro: parametro,
            codigoDe: flor
        },
        success: function (result) {
            $("#establecerSelectVariedadPorCodigoCanastilla" + canastilla).html(result); //-div

        }
    });
}
//nueva seleccion referencia 
function referenciaNewFucntion(canastilla) {

    var canastilla = canastilla;
    var varieda = $("#variedadesCanastilla" + canastilla).val();
    var parametro = 2;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "cargarSelectRerenciasNew",
            canastilla: canastilla,
            parametro: parametro,
            variedad: varieda
        },
        success: function (result) {
            $("#referernciaSelectSimbra" + canastilla).html(result); //----------listo
            estimadoIndependiente(canastilla);
            codigoReferenciaCanastilla(canastilla);
        }
    });
}
//nueva seleccion referencia 
function selectedColor(canastilla) {

    var canastilla = canastilla;
    var varieda = $("#referenciasCanastilla" + canastilla).val();
    var parametro = 2;

    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionarColorForReferencia",
            canastilla: canastilla,
            parametro: parametro,
            variedad: varieda
        },
        success: function (result) {

            let idColor = parseInt(result);

            $("#coloresCanastilla" + canastilla).val(idColor);
            estimadoIndependiente(canastilla);
            codigoColorCanastilla(canastilla);
        }
    });
}
//rellenar select referencia
function referenciaSelect(canastilla) {
    var canastilla = canastilla;
    var colorId = $("#coloresCanastilla" + canastilla).val();
    var varieda = $("#variedadesCanastilla" + canastilla).val();
    var parametro = 2;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "cargarSelectDependientes",
            codigo: colorId,
            canastilla: canastilla,
            parametro: parametro,
            variedad: varieda
        },
        success: function (result) {
            $("#referernciaSelectSimbra" + canastilla).html(result); //----------listo
            estimadoIndependiente(canastilla);
        }
    });
}
//select referencia  por codigo 
function selectReferenciaPorCodigo(canastilla) {
    var codigo = $("#referenciaCanastillaCodigo" + canastilla).val();
    var color = $("#coloresCanastilla" + canastilla).val();
    var canastilla = canastilla;
    var varieda = $("#variedadesCanastilla" + canastilla).val();
    var parametro = 2; //-parametro
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "seleccionDeDependientesPorCodigo",
            codigo: codigo,
            canastilla: canastilla,
            parametro: parametro,
            codigoDe: color,
            variedad: varieda
        },
        success: function (result) {
            $("#establecerSelectReferenciaPorCodigoCanastilla" + canastilla).html(result); //-div

        }
    });
}
//cantidad de bulbos por lote
function cantidadBulbosPorCanasta(canastilla) {

    let cantidadBulbos = $("#cantidadBulbosPorCanastillas" + canastilla).val();
    let cantidadCanastillas = $("#cantidadCanastillas" + canastilla).val();
    document.getElementById("cantidadBulbos" + canastilla).value = cantidadBulbos * cantidadCanastillas;
}
//calcular area
function calcualarArea(canastilla, edit) {
    var densidad = $("#desidadCanastilla" + canastilla).val();
    var cantidadBulbos = $("#cantidadBulbos" + canastilla).val();
    var areaDisponibreF = $("#areTotalDisponible").val();
    var areaUsada = cantidadBulbos / densidad;
    document.getElementById("areaCanastillas" + canastilla).value = areaUsada;
    console.log("area usada: " + areaUsada + " area dispobile: " + areaDisponible);
    calcularAreaTotalTabal(edit);
}
function calcularAreaTotalTabal() {

    var contadorCanastillas = parseInt($("#contador").val());
    contadorCanastillas = contadorCanastillas - 1;
    var total = 0;
    for (var i = 1; i <= contadorCanastillas; i++) {
        total = total + parseFloat($("#areaCanastillas" + i).val());
    }
    document.getElementById("totalArea").value = total;
    var aTotalDisponible = parseFloat($("#areTotalDisponible").val());
    total = parseFloat(aTotalDisponible - total);
    document.getElementById("calculoDeAreaParaSiembra").value = total;
    total = 0;
    for (var i = 1; i <= contadorCanastillas; i++) {
        total = total + parseInt($("#cantidadCanastillas" + i).val());
    }
    document.getElementById("totalCanastillas").value = total;
    total = 0;
    for (var i = 1; i <= contadorCanastillas; i++) {
        total = total + parseInt($("#cantidadBulbos" + i).val());
    }
    document.getElementById("totalBubos").value = total;
    window.scrollTo(0, 99999);


}
function guardarDatos() {

    $("#mensaje").html("...");
    enviarDatos();
}

//select referencia  por codigo 
function enviarDatos() {

    var contador = parseInt($("#contador").val());
    var fecha = $("#fecha").val();
    var finca = $("#FincaSelecionada").val();

    var codigoDeFinido;
    var flor;
    var variedad;
    var color;
    var referencia;
    var calibre;
    var proveedor;
    var desidad;
    var area;
    var bulbos;
    var totalCanastillas;
    var i = 1;
    var valor = "True";
    var valor2 = "False";
    var estado = $("#respuesta").val();
    do {
        codigoDeFinido = parseInt($("#codigoGenarado" + i).val());
        flor = $("#florCanastilla" + i).val();
        variedad = $("#variedadesCanastilla" + i).val();
        color = $("#coloresCanastilla" + i).val();
        referencia = $("#referenciasCanastilla" + i).val();
        calibre = $("#calibresCanastilla" + i).val();
        proveedor = $("#proveedoresCanastilla" + i).val();
        desidad = $("#desidadCanastilla" + i).val();
        area = parseInt($("#areaCanastillas" + i).val());
        bulbos = $("#cantidadBulbos" + i).val();
        totalCanastillas = $("#cantidadCanastillas" + i).val();
        if (codigoDeFinido >= 1) {
            if (flor >= 1) {
                if (variedad >= 1) {
                    if (color >= 1) {
                        if (referencia >= 1) {
                            if (calibre >= 1) {
                                if (proveedor >= 1) {
                                    if (desidad >= 1) {
                                        if (area >= 1) {
                                            if (bulbos >= 1) {
                                                if (totalCanastillas >= 1) {

                                                    i++;
                                                    if (i == contador) {

                                                        var form = document.getElementById("myForm");
                                                        form.action = "controladorLotes";
                                                        document.getElementById("myForm").submit();

                                                        alert("Los Datos Se guardaron con Exito");

                                                    }

                                                } else {
                                                    document.getElementById("respuesta").value = valor;
                                                    alert("No definiste CANASTILLAS en la FILA---->" + i);
                                                    event.preventDefault();
                                                    break;
                                                }
                                            } else {
                                                document.getElementById("respuesta").value = valor;
                                                alert("No definiste BULBOS  en la FILA---->" + i);
                                                event.preventDefault();
                                                break;
                                            }
                                        } else {
                                            document.getElementById("respuesta").value = valor;
                                            alert("No definiste AREA  en la FILA---->" + i);
                                            event.preventDefault();
                                            break;
                                        }
                                    } else {
                                        document.getElementById("respuesta").value = valor;
                                        alert("No definiste DENSIDAD  en la FILA---->" + i);
                                        event.preventDefault();
                                        break;
                                    }
                                } else {
                                    document.getElementById("respuesta").value = valor;
                                    alert("No definiste PROVEEDOR  en la FILA---->" + i);
                                    event.preventDefault();
                                    break;
                                }
                            } else {
                                document.getElementById("respuesta").value = valor;
                                alert("No definiste CALIBRE  en la FILA---->" + i);
                                event.preventDefault();
                                break;
                            }
                        } else {
                            document.getElementById("respuesta").value = valor;
                            alert("No definiste REFERENCIA  en la FILA---->" + i);
                            event.preventDefault();
                            break;
                        }
                    } else {
                        document.getElementById("respuesta").value = valor;
                        alert("No definiste COLOR  en la FILA---->" + i);
                        event.preventDefault();
                        break;
                    }
                } else {
                    document.getElementById("respuesta").value = valor;
                    alert("No definiste VARIEDAD  en la FILA---->" + i);
                    event.preventDefault();
                    break;
                }
            } else {
                document.getElementById("respuesta").value = valor;
                alert("No definiste FLOR  en la FILA---->" + i);
                event.preventDefault();
                break;
            }
        } else {

            document.getElementById("respuesta").value = valor;
            alert("No definiste CODIGO  en la FILA---->" + i);
            event.preventDefault();
            break;
        }
    } while (i < contador);
}
//CALCULAR ESTIMADO independiente 
function estimadoIndependiente(canastilla) {
    var flor = $("#florCanastilla" + canastilla).val();
    var variedad = $("#variedadesCanastilla" + canastilla).val();
    var fica = $("#FincaSelecionada").val();
    var fecha = $("#fecha").val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "estimadoFechaIndividual",
            flor: flor,
            finca: fica,
            variedad: variedad,
            fecha: fecha,
            contador: canastilla

        },
        success: function (result) {
            $("#f" + canastilla).html(result);
            //-div

        }
    });
}
function eliminarFila(edit) {
    var objDiv = document.getElementById("myForm");
    if (edit === 1) {
        var agregados = parseInt($("#agregados").val());

        console.log(agregados);
        if (agregados >= 1) {
            document.getElementById("agregados").value = agregados - 1;
        }
    }
    var contador = parseInt($("#contador").val());
    var contador = contador - 1;
    document.getElementById("contador").value = contador;
    $("#Siembralote" + contador).remove();

    objDiv.scrollTop = objDiv.scrollHeight;
    cantidadBulbosPorCanasta(contador - 1);
    calcualarArea(contador - 1);



}
//auto completar codigo 
function AutoCompletarCodigo(canastilla) {

    var flor = $("#florCanastilla" + canastilla).val();
    var variedad = $("#variedadesCanastilla" + canastilla).val();
    var color = $("#coloresCanastilla" + canastilla).val();
    var referencia = $("#referenciasCanastilla" + canastilla).val();
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "autoRellenaCodigo",
            flor: flor,
            variedad: variedad,
            color: color,
            referencia: referencia
        },
        success: function (result) {
            console.log(result + "----------------" + canastilla);

            document.getElementById("buscarCodigo" + canastilla).value = result;

        }
    });
}
function autoRellenarCantidad(canastilla) {
    var calibre = $("#calibresCanastilla" + canastilla).val();
    if (calibre === "2") {
        document.getElementById("cantidadBulbosPorCanastillas" + canastilla).value = 300;
        console.log("2");
    } else if (calibre === "1") {
        console.log("1");
        document.getElementById("cantidadBulbosPorCanastillas" + canastilla).value = 400;
    }
}
//menu siembra 
function menuSimbra() {

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
//menu siembra 
function quitarBotones() {

    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "quitarbotonesMenuSiembra"

        },
        success: function (result) {
            $("#menuSiembraM").html(result);
        }
    });
}
//comprobation exist de codigo 
function existenceCodLote() {
    try {
        let fecha = $("#fecha").val();
        let finca = $("#FincaSelecionada").val();
        let response = "";
        $.ajax({
            url: "controladorSiembra",
            data: {
                intrucion: "existenceCodLote",
                fecha: fecha,
                finca: finca
            },
            success: function (result) {
                $("#existenceCodLote").html(result);
                alertAlreadyExisLote();

            }

        });
    } catch (e) {

    }

}
//alert already the existence cod lote 
function alertAlreadyExisLote() {

    let confir = parseInt($("#existenceCodLoteInput").val());


    if (confir > 0) {
        quitarBotones();
        document.getElementById('codRmision').disabled = true;
        $("#alert").html("<div class='alert show'><span class='fas fa-exclamation-circle'>\n\
                 </span><span class='msg'>El lote que intenta registrar ya existe. Si quiere continuar\n\
                 registrando en el lote ya existente seleccione <a href='#' onclick='editarLote(" + confir + "); \n\
                 menuBotonesEditar();'>aquí </span><sapn oclick='closeAlertLote();' class='close-btn'><span class='fas fa-times'></span></span></div>");
        setTimeout(function () {
            $('.alert').addClass("hide");
            $('alert').removeClass("show");

        }, 10000);
    } else {
        $('.alert').addClass("hide");
        $('alert').removeClass("show");
        document.getElementById('codRmision').disabled = false;
        botonesMenuSiembra();
    }

}
//updated cod lote

// week date
function l() {
    return new Promise((resolve, reject) => {
        var fecha = document.getElementById("fecha");
        fecha = fecha.value;
        resolve($.ajax({
            url: "controladorSiembra",
            data: {
                intrucion: "updatignWeek",
                fecha: fecha

            },
            success: function (result) {
                return result;
            }
        }));
    });

}
function uuu(b) {
    b.then(function (data) {
        console.log(data);
    });
}
//agregar color panel 
function addPanelColor() {
    document.querySelector('.add-colors').classList.add('active');
}
//agregar color referencia 
function addPanelReferencia() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro con\n\
los siguientes requerimientos: Una especie seleccionada y una referencia seleccionada-⚠");
    } else {
        contador = contador - 1;
        try {
            let ref = $('#variedadesCanastilla' + contador).val();
            let vari = $('#florCanastilla' + contador).val();
            if (ref > 0 && vari > 0) {
                document.querySelector('.add-referencia').classList.add('active');
                infoSelcetsReferencia(contador);
            } else {
                alert("⚠no podemos hacer esto hasta que se tenga un registro con\n\
los siguientes requerimientos: Una especie seleccionada y una referencia seleccionada⚠" + contador);
            }
        } catch (e) {

        }

    }

}
//agregar color referencia 
function addPanelVariedad() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro con\n\
los siguientes requerimientos: Una especie seleccionada⚠");
    } else {
        contador = contador - 1;
        try {

            let vari = $('#florCanastilla' + contador).val();
            if (vari > 0) {
                document.querySelector('.add-variedad').classList.add('active');
                infoSelcetsVariedad(contador);
            } else {
                alert("⚠no podemos hacer esto hasta que se tenga un registro con\n\
los siguientes requerimientos: Una flor seleccionada ⚠" + contador);
            }
        } catch (e) {

        }

    }

}
//agregar color referencia 
function addPanelFlor() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro en la tabla⚠");
    } else {
        contador = contador - 1;
        try {
            document.querySelector('.add-flor').classList.add('active');
        } catch (e) {

        }

    }

}
//agregar calibre referencia 
function addPanelCalibre() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro en la tabla⚠");
    } else {
        contador = contador - 1;
        try {
            document.querySelector('.add-calibre').classList.add('active');
        } catch (e) {

        }

    }

}
//agregar proveedor 
function addPanelProveedor() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro en la tabla⚠");
    } else {
        contador = contador - 1;
        try {
            document.querySelector('.add-proveedor').classList.add('active');
        } catch (e) {

        }

    }

}
//agregar calibre
function addPanelCalibre() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro en la tabla⚠");
    } else {
        contador = contador - 1;
        try {
            document.querySelector('.add-calibre').classList.add('active');
        } catch (e) {

        }

    }

}
//agregar proveedor 
function addPanelProveedor() {
    let contador = $('#contador').val();

    if (contador == 1) {

        alert("⚠no podemos hacer esto hasta que se tenga un registro en la tabla⚠");
    } else {
        contador = contador - 1;
        try {
            document.querySelector('.add-proveedor').classList.add('active');
        } catch (e) {

        }

    }

}
//info agregar select referencia
function infoSelcetsReferencia(contador) {
    setTimeout(() => {
        let alertInfo = document.getElementById("description_add_referencia");
        let flor = document.getElementById('florCanastilla' + contador);
        let variedad = document.getElementById('variedadesCanastilla' + contador);
        let color = document.getElementById('coloresCanastilla' + contador);
        let nameFlor = flor.options[flor.selectedIndex].text;
        let nameVariedad = variedad.options[variedad.selectedIndex].text;
        let nombreColor = color.options[color.selectedIndex].text;
        alertInfo.innerText = "Flor " + nameFlor + " |  Variedad " + nameVariedad + " | Color " + nombreColor;
    }, 100);

}
//info agregar select variedad
function infoSelcetsVariedad(contador) {
    setTimeout(() => {
        let alertInfo = document.getElementById("description_add_variedad");
        let flor = document.getElementById('florCanastilla' + contador);
        let nameFlor = flor.options[flor.selectedIndex].text;
        alertInfo.innerText = "Flor " + nameFlor;
    }, 100);

}
//save referencia 
function saveReferenciSiembra() {
    try {
        let contador = ($('#contador').val()) - 1;
        var idVariedad = $('#variedadesCanastilla' + contador).val();
        var idColor = $('#coloresCanastilla' + contador).val();
        var nombreDegradado = $('#nombreDegradado').val();
        var parametro = 1;
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
                referenciaNewFucntion(contador);
            }
        });
    } catch (e) {
        alert("no pudimos procesar la solicitud ⚠️");
    }
}
//save referencia warning
function warningReferencia() {
    let contador = ($('#contador').val()) - 1;
    let alertInfo = document.getElementById("description_add_referencia");
    let flor = document.getElementById('florCanastilla' + contador);
    let variedad = document.getElementById('variedadesCanastilla' + contador);
    let color = document.getElementById('coloresCanastilla' + contador);
    let nameFlor = flor.options[flor.selectedIndex].text;
    let nameVariedad = variedad.options[variedad.selectedIndex].text;
    let nombreColor = color.options[color.selectedIndex].text;
    let nombreNuevaReferencia = $('#nombreDegradado').val();
    if (nombreNuevaReferencia !== "") {
        if (window.confirm("⚠️ Confirma la información una vez mas: Flor " + nameFlor + " | Variedad: " + nameVariedad + " | Color: " + nombreColor + " | NOMBRE DE LA NUEVA REFERENCIA: " + nombreNuevaReferencia + ". Al oprimir (ACEPTAR) aceptas que se agregue esta referencia y confirmas haber revisado que dicha referencia no existe (por favor revise antes de continuar)")) {
            saveReferenciSiembra();
        }
    } else {
        alert("🛑No definio un Nombre🛑");
    }

}
//save flor
function saveFlorSiembra() {
    try {
        let contador = ($('#contador').val()) - 1;
        var nombreFlor = $("#nombreFlor").val();
        var parametro = 2;
        $.ajax({
            url: "controladorEspecies",
            data: {
                intrucion: "agregarBD",
                nombre: nombreFlor,
                parametroAgregar: parametro
            },
            success: function (result) {
                $("#respuestaAgregarFlor").html(result);
                refreshFlores();
            }
        });
    } catch (e) {
        alert("no pudimos procesar la solicitud ⚠️");
    }
}

//save referencia warning
function warningFlor() {
    let contador = ($('#contador').val()) - 1;
    let nombreNuevaReferencia = $('#nombreFlor').val();
    if (nombreNuevaReferencia !== "") {
        if (window.confirm("⚠️ Confirma la información una vez mas: | NOMBRE DE LA NUEVA FLOR: " + nombreNuevaReferencia + ". Al oprimir (ACEPTAR) aceptas que se agregue esta flor y confirmas haber revisado que dicha flor no existe (por favor revise antes de continuar)")) {
            saveFlorSiembra();
        }
    } else {
        alert("🛑No definio un Nombre🛑");
    }

}
//save proveedor
function saveProveedorSiembra(contador) {
    try {
        var nombre = $('#nombreProveedor').val();
        var telefono = $('#celularProveedor').val();
        var documento = $('#documentoProveedor').val();
        $.ajax({
            url: "controladorProveedores",
            data: {
                intrucion: "agregarProveedores",
                nombre: nombre,
                telefono: telefono,
                documento: documento
            },
            success: function (result) {
                $("#respuestaPoveedores").html(result);
                refreshProveedores();
            }
        });
    } catch (e) {
        alert("no pudimos procesar la solicitud ⚠️");
    }
}
//save proveedor warning
function warningProveedor() {
    let contador = ($('#contador').val()) - 1;
    let nombreNuevoProveedos = $('#nombreProveedor').val();
    let celularNuevoProveedor = $('#celularProveedor').val();
    if (nombreNuevoProveedos !== "" && celularNuevoProveedor !== "") {
        if (window.confirm("⚠️ Confirma la información una vez mas: | NOMBRE DEl NUEVO PROVEEDOR: " + nombreNuevoProveedos + ". Al oprimir (ACEPTAR) aceptas que se agregue este proveedor y confirmas haber revisado que dicho proveedor no existe (por favor revise antes de continuar)")) {
            saveProveedorSiembra(contador);
        }
    } else {
        alert("🛑No definio un Nombre o Celular🛑");
    }

}
//save calibre
function saveCalibreSiembra(contador) {
    try {
        var calibre = $('#calibre').val();
        var parametro = $("#parametro6").val();

        $.ajax({
            url: "controladorCalibres",
            data: {
                intrucion: "agregarBD",
                nombre: calibre,
                parametroAgregar: 6
            },
            success: function (result) {
                $("#respuestasCalibres").html(result);
                refreshCalibres();
            }
        });
    } catch (e) {
        alert("no pudimos procesar la solicitud ⚠️");
    }
}
//save calibre warning
function warningCalibre() {
    let contador = ($('#contador').val()) - 1;
    let calibre = $('#calibre').val();
    if (calibre != "0/0") {
        if (window.confirm("⚠️ Confirma la información una vez mas: | El NUEVO CALIBRE: " + calibre + ". Al oprimir (ACEPTAR) aceptas que se agregue este calibre y confirmas haber revisado que dicho calibre no existe (por favor revise antes de continuar)")) {
            saveCalibreSiembra(contador);
        }
    } else {
        alert("🛑No definio un calibre🛑");
    }

}
//save variedad 
function saveVariedadSiembra() {
    try {
        let contador = ($('#contador').val()) - 1;
        var idFlor = $('#florCanastilla' + contador).val();
        var nombreVaridad = $('#nombreVariedad').val();
        var parametro = 3;
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
                refreshVariedades();
            }
        });
    } catch (e) {
        alert("no pudimos proecesar la solicitud ⚠️");
    }
}
//save variedad warning
function warningVariedad() {
    let contador = ($('#contador').val()) - 1;
    let alertInfo = document.getElementById("description_add_referencia");
    let flor = document.getElementById('florCanastilla' + contador);

    let nameFlor = flor.options[flor.selectedIndex].text;

    let nombreNuevaVariedad = $('#nombreVariedad').val();
    if (nombreNuevaVariedad !== "") {
        if (window.confirm("⚠️ Confirma la información una vez mas: Flor " + nameFlor + " | NOMBRE DE LA NUEVA VARIEDAD: " + nombreNuevaVariedad + ". Al oprimir (ACEPTAR) aceptas que se agregue esta variedad y confirmas haber revisado que dicha variedad no existe (por favor revise antes de continuar)")) {
            saveVariedadSiembra();
        }
    } else {
        alert("🛑No definio un Nombre🛑");
    }

}
//close panen
function remove(close) {
    if (close == 1) {
        document.querySelector('.add-colors').classList.remove('active');
    } else if (close == 2) {
        document.querySelector('.add-referencia').classList.remove('active');
    } else if (close == 3) {
        document.querySelector('.add-variedad').classList.remove('active');
    } else if (close == 4) {
        document.querySelector('.add-flor').classList.remove('active');
    } else if (close == 5) {
        document.querySelector('.add-proveedor').classList.remove('active');
    } else if (close == 6) {
        document.querySelector('.add-calibre').classList.remove('active');
    } else if (close == 7) {
        document.querySelector('.add-calendar').classList.remove('active');
    } else if (close == 8) {
        document.querySelector('.add-details').classList.remove('active');
    }
}
//refresh colors
function refreshColors() {
    let contador = $("#contador").val() - 1;
    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "selectColors",
            contador: contador
        },
        success: function (result) {
            $("#establecerSelectColorPorCodigoCanastilla" + contador + "").html(result);


        }

    });
}
//refresh variedades 
function refreshVariedades() {
    let contador = $("#contador").val() - 1;
    let flor = $('#florCanastilla' + contador).val();

    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "selectVariedad",
            contador: contador,
            flor: flor
        },
        success: function (result) {
            $("#variedadSelectReferencia" + contador + "").html(result);


        }

    });
}
//refresh flores  
function refreshFlores() {
    let contador = $("#contador").val() - 1;

    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "selectFlor",
            contador: contador
        },
        success: function (result) {
            $("#florSelectSimbra" + contador + "").html(result);


        }

    });
}
//refresh proveedores  
function refreshProveedores() {
    let contador = $("#contador").val() - 1;

    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "selectProveedor",
            contador: contador
        },
        success: function (result) {
            $("#establecerSelectProveedorPorCodigoCanastilla" + contador + "").html(result);


        }

    });
}
//refresh proveedores  
function refreshCalibres() {
    let contador = $("#contador").val() - 1;

    $.ajax({
        url: "controladorSiembra",
        data: {
            intrucion: "selectCalibre",
            contador: contador
        },
        success: function (result) {
            $("#establecerSelectCalibrePorCodigoCanastilla" + contador + "").html(result);


        }

    });
}
//close alert repeated lote 
function closeAlertLote(){
    $('.alert').addClass("hide");
    $('alert').removeClass("show");
}