/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.FloresModelo;
import Dao.FloresDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

/**
 *
 * @author ANDRES
 */
@WebServlet(name = "ControladorFlores", urlPatterns = {"/ControladorFlores"})

public class ControladorFlores extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private FloresDAO modeloFlores;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloFlores = new FloresDAO(miPool);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String instruccion = "";
            try {
                instruccion = request.getParameter("intrucion");
            } catch (Exception e) {

            }
            if (instruccion == null) {
                instruccion = "listar";
            }

            switch (instruccion) {

                case "listar":
                    mostar(response, request);
                    break;
                case "agregarColores"://funcion individua
                    agregarColores(request, response);
                    break;
                case "mostraColoreDeLaBD"://funcion individual
                    ColoresDeLaBD(response, request);
                    break;
                case "formularioAgreagarDegradado"://funcion individual
                    formularioDegradaso(response, request);
                    break;
                case "mostraDegradadosDeLaBD"://funcion individua
                    degradadosDeDB(response, request);
                    break;
                case "formularioAgreagarVariedad"://funcion individua
                    formularioVariedad(response, request);
                    break;
                case "editraBD"://funcion individua
                    editarEnBD(response, request);
                    break;
                case "agregarBD"://funcion individua
                    agregarEnBD(response, request);
                    break;
                case "mostrarFormularios"://funcion individual
                    mostrarFormularios(response, request);
                    break;
                case "mostarFormularioFlores"://funcion individual
                    mostrarFormularioAgregarFlorsBD(response, request);
                    break;
                case "mostarFloresDeBd"://funcion individual
                    mostrarFloresDeLaBD(response, request);
                    break;
                case "mostraVariedadesDeLaBD"://funcion individual
                    variedadesDeLaBD(response, request);
                    break;
                case "mostarFormularioFincas"://funcion individual
                    formularioFincas(response, request);
                    break;
                case "mostraFincasDeLaBD"://funcion individual
                    mostrarFincasDeLaBd(response, request);
                    break;
                case "agregarFincaBD"://funcion individual
                    agregarFincasBD(response, request);
                    break;
                case "mostarFloresDeBdFincas"://funcion individual
                    floresFincas(response, request);
                    break;
                case "mostarVariedadesDeBdFincas"://funcion individual
                    varidadesFinca(response, request);
                    break;
                case "modificarSemanas"://funcion individual
                    modificarSemanasBD(response, request);
                    break;
                case "mostarFormularioSiembra"://funcion individual
                    formularioSiembra(response, request);
                    break;
                case "mostarFormularioCalibreClasificaciones"://funcion individual
                    formularioClasificaionCalibre(response, request);
                    break;
                case "mostarCalibresDeBD"://funcion individual
                    calibresDeLaBD(response, request);
                    break;
                case "mostraClasificacionesDeLaBD"://funcion individual
                    clasificaionesDeLaBD(response, request);
                    break;
                case "cargarSelectSiembra"://funcion individual
                    selectSiembra(response, request);
                    break;
                case "cargarSelectDependientes"://funcion individual
                    selectDependientes(response, request);
                    break;
                case "calcularFecha"://funcion individual
                    calcularFecha(response, request);
                    break;
                case "tabalaContenido"://??????????????????????????????????????????????????????
                    tablaContendio(response, request);
                    break;
                case "agreagarCanastilla"://funcion individual
                    canastillas(response, request);
                    break;
                case "calcularSemana"://funcion individual
                    calcularSemanaActul(response, request);
                    break;
                case "selecFinca"://funcion individual
                    selectFinca(response, request);
                    break;
                case "mostraCodigosDeLaBD"://funcion individual
                    trarCodigosDeLaBD(response, request);
                    break;
                case "mostrarVariedadesParaCodigos"://funcion individual
                    varidadesCodigosDeLaBD(response, request);
                    break;
                case "mostrarReferenciaParaCodigos"://funcion individual
                    referenciaCodigosDeLaBD(response, request);
                    break;
                case "agregarCodigoALaBD"://funcion individual
                    agregarCodigoEnLaBD(response, request);
                    break;
                case "mostraCodigosDeLaBDGuardados"://funcion individual
                    rCodigoDeLaBD(response, request);
                    break;
                case "editarCodigoALaBD"://funcion individual
                    editarCodigoDeLaBD(response, request);
                    break;
                case "mostarFormularioProveedoresUsuairos"://funcion individual
                    formularioProveedoresUsuarios(response, request);
                    break;
                case "usuariosDeLaBD"://funcion individual
                    formularioProveedoresUsuariosBD(response, request);
                    break;
                case "agregarPersonas"://funcion individual
                    agreagarPersonas(response, request);
                    break;
                case "eliminarUsuarios"://funcion individual
                    eliminarUsuarios(response, request);
                    break;
                case "proveedoresDeLaBD"://funcion individual
                    proveedoresDeLaBD(response, request);
                    break;
                case "editarProveedores"://funcion individual
                    editarProveedores(response, request);
                    break;
                case "eliminarProveedores"://funcion individual
                    eliminarProveedores(response, request);
                    break;
                case "agregarProveedores"://funcion individual
                    agregarProveedores(response, request);
                    break;
                case "areaDisponible"://funcion individual
                    areaDisponible(response, request);
                    break;
                case "verificarQElcodigonoExista"://funcion individual
                    verificarCodigo(response, request);
                    break;
                case "codigoParaTablaDeSiembra"://funcion individual
                    codigoTabalSiembra(response, request);
                    break;
                case "seleccionDeFlorPorCodigo"://funcion individual
                    selectPorCodigo(response, request);
                    break;
                case "existencicodigo"://funcion individual
                    comprobarCodigo(response, request);
                    break;
                case "seleccionDeDependientesPorCodigo"://funcion individual
                    selectPorCodigoDe(response, request);
                    break;
                case "guardarLotesEnBd"://funcion individual
                    guardarLote(response, request);
                    break;
                case "estimadoFechaIndividual"://funcion individual
                    estimarFechas(response, request);
                    break;
                case "autoRellenaCodigo"://funcion individual
                    autoBuscarCodigo(response, request);
                    break;
                case "botonesMenuSiembra"://funcion individual
                    menuBotonesSiembra(response, request);
                    break;
                case "quitarbotonesMenuSiembra"://funcion individual
                    quitarBotones(response, request);
                    break;
                case "cerrarSesion":
                    cerrarSesion(response, request);
                    break;
                case "guardarEdision":
                    guardarDatosLoteEditados(response, request);
                    break;
                default:

                    mostar(response, request);
                    break;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorFlores.class.getName()).log(Level.SEVERE, null, ex);

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

    private void mostar(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/DataFlower.jsp");
        disptcher.forward(request, response);
    }

    private void agregarColores(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarColores.jsp");
        disptcher.forward(request, response);

    }

    private void ColoresDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> colores;
        colores = modeloFlores.TraerColoresBD();

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Edi.</th>");
            out.println("<th>Colores</th>");
            out.println("<th>Degr.</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaColores : colores) {
                out.println("<tr>");
                out.println("<td><input value='" + listaColores.getId() + "' type='radio' class='EditarColor' onclick='editarColorEnLaBD();ColoresDeLaBD();' name='colorParaEditar'></td>");
                out.println("<td><input value='" + listaColores.getNombre() + "' type='text' id='nombreEditadoColor" + listaColores.getId() + "'></td>");
                out.println("<td><input value='" + listaColores.getId() + "' type='radio' onclick='formularioAgregarDegradado();degradadosDeLaBD();' class='degradadoCol' name='degradadoColor'/></td>");
                out.println("</tr>");
            }
            out.println("<input type='hidden' id='parametro5' value='5'>");
            out.println("</table>");

        }
    }

    private void formularioDegradaso(HttpServletResponse response, HttpServletRequest request) throws SQLException, ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarDegradados.jsp");
        disptcher.forward(request, response);
    }

    private void degradadosDeDB(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int color = Integer.parseInt(request.getParameter("color"));
        String nombreVariedad = "";
        List<FloresModelo> degradados;
        FloresModelo consultarDegradados = new FloresModelo(variedad, color);
        nombreVariedad = modeloFlores.TraerNombreDelColor(consultarDegradados);
        degradados = modeloFlores.TraerDegradadosBD(consultarDegradados);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<h3>" + nombreVariedad + "</h3>");
            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Degradados  </th>");
            out.println("<th>Editar</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaDegradados : degradados) {
                out.println("<tr>");
                out.println("<td><input value='" + listaDegradados.getNombre() + "' type='text' id='idEditar" + listaDegradados.getId() + "'></td>");
                out.println("<td><input value='" + listaDegradados.getId() + "' type='radio' onclick='editarDegradadoEnLaBD();degradadosDeLaBD();' class='editarDegradado' name='degradadoEditar'/></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<input type='hidden' id='colorAgregarDegradado' value='" + variedad + "'>");
            out.println("<input type='hidden' id='parametro1' value='1'>");
        }
    }

    private void mostrarFormularioAgregarFlorsBD(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {

        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarFlores.jsp");
        disptcher.forward(request, response);

    }

    private void mostrarFloresDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> flores;
        flores = modeloFlores.TraerFloresBD();

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Edi.</th>");
            out.println("<th>FLores</th>");
            out.println("<th>Vari.</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaFlores : flores) {
                out.println("<tr>");
                out.println("<td><input value='" + listaFlores.getId() + "' type='radio' class='EditarFlor' onclick='editarFloresEnLaBD(); floresDeLaBD();' name='floresParaEditar'></td>");
                out.println("<td><input value='" + listaFlores.getNombre() + "' type='text' id='NombreDeFlorEditado" + listaFlores.getId() + "'></td>");
                out.println("<td><input value='" + listaFlores.getId() + "' type='radio' onclick='formularioAgregarVariedad(); variedadesDeLaBD();' class='varidadesFlor' name='variedadesFlor'/></td>");
                out.println("</tr>");
            }
            out.println("<input type='hidden' id='parametro2' value='2'");
            out.println("</table>");

        }
    }

    private void formularioVariedad(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarVariedades.jsp");
        disptcher.forward(request, response);
    }

    private void variedadesDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int flor = Integer.parseInt(request.getParameter("flor"));
        String nombreDelaFlor = "";
        List<FloresModelo> variedades;
        FloresModelo consultarFlor = new FloresModelo(flor);
        nombreDelaFlor = modeloFlores.TraerFlorDelColor(consultarFlor);
        variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<h3>" + nombreDelaFlor + "</h3>");
            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Editar</th>");
            out.println("<th>Variedades  </th>");
            out.println("<th>Colores</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaVariedades : variedades) {
                out.println("<tr>");
                out.println("<td><input value='" + listaVariedades.getId() + "' type='radio' onclick='editarVaridadesEnLaBD(); variedadesDeLaBD();' class='editarVariedad' name='variedad'/></td>");
                out.println("<td><input value='" + listaVariedades.getNombre() + "' type='text' id='nombreEditadoVaridad" + listaVariedades.getId() + "'></td>");
                out.println("<td><input value='" + listaVariedades.getId() + "' type='radio' onclick='coloresFormulario(); ColoresDeLaBD()' class='coloresVariedad' name='variedad'/></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<input type='hidden' id='idFLorAgregarVariedad' value='" + flor + "'>");
            out.println("<input type='hidden' id='parametro3' value='3'>");
        }
    }

    private void formularioFincas(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarFincas.jsp");
        disptcher.forward(request, response);
    }

    private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int parametro = Integer.parseInt(request.getParameter("parametroEditar"));
        int extension = Integer.parseInt(request.getParameter("extension"));
        String nombreEditado = request.getParameter("nombreActualizado");
        FloresModelo editarNombre = new FloresModelo(id, nombreEditado.toUpperCase(), parametro, extension);
        String respuesta = modeloFlores.editarNombreEnBD(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

    private void agregarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int parametro = Integer.parseInt(request.getParameter("parametroAgregar"));
        String nombre = request.getParameter("nombre");
        String respuesta = "";
        if (nombre != "") {
            FloresModelo agregar = null;
            //agregar Degradados
            int id_variedad = 0;
            int id_color = 0;
            if (parametro == 1) {
                id_variedad = Integer.parseInt(request.getParameter("variedad"));
                id_color = Integer.parseInt(request.getParameter("color"));
                agregar = new FloresModelo(id_color, parametro, nombre.toUpperCase(), id_variedad);
            } else if (parametro == 3) {
                id_color = Integer.parseInt(request.getParameter("color"));
                agregar = new FloresModelo(id_color, nombre.toUpperCase(), parametro);
            } else {
                agregar = new FloresModelo(parametro, nombre.toUpperCase());
            }
            respuesta = modeloFlores.agregarEnBD(agregar);
        } else {
            respuesta = "No ingresaste nada, en le campo de texto.";
        }
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

    private void mostrarFincasDeLaBd(HttpServletResponse response, HttpServletRequest request) throws SQLException, SQLException, IOException {
        List<FloresModelo> Fincas;
        Fincas = modeloFlores.TraerFincasBD();

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Edi.</th>");
            out.println("<th>Fincas</th>");
            out.println("<th>Extensión</th>");
            out.println("<th>Sm.</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaFincas : Fincas) {
                out.println("<tr>");
                out.println("<td><input value='" + listaFincas.getId() + "' type='radio' class='EditarFinca' onclick='editarFincasEnLaBD();fincasDeLaBD();' name='fincaParaEditar'></td>");
                out.println("<td><input value='" + listaFincas.getNombre() + "' type='text' id='nombreEditadoFinca" + listaFincas.getId() + "'></td>");
                out.println("<td><input value='" + listaFincas.getParametro() + "' type='number' id='extensionEditadoFinca" + listaFincas.getId() + "'></td>");
                out.println("<td><input value='" + listaFincas.getId() + "' type='radio' onclick='floresDeLaBDFinca()' class='fincasSemanas' name='fincaIdSemanas'/></td>");
                out.println("</tr>");
            }
            out.println("<input type='hidden' id='parametro4' value='4'>");
            out.println("</table>");

        }
    }

    private void agregarFincasBD(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String finca = null;
        int extencion = 0;
        String estadoDelProceso = "";
        try {
            finca = request.getParameter("finca");
            extencion = Integer.parseInt(request.getParameter("extencion"));

        } catch (Exception e) {
        }
        if (finca != "") {
            FloresModelo AgregarFinca = new FloresModelo(extencion, finca.toUpperCase());
            try {
                estadoDelProceso = modeloFlores.insertarFincaBD(AgregarFinca);

            } catch (Exception e) {
            }

            try ( PrintWriter out = response.getWriter()) {
                out.println("<h5 class='respuestaCrearColor'>La flor " + estadoDelProceso + " </h5>");
            }

        } else {
            try ( PrintWriter out = response.getWriter()) {
                out.println("<h5 class='respuestaCrearColor'>Disculpa pero no ingresaste una flor</h5>");
            }
        }
    }

    private void floresFincas(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> flores;
        flores = modeloFlores.TraerFloresBD();

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<h1>Flores</h1>");
            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>FLores</th>");
            out.println("<th>Var.</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaFlores : flores) {
                out.println("<tr>");
                out.println("<td>" + listaFlores.getNombre() + "</td>");
                out.println("<td><input value='" + listaFlores.getId() + "' type='radio' onclick='varidadesDeLaBDFinca()' class='varidadesFlor' name='degradadoColorF'/></td>");
                out.println("</tr>");
            }
            out.println("<input type='hidden' id='parametro2' value='2'");
            out.println("</table>");

        }
    }

    private void varidadesFinca(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        try {
            int flor = Integer.parseInt(request.getParameter("flor"));
            String nombreDelaFlor = "";
            List<FloresModelo> variedades;
            int semanas = 0;
            int finca = Integer.parseInt(request.getParameter("finca"));

            FloresModelo consultarFlor = new FloresModelo(flor);
            nombreDelaFlor = modeloFlores.TraerFlorDelColor(consultarFlor);
            variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                out.println("<h3>" + nombreDelaFlor + "</h3>");
                out.println("<table class='tablasInoformacion'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Variedades  </th>");
                out.println("<th>Sm+</th>");
                out.println("<th>Semanas</th>");
                out.println("</tr>");
                out.println("</thead>");
                for (FloresModelo listaVariedades : variedades) {
                    FloresModelo consultarSemanas = new FloresModelo(listaVariedades.getId(), finca);
                    semanas = modeloFlores.TraerSemanas(consultarSemanas);
                    out.println("<tr>");
                    out.println("<td><input value='" + listaVariedades.getNombre() + "' type='text' id='nombreEditadoVaridad" + listaVariedades.getId() + "'></td>");
                    out.println("<td><input value='" + listaVariedades.getId() + "' type='radio' onclick='AgregarSemanaAvaridadesDeLaBDFinca(); variedadesDeLaBD();' class='anadirSemanas' name='variedad'/></td>");
                    out.println("<td><input value='" + semanas + "' type='number' id='semasPorVaridad" + listaVariedades.getId() + "'></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<input type='hidden' id='florAgregarVariedad' value='" + flor + "'>");
                out.println("<input type='hidden' id='parametro3' value='3'>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void modificarSemanasBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int varidad = Integer.parseInt(request.getParameter("variedad"));
        int finca = Integer.parseInt(request.getParameter("finca"));
        int numeroDeSemanas = Integer.parseInt(request.getParameter("semanas"));

        String estadoDelProceso = "";

        FloresModelo AgregarSemanas = new FloresModelo(varidad, numeroDeSemanas, finca);
        estadoDelProceso = modeloFlores.insertarSemanasBD(AgregarSemanas);

        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuestaCrearColor'>" + estadoDelProceso + " </h5>");
        }

    }

    private void formularioSiembra(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/Siembra.jsp");
        disptcher.forward(request, response);
    }

    private void mostrarFormularios(HttpServletResponse response, HttpServletRequest request) {

    }

    private void formularioClasificaionCalibre(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarClasificacionesCalibres.jsp");
        disptcher.forward(request, response);
    }

    private void calibresDeLaBD(HttpServletResponse response, HttpServletRequest request) {
        try {
            List<FloresModelo> calibres;
            calibres = modeloFlores.TraerCalibresBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                out.println("<table class='tablasInoformacion'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Edi.</th>");
                out.println("<th>Calibres</th>");
                out.println("</tr>");
                out.println("</thead>");
                for (FloresModelo listaCalibres : calibres) {
                    out.println("<tr>");
                    out.println("<td><input value='" + listaCalibres.getId() + "' type='radio' onclick='editarCalibreEnLaBD(); calibresDeLaBD();' class='anadirCalibre' name='variedad'/></td>");
                    out.println("<td><input value='" + listaCalibres.getNombre() + "' type='text' id='nombreEditadoCalibre" + listaCalibres.getId() + "'></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<input type='hidden' id='parametro6' value='6'>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void clasificaionesDeLaBD(HttpServletResponse response, HttpServletRequest request) {
        try {
            List<FloresModelo> clasifiaciones;
            clasifiaciones = modeloFlores.TraerClasificacionesBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                out.println("<table class='tablasInoformacion'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Edi.</th>");
                out.println("<th>Clasificaciones</th>");
                out.println("</tr>");
                out.println("</thead>");
                for (FloresModelo listaClasificaciones : clasifiaciones) {
                    out.println("<tr>");
                    out.println("<td><input value='" + listaClasificaciones.getId() + "' type='radio' onclick='editarClasificacionesEnLaBD(); clasificacionesDeLaBD();' class='ditarClasificacion' name='variedad'/></td>");
                    out.println("<td><input value='" + listaClasificaciones.getNombre() + "' type='text' id='nombreEditadoClasificacion" + listaClasificaciones.getId() + "'></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<input type='hidden' id='parametro7' value='7'>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void selectSiembra(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        List<FloresModelo> fincas;
        List<FloresModelo> flores;
        fincas = modeloFlores.TraerFincasBD();
        flores = modeloFlores.TraerFloresBD();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<select id='FincaSelecionada' onchange='calcularSemanasFecha()'>");
            out.println("<option value='0'>Finca</option>");
            for (FloresModelo fincaLi : fincas) {
                out.println("<option value='" + fincaLi.getId() + "'>" + fincaLi.getNombre() + "</option>");
            }
            out.println("</select>");

            out.println("<select id='FlorSeleccionada' onchange='variedadSelect(); calcularSemanasFecha();'>");
            out.println("<option value='0'>Flores</option>");
            for (FloresModelo floresLi : flores) {
                out.println("<option value='" + floresLi.getId() + "'>" + floresLi.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("<div id='selectVariedad'>");
            out.println("</div>");

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

                FloresModelo consultarSemanas = new FloresModelo(idVariedad, idFinca);
                int semanas = modeloFlores.TraerSemanas(consultarSemanas);
                consultarSemanas = new FloresModelo(semanas, fecha);
                String fechaEstimada = modeloFlores.TraerFechaEstimada(consultarSemanas);
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<h5>La fecha estimada de corte es: " + fechaEstimada + " - semanas preestablecidas " + semanas + "</h5>");
                }

            }

        } catch (Exception e) {

        }

    }

    private void tablaContendio(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int dispoibilidadDEGRADADOS = 0;
        List<FloresModelo> colores;
        colores = modeloFlores.TraerColoresBD();
        List<FloresModelo> degradados;
        FloresModelo consultarDegradados;
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<div>");
            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>.</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>.</td>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaColores : colores) {
                dispoibilidadDEGRADADOS = modeloFlores.consultarCantidadDeDegradados(listaColores.getId(), variedad);
                if (dispoibilidadDEGRADADOS > 0) {
                    out.println("<tr>");
                    out.println("<td><h4>" + listaColores.getNombre() + "</h4></td>");
                    out.println("</tr>");

                    consultarDegradados = new FloresModelo(variedad, listaColores.getId());
                    degradados = modeloFlores.TraerDegradadosBD(consultarDegradados);
                    for (FloresModelo listaDegradados : degradados) {

                        out.println("<tr>");
                        out.println("<td>" + listaDegradados.getNombre() + "</td>");
                        out.println("</tr>");

                    }
                }
            }
            out.println("</table>");
            out.println("</div>");
            out.println("<div id='canastilla1'>");
            out.println("</div>");

        }

    }

    private void codigoTabalSiembra(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int parametro = Integer.parseInt(request.getParameter("parametro"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
        List<FloresModelo> parametros;
        List<FloresModelo> flores;
        List<FloresModelo> variedades;
        List<FloresModelo> colores;
        List<FloresModelo> referencia;
        List<FloresModelo> calibres;
        calibres = modeloFlores.TraerCalibresBD();
        colores = modeloFlores.TraerColoresBD();
        FloresModelo consultarFlor = null;
        FloresModelo consultarDegradados = null;
        flores = modeloFlores.TraerFloresBD();
        FloresModelo codigoEnTablaSiembra = new FloresModelo(codigo);
        parametros = modeloFlores.BuscarParametrosCodigoSiembra(codigoEnTablaSiembra);

        for (FloresModelo listaCodigo : parametros) {
            if (parametro == 1) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='florCanastillaCodigo" + contadorCanastilla + "' id='florCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='" + listaCodigo.getId_flor() + "' class='inputSiembra' onkeyup='selectFlorPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerFlorSelectPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaFlores : flores) {
                        if (listaFlores.getId() == listaCodigo.getId_flor()) {
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
                    consultarFlor = new FloresModelo(listaCodigo.getId_flor());
                    variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
                    out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaCodigo.getId_variedad() + "' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaVariedades : variedades) {
                        if (listaVariedades.getId() == listaCodigo.getId_variedad()) {
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
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaCodigo.getId_color() + "' onkeyup='selectColorPorCodigo(" + contadorCanastilla + ")'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + "); referenciaSelect(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaColores : colores) {
                        if (listaColores.getId() == listaCodigo.getId_color()) {
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
                    out.println("<input type='number' name='referenciaCanastillaCodigo" + contadorCanastilla + "' id='referenciaCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' value='" + listaCodigo.getId_referencia() + "' class='inputSiembra' onkeyup='selectReferenciaPorCodigo(" + contadorCanastilla + ")'>");
                    out.println("<div id='establecerSelectReferenciaPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + ")'>");
                    consultarDegradados = new FloresModelo(listaCodigo.getId_variedad(), listaCodigo.getId_color());
                    referencia = modeloFlores.TraerDegradadosBD(consultarDegradados);
                    for (FloresModelo listaReferencias : referencia) {
                        if (listaCodigo.getId_referencia() == listaReferencias.getId()) {
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
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + listaCodigo.getId_calibre() + "' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + ")'>");
                    for (FloresModelo listaCalibres : calibres) {
                        if (listaCodigo.getId_calibre() == listaCalibres.getId()) {
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

    private void canastillas(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        try {
            int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
            String fecha = request.getParameter("fecha");
            List<FloresModelo> colores;
            colores = modeloFlores.TraerColoresBD();
            List<FloresModelo> flores;
            flores = modeloFlores.TraerFloresBD();
            List<FloresModelo> calibres;
            calibres = modeloFlores.TraerCalibresBD();
            List<FloresModelo> proveedores;
            proveedores = modeloFlores.getProveedores();

            FloresModelo consultarDegradados;
            FloresModelo generaCodigoDeLoe = new FloresModelo(fecha);

            String CogiodeLoted = modeloFlores.generacionDeCodigoLote(generaCodigoDeLoe);
            String fincaD = request.getParameter("finca");
            CogiodeLoted = CogiodeLoted + fincaD;
            response.setContentType("text/html;charset=UTF-8");
            if (contadorCanastilla > 1) {
                List<FloresModelo> variedades;
                List<FloresModelo> referencias;
                int flor = Integer.parseInt(request.getParameter("flor"));
                
                int variedad = Integer.parseInt(request.getParameter("varidad"));
                
                int color = Integer.parseInt(request.getParameter("color"));
                
                int referencia = Integer.parseInt(request.getParameter("referencia"));
                
                int calibre = Integer.parseInt(request.getParameter("calibre"));
                
                int proveedor = Integer.parseInt(request.getParameter("proveedor"));
                
                int densidad = Integer.parseInt(request.getParameter("densidad"));
                
                int canastillasPorFila = Integer.parseInt(request.getParameter("cantidadCANASTILLAS"));
                
                int bulbuos = Integer.parseInt(request.getParameter("bulbos"));
                
                String fechaE = request.getParameter("fechaESTIMADA");
                FloresModelo consultarFlor = new FloresModelo(flor);
                variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
                FloresModelo consultarDegradadose = new FloresModelo(variedad, color);
                referencias = modeloFlores.TraerDegradadosBD(consultarDegradadose);
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
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaFlores : flores) {
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
                    for (FloresModelo listaVariedades : variedades) {
                        if (listaVariedades.getId() == variedad) {
                            out.println("<option selected value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='ColorSelectSiembra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + color + "' onkeyup='selectColorPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + "); referenciaSelect(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaColores : colores) {
                        if (listaColores.getId() == color) {
                            out.println("<option value='" + listaColores.getId() + "' selected>" + listaColores.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
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
                    for (FloresModelo listaReferencias : referencias) {
                        if (referencia == listaReferencias.getId()) {
                            out.println("<option value='" + listaReferencias.getId() + "' selected>" + listaReferencias.getNombre() + "</option>");
                        } else {
                            out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                        }
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='calibreSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='" + calibre + "' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + ");  autoRellenarCantidad(" + contadorCanastilla + ")'>");
                    for (FloresModelo listaCalibres : calibres) {
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
                    for (FloresModelo listaProveedore : proveedores) {
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
                    out.println("<td id='areaCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Area' name='areaCanastillas" + contadorCanastilla + "' id='areaCanastillas" + contadorCanastilla + "' class='inputCodigo sumaAreas' class='sumaAreas ' readonly");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosPorCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadBulbosPorCanastillas" + contadorCanastilla + "' id='cantidadBulbosPorCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();' value='" + bulbuos + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadCanastillas" + contadorCanastilla + "' id='cantidadCanastillas" + contadorCanastilla + "' class='inputCodigo sumaCanastillasE' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + ");' value='" + canastillasPorFila + "'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='CantiBul.' name='cantidadBulbos" + contadorCanastilla + "' id='cantidadBulbos" + contadorCanastilla + "' class='inputCodigo sumaBulbosTotal' readonly>");
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
                    out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");estimadoIndependiente(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaFlores : flores) {
                        out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='variedadSelectReferencia" + contadorCanastilla + "'>");
                    out.println("</td>");
                    out.println("<td id='ColorSelectSiembra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='colorCanastillaCodigo" + contadorCanastilla + "' id='colorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='1' onkeyup='selectColorPorCodigo(" + contadorCanastilla + ")'>");
                    out.println("<div id='establecerSelectColorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + "); referenciaSelect(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaColores : colores) {
                        out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='referernciaSelectSimbra" + contadorCanastilla + "'>");
                    out.println("</td>");
                    out.println("<td id='calibreSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input type='number' name='calibreCanastillaCodigo" + contadorCanastilla + "' id='calibreCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='1' onkeyup='selectCaibrePorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectCalibrePorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + "); autoRellenarCantidad(" + contadorCanastilla + ")'>");
                    for (FloresModelo listaCalibres : calibres) {
                        out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='proveedoresSelectSimbra" + contadorCanastilla + "'>");
                    out.println("<input name='proveedorCanastillaCodigo" + contadorCanastilla + "' type='number' id='proveedorCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' value='1' onkeyup='selectProveedorPorCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectProveedorPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='proveedoresCanastilla" + contadorCanastilla + "' id='proveedoresCanastilla" + contadorCanastilla + "' onclick='codigoProveedoresiaCanastilla(" + contadorCanastilla + ")'>");
                    for (FloresModelo listaProveedore : proveedores) {
                        out.println("<option value='" + listaProveedore.getId() + "'>" + listaProveedore.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                    out.println("</td>");
                    out.println("<td id='desidadCanastillaTD" + contadorCanastilla + "'>");
                    out.println("<input name='desidadCanastilla" + contadorCanastilla + "' type='number' placeholder='Desi.' id='desidadCanastilla" + contadorCanastilla + "' class='inputCodigo' onkeyup='calcualarArea(" + contadorCanastilla + "); cantidadBulbosPorCanasta(" + contadorCanastilla + ");'>");
                    out.println("</td>");
                    out.println("<td id='areaCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Area' name='areaCanastillas" + contadorCanastilla + "' id='areaCanastillas" + contadorCanastilla + "' class='inputCodigo sumaAreas'  readonly");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosPorCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadBulbosPorCanastillas" + contadorCanastilla + "' id='cantidadBulbosPorCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + "); calcularAreaTotalTabalEdit();'>");
                    out.println("</td>");
                    out.println("<td id='cantidadCanastillasTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='Canti.'  name='cantidadCanastillas" + contadorCanastilla + "' id='cantidadCanastillas" + contadorCanastilla + "' class='inputCodigo' onkeyup='cantidadBulbosPorCanasta(" + contadorCanastilla + "); calcualarArea(" + contadorCanastilla + ");'>");
                    out.println("</td>");
                    out.println("<td id='cantidadBulbosTD" + contadorCanastilla + "'>");
                    out.println("<input type='number' placeholder='CantiBul.' name='cantidadBulbos" + contadorCanastilla + "' id='cantidadBulbos" + contadorCanastilla + "' class='inputCodigo sumaBulbosTotal' readonly>");
                    out.println("</td>");
                    out.println("<td id='estimadosPara" + contadorCanastilla + "'>");
                    out.println("<div id='f" + contadorCanastilla + "'><h4>DD-MM-AAAA</h4>");
                    out.println("</td>");
                }
            }
        } catch (Exception e) {
        }
    }

    private void selectDependientes(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {

        List<FloresModelo> variedades;
        List<FloresModelo> referencias;
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
                FloresModelo consultarFlor = new FloresModelo(id);
                variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='variedadCanastillaCodigo" + contadorCanastilla + "' id='variedadCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectVariedadPorCodigo(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectVariedadPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + "); estimadoIndependiente(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaVariedades : variedades) {
                        out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    }
                    out.println("</select>");
                    out.println("</div>");
                }
            } else if (parametro == 2) {
                int variedad = Integer.parseInt(request.getParameter("variedad"));
                FloresModelo consultarDegradados = new FloresModelo(variedad, id);
                referencias = modeloFlores.TraerDegradadosBD(consultarDegradados);
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<input type='number' name='referenciaCanastillaCodigo" + contadorCanastilla + "' id='referenciaCanastillaCodigo" + contadorCanastilla + "' placeholder='-0-' class='inputSiembra' onkeyup='selectReferenciaPorCodigo(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    out.println("<div id='establecerSelectReferenciaPorCodigoCanastilla" + contadorCanastilla + "'>");
                    out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + "); AutoCompletarCodigo(" + contadorCanastilla + ");'>");
                    for (FloresModelo listaReferencias : referencias) {
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

    private void calcularSemanaActul(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        String fecha = request.getParameter("fecha");
        FloresModelo calcularSemana = new FloresModelo(fecha);
        fecha = modeloFlores.CalcualarSemana(calcularSemana);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<h4>" + fecha + "</h4>");
        }
    }

    private void selectFinca(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> fincas;
        fincas = modeloFlores.TraerFincasBD();
        int codigoFinca = 0;
        try {
            codigoFinca = Integer.parseInt(request.getParameter("codigoFinca"));
            
        } catch (Exception e) {
            System.out.println("el erroe es: " + e);
            codigoFinca = 0;
        }
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<select name='finca' id='FincaSelecionada' onclick='areaDisponible(); areaUsada(); codigoFincaSelectSiembras(); a();'>");
            out.println("<option value='0'>Finca</option>");
            for (FloresModelo fincaLi : fincas) {
                if (codigoFinca == fincaLi.getId()) {
                    out.println("<option selected value='" + fincaLi.getId() + "'>" + fincaLi.getNombre() + "</option>");
                } else {
                    out.println("<option value='" + fincaLi.getId() + "'>" + fincaLi.getNombre() + "</option>");
                }
            }

            out.println("</select>");

        }
    }

    private void trarCodigosDeLaBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        List<FloresModelo> codigos;
        codigos = modeloFlores.TraerCodigsoBD();

        List<FloresModelo> colores;
        List<FloresModelo> flores;
        List<FloresModelo> calibres;

        flores = modeloFlores.TraerFloresBD();
        calibres = modeloFlores.TraerCalibresBD();
        colores = modeloFlores.TraerColoresBD();

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Código</th>");
            out.println("<th>Flor.</th>");
            out.println("<th>Vari.</th>");
            out.println("<th>Color.</th>");
            out.println("<th>Refe.</th>");
            out.println("<th>Calibre.</th>");
            out.println("<th>Agr.</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tr>");
            out.println("<td><input type='number' class='EditarColor' id='codigoNuevo' onkeyup='verificarCodigo()' name='colorParaEditar' placeholder='Cod.'></td>");
            out.println("<td>");
            out.println("<select id='florSeleccionada' onchange='codigosVariedadesDeLaBD()'><option value='0'>Flores</option>");
            for (FloresModelo listaFlores : flores) {
                out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");

            out.println("<td id='varidadesCodigosSelect'></td>");
            out.println("<td>");
            out.println("<select id='coloresSelectCodigos' onchange='codigosReferenciaDeLaBD();'><option value='0'>Colores</option>");
            for (FloresModelo listaColores : colores) {
                out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("<td id='referenciasCodigosSelect'>");
            out.println("</td>");
            out.println("<td>");
            out.println("<select id='calibresListas'><option value='0'>Calibres</option>");
            for (FloresModelo listaCalibres : calibres) {
                out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("<td><input name='agregarcodigo' value='0' type='radio' onclick='codigosAgregarALaBD(); codigosDeLaBDGuardados();'></td>");
            out.println("</tr>");
            out.println("</table>");
        }
    }

    private void varidadesCodigosDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> variedades;
        int idFLor = Integer.parseInt(request.getParameter("flor"));
        FloresModelo consultarFlor = new FloresModelo(idFLor);
        variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<select id='variedadesListas'><option value='0'>Variedades</option>");
            for (FloresModelo listaVariedades : variedades) {
                out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
            }
            out.println("</select>");
        }
    }

    private void referenciaCodigosDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> referencia;
        int color = Integer.parseInt(request.getParameter("color"));
        try {

            int variedad = Integer.parseInt(request.getParameter("variedad"));
            FloresModelo consultarDegradados = new FloresModelo(variedad, color);
            referencia = modeloFlores.TraerDegradadosBD(consultarDegradados);
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<select id='referenciaListas'><option value='0'>Referencia</option>");
                for (FloresModelo listaReferencia : referencia) {
                    out.println("<option value='" + listaReferencia.getId() + "'>" + listaReferencia.getNombre() + "</option>");
                }
                out.println("</select>");
            }
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<h5>Falta que selecciones la variedad.</h5>");
            }
        }

    }

    private void agregarCodigoEnLaBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        String respuesta = "Falta definir ";
        try {
            int color = Integer.parseInt(request.getParameter("color"));
            int referencia = Integer.parseInt(request.getParameter("referencia"));
            int flor = Integer.parseInt(request.getParameter("flor"));
            int variedad = Integer.parseInt(request.getParameter("variedad"));
            int calibre = Integer.parseInt(request.getParameter("calibre"));
            int codigo = 0;
            codigo = Integer.parseInt(request.getParameter("codigo"));
            if (color == 0) {
                respuesta = respuesta + "flor, ";
            } else if (referencia == 0) {
                respuesta = respuesta + "referencia, ";
            } else if (flor == 0) {
                respuesta = respuesta + "Flor, ";
            } else if (variedad == 0) {
                respuesta = respuesta + "variedad, ";
            } else if (calibre == 0) {
                respuesta = respuesta + "calibre, ";
            } else if (codigo == 0) {
                respuesta = respuesta + "codigo, ";
            } else {
                FloresModelo agregarCodigoBD = new FloresModelo(color, variedad, calibre, referencia, codigo, flor, codigo);
                respuesta = modeloFlores.crearCodigoEnLaBD(agregarCodigoBD);
            }
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<h5>" + respuesta + "</h5>");
            }
        } catch (Exception e) {
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<h5>Faltan campos</h5>");
            }
        }

    }

    private void rCodigoDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> codigos;
        List<FloresModelo> flores;
        List<FloresModelo> referencia;
        List<FloresModelo> colores;
        List<FloresModelo> calibres;
        List<FloresModelo> variedades = null;
        codigos = modeloFlores.TraerCodigsoBD();
        flores = modeloFlores.TraerFloresBD();
        colores = modeloFlores.TraerColoresBD();
        calibres = modeloFlores.TraerCalibresBD();
        FloresModelo consultarDegradados = null;
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Código</th>");
            out.println("<th>Flor.</th>");
            out.println("<th>Vari.</th>");
            out.println("<th>Color.</th>");
            out.println("<th>Refe.</th>");
            out.println("<th>Calibre.</th>");
            out.println("<th>Agr.</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (FloresModelo listaCodigos : codigos) {
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type='number' value=" + listaCodigos.getCodigo_difinido() + " id='codioEditado" + listaCodigos.getId_codigo() + "'>");
                out.println("</td>");
                out.println("<td>");
                out.println("<select id='selectFlorEditadoCodigo" + listaCodigos.getId_codigo() + "'>");
                for (FloresModelo listaFlores : flores) {

                    if (listaCodigos.getId_flor() == listaFlores.getId()) {
                        out.println("<option selected value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                        FloresModelo consultarFlor = new FloresModelo(listaFlores.getId());
                        variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
                    } else {
                        out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                    }

                }
                out.println("</select");
                out.println("</td>");
                out.println("<td id='variedadCodigosEditados'>");
                out.println("<select id='selectVariedadEditadoCodigo" + listaCodigos.getId_codigo() + "'>");
                for (FloresModelo listaVariedades : variedades) {
                    if (listaCodigos.getId_variedad() == listaVariedades.getId()) {
                        out.println("<option selected value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.println("<select id='selectColordEditadoCodigo" + listaCodigos.getId_codigo() + "'>");
                for (FloresModelo listaColores : colores) {
                    if (listaCodigos.getId_color() == listaColores.getId()) {
                        out.println("<option selected value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");

                out.println("<td id='referenciasCodigosEditados'>");
                out.println("<select id='selectReferenciaEditadoCodigo" + listaCodigos.getId_codigo() + "'>");
                consultarDegradados = new FloresModelo(listaCodigos.getId_variedad(), listaCodigos.getId_color());
                referencia = modeloFlores.TraerDegradadosBD(consultarDegradados);
                for (FloresModelo listaReferencias : referencia) {
                    if (listaCodigos.getId_referencia() == listaReferencias.getId()) {
                        out.println("<option selected value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");

                out.println("<td>");
                out.println("<select id='selectCalibreEditadoCodigo" + listaCodigos.getId_codigo() + "'>");
                for (FloresModelo listaCalibres : calibres) {
                    if (listaCodigos.getId_referencia() == listaCalibres.getId()) {
                        out.println("<option selected value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type='radio' name='editarCogigos' value='" + listaCodigos.getId_codigo() + "' onclick='editarCodigosEnLaBD(); codigosVariedadesDeLaBD();' class='codigoEditado'>");
                out.println("</td>");
                out.println("</tr>");

            }
            out.println("</table>");
        }
    }

    private void editarCodigoDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int color = Integer.parseInt(request.getParameter("color"));
        int referencia = Integer.parseInt(request.getParameter("referencia"));
        int flor = Integer.parseInt(request.getParameter("flor"));
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int calibre = Integer.parseInt(request.getParameter("calibre"));
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int codigEditado = Integer.parseInt(request.getParameter("codigoEditado"));
        FloresModelo editarCodigos = new FloresModelo(color, variedad, calibre, referencia, codigo, flor, codigEditado);
        String respuesta = modeloFlores.editarCodigoEnBD(editarCodigos);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<h4>" + respuesta + "</h4>");
        }

    }

    private void formularioProveedoresUsuarios(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/userProveedores.jsp");
        disptcher.forward(request, response);
    }

    private void agreagarPersonas(HttpServletResponse response, HttpServletRequest request) throws SQLException {
        int id = 0;
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String documento = request.getParameter("documento");
        String contrasena = request.getParameter("contrasena");

        FloresModelo agreagregarPersonas = new FloresModelo(id, nombre.toUpperCase(), telefono, documento, contrasena);
        String respuesta = modeloFlores.agregarPersonasBD(agreagregarPersonas);
    }

    private void formularioProveedoresUsuariosBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> usuarios;
        usuarios = modeloFlores.getUsuarios();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Edi.</td>");
            out.println("<td>Nombre</td>");
            out.println("<td>Telefono</td>");
            out.println("<td>Telefono</td>");
            out.println("<td>Contraseña</td>");
            out.println("<td>Eliminar</td>");
            out.println("</tr>");
            for (FloresModelo listaUsuarios : usuarios) {
                out.println("<tr>");
                out.println("<td><input type='radio' name='editarUsuario' class='EditarUsuario' value='" + listaUsuarios.getId() + "' onclick='editarUsuarios()'</td>");
                out.println("<td><input type='text' id='nombreEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getNombre() + "'</td>");
                out.println("<td><input type='text' id='documentoEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getDocumeto() + "'</td>");
                out.println("<td><input type='text' id='telefonoEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getTelefono() + "'</td>");
                out.println("<td><input type='text' id='contrasenaEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getContrasena() + "'</td>");
                out.println("<td><input  name='eliminarUsuarios' type='radio' id='telefonoEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getId() + "' onclick='eliminarUsuarios(); usuariosDeLaBD();' class='eliminarUsuarios'></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
    }

    private void eliminarUsuarios(HttpServletResponse response, HttpServletRequest request) throws SQLException {
        int codigos = Integer.parseInt(request.getParameter("codigo"));
        FloresModelo aliminarUsuariosBD = new FloresModelo(codigos);
        modeloFlores.eliminarUsuariosDeBD(aliminarUsuariosBD);
    }

    private void proveedoresDeLaBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        List<FloresModelo> proveedores;
        proveedores = modeloFlores.getProveedores();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Edid.</td>");
            out.println("<td>Nombre</td>");
            out.println("<td>Telefono</td>");
            out.println("<td>Documento</td>");
            out.println("<td>Eliminar</td>");
            out.println("</tr>");

            for (FloresModelo listaProveedores : proveedores) {
                out.println("<tr>");
                out.println("<td><input type='radio' class='editarProveedores' value='" + listaProveedores.getId() + "' onclick='editarProveedores(), proveedoresDeLaBD()' class='inputUpadate'></td>");
                out.println("<td><input type='text' id='nombreEditadoProveedor" + listaProveedores.getId() + "' value='" + listaProveedores.getNombre() + "' class='inputUpadate'></td>");
                out.println("<td><input type='text' id='telefonoEditadoProveedor" + listaProveedores.getId() + "' value='" + listaProveedores.getTelefono() + "' class='inputUpadate'></td>");
                out.println("<td><input type='text' id='documentoEditadoProveedor" + listaProveedores.getId() + "' value='" + listaProveedores.getDocumeto() + "' ></td>");
                out.println("<td><input type='radio' id='eliminarEditadoProveedor' value='" + listaProveedores.getId() + "' class='eleminarProveedores' onclick='eliminarProveedores(); proveedoresDeLaBD();'></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

    }

    private void editarProveedores(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String documento = request.getParameter("documento");
        String contrasena = "";
        String respuesta = "";
        FloresModelo editarProveedoresBD = new FloresModelo(codigo, nombre.toUpperCase(), telefono, documento, contrasena);
        respuesta = modeloFlores.editarProveedores(editarProveedoresBD);

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
        }
    }

    private void eliminarProveedores(HttpServletResponse response, HttpServletRequest request) throws SQLException {
        int codigoProveedor = Integer.parseInt(request.getParameter("codigo"));

        FloresModelo eliminarProveedores = new FloresModelo(codigoProveedor);
        String respuesta = modeloFlores.eliminarProveedoresBD(eliminarProveedores);
    }

    private void agregarProveedores(HttpServletResponse response, HttpServletRequest request) throws SQLException {
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String documento = request.getParameter("documento");
        int id = 0;
        String contrasena = "";
        FloresModelo agregarProveedoresBD = new FloresModelo(id, nombre, telefono, documento, contrasena);
        String respuesta = modeloFlores.agregarProveedores(agregarProveedoresBD);
    }

    private void areaDisponible(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigoFinca = Integer.parseInt(request.getParameter("finca"));
        int parametro = Integer.parseInt(request.getParameter("parameter"));
     
        FloresModelo traerAreaDisponible = new FloresModelo(codigoFinca);
        float areaDisponible = modeloFlores.consultarAreaDisponible(traerAreaDisponible);
        float areaUsada = areaDisponible;
        FloresModelo traerAreaDisponible2 = new FloresModelo(areaDisponible, codigoFinca);
        float areEnUso = modeloFlores.cosultarAreaEnUsu(traerAreaDisponible2);
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

    private void verificarCodigo(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        FloresModelo verificarExistenciaDelCodigo = new FloresModelo(codigo);
        String respuesta = modeloFlores.verificarExistenciaDelCoDigo(verificarExistenciaDelCodigo);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
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
            List<FloresModelo> flores;
            flores = modeloFlores.TraerFloresBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='florCanastilla" + contadorCanastilla + "' id='florCanastilla" + contadorCanastilla + "' onclick='codigoFlorCanastilla(" + contadorCanastilla + ");'>");
                for (FloresModelo listaFlores : flores) {
                    if (codigo == listaFlores.getId()) {
                        out.println("<option  value='" + listaFlores.getId() + "' selected>" + listaFlores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }
        } else if (parametro == 2) {
            List<FloresModelo> colores;
            colores = modeloFlores.TraerColoresBD();;
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='coloresCanastilla" + contadorCanastilla + "' id='coloresCanastilla" + contadorCanastilla + "' onclick='codigoColorCanastilla(" + contadorCanastilla + ")'>");
                for (FloresModelo listaColores : colores) {
                    if (codigo == listaColores.getId()) {
                        out.println("<option value='" + listaColores.getId() + "' selected>" + listaColores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }

        } else if (parametro == 3) {
            List<FloresModelo> calibres;
            calibres = modeloFlores.TraerCalibresBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + "); autoRellenarCantidad(" + contadorCanastilla + ")'>");
                for (FloresModelo listaCalibres : calibres) {
                    if (codigo == listaCalibres.getId()) {
                        out.println("<option value='" + listaCalibres.getId() + "' selected>" + listaCalibres.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
            }

        } else if (parametro == 4) {
            List<FloresModelo> proveedores;
            proveedores = modeloFlores.getProveedores();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='calibresCanastilla" + contadorCanastilla + "' id='calibresCanastilla" + contadorCanastilla + "' onclick='codigoCalibreCanastilla(" + contadorCanastilla + ")'>");
                for (FloresModelo listaProveedores : proveedores) {
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

    private void selectPorCodigoDe(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int parametro = Integer.parseInt(request.getParameter("parametro"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        List<FloresModelo> variedades;
        List<FloresModelo> referencias;
        if (parametro == 1) {
            int id = Integer.parseInt(request.getParameter("codigoDe"));
            FloresModelo consultarFlor = new FloresModelo(id);
            variedades = modeloFlores.TraerVariedadesBD(consultarFlor);
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='variedadesCanastilla" + contadorCanastilla + "' id='variedadesCanastilla" + contadorCanastilla + "' onclick='codigoVariedadCanastilla(" + contadorCanastilla + ")'>");
                for (FloresModelo listaVariedades : variedades) {
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
            FloresModelo consultarDegradados = new FloresModelo(variedad, id);
            referencias = modeloFlores.TraerDegradadosBD(consultarDegradados);
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<select name='referenciasCanastilla" + contadorCanastilla + "' id='referenciasCanastilla" + contadorCanastilla + "' onclick='codigoReferenciaCanastilla(" + contadorCanastilla + ")'>");
                for (FloresModelo listaReferencias : referencias) {
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

    private void comprobarCodigo(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int contadorCanastilla = Integer.parseInt(request.getParameter("canastilla"));
        List<FloresModelo> parametros;
        FloresModelo codigoEnTablaSiembra = new FloresModelo(codigo);
        parametros = modeloFlores.BuscarParametrosCodigoSiembra(codigoEnTablaSiembra);

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

    private void guardarLote(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {

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
            int canastillas = Integer.parseInt(request.getParameter("cantidadCanastillas" + i));
            FloresModelo agregarLotesEnBD = new FloresModelo(fecha, color, variedad, calibre, referencia, codigo, flor, proveedor, densidad, area, bulbuos, canastillas, finca, fechaE, remision);
            String respuesta = modeloFlores.agregarLotes(agregarLotesEnBD);
        }
        mostar(response, request);

    }

    private void estimarFechas(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {
        try {
            int flor = Integer.parseInt(request.getParameter("flor"));
            int variedad = Integer.parseInt(request.getParameter("variedad"));
            int finca = Integer.parseInt(request.getParameter("finca"));
            int contadorCanastilla = Integer.parseInt(request.getParameter("contador"));
            String fecha = request.getParameter("fecha");
            String fechaEstimada = modeloFlores.estimarFechas(flor, variedad, finca, fecha);
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

    private void cerrarSesion(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {

        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/Inicio_sesion.jsp");
        disptcher.forward(request, response);

    }

    private void autoBuscarCodigo(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int flor = Integer.parseInt(request.getParameter("flor"));
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int color = Integer.parseInt(request.getParameter("color"));
        int referencia = Integer.parseInt(request.getParameter("referencia"));
        int codigo = modeloFlores.traerAutoRellenoCodigo(flor, variedad, color, referencia);
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

    private void guardarDatosLoteEditados(HttpServletResponse response, HttpServletRequest request) throws SQLException, ServletException, IOException {
        
        int cantidadDedatos = Integer.parseInt(request.getParameter("contador")) - 1;
        int agregadosUpdate = Integer.parseInt(request.getParameter("contadorUpdate"));
        int inicioConteoEliminacion = Integer.parseInt(request.getParameter("contadorUpdate")) - 1;
        int nuevosDatos = Integer.parseInt(request.getParameter("agregados"));
        String fecha = request.getParameter("fecha");
        int finca = Integer.parseInt(request.getParameter("finca"));
        int remision = Integer.parseInt(request.getParameter("remision"));
        try {
            do {
                int codigo = Integer.parseInt(request.getParameter("codigoGenarado" + agregadosUpdate));
                int flor = Integer.parseInt(request.getParameter("florCanastilla" + agregadosUpdate));
                int variedad = Integer.parseInt(request.getParameter("variedadesCanastilla" + agregadosUpdate));
                int color = Integer.parseInt(request.getParameter("coloresCanastilla" + agregadosUpdate));
                int referencia = Integer.parseInt(request.getParameter("referenciasCanastilla" + agregadosUpdate));
                int calibre = Integer.parseInt(request.getParameter("calibresCanastilla" + agregadosUpdate));
                int proveedor = Integer.parseInt(request.getParameter("proveedoresCanastilla" + agregadosUpdate));
                int densidad = Integer.parseInt(request.getParameter("desidadCanastilla" + agregadosUpdate));
                float area = Float.parseFloat(request.getParameter("areaCanastillas" + agregadosUpdate));
                int bulbuos = Integer.parseInt(request.getParameter("cantidadBulbosPorCanastillas" + agregadosUpdate));
                String fechaE = request.getParameter("fechaEstimadaCo" + agregadosUpdate);
                float canastillas = Float.parseFloat(request.getParameter("cantidadCanastillas" + agregadosUpdate));
                FloresModelo agregarLotesEnBD = new FloresModelo(fecha, color, variedad, calibre, referencia, codigo, flor, proveedor, densidad, area, bulbuos, canastillas, finca, fechaE, remision);
                String respuesta = modeloFlores.agregarLotes(agregarLotesEnBD);
                agregadosUpdate++;

            } while (agregadosUpdate <= cantidadDedatos);
        } catch (Exception e) {

        }

        for (int a = 1; a <= inicioConteoEliminacion; a++) {
            int parametro = Integer.parseInt(request.getParameter("canastillaEliminar" + a));
            if (parametro != 0) {
                FloresModelo codigoEliminar = new FloresModelo(parametro);
                modeloFlores.eliminarContenido(codigoEliminar);
            }
        }
        for (int i = 1; i <= inicioConteoEliminacion; i++) {
            try {
                int codigoLote = Integer.parseInt(request.getParameter("idContenidos" + i));
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
                FloresModelo editarLotesEnBD = new FloresModelo(fecha, color, variedad, calibre, referencia, codigo, flor, proveedor, densidad, area, bulbuos, canastillas, finca, fechaE, remision, codigoLote);
                String respuesta = modeloFlores.editarLotes(editarLotesEnBD);
            } catch (Exception e) {
                System.out.println("contador --- " + i);
            }
        }
        mostar(response, request);
    }

}
