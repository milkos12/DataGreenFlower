package Controladores;

import Dao.calibresDAO;
import Dao.codigosDAO;
import Dao.coloresDAO;
import Dao.especiesDAO;
import Dao.fincasDAO;
import Dao.proveedoresDAO;
import Dao.referenciasDAO;
import Dao.semanasDAO;
import Dao.siembraDAO;
import Dao.variedadesDAO;
import Modelo.calibreModelo;
import Modelo.codigoModelo;
import Modelo.colorModelo;
import Modelo.especieModelo;
import Modelo.fincaModelo;
import Modelo.proveedorModelo;
import Modelo.referenciaModelo;
import Modelo.semanaModelo;
import Modelo.siembreModelo;
import Modelo.variedadModelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "controladorSiembra", urlPatterns = {"/controladorSiembra"})
public class controladorSiembra extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private siembraDAO modeloSiembra;
    private coloresDAO modeloColores;
    private especiesDAO modeloEspecies;
    private fincasDAO modeloFincas;
    private variedadesDAO modeloVariedades;
    private semanasDAO modeloSemanas;
    private referenciasDAO modeloReferencias;
    private calibresDAO modeloCalibres;
    private proveedoresDAO modeloProveedores;
    private codigosDAO modeloCodigos;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloSiembra = new siembraDAO(miPool);
            modeloFincas = new fincasDAO(miPool);
            modeloEspecies = new especiesDAO(miPool);
            modeloVariedades = new variedadesDAO(miPool);
            modeloSemanas = new semanasDAO(miPool);
            modeloReferencias = new referenciasDAO(miPool);
            modeloColores = new coloresDAO(miPool);
            modeloCalibres = new calibresDAO(miPool);
            modeloProveedores = new proveedoresDAO(miPool);
            modeloCodigos = new codigosDAO(miPool);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String instruccion = "";
            try {
                instruccion = request.getParameter("intrucion");
            } catch (Exception e) {
                System.out.println("error parametro principal controlador Usuarios");
            }
            switch (instruccion) {
                case "mostarFormularioSiembra":
                    formularioSiembra(response, request);
                    break;
                case "cargarSelectSiembra"://los selects de especies colores e
                    selectSiembra(response, request);
                    break;
                case "cargarSelectDependientes"://los selects de referencias  y variedades
                    selectDependientes(response, request);
                    break;
                case "calcularFecha":
                    calcularFecha(response, request);
                    break;
                case "calcularSemana":
                    calcularSemanaActul(response, request);
                    break;
                case "selecFinca":
                    selectFinca(response, request);
                    break;
                case "areaDisponible":
                    areaDisponible(response, request);
                    break;
                case "agreagarCanastilla":
                    canastillas(response, request);
                    break;
                case "seleccionDeFlorPorCodigo":
                    selectPorCodigo(response, request);
                    break;
                case "existencicodigo":
                    comprobarCodigo(response, request);
                    break;
                case "codigoParaTablaDeSiembra":
                    codigoTabalSiembra(response, request);
                    break;
                case "seleccionDeDependientesPorCodigo":
                    selectPorCodigoDe(response, request);
                    break;
                case "estimadoFechaIndividual":
                    estimarFechas(response, request);
                    break;
                case "autoRellenaCodigo":
                    autoBuscarCodigo(response, request);
                    break;
                case "botonesMenuSiembra":
                    menuBotonesSiembra(response, request);
                    break;
                case "quitarbotonesMenuSiembra":
                    quitarBotones(response, request);
                    break;
                case "cargarSelectRerenciasNew":
                    seletectVariedForEpecies(response, request);
                    break;
                case "seleccionarColorForReferencia":
                    seletectColorForVariedad(response, request);
                    break;
                case "updatignWeek":
                    weekUpdated(response, request);
                    break;
                case "existenceCodLote":
                    existencCodLote(response, request);
                    break;
                case "selectColors":
                    selectColors(response, request);
                    break;
                case "selectVariedad":
                    selectVariedades(response, request);
                    break;
                case "selectFlor":
                    selectFlor(response, request);
                    break;
                case "selectProveedor":
                    selectProveedor(response, request);
                    break;
                case "selectCalibre":
                    selectCalibre(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador siembra");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador siembra" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void formularioSiembra(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/Siembra.jsp");
        disptcher.forward(request, response);
    }

    private void selectSiembra(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        List<fincaModelo> fincas;
        List<especieModelo> flores;
        fincas = modeloFincas.TraerFincasBD();
        flores = modeloEspecies.TraerEspeciesBD();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<select id='FincaSelecionada' onchange='calcularSemanasFecha()'>");
            out.println("<option value='0'>Finca</option>");
            for (fincaModelo fincaLi : fincas) {
                out.println("<option value='" + fincaLi.getId() + "'>" + fincaLi.getNombre() + "</option>");
            }
            out.println("</select>");

            out.println("<select id='FlorSeleccionada' onchange='variedadSelect(); calcularSemanasFecha();'>");
            out.println("<option value='0'>Flores</option>");
            for (especieModelo floresLi : flores) {
                out.println("<option value='" + floresLi.getId() + "'>" + floresLi.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("<div id='selectVariedad'>");
            out.println("</div>");

        }
    }

    private void selectDependientes(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {

        List<variedadModelo> variedades;
        List<referenciaModelo> referencias;
        int id = 0;
        int parametro = 0;
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        try {
            id = Integer.parseInt(request.getParameter("codigo"));
            parametro = Integer.parseInt(request.getParameter("parametro"));
        } catch (Exception e) {
            id = 0;
        }
        if (id != 0) {
            if (parametro == 1) {
                variedadModelo consultarFlor = new variedadModelo(id);
                variedades = modeloVariedades.TraerVariedadesBD(consultarFlor);
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + "); infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");referenciaNewFucntion(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                    for (variedadModelo listaVariedades : variedades) {
                        out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                }
            } else if (parametro == 2) {
                int variedad = Integer.parseInt(request.getParameter("variedad"));
                referenciaModelo consultarDegradados = new referenciaModelo(id, variedad);
                referencias = modeloReferencias.TraerDegradadosBD(consultarDegradados);
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='referenciaCanastillaCodigo" + contadorCanastilla + "' id='referenciaCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectReferenciaPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectReferenciaPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (referenciaModelo listaReferencias : referencias) {
                        out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                }
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<h5>Por definir.</h5>");
            }
        }
    }

    private void calcularFecha(HttpServletResponse response, HttpServletRequest request) {
        String respuesta = "";
        String fecha = "";
        int idFlor = Integer.parseInt(request.getParameter("flor"));
        int idVariedad = 0;
        try {
            idVariedad = Integer.parseInt(request.getParameter("variedad"));;
        } catch (Exception e) {
            idVariedad = 0;
        }
        int idFinca = Integer.parseInt(request.getParameter("finca"));;
        try {
            if (idFlor != 0) {

            } else {
                respuesta = " -Flor- " + respuesta;
            }
            if (request.getParameter("fecha") != "") {
                fecha = request.getParameter("fecha");

            } else {
                respuesta = " -Fehca- " + respuesta;
            }
            if (idVariedad != 0) {
            } else {
                respuesta = " -Variedad- " + respuesta;
            }
            if (idFinca > 0) {
            } else {
                respuesta = " -Finca- " + respuesta;
            }

            if (respuesta != "") {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<h5>Faltan los siguientes campos: " + respuesta + ".</h5>");
                }
            } else {

                semanaModelo consultarSemanas = new semanaModelo(idVariedad, idFinca);
                int semanas = modeloSemanas.TraerSemanas(consultarSemanas);
                consultarSemanas = new semanaModelo(semanas, fecha);
                String fechaEstimada = modeloSemanas.TraerFechaEstimada(consultarSemanas);
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<h5>La fecha estimada de corte es: " + fechaEstimada + " - semanas preestablecidas " + semanas + "</h5>");
                }

            }

        } catch (Exception e) {

        }

    }

    private void calcularSemanaActul(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        String fecha = request.getParameter("fecha");
        semanaModelo calcularSemana = new semanaModelo(fecha);
        fecha = modeloSemanas.CalcualarSemana(calcularSemana);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<h4>" + fecha + "</h4>");
        }
    }

    private void selectFinca(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<fincaModelo> fincas;
        fincas = modeloFincas.TraerFincasBD();
        int codigoFinca = 0;
        try {
            codigoFinca = Integer.parseInt(request.getParameter("codigoFinca"));
            System.out.println("contenid de la variable " + codigoFinca);
        } catch (Exception e) {
            System.out.println("el erroe es: " + e);
            codigoFinca = 0;
        }
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<select name='finca' id='FincaSelecionada' onclick='areaDisponible(); areaUsada(); codigoFincaSelectSiembras(); a(); '>");
            out.println("<option value='0'>Finca</option>");
            for (fincaModelo fincaLi : fincas) {
                if (codigoFinca == fincaLi.getId()) {
                    out.println("<option selected value='" + fincaLi.getId() + "'>" + fincaLi.getNombre() + "</option>");
                } else {
                    out.println("<option value='" + fincaLi.getId() + "'>" + fincaLi.getNombre() + "</option>");
                }
            }

            out.println("</select>");

        }
    }

    private void areaDisponible(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigoFinca = Integer.parseInt(request.getParameter("finca"));
        int parametro = Integer.parseInt(request.getParameter("parameter"));
        fincaModelo traerAreaDisponible = new fincaModelo(codigoFinca);
        float areaDisponible = modeloFincas.consultarAreaDisponible(traerAreaDisponible);
        float areaUsada = areaDisponible;
        fincaModelo traerAreaDisponible2 = new fincaModelo(codigoFinca, areaDisponible);
        float areEnUso = modeloFincas.cosultarAreaEnUsu(traerAreaDisponible2);
        if (parametro == 1) {
            //areaUsada = areaUsada - areEnUso;
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<input type='number' id='areTotalDisponible' value='" + areEnUso + "'  readonly class='inputCodigo'>");
            }
        } else if (parametro == 0) {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<input type='number' id='areTotalFinca' value='" + areaDisponible + "'  readonly class='inputCodigo'>");
            }
        }

    }

    private void canastillas(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        try {
            int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
            String fecha = request.getParameter("fecha");
            List<colorModelo> colores;
            colores = modeloColores.TraerColoresBD();
            List<especieModelo> flores;
            flores = modeloEspecies.TraerEspeciesBD();
            List<calibreModelo> calibres;
            calibres = modeloCalibres.TraerCalibresBD();
            List<proveedorModelo> proveedores;
            proveedores = modeloProveedores.getProveedores();

            referenciaModelo consultarDegradados;
            siembreModelo generaCodigoDeLoe = new siembreModelo(fecha);

            String CogiodeLoted = modeloSiembra.generacionDeCodigoLote(generaCodigoDeLoe);
            String fincaD = request.getParameter("finca");
            CogiodeLoted = CogiodeLoted + fincaD;
            response.setContentType("text/html;charset=UTF-8");
            if (contadorCanastilla > 1) {
                List<variedadModelo> variedades;
                List<referenciaModelo> referencias;

                int flor = Integer.parseInt(request.getParameter("flor"));
                int variedad = Integer.parseInt(request.getParameter("varidad"));
                int color = Integer.parseInt(request.getParameter("color"));
                int referencia = Integer.parseInt(request.getParameter("referencia"));
                int calibre = Integer.parseInt(request.getParameter("calibre"));
                int proveedor = Integer.parseInt(request.getParameter("proveedor"));
                int densidad = Integer.parseInt(request.getParameter("densidad"));
                float canastillasPorFila = Float.parseFloat(request.getParameter("cantidadCANASTILLAS"));
                int bulbuos = Integer.parseInt(request.getParameter("bulbos"));

                String fechaE = request.getParameter("fechaESTIMADA");
                variedadModelo consularVariedad = new variedadModelo(flor);
                variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);
                referenciaModelo consultarReferencia = new referenciaModelo(color, variedad);
                referencias = modeloReferencias.TraerDegradadosBD(consultarReferencia);
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<td>");
                    out.println("<input type='number' name='codigoGenarado" + contadorCanastilla + "' id='codigoGenarado" + contadorCanastilla + "' value='" + CogiodeLoted + "' class='inputCodigo' readonly=\"readonly\" requiered>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<input type='number' placeholder='cod.' name='buscarCodigo" + contadorCanastilla + "' id='buscarCodigo" + contadorCanastilla + "' onkeyup='existenciaCodigo(" + contadorCanastilla + ");' class='inputCodigo'>");
                    out.println("<div id='actualizarEstadoDeBusqueda" + contadorCanastilla + "'>");
                    out.println("<input type='hidden' id='estadoDeBusqueda" + contadorCanastilla + "' value='0' >");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='florSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='florCanastillaCodigo" + contadorCanastilla + "' id='florCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='" + flor + "' class='inputSiembra' onkeyup='selectFlorPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerFlorSelectPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");infoSelcetsVariedad(" + contadorCanastilla + ");'>");
                    for (especieModelo listaFlores : flores) {
                        if (listaFlores.getId() == flor) {
                            out.println("<option value='" + listaFlores.getId() + "' selected>" + listaFlores.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='variedadSelectReferencia" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + "); ' value='" + variedad + "'>");
                    out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                    for (variedadModelo listaVariedades : variedades) {
                        if (listaVariedades.getId() == variedad) {
                            out.println("<option selected value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='referernciaSelectSimbra" + contadorCanastilla + "'>");
                    //referencia
                    out.println("<input type='number' name='referenciaCanastillaCodigo" + contadorCanastilla + "' id='referenciaCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectReferenciaPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");' value='" + referencia + "'>");
                    out.println("<div id='establecerSelectReferenciaPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (referenciaModelo listaReferencias : referencias) {

                        out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");

                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='ColorSelectSiembra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + color + "' onkeyup='selectColorPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + "); referenciaSelect(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + "); infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                    for (colorModelo listaColores : colores) {
                        if (listaColores.getId() == color) {
                            out.println("<option value='" + listaColores.getId() + "' selected>" + listaColores.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");

                    out.println("<td id='calibreSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + calibre + "' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + ");  autoRellenarCantidad(" + contadorCanastilla + "); cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();'>");
                    for (calibreModelo listaCalibres : calibres) {
                        if (listaCalibres.getId() == calibre) {
                            out.println("<option value='" + listaCalibres.getId() + "' selected>" + listaCalibres.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='proveedoresSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input name='proveedorCanastillaCodigo" + contadorCanastilla + "' type='number' id='proveedorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + proveedor + "' onkeyup='selectProveedorPorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectProveedorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='proveedoresCanastilla" + contadorCanastilla + "' id='proveedoresCanastilla" + contadorCanastilla + "' onclick='codigoProveedoresiaCanastilla(" + contadorCanastilla + ")'>");
                    for (proveedorModelo listaProveedore : proveedores) {
                        if (listaProveedore.getId() == proveedor) {
                            out.println("<option value='" + listaProveedore.getId() + "' selected>" + listaProveedore.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaProveedore.getId() + "'>" + listaProveedore.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='desidadCanastillaTD" + contadorCanastilla + "'>");
                    out.println("<input name='desidadCanastilla" + contadorCanastilla + "' type='number' placeholder='Desi.' id='desidadCanastilla" + contadorCanastilla + "' class='inputCodigo' onkeyup='calcualarArea(" + contadorCanastilla + "); cantidadBulbosPorCanasta(" + contadorCanastilla + ");' value='" + densidad + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadCanastillas" + contadorCanastilla + "' id='cantidadCanastillas" + contadorCanastilla + "' class='inputCodigo sumaCanastillasE' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + ");' value='" + canastillasPorFila + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosPorCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadBulbosPorCanastillas" + contadorCanastilla + "' id='cantidadBulbosPorCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();' value='" + bulbuos + "'>");
                    out.println("</td>");

                    out.println("<td id='cantidadBulbosTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='CantiBul.' name='cantidadBulbos" + contadorCanastilla + "' id='cantidadBulbos" + contadorCanastilla + "' class='inputCodigo sumaBulbosTotal' readonly>");
                    out.println("</td>");

                    out.println("<td id='areaCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Area' name='areaCanastillas" + contadorCanastilla + "' id='areaCanastillas" + contadorCanastilla + "' class='inputCodigo sumaAreas' class='sumaAreas ' readonly");
                    out.println("</td>");

                    out.println("<td id='estimadosPara" + contadorCanastilla + "'>");
                    out.println("<div id='f" + contadorCanastilla + "'>");
                    out.println("<input type='hidden' name='fechaEstimadaCo" + contadorCanastilla + "' value='" + fechaE + "' id='fechaEstimadaCo" + contadorCanastilla + "' placeholder='dd-mm-aaaa' readonly>");
                    out.println("<h4>" + fechaE + "</h4>");
                    out.println("</div>");
                    out.println("</td>");

                }
            } else {
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<td>");
                    out.println("<input type='number' name='codigoGenarado" + contadorCanastilla + "' id='codigoGenarado" + contadorCanastilla + "' value='" + CogiodeLoted + "' class='inputCodigo' readonly=\"readonly\" requiered>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<input type='number' placeholder='cod.' name='buscarCodigo" + contadorCanastilla + "' id='buscarCodigo" + contadorCanastilla + "' onkeyup='existenciaCodigo(" + contadorCanastilla + ");' class='inputCodigo'>");
                    out.println("<div id='actualizarEstadoDeBusqueda" + contadorCanastilla + "'>");
                    out.println("<input type='hidden' id='estadoDeBusqueda" + contadorCanastilla + "' value='0' >");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='florSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='florCanastillaCodigo" + contadorCanastilla + "' id='florCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='' class='inputSiembra' onkeyup='selectFlorPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerFlorSelectPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");estimadoIndependiente(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");infoSelcetsVariedad(" + contadorCanastilla + ");'>");
                    for (especieModelo listaFlores : flores) {
                        out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='variedadSelectReferencia" + contadorCanastilla + "'>");
                    out.println("</td>");
                    out.println("<td id='referernciaSelectSimbra" + contadorCanastilla + "'>");
                    out.println("</td>");
                    out.println("<td id='ColorSelectSiembra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='1' onkeyup='selectColorPorCodigo(" + contadorCanastilla + ")'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");//referenciaSelect(" + contadorCanastilla + ")
                    for (colorModelo listaColores : colores) {
                        out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");

                    out.println("<td id='calibreSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='1' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + "); autoRellenarCantidad(" + contadorCanastilla + "); cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();'>");
                    for (calibreModelo listaCalibres : calibres) {
                        out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='proveedoresSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input name='proveedorCanastillaCodigo" + contadorCanastilla + "' type='number' id='proveedorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='1' onkeyup='selectProveedorPorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectProveedorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='proveedoresCanastilla" + contadorCanastilla + "' id='proveedoresCanastilla" + contadorCanastilla + "' onclick='codigoProveedoresiaCanastilla(" + contadorCanastilla + ")'>");
                    for (proveedorModelo listaProveedore : proveedores) {
                        out.println("<option value='" + listaProveedore.getId() + "'>" + listaProveedore.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='desidadCanastillaTD" + contadorCanastilla + "'>");
                    out.println("<input name='desidadCanastilla" + contadorCanastilla + "' type='number' placeholder='Desi.' id='desidadCanastilla" + contadorCanastilla + "' class='inputCodigo' onkeyup='calcualarArea(" + contadorCanastilla + "); cantidadBulbosPorCanasta(" + contadorCanastilla + ");'>");
                    out.println("</td>");
                    out.println("<td id='cantidadCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadCanastillas" + contadorCanastilla + "' id='cantidadCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + ");'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosPorCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadBulbosPorCanastillas" + contadorCanastilla + "' id='cantidadBulbosPorCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='CantiBul.' name='cantidadBulbos" + contadorCanastilla + "' id='cantidadBulbos" + contadorCanastilla + "' class='inputCodigo sumaBulbosTotal' readonly>");
                    out.println("</td>");

                    out.println("<td id='areaCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Area' name='areaCanastillas" + contadorCanastilla + "' id='areaCanastillas" + contadorCanastilla + "' class='inputCodigo sumaAreas'  readonly");
                    out.println("</td>");

                    out.println("<td id='estimadosPara" + contadorCanastilla + "'>");
                    out.println("<div id='f" + contadorCanastilla + "'><h4>AAAA-MM-DD</h4>");
                    out.println("</td>");
                }
            }
        } catch (Exception e) {
            System.out.println("*********------- " + e);
        }
    }

    private void selectPorCodigo(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int parametro = 0;
        int codigo = 0;
        int contadorCanastilla = 0;
        try {
            codigo = Integer.parseInt(request.getParameter("codigo"));
            contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
            parametro = Integer.parseInt(request.getParameter("parametro"));
        } catch (Exception e) {
            System.out.println("----------" + e);
        }
        if (parametro == 1) {
            List<especieModelo> flores;
            flores = modeloEspecies.TraerEspeciesBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");infoSelcetsVariedad(" + contadorCanastilla + ");'>");
                for (especieModelo listaFlores : flores) {
                    if (codigo == listaFlores.getId()) {
                        out.println("<option  value='" + listaFlores.getId() + "' selected>" + listaFlores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }
        } else if (parametro == 2) {
            List<colorModelo> colores;
            colores = modeloColores.TraerColoresBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                for (colorModelo listaColores : colores) {
                    if (codigo == listaColores.getId()) {
                        out.println("<option value='" + listaColores.getId() + "' selected>" + listaColores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }

        } else if (parametro == 3) {
            List<calibreModelo> calibres;
            calibres = modeloCalibres.TraerCalibresBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + "); autoRellenarCantidad(" + contadorCanastilla + ")'>");
                for (calibreModelo listaCalibres : calibres) {
                    if (codigo == listaCalibres.getId()) {
                        out.println("<option value='" + listaCalibres.getId() + "' selected>" + listaCalibres.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }

        } else if (parametro == 4) {
            List<proveedorModelo> proveedores;
            proveedores = modeloProveedores.getProveedores();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + ")'>");
                for (proveedorModelo listaProveedores : proveedores) {
                    if (codigo == listaProveedores.getId()) {
                        out.println("<option value='" + listaProveedores.getId() + "' selected>" + listaProveedores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaProveedores.getId() + "'>" + listaProveedores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }
        }
    }

    private void comprobarCodigo(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        List<codigoModelo> parametros;
        codigoModelo codigoEnTablaSiembra = new codigoModelo(codigo);
        parametros = modeloCodigos.BuscarParametrosCodigoSiembra(codigoEnTablaSiembra);

        if (parametros.size() >= 1) {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<input type='hidden' id='estadoDeBusqueda" + contadorCanastilla + "' value='1'>");
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<input type='hidden' id='estadoDeBusqueda" + contadorCanastilla + "' value='0'>");
            }
        }
    }

    private void codigoTabalSiembra(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int parametro = Integer.parseInt(request.getParameter("parametro"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
        List<codigoModelo> parametros;
        List<especieModelo> flores;
        List<variedadModelo> variedades;
        List<colorModelo> colores;
        List<referenciaModelo> referencia;
        List<calibreModelo> calibres;
        calibres = modeloCalibres.TraerCalibresBD();
        colores = modeloColores.TraerColoresBD();
        especieModelo consultarFlor = null;
        referenciaModelo consultarDegradados = null;
        flores = modeloEspecies.TraerEspeciesBD();
        codigoModelo codigoEnTablaSiembra = new codigoModelo(codigo);
        parametros = modeloCodigos.BuscarParametrosCodigoSiembra(codigoEnTablaSiembra);

        for (codigoModelo listaCodigo : parametros) {
            if (parametro == 1) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='florCanastillaCodigo" + contadorCanastilla + "' id='florCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='" + listaCodigo.getEspecie() + "' class='inputSiembra' onkeyup='selectFlorPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerFlorSelectPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");infoSelcetsVariedad(" + contadorCanastilla + ");'>");
                    for (especieModelo listaFlores : flores) {
                        if (listaFlores.getId() == listaCodigo.getEspecie()) {
                            out.println("<option selected value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");

                        } else {
                            out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                }

            } else if (parametro == 2) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    variedadModelo consularVariedad = new variedadModelo(listaCodigo.getEspecie());
                    variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);

                    out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaCodigo.getVariedad() + "' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                    for (variedadModelo listaVariedades : variedades) {
                        if (listaVariedades.getId() == listaCodigo.getVariedad()) {
                            out.println("<option value='" + listaVariedades.getId() + "' selected>" + listaVariedades.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                }
            } else if (parametro == 3) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaCodigo.getColor() + "' onkeyup='selectColorPorCodigo(" + contadorCanastilla + ")'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + "); referenciaSelect(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                    for (colorModelo listaColores : colores) {
                        if (listaColores.getId() == listaCodigo.getColor()) {
                            out.println("<option value='" + listaColores.getId() + "' selected>" + listaColores.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                }
            } else if (parametro == 4) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='referenciaCanastillaCodigo" + contadorCanastilla + "' id='referenciaCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='" + listaCodigo.getReferencia() + "' class='inputSiembra' onkeyup='selectReferenciaPorCodigo(" + contadorCanastilla + ")'>");
                    out.println("<div id='establecerSelectReferenciaPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + ")'>");

                    referenciaModelo consultarReferencia = new referenciaModelo(listaCodigo.getColor(), listaCodigo.getVariedad());
                    referencia = modeloReferencias.TraerDegradadosBD(consultarReferencia);
                    for (referenciaModelo listaReferencias : referencia) {
                        if (listaCodigo.getReferencia() == listaReferencias.getId()) {
                            out.println("<option value='" + listaReferencias.getId() + "' selected>" + listaReferencias.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                }
            } else if (parametro == 5) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaCodigo.getCalibre() + "' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + ")'>");
                    for (calibreModelo listaCalibres : calibres) {
                        if (listaCodigo.getCalibre() == listaCalibres.getId()) {
                            out.println("<option value='" + listaCalibres.getId() + "' selected>" + listaCalibres.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                }
            }
        }
    }

    private void selectPorCodigoDe(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int parametro = Integer.parseInt(request.getParameter("parametro"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        List<variedadModelo> variedades;
        List<referenciaModelo> referencias;
        if (parametro == 1) {
            int id = Integer.parseInt(request.getParameter("codigoDe"));
            variedadModelo consularVariedad = new variedadModelo(id);
            variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);

            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); infoSelcetsReferencia(" + contadorCanastilla + ");'>");
                for (variedadModelo listaVariedades : variedades) {
                    if (codigo == listaVariedades.getId()) {
                        out.println("<option value='" + listaVariedades.getId() + "' selected>" + listaVariedades.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    }

                }
                out.println("</select>");
            }
        } else if (parametro == 2) {
            int id = Integer.parseInt(request.getParameter("codigoDe"));
            int variedad = Integer.parseInt(request.getParameter("variedad"));
            referenciaModelo consultarReferencia = new referenciaModelo(id, variedad);
            referencias = modeloReferencias.TraerDegradadosBD(consultarReferencia);

            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + ")'>");
                for (referenciaModelo listaReferencias : referencias) {
                    if (codigo == listaReferencias.getId()) {
                        out.println("<option value='" + listaReferencias.getId() + "' selected>" + listaReferencias.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</div>");
            }
        }
    }

    private void estimarFechas(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {
        try {
            int flor = Integer.parseInt(request.getParameter("flor"));
            int variedad = Integer.parseInt(request.getParameter("variedad"));
            int finca = Integer.parseInt(request.getParameter("finca"));
            int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
            String fecha = request.getParameter("fecha");
            String fechaEstimada = modeloSemanas.estimarFechas(flor, variedad, finca, fecha);
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<input type='hidden' name='fechaEstimadaCo" + contadorCanastilla + "' value='" + fechaEstimada + "' id='fechaEstimadaCo" + contadorCanastilla + "' placeholder='dd-mm-aaaa' readonly>");
                out.println("<h4>" + fechaEstimada + "</h4>");
            }

        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("Esperando Codigo");
            }
            System.out.println("error---" + e);
        }

    }

    private void autoBuscarCodigo(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int flor = Integer.parseInt(request.getParameter("flor"));
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int color = Integer.parseInt(request.getParameter("color"));
        int referencia = Integer.parseInt(request.getParameter("referencia"));
        System.out.println("flor:" + flor + "variedad:" + variedad + "color" + color + "refernecia" + referencia);
        int codigo = modeloCodigos.traerAutoRellenoCodigo(flor, variedad, color, referencia);
        System.out.println("coidigo encontrado " + codigo);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(codigo);
        }
    }

    private void menuBotonesSiembra(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/barraBotonesSiembra.jsp");
        disptcher.forward(request, response);
    }

    private void quitarBotones(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("");
        }
    }

    private void seletectVariedForEpecies(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int id = 0;
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        List<referenciaModelo> referencias;
        referenciaModelo consultarDegradados = new referenciaModelo(id, variedad);
        referencias = modeloReferencias.TraerDegradadosBDNEWP(consultarDegradados);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<input type='number' name='referenciaCanastillaCodigo" + contadorCanastilla + "' id='referenciaCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectReferenciaPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
            out.println("<div id='establecerSelectReferenciaPorCodigoCanastilla" + contadorCanastilla + "'>");
            out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + "); selectedColor(" + contadorCanastilla + ");'>");
            for (referenciaModelo listaReferencias : referencias) {
                out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</div>");
        }
    }

    private void seletectColorForVariedad(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int variedad = 0;
        int id = Integer.parseInt(request.getParameter("variedad"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        List<referenciaModelo> referencias;
        referencias = modeloReferencias.TraerDegradadosColorNew(id);
        System.out.println(referencias + "nnnn");
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            for (referenciaModelo listaReferencias : referencias) {
                out.println(listaReferencias.getId());
            }
        }
    }

    private void weekUpdated(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        String fecha = request.getParameter("fecha");
        semanaModelo calcularSemana = new semanaModelo(fecha);
        fecha = modeloSemanas.CalcualarSemana(calcularSemana);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println(fecha);
        }
    }

    private void existencCodLote(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        String fecha = request.getParameter("fecha");
        String codFinca = request.getParameter("finca");

        //generator the cod lote
        siembreModelo generaCodigoDeLoe = new siembreModelo(fecha);
        String codLote = modeloSiembra.generacionDeCodigoLote(generaCodigoDeLoe);

        codLote = codLote + "" + codFinca;

        // search the cod lote
        String confirnExistenceCod = modeloSiembra.existenceCodLote(codLote);

        // send response 
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            if (confirnExistenceCod == "false") {
                out.print("<input type=\"hidden\" name=\"contadorUpdate\" id=\"existenceCodLoteInput\" value='0'>");
            } else {
                out.print("<input type=\"hidden\" name=\"contadorUpdate\" id=\"existenceCodLoteInput\" value='" + codLote + "'>");
            }
        }

    }

    private void selectColors(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));

        List<colorModelo> colores;
        colores = modeloColores.TraerColoresBD();

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + ");'>");//referenciaSelect(" + contadorCanastilla + ")
            for (colorModelo listaColores : colores) {
                out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
            }
            out.println("</select>");
        }
    }

    private void selectVariedades(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<variedadModelo> variedades;
        int id = Integer.parseInt(request.getParameter("flor"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
        variedadModelo consularVariedad = new variedadModelo(id);
        variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + "); infoSelcetsReferencia(" + contadorCanastilla + ");'>");
            out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
            out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");referenciaNewFucntion(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");'>");
            for (variedadModelo listaVariedades : variedades) {
                out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</div>");
        }
    }

    private void selectFlor(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<especieModelo> flores;
        flores = modeloEspecies.TraerEspeciesBD();

        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<input type='number' name='florCanastillaCodigo" + contadorCanastilla + "' id='florCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='' class='inputSiembra' onkeyup='selectFlorPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
            out.println("<div id='establecerFlorSelectPorCodigoCanastilla" + contadorCanastilla + "'>");
            out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");estimadoIndependiente(" + contadorCanastilla + ");infoSelcetsReferencia(" + contadorCanastilla + ");infoSelcetsVariedad(" + contadorCanastilla + ");'>");
            for (especieModelo listaFlores : flores) {
                out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
            }
            out.println("</select>");
        }
    }

    private void selectProveedor(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<proveedorModelo> proveedores;
        proveedores = modeloProveedores.getProveedores();
        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<select name='proveedoresCanastilla" + contadorCanastilla + "' id='proveedoresCanastilla" + contadorCanastilla + "' onclick='codigoProveedoresiaCanastilla(" + contadorCanastilla + ")'>");
            for (proveedorModelo listaProveedore : proveedores) {
                out.println("<option value='" + listaProveedore.getId() + "'>" + listaProveedore.getNombre() + "</option>");
            }
            out.println("</select>");
        }
    }

    private void selectCalibre(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<calibreModelo> calibres;
        calibres = modeloCalibres.TraerCalibresBD();
        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + "); autoRellenarCantidad(" + contadorCanastilla + "); cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();'>");
            for (calibreModelo listaCalibres : calibres) {
                out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
            }
            out.println("</select>");

        }

    }
}
