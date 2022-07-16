/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import Dao.FloresDAO;
import Dao.calibresDAO;
import Dao.clasificaionesDAO;
import Dao.codigosDAO;
import Dao.coloresDAO;
import Dao.corteDAO;
import Dao.especiesDAO;
import Dao.fincasDAO;
import Dao.lotesDAO;
import Dao.proveedoresDAO;
import Dao.referenciasDAO;
import Dao.semanasDAO;
import Dao.siembraDAO;
import Dao.variedadesDAO;
import Modelo.FloresModelo;
import Modelo.colorModelo;
import Modelo.corteModelo;
import Modelo.especieModelo;
import Modelo.fincaModelo;
import Modelo.loteModelo;
import Modelo.proveedorModelo;
import Modelo.referenciaModelo;
import Modelo.variedadModelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.CodingErrorAction;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.jasper.tagplugins.jstl.core.Catch;

/**
 *
 * @author Ubits
 */
@WebServlet(name = "ControladorDashboart", urlPatterns = {"/ControladorDashboart"})
public class ControladorDashboart extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private FloresDAO modeloFlores;
    private corteDAO modeloCortes;
    private lotesDAO modeloLotes;
    private siembraDAO modeloSiembra;
    private coloresDAO modeloColores;
    private especiesDAO modeloEspecies;
    private fincasDAO modeloFincas;
    private variedadesDAO modeloVariedades;
    private semanasDAO modeloSemanas;
    private referenciasDAO modeloReferencias;
    private calibresDAO modeloCalibres;
    private proveedoresDAO modeloProveedores;
    private codigosDAO modeloCodigos;
    private clasificaionesDAO modeloClasificaciones;

    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloFlores = new FloresDAO(miPool);
            modeloCortes = new corteDAO(miPool);
            modeloLotes = new lotesDAO(miPool);
            modeloLotes = new lotesDAO(miPool);
            modeloSiembra = new siembraDAO(miPool);
            modeloFincas = new fincasDAO(miPool);
            modeloEspecies = new especiesDAO(miPool);
            modeloVariedades = new variedadesDAO(miPool);
            modeloSemanas = new semanasDAO(miPool);
            modeloReferencias = new referenciasDAO(miPool);
            modeloColores = new coloresDAO(miPool);
            modeloCalibres = new calibresDAO(miPool);
            modeloProveedores = new proveedoresDAO(miPool);
            modeloCodigos = new codigosDAO(miPool);
            modeloClasificaciones = new clasificaionesDAO(miPool);
            modeloCortes = new corteDAO(miPool);
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
        String instruccion = "";
        try {
            instruccion = request.getParameter("intrucion");
        } catch (Exception e) {

        }
        if (instruccion == null) {
            instruccion = "tabla";
        }
        try {
            switch (instruccion) {

                case "tablas_consultas_semanas":
                    mostrarInformacionTablasSemanas(response, request);
                    break;
                case "dashboard":
                    mostrarInformacionTablasSemanas(response, request);
                    break;
                case "color_list":
                    listColorsQueries(response, request);
                case "sherch_for_color":
                    shearListColorsQueries(response, request);
                case "search_for_week":
                    searchForWeek(response, request);
                case "dashboard_data_ejemplo":
                    dashboard_data(response, request);
                    break;

            }
        } catch (Exception e) {
            System.out.println("error---  " + e);
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

    private void dashboard(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/dashboard.jsp");
        disptcher.forward(request, response);
    }

    private void dashboard_data(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("2,3,40,20,30");
        }
    }

    private void mostrarInformacionTablasSemanas(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        // semana end stard | flor color 
        int semanaStard = modeloSemanas.weekDash();;
        int semanaEnd = semanaStard + 4;
        int flor = 1;
        String color = "";
        int finca = 1;

        //lotes que esten en el rango de semanas búscado 
        List<colorModelo> colores;
        List<fincaModelo> fincas;

        fincas = modeloFincas.TraerFincasBD();
        colores = modeloColores.TraerColoresBD();

        //buscar la cantidad de color por finca y semana 
        //for desde con el número de semnas 
        int i = 0;//i del for
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<div class=\"accordion__container\">");
            for (colorModelo listColores : colores) {
                //aca las partes principales de la

                out.print("<div class=\"accordion__item\">");
                out.print("<header class=\"accordion__header\">");
                out.print("<i class='bx bx-plus accordion__icon'></i>");
                out.print("<table id=\"table_dash\">");

                out.print("<tr>");
                out.print("<td class=\"title_info\" style=\"text-align:center;min-width: 78px; max-width:78px;overflow: hidden;\">");
                out.print("<p class=\"accordion__title\" style=\"text-align:center;min-width: 20px; max-width:20px;\">" + listColores.getNombre() + "</p>");
                out.print("</td>");
                //for con totales para cada semna 
                for (int week = semanaStard; week <= semanaEnd; week++) {
                    List<loteModelo> lotes;
                    lotes = modeloLotes.buscarLotesSemanasTotales(week);
                    //generar la informacion para para el onclik
                    String ids = "";

                    ids = ids + "0";

                    int totales = modeloLotes.buscarContenidosLotesTotales(lotes, listColores.getId());
                    String detalles = "";
                    if (totales > 0) {
                        detalles = modeloLotes.buscarContenidosDetallesLotesTotales(lotes, listColores.getId());
                    } else {
                        detalles = "undefined";
                    }

                    out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\" onclick='showDetails(" + detalles + "," + week + "); addPanelDetails()'><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'>" + totales + "</p></td>");

                }
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p class=\"accordion__title\" style='overflow: hidden;'>semanas</p></td>");
                for (int week = semanaStard; week <= semanaEnd; week++) {
                    out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'>" + week + "</p></td>");
                }
                out.print("</tr>");

                out.print("</table>");
                out.print("</header>");

                // cotnenido por color divido por fincas
                out.print("<div class=\"accordion__content\">");
                out.print("<div class=\"accordion__header\">");
                out.print("<table id=\"table_dash\" class=\"details_table\">");

                for (fincaModelo listFinca : fincas) {
                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 78px; min-width:78px;overflow: hidden;\"><p style='text-align:center;max-width: 78px; min-width:78px;overflow: hidden;'>" + listFinca.getNombre() + "</p></td>");

                    out.print("");
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        //aca los totales 
                        List<loteModelo> lotes;
                        lotes = modeloLotes.buscarLotesSemanas(week, listFinca.getId());
                        //generar la informacion para para el onclik
                        String ids = "";
                        for (loteModelo listInfoId : lotes) {
                            ids = ids + "" + listInfoId.getId_lote() + ",";
                        }
                        ids = ids + "0";
                        List<loteModelo> contenidos;
                        contenidos = modeloLotes.buscarContenidosLotes(lotes, listColores.getId());
                        for (loteModelo listConten : contenidos) {

                            out.print("<td onclick=\"prueba2({name:[" + ids + "]})\" style=\"text-align:center;max-width: 55px; min-width:55px;text-overflow:'';\"><p style='text-align:center;max-width: 55px; max-width:55px;overflow: hidden;'>" + listConten.getBulbos() + "</p></td>");
                            break;
                        }

                    }
                    out.print("</tr>");
                    //aca los cotenidos de la tabla 
                }
                out.print("</table>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");

            }
            out.print("</div>");

        }

    }

    private int conmprobationTallos(int semanaStard, int semanaEnd, int idColor, List<fincaModelo> fincas) throws SQLException {
        int numTallos = 0;

        for (int week = semanaStard; week <= semanaEnd; week++) {
            List<loteModelo> lotes;
            lotes = modeloLotes.buscarLotesSemanasTotales(week);
            //generar la informacion para para el onclik
            numTallos = numTallos + modeloLotes.buscarContenidosLotesTotales(lotes, idColor);

        }

        return numTallos;
    }

    private void prueba(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("");
        }
    }

    private void listColorsQueries(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<colorModelo> colores;
        colores = modeloColores.TraerColoresBD();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            for (colorModelo listColor : colores) {
                out.print("<option value='" + listColor.getNombre() + "'>");
            }

        }
    }

    private void shearListColorsQueries(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {

        // semana end stard | flor color 
        int semanaStard = modeloSemanas.weekDash();
        int semanaEnd = semanaStard + 4;
        int flor = 1;
        String color = "";
        int finca = 1;
        int confirmFound = 0;
        int cofirmRangeWeek = 0;

        try {
            semanaStard = Integer.parseInt(request.getParameter("weekStard"));
            semanaEnd = Integer.parseInt(request.getParameter("weekEnd"));
            color = (request.getParameter("color")).toUpperCase();
        } catch (Exception e) {

        }
        if (color == "" && semanaStard == 0) {
            mostrarInformacionTablasSemanas(response, request);
        } else if (color != "") {
            searchEveryColor(response, request);
        } else if (semanaStard > 0 && semanaEnd > 0) {
            searchForWeek(response, request);
        }
        try {

        } catch (Exception e) {

        }

        //lotes que esten en el rango de semanas búscado 
        List<loteModelo> lotes;
        List<loteModelo> contenidos;
        List<colorModelo> colores;
        List<fincaModelo> fincas;

        fincas = modeloFincas.TraerFincasBD();
        colores = modeloColores.TraerColoresBD();

        //buscar la cantidad de color por finca y semana 
        //for desde con el número de semnas 
        int i = 0;//i del for
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<div class=\"accordion__container\">");

            for (colorModelo listColores : colores) {
                //aca las partes principales de la

                if (color.equals(listColores.getNombre())) {
                    out.print("<div class=\"accordion__item\">");
                    out.print("<header class=\"accordion__header\">");
                    out.print("<i class='bx bx-plus accordion__icon'></i>");
                    out.print("<table id=\"table_dash\">");

                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;min-width: 78px; max-width:78px;overflow: hidden;\">");
                    out.print("<p class=\"accordion__title\" style=\"text-align:center;min-width: 20px; max-width:20px;\">" + listColores.getNombre() + "</p>");
                    out.print("</td>");
                    //for con totales para cada semna 
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        lotes = modeloLotes.buscarLotesSemanasTotales(week);
                        //generar la informacion para para el onclik
                        String ids = "";
                        for (loteModelo listInfoId : lotes) {
                            ids = ids + "" + listInfoId.getId_lote() + ",";
                        }
                        ids = ids + "0";

                        int totales = modeloLotes.buscarContenidosLotesTotales(lotes, listColores.getId());

                        out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'> " + totales + "</p></td>");

                    }
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p class=\"accordion__title\" style='overflow: hidden;'>semanas</p></td>");
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'>" + week + "</p></td>");
                    }
                    out.print("</tr>");

                    out.print("</table>");
                    out.print("</header>");

                    // cotnenido por color divido por fincas
                    out.print("<div class=\"accordion__content\">");
                    out.print("<div class=\"accordion__header\">");
                    out.print("<table id=\"table_dash\" class=\"details_table\">");

                    for (fincaModelo listFinca : fincas) {
                        out.print("<tr>");
                        out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 78px; min-width:78px;overflow: hidden;\"><p style='text-align:center;max-width: 78px; min-width:78px;overflow: hidden;'>" + listFinca.getNombre() + "</p></td>");

                        out.print("");
                        for (int week = semanaStard; week <= semanaEnd; week++) {
                            //aca los totales 
                            lotes = modeloLotes.buscarLotesSemanas(week, listFinca.getId());
                            //generar la informacion para para el onclik
                            String ids = "";
                            for (loteModelo listInfoId : lotes) {
                                ids = ids + "" + listInfoId.getId_lote() + ",";
                            }
                            ids = ids + "0";
                            contenidos = modeloLotes.buscarContenidosLotes(lotes, listColores.getId());
                            for (loteModelo listConten : contenidos) {

                                out.print("<td onclick=\"prueba2({name:[" + ids + "]})\" style=\"text-align:center;max-width: 55px; min-width:55px;text-overflow:'';\"><p style='text-align:center;max-width: 55px; max-width:55px;overflow: hidden;'>" + listConten.getBulbos() + "</p></td>");
                                break;
                            }

                        }
                        out.print("</tr>");
                        //aca los cotenidos de la tabla 
                    }
                    out.print("</table>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                    confirmFound = 1;
                }
            }
            out.print("</div>");

        }

    }

    private void searchForWeek(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int semanaStard = 0;
        int semanaEnd = 0;
        int flor = 1;
        String color = "";
        int finca = 1;
        int confirmFound = 0;
        int cofirmRangeWeek = 0;

        try {
            semanaStard = Integer.parseInt(request.getParameter("weekStard"));
            semanaEnd = Integer.parseInt(request.getParameter("weekEnd"));
            color = (request.getParameter("color")).toUpperCase();
        } catch (Exception e) {
            mostrarInformacionTablasSemanas(response, request);
        }

        try {

        } catch (Exception e) {

        }

        //lotes que esten en el rango de semanas búscado 
        List<loteModelo> lotes;
        List<loteModelo> contenidos;
        List<colorModelo> colores;
        List<fincaModelo> fincas;

        fincas = modeloFincas.TraerFincasBD();
        colores = modeloColores.TraerColoresBD();

        //buscar la cantidad de color por finca y semana 
        //for desde con el número de semnas 
        int i = 0;//i del for
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<div class=\"accordion__container\">");

            for (colorModelo listColores : colores) {
                //aca las partes principales de la
                int numTallos = conmprobationTallos(semanaStard, semanaEnd, listColores.getId(), fincas);
                if (numTallos > 0) {
                    out.print("<div class=\"accordion__item\">");
                    out.print("<header class=\"accordion__header\">");
                    out.print("<i class='bx bx-plus accordion__icon'></i>");
                    out.print("<table id=\"table_dash\">");

                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;min-width: 78px; max-width:78px;overflow: hidden;\">");
                    out.print("<p class=\"accordion__title\" style=\"text-align:center;min-width: 20px; max-width:20px;\">" + listColores.getNombre() + "</p>");
                    out.print("</td>");
                    //for con totales para cada semna 
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        lotes = modeloLotes.buscarLotesSemanasTotales(week);
                        //generar la informacion para para el onclik
                        String ids = "";
                        for (loteModelo listInfoId : lotes) {
                            ids = ids + "" + listInfoId.getId_lote() + ",";
                        }
                        ids = ids + "0";

                        int totales = modeloLotes.buscarContenidosLotesTotales(lotes, listColores.getId());

                        out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'> " + totales + "</p></td>");

                    }
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p class=\"accordion__title\" style='overflow: hidden;'>semanas</p></td>");
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'>" + week + "</p></td>");
                    }
                    out.print("</tr>");

                    out.print("</table>");
                    out.print("</header>");

                    // cotnenido por color divido por fincas
                    out.print("<div class=\"accordion__content\">");
                    out.print("<div class=\"accordion__header\">");
                    out.print("<table id=\"table_dash\" class=\"details_table\">");

                    for (fincaModelo listFinca : fincas) {
                        out.print("<tr>");
                        out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 78px; min-width:78px;overflow: hidden;\"><p style='text-align:center;max-width: 78px; min-width:78px;overflow: hidden;'>" + listFinca.getNombre() + "</p></td>");

                        out.print("");
                        for (int week = semanaStard; week <= semanaEnd; week++) {
                            //aca los totales 
                            lotes = modeloLotes.buscarLotesSemanas(week, listFinca.getId());
                            //generar la informacion para para el onclik
                            String ids = "";
                            for (loteModelo listInfoId : lotes) {
                                ids = ids + "" + listInfoId.getId_lote() + ",";
                            }
                            ids = ids + "0";
                            contenidos = modeloLotes.buscarContenidosLotes(lotes, listColores.getId());
                            for (loteModelo listConten : contenidos) {

                                out.print("<td onclick=\"prueba2({name:[" + ids + "]})\" style=\"text-align:center;max-width: 55px; min-width:55px;text-overflow:'';\"><p style='text-align:center;max-width: 55px; max-width:55px;overflow: hidden;'>" + listConten.getBulbos() + "</p></td>");
                                break;
                            }

                        }
                        out.print("</tr>");
                        //aca los cotenidos de la tabla 
                    }
                    out.print("</table>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                    confirmFound = 1;

                }
            }
            out.print("</div>");

        }
    }

    private void searchEveryColor(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        int semanaStard = 24;
        int semanaEnd = 24;
        int flor = 1;
        String color = "";
        int finca = 1;
        int confirmFound = 0;
        int cofirmRangeWeek = 0;

        try {
            semanaStard = Integer.parseInt(request.getParameter("weekStard"));
            semanaEnd = Integer.parseInt(request.getParameter("weekEnd"));
            color = (request.getParameter("color")).toUpperCase();
        } catch (Exception e) {
            mostrarInformacionTablasSemanas(response, request);
        }
        System.out.println(semanaEnd + "----------------............" + semanaStard);
        if (semanaEnd == 0 && semanaStard == 0) {
            semanaStard = modeloSemanas.weekDash();
            semanaEnd = semanaStard + 4;

        }
        try {

        } catch (Exception e) {

        }

        //lotes que esten en el rango de semanas búscado 
        List<loteModelo> lotes;
        List<loteModelo> contenidos;
        List<colorModelo> colores;
        List<fincaModelo> fincas;

        fincas = modeloFincas.TraerFincasBD();
        colores = modeloColores.TraerColoresBD();

        //buscar la cantidad de color por finca y semana 
        //for desde con el número de semnas 
        int i = 0;//i del for
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<div class=\"accordion__container\">");

            for (colorModelo listColores : colores) {

                //aca las partes principales de la
                if (color.equals(listColores.getNombre())) {
                    out.print("<div class=\"accordion__item\">");
                    out.print("<header class=\"accordion__header\">");
                    out.print("<i class='bx bx-plus accordion__icon'></i>");
                    out.print("<table id=\"table_dash\">");

                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;min-width: 78px; max-width:78px;overflow: hidden;\">");
                    out.print("<p class=\"accordion__title\" style=\"text-align:center;min-width: 20px; max-width:20px;\">" + listColores.getNombre() + "</p>");
                    out.print("</td>");
                    //for con totales para cada semna 
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        lotes = modeloLotes.buscarLotesSemanasTotales(week);
                        //generar la informacion para para el onclik
                        String ids = "";
                        for (loteModelo listInfoId : lotes) {
                            ids = ids + "" + listInfoId.getId_lote() + ",";
                        }
                        ids = ids + "0";

                        int totales = modeloLotes.buscarContenidosLotesTotales(lotes, listColores.getId());

                        out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'>" + totales + "</p></td>");

                    }
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p class=\"accordion__title\" style='overflow: hidden;'>semanas</p></td>");
                    for (int week = semanaStard; week <= semanaEnd; week++) {
                        out.print("<td style=\"text-align:center;max-width: 55px; min-width:55px;overflow: hidden;\"><p style='text-align:center;max-width: 55px; min-width:55px;overflow: hidden;'>" + week + "</p></td>");
                    }
                    out.print("</tr>");

                    out.print("</table>");
                    out.print("</header>");

                    // cotnenido por color divido por fincas
                    out.print("<div class=\"accordion__content\">");
                    out.print("<div class=\"accordion__header\">");
                    out.print("<table id=\"table_dash\" class=\"details_table\">");

                    for (fincaModelo listFinca : fincas) {
                        out.print("<tr>");
                        out.print("<td class=\"title_info\" style=\"text-align:center;max-width: 78px; min-width:78px;overflow: hidden;\"><p style='text-align:center;max-width: 78px; min-width:78px;overflow: hidden;'>" + listFinca.getNombre() + "</p></td>");

                        out.print("");
                        for (int week = semanaStard; week <= semanaEnd; week++) {
                            //aca los totales 
                            lotes = modeloLotes.buscarLotesSemanas(week, listFinca.getId());
                            //generar la informacion para para el onclik
                            String ids = "";
                            for (loteModelo listInfoId : lotes) {
                                ids = ids + "" + listInfoId.getId_lote() + ",";
                            }
                            ids = ids + "0";
                            contenidos = modeloLotes.buscarContenidosLotes(lotes, listColores.getId());
                            for (loteModelo listConten : contenidos) {

                                out.print("<td onclick=\"prueba2({name:[" + ids + "]})\" style=\"text-align:center;max-width: 55px; min-width:55px;text-overflow:'';\"><p style='text-align:center;max-width: 55px; max-width:55px;overflow: hidden;'>" + listConten.getBulbos() + "</p></td>");
                                break;
                            }

                        }
                        out.print("</tr>");
                        //aca los cotenidos de la tabla 
                    }
                    out.print("</table>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                    confirmFound = 1;

                }

                out.print("</div>");

            }
        }
    }

}
