package Dao;

import Modelo.semanaModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class semanasDAO {

    private DataSource datos;

    public semanasDAO(DataSource datos) {
        this.datos = datos;
    }

    public int TraerSemanas(semanaModelo consultarSemanas) throws SQLException {
        int semanas = 0;

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from semanas where id_variedad = ? and id_finca = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarSemanas.getVariedad());
                statement.setInt(2, consultarSemanas.getFinca());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    semanas = resul.getInt("numero_semanas");
                    System.out.println(semanas);
                } else {
                    semanas = 0;
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
                semanas = 0;
            }
            return semanas;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String insertarSemanasBD(semanaModelo AgregarSemanas) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            conexion = datos.getConnection();
            String sql = "select * from semanas where id_finca = ? and id_variedad = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, AgregarSemanas.getFinca());
            statement.setInt(2, AgregarSemanas.getVariedad());
            resul = statement.executeQuery();
            if (resul.next()) {
                sql = "update semanas set numero_semanas = ? where id_finca = ? and id_variedad = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, AgregarSemanas.getNumeroSemnas());
                statement.setInt(2, AgregarSemanas.getFinca());
                statement.setInt(3, AgregarSemanas.getVariedad());
                statement.execute();
            } else {
                sql = "insert into semanas value(default, ?, ?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, AgregarSemanas.getNumeroSemnas());
                statement.setInt(2, AgregarSemanas.getFinca());
                statement.setInt(3, AgregarSemanas.getVariedad());
                statement.execute();
            }
            sql = "select * from fincas where id_finca = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, AgregarSemanas.getFinca());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = "Se ingreso " + AgregarSemanas.getNumeroSemnas() + " semanas a la finca " + resul.getString("nombre_finca") + " con la variedad ";
            }

            sql = "select * from variedad where id_variedad = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, AgregarSemanas.getId());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = estadoProceso + resul.getString("nombre_variedad");
            }

        } catch (Exception e) {
            estadoProceso = AgregarSemanas.getNombre() + " NO se puedo guardar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }
        return estadoProceso;
    }

    public String TraerFechaEstimada(semanaModelo consultarSemanas) throws SQLException {
        String fechaEstimada = "";
        int dias = consultarSemanas.getId() * 7;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "SELECT DATE_ADD(?, INTERVAL ? DAY) as fecha";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, consultarSemanas.getNombre());
                statement.setInt(2, dias);
                resul = statement.executeQuery();
                while (resul.next()) {

                    fechaEstimada = resul.getString("fecha");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return fechaEstimada;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public String CalcualarSemana(semanaModelo calcularSemana) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String fecha = "";
        try {
            conexion = datos.getConnection();
            String sql = "SELECT WEEK(?) as semanas";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, calcularSemana.getFecha());
            resul = statement.executeQuery();
            while (resul.next()) {
                fecha = resul.getString("semanas");
            }

        } finally {
            conexion.close();
            statement.close();
        }
        return fecha;
    }

    public String estimarFechas(int flor, int variedad, int finca, String fecha) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        ResultSet resul2 = null;
        String fechaestimada = "";
        int semanas = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select numero_semanas from semanas where id_finca = ? and id_variedad = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, finca);
                statement.setInt(2, variedad);
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    semanas = resul.getInt("numero_semanas");

                }
                semanas = semanas * 7;
                sql = "select DATE_ADD(?, INTERVAL ? DAY) as estimado";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, fecha);
                statement.setInt(2, semanas);
                resul = statement.executeQuery();
                if (resul.next()) {
                    fechaestimada = resul.getString("estimado");
                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return fechaestimada;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public String traerFechaEs(int CogiodeLoted, int idContenido) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String Fecha = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where codigo_definido = ? and id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, CogiodeLoted);
                statement.setInt(2, idContenido);
                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                    Fecha = resul.getString("estimado");

                }

            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return Fecha;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public int weekDash() throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int fecha = 0;
        try {
            conexion = datos.getConnection();
            String sql = "SELECT WEEK(curdate()) as semanas";
            statement = conexion.prepareStatement(sql);
            resul = statement.executeQuery();
            while (resul.next()) {
                fecha = resul.getInt("semanas");
            }

        } finally {
            conexion.close();
            statement.close();
        }
        return fecha;
    }

}
