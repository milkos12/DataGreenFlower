
function cerrarSesion() {
    $.ajax({
        url: "ControladorFlores",
        data: {
            intrucion: "cerrarSesion"
        },
        success: function (result) {
            
        }
    });
}
