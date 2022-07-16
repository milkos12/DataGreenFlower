<div class="panel-agregar">
    <div class="card car-insert">
        <div class="card-head">
            <div class="team-head">
                <div>
                    <h3>Agregar Especies</h3>
                    <small id="respuestaAgregarFlor">Respuesta</small>
                </div>

            </div>
        </div>
        <div id="accionesFlores">
            <div id="flores" class="div_flores">

                <input class='input_text_agregar' type='text' placeholder='Nombre del Flor ' id='nombreFlor'>
                <input class="button-agregar" type='button' value='Agregar' onclick='agregarFlor(); floresDeLaBD();'>

                <div id='contenedorTablaflores'>
                </div>   
            </div>
        </div>
    </div>
    <div id="variedad" class="card car-insert" ></div>
    <div id="contenerAccionesColores" class="card car-insert"></div>
    <div id="degradado" class="card car-insert"></div>
</div>