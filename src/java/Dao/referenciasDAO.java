package Dao;

import Modelo.referenciaModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class referenciasDAO {

    private DataSource datos;

    public referenciasDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<referenciaModelo> TraerDegradadosBD(referenciaModelo consultarReferencia) throws SQLException {
        List<referenciaModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados where id_variedad = ? order by nombre_degradado ASC";
                statement = conexion.prepareStatement(sql);
                
                statement.setInt(1, consultarReferencia.getVariedad());
                resul = statement.executeQuery();
                while (resul.next()) {
                    int id_degradado = resul.getInt("id_degradado");
                    String degradado = resul.getString("nombre_degradado");
                    referenciaModelo degradadosTEM = new referenciaModelo(id_degradado, degradado);
                    degradados.add(degradadosTEM);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return degradados;
    }

    public String editarNombreEnBD(referenciaModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "update degradados set nombre_degradado = ? where id_degradado =  ?";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, editarNombre.getNombre());
            statement.setInt(2, editarNombre.getId());
            statement.execute();
            estadoProceso = editarNombre.getNombre() + " se Edito correctamente.";

        } catch (Exception e) {
            estadoProceso = editarNombre.getNombre() + " NO se puedo editar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }
        return estadoProceso;
    }

    public String agregarEnBD(referenciaModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;
        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "select * from degradados where nombre_degradado = ?";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();

            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {

                sql = "insert into degradados values (default, ?,?,?)";

                statement = conexion.prepareStatement(sql);
                statement.setString(1, agregar.getNombre());
                statement.setInt(3, agregar.getColor());
                statement.setInt(2, agregar.getVariedad());

                statement.execute();
                estadoProceso = agregar.getNombre() + " se Agrego correctamente.";
            }

        } catch (Exception e) {
            estadoProceso = agregar.getNombre() + " NO se puedo Agregar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }
        return estadoProceso;

    }

    public List<referenciaModelo> TraerTodsoDegradadosBD(referenciaModelo consultarReferencia) throws SQLException {
        List<referenciaModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados order by nombre_degradado ASC";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);
                while (resul.next()) {
                    int id_degradado = resul.getInt("id_degradado");
                    String degradado = resul.getString("nombre_degradado");
                    referenciaModelo degradadosTEM = new referenciaModelo(id_degradado, degradado);
                    degradados.add(degradadosTEM);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return degradados;
    }

    public String traerNombreReferencias(referenciaModelo traerNombreReferencia) throws SQLException {
        String nombreDegradado = "";
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados where id_degradado = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, traerNombreReferencia.getId());
                resul = statement.executeQuery();
                if (resul.next()) {

                    nombreDegradado = resul.getString("nombre_degradado");

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }

        return nombreDegradado;

    }

    public String nombreEspecieContenido(referenciaModelo consultarContenidosE) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String referencia = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select degradados.nombre_degradado  from contenido inner join degradados on degradados.id_degradado = contenido.id_degradado where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarContenidosE.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    referencia = resul.getString("degradados.nombre_degradado");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return referencia;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<referenciaModelo> TraerDegradadosBDNEWP(referenciaModelo consultarDegradados) throws SQLException {
        List<referenciaModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados where id_variedad = ? order by nombre_degradado ASC";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarDegradados.getVariedad());
                resul = statement.executeQuery();
                while (resul.next()) {
                    int id_degradado = resul.getInt("id_degradado");
                    String degradado = resul.getString("nombre_degradado");
                    referenciaModelo degradadosTEM = new referenciaModelo(id_degradado, degradado);
                    degradados.add(degradadosTEM);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return degradados;
    }

    public List<referenciaModelo> TraerDegradadosColorNew(int id) throws SQLException {
        List<referenciaModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados where id_degradado = ? order by nombre_degradado ASC";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, id);
                resul = statement.executeQuery();
                while (resul.next()) {
                    int id_degradado = resul.getInt("id_color");
                    String degradado = resul.getString("nombre_degradado");
                    referenciaModelo degradadosTEM = new referenciaModelo(id_degradado, degradado);
                    degradados.add(degradadosTEM);
                }
            } catch (Exception e) {
                System.out.println( e);
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return degradados;
    }

}
