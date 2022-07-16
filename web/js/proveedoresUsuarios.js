//agregar ususarios a la BD
function agregarUsuarios() {
    var nombre = $('#nombreUsuarios').val();
    var telefono = $('#celularUsuarios').val();
    var documento = $('#documetoUsuarios').val();
    var contrasena = $('#contrasenasUsuarios').val();
    $.ajax({
        url: "controladorUsuarios",
        data: {
            intrucion: "agregarPersonas", 
            nombre:nombre,
            telefono: telefono, 
            documento: documento, 
            contrasena: contrasena
        },
        success: function (result) {
            $("#contenedorTablafincas").html(result);
        }
    });
}
//mostrar usuarios en la tabla 
function usuariosDeLaBD() {
    
    $.ajax({
        url: "controladorUsuarios",
        data: {
            intrucion: "usuariosDeLaBD"
        },
        success: function (result) {
            $("#usuariosDeLaBD").html(result);
        }
    });
}
//mostrar usuarios en la tabla 
function proveedoresDeLaBD() {
    
    $.ajax({
        url: "controladorProveedores",
        data: {
            intrucion: "proveedoresDeLaBD"
        },
        success: function (result) {
            $("#contenedorTablaProveedores").html(result);
        }
    });
}
//editar usuarios de la BD
function editarUsuarios() {
    var codigoUser = $('.EditarUsuario:checked').val();
    var nombre = $('#nombreEditadoUsuario'+codigoUser).val();
    var telefono = $('#telefonoEditadoUsuario'+codigoUser).val();
    var documento = $('#documentoEditadoUsuario'+codigoUser).val();
    var contrasena = $('#contrasenaEditadoUsuario'+codigoUser).val();
    $.ajax({
        url: "controladorUsuarios",
        data: {
            intrucion: "editarUsuarios", 
            nombre:nombre,
            telefono: telefono, 
            documento: documento, 
            contrasena: contrasena, 
            codigo: codigoUser
        },
        success: function (result) {
            $("#contenedorTablafincas").html(result);
        }
    });
}
//eliminar usuarios
function eliminarUsuarios() {
    var codigoUser = $('.eliminarUsuarios:checked').val();
    $.ajax({
        url: "controladorUsuarios",
        data: {
            intrucion: "eliminarUsuarios", 
            codigo: codigoUser
        },
        success: function (result) {
            $("#contenedorTablafincas").html(result);
        }
    });
}
//editar usuarios de la BD
function editarProveedores() {
    var codigoUser = $('.editarProveedores:checked').val();
    var nombre = $('#nombreEditadoProveedor'+codigoUser).val();
    var telefono = $('#telefonoEditadoProveedor'+codigoUser).val();
    var documento = $('#documentoEditadoProveedor'+codigoUser).val();    $.ajax({
        url: "controladorProveedores",
        data: {
            intrucion: "editarProveedores", 
            nombre:nombre,
            telefono: telefono, 
            documento: documento, 
            codigo: codigoUser
            
        },
        success: function (result) {
            $("#respuestaPoveedores").html(result);
            proveedoresDeLaBD();
        }
    });
}
//eliminar usuarios
function eliminarProveedores() {
    var codigoUser = $('.eleminarProveedores:checked').val();
    $.ajax({
        url: "controladorProveedores",
        data: {
            intrucion: "eliminarProveedores", 
            codigo: codigoUser
        },
        success: function (result) {
            $("#contenedorTablafincas").html(result);
            proveedoresDeLaBD();
        }
    });
}
//agregar ususarios a la BD
function agregarProveedores() {
    var nombre = $('#nombreProveedor').val();
    var telefono = $('#celularProveedor').val();
    var documento = $('#documentoProveedor').val();
    $.ajax({
        url: "controladorProveedores",
        data: {
            intrucion: "agregarProveedores", 
            nombre:nombre,
            telefono: telefono, 
            documento: documento
        },
        success: function (result) {
            $("#respuestaPoveedores").html(result);
            proveedoresDeLaBD(); 
        }
    });
}

