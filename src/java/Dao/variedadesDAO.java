package Dao;

import Modelo.especieModelo;
import Modelo.variedadModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class variedadesDAO {

    private DataSource datos;

    public variedadesDAO(DataSource datos) {
        this.datos = datos;
    }

    public String editarNombreEnBD(variedadModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "update variedad set nombre_variedad = ? where id_variedad =  ?";

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

    public String agregarEnBD(variedadModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;
        String sql = "";
        try {
            conexion = datos.getConnection();

            sql = "select * from variedad where nombre_variedad = ?";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();

            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {

                sql = "insert into variedad values(default, ?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, agregar.getNombre());
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

    public List<variedadModelo> TraerVariedadesBD(variedadModelo consultarFlor) throws SQLException {
        List<variedadModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from variedad where id_flor = ? order by nombre_variedad ASC";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarFlor.getId());
                resul = statement.executeQuery();
                while (resul.next()) {
                    int id_variedad = resul.getInt("id_variedad");
                    String variedad = resul.getString("nombre_variedad");

                    variedadModelo degradadosTEM = new variedadModelo(id_variedad, variedad);
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
//areglar se dumplica el metodo por error de crear dos metodos del mismo nombre pero con direrente modelo

    public List<variedadModelo> TraerVariedadesBD(especieModelo consultarFlor) throws SQLException {
        List<variedadModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from variedad where id_flor = ? order by nombre_variedad ASC";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarFlor.getId());
                resul = statement.executeQuery();
                while (resul.next()) {
                    int id_variedad = resul.getInt("id_variedad");
                    String variedad = resul.getString("nombre_variedad");

                    variedadModelo degradadosTEM = new variedadModelo(id_variedad, variedad);
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

    public String variedadesNombreEspecie(variedadModelo consultarVariedadName) throws SQLException {
        String nombreFlor = "";
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from variedad where id_variedad = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarVariedadName.getId());
                resul = statement.executeQuery();
                if (resul.next()) {

                    nombreFlor = resul.getString("nombre_variedad");

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }

        return nombreFlor;
    }
}
