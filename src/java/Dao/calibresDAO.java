package Dao;

import Modelo.calibreModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class calibresDAO {

    private DataSource datos;

    public calibresDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<calibreModelo> TraerCalibresBD() throws SQLException {
        List<calibreModelo> calibres = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from  calibres";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {
                    int id_calibre = resul.getInt("id_calibre");
                    String calibre = resul.getString("nombre_calibre");

                    calibreModelo calibreTEM = new calibreModelo(id_calibre, calibre);
                    calibres.add(calibreTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return calibres;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String agregarEnBD(calibreModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;

        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "select * from calibres where nombre_calibre = ?";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();

            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {

                sql = "insert into calibres values(default, ?)";

                statement = conexion.prepareStatement(sql);
                statement.setString(1, agregar.getNombre());

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

    public String editarNombreEnBD(calibreModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "update calibres set nombre_calibre = ? where id_calibre = ?";

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

}
