<div class="panel-agregar">
    <div id="colres" class="card car-insert">
        <h1>Agregar Fincas</h1>
        <input  type='text' placeholder='Nombre Finca' id='nombreFinca' class='input_text_agregar'>
        <input  type='number' placeholder='Extencion' id='extencionFinca' class='input_text_agregar'>
        <input type='button' value='Agregar' onclick='agregarFincaEnLaBD(); fincasDeLaBD();' class="button-agregar">

        <div id='respuestaAgregarFincas'>
        </div>
        <div id='contenedorTablafincas'>
        </div>
    </div>
    <div class="card car-insert">
        <div id="divSemanasFinca">
            <div id="floresSm">
            </div>
            
        </div>

    </div>
    <div class="card car-insert">
        <div id='respuestaSemanas'></div>
        <div id="variedadesSm"></div>
    </div>

</div>

