package Controladores;

import Dao.coloresDAO;
import Dao.especiesDAO;
import Modelo.FloresModelo;
import Modelo.especieModelo;
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

/**
 *
 * @author Ubits
 */
@WebServlet(name = "controladorEspecies", urlPatterns = {"/controladorEspecies"})
public class controladorEspecies extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private especiesDAO modeloEspecies;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloEspecies = new especiesDAO(miPool);
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
                System.out.println("error parametro principal controlador colores");
            }
            switch (instruccion) {
                case "mostarFormularioFlores":
                    mostrarFormularioAgregarFlorsBD(response, request);
                    break;
                case "mostarFloresDeBd":
                    mostrarFloresDeLaBD(response, request);
                    break;
                case "editraBD":
                    editarEnBD(response, request);
                    break;
                case "agregarBD":
                    agregarEnBD(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador colores");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador especies" + e);
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

    private void mostrarFormularioAgregarFlorsBD(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {

        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarFlores.jsp");
        disptcher.forward(request, response);

    }

    private void mostrarFloresDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<especieModelo> especies;
        especies = modeloEspecies.TraerEspeciesBD();

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
            for (especieModelo listaEspecies : especies) {
                out.println("<tr>");
                out.println("<td><input value='" + listaEspecies.getId() + "' type='radio' class='EditarFlor' onclick='editarFloresEnLaBD(); floresDeLaBD();' name='floresParaEditar'></td>");
                out.println("<td><input value='" + listaEspecies.getNombre() + "' type='text' id='NombreDeFlorEditado" + listaEspecies.getId() + "'></td>");
                out.println("<td><input value='" + listaEspecies.getId() + "' type='radio' onclick='formularioAgregarVariedad(); variedadesDeLaBD();' class='varidadesFlor' name='variedadesFlor'/></td>");
                out.println("</tr>");
            }
            out.println("<input type='hidden' id='parametro2' value='2'");
            out.println("</table>");

        }
    }

    private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreEditado = request.getParameter("nombreActualizado");
        especieModelo editarNombre = new especieModelo(id, nombreEditado.toUpperCase());
        String respuesta = modeloEspecies.editarNombreEnBDEspecie(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

    private void agregarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        String nombre = "";
        try {
            nombre = request.getParameter("nombre");
        } catch (Exception e) {
        }
        String respuesta = "";
        if (nombre != "") {
            especieModelo agregar = new especieModelo(nombre.toUpperCase());
            respuesta = modeloEspecies.agregarEnBDEspecie(agregar);
        } else {
            respuesta = "No ingresaste nada, en le campo de texto.";
        }
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

}
