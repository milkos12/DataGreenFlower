
<div class="accordion__container">
    <div class="querys_week">
        <h1>Queries</h1>
        <h5 style="color: orange">¡Disponible con restricciones (Warning??)!</h5>
        <div class="shear_bar"><p style="display: inline-block">Que este entre las semana(s)</p>
            (<input type="number" class="weeks_serh" id="stard_week" placeholder="stard" onkeyup="asigWeekSerch();cargarColoresListaQueries();">-
            <input type="number" class="weeks_serh" id="end_week" placeholder="end" onkeyup="cargarColoresListaQueries();">)
            <p style="display: inline-block">y que el color sea</p>
            <input type="text" id="shear_color" list="listanavegadores" onkeyup="cargarColoresListaQueries();cargarColoresListaQueries();" style="display: inline-block;;">
            <datalist id="listanavegadores">
                
            </datalist>
            <ion-icon name="close-circle-outline" onkeyup="quit_color_query()" class="quit_color_query" style="cursor: pointer" ></ion-icon>
        </div>
    </div>
    <div id="container_tables_dinamic">
     

    </div>
</div>

<%--<input type="button" onclick="prueba2({name:[1,2,3,4,2,3]})">--%>

<script type="text/javascript">
    let variablePrueba = 3222222;
</script>






