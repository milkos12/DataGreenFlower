package Controladores;

import java.time.LocalDate;
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
import Modelo.clasificacionModelo;
import Modelo.codigoModelo;
import Modelo.colorModelo;
import Modelo.corteModelo;
import Modelo.especieModelo;
import Modelo.loteModelo;
import Modelo.referenciaModelo;
import Modelo.semanaModelo;
import Modelo.variedadModelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

@WebServlet(name = "controladorCorte", urlPatterns = {"/controladorCorte"})
public class controladorCorte extends HttpServlet {

    private static final long serialVersionUID = 1L;
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
    private lotesDAO modeloLotes;
    private clasificaionesDAO modeloClasificaciones;
    private corteDAO modeloCortes;
    @Resource(name = "jdbc/FLOWER")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
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
                case "mostarFormularioCorte":
                    formularioCorte(request, response);
                    break;
                case "fincaDelLote":
                    traerFinca(request, response);
                    break;
                case "codigosContenidosLote":
                    traerCodigosDeLoSembrado(request, response);
                    break;
                case "nombreEspecie":
                    nombreEspecie(request, response);
                    break;
                case "contenidosUrlCentroTabla":
                    contenidosUrl(request, response);
                    break;
                case "fechaSiembra":
                    fechaSimbra(request, response);
                    break;
                case "semanaSiembra":
                    semanaSimbra(request, response);
                    break;
                case "historialCorte":
                    histrialCorte(request, response);
                    break;
                case "botonesMenuCorte":
                    botonesMenu(request, response);
                    break;
                case "headerTable":
                    establecerHeaderTabla(request, response);
                    break;
                case "cacularDiasHastaLaFecha":
                    calcularDias(request, response);
                    break;
                case "actulaizarContador":
                    actualizarContador(request, response);
                    break;
                case "actulaizarContadorClasificaciones":
                    actualizarContadorClasificaciones(request, response);
                    break;
                case "contenidodNuevaFila":
                    contenidoNuevaFila(request, response);
                    break;
                case "pieTabla":
                    pieTabla(request, response);
                    break;
                case "guardarCorte":
                    guardarCorte(request, response);
                    break;
                default:
                    System.out.println("instruccion no contemplado controlador lotes ");
                    break;

            }
        } catch (Exception e) {
            System.out.println("-Error en el switch principal-" + e);
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

    private void formularioCorte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/Corte.jsp");
        disptcher.forward(request, response);
    }

    private void traerFinca(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String finca = modeloLotes.buscarFincaLoteNombre(buscarLotes);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(finca);
        }

    }

    private void traerCodigosDeLoSembrado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<loteModelo> IDcontenidos;
        List<loteModelo> contenido;
        int CogiodeLoted = Integer.parseInt(request.getParameter("lote"));
        IDcontenidos = modeloLotes.traerContenidos(CogiodeLoted);
        int especie = 0;
        int variedad = 0;
        int color = 0;
        int referencia = 0;

        int codigoCohincidencia = 0;
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<datalist id=\"codigos\">");
            for (loteModelo listaContenidos : IDcontenidos) {
                contenido = modeloLotes.traerContenidosDescripcion(listaContenidos.getId_contenido());
                for (loteModelo litatContenidosC : contenido) {
                    especie = litatContenidosC.getEspecie();
                    variedad = litatContenidosC.getVariedad();
                    color = litatContenidosC.getColor();
                    referencia = litatContenidosC.getReferencia();
                    codigoCohincidencia = modeloCodigos.traerAutoRellenoCodigo(especie, variedad, color, referencia);
                    out.println("<option> " + codigoCohincidencia + "</option>");

                }
            }
            out.println("</datalist>");
        }
    }

    private void nombreEspecie(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String especie = modeloLotes.buscarEspecieLoteNombre(buscarLotes);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(especie);
        }
    }

    private void contenidosUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<loteModelo> IDcontenidos;
        List<loteModelo> contenido;
        int CogiodeLoted = Integer.parseInt(request.getParameter("lote"));
        IDcontenidos = modeloLotes.traerContenidos(CogiodeLoted);
        int especie = 0;
        int variedad = 0;
        int color = 0;
        int referencia = 0;

        String nombreVariedad = "";
        String nombreEspecie = "";
        String nombreColor = "";
        String nombreReferencia = "";
        float cantidadTallosContenido = 0;
        float numeroDeTallosCortados = 0;
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            for (loteModelo listaContenidos : IDcontenidos) {
                contenido = modeloLotes.traerContenidosDescripcionUnique(listaContenidos.getId_contenido());
                for (loteModelo litatContenidosC : contenido) {

                    variedad = litatContenidosC.getVariedad();
                    variedadModelo consultarVariedadName = new variedadModelo(variedad);
                    nombreVariedad = modeloVariedades.variedadesNombreEspecie(consultarVariedadName);
                    especieModelo consultarFlor = new especieModelo(litatContenidosC.getEspecie());
                    nombreEspecie = modeloEspecies.traerNombreEspecie(consultarFlor);
                    colorModelo consultarDegradados = new colorModelo(litatContenidosC.getColor());
                    nombreColor = modeloColores.traerNombreDelColorCorte(consultarDegradados);
                    referenciaModelo traerNombreReferencia = new referenciaModelo(litatContenidosC.getReferencia());
                    nombreReferencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                    cantidadTallosContenido = litatContenidosC.getBulbos() * litatContenidosC.getCanastillas();
                    corteModelo traerNumeroDeTallos = new corteModelo(listaContenidos.getId_contenido(), 0);
                    numeroDeTallosCortados = modeloCortes.getTallos(traerNumeroDeTallos);

                    numeroDeTallosCortados = cantidadTallosContenido - numeroDeTallosCortados;
                    out.println("<a href='#' onclick=" + '"' + "corteCod(" + CogiodeLoted + "," + "'" + "" + nombreReferencia + "" + "'" + "," + litatContenidosC.getReferencia() + "," + litatContenidosC.getEspecie() + "," + listaContenidos.getId_contenido() + "," + cantidadTallosContenido + "," + cantidadTallosContenido + "); lipiarContenidosEliminar();" + '"' + " class='btn-url-corte'> -" + nombreVariedad + " " + nombreColor + " " + nombreReferencia + " #T: " + numeroDeTallosCortados + "</a><br>");

                }
            }

        }
    }

    private void fechaSimbra(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String fehca = modeloLotes.buscarFechaDeSiembra(buscarLotes);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(fehca);
        }
    }

    private void semanaSimbra(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int lote = Integer.parseInt(request.getParameter("lote"));
        loteModelo buscarLotes = new loteModelo(lote);
        String fecha = modeloLotes.buscarFechaDeSiembra(buscarLotes);
        semanaModelo calcularSemana = new semanaModelo(fecha);
        fecha = modeloSemanas.CalcualarSemana(calcularSemana);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(fecha);
        }
    }

    private void establecerHeaderTabla(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int especie = Integer.parseInt(request.getParameter("especie"));
        List<clasificacionModelo> clasificaciones;
        clasificacionModelo traerClasificaciones = new clasificacionModelo(especie);
        clasificaciones = modeloClasificaciones.traerClasificacionesEspecificas(traerClasificaciones);

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<td class=\"capos_tabla_siembra\" >  F.Corte  </td>");
            out.print("<td class=\"capos_tabla_siembra\" >  Referencia  </td>");
            out.print("<td class=\"capos_tabla_siembra\" >  Color  </td>");
            out.print("<td class=\"capos_tabla_siembra\" >  D.HastaCorte  </td>");
            for (clasificacionModelo listaClasificaiones : clasificaciones) {

                out.print("<td class=\"capos_tabla_siembra\" > " + listaClasificaiones.getNombre() + "  </td>");

            }
            out.print("<td class=\"capos_tabla_siembra\" >  #T. Nacional  </td>");
            out.print("<td class=\"capos_tabla_siembra\" >  #T. Baja  </td>");
            out.print("<td class=\"capos_tabla_siembra\" >  No. Tallos  </td>");
        }

    }

    private void histrialCorte(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        List<corteModelo> historialCorte;
        List<corteModelo> contenidoCorte;
        LocalDate todaysDate = LocalDate.now();
        int contador = 1;
        int id_contenido = Integer.parseInt(request.getParameter("contenido"));
        corteModelo traerHistorialCorte = new corteModelo(id_contenido, 0);
        historialCorte = modeloCortes.getHistorialCorte(traerHistorialCorte);
        //clasificaciones 
        int especie = Integer.parseInt(request.getParameter("especie"));

        List<clasificacionModelo> clasificaciones;
        clasificacionModelo traerClasificaciones = new clasificacionModelo(especie);
        clasificaciones = modeloClasificaciones.traerClasificacionesEspecificas(traerClasificaciones);

        int confirmacionClasificacionCorte = 0;
        int diasEntreFechas = 0;
        int contadorClasificaciones = 1;
        int contadorId = 1;
        String referencia = "";
        String color = "";

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            if (historialCorte.size() > 0) {
                for (corteModelo listaHistorialCorte : historialCorte) {
                    corteModelo contenidoCorteE = new corteModelo(listaHistorialCorte.getId(), 0);
                    contenidoCorte = modeloCortes.getCortes(contenidoCorteE);
                    referenciaModelo traerNombreReferencia = new referenciaModelo(listaHistorialCorte.getReferencia());
                    referencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                    colorModelo consultarDegradados = new colorModelo(listaHistorialCorte.getColor());
                    color = modeloColores.traerNombreDelColorCorte(consultarDegradados);
                    out.print("<tr id='fila" + contador + "'>");
                    out.print("<input type='hidden' name='idCorte" + contador + "' value='" + listaHistorialCorte.getId() + "'>");
                    out.print("<input type='hidden' name='id" + listaHistorialCorte.getId() + "' value='" + contador + "'>");
                    out.print("<td><input type='date' class='btn-class-date-corte' value='" + listaHistorialCorte.getFecha() + "' name='contenidoFecha" + contador + "' id='contenidoFecha" + contador + "' onchange='diasCorte(" + contador + ")'; diasPromedio();></td>");
                    out.print("<td>" + referencia + "</td>");
                    out.print("<td>" + color + "</td>");
                    out.print("<td> <input type='number' name='diasContenido" + contador + "' id='diasContenido" + contador + "' class='dias' value='" + listaHistorialCorte.getDiasHasElCorte() + "' readonly></td>");
                    for (clasificacionModelo listaClasificiaciones : clasificaciones) {
                        for (corteModelo listaContenido : contenidoCorte) {

                            if (listaContenido.getClasificacion() == listaClasificiaciones.getId()) {
                                out.print("<td><input type='number' class='clasificacion" + contadorClasificaciones + " valoresColum" + contador + "' name='contenido" + contadorId + "' id='contenido" + contadorId + "' value='" + listaContenido.getTallos() + "' onkeyup='totales(); totalesFila(\"contenido" + contadorId + "\"); '></td>");
                                confirmacionClasificacionCorte = 1;
                                contadorClasificaciones++;
                                contadorId++;
                            }

                        }
                        if (confirmacionClasificacionCorte == 0) {
                            out.print("<td><input type='number' class='clasificacion" + contadorClasificaciones + " valoresColum" + contador + " tallosEliminacion' name='contenido" + contadorId + "' id='contenido" + contadorId + "' value='0' onkeyup='totales(); totalesFila(\"contenido" + contadorId + "\");'></td>");
                            contadorClasificaciones++;
                            contadorId++;
                        }
                        confirmacionClasificacionCorte = 0;
                    }

                    out.print("<td><input type='number' value='" + listaHistorialCorte.getNacional() + "' name='contenidoNacional" + contador + "' id='contenidoNacional" + contador + "' class='nacionalesColum valoresColum" + contador + " tallosEliminacion' onkeyup='totalesNacionales(); totalesFila(\"contenidoNacional" + contador + "\");'></td>");
                    out.print("<td><input type='number' value='" + listaHistorialCorte.getBajas() + "' name='contenidoBaja" + contador + "' id='contenidoBaja" + contador + "' class='bajasColum valoresColum" + contador + " tallosEliminacion' onkeyup='totalesBajas(); totalesFila(\"contenidoBaja" + contador + "\");'></td>");

                    out.print("<td><input type='number' value='0' id='totalTallosContenido" + contador + "' class='totalesTallos'></td>");
                    out.println("<td>");
                    out.println("<ion-icon onclick='eliminarContenidoCorte(" + contador + "," + listaHistorialCorte.getId() + ");' name='close-outline' id='iconoBotonEliminarEspecifica'></ion-icon>");
                    out.println("</td>");
                    out.print("</tr>");
                    contador++;
                    contadorClasificaciones = 1;

                }
            } else {
                loteModelo consultarContenidos = new loteModelo(id_contenido);
                color = modeloLotes.colorContenido(consultarContenidos);

                referenciaModelo consultarContenidosE = new referenciaModelo(id_contenido);
                referencia = modeloReferencias.nombreEspecieContenido(consultarContenidosE);
            }

            contadorClasificaciones = 1;

            out.print("<tr id='fila" + contador + "'>");
            out.print("<td><input type='date' class='btn-class-date-corte' value='" + todaysDate + "' name='contenidoFecha" + contador + "' id='contenidoFecha" + contador + "' onchange='diasCorte(" + contador + "); diasPromedio();'></td>");
            out.print("<td>" + referencia + "</td>");
            out.print("<td>" + color + "</td>");
            out.print("<td><input type='number' name='diasContenido" + contador + "' id='diasContenido" + contador + "' class='dias' value='0' readonly></td>");

            for (clasificacionModelo listaClasificiaciones : clasificaciones) {
                out.print("<td><input type='number' class='clasificacion" + contadorClasificaciones + " valoresColum" + contador + " tallosEliminacion' name='contenido" + contadorId + "' id='contenido" + contadorId + "' value='0' onkeyup='totales(); totalesFila(\"contenido" + contadorId + "\");'></td>");
                contadorClasificaciones++;
                contadorId++;

            }

            out.print("<td><input type='number' value='0' name='contenidoNacional" + contador + "' id='contenidoNacional" + contador + "' class='nacionalesColum valoresColum" + contador + " tallosEliminacion' onkeyup='totalesNacionales(); totalesFila(\"contenidoNacional" + contador + "\");'></td>");
            out.print("<td><input type='number' value='0' name='contenidoBaja" + contador + "' id='contenidoBaja" + contador + "' class='bajasColum valoresColum" + contador + " tallosEliminacion' onkeyup='totalesBajas(); totalesFila(\"contenidoBaja" + contador + "\");'></td>");
            out.print("<td><input type='number' value='0' name='totalTallosContenido" + contador + "' id='totalTallosContenido" + contador + "' class='totalesTallos'></td>");
            out.print("</tr>");

            contador++;

        }
    }

    private void botonesMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher disptcher = request.getRequestDispatcher("vistas/barraBotonesCorte.jsp");
        disptcher.forward(request, response);
    }

    private void calcularDias(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String fecha = request.getParameter("fecha");
        int idContenido = Integer.parseInt(request.getParameter("idContenido"));
        int diasEntreFechas = modeloCortes.diasEntreFechas(fecha, idContenido);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print(diasEntreFechas);
        }
    }

    private void actualizarContador(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<corteModelo> historialCorte;
        int id_contenido = Integer.parseInt(request.getParameter("contenido"));
        corteModelo traerHistorialCorte = new corteModelo(id_contenido, 0);
        historialCorte = modeloCortes.getHistorialCorte(traerHistorialCorte);
        try ( PrintWriter out = response.getWriter()) {
            out.print(historialCorte.size() + 1);
        }
    }

    private void actualizarContadorClasificaciones(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int especie = Integer.parseInt(request.getParameter("especie"));
        List<clasificacionModelo> clasificaciones;
        clasificacionModelo traerClasificaciones = new clasificacionModelo(especie);
        clasificaciones = modeloClasificaciones.traerClasificacionesEspecificas(traerClasificaciones);
        try ( PrintWriter out = response.getWriter()) {
            out.print(clasificaciones.size());
        }
    }

    private void contenidoNuevaFila(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<corteModelo> historialCorte;
        List<corteModelo> contenidoCorte;
        LocalDate todaysDate = LocalDate.now();
        int contador = Integer.parseInt(request.getParameter("contador"));
        int id_contenido = Integer.parseInt(request.getParameter("contenido"));
        corteModelo traerHistorialCorte = new corteModelo(id_contenido, 0);
        historialCorte = modeloCortes.getHistorialCorte(traerHistorialCorte);

        //clasificaciones 
        int especie = Integer.parseInt(request.getParameter("especie"));
        List<clasificacionModelo> clasificaciones;
        clasificacionModelo traerClasificaciones = new clasificacionModelo(especie);
        clasificaciones = modeloClasificaciones.traerClasificacionesEspecificas(traerClasificaciones);

        int confirmacionClasificacionCorte = 0;
        int diasEntreFechas = 0;
        int contadorId = 1;
        int contadorClasificaciones = 1;
        String referencia = "";
        String color = "";

        for (corteModelo listaHistorialCorte : historialCorte) {

            corteModelo contenidoCorteE = new corteModelo(listaHistorialCorte.getId(), 0);
            contenidoCorte = modeloCortes.getCortes(contenidoCorteE);
            for (corteModelo listaContenido : contenidoCorte) {
                referenciaModelo traerNombreReferencia = new referenciaModelo(listaHistorialCorte.getReferencia());
                referencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                colorModelo consultarDegradados = new colorModelo(listaHistorialCorte.getColor());
                color = modeloColores.traerNombreDelColorCorte(consultarDegradados);

            }
        }

        if (historialCorte.size() == 0) {
            List<loteModelo> contenido;
            int idContenido = Integer.parseInt(request.getParameter("contenido"));
            contenido = modeloLotes.traerContenidosDescripcion(idContenido);

            for (loteModelo listaContedio : contenido) {
                referenciaModelo traerNombreReferencia = new referenciaModelo(listaContedio.getReferencia());
                referencia = modeloReferencias.traerNombreReferencias(traerNombreReferencia);
                colorModelo consultarDegradados = new colorModelo(listaContedio.getColor());
                color = modeloColores.traerNombreDelColorCorte(consultarDegradados);
            }

        }
        contadorId = contador * clasificaciones.size();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            contadorClasificaciones = 1;

            out.print("<td><input type='date' class='btn-class-date-corte' value='" + todaysDate + "' name='contenidoFecha" + contador + "' id='contenidoFecha" + contador + "' onchange='diasCorte(" + contador + "); diasPromedio();'></td>");
            out.print("<td>" + referencia + "</td>");
            out.print("<td>" + color + "</td>");
            out.print("<td><input type='number' name='diasContenido" + contador + "' id='diasContenido" + contador + "' class='dias' value='0' readonly></td>");

            for (clasificacionModelo listaClasificiaciones : clasificaciones) {
                out.print("<td><input type='number' class='clasificacion" + contadorClasificaciones + " valoresColum" + contador + "' name='contenido" + contadorId + "' id='contenido" + contadorId + "' value='0' onkeyup='totales(); totalesFila(\"contenido" + contadorId + "\");'></td>");
                contadorClasificaciones++;
                contadorId++;
            }

            out.print("<td><input type='number' value='0' name='contenidoNacional" + contador + "' id='contenidoNacional" + contador + "' class='nacionalesColum valoresColum" + contador + "' onkeyup='totalesNacionales(); totalesFila(\"contenidoNacional" + contador + "\");'></td>");
            out.print("<td><input type='number' value='0' name='contenidoBaja" + contador + "' id='contenidoBaja" + contador + "' class='bajasColum valoresColum" + contador + "' onkeyup='totalesBajas();totalesFila(\"contenidoBaja" + contador + "\");'></td>");
            out.print("<td><input type='number' value='0' name='totalTallosContenido" + contador + "' id='totalTallosContenido" + contador + "' class='totalesTallos'></td>");

            contador++;

        }

    }

    private void pieTabla(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int especie = Integer.parseInt(request.getParameter("especie"));
        int contador = 1;
        List<clasificacionModelo> clasificaciones;
        clasificacionModelo traerClasificaciones = new clasificacionModelo(especie);
        clasificaciones = modeloClasificaciones.traerClasificacionesEspecificas(traerClasificaciones);

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<tr>");
            out.print("<td class=\"cellFot\"> Días Promedio</td>");
            out.print("<td class=\"cellFot\"> D.Máximo   </td>");
            out.print("<td class=\"cellFot\"> D.Míminimo   </td>");
            out.print("<td class=\"cellFot\"> D.Moda   </td>");
            for (clasificacionModelo listaClasificaiones : clasificaciones) {
                out.print("<td class=\"capos_tabla_siembra\" ><input type='number' id='totalClasificacion" + contador + "' value='0' readonly></td>");
                contador++;

            }
            out.print("<td><input type='number' id='totalTalllosNacional' value='0' readonly></td>");
            out.print("<td><input type='number' id='totalTalllosBaja' value='0' readonly></td>");
            out.print("<td><input type='number' id='totalTalllosTabla' value='0' readonly></td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td class=\"cellFot\"> <input type=\"number\" id=\"diasPromedio\" value=\"0\" readonly>   </td>");
            out.print("<td class=\"cellFot\"> <input type=\"number\" id=\"diaMax\" value=\"0\" readonly>   </td>");
            out.print("<td class=\"cellFot\"> <input type=\"number\" id=\"diaMin\" value=\"0\" readonly>  </td>");
            out.print("<td class=\"cellFot\"> <input type=\"number\" id=\"diaModa\" value=\"0\" readonly>   </td>");
            contador = 1;
            for (clasificacionModelo listaClasificaiones : clasificaciones) {
                out.print("<td id='cellProcentaje'><input type='number' id='porcentajeClasificacion" + contador + "' value='0' class='porcentajes' readonly>%</td>");
                contador++;
            }

            out.print("<td id='cellProcentaje'><input type='number' id='porcentajeNacional' value='0' class='porcentajes' readonly>%</td>");
            out.print("<td id='cellProcentaje'><input type='number' id='porcentajeBajas' value='0' class='porcentajes' readonly>%</td>");
            out.print("<td><input type='number' id='totalPorcentaje' value='0' readonly></td>");
            out.print("</tr>");

        }
    }

    private void guardarCorte(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<corteModelo> historialCorte;
        int contador = Integer.parseInt(request.getParameter("contador"));
        int cantidadClasificaicones = Integer.parseInt(request.getParameter("cantidadClasificaciones"));
        int lote = Integer.parseInt(request.getParameter("lote"));
        int id_contenido = Integer.parseInt(request.getParameter("contenidoLote"));
        int contadorContenidos = 1;
        int nuevasFilasCorte = 1;
        int especieLote = Integer.parseInt(request.getParameter("especieLote"));
        corteModelo traerHistorialCorte = new corteModelo(id_contenido, 0);
        historialCorte = modeloCortes.getHistorialCorte(traerHistorialCorte);
        int confirmacion_continuar_proceso = 0;
        int contador_comprobacion = 0;
        int conprobacion_filas_eliminadas = 0;
        int cantidad_contenidos_corte = historialCorte.size();
        int contadorEliminar = Integer.parseInt(request.getParameter("contadorEliminar")) - 1;
        int sumEliinar = 1;
        int tallosPorClasificaion[] = new int[cantidadClasificaicones + 1];
        //eliminar datos
        int confirmacion = 0;
        int arrayEliminar[] = new int[contadorEliminar + sumEliinar];
        int row = 0;
        int cantidadActualizados = 0;
        //array id de contenios para eliminar 
        if (contadorEliminar > 0) {

            for (int u = 1; u <= contadorEliminar; u++) {
                try {
                    int id_eliminar = Integer.parseInt(request.getParameter("eliminar" + u));
                    //modeloCortes.eliminarContenidos(id_eliminar);
                    conprobacion_filas_eliminadas = 1;
                    arrayEliminar[u] = Integer.parseInt(request.getParameter("eliminar" + u));
                } catch (Exception e) {

                }
            }
        }
        int confiDelete = 0;
        for (corteModelo listaIdCorte : historialCorte) {
            try {
                //confirmar q el contenido no este en el array de ids eliminados 
                if (contadorEliminar > 0) {
                    for (int p = 1; p <= contadorEliminar; p++) {
                        if (arrayEliminar[p] == listaIdCorte.getId()) {
                            confiDelete = 1;
                            contadorContenidos = contadorContenidos + 3;
                            System.out.println("eliminado-------------  " + arrayEliminar[p]);
                        }
                    }
                }
            } catch (Exception e) {

            }

            if (confiDelete == 0) {
                try {
                    //coprobar q el id no se este en el arry
                    cantidadActualizados = cantidadActualizados + 1;
                    System.out.println("atulizado *************************** " + listaIdCorte.getId());
                    row = Integer.parseInt(request.getParameter("id" + listaIdCorte.getId()));
                    int idCorte = Integer.parseInt(request.getParameter("idCorte" + row));
                    String fecha = request.getParameter("contenidoFecha" + row);
                    int diasHasElCorte = Integer.parseInt(request.getParameter("diasContenido" + row));

                    for (int a = 1; a <= cantidadClasificaicones; a++) {
                        tallosPorClasificaion[a] = Integer.parseInt(request.getParameter("contenido" + contadorContenidos));

                        contadorContenidos++;
                    }

                    int tallosNacionales = Integer.parseInt(request.getParameter("contenidoNacional" + row));
                    int tallosTB = Integer.parseInt(request.getParameter("contenidoBaja" + row));
                    modeloCortes.actualizarCorte(idCorte, fecha, diasHasElCorte, tallosPorClasificaion, tallosNacionales, tallosTB, especieLote);
                } catch (Exception e) {

                }
            }
            confiDelete = 0;
        }

        for (int o = 1; o <= contadorEliminar; o++) {
            try {

                modeloCortes.eliminarContenidos(arrayEliminar[o]);

            } catch (Exception e) {

            }
        }
        int contenidoInicial = 0;
        if (contadorEliminar > 0) {
            contenidoInicial = cantidadActualizados + contadorEliminar;
            //contadorContenidos = contadorContenidos - 1;
        } else {
            contenidoInicial = cantidadActualizados;
            //contadorContenidos = contadorContenidos + 1;

        }
        int numero_de_tallos = 0;
        //agrgar nuevos datos 
        int primerCiclo = 0; 
        if (contenidoInicial == cantidad_contenidos_corte) {
            cantidad_contenidos_corte = cantidad_contenidos_corte;
            System.out.println("/-/-/-/-/ cantidad corte " + cantidad_contenidos_corte + "******** contador " + contador);
            System.out.println(".,.,.,.   " + contadorContenidos);
            for (int b = cantidad_contenidos_corte; b <= contador; b++) {
                try {
                    //establacer desde donde se debe empezar a extraer contenidos 
                    int idCorte = 0;
                    int conteoBreak = 0;
                    String fecha = request.getParameter("contenidoFecha" + (b + 1));
                    int diasHasElCorte = Integer.parseInt(request.getParameter("diasContenido" + (b + 1)));
                    System.out.println("-----.------ " + contadorContenidos);
                    for (int a = 1; a <= cantidadClasificaicones; a++) {
                        tallosPorClasificaion[a] = Integer.parseInt(request.getParameter("contenido" + contadorContenidos));
                        contadorContenidos++;
                        numero_de_tallos = numero_de_tallos + tallosPorClasificaion[a];
                    }
                    if(primerCiclo == 0){
                        primerCiclo = 1;
                        contadorContenidos = contadorContenidos +2;
                    }

                    int tallosNacionales = Integer.parseInt(request.getParameter("contenidoNacional" + (b + 1)));
                    int tallosTB = Integer.parseInt(request.getParameter("contenidoBaja" + (b + 1)));
                    //comprobar que la fila tenga tallos para porder guardar 
                    numero_de_tallos = numero_de_tallos + tallosNacionales + tallosTB;
                    if (numero_de_tallos > 0) {
                        modeloCortes.agregarCorte(id_contenido, fecha, diasHasElCorte, tallosPorClasificaion, tallosNacionales, tallosTB, especieLote);
                    }
                    numero_de_tallos = 0;
                    confirmacion_continuar_proceso = 0;
                } catch (Exception e) {
                    System.out.println("error,,,,,, " + e);
                }
            }
        }
        
        request.setAttribute("comfirm", lote);
        RequestDispatcher disptcher = request.getRequestDispatcher("/vistas/DataFlower_Corte.jsp");
        disptcher.forward(request, response);
    }
}
