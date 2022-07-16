package Controladores;

import Dao.especiesDAO;
import Dao.variedadesDAO;
import Modelo.especieModelo;
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

@WebServlet(name = "controladorVariedades", urlPatterns = {"/controladorVariedades"})
public class controladorVariedades extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private variedadesDAO modeloVariedades;
    private especiesDAO modeloEspecies;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloVariedades = new variedadesDAO(miPool);
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
                case "formularioAgreagarVariedad":
                    formularioVariedad(response, request);
                    break;
                 case "editraBD":
                    editarEnBD(response, request);
                    break;
                case "agregarBD":
                    agregarEnBD(response, request);
                    break;
                case "mostraVariedadesDeLaBD":
                    variedadesDeLaBD(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador variedades");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador variedades" + e);
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

    private void formularioVariedad(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/agregarVariedades.jsp");
        disptcher.forward(request, response);
    }
     private void editarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreEditado = request.getParameter("nombreActualizado");
        variedadModelo editarNombre = new variedadModelo(id, nombreEditado.toUpperCase());
        String respuesta = modeloVariedades.editarNombreEnBD(editarNombre);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }
     private void agregarEnBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        String nombre = request.getParameter("nombre");
        String respuesta = "";
        if (nombre != "") {
            variedadModelo agregar = null;
            //agregar Degradados
            int id_variedad = 0;
            int id_color = 0;
                id_variedad = Integer.parseInt(request.getParameter("color"));
                id_color = 0;
                agregar = new variedadModelo(id_color, nombre.toUpperCase(), id_variedad);
            
            respuesta = modeloVariedades.agregarEnBD(agregar);
        } else {
            respuesta = "No ingresaste nada, en le campo de texto.";
        }
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h5 class='respuesta'> " + respuesta + "</h5>");
        }
    }
   private void variedadesDeLaBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int flor = Integer.parseInt(request.getParameter("flor"));
        String nombreDelaFlor = "";
        List<variedadModelo> variedades;
        especieModelo consultarFlor = new especieModelo(flor);
        nombreDelaFlor = modeloEspecies.traerNombreEspecie(consultarFlor);
        variedadModelo consularVariedad = new variedadModelo(flor);
        variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println("<h3>" + nombreDelaFlor + "</h3>");
            out.println("<table class='tablasInoformacion'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Edi.</th>");
            out.println("<th>Variedad  </th>");
            out.println("<th>Color</th>");
            out.println("</tr>");
            out.println("</thead>");
            for (variedadModelo listaVariedades : variedades) {
                out.println("<tr>");
                out.println("<td><input value='" + listaVariedades.getId() + "' type='radio' onclick='editarVaridadesEnLaBD(); variedadesDeLaBD();' class='editarVariedad' name='variedad'/></td>");
                out.println("<td><input value='" + listaVariedades.getNombre() + "' type='text' id='nombreEditadoVaridad" + listaVariedades.getId() + "'></td>");
                out.println("<td><input value='" + listaVariedades.getId() + "' type='radio' onclick='coloresFormulario();' class='coloresVariedad' name='variedad'/></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<input type='hidden' id='idFLorAgregarVariedad' value='" + flor + "'>");
            out.println("<input type='hidden' id='parametro3' value='3'>");
        }
    }

 
}
