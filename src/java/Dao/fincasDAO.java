package Dao;

import Modelo.fincaModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class fincasDAO {

    private DataSource datos;

    public fincasDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<fincaModelo> TraerFincasBD() throws SQLException {
        List<fincaModelo> Fincas = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from  fincas";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {
                    int id_finca = resul.getInt("id_finca");
                    String finca = resul.getString("nombre_finca");
                    int extension = resul.getInt("esxtencion");

                    fincaModelo fincasTEM = new fincaModelo(id_finca, finca, extension);
                    Fincas.add(fincasTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Fincas;
        } finally {
            conexion.close();
            statement.close();

        }

    }

    public String insertarFincaBD(fincaModelo AgregarFinca) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            conexion = datos.getConnection();
            String sql = "select * from  fincas where nombre_finca = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, AgregarFinca.getNombre());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = AgregarFinca.getNombre() + " ya existe.";
            } else {
                sql = "insert into fincas values(default, ?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, AgregarFinca.getNombre());
                statement.setInt(2, AgregarFinca.getExtension());
                statement.execute();
                estadoProceso = AgregarFinca.getNombre() + " se guardo correctamente.";
            }
        } catch (Exception e) {
            estadoProceso = AgregarFinca.getNombre() + " NO se puedo guardar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }

        return estadoProceso;
    }

    public String editarNombreEnBD(fincaModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        String sql = "";
        try {
            conexion = datos.getConnection();
            sql = "update fincas set nombre_finca = ?, esxtencion = ? where id_finca =  ?";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, editarNombre.getNombre());
            statement.setInt(2, editarNombre.getExtension());
            statement.setInt(3, editarNombre.getId());
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

    public float consultarAreaDisponible(fincaModelo traerAreaDisponible) throws SQLException {
        float areaDisponible = 0;
        int contenidosId = 0;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select esxtencion from fincas where id_finca = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, traerAreaDisponible.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {

                    areaDisponible = resul.getFloat("esxtencion");

                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return areaDisponible;
        } finally {
            conexion.close();
            statement.close();
        }
    }
    public float cosultarAreaEnUsu(fincaModelo traerAreaDisponible2) throws SQLException {
        float areaDisponible = traerAreaDisponible2.getAreaDisponible();
        int contenidosId = 0;

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        ResultSet resul2 = null;

        //conexion 
        try {
            try {

                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where id_finca = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, traerAreaDisponible2.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {

                    int idContenido = resul.getInt("id_contenido");
                    sql = "select * from contenido where id_contenido = ?";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, idContenido);
                    resul2 = statement.executeQuery();
                    while (resul2.next()) {
                        areaDisponible = areaDisponible - resul2.getFloat("area");
                    }

                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return areaDisponible;
        } finally {
            conexion.close();
            statement.close();
        }
    }

}
