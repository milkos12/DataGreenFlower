
package Dao;

import Modelo.especieModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class especiesDAO {
    private DataSource datos;

    public especiesDAO(DataSource datos) {
        this.datos = datos;
    }
    
        public List<especieModelo> TraerEspeciesBD() throws SQLException {
        List<especieModelo> especies = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from Flores order by nombre_flor ASC";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {
                    int id = resul.getInt("id_flor");
                    String nombre = resul.getString("nombre_flor");

                    especieModelo especiesTEM = new especieModelo(id, nombre);
                    especies.add(especiesTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es traer flores de la BD --- " + e);
            }
            return especies;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String agregarEnBDEspecie(especieModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;
        try {
            conexion = datos.getConnection();
            String sql = "select * from flores where nombre_flor = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {
                sql = "insert into flores values(default, ?)";
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

    public String editarNombreEnBDEspecie(especieModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";

        try {
            conexion = datos.getConnection();
            sql = "update flores set nombre_flor = ? where id_flor =  ?";
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
    public String traerNombreEspecie(especieModelo consultarFlor) throws SQLException {
        String nombreFlor = "";
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from flores where id_flor = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarFlor.getId());
                resul = statement.executeQuery();
                if (resul.next()) {

                    nombreFlor = resul.getString("nombre_flor");

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
