
package Controladores;

import Dao.usuariosDAO;
import Modelo.usuarioModelo;
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

@WebServlet(name = "controladorUsuarios", urlPatterns = {"/controladorUsuarios"})
public class controladorUsuarios extends HttpServlet {
private static final long serialVersionUID = 1L;

    private usuariosDAO modeloUsuarios;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloUsuarios = new usuariosDAO(miPool);
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
                System.out.println("error parametro principal controlador Usuarios");
            }
            switch (instruccion) {
                case "formularioUsuarios":
                    formularioUsuarios(response, request);
                    break;
                case "usuariosDeLaBD":
                    formularioProveedoresUsuariosBD(response, request);
                    break;
                case "agregarPersonas":
                    agreagarPersonas(response, request);
                    break;
                case "eliminarUsuarios":
                    eliminarUsuarios(response, request);
                    break;
                case "editarUsuarios":
                    editarUsuarios(response, request);
                    break;
                default:
                    System.out.println("intruccion no contemplada-controlador usuarios");
                    break;

            }
        } catch (Exception e) {
            System.out.println("error controlador usuarios" + e);
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
    private void formularioUsuarios(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/user.jsp");
        disptcher.forward(request, response);
    }
    private void formularioProveedoresUsuariosBD(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<usuarioModelo> usuarios;
        usuarios = modeloUsuarios.getUsuarios();
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
            for (usuarioModelo listaUsuarios : usuarios) {
                out.println("<tr>");
                out.println("<td><input type='radio' name='editarUsuario' class='EditarUsuario' value='" + listaUsuarios.getId() + "' onclick='editarUsuarios()'</td>");
                out.println("<td><input type='text' id='nombreEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getNombre() + "'</td>");
                out.println("<td><input type='text' id='documentoEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getDocumento() + "'</td>");
                out.println("<td><input type='text' id='telefonoEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getTelefono() + "'</td>");
                out.println("<td><input type='text' id='contrasenaEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getContrasena() + "'</td>");
                out.println("<td><input  name='eliminarUsuarios' type='radio' id='telefonoEditadoUsuario" + listaUsuarios.getId() + "' value='" + listaUsuarios.getId() + "' onclick='eliminarUsuarios(); usuariosDeLaBD();' class='eliminarUsuarios'></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
    }
    private void agreagarPersonas(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String documento = request.getParameter("documento");
        String contrasena = request.getParameter("contrasena");
        usuarioModelo agreagregarPersonas = new usuarioModelo(nombre.toUpperCase(), telefono, documento, contrasena);
        String respuesta = modeloUsuarios.agregarPersonasBD(agreagregarPersonas);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
        }
    }
    private void eliminarUsuarios(HttpServletResponse response, HttpServletRequest request) throws SQLException {
        int codigos = Integer.parseInt(request.getParameter("codigo"));
        usuarioModelo aliminarUsuariosBD = new usuarioModelo(codigos);
        modeloUsuarios.eliminarUsuariosDeBD(aliminarUsuariosBD);
    }

    private void editarUsuarios(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String documento = request.getParameter("documento");
        String contrasena = request.getParameter("contrasena");
        int id = Integer.parseInt(request.getParameter("codigo"));
        usuarioModelo agreagregarPersonas = new usuarioModelo(nombre.toUpperCase(), telefono, documento, contrasena);
        String respuesta = modeloUsuarios.editarPersonasBD(agreagregarPersonas);
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h4>" + respuesta + "</h4>");
        }
    }

}
