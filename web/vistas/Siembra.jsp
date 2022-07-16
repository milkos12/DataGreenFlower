<div class="card table-unique">
    <div class="card-head">
        <div class="team-head">
            <div>
                <h3>Registro</h3>
                <small>Data</small>
            </div>

        </div>
    </div>
    <div class="card-body">
        <div id="tablePrincipal">
            <table id="siembreTABLA">
                <tr>
                    <td colspan="3">Fecha de Siembra</td>
                    <td colspan="2"><input type="date" class="input_date_siembra" id="fecha" onchange="calcularSemana(); a();" name="fecha"></td>
                    <td colspan="3"><h2 id='comenzar'></h2></td>
                    <td colspan="3">Codigo Finca</td>
                    <td colspan="3"><input type="number" id="codigoFinca" placeholder="- 0 -" value="0" onkeyup="selectFincas();a();"></td>
                </tr>
                <tr>
                    <td colspan="3"> </td>
                    <td colspan="2">
                        <%--
                        <p onclick="coloresFormulario(); addPanelFlor();" class="open-btn" style="cursor: pointer"> Flor </p>
                        <p onclick="coloresFormulario(); addPanelProveedor();" class="open-btn" style="cursor: pointer"> Proveedor </p>
                        <p onclick="coloresFormulario(); addPanelCalibre();" class="open-btn" style="cursor: pointer"> calibre </p>--%>
                    </td>
                    <td colspan="3"><%--<p onclick="coloresFormulario(); addPanelColor();" class="open-btn" style="cursor: pointer">Color</p>
                        <p onclick="coloresFormulario(); addPanelReferencia();" class="open-btn" style="cursor: pointer">Referencia</p>
                        <p onclick="coloresFormulario(); addPanelVariedad();" class="open-btn" style="cursor: pointer"> variedad </p>--%>
                    </td>
                    <td colspan="3">Nombre de la finca</td>
                    <td colspan="3"><div id="selectFinca"></div></td>
                </tr>
                <tr>
                    <td colspan="3">Semana</td>
                    <td colspan="2"><div id="respuestaSemanas" class="respuestasfehcas"></div></td>
                    <td colspan="3"></td>
                    <td colspan="3">Area Total</td>
                    <td colspan="3" id="areaDisponible"></td>
                </tr>
                <tr>
                    <td colspan="3">N°Remisión</td>
                    <td colspan="2"><input name="remision" type="number" placeholder="-0-" id="codRmision" onclick="avisioRemision();" onchange="a();" required></td>
                    <td colspan="3"></td>
                    <td colspan="3">Area Disponible</td>
                    <td colspan="3" id="areaUsada"></td>
                </tr>
                <tr>
                    <td colspan="3"></td>
                    <td colspan="2"></td>
                    <td colspan="3"><h3 id="mensaje"></h3></td>
                    <td colspan="3">Area Para siembra</td>
                    <td colspan="3"><input type="number" id="calculoDeAreaParaSiembra" class='inputCodigo' readonly></td>
                </tr>
                <tr>
                    <td class="capos_tabla_siembra" width="5%">  Lote  </td>
                    <td class="capos_tabla_siembra" width="5%">  Código  </td>
                    <td class="capos_tabla_siembra" width="5%">  Flor  </td>
                    <td class="capos_tabla_siembra" width="5%">  Variedad  </td>
                    <td class="capos_tabla_siembra" width="5%">  Referencia  </td>
                    <td class="capos_tabla_siembra" width="5%">  Color  </td>
                    <td class="capos_tabla_siembra" width="5%">  Calibre  </td>
                    <td class="capos_tabla_siembra" width="5%">  Proveedor  </td>
                    <td class="capos_tabla_siembra" width="5%">  Densidad  </td>
                    <td class="capos_tabla_siembra" width="5%">  #Canastilla  </td>
                    <td class="capos_tabla_siembra" width="5%">  #BxCanas. </td>
                    <td class="capos_tabla_siembra" width="5%">  #Bulbos  </td>
                    <td class="capos_tabla_siembra" width="5%">  Area  </td>
                    <td class="capos_tabla_siembra" width="5%">  Estimado  </td>
                </tr> 
                <tbody id="contend">

                </tbody>
                <tfoot>
                <td class="cellFot">  </td>
                <td class="cellFot">    </td>
                <td class="cellFot">    </td> 
                <td class="cellFot">    </td>
                <td class="cellFot">    </td>
                <td class="cellFot">    </td>
                <td class="cellFot">    </td>
                <td class="cellFot">    </td>
                <td class="cellFot">    </td>
                <td class="cellTotales">  <input type="number" id="totalCanastillas" placeholder="Total" class='inputCodigo' readonly>  </td>
                <td class="cellFot">    </td>
                <td class="cellTotales">  <input type="number" id="totalBubos" placeholder="Total" class='inputCodigo' readonly>  </td>
                <td class="cellTotales">  <input type="number" id="totalArea" placeholder="Total" class='inputCodigo' readonly >    </td>


                </tfoot>
            </table>


            <input type="hidden" name="contador" id="contador" value="1">
            <input type="hidden" name="contadorUpdate" id="contadorUpdate" value="1">

            <div id="acutalizar">
                <input type="hidden" name="respuesta" id="respuesta" value="True">
                <input type="hidden" name="agregados" id="agregados" value="0">
            </div>
            <div id="nuevosRegistros">
                <input type="hidden" id="numeroMasAlto" value="0"><!--este imput controla el numero de identificacion de los registros para no repetirlos!-->


            </div>
            <div id="conEliminar">

            </div>
            <div id="existenceCodLote">

            </div>
        </div>
    </div>
</div>

