<div id="menuSiembra" class="botonesSimbra">
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
        <a href="#"><ion-icon name="save-outline" id="iconoBoton" onclick="enviarDatos();"></ion-icon><input type='hidden' value="guardarLotesEnBd" name="intrucion"></a>
        <ion-icon id="iconoBoton" name="add-circle-outline" id="iconoBoton" onclick="a(); calcularAreaTotalTabal()"></ion-icon>
        <a href="#" onclick="eliminarFila(); calcularAreaTotalTabal()" value="+" ><ion-icon name="remove-circle-outline" id="iconoBoton"></ion-icon></a>
    </div>
</div>
