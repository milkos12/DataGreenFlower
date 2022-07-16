package Controladores;

import Dao.proveedoresDAO;
import Modelo.proveedorModelo;
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

@WebServlet(name = "controladorProveedores", urlPatterns = {"/controladorProveedores"})
public class controladorProveedores extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private proveedoresDAO modeloProveedores;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloProveedores = new proveedoresDAO(miPool);
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
                System.out.println("error parametro principal controlador proveedores");
            }
            switch (instruccion) {
              
                case "proveedoresDeLaBD":
                    proveedoresDeLaBD(response, request);
                    break;
                case "editarProveedores":
                    editarProveedores(response, request);
                    break;
                case "eliminarProveedores":
                    eliminarProveedores(response, request);
                    break;
                case "agregarProveedores":
                    agregarProveedores(response, request);
                    break;
                case "formularioProveedores":
                    formularioProveedores(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador proveedores");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador proveedores" + e);
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

    private void formularioProveedores(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("vistas/proveedores.jsp");
        disptcher.forward(request, response);
    }

    private void proveedoresDeLaBD(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        List<proveedorModelo> proveedores;
        proveedores = modeloProveedores.getProveedores();
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

            for (proveedorModelo listaProveedores : proveedores) {
                out.println("<tr>");
                out.println("<td><input type='radio' class='editarProveedores' value='" + listaProveedores.getId() + "' onclick='editarProveedores(), proveedoresDeLaBD()' class='inputUpadate'></td>");
                out.println("<td><input type='text' id='nombreEditadoProveedor" + listaProveedores.getId() + "' value='" + listaProveedores.getNombre() + "' class='inputUpadate'></td>");
                out.println("<td><input type='text' id='telefonoEditadoProveedor" + listaProveedores.getId() + "' value='" + listaProveedores.getTelefono() + "' class='inputUpadate'></td>");
                out.println("<td><input type='text' id='documentoEditadoProveedor" + listaProveedores.getId() + "' value='" + listaProveedores.getDocumento() + "' ></td>");
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
        proveedorModelo editarProveedoresBD = new proveedorModelo(codigo, nombre.toUpperCase(), telefono, documento);
        respuesta = modeloProveedores.editarProveedores(editarProveedoresBD);

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
        }
    }

    private void eliminarProveedores(HttpServletResponse response, HttpServletRequest request) throws SQLException {
        int codigoProveedor = Integer.parseInt(request.getParameter("codigo"));

        proveedorModelo eliminarProveedores = new proveedorModelo(codigoProveedor);
        String respuesta = modeloProveedores.eliminarProveedoresBD(eliminarProveedores);
    }

    private void agregarProveedores(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String documento = request.getParameter("documento");
        
        proveedorModelo agregarProveedoresBD = new proveedorModelo(nombre, telefono, documento);
        String respuesta = modeloProveedores.agregarProveedores(agregarProveedoresBD);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
        }
    }
}
