/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.sql.*;

/**
 *
 * @author ANDRES
 */
/*
@WebServlet(name = "ConexionBD", urlPatterns = {"/ConexionBD"})
public class ConexionBD extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //Definir el DataSource
    @Resource(name = "jdbc/Bodega")
     
    private DataSource miPool;
    
    public ConexionBD() {
           
        super();
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //crear el objeto printWritter
        

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
        PrintWriter salida = response.getWriter();
        response.setContentType("text/plain");
        
        Connection conexion = null;
        Statement S = null;
        ResultSet R = null;
        
        try {
            conexion = miPool.getConnection();
            String sql = "SELECT * FROM proveedores";
            S = conexion.createStatement();
            R = S.executeQuery(sql);
            
            while (R.next()) {
                
                String nombre = R.getString(3);
                salida.print(nombre);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
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
    }// </editor-fold>

}*/
@WebServlet("/ConexionBD")
public class ConexionBD extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    public ConexionBD() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter salida = response.getWriter();
        response.setContentType("text/plain");

        Connection conexion = null;
        Statement S = null;
        ResultSet R = null;

        try {
            
            conexion = miPool.getConnection();
            String sql = "SELECT * FROM colores";
            S = conexion.createStatement();
            R = S.executeQuery(sql);

            while (R.next()) {
                System.out.println(R.getString("nombre_color")+"--------------------********************");

            }

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("error");

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
