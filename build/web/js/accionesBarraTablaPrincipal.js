
//mostrar tabla principal
function mostrarTablaPrincipal() {
    
    $.ajax({
        url: "ControladorTablaPrincipal",
        data: {
            intrucion: "tabla"
        },
        success: function (result) {
            $("#window").html(result);
            history.pushState(null, "", "http://localhost:8080/DataGreenFlower/ControladorFlores#");
            
        }
    });
}
//buscar lote
function buscarLote() {
    var lote = $("#bucarPorLotes").val();
    $.ajax({
        url: "ControladorTablaPrincipal",
        data: {
            intrucion: "buscarLote",
            lote: lote
        },
        success: function (result) {
            $("#resultadosBusquedaLote").html(result);
        }
    });
}
// editar lote 
function editarLote(lote) {
    var lote = lote;
    $.ajax({
        url: "ControladorTablaPrincipal",
        data: {
            intrucion: "editarLote",
            lote: lote
        },
        success: function (result) {
            $("#window").html(result);
            fechaEditarLote(lote);

        }
    });
}
// buscar especie
function buscarVariedad() {
    var especie = $("#flor-filter").val();
    
    $.ajax({
        url: "ControladorTablaPrincipal",
        data: {
            intrucion: "variedadFiltro",
            especie: especie
        },
        success: function (result) {
            $("#select-varidades").html(result);
            

        }
    });
}
//aplicar filtros 
function filter() {
    
    var color = document.getElementsByClassName("valor-filter-color")[0].value;
    var flor = document.getElementsByClassName("valor-filter-flor")[0].value;
    var proveedor = 0;//document.getElementsByClassName("valor-filter-proveedor")[0].value;
    var finca = document.getElementsByClassName("valor-filter-finca")[0].value;
    var fecha = document.getElementsByClassName("valor-filter-fecha")[0].value;
    if(fecha === ""){
        fecha = "";
    }
    var variedad = 0;
    try{
       variedad = document.getElementsByClassName("valor-filter-variedad")[0].value;
    }catch (error){
        
    }
    var dias = 0;
    
    
    $.ajax({
        url: "ControladorTablaPrincipal",
        data: {
            intrucion: "filtrar",
            color: color,
            flor: flor,
            proveedor: proveedor, 
            finca: finca,
            fecha: fecha, 
            variedad: variedad,
            dias: dias
        },
        success: function (result) {
            $("#resultadosBusquedaLote").html(result);
            

        }
    });
    
}