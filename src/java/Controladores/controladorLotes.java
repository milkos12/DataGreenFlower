package Controladores;

import Dao.calibresDAO;
import Dao.codigosDAO;
import Dao.coloresDAO;
import Dao.especiesDAO;
import Dao.fincasDAO;
import Dao.lotesDAO;
import Dao.proveedoresDAO;
import Dao.referenciasDAO;
import Dao.semanasDAO;
import Dao.siembraDAO;
import Dao.variedadesDAO;
import Modelo.calibreModelo;
import Modelo.colorModelo;
import Modelo.especieModelo;
import Modelo.loteModelo;
import Modelo.proveedorModelo;
import Modelo.referenciaModelo;
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

@WebServlet(name = "controladorLotes", urlPatterns = {"/controladorLotes"})
public class controladorLotes extends HttpServlet {

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
    private lotesDAO modeloLotes;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloLotes = new lotesDAO(miPool);
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
        String instruccion = "";
        try {
            instruccion = request.getParameter("intrucion");
        } catch (Exception e) {

        }
        if (instruccion == null) {
            instruccion = "tabla";
        }
        try {
            switch (instruccion) {
                case "guardarLotesEnBd":
                    guardarLote(response, request);
                    break;
                case "fechaDelLoteAEditar":
                    fechaDeLoteEditar(response, request);
                    break;
                case "ficaDelLoteAEditar":
                    fincaDeLoteEditar(response, request);
                    break;
                case "remisionDelLoteAEditar":
                    remisionDeLoteEditar(response, request);
                    break;
                case "contenidoDelLoteAEditar":
                    contenidoDeLoteEditar(response, request);
                    break;
                case "actualizarContador":
                    actualizarContador(response, request);
                    break;
                case "menuBotonesEditar":
                    botonesEditar(response, request);
                    break;
                case "imprimirEsquemaParaEliminarCanastillas":
                    imprimirEsquiema(response, request);
                    break;
                default:

                    System.out.println("instruccion no contemplado controlador lotes ");
                    break;

            }
        } catch (Exception e) {
            System.out.println("-Error en el switch principal-" + e);
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

    private void guardarLote(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {
        String codComporbation = request.getParameter("codigoGenarado1");
        String confirnExistenceCod = modeloSiembra.existenceCodLote(codComporbation);
        
        if (confirnExistenceCod == "false") {
            int cantidadDedatos = Integer.parseInt(request.getParameter("contador")) - 1;
            String fecha = request.getParameter("fecha");
            int finca = Integer.parseInt(request.getParameter("finca"));
            int remision = Integer.parseInt(request.getParameter("remision"));
            for (int i = 1; cantidadDedatos >= i; i++) {

                int codigo = Integer.parseInt(request.getParameter("codigoGenarado" + i));
                int flor = Integer.parseInt(request.getParameter("florCanastilla" + i));
                int variedad = Integer.parseInt(request.getParameter("variedadesCanastilla" + i));
                int color = Integer.parseInt(request.getParameter("coloresCanastilla" + i));
                int referencia = Integer.parseInt(request.getParameter("referenciasCanastilla" + i));
                int calibre = Integer.parseInt(request.getParameter("calibresCanastilla" + i));
                int proveedor = Integer.parseInt(request.getParameter("proveedoresCanastilla" + i));
                int densidad = Integer.parseInt(request.getParameter("desidadCanastilla" + i));
                float area = Float.parseFloat(request.getParameter("areaCanastillas" + i));
                int bulbuos = Integer.parseInt(request.getParameter("cantidadBulbosPorCanastillas" + i));
                String fechaE = request.getParameter("fechaEstimadaCo" + i);
                float canastillas = Float.parseFloat(request.getParameter("cantidadCanastillas" + i));
                loteModelo agregarLotesEnBD = new loteModelo(fecha, color, variedad, calibre, referencia, codigo, flor, proveedor, densidad, area, bulbuos, canastillas, finca, fechaE, remision);
                String respuesta = modeloLotes.agregarLotes(agregarLotesEnBD);
            }
        }
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/DataFlower.jsp");
        disptcher.forward(request, response);
    }

    private void fechaDeLoteEditar(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String fecha = modeloLotes.BuscarFechaLote(buscarLotes);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(fecha);
        }

    }

    private void fincaDeLoteEditar(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String finca = modeloLotes.BuscarFincaLote(buscarLotes);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(finca);
        }
    }

