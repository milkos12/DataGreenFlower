package Controladores;

import Dao.calibresDAO;
import Dao.codigosDAO;
import Dao.coloresDAO;
import Dao.especiesDAO;
import Dao.referenciasDAO;
import Dao.variedadesDAO;
import Modelo.calibreModelo;
import Modelo.codigoModelo;
import Modelo.colorModelo;
import Modelo.especieModelo;
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

@WebServlet(name = "controladorCodigos", urlPatterns = {"/controladorCodigos"})
public class controladorCodigos extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private codigosDAO modeloCodigos;
    private coloresDAO modeloColores;
    private referenciasDAO modeloReferencias;
    private especiesDAO modeloEspecies;
    private variedadesDAO modeloVariedades;
    private calibresDAO modeloCalibres;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloCodigos = new codigosDAO(miPool);
            modeloColores = new coloresDAO(miPool);
            modeloReferencias = new referenciasDAO(miPool);
            modeloEspecies = new especiesDAO(miPool);
            modeloVariedades = new variedadesDAO(miPool);
            modeloCalibres = new calibresDAO(miPool);

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
                System.out.println("error parametro principal controlador codigos");
            }
            switch (instruccion) {
                case "mostarFormularioCodigos":
                    formularioCodigos(response, request);
                    break;
                case "mostraCodigosDeLaBD":
                    trarCodigosDeLaBD(response, request);
                    break;
                case "mostrarVariedadesParaCodigos":
                    varidadesCodigosDeLaBD(response, request);
                    break;
                case "mostrarReferenciaParaCodigos":
                    referenciaCodigosDeLaBD(response, request);
                    break;
                case "agregarCodigoALaBD":
                    agregarCodigoEnLaBD(response, request);
                    break;
                case "mostraCodigosDeLaBDGuardados":
                    rCodigoDeLaBD(response, request);
                    break;
                case "editarCodigoALaBD":
                    editarCodigoDeLaBD(response, request);
                    break;
                case "verificarQElcodigonoExista":
                    verificarCodigo(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador codigos");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador especies" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void trarCodigosDeLaBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {

        List<colorModelo> colores;
        List<especieModelo> especies;
        List<calibreModelo> calibres;

        calibres = modeloCalibres.TraerCalibresBD();
        especies = modeloEspecies.TraerEspeciesBD();
        colores = modeloColores.TraerColoresBD();

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
            out.println("<td><input type='number' class='EditarColor' id='codigoNuevo' onkeyup='verificarCodigo()' name='colorParaEditar' placeholder='Cod.' class='input_text_agregar'></td>");
            out.println("<td>");
            out.println("<select id='florSeleccionada' onchange='codigosVariedadesDeLaBD()' class='input_text_agregar'><option value='0'>Flores</option>");
            for (especieModelo listaFlores : especies) {
                out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");

            out.println("<td id='varidadesCodigosSelect'></td>");
            out.println("<td>");
            out.println("<select id='coloresSelectCodigos' onchange='codigosReferenciaDeLaBD();' class='input_text_agregar'><option value='0' class='input_text_agregar'>Colores</option>");
            for (colorModelo listaColores : colores) {
                out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("<td id='referenciasCodigosSelect'>");
            out.println("</td>");
            out.println("<td>");
            out.println("<select id='calibresListas' class='input_text_agregar'><option value='0'>Calibres</option>");
            for (calibreModelo listaCalibres : calibres) {
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
        List<variedadModelo> variedades;
        int idFLor = Integer.parseInt(request.getParameter("flor"));
        variedadModelo consultarFlor = new variedadModelo(idFLor);
        variedades = modeloVariedades.TraerVariedadesBD(consultarFlor);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<select id='variedadesListas' class='input_text_agregar'><option value='0'>Variedades</option>");
            for (variedadModelo listaVariedades : variedades) {
                out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
            }
            out.println("</select>");
        }
    }

    private void referenciaCodigosDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<referenciaModelo> referencia;
        int color = Integer.parseInt(request.getParameter("color"));
        try {

            int variedad = Integer.parseInt(request.getParameter("variedad"));
            referenciaModelo consultarDegradados = new referenciaModelo(variedad, color);
            referencia = modeloReferencias.TraerDegradadosBD(consultarDegradados);
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<select id='referenciaListas' class='input_text_agregar'><option value='0' >Referencia</option>");
                for (referenciaModelo listaReferencia : referencia) {
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
                codigoModelo agregarCodigoBD = new codigoModelo(color, variedad, calibre, referencia, flor, codigo);
                respuesta = modeloCodigos.crearCodigoEnLaBD(agregarCodigoBD);
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
        List<codigoModelo> codigos;
        List<colorModelo> colores;
        List<especieModelo> especies;
        List<calibreModelo> calibres;
        List<referenciaModelo> referencia;
        List<variedadModelo> variedades = null;

        codigos = modeloCodigos.TraerCodigsoBD();
        calibres = modeloCalibres.TraerCalibresBD();
        especies = modeloEspecies.TraerEspeciesBD();
        colores = modeloColores.TraerColoresBD();

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
            out.println("<th>Edid.</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (codigoModelo listaCodigos : codigos) {
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type='number' value=" + listaCodigos.getCodigoDefinido() + " id='codioEditado" + listaCodigos.getId() + "' class='input_text_agregar'>");
                out.println("</td>");
                out.println("<td>");
                out.println("<select id='selectFlorEditadoCodigo" + listaCodigos.getId() + "' class='input_text_agregar'>");
                for (especieModelo listaFlores : especies) {

                    if (listaCodigos.getEspecie() == listaFlores.getId()) {
                        out.println("<option selected value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                        variedadModelo consultarFlor = new variedadModelo(listaFlores.getId());
                        variedades = modeloVariedades.TraerVariedadesBD(consultarFlor);
                    } else {
                        out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
                    }

                }
                out.println("</select");
                out.println("</td>");
                out.println("<td id='variedadCodigosEditados'>");
                out.println("<select id='selectVariedadEditadoCodigo" + listaCodigos.getId() + "' class='input_text_agregar'>");
                for (variedadModelo listaVariedades : variedades) {
                    if (listaCodigos.getVariedad() == listaVariedades.getId()) {
                        out.println("<option selected value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaVariedades.getId() + "'>" + listaVariedades.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.println("<select id='selectColordEditadoCodigo" + listaCodigos.getId() + "' class='input_text_agregar'>");
                for (colorModelo listaColores : colores) {
                    if (listaCodigos.getColor() == listaColores.getId()) {
                        out.println("<option selected value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");

                out.println("<td id='referenciasCodigosEditados'>");
                out.println("<select id='selectReferenciaEditadoCodigo" + listaCodigos.getId() + "' class='input_text_agregar'>");
                referenciaModelo consultarReferencia = new referenciaModelo(listaCodigos.getVariedad(), listaCodigos.getColor());
                referencia = modeloReferencias.TraerTodsoDegradadosBD(consultarReferencia);
                for (referenciaModelo listaReferencias : referencia) {
                    if (listaCodigos.getReferencia() == listaReferencias.getId()) {
                        out.println("<option selected value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaReferencias.getId() + "'>" + listaReferencias.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");

                out.println("<td>");
                out.println("<select id='selectCalibreEditadoCodigo" + listaCodigos.getId() + "' class='input_text_agregar'>");
                for (calibreModelo listaCalibres : calibres) {
                    if (listaCodigos.getCalibre() == listaCalibres.getId()) {
                        out.println("<option selected value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    } else {
                        out.println("<option value='" + listaCalibres.getId() + "'>" + listaCalibres.getNombre() + "</option>");
                    }
                }
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type='radio' name='editarCogigos' value='" + listaCodigos.getId() + "' onclick='editarCodigosEnLaBD(); codigosVariedadesDeLaBD();' class='codigoEditado' >");
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
        codigoModelo editarCodigos = new codigoModelo(color, variedad, calibre, referencia, codigo, flor, codigEditado);
        String respuesta = modeloCodigos.editarCodigoEnBD(editarCodigos);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<h4>" + respuesta + "</h4>");
        }

    }

    private void verificarCodigo(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        codigoModelo verificarExistenciaDelCodigo = new codigoModelo(codigo);
        String respuesta = modeloCodigos.verificarExistenciaDelCoDigo(verificarExistenciaDelCodigo);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
        }
    }

    private void formularioCodigos(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("vistas/agregarCodigos.jsp");
        disptcher.forward(request, response);
    }

}
