package Controladores;


import Dao.FloresDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet(urlPatterns = {"/controladorLogin"})
public class controladorLogin extends HttpServlet {

 private static final long serialVersionUID = 1L;

    private FloresDAO modeloFlores;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloFlores = new FloresDAO(miPool);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String instruccion = "";
            try {
                instruccion = request.getParameter("intrucion");
            } catch (Exception e) {

            }
            if (instruccion == null) {
                instruccion = "listar";
            }

            switch (instruccion) {

                case "listar":
            {
                try {
                    iniciar(response, request);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;

                default:
            {
                try {
                    iniciar(response, request);
                } catch (SQLException ex) {
                    Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;

            
        }

    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     try {
         processRequest(request, response);
     } catch (SQLException ex) {
         Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void iniciar(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {
        int existencia = 2;
        String password = request.getParameter("contra");
        int user = Integer.parseInt(request.getParameter("usuario"));
        existencia = modeloFlores.login(password, user);
        if(existencia == 1){
            HttpSession objSesion = request.getSession();
            response.sendRedirect("ControladorFlores");
        }else if(existencia == 0){
            existencia = 0;
            request.setAttribute("Estado", existencia);
            RequestDispatcher disfacter = request.getRequestDispatcher("/vistas/Inicio_sesion.jsp");
            disfacter.forward(request, response);
        }else{
            existencia = 2;
            request.setAttribute("Estado", existencia);
            RequestDispatcher disfacter = request.getRequestDispatcher("/vistas/Inicio_sesion.jsp");
            disfacter.forward(request, response);

        }
        
    }
    

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        iniciar(response, request);
    }

}
