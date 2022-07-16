
<div id="clasifiaciones">
    <h1>Agregar Clasificaciones</h1>
    <input class='input_text' type='text' placeholder='Nombre del clasificacion' id='nombreClasificacion'>
    <input type='button' value='Agregar' onclick='agregarClasificacion();clasificacionesDeLaBD();'>

    <div id='respuestaClasificaciones'>
    </div>
    <div id='contenedorTablaClasificaciones'>
    </div>
</div>
<div id="calibres">
    <h1>Agregar Calibres</h1>
    <input class='input_text' type='text' placeholder='Número' id='calibre' value="0/0">
    <input type='button' value='Agregar' onclick='agregarCalibre(); calibresDeLaBD();'>

    <div id='respuestasCalibres'>
    </div>
    <div id='contenedorTablaClasifiaciones'>
    </div>
</div>
<div id="calibres">
    <h1>Agregar Códigos</h1>
    <div id="contenedorCodigos"></div>

    <div id='respuestasCodigos'>
    </div>
    <div id='contenedorTablaCodigos'>
    </div>
</div>