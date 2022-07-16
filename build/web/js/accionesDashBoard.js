function openDashboardTablesEstimations() {
    $.ajax({
        url: "ControladorDashboart",
        data: {
            intrucion: "dashboard"
        },
        success: function (result) {
            $("#container_tables_dinamic").html(result);
            setTimeout(despliegeSelection(), 1000);

        }
    });
}
//duplicar el valor de la senma stard 
function asigWeekSerch() {
    let weekStard = document.getElementById("stard_week");
    weekStard = parseInt(weekStard.value);
    document.getElementById("end_week").value = weekStard;

}
//limpiar el comapol de color 
function quit_color_query() {
    let color = document.getElementById("shear_color").value = "";
}
//cargar los colores para la base 
function cargarColoresListaQueries() {
    $.ajax({
        url: "ControladorDashboart",
        data: {
            intrucion: "color_list"
        },
        success: function (result) {
            $("#listanavegadores").html(result);
        }
    });
}
//hacer queries 
function cargarColoresListaQueries() {

    let color = document.getElementById("shear_color");
    let stardWeek = document.getElementById("stard_week");
    let endWeek = document.getElementById("end_week");
    color = color.value;
    stardWeek = stardWeek.value;
    endWeek = endWeek.value;
    if (stardWeek == 0) {
        stardWeek = 0;
        endWeek = 0;
    }

    $.ajax({
        url: "ControladorDashboart",
        data: {
            intrucion: "sherch_for_color",
            color: color,
            weekEnd: endWeek,
            weekStard: stardWeek
        },
        success: function (result) {
            $("#container_tables_dinamic").html(result);
            setTimeout(despliegeSelection(), 1000);
        }
    });
}
//detalles dashboart
//agregar color panel 
function addPanelDetails() {
    document.querySelector('.add-details').classList.add('active');
}
// to show details lote
function showDetails(data, week) {
    let details = "<table><tr id='title-details-dash'><td>Finca</td><td>Lote</td><td>Número de bulbos</td><td> Número de canastillas</td><td> Especie</td><td> Variedad</td></tr>";
    let title = document.getElementById('title_week_deatils');
    title.innerText = `Detalles Semana ${week}`;
    
    if (data != undefined) {
        data.forEach(register => {
            console.log(register);
            details += "<tr><td>"+register[5]+"</td><td>"+register[4]+"</td><td>" + register[0] + "</td><td>" + register[1] + " </td><td>" + register[2] + "</td><td>" + register[3] + "</td></tr>";
        });
        
    }
    let text = document.getElementById('detail_week');
    text.innerHTML = details
}