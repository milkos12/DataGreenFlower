package Controladores;

import Dao.especiesDAO;
import Dao.fincasDAO;
import Dao.semanasDAO;
import Dao.variedadesDAO;
import Modelo.especieModelo;
import Modelo.fincaModelo;
import Modelo.semanaModelo;
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

@WebServlet(name = "controladorFincas", urlPatterns = {"/controladorFincas"})
public class controladorFincas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private especiesDAO modeloEspecies;
    private fincasDAO modeloFincas;
    private variedadesDAO modeloVariedades;
    private semanasDAO modeloSemanas;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloFincas = new fincasDAO(miPool);
            modeloEspecies = new especiesDAO(miPool);
            modeloVariedades = new variedadesDAO(miPool);
            modeloSemanas = new semanasDAO(miPool);
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
           
            try {
                instruccion = request.getParameter("intrucion");
            } catch (Exception e) {
                System.out.println("error parametro principal controlador colores");
            }
            switch (instruccion) {
                case "mostarFormularioFincas":
                    formularioFincas(response, request);
                    break;
                case "mostraFincasDeLaBD":
                    mostrarFincasDeLaBd(response, request);
                    break;
                case "agregarFincaBD":
                    agregarFincasBD(response, request);
                    break;
                case "editraBD":
                    editarEnBD(response, request);
                    break;
                case "mostarFloresDeBdFincas":
                    floresFincas(response, request);
                    break;
                case "mostarVariedadesDeBdFincas":
                    varidadesFinca(response, request);
                    break;
                case "modificarSemanas":
                    modificarSemanasBD(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador fincas");
                    break;

            }
        } catch (Exception e) {
            
            System.out.println("error controlador fincas" + e+ "instrucicon" + instruccion);
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

    private void formularioFincas(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarFincas.jsp");
        disptcher.forward(request, response);
    }

    private void mostrarFincasDeLaBd(HttpServletResponse response, HttpServletRequest request) throws SQLException, SQLException, IOException {
        List<fincaModelo> Fincas;
        Fincas = modeloFincas.TraerFincasBD();

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
            for (fincaModelo listaFincas : Fincas) {
                out.println("<tr>");
                out.println("<td><input value='" + listaFincas.getId() + "' type='radio' class='EditarFinca' onclick='editarFincasEnLaBD();' name='fincaParaEditar'></td>");
                out.println("<td><input value='" + listaFincas.getNombre() + "' type='text' id='nombreEditadoFinca" + listaFincas.getId() + "'></td>");
                out.println("<td><input value='" + listaFincas.getExtension() + "' type='number' id='extensionEditadoFinca" + listaFincas.getId() + "'></td>");
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
            fincaModelo AgregarFinca = new fincaModelo(finca.toUpperCase(), extencion);
            try {
                estadoDelProceso = modeloFincas.insertarFincaBD(AgregarFinca);

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

    private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int parametro = Integer.parseInt(request.getParameter("parametroEditar"));
        int extension = Integer.parseInt(request.getParameter("extension"));
        String nombreEditado = request.getParameter("nombreActualizado");
        fincaModelo editarNombre = new fincaModelo(id, nombreEditado.toUpperCase(), extension);
        String respuesta = modeloFincas.editarNombreEnBD(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }
// provisional mientras se establece la gestion de las semanas de manera automatica con promedios etc...

    private void floresFincas(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<especieModelo> flores;
        flores = modeloEspecies.TraerEspeciesBD();

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
            for (especieModelo listaFlores : flores) {
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
            List<variedadModelo> variedades;
            int semanas = 0;
            int finca = Integer.parseInt(request.getParameter("finca"));

            especieModelo consultarFlor = new especieModelo(flor);
            nombreDelaFlor = modeloEspecies.traerNombreEspecie(consultarFlor);
            variedades = modeloVariedades.TraerVariedadesBD(consultarFlor);
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
                for (variedadModelo listaVariedades : variedades) {
                    semanaModelo consultarSemanas = new semanaModelo(listaVariedades.getId(), finca);
                    semanas = modeloSemanas.TraerSemanas(consultarSemanas);
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
        System.out.println("variedad" + varidad + "finca" + finca + "numero de semanas" + numeroDeSemanas);
        String estadoDelProceso = "";
        
        semanaModelo AgregarSemanas = new semanaModelo(varidad, numeroDeSemanas, finca);
        estadoDelProceso = modeloSemanas.insertarSemanasBD(AgregarSemanas);

        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuestaCrearColor'>" + estadoDelProceso + " </h5>");
        }

    }
}
