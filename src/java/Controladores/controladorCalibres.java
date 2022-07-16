
package Controladores;

import Dao.calibresDAO;
import Modelo.calibreModelo;
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

@WebServlet(name = "controladorCalibres", urlPatterns = {"/controladorCalibres"})
public class controladorCalibres extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private calibresDAO modeloCalibres;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
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
                System.out.println("error parametro principal controlador calibres");
            }
            switch (instruccion) {
                case "formularioCalibres":
                    formularioCalibres(response, request);
                    break;
                case "mostarCalibresDeBD":
                    calibresDeLaBD(response, request);
                    break;
                case "agregarBD":
                    agregarEnBD(response, request);
                    break;
                case "editraBD":
                    editarEnBD(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador calibres");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador calibres" + e);
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
    private void formularioCalibres(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("vistas/agregarCalibres.jsp");
        disptcher.forward(request, response);
    }
    private void calibresDeLaBD(HttpServletResponse response, HttpServletRequest request) {
        try {
            List<calibreModelo> calibres;
            calibres = modeloCalibres.TraerCalibresBD();
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                out.println("<table>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Edi.</th>");
                out.println("<th>Calibres</th>");
                out.println("</tr>");
                out.println("</thead>");
                for (calibreModelo listaCalibres : calibres) {
                    out.println("<tr>");
                    out.println("<td><input value='" + listaCalibres.getId() + "' type='radio' onclick='editarCalibreEnLaBD(); calibresDeLaBD();' class='anadirCalibre' name='variedad'/></td>");
                    out.println("<td><input value='" + listaCalibres.getNombre() + "' type='text' id='nombreEditadoCalibre" + listaCalibres.getId() + "' class='input_text_agregar'></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<input type='hidden' id='parametro6' value='6'>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void agregarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int parametro = Integer.parseInt(request.getParameter("parametroAgregar"));
        String nombre = request.getParameter("nombre");
        String respuesta = "";
        if (nombre != "") {
            calibreModelo agregar = null;
            
                agregar = new calibreModelo(parametro, nombre.toUpperCase());
            
            respuesta = modeloCalibres.agregarEnBD(agregar);
        } else {
            respuesta = "No ingresaste nada, en le campo de texto.";
        }
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }
    private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreEditado = request.getParameter("nombreActualizado");
        calibreModelo editarNombre = new calibreModelo(id, nombreEditado.toUpperCase());
        String respuesta = modeloCalibres.editarNombreEnBD(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

}
