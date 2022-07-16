
package Controladores;

import Dao.clasificaionesDAO;
import Modelo.clasificacionModelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet(name = "controladorClasificaciones", urlPatterns = {"/controladorClasificaciones"})
public class controladorClasificaciones extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private clasificaionesDAO modeloClasificaciones;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloClasificaciones = new clasificaionesDAO(miPool);
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
                case "mostraClasificacionesDeLaBD":
                    clasificaionesDeLaBD(response, request);
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
    private void clasificaionesDeLaBD(HttpServletResponse response, HttpServletRequest request) {
        try {
            List<clasificacionModelo> clasifiaciones;
            clasifiaciones = modeloClasificaciones.TraerClasificacionesBD();
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
                for (clasificacionModelo listaClasificaciones : clasifiaciones) {
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
    private void agregarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        String nombre = request.getParameter("nombre");
        String respuesta = "";
        if (nombre != "") {
            clasificacionModelo agregar = null;
            //agregar Degradados
           
                agregar = new clasificacionModelo(nombre.toUpperCase());
            
            respuesta = modeloClasificaciones.agregarEnBD(agregar);
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
        clasificacionModelo editarNombre = new clasificacionModelo(id, nombreEditado.toUpperCase());
        String respuesta = modeloClasificaciones.editarNombreEnBD(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

}
