package Controladores;

import Dao.FloresDAO;
import Dao.coloresDAO;
import Modelo.FloresModelo;
import Modelo.colorModelo;
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

@WebServlet(name = "controladoColores", urlPatterns = {"/controladoColores"})
public class controladoColores extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private coloresDAO modeloColores;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloColores = new coloresDAO(miPool);
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
                case "agregarColores":
                    agregarColores(request, response);
                    break;
                case "mostraColoreDeLaBD":
                    ColoresDeLaBD(response, request);
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
            System.out.println("error controlador colores" + e);
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

    private void agregarColores(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarColores.jsp");
        disptcher.forward(request, response);

    }

    private void ColoresDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<colorModelo> colores;
        colores = modeloColores.TraerColoresBD();

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
            for (colorModelo listaColores : colores) {
                out.println("<tr>");
                out.println("<td><input value='" + listaColores.getId() + "' type='radio' class='EditarColor' onclick='editarColorEnLaBD();ColoresDeLaBD();' name='colorParaEditar'></td>");
                out.println("<td><input value='" + listaColores.getNombre() + "' type='text' id='nombreEditadoColor" + listaColores.getId() + "'></td>");
                out.println("<td><input value='" + listaColores.getId() + "' type='radio' onclick='formularioAgregarDegradado();' class='degradadoCol' name='degradadoColor'/></td>");
                out.println("</tr>");
            }
            out.println("<input type='hidden' id='parametro5' value='5'>");
            out.println("</table>");

        }
    }

    private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        String nombreEditado = request.getParameter("nombreActualizado");

        colorModelo editarNombre = new colorModelo(id, nombreEditado.toUpperCase());
        String respuesta = modeloColores.editarNombreEnBDColor(editarNombre);
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
            colorModelo agregar = new colorModelo(nombre.toUpperCase());
            respuesta = modeloColores.agregarEnBDColor(agregar);
        } else {
            respuesta = "No ingresaste nada, en le campo de texto.";
        }
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

}
