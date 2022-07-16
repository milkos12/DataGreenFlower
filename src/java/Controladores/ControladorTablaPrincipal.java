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
@WebServlet(name = "ControladorTablaPrincipal", urlPatterns = {"/ControladorTablaPrincipal"})
public class ControladorTablaPrincipal extends HttpServlet {

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

                case "tabla":
                    mostrarInformacion(response, request);
                    break;
                case "buscarLote":
                    buscarLote(response, request);
                    break;
                case "editarLote":
                    mostrarFormulariEditarLote(response, request);
                    break;
                case "variedadFiltro":
                    buscarBariedadFiltro(response, request);
                    break;
                case "filtrar":
                    filtrarTable(response, request);
                    break;
                case "buscarPorDias":
                    diasBus(response, request);
                    break;
                case "dashboard":
                    dashboard(response, request);
                    break;
                case "dashboard_data_ejemplo":
                    dashboard_data(response, request);
                    break;
                default:
                    mostar(response, request);
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

    private void mostar(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/DataFlower.jsp");
        disptcher.forward(request, response);
    }

    private void mostrarInformacion(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> lotes;
        lotes = modeloFlores.TraerLotesBD();
        List<FloresModelo> fincas;
        fincas = modeloFlores.TraerFincasBD();
        List<proveedorModelo> proveedores;
        proveedores = modeloProveedores.getProveedores();
        List<especieModelo> flores;
        flores = modeloEspecies.TraerEspeciesBD();
        List<colorModelo> colores;
        colores = modeloColores.TraerColoresBD();

        int numeroDeTallosCortados = 0;
        float cantidadTallosContenido = 0;
        String detalles = "";
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String nomVaridadDe = "";
            String nomColorDe = "";
            String nomReferenciaDe = "";
            String nomDisponibilidad = "";

            out.println("<div class=\"card team-progress\">");
            out.println("<div class=\"card-head\">");
            out.println("<div class=\"team-head\">");
            out.println("<div>");
            out.println("<h3>Lotes registrados</h3>");
            out.println("<small>lotes</small>");
            out.println("</div>");
           
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"card-body\">");

            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th><input  type=\"number\" placeholder=\"Lote\" class=\"input-cod\" id='bucarPorLotes' onkeyup='buscarLote();'></th>");
            out.println("<th colspan='3'>Dellates</th>");
            out.println("<th width=\"22%\">Progreso en corte</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody id='resultadosBusquedaLote'>");
            // barra de disponibilidad de lote 
            for (FloresModelo listaLotes : lotes) {

                List<loteModelo> contenido;
                List<loteModelo> IDcontenidos = modeloLotes.traerContenidos(listaLotes.getId());
                for (loteModelo listaContenidos : IDcontenidos) {
                    contenido = modeloLotes.traerContenidosDescripcionUnique(listaContenidos.getId_contenido());
                    for (loteModelo litatContenidosC : contenido) {
                        int variedad = litatContenidosC.getVariedad();
                        variedadModelo consultarVariedadName = new variedadModelo(variedad);
                        String nombreVariedad = modeloVariedades.variedadesNombreEspecie(consultarVariedadName);
                        especieModelo consultarFlor = new especieModelo(litatContenidosC.getEspecie());
                        String nombreEspecie = modeloEspecies.traerNombreEspecie(consultarFlor);
                        colorModelo consultarDegradados = new colorModelo(litatContenidosC.getColor());
                        String nombreColor = modeloColores.traerNombreDelColorCorte(consultarDegradados);
                        referenciaModelo traerNombreReferencia = new referenciaModelo(litatContenidosC.getReferencia());
                        String nombreReferencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                        cantidadTallosContenido = cantidadTallosContenido + (litatContenidosC.getBulbos() * litatContenidosC.getCanastillas());
                        corteModelo traerNumeroDeTallos = new corteModelo(listaContenidos.getId_contenido(), 0);
                        numeroDeTallosCortados = numeroDeTallosCortados + modeloCortes.getTallos(traerNumeroDeTallos);
                        float diponibilida = (litatContenidosC.getBulbos() * litatContenidosC.getCanastillas()) - modeloCortes.getTallos(traerNumeroDeTallos);
                        nomVaridadDe = nomVaridadDe + nombreVariedad + "<br>";
                        nomColorDe = nomColorDe + nombreColor + "<br>";
                        nomReferenciaDe = nomReferenciaDe + nombreReferencia + "<br>";
                        nomDisponibilidad = nomDisponibilidad + diponibilida + "<br>";

                    }
                }
                float porcentaje = (numeroDeTallosCortados * 100)/cantidadTallosContenido;
                
                if(cantidadTallosContenido == numeroDeTallosCortados){
                    porcentaje = 100;
                } else {
                    porcentaje = (float) (Math.round(porcentaje*100)/100.0);
                }
                out.println("<tr>");
                out.println("<td>");
                out.println("<h6 style='color:green'>"+listaLotes.getTelefono()+"</h6>");
                out.println("<h4>" + listaLotes.getId() +"</h4>");
                out.println("<small>Siembra " + listaLotes.getFechaE() + "<br> " + listaLotes.getDegradado() + "</small>");

                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomVaridadDe);
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomReferenciaDe);
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomDisponibilidad);
                out.println("</td>");

                out.println("<td>");
                out.println("<div class=\"team-progress\">");
                out.println("<h4>" + porcentaje + "%</h4>");
                out.println("<div class=\"progress-bar\">");
                out.println("<div class=\"indicator success\" style=\"width: " + porcentaje + "%\"></div>");
                out.println("</div>");
                out.println("</div>");
                out.println("<small class=\"tooltip\" tool-tips='" + listaLotes.getTelefono() + " - " + listaLotes.getParametro() + " - " + listaLotes.getNombre() + "'>Detalle corte</small>");
                out.println("</td>");
                out.println("<td>");
                out.println("<div class=\"btn btn-main\">");
                out.println("<a href='#' onclick='editarLote(" + listaLotes.getId() + "); menuBotonesEditar();'><small class=\"tooltip\" tool-tips='Editar'><ion-icon name=\"create-outline\" id='iconoBoton'></ion-icon></small></a> | ");
                out.println("<a href='#' onclick='corteFormulario(" + listaLotes.getId() + ");'><small class=\"tooltip\" tool-tips='Cortar'><ion-icon name='cut-outline' id='iconoBoton'></ion-icon></small></a>");
                out.println("</div>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                //box info
                out.println("<td>");

                out.println("</td>");
                out.println("</tr>");

                //out.println("<td><a href='#' onclick='' value='' ><ion-icon name='cut-outline' id='iconoBoton'></ion-icon></a></a>");
                //out.println("<td><a href='#' ><ion-icon name='create-outline' id='iconoBoton'></ion-icon></a></td>");
                out.print("</tr>");
                numeroDeTallosCortados = 0;
                cantidadTallosContenido = 0;
                detalles = "";
                nomVaridadDe = "";
                nomColorDe = "";
                nomReferenciaDe = "";
                nomDisponibilidad = "";
                porcentaje = 0;
                
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"card competitors\">");
            out.println("<div class=\"card-head\">");
            out.println("<div class=\"team-head\">");
            out.println("<div>");
            out.println("<h4>Gestión de filtros</h4><a href=''>Limpiar</a>");

            
            out.println("<table id='table-filter'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<small>Especie</small>");
            out.println("</td>");
            out.println("<td>");
            out.println("<small>Variedad</small>");
            out.println("</td>");
            out.println("<td>");
            out.println("<small>Color</small>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<select class='input-cod valor-filter-flor' id='flor-filter' onchange='filter();' onclick='buscarVariedad();'>");
            out.println("<option value='0'>especie</option>");
            for (especieModelo listaFlores : flores) {
                out.println("<option value='" + listaFlores.getId() + "'>" + listaFlores.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("<td>");
            out.println("<div id='select-varidades'></div>");
            out.println("</td>");
            out.println("<td>");
            out.println("<select class='input-cod valor-filter-color' id='flor-filter' onchange='filter();'>");
            out.println("<option value='0'>colores</option>");
            for (colorModelo listaColores : colores) {
                out.println("<option value='" + listaColores.getId() + "'>" + listaColores.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<table id='table-filter'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<small>Fecha</small>");
            out.println("</td>");
            out.println("<tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<input type='date' class='input-cod valor-filter-fecha' id='fecha-filter' onchange='filter();'>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<table id='table-filter'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<small>Finca</small>");
            //out.println("</td>");
            //out.println("<tr>");
            //out.println("<td>");
            //out.println("<select class='input-cod valor-filter-proveedor' id='flor-filter' onchange='filter();'>");
            //out.println("<option value='0'>proveedores</option>");
            //for (proveedorModelo listaProveedores : proveedores) {
            //    out.println("<option value='" + listaProveedores.getId() + "'>" + listaProveedores.getNombre() + "</option>");
            //}
            //out.println("</select>");
            //out.println("</td>");

            out.println("<td>");
            out.println("<select class='input-cod valor-filter-finca' id='flor-filter' onchange='filter();'>");
            out.println("<option value='0'>finca</option>");
            for (FloresModelo listaFincas : fincas) {
                out.println("<option value='" + listaFincas.getId() + "'>" + listaFincas.getNombre() + "</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");

        }
    }

    private void buscarLote(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<FloresModelo> lotes;
        try {
            int codigo = Integer.parseInt(request.getParameter("lote"));
            FloresModelo buscarLotes = new FloresModelo(codigo);
            lotes = modeloFlores.BuscarLotes(buscarLotes);
        } catch (Exception e) {
            lotes = modeloFlores.TraerLotesBD();
        }
        int numeroDeTallosCortados = 0;
        float cantidadTallosContenido = 0;
        String detalles = "";
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            for (FloresModelo listaLotes : lotes) {
                String nomVaridadDe = "";
                String nomColorDe = "";
                String nomReferenciaDe = "";
                String nomDisponibilidad = "";

                List<loteModelo> contenido;
                List<loteModelo> IDcontenidos = modeloLotes.traerContenidos(listaLotes.getId());
                for (loteModelo listaContenidos : IDcontenidos) {
                    contenido = modeloLotes.traerContenidosDescripcionUnique(listaContenidos.getId_contenido());
                    for (loteModelo litatContenidosC : contenido) {
                        int variedad = litatContenidosC.getVariedad();
                        variedadModelo consultarVariedadName = new variedadModelo(variedad);
                        String nombreVariedad = modeloVariedades.variedadesNombreEspecie(consultarVariedadName);
                        especieModelo consultarFlor = new especieModelo(litatContenidosC.getEspecie());
                        String nombreEspecie = modeloEspecies.traerNombreEspecie(consultarFlor);
                        colorModelo consultarDegradados = new colorModelo(litatContenidosC.getColor());
                        String nombreColor = modeloColores.traerNombreDelColorCorte(consultarDegradados);
                        referenciaModelo traerNombreReferencia = new referenciaModelo(litatContenidosC.getReferencia());
                        String nombreReferencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                        cantidadTallosContenido = cantidadTallosContenido + (litatContenidosC.getBulbos() * litatContenidosC.getCanastillas());
                        corteModelo traerNumeroDeTallos = new corteModelo(listaContenidos.getId_contenido(), 0);
                        numeroDeTallosCortados = numeroDeTallosCortados + modeloCortes.getTallos(traerNumeroDeTallos);
                        float diponibilida = (litatContenidosC.getBulbos() * litatContenidosC.getCanastillas()) - modeloCortes.getTallos(traerNumeroDeTallos);
                        nomVaridadDe = nomVaridadDe + nombreVariedad + "<br>";
                        nomColorDe = nomColorDe + nombreColor + "<br>";
                        nomReferenciaDe = nomReferenciaDe + nombreReferencia + "<br>";
                        nomDisponibilidad = nomDisponibilidad + diponibilida + "<br>";
                    }
                }
                float porcentaje = (numeroDeTallosCortados * 100)/cantidadTallosContenido;
                
                if(cantidadTallosContenido == numeroDeTallosCortados){
                    porcentaje = 100;
                } else {
                    porcentaje = (float) (Math.round(porcentaje*100)/100.0);
                }
                out.println("<tr>");
                out.println("<td>");
                out.println("<div class=\"team-info\">");
                out.println("<div class=\"team-details\">");
                out.println("<h6 style='color:green'>"+listaLotes.getTelefono()+"</h6>");
                out.println("<h4>" + listaLotes.getId() + "</h4>");
                out.println("<small>Siembra " + listaLotes.getFechaE() + " Corte " + listaLotes.getDegradado() + "</small>");
                out.println("</div>");
                out.println("</div>");
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomVaridadDe);
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomReferenciaDe);
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomDisponibilidad);
                out.println("</td>");
                out.println("<td>");
                out.println("<div class=\"team-progress\">");
                out.println("<h4>" + porcentaje + "%</h4>");
                out.println("<div class=\"progress-bar\">");
                out.println("<div class=\"indicator success\" style=\"width: " + porcentaje + "%\"></div>");
                out.println("</div>");
                out.println("</div>");
                out.println("<small class=\"tooltip\" tool-tips='" + listaLotes.getTelefono() + " - " + listaLotes.getParametro() + " - " + listaLotes.getNombre() + "'>Detalle corte</small>");
                out.println("</td>");
                out.println("<td>");
                out.println("<div class=\"btn btn-main\">");
                out.println("<a href='#' onclick='editarLote(" + listaLotes.getId() + "); menuBotonesEditar();'><small class=\"tooltip\" tool-tips='Editar'><ion-icon name=\"create-outline\" id='iconoBoton'></ion-icon></small></a> | ");
                out.println("<a href='#' onclick='corteFormulario(" + listaLotes.getId() + ");'><small class=\"tooltip\" tool-tips='Cortar'><ion-icon name='cut-outline' id='iconoBoton'></ion-icon></small></a>");
                out.println("</div>");
                out.println("</td>");
                out.println("</tr>");

                //out.println("<td><a href='#' onclick='' value='' ><ion-icon name='cut-outline' id='iconoBoton'></ion-icon></a></a>");
                //out.println("<td><a href='#' onclick='editarLote(" + listaLotes.getId() + "); menuBotonesEditar();'><ion-icon name='create-outline' id='iconoBoton'></ion-icon></a></td>");
                out.print("</tr>");
                numeroDeTallosCortados = 0;
                cantidadTallosContenido = 0;
                detalles = "";

            }
        }

    }

    private void mostrarFormulariEditarLote(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/Siembra.jsp");
        disptcher.forward(request, response);
    }

    private void buscarBariedadFiltro(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
        List<variedadModelo> variedades;
        int flor = Integer.parseInt(request.getParameter("especie"));
        variedadModelo consularVariedad = new variedadModelo(flor);
        variedades = modeloVariedades.TraerVariedadesBD(consularVariedad);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<select class='input-cod valor-filter-variedad' id='flor-filter' onchange='filter();'>");
            out.println("<option value='0'>variedad</option>");
            for (variedadModelo listaVaridda : variedades) {
                out.println("<option value='" + listaVaridda.getId() + "'>" + listaVaridda.getNombre() + "</option>");
            }
            out.println("</select>");
        }
    }

    private void filtrarTable(HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {
        int especie = Integer.parseInt(request.getParameter("flor"));
        int vairedad = Integer.parseInt(request.getParameter("variedad"));
        int color = Integer.parseInt(request.getParameter("color"));
        String fecha = request.getParameter("fecha");
        int proveedor = 0;//Integer.parseInt(request.getParameter("proveedor"));
        int finca = Integer.parseInt(request.getParameter("finca"));
        int dias = Integer.parseInt(request.getParameter("dias"));

        List<FloresModelo> lotes = null;
        try {
            lotes = modeloFlores.buscarLotesFilter(especie, vairedad, color, fecha, proveedor, finca, dias);
        } catch (Exception e) {

        }
        int numeroDeTallosCortados = 0;
        float cantidadTallosContenido = 0;
        String detalles = "";
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            for (FloresModelo listaLotes : lotes) {
                
                String nomVaridadDe = "";
                String nomColorDe = "";
                String nomReferenciaDe = "";
                String nomDisponibilidad = "";
                
                List<loteModelo> contenido;
                List<loteModelo> IDcontenidos = modeloLotes.traerContenidos(listaLotes.getId());
                for (loteModelo listaContenidos : IDcontenidos) {
                    contenido = modeloLotes.traerContenidosDescripcionUnique(listaContenidos.getId_contenido());
                    for (loteModelo litatContenidosC : contenido) {
                        int variedad = litatContenidosC.getVariedad();
                        variedadModelo consultarVariedadName = new variedadModelo(variedad);
                        String nombreVariedad = modeloVariedades.variedadesNombreEspecie(consultarVariedadName);
                        especieModelo consultarFlor = new especieModelo(litatContenidosC.getEspecie());
                        String nombreEspecie = modeloEspecies.traerNombreEspecie(consultarFlor);
                        colorModelo consultarDegradados = new colorModelo(litatContenidosC.getColor());
                        String nombreColor = modeloColores.traerNombreDelColorCorte(consultarDegradados);
                        referenciaModelo traerNombreReferencia = new referenciaModelo(litatContenidosC.getReferencia());
                        String nombreReferencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                        cantidadTallosContenido = cantidadTallosContenido + (litatContenidosC.getBulbos() * litatContenidosC.getCanastillas());
                        corteModelo traerNumeroDeTallos = new corteModelo(listaContenidos.getId_contenido(), 0);
                        numeroDeTallosCortados = numeroDeTallosCortados + modeloCortes.getTallos(traerNumeroDeTallos);
                        float diponibilida = (litatContenidosC.getBulbos() * litatContenidosC.getCanastillas()) - modeloCortes.getTallos(traerNumeroDeTallos);
                        nomVaridadDe = nomVaridadDe + nombreVariedad + "<br>";
                        nomColorDe = nomColorDe + nombreColor + "<br>";
                        nomReferenciaDe = nomReferenciaDe + nombreReferencia + "<br>";
                        nomDisponibilidad = nomDisponibilidad + diponibilida + "<br>";
                        
                    }
                }
                //hacer filtro por dias 
                
                
                
                float porcentaje = (numeroDeTallosCortados * 100)/cantidadTallosContenido;
                
                if(cantidadTallosContenido == numeroDeTallosCortados){
                    porcentaje = 100;
                } else {
                    porcentaje = (float) (Math.round(porcentaje*100)/100.0);
                }
                out.println("<tr>");
                out.println("<td>");
                out.println("<div class=\"team-info\">");
                out.println("<div class=\"team-details\">");
                out.println("<h6 style='color:green'>"+listaLotes.getTelefono()+"</h6>");
                out.println("<h4>" + listaLotes.getId() + "</h4>");
                out.println("<small>Siembra " + listaLotes.getFechaE() + " Corte " + listaLotes.getDegradado() + "</small>");
                out.println("</div>");
                out.println("</div>");
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomVaridadDe);
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomReferenciaDe);
                out.println("</td>");

                out.println("<td class='td-info-table-firts'>");
                out.println(nomDisponibilidad);
                out.println("</td>");
                out.println("<td>");
                out.println("<div class=\"team-progress\">");
                out.println("<h4>" + porcentaje + "%</h4>");
                out.println("<div class=\"progress-bar\">");
                out.println("<div class=\"indicator success\" style=\"width: " + porcentaje + "%\"></div>");
                out.println("</div>");
                out.println("</div>");
                out.println("<small class=\"tooltip\" tool-tips='" + listaLotes.getTelefono() + " - " + listaLotes.getParametro() + " - " + listaLotes.getNombre() + "'>Detalle corte</small>");
                out.println("</td>");
                out.println("<td>");
                out.println("<div class=\"btn btn-main\">");
                out.println("<a href='#' onclick='editarLote(" + listaLotes.getId() + "); menuBotonesEditar();'><small class=\"tooltip\" tool-tips='Editar'><ion-icon name=\"create-outline\" id='iconoBoton'></ion-icon></small></a> | ");
                out.println("<a href='#' onclick='corteFormulario(" + listaLotes.getId() + ");'><small class=\"tooltip\" tool-tips='Cortar'><ion-icon name='cut-outline' id='iconoBoton'></ion-icon></small></a>");
                out.println("</div>");
                out.println("</td>");
                out.println("</tr>");

                //out.println("<td><a href='#' onclick='' value='' ><ion-icon name='cut-outline' id='iconoBoton'></ion-icon></a></a>");
                //out.println("<td><a href='#' onclick='editarLote(" + listaLotes.getId() + "); menuBotonesEditar();'><ion-icon name='create-outline' id='iconoBoton'></ion-icon></a></td>");
                out.print("</tr>");
                numeroDeTallosCortados = 0;
                cantidadTallosContenido = 0;
                detalles = "";

            }
        }
    }

    private void diasBus(HttpServletResponse response, HttpServletRequest request) {
        int dias = Integer.parseInt(request.getParameter("dias"));
        
    }

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

}
