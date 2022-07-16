<div id="menuSiembra" class="botonesSimbraEdit botonesSimbra">
    <div>
        <div>
        <small class="tooltip" tool-tips='Color'><ion-icon id="iconoBoton" name="color-palette-outline" onclick="coloresFormulario(); addPanelColor();" class="open-btn" style="cursor: pointer"></ion-icon></small>
        </div>
        <div>
        <small class="tooltip" tool-tips='Referencia________'><ion-icon id="iconoBoton" name="leaf-outline" onclick="coloresFormulario(); addPanelReferencia();" class="open-btn" style="cursor: pointer"></ion-icon></small>
        </div>
        <div>
        <small class="tooltip" tool-tips='Variedad'><ion-icon id="iconoBoton" name="rose-outline" onclick="coloresFormulario(); addPanelVariedad();" class="open-btn" style="cursor: pointer"></ion-icon></small>     
        </div>
        <div>
        <small class="tooltip" tool-tips='Flor'><ion-icon id="iconoBoton" name="flower-outline" onclick="coloresFormulario(); addPanelFlor();" class="open-btn" style="cursor: pointer"></ion-icon></small>
        </div>
        <div>
        <small class="tooltip" tool-tips='Proveedor'><ion-icon id="iconoBoton" name="people-outline" onclick="coloresFormulario(); addPanelProveedor();" class="open-btn" style="cursor: pointer"></ion-icon></small>
        </div>
        <div>
        <small class="tooltip" tool-tips='Calibre'><ion-icon id="iconoBoton" name="resize-outline" onclick="coloresFormulario(); addPanelCalibre();" class="open-btn" style="cursor: pointer"></ion-icon></small>
        </div>
        <a href="#" onclick="enviarDatosEditadoLote();"><ion-icon name="save-outline" id="iconoBoton"></ion-icon><input type='hidden' value="guardarLotesEnBd" name="intrucion"></a>
        <a href="#" onclick="a(1); calcularAreaTotalTabalEdit();" value="+" ><ion-icon name="add-circle-outline" id="iconoBoton"></ion-icon></a> 
        <a href="#" onclick="eliminarFila(1); calcularAreaTotalTabalEdit();" value="+" ><ion-icon name="remove-circle-outline" id="iconoBoton"></ion-icon></a>
    </div>
</div>