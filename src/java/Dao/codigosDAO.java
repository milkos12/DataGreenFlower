package Dao;

import Modelo.codigoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class codigosDAO {

    private DataSource datos;

    public codigosDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<codigoModelo> TraerCodigsoBD() throws SQLException {
        List<codigoModelo> Codigos = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from codigos";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {

                    int id_codigo = resul.getInt("id_codigo");
                    int id_variedad = resul.getInt("id_variedad");
                    int id_referencia = resul.getInt("id_referencia");
                    int id_color = resul.getInt("id_color");
                    int id_calibre = resul.getInt("id_calibre");
                    int id_flor = resul.getInt("id_flor");
                    int codigo_definido = resul.getInt("codigoDefinido");

                    codigoModelo codigosTEM = new codigoModelo(id_color, id_variedad, id_calibre, id_referencia, id_codigo, id_flor, codigo_definido);
                    Codigos.add(codigosTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Codigos;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String crearCodigoEnLaBD(codigoModelo agregarCodigoBD) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "insert into codigos values(default,?, ?, ?, ?, ?, ?)";
                statement = conexion.prepareStatement(sql);

                statement.setInt(1, agregarCodigoBD.getVariedad());
                statement.setInt(2, agregarCodigoBD.getReferencia());
                statement.setInt(3, agregarCodigoBD.getColor());
                statement.setInt(4, agregarCodigoBD.getCalibre());
                statement.setInt(5, agregarCodigoBD.getEspecie());
                statement.setInt(6, agregarCodigoBD.getCodigoDefinido());

                statement.execute();
                respuesta = "El código " + agregarCodigoBD.getCodigoDefinido() + " se creo correctamente.";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public String editarCodigoEnBD(codigoModelo editarCodigos) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "update codigos set id_variedad = ?, id_referencia = ?, id_color=?, id_calibre=?, id_flor=?, codigoDefinido = ? where id_codigo = ?";
                statement = conexion.prepareStatement(sql);

                statement.setInt(1, editarCodigos.getVariedad());
                statement.setInt(2, editarCodigos.getReferencia());
                statement.setInt(3, editarCodigos.getColor());
                statement.setInt(4, editarCodigos.getCalibre());
                statement.setInt(5, editarCodigos.getEspecie());
                statement.setInt(6, editarCodigos.getCodigoDefinido());
                statement.setInt(7, editarCodigos.getId());

                statement.execute();
                respuesta = "El código " + editarCodigos.getCodigoDefinido() + " se Edito correctamente.";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public String verificarExistenciaDelCoDigo(codigoModelo verificarExistenciaDelCodigo) throws SQLException {
        String respuesta = "";
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from codigos where codigoDefinido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, verificarExistenciaDelCodigo.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {

                    respuesta = "El código " + verificarExistenciaDelCodigo.getId() + " YA esta en uso.";

                } else {
                    respuesta = "" + verificarExistenciaDelCodigo.getId();
                }
                sql = "select id_contenido from lotes where id_finca = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, verificarExistenciaDelCodigo.getId());
                resul = statement.executeQuery();

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
                respuesta = "Error en el servidor...";
            }
            return respuesta;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public List<codigoModelo> BuscarParametrosCodigoSiembra(codigoModelo codigoEnTablaSiembra) throws SQLException {
        List<codigoModelo> contenidoCodigo = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        ResultSet resul2 = null;

        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from codigos where codigoDefinido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigoEnTablaSiembra.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int id_variedad = resul.getInt("id_variedad");
                    int id_flor = resul.getInt("id_flor");
                    int id_color = resul.getInt("id_color");
                    int id_referencia = resul.getInt("id_referencia");
                    int id_calibre = resul.getInt("id_calibre");
                    codigoModelo codigoTEM = new codigoModelo(id_color, id_variedad, id_calibre, id_referencia, id_flor);
                    contenidoCodigo.add(codigoTEM);
                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return contenidoCodigo;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public int traerAutoRellenoCodigo(int flor, int variedad, int color, int referencia) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from codigos where id_variedad = ?  and id_referencia = ? and id_color =? and id_flor = ?;";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, variedad);
                statement.setInt(2, referencia);
                statement.setInt(3, color);
                statement.setInt(4, flor);
                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                    codigo = resul.getInt("codigoDefinido");
                }
            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return codigo;
        } finally {
            conexion.close();
            statement.close();
        }
    }
}
