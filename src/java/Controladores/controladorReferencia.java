package Controladores;

import Dao.coloresDAO;
import Dao.referenciasDAO;
import Modelo.colorModelo;
import Modelo.referenciaModelo;
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

@WebServlet(name = "controladorReferencia", urlPatterns = {"/controladorReferencia"})
public class controladorReferencia extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private referenciasDAO modeloReferencias;
    private coloresDAO modeloColores;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloReferencias = new referenciasDAO(miPool);
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
                case "formularioAgreagarDegradado":
                    formularioReferencias(response, request);
                    break;
                case "mostraDegradadosDeLaBD":
                    referenciasDeDB(response, request);
                    break;
                case "editraBD":
                    editarEnBD(response, request);
                    break;
                case "agregarBD":
                    agregarEnBD(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador referenicias");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador referencias" + e);
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

    private void formularioReferencias(HttpServletResponse response, HttpServletRequest request) throws SQLException, ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarDegradados.jsp");
        disptcher.forward(request, response);
    }

    private void referenciasDeDB(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int variedad = Integer.parseInt(request.getParameter("variedad"));
        int color = Integer.parseInt(request.getParameter("color"));
        String nombreVariedad = "";
        List<referenciaModelo> degradados;
        colorModelo consultarNombreColor = new colorModelo(variedad);
        nombreVariedad = modeloColores.TraerNombreDelColor(consultarNombreColor);
        referenciaModelo consultarReferencia = new referenciaModelo(color, variedad);
        degradados = modeloReferencias.TraerDegradadosBD(consultarReferencia);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<h3>" + nombreVariedad + "</h3>");
            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Referencia  </th>");
            out.println("<th>Editar</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (referenciaModelo listaDegradados : degradados) {
                out.println("<tr>");
                out.println("<td><input value='" + listaDegradados.getNombre() + "' type='text' id='idEditar" + listaDegradados.getId() + "' style='width:150px'></td>");
                out.println("<td><input value='" + listaDegradados.getId() + "' type='radio' onclick='editarDegradadoEnLaBD();degradadosDeLaBD();' class='editarDegradado' name='degradadoEditar'/></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<input type='hidden' id='colorAgregarDegradado' value='" + variedad + "'>");
            out.println("<input type='hidden' id='parametro1' value='1'>");
        }
    }

    private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreEditado = request.getParameter("nombreActualizado");
        referenciaModelo editarNombre = new referenciaModelo(id, nombreEditado.toUpperCase());
        String respuesta = modeloReferencias.editarNombreEnBD(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

    private void agregarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        String nombre = request.getParameter("nombre");
        String respuesta = "";
        if (nombre != "") {
            referenciaModelo agregar = null;
            //agregar Degradados
            int id_variedad = 0;
            int id_color = 0;
            id_color = Integer.parseInt(request.getParameter("color"));
            id_variedad = Integer.parseInt(request.getParameter("variedad"));
            agregar = new referenciaModelo(nombre.toUpperCase(), id_color,id_variedad);

            respuesta = modeloReferencias.agregarEnBD(agregar);
        } else {
            respuesta = "No ingresaste nada, en le campo de texto.";
        }
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }

}
