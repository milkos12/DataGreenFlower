package Dao;

import Modelo.clasificacionModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class clasificaionesDAO {

    private DataSource datos;

    public clasificaionesDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<clasificacionModelo> TraerClasificacionesBD() throws SQLException {
        List<clasificacionModelo> clasificaciones = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from  clasificaciones";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {
                    int id_clasificacion = resul.getInt("id_clasificacion");
                    String clasificacion = resul.getString("nombre_clasificacion");

                    clasificacionModelo clasificaionTEM = new clasificacionModelo(id_clasificacion, clasificacion);
                    clasificaciones.add(clasificaionTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return clasificaciones;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String agregarEnBD(clasificacionModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;

        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "select * from clasificaciones where nombre_clasificacion = ?";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();

            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {

                sql = "insert into clasificaciones values(default, ?)";
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

    public String editarNombreEnBD(clasificacionModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "update clasificaciones set nombre_clasificacion = ? where id_clasificacion = ?";

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

    public List<clasificacionModelo> traerClasificacionesEspecificas(clasificacionModelo traerClasificaciones) throws SQLException {
        List<clasificacionModelo> clasificaciones = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from  clasificaciones where id_especie = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1,traerClasificaciones.getId());
                //ejecucions de sqL
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int id_clasificacion = resul.getInt("id_clasificacion");
                    String clasificacion = resul.getString("nombre_clasificacion");

                    clasificacionModelo clasificaionTEM = new clasificacionModelo(id_clasificacion, clasificacion);
                    clasificaciones.add(clasificaionTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return clasificaciones;
        } finally {
            conexion.close();
            statement.close();

        }
    }

}
