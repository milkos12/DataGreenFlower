<div class="card card-user">
    <div id="siembraContenedor" class="div_colores">
        <h1>Proveedores</h1>
        <input type="text" id="nombreProveedor" placeholder="Nombres Y Apellidos" class='input_text_agregar'>
        <input type="number" id="celularProveedor" placeholder="Número de Celular" class='input_text_agregar'>
        <input type="number" id="documentoProveedor" placeholder="Documento" class='input_text_agregar'>
        <input type="button" value="Agregar" onclick="agregarProveedores(); proveedoresDeLaBD();" class="button-agregar">
        <div id="respuestaPoveedores"></div>
        <div id="contenedorTablaProveedores"></div>
    </div>  
</div>