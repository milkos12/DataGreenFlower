package Dao;


import Modelo.colorModelo;
import Modelo.especieModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class coloresDAO {

    private DataSource datos;

    public coloresDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<colorModelo> TraerColoresBD() throws SQLException {
        List<colorModelo> Colores = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from colores order by nombre_color ASC";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {
                    int id_color = resul.getInt("id_color");
                    String color = resul.getString("nombre_color");

                    colorModelo coloresTEM = new colorModelo(id_color, color);
                    Colores.add(coloresTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es traer flores de la BD --- " + e);
            }
            return Colores;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String agregarEnBDColor(colorModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;
        try {
            conexion = datos.getConnection();
            String sql = "select * from colores where nombre_color = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {
                sql = "insert into colores values(default, ?)";
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

    public String editarNombreEnBDColor(colorModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";

        try {
            conexion = datos.getConnection();
            sql = "update colores set nombre_color = ? where id_color = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, editarNombre.getNombre());
            statement.setInt(2, editarNombre.getId());
            statement.execute();
            estadoProceso = editarNombre.getNombre() + " se Edito correctamente.";
        } catch (Exception e) {
            estadoProceso = editarNombre.getNombre() + " NO se puedo editar.";
            System.out.println("error editar colores----"+e);
        } finally {
            conexion.close();
            statement.close();
        }
        return estadoProceso;
    }
    public String TraerNombreDelColor(colorModelo consultarDegradados) throws SQLException {
        String nombreVariedad = "";
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
                statement.setInt(1, consultarDegradados.getId());
                resul = statement.executeQuery();
                if (resul.next()) {

                    nombreVariedad = resul.getString("nombre_variedad");

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }

        return nombreVariedad;

    }
    public String traerNombreDelColorCorte(colorModelo consultarDegradados) throws SQLException {
        String nombreVariedad = "";
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from colores where id_color = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarDegradados.getId());
                resul = statement.executeQuery();
                if (resul.next()) {

                    nombreVariedad = resul.getString("nombre_color");

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            conexion.close();
            statement.close();
        }

        return nombreVariedad;

    }



}
