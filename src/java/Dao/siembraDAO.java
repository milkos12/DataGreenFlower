package Dao;

import Modelo.siembreModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class siembraDAO {

    private DataSource datos;

    public siembraDAO(DataSource datos) {
        this.datos = datos;
    }

    public String generacionDeCodigoLote(siembreModelo generaCodigoDeLoe) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String lote = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "SELECT WEEK(?) as semanas";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, generaCodigoDeLoe.getFecha());
                resul = statement.executeQuery();
                while (resul.next()) {
                    lote = resul.getString("semanas");
                }
                sql = "SELECT YEAR(?) AS AoActual";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, generaCodigoDeLoe.getFecha());
                resul = statement.executeQuery();
                while (resul.next()) {
                    lote = resul.getString("AoActual") + lote;
                }
            } catch (Exception e) {
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return lote;
    }

    public String existenceCodLote(String codLote) throws SQLException {
         Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String confirmacion = "";
        try {
            try {
                
                conexion = datos.getConnection();
                String sql = "select * from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, codLote);
                resul = statement.executeQuery();
                if (resul.next()) {
                    confirmacion = "true";
                }else{
                    confirmacion = "false";
                }

            } catch (Exception e) {
                System.out.println("error existence del cod " + e);
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return confirmacion;
    }

}