    private void remisionDeLoteEditar(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String remision = modeloLotes.BuscarRemisionLote(buscarLotes);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(remision);
        }
    }

    private void contenidoDeLoteEditar(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int CogiodeLoted = Integer.parseInt(request.getParameter("lote"));
        List<loteModelo> IDcontenidos;
        List<loteModelo> contenido;
        List<especieModelo> flores;
        List<colorModelo> colores;
        List<calibreModelo> calibres;
        List<proveedorModelo> proveedores;
        proveedores = modeloProveedores.getProveedores();
        calibres = modeloCalibres.TraerCalibresBD();
        flores = modeloEspecies.TraerEspeciesBD();
        colores = modeloColores.TraerColoresBD();
        IDcontenidos = modeloLotes.traerContenidos(CogiodeLoted);
        int proveedor = modeloProveedores.traerProveedor(CogiodeLoted);

        int contadorCanastilla = 1;
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            for (loteModelo listaContenidos : IDcontenidos) {
                contenido = modeloLotes.traerContenidosDescripcion(listaContenidos.getId_contenido());

                for (loteModelo litatContenidosC : contenido) {

                    List<variedadModelo> variedades;
                    List<referenciaModelo> referencias;
                    int flor = litatContenidosC.getEspecie();
                    int variedad = litatContenidosC.getVariedad();
                    int color = litatContenidosC.getColor();
                    int referencia = litatContenidosC.getReferencia();
                    int calibre = litatContenidosC.getCalibre();
                    int densidad = litatContenidosC.getDensidad();
                    float canastillasPorFila = litatContenidosC.getCanastillas();
                    int bulbuos = litatContenidosC.getBulbos();
                    
                    String fechaE = modeloSemanas.traerFechaEs(CogiodeLoted, litatContenidosC.getCodigo_defininido());//----------------------------

                    variedadModelo consularVariedad = new variedadModelo(flor);
                    variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);

                    referenciaModelo consultarReferencia = new referenciaModelo(color, variedad);
                    referencias = modeloReferencias.TraerDegradadosBD(consultarReferencia);

                    out.println("<tr id='Siembralote" + contadorCanastilla + "'>");
                    out.println("<td>");
                    out.println("<input type='hidden' name='idContenidos" + contadorCanastilla + "' value='" + litatContenidosC.getCodigo_defininido() + "'>");
                    out.println("<input type='number' name='codigoGenarado" + contadorCanastilla + "' id='codigoGenarado" + contadorCanastilla + "' value='" + CogiodeLoted + "' class='inputCodigo' readonly=\"readonly\" requiered>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<input type='number' placeholder='cod.' name='buscarCodigo" + contadorCanastilla + "' id='buscarCodigo" + contadorCanastilla + "' onkeyup='existenciaCodigo(" + contadorCanastilla + ");' class='inputCodigo' >");
                    out.println("<div id='actualizarEstadoDeBusqueda" + contadorCanastilla + "'>");
                    out.println("<input type='hidden' id='estadoDeBusqueda" + contadorCanastilla + "' value='0' >");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='florSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='florCanastillaCodigo" + contadorCanastilla + "' id='florCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='" + flor + "' class='inputSiembra' onkeyup='selectFlorPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerFlorSelectPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
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
                    out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");' value='" + variedad + "'>");
                    out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
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
                        if (referencia == listaReferencias.getId()) {
                            out.println("<option value='" + listaReferencias.getId() + "' selected>" + listaReferencias.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='ColorSelectSiembra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + color + "' onkeyup='selectColorPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + "); referenciaSelect(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
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
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + calibre + "' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + "); autoRellenarCantidad(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
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
                    out.println("<input name='proveedorCanastillaCodigo" + contadorCanastilla + "' type='number' id='proveedorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaContenidos.getProveedor() + "' onkeyup='selectProveedorPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectProveedorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='proveedoresCanastilla" + contadorCanastilla + "' id='proveedoresCanastilla" + contadorCanastilla + "' onclick='codigoProveedoresiaCanastilla(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (proveedorModelo listaProveedore : proveedores) {
                        System.out.println("##########3--------- " + listaProveedore.getId());
                        if (listaProveedore.getId() == listaContenidos.getProveedor()) {
                            out.println("<option value='" + listaProveedore.getId() + "' selected>" + listaProveedore.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaProveedore.getId() + "'>" + listaProveedore.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='desidadCanastillaTD" + contadorCanastilla + "'>");
                    out.println("<input name='desidadCanastilla" + contadorCanastilla + "' type='number' placeholder='Desi.' id='desidadCanastilla" + contadorCanastilla + "' class='inputCodigo' onkeyup='calcularAreaTotalTabalEdit(); cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();' value='" + densidad + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadCanastillas" + contadorCanastilla + "' id='cantidadCanastillas" + contadorCanastilla + "' class='inputCodigo sumaCanastillasE' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();' value='" + canastillasPorFila + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosPorCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadBulbosPorCanastillas" + contadorCanastilla + "' id='cantidadBulbosPorCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcularAreaTotalTabalEdit(); calcularAreaTotalTabal(1); calcualarArea(" + contadorCanastilla + ", 1);' value='" + bulbuos + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='CantiBul.' name='cantidadBulbos" + contadorCanastilla + "' id='cantidadBulbos" + contadorCanastilla + "' class='inputCodigo sumaBulbosTotal' readonly>");
                    out.println("</td>");
                    out.println("<td id='areaCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Area' name='areaCanastillas" + contadorCanastilla + "' id='areaCanastillas" + contadorCanastilla + "' class='inputCodigo sumaAreas'  readonly");
                    out.println("</td>");
                    out.println("<td id='estimadosPara" + contadorCanastilla + "'>");
                    out.println("<div id='f" + contadorCanastilla + "'>");
                    out.println("<input type='hidden' name='fechaEstimadaCo" + contadorCanastilla + "' value='" + fechaE + "' id='fechaEstimadaCo" + contadorCanastilla + "' placeholder='dd-mm-aaaa' readonly>");
                    out.println("<h4>" + fechaE + "</h4>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<a href='#' onclick='eliminarRegistroEspesifico(" + contadorCanastilla + "," + litatContenidosC.getCodigo_defininido() + "); calcularAreaTotalTabalEdit()' value='+' ><ion-icon name='close-outline' id='iconoBotonEliminarEspecifica'></ion-icon></a>");
                    out.println("</td>");
                    out.println("</tr>");

                    contadorCanastilla++;

                }
            }
            out.println("<input type='hidden' name='intrucion' value='guardarEdision'>");

        }

    }

    private void actualizarContador(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<loteModelo> IDcontenidos;
        int lote = Integer.parseInt(request.getParameter("lote"));
        IDcontenidos = modeloLotes.traerContenidos(lote);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println(IDcontenidos.size() + 1);
        }
    }

    private void botonesEditar(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/barraBotonesEditarSiebra.jsp");
        disptcher.forward(request, response);
    }

    private void imprimirEsquiema(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        List<loteModelo> IDcontenidos;
        int cogiodeLoted = Integer.parseInt(request.getParameter("lote"));
        IDcontenidos = modeloLotes.traerContenidos(cogiodeLoted);
        int contador = 1;
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            for (loteModelo listaIdContenidos : IDcontenidos) {
                out.println("<input type='hidden' id='canastillaEliminar" + contador + "' name='canastillaEliminar" + contador + "' value='0'>");
                contador++;
            }
        }
    }
}
