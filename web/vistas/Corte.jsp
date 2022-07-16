<div class="card table-unique">
    <div class="card-head">
        <small>Corte</small>
    </div>
    <div class="card-body">
        <div id="tablePrincipal">
            <table id="siembreTABLA" class="tabla-corte">
                <tr>
                    <td colspan="2">Lote</td>
                    <td colspan="2"><input type="number" class='input_text_agregar input_corte' name="lote" placeholder='lote' onkeyup="lipiarContenidosTablaCor(); buscarLoteCorte(); lipiarContenidosEliminar();" id="codigoLote"></td>
                    <td colspan="3" rowspan="4">

                        <div class="card-head info-corte">
                            <div class="team-head">
                                <div>
                                    <h3>Contenidos</h3>
                                    <small id="respuestaEspecieLote">Seleccionar</small>
                                    <div id="contenidos">

                                    </div>
                                </div>

                            </div>
                        </div>

                    </td>
                    <td colspan="2">Fecha siembra</td>
                    <td colspan="1"><div id="fechaSiembra"></div></td>
                </tr>
                <tr>
                    <td colspan="2">Código</td>
                    <td colspan="2"><input list="codigos" type="number" class='input_text_agregar input_corte' placeholder='código'>
                        <div id="selectCodgios"></div></td>
                    <td colspan="2">Semana</td>
                    <td colspan="1"><div id="semanaSiembra"></div></td>
                </tr>
                <tr>
                    <td colspan="2">Referencia</td>
                    <td colspan="2"><div id="respuestaReferencia"></div></td>
                    <td colspan="2">#Bulbos</td>
                    <td colspan="1"><input type="number" id="numeroDebulbos" readonly></div></td>
                </tr>
                <tr>
                    <td colspan="2">Finca</td>
                    <td colspan="2"><div id="nombreFincaCorte"></div></td>
                    <td colspan="2">Saldo</td>
                    <td colspan="1"><input type="number" id="saldoInput" min="0" readonly><input type="hidden" id="saldo"></td>
                </tr>

                <tr id="head-tabla-corte">
                   
                </tr> 
                <tbody id="contend">

                </tbody>
                <tfoot id="foot">
                    
                </tfoot>
            </table>


            <input type="hidden" name="contador" id="contador" value="1">
            <input type="hidden" name="cantidadClasificaciones" id="cantidadClasificaciones" value="1">
            <input type="hidden" name="contadorUpdate" id="contadorUpdate" value="1">
            <input type="hidden" name="especieLote" id="codEspecie" value="0">
            <input type="hidden" name="contenidoLote" id="codContenido" value="0">
            <input type="hidden" name="intrucion" value="guardarCorte">
            <div id="acutalizar">
                <input type="hidden" name="respuesta" id="respuesta" value="True">
                <input type="hidden" name="agregados" id="agregados" value="0">
            </div>
            <div id="conteidosCorteActualizar">
                
            </div>
            <input type="hidden" id="contadorEliminar" name="contadorEliminar" value="">
            <div id="conEliminar">
                
            </div>
        </div>
    </div>
</div>

