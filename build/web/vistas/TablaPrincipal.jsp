<div class="card team-progress">
    <div class="card-head">
        <div class="team-head">
            <div>
                <h3>Taem progress</h3>
                <small>890, 344 sales</small>
            </div>

        </div>
    </div>
    <div class="card-body">
        <table id="siembreTABLA">
            <tr>
                <td colspan="3">Fecha de Siembra</td>
                <td colspan="2"><input type="date" class="input_date_siembra" id="fecha" onchange="calcularSemana(); a();" name="fecha"><input type="button" onclick="a();" value="+" ></td>
                <td colspan="3"><h2 id='comenzar'></h2></td>
                <td colspan="3">Codigo Finca</td>
                <td colspan="3"><input type="number" id="codigoFinca" placeholder="- 0 -" value="0" onkeyup="selectFincas();a();"></td>
            </tr>
            <tr>
                <td colspan="3">Cosecha</td>
                <td colspan="2"><input type="number" placeholder="- 0 -"></td>
                <td colspan="3"></td>
                <td colspan="3">Nombre de la finca</td>
                <td colspan="3"><div id="selectFinca"></div></td>
            </tr>
            <tr>
                <td colspan="3">Semana</td>
                <td colspan="2"><div id="respuestaSemanas" class="respuestasfehcas"></div></td>
                <td colspan="3"></td>
                <td colspan="3"><h4>Area Disponible</h4></td>
                <td colspan="3" id="areaDisponible"></td>
            </tr>
            <tr>
                <td colspan="3">Estimado</td>
                <td colspan="2"><div id="respuestaSemanas" class="respuestasfehcas"></div></td>
                <td colspan="3"></td>
                <td colspan="3"><h4>Area Usada</h4></td>
                <td colspan="3" id="areaUsada"></td>
            </tr>
            <tr>
                <td colspan="3"><h3>Sembrar</h3></td>
                <td colspan="2"><input type='submit' value="guardarLotesEnBd" name="intrucion" onclick="enviarDatos()"></td>
                <td colspan="3"><h3 id="mensaje"></h3></td>
                <td colspan="3"><h4>Area Para siembra</h4></td>
                <td colspan="3"><input type="number" id="calculoDeAreaParaSiembra" class='inputCodigo' readonly></td>
            </tr>
            <tr>
                <td class="capos_tabla_siembra">  <input type="number" placeholder="Lote">  </td>
                <td class="capos_tabla_siembra">  Código  </td>
                <td class="capos_tabla_siembra">  <input type="number" placeholder="Flores"> </td><%--SELECT FLORES--%>
                <td class="capos_tabla_siembra">  Variedad  </td><%--SELECT VAIEDADES--%>
                <td class="capos_tabla_siembra">  Color  </td><%--SELECT COLORES--%>
                <td class="capos_tabla_siembra">  Referencia  </td><%--SELECT REFERENCIAS--%>
                <td class="capos_tabla_siembra">  Calibre  </td><%--SELEC COLORES--%>
                <td class="capos_tabla_siembra">  Proveedor  </td><%--PROVEEDORES--%>
                <td class="capos_tabla_siembra">  Densidad  </td><%--SELECT FUTURAS COSECHAS --%>
                <td class="capos_tabla_siembra">  Area  </td>
                <td class="capos_tabla_siembra">  #BxCanastilla  </td>
                <td class="capos_tabla_siembra">  #Canastilla  </td>
                <td class="capos_tabla_siembra">  #Bulbos  </td>
                <td class="capos_tabla_siembra">  Estimado  </td>
            </tr> 
            <tfoot>
            <td class="cellFot">    </td>
            <td class="cellFot">    </td>
            <td class="cellFot">    </td> 
            <td class="cellFot">    </td>
            <td class="cellFot">    </td>
            <td class="cellFot">    </td>
            <td class="cellFot">    </td>
            <td class="cellFot">    </td>
            <td class="cellFot">    </td>
            <td class="cellTotales">  <input type="number" id="totalArea" placeholder="Total" class='inputCodigo' readonly >    </td>
            <td class="cellFot">    </td>
            <td class="cellTotales">  <input type="number" id="totalCanastillas" placeholder="Total" class='inputCodigo' readonly>  </td>
            <td class="cellTotales">  <input type="number" id="totalBubos" placeholder="Total" class='inputCodigo' readonly>  </td>
            </tfoot>
        </table>


        <input type="hidden" name="contador" id="contador" value="1">
        <div id="acutalizar">
            <input type="hidden" name="respuesta" id="respuesta" value="True">
        </div>
    </div>
</div>


