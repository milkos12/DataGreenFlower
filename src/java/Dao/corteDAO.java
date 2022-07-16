package Dao;

import Modelo.corteModelo;
import Modelo.referenciaModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class corteDAO {

    private DataSource datos;

    public corteDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<corteModelo> getHistorialCorte(corteModelo traerHistorialCorte) throws SQLException {
        List<corteModelo> cortes = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;

        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from cortes where id_contenido_lote = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, traerHistorialCorte.getId());
                //ejecucions de sqL
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int id_corte = resul.getInt("id_corte");
                    String fecha_corte = resul.getString("fecha_corte");
                    int id_referencia = resul.getInt("id_referencia");
                    int id_color = resul.getInt("id_color");
                    int dias_hasta_el_corte = resul.getInt("dias_hasta_el_corte");
                    int id_contenido_corte = resul.getInt("id_contenido_corte");
                    int nacional = resul.getInt("nacionales");
                    int bajas = resul.getInt("bajas");

                    corteModelo cortesTEM = new corteModelo(id_corte, fecha_corte, id_referencia, id_color, dias_hasta_el_corte, id_contenido_corte, nacional, bajas);
                    cortes.add(cortesTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return cortes;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<corteModelo> getCortes(corteModelo contenidoCorteE) throws SQLException {
        List<corteModelo> cortes = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from contenidoscortes where id_corte = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, contenidoCorteE.getId());
                //ejecucions de sqL
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {

                    int id_contenido_corte = resul.getInt("id_contenido_corte");
                    int id_clasificacion = resul.getInt("id_clasificacion");
                    int cantidad = resul.getInt("cantidad");

                    corteModelo cortesTEM = new corteModelo(id_contenido_corte, id_clasificacion, cantidad);
                    cortes.add(cortesTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return cortes;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public int getTallos(corteModelo traerNumeroDeTallos) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        ResultSet resul2 = null;

        int tallos = 0;
        int nacionales = 0;
        int bajas = 0;
        int cantidadFilasCorte = 0;
        int id_contenido_corte = 0;

        int contador = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();

                String sql = "select count(*) as cantidadFilas from cortes where id_contenido_lote = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, traerNumeroDeTallos.getId());
                //ejecucions de sqL
                resul = statement.executeQuery();
                //recorrido resulset
                if (resul.next()) {
                    cantidadFilasCorte = resul.getInt("cantidadFilas");
                }

                int idcontenidios[] = new int[cantidadFilasCorte];
                //intancia sql y el statement
                sql = "select * from cortes where id_contenido_lote = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, traerNumeroDeTallos.getId());
                resul = statement.executeQuery();

                while (resul.next()) {
                    //sumar la cantidad de nacionales y bajas

                    id_contenido_corte = resul.getInt("id_corte");
                    nacionales = nacionales + resul.getInt("nacionales");
                    bajas = bajas + resul.getInt("bajas");
                    idcontenidios[contador] = id_contenido_corte;
                    contador++;

                }

                for (int o = 0; o <= idcontenidios.length; o++) {
                    sql = "select sum(cantidad) as suma from contenidoscortes where id_corte = ?";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, idcontenidios[o]);
                    //ejecucions de sqL
                    resul = statement.executeQuery();
                    //recorrido resulset
                    if (resul.next()) {
                        tallos = tallos + resul.getInt("suma");
                    }
                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            System.out.println("" + tallos + "(" + nacionales + " + " + bajas + ")");
            tallos = tallos + (nacionales + bajas);
            return tallos;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public int diasEntreFechas(String fecha, int idContenido) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String fechaSiembra = "";
        int dias = 0;

        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, idContenido);
                //ejecucions de sqL
                resul = statement.executeQuery();
                //recorrido resulset
                if (resul.next()) {
                    fechaSiembra = resul.getString("fecha");
                }
                sql = "SELECT DATEDIFF(?, ?)as 'dias'";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, fecha);
                statement.setString(2, fechaSiembra);
                //ejecucions de sqL
                resul = statement.executeQuery();
                //recorrido resulset
                if (resul.next()) {
                    dias = resul.getInt("dias");
                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }

        } finally {
            conexion.close();
            statement.close();

        }
        return dias;
    }

    public void actualizarCorte(int idCorte, String fecha, int diasHasElCorte, int[] tallosPorClasificaion, int tallosNacionales, int tallosTB, int especieLote) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;

        ResultSet resul = null;
        String fechaSiembra = "";
        int dias = 0;
        int contadorClasificaciones = 0;
        int contenidoActive = 0;

        //conexion 
        try {
            try {
                //actualizar el corte pose se cambio el valo de los nacinales o fechas d corte 
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "update cortes set fecha_corte = ?, dias_hasta_el_corte= ?, nacionales = ?, bajas = ? where id_corte = ?";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, fecha);
                statement.setInt(2, diasHasElCorte);
                statement.setInt(3, tallosNacionales);
                statement.setInt(4, tallosTB);
                statement.setInt(5, idCorte);
                statement.execute();
                System.out.println("datos ingresador fecha "+ fecha + " dias hasta el corte " + diasHasElCorte + " nacionales " + tallosNacionales + " bajas " + tallosTB + " **********");
                //ejecucions de sqL
                //buscar la cantidad de clasificaciones por la especie del lote para saber como agregar las cantidades ingresadas en la tabla de cortes 
                sql = "select * from clasificaciones where id_especie = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, especieLote);
                resul = statement.executeQuery();

                int logitudResulset = 0;

                while (resul.next()) {
                    logitudResulset++;
                }

                int clasificaciones[] = new int[logitudResulset];
                sql = "select * from clasificaciones where id_especie = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, especieLote);
                resul = statement.executeQuery();
                int contador = 0;
                while (resul.next()) {
                    clasificaciones[contador] = resul.getInt("id_clasificacion");
                    contador++;
                }

                int cantidadClasificaciones = clasificaciones.length;
                //con base a las calsificaciones se itera para actualizar valores o agregar 
                for (int b = 0; b < cantidadClasificaciones; b++) {
                    sql = "select * from contenidoscortes where id_corte = ? and id_clasificacion = ?";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, idCorte);
                    statement.setInt(2, clasificaciones[b]);

                    resul = statement.executeQuery();

                    if (resul.next()) {

                        sql = "update contenidoscortes set cantidad = ? where id_corte = ? and id_clasificacion = ?";
                        statement = conexion.prepareStatement(sql);
                        statement.setInt(1, tallosPorClasificaion[b + 1]);
                        statement.setInt(2, idCorte);
                        statement.setInt(3, clasificaciones[b]);
                        statement.execute();
                        System.out.println("clasificacion  " + b + "  cantidad tallos  " + tallosPorClasificaion[b + 1]);
                        contenidoActive = 1;

                    }

                    contenidoActive = 0;
                    contadorClasificaciones++;

                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }

        } finally {
            conexion.close();
            statement.close();
        }
    }

    public void agregarCorte(int idCorte, String fecha, int diasHasElCorte, int[] tallosPorClasificaion, int tallosNacionales, int tallosTB, int especieLote) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        Statement statementC = null;
        ResultSet resul = null;
        String fechaSiembra = "";
        int dias = 0;
        int contadorClasificaciones = 0;
        int contenidoActive = 0;

        int referencia = 0;
        int color = 0;
        int id_corte = 0;
        int id_contenido_lote = 0;
        //conexion 
        try {
            try {
                //actualizar el corte pose se cambio el valo de los nacinales o fechas d corte 
                conexion = datos.getConnection();

                String sql = "select * from contenido where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, idCorte);

                resul = statement.executeQuery();

                if (resul.next()) {
                    referencia = resul.getInt("id_degradado");
                    color = resul.getInt("id_color");
                    id_contenido_lote = resul.getInt("id_contenido");

                }
                System.out.println("90909090909090¿'¿'¿'¿'¿'¿¿");
                System.out.println("fecha " + fecha + " referencia " + referencia + " color " + color + " dias de corte " + diasHasElCorte + " id contenidos lote " + id_contenido_lote + " tallos nacionales " + tallosNacionales + " tallos bajas " + tallosTB);
                //intancia sql y el statement
                sql = "insert into cortes values(default,?,?,?,?,null,?,?,?)";
                
                statement = conexion.prepareStatement(sql);
                statement.setString(1, fecha);
                statement.setInt(2, referencia);//referencia
                statement.setInt(3, color);//color
                statement.setInt(4, diasHasElCorte);
                statement.setInt(5, id_contenido_lote);//id contenido lote
                statement.setInt(6, tallosNacionales);
                statement.setInt(7, tallosTB);
                statement.execute();
                sql = "SELECT @@identity AS id;";
                statement = conexion.prepareStatement(sql);
                resul = statement.executeQuery();

                if (resul.next()) {
                    id_corte = resul.getInt("id");
                }
                //buscar la cantidad de clasificaciones por la especie del lote para saber como agregar las cantidades ingresadas en la tabla de cortes 
                sql = "select * from clasificaciones where id_especie = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, especieLote);
                resul = statement.executeQuery();

                int logitudResulset = 0;

                while (resul.next()) {
                    logitudResulset++;
                }
                int clasificaciones[] = new int[logitudResulset];
                sql = "select * from clasificaciones where id_especie = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, especieLote);
                resul = statement.executeQuery();
                int contador = 0;
                while (resul.next()) {
                    clasificaciones[contador] = resul.getInt("id_clasificacion");
                    contador++;
                }

                int cantidadClasificaciones = clasificaciones.length;
                //con base a las calsificaciones se itera para actualizar valores o agregar 
                for (int b = 0; b < cantidadClasificaciones; b++) {

                    sql = "insert into contenidoscortes values(default, ?, ?, ?)";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, clasificaciones[b]);
                    statement.setInt(2, tallosPorClasificaion[b + 1]);
                    statement.setInt(3, id_corte);
                    statement.execute();

                }
                contenidoActive = 0;
                contadorClasificaciones++;

            } catch (Exception e) {
                System.out.println("_-___--el error es --- " + e);
            }

        } finally {
            conexion.close();
            statement.close();
        }
    }

    public void eliminarContenidos(int id_eliminar) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        Statement statementC = null;
        ResultSet resul = null;
        try {
            try {
                conexion = datos.getConnection();
                String sql = "delete from contenidoscortes where id_corte = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, id_eliminar);
                statement.execute();

                sql = "delete from cortes where id_corte = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, id_eliminar);
                statement.execute();
            } catch (Exception e) {
                System.out.println("error en eliminacion de contenidos cortes " + e);
            }
        } finally {
            conexion.close();
            statement.close();
        }

    }

}
