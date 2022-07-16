package Dao;

import Modelo.proveedorModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class proveedoresDAO {

    private DataSource datos;

    public proveedoresDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<proveedorModelo> getProveedores() throws SQLException {
        List<proveedorModelo> Usuarios = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from proveedores order by nombre_proveedor ASC";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {

                    int id_usuario = resul.getInt("id_proveedores");
                    String nombre = resul.getString("nombre_proveedor");
                    String documento = resul.getString("documento");
                    String telefono = resul.getString("celular");

                    proveedorModelo usuariosTEM = new proveedorModelo(id_usuario, nombre, telefono, documento);
                    Usuarios.add(usuariosTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Usuarios;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public String editarProveedores(proveedorModelo editarProveedoresBD) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "update proveedores set nombre_proveedor = ?, celular = ?, documento = ? where id_proveedores = ?";
                statement = conexion.prepareStatement(sql);

                statement.setString(1, editarProveedoresBD.getNombre());
                statement.setString(2, editarProveedoresBD.getTelefono());
                statement.setString(3, editarProveedoresBD.getDocumento());
                statement.setInt(4, editarProveedoresBD.getId());

                statement.execute();
                respuesta = "El proveedor " + editarProveedoresBD.getNombre() + " se Edito correctamente.";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public String eliminarProveedoresBD(proveedorModelo eliminarProveedores) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "delete from proveedores where id_proveedores = ?";
                statement = conexion.prepareStatement(sql);

                statement.setInt(1, eliminarProveedores.getId());

                statement.execute();

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public String agregarProveedores(proveedorModelo agregarProveedoresBD) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "insert into proveedores values(default,?, ?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, agregarProveedoresBD.getNombre());
                statement.setString(2, agregarProveedoresBD.getTelefono());
                statement.setString(3, agregarProveedoresBD.getDocumento());

                statement.execute();
                respuesta = "El proveedores " + agregarProveedoresBD.getNombre() + " se agreago correctamente. ";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public int traerProveedor(int CogiodeLoted) throws SQLException {
        int proveedor = 0;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select proveedor from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, CogiodeLoted);
                resul = statement.executeQuery();
                int contador = 0;
                //recorrido resulset
                if (resul.next()) {
                    proveedor = resul.getInt("proveedor");

                }
            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return proveedor;
        } finally {
            conexion.close();
            statement.close();

        }
    }

}
