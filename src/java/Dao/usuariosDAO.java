package Dao;

import Modelo.usuarioModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class usuariosDAO {

    private DataSource datos;

    public usuariosDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<usuarioModelo> getUsuarios() throws SQLException {
        List<usuarioModelo> Usuarios = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from usuarios";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {

                    int id_usuario = resul.getInt("id_usuairos");
                    String nombre = resul.getString("nombre_usuairo");
                    String contrasena = resul.getString("contrasena");
                    String documento = resul.getString("documento");
                    String telefono = resul.getString("celular");

                    usuarioModelo usuariosTEM = new usuarioModelo(id_usuario, nombre, telefono, documento, contrasena);
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

    public String agregarPersonasBD(usuarioModelo agreagregarPersonas) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "insert into usuarios values(default,?, ?, ?, ?)";
                statement = conexion.prepareStatement(sql);

                statement.setString(1, agreagregarPersonas.getNombre());
                statement.setString(2, agreagregarPersonas.getTelefono());
                statement.setString(3, agreagregarPersonas.getDocumento());
                statement.setString(4, agreagregarPersonas.getContrasena());

                statement.execute();
                respuesta = "El usuario " + agreagregarPersonas.getNombre() + " se agreago correctamente. ";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public void eliminarUsuariosDeBD(usuarioModelo aliminarUsuariosBD) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "delete from usuarios where id_usuairos = ?";
                statement = conexion.prepareStatement(sql);

                statement.setInt(1, aliminarUsuariosBD.getId());

                statement.execute();

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public String editarPersonasBD(usuarioModelo agreagregarPersonas) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "update usuarios set nombre_usuairo = ?, celular = ?, documento = ?, contrasena = ? where id_usuairos = ?";
                statement = conexion.prepareStatement(sql);

                statement.setString(1, agreagregarPersonas.getNombre());
                statement.setString(2, agreagregarPersonas.getTelefono());
                statement.setString(3, agreagregarPersonas.getDocumento());
                statement.setString(4, agreagregarPersonas.getContrasena());
                statement.setString(5, agreagregarPersonas.getContrasena());

                statement.execute();
                respuesta = "El usuario " + agreagregarPersonas.getNombre() + " se edito correctamente. ";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;

    }

}
