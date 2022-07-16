/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.FloresModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author ANDRES
 */
public class FloresDAO {

    private DataSource datos;

    public FloresDAO(DataSource datos) {
        this.datos = datos;
    }

    public List<FloresModelo> TraerColoresBD() throws SQLException {
        List<FloresModelo> Colores = new ArrayList<>();

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

                    FloresModelo coloresTEM = new FloresModelo(id_color, color);
                    Colores.add(coloresTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Colores;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<FloresModelo> TraerDegradadosBD(FloresModelo consultarDegradados) throws SQLException {
        List<FloresModelo> degradados = new ArrayList<>();
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados where id_variedad = ? and id_color = ? order by nombre_degradado ASC";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarDegradados.getId());
                statement.setInt(2, consultarDegradados.getFinca());
                resul = statement.executeQuery();
                while (resul.next()) {
                    int id_degradado = resul.getInt("id_degradado");
                    String degradado = resul.getString("nombre_degradado");
                    FloresModelo degradadosTEM = new FloresModelo(id_degradado, degradado);
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

    public String TraerNombreDelColor(FloresModelo consultarDegradados) throws SQLException {
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

    public String insertarDegradadoBD(FloresModelo AgregarDegraadado) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            conexion = datos.getConnection();
            String sql = "select * from degradados where nombre_variedad = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, AgregarDegraadado.getNombre());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = AgregarDegraadado.getNombre() + " ya existe.";
            } else {
                sql = "insert into degradados values (default, ?,?,?)";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, AgregarDegraadado.getNombre());
                statement.setInt(2, AgregarDegraadado.getParametro());
                statement.setInt(3, AgregarDegraadado.getId());
                statement.execute();
                estadoProceso = AgregarDegraadado.getNombre() + " se guardo correctamente.";
            }
        } catch (Exception e) {
            estadoProceso = AgregarDegraadado.getNombre() + " NO se puedo guardar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }

        return estadoProceso;
    }

    public List<FloresModelo> TraerFloresBD() throws SQLException {
        List<FloresModelo> Flores = new ArrayList<>();

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
                    int id_flor = resul.getInt("id_flor");
                    String flor = resul.getString("nombre_flor");
                    System.out.println("la flores consultadas en BD+++++++++++++" + flor);

                    FloresModelo floresTEM = new FloresModelo(id_flor, flor);
                    Flores.add(floresTEM);
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Flores;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String TraerFlorDelColor(FloresModelo consultarFlor) throws SQLException {
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

    public List<FloresModelo> TraerVariedadesBD(FloresModelo consultarFlor) throws SQLException {
        List<FloresModelo> degradados = new ArrayList<>();
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

                    FloresModelo degradadosTEM = new FloresModelo(id_variedad, variedad);
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

    public String editarNombreEnBD(FloresModelo editarNombre) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        int parametro = editarNombre.getParametro();
        String sql = "";
        try {
            conexion = datos.getConnection();

            if (parametro == 1) {
                sql = "update degradados set nombre_degradado = ? where id_degradado =  ?";
            } else if (parametro == 2) {
                sql = "update flores set nombre_flor = ? where id_flor =  ?";
            } else if (parametro == 3) {
                sql = "update variedad set nombre_variedad = ? where id_variedad =  ?";
            } else if (parametro == 4) {
                sql = "update fincas set nombre_finca = ?, esxtencion = ? where id_finca =  ?";
            } else if (parametro == 5) {
                sql = "update colores set nombre_color = ? where id_color = ?";
            } else if (parametro == 6) {
                sql = "update calibres set nombre_calibre = ? where id_calibre = ?";
            } else if (parametro == 7) {
                sql = "update clasificaciones set nombre_clasificacion = ? where id_clasificacion = ?";
            }
            if (parametro == 4) {
                statement = conexion.prepareStatement(sql);
                statement.setString(1, editarNombre.getNombre());
                statement.setInt(2, editarNombre.getFinca());
                statement.setInt(3, editarNombre.getId());
                statement.execute();
                estadoProceso = editarNombre.getNombre() + " se Edito correctamente.";
            } else {
                statement = conexion.prepareStatement(sql);
                statement.setString(1, editarNombre.getNombre());
                statement.setInt(2, editarNombre.getId());
                statement.execute();
                estadoProceso = editarNombre.getNombre() + " se Edito correctamente.";
            }

        } catch (Exception e) {
            estadoProceso = editarNombre.getNombre() + " NO se puedo editar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }
        return estadoProceso;
    }

    public List<FloresModelo> TraerFincasBD() throws SQLException {
        List<FloresModelo> Fincas = new ArrayList<>();

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

                    FloresModelo fincasTEM = new FloresModelo(id_finca, finca, extension);
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

    public String insertarFincaBD(FloresModelo AgregarFinca) throws SQLException {
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
                statement.setInt(2, AgregarFinca.getId());
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

    public int TraerSemanas(FloresModelo consultarSemanas) throws SQLException {
        int semanas = 0;

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from semanas where id_variedad = ? and id_finca = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarSemanas.getId());
                statement.setInt(2, consultarSemanas.getFinca());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    semanas = resul.getInt("numero_semanas");
                    System.out.println(semanas);
                } else {
                    semanas = 0;
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
                semanas = 0;
            }
            return semanas;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String insertarSemanasBD(FloresModelo AgregarSemanas) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            conexion = datos.getConnection();
            String sql = "select * from semanas where id_finca = ? and id_variedad = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, AgregarSemanas.getFinca());
            statement.setInt(2, AgregarSemanas.getId());
            resul = statement.executeQuery();
            if (resul.next()) {
                sql = "update semanas set numero_semanas = ? where id_finca = ? and id_variedad = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, AgregarSemanas.getSemanas());
                statement.setInt(2, AgregarSemanas.getFinca());
                statement.setInt(3, AgregarSemanas.getId());
                statement.execute();
            } else {
                sql = "insert into semanas value(default, ?, ?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, AgregarSemanas.getSemanas());
                statement.setInt(2, AgregarSemanas.getFinca());
                statement.setInt(3, AgregarSemanas.getId());
                statement.execute();
            }
            sql = "select * from fincas where id_finca = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, AgregarSemanas.getFinca());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = "Se ingreso " + AgregarSemanas.getSemanas() + " semanas a la finca " + resul.getString("nombre_finca") + " con la variedad ";
            }

            sql = "select * from variedad where id_variedad = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, AgregarSemanas.getId());
            resul = statement.executeQuery();
            if (resul.next()) {
                estadoProceso = estadoProceso + resul.getString("nombre_variedad");
            }

        } catch (Exception e) {
            estadoProceso = AgregarSemanas.getNombre() + " NO se puedo guardar.";
            System.out.println(e);
        } finally {
            conexion.close();
            statement.close();
        }
        return estadoProceso;
    }

    public String agregarEnBD(FloresModelo agregar) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        String estadoProceso = "";
        ResultSet resul = null;
        int parametro = agregar.getParametro();
        System.out.println(parametro);
        if (parametro == 0) {
            parametro = agregar.getId();
        }
        String sql = "";
        try {
            conexion = datos.getConnection();
            if (parametro == 1) {
                sql = "select * from degradados where nombre_degradado = ?";
            } else if (parametro == 2) {
                sql = "select * from flores where nombre_flor = ?";
            } else if (parametro == 3) {
                sql = "select * from variedad where nombre_variedad = ?";
            }/* else if (parametro == 4) {
                sql = "update fincas set nombre_finca = ? where id_finca =  ?";
            }*/ else if (parametro == 5) {
                sql = "select * from colores where nombre_color = ?";
            } else if (parametro == 6) {
                sql = "select * from calibres where nombre_calibre = ?";
            } else if (parametro == 7) {
                sql = "select * from clasificaciones where nombre_clasificacion = ?";
            }
            statement = conexion.prepareStatement(sql);
            statement.setString(1, agregar.getNombre());
            resul = statement.executeQuery();

            if (resul.next()) {
                estadoProceso = agregar.getNombre() + ", ya existe.";
            } else {
                if (parametro == 1) {
                    sql = "insert into degradados values (default, ?,?,?)";
                } else if (parametro == 2) {
                    sql = "insert into flores values(default, ?)";
                } else if (parametro == 3) {
                    sql = "insert into variedad values(default, ?, ?)";
                }/* else if (parametro == 4) {
                    sql = "update fincas set nombre_finca = ? where id_finca =  ?";
                }*/ else if (parametro == 5) {
                    sql = "insert into colores values(default, ?)";
                } else if (parametro == 6) {
                    sql = "insert into calibres values(default, ?)";
                } else if (parametro == 7) {
                    sql = "insert into clasificaciones values(default, ?)";
                }

                statement = conexion.prepareStatement(sql);
                statement.setString(1, agregar.getNombre());

                if (parametro == 1) {
                    statement.setInt(2, agregar.getSemanas());
                    statement.setInt(3, agregar.getId());
                } else if (parametro == 3) {
                    statement.setInt(2, agregar.getId());
                }

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

    public List<FloresModelo> TraerCalibresBD() throws SQLException {
        List<FloresModelo> calibres = new ArrayList<>();

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

                    FloresModelo calibreTEM = new FloresModelo(id_calibre, calibre);
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

    public List<FloresModelo> TraerClasificacionesBD() throws SQLException {
        List<FloresModelo> clasificaciones = new ArrayList<>();

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

                    FloresModelo clasificaionTEM = new FloresModelo(id_clasificacion, clasificacion);
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

    public String TraerFechaEstimada(FloresModelo consultarSemanas) throws SQLException {
        String fechaEstimada = "";
        int dias = consultarSemanas.getId() * 7;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "SELECT DATE_ADD(?, INTERVAL ? DAY) as fecha";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, consultarSemanas.getNombre());
                statement.setInt(2, dias);
                resul = statement.executeQuery();
                while (resul.next()) {

                    fechaEstimada = resul.getString("fecha");
                    System.out.println("la fecha estimada es: " + fechaEstimada + "********************************************************");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return fechaEstimada;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public int consultarCantidadDeDegradados(int id, int variedad) throws SQLException {
        int resulatado = 0;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String estadoProceso = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "select * from degradados where id_color = ? and id_variedad = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, id);
                statement.setInt(2, variedad);
                resul = statement.executeQuery();
                if (resul.next()) {
                    resulatado = 1;
                } else {
                    resulatado = 0;
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return resulatado;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public String CalcualarSemana(FloresModelo calcularSemana) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String fecha = "";
        try {
            conexion = datos.getConnection();
            String sql = "SELECT WEEK(?) as semanas";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, calcularSemana.getNombre());
            resul = statement.executeQuery();
            while (resul.next()) {
                fecha = resul.getString("semanas");
            }

        } finally {
            conexion.close();
            statement.close();
        }
        return fecha;
    }

    public String generacionDeCodigoLote(FloresModelo generaCodigoDeLoe) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String lote = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "SELECT WEEK(?) as semanas";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, generaCodigoDeLoe.getNombre());
                resul = statement.executeQuery();
                while (resul.next()) {
                    lote = resul.getString("semanas");
                }
                sql = "SELECT YEAR(?) AS AñoActual";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, generaCodigoDeLoe.getNombre());
                resul = statement.executeQuery();
                while (resul.next()) {
                    lote = resul.getString("AñoActual") + lote;
                }
            } catch (Exception e) {
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return lote;
    }

    public List<FloresModelo> TraerCodigsoBD() throws SQLException {
        List<FloresModelo> Codigos = new ArrayList<>();

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

                    FloresModelo codigosTEM = new FloresModelo(id_color, id_variedad, id_calibre, id_referencia, id_codigo, id_flor, codigo_definido);
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

    public String crearCodigoEnLaBD(FloresModelo agregarCodigoBD) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "insert into codigos values(default,?, ?, ?, ?, ?, ?)";
                statement = conexion.prepareStatement(sql);

                statement.setInt(1, agregarCodigoBD.getId_variedad());
                statement.setInt(2, agregarCodigoBD.getId_referencia());
                statement.setInt(3, agregarCodigoBD.getId_color());
                statement.setInt(4, agregarCodigoBD.getId_calibre());
                statement.setInt(5, agregarCodigoBD.getId_flor());
                statement.setInt(6, agregarCodigoBD.getId_codigo());

                statement.execute();
                respuesta = "El código " + agregarCodigoBD.getId_codigo() + " se creo correctamente.";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public String editarCodigoEnBD(FloresModelo editarCodigos) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String respuesta = "";
        try {
            try {
                conexion = datos.getConnection();
                String sql = "update codigos set id_variedad = ?, id_referencia = ?, id_color=?, id_calibre=?, id_flor=?, codigoDefinido = ? where id_codigo = ?";
                statement = conexion.prepareStatement(sql);

                statement.setInt(1, editarCodigos.getId_variedad());
                statement.setInt(2, editarCodigos.getId_referencia());
                statement.setInt(3, editarCodigos.getId_color());
                statement.setInt(4, editarCodigos.getId_calibre());
                statement.setInt(5, editarCodigos.getId_flor());
                statement.setInt(6, editarCodigos.getCodigo_difinido());
                statement.setInt(7, editarCodigos.getId_codigo());

                statement.execute();
                respuesta = "El código " + editarCodigos.getId_codigo() + " se Edito correctamente.";

            } catch (Exception e) {
                respuesta = "Ocurrio un error";
            }
        } finally {
            conexion.close();
            statement.close();
        }
        return respuesta;
    }

    public String agregarPersonasBD(FloresModelo agreagregarPersonas) throws SQLException {
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
                statement.setString(3, agreagregarPersonas.getDocumeto());
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

    public List<FloresModelo> getUsuarios() throws SQLException {
        List<FloresModelo> Usuarios = new ArrayList<>();

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

                    FloresModelo usuariosTEM = new FloresModelo(id_usuario, nombre, telefono, documento, contrasena);
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

    public void eliminarUsuariosDeBD(FloresModelo aliminarUsuariosBD) throws SQLException {
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

    public List<FloresModelo> getProveedores() throws SQLException {
        List<FloresModelo> Usuarios = new ArrayList<>();

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
                    String contrasena = "";
                    String documento = resul.getString("documento");
                    String telefono = resul.getString("celular");

                    FloresModelo usuariosTEM = new FloresModelo(id_usuario, nombre, telefono, documento, contrasena);
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

    public String editarProveedores(FloresModelo editarProveedoresBD) throws SQLException {
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
                statement.setString(3, editarProveedoresBD.getDocumeto());
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

    public String eliminarProveedoresBD(FloresModelo eliminarProveedores) throws SQLException {
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

    public String agregarProveedores(FloresModelo agregarProveedoresBD) throws SQLException {
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
                statement.setString(3, agregarProveedoresBD.getDocumeto());

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

    public float consultarAreaDisponible(FloresModelo traerAreaDisponible) throws SQLException {
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

    public String verificarExistenciaDelCoDigo(FloresModelo verificarExistenciaDelCodigo) throws SQLException {
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

    public float cosultarAreaEnUsu(FloresModelo traerAreaDisponible2) throws SQLException {
        float areaDisponible = traerAreaDisponible2.getArea();
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
                    System.out.println("---------------------srea disponible" + areaDisponible);
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

    public List<FloresModelo> BuscarParametrosCodigoSiembra(FloresModelo codigoEnTablaSiembra) throws SQLException {
        List<FloresModelo> contenidoCodigo = new ArrayList<>();

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
                    System.out.println("id flor ************ " + id_flor);
                    int id_color = resul.getInt("id_color");
                    int id_referencia = resul.getInt("id_referencia");
                    int id_calibre = resul.getInt("id_calibre");
                    FloresModelo codigoTEM = new FloresModelo(id_color, id_variedad, id_calibre, id_referencia, id_flor);
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

    public String agregarLotes(FloresModelo agregarLotesEnBD) throws SQLException {
        //obtener conexion
        Connection conexion = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        String sql2 = "", sql3;
        ResultSet resul = null;
        String estadoProceso = "";
        String respuesta = "0";
        int codigoContenido = 0;
        List<FloresModelo> contenidos;
        try {
            try {

                conexion = datos.getConnection();
                String sql = "insert into contenido values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                sql2 = "select max(id_contenido) as codigo from contenido;";
                             
                sql3 = "insert into lotes(id_lote, codigo_definido, fecha, id_finca, id_usuario, id_contenido, proveedor, estimado, remision) values (default, ?, ?, ?,2, ?, ?, ?, ?);";
                statement = conexion.prepareStatement(sql);
                statement.setFloat(1, agregarLotesEnBD.getCanastilla());
                statement.setInt(2, agregarLotesEnBD.getId_referencia());
                statement.setInt(3, agregarLotesEnBD.getId_calibre());
                statement.setInt(4, agregarLotesEnBD.getBulbos());
                statement.setFloat(5, agregarLotesEnBD.getArea());
                statement.setInt(6, agregarLotesEnBD.getId_flor());
                statement.setInt(7, agregarLotesEnBD.getId_variedad());
                statement.setInt(8, agregarLotesEnBD.getDesidad());
                statement.setInt(9, agregarLotesEnBD.getId_color());
                statement.execute();
                statement.close();
                conexion.close();
                conexion = datos.getConnection();
                statement2 = conexion.prepareStatement(sql2);
                resul = statement2.executeQuery();
                if (resul.next()) {
                    codigoContenido = resul.getInt("codigo");
                    System.out.println("el ultimo codigo q se investigo fues ---- " + codigoContenido);
                }
                statement2.close();
                
                System.out.println("id codigo: " + agregarLotesEnBD.getId_codigo() + " nombre: " + agregarLotesEnBD.getNombre() + " id_finca " + agregarLotesEnBD.getFinca() + " codigo: " + codigoContenido + " codigo_proveedor: " + agregarLotesEnBD.getProveedor() + " fecha_estimada: " + agregarLotesEnBD.getFechaE() + " remision: " + agregarLotesEnBD.getRemision());
                
                statement3 = conexion.prepareStatement(sql3);
                statement3.setInt(1, agregarLotesEnBD.getId_codigo());
                statement3.setString(2, agregarLotesEnBD.getNombre());
                statement3.setInt(3, agregarLotesEnBD.getFinca());
                statement3.setInt(4, codigoContenido);
                statement3.setInt(5, agregarLotesEnBD.getProveedor());
                statement3.setString(6, agregarLotesEnBD.getFechaE());
                statement3.setInt(7, agregarLotesEnBD.getRemision());
                statement3.execute();
                respuesta = "1";

            } catch (Exception e) {
                System.out.println("----------------------------" + e);
                respuesta = "1";
            }
        } finally {
            conexion.close();
            statement3.close();
        }
        return respuesta;
    }

    public String estimarFechas(int flor, int variedad, int finca, String fecha) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        ResultSet resul2 = null;
        String fechaestimada = "";
        int semanas = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select numero_semanas from semanas where id_finca = ? and id_variedad = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, finca);
                statement.setInt(2, variedad);
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    semanas = resul.getInt("numero_semanas");

                }
                semanas = semanas * 7;
                sql = "select DATE_ADD(?, INTERVAL ? DAY) as estimado";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, fecha);
                statement.setInt(2, semanas);
                resul = statement.executeQuery();
                if (resul.next()) {
                    fechaestimada = resul.getString("estimado");
                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return fechaestimada;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public int login(String password, int user) throws SQLException {
        int existencia = 0;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        ResultSet resul2 = null;

        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from usuarios where documento = ? and contrasena = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, user);
                statement.setString(2, password);
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    existencia = 1;
                } else {
                    existencia = 0;
                }

            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return existencia;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public List<FloresModelo> TraerLotesBD() throws SQLException {
        List<FloresModelo> Lotes = new ArrayList<>();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select distinct codigo_definido, fecha, fincas.nombre_finca, remision from lotes inner join fincas on fincas.id_finca = lotes.id_finca order by fecha;";
                statement = conexion.createStatement();
                //ejecucions de sqL
                resul = statement.executeQuery(sql);

                //recorrido resulset
                while (resul.next()) {
                    int codigoLote = resul.getInt("codigo_definido");
                    String fecha = resul.getString("fecha");
                    String fechaestimada = "";
                    String fica = resul.getString("fincas.nombre_finca");
                    String proveedor = "";
                    int remision = resul.getInt("remision");
                    int idLote = 0;

                    FloresModelo loteTEM = new FloresModelo(codigoLote, fecha, fechaestimada, fica, proveedor, remision, idLote);
                    Lotes.add(loteTEM);

                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Lotes;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<FloresModelo> BuscarLotes(FloresModelo buscarLotes) throws SQLException {
        List<FloresModelo> Lotes = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String busqueda = "%" + Integer.toString(buscarLotes.getId()) + "%";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select distinct codigo_definido, fecha, fincas.nombre_finca, remision from lotes inner join fincas on fincas.id_finca = lotes.id_finca inner join proveedores on proveedores.id_proveedores = lotes.proveedor  where codigo_definido like ?";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, busqueda);
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int codigoLote = resul.getInt("codigo_definido");
                    String fecha = resul.getString("fecha");
                    String fechaestimada = "";
                    String fica = resul.getString("fincas.nombre_finca");
                    String proveedor = "";
                    int remision = resul.getInt("remision");
                    int idLote = 0;

                    FloresModelo loteTEM = new FloresModelo(codigoLote, fecha, fechaestimada, fica, proveedor, remision, idLote);
                    Lotes.add(loteTEM);

                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e + "----" + buscarLotes.getId());
            }
            return Lotes;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<FloresModelo> buscarLotesFilter(int especie, int vairedad, int color, String fecha, int proveedor, int finca, int dias) throws SQLException {
        List<FloresModelo> Lotes = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String busqueda = "";
        String sql = "select distinct codigo_definido, fecha, fincas.nombre_finca, remision from lotes inner join fincas on fincas.id_finca = lotes.id_finca  where";
        String sql2 = "select * from contenido where";
        ResultSet resul2 = null;
        PreparedStatement statement2 = null;
        Connection conexion2 = null;
        ResultSet resul3 = null;
        PreparedStatement statement3 = null;
        Connection conexion3 = null;

        String especieCons = "";
        int confi = 0;
        int confi2 = 0;
        int confi3 = 0;
        int contador = 0;
        int contador2 = 0;
        //conexion 
        try {
            try {
                //  
                if (finca > 0) {
                    sql = sql + " lotes.id_finca = " + finca;
                    confi = 1;
                }
                if (proveedor > 0) {
                    
                }
                if (fecha != "") {
                    if (confi == 1) {
                        sql = sql + " and";
                    }
                    sql = sql + " lotes.fecha = '" + fecha + "'";
                    confi = 1;
                }
                if (confi == 0) {
                    sql = "select distinct codigo_definido, fecha, fincas.nombre_finca, remision from lotes inner join fincas on fincas.id_finca = lotes.id_finca";
                }
                System.out.println("****sql " + sql);
                conexion = datos.getConnection();
                //intancia sql y el statement
                statement = conexion.prepareStatement(sql);
                resul = statement.executeQuery();
                conexion2 = datos.getConnection();
                conexion3 = datos.getConnection();
                //recorrido resulset
                while (resul.next()) {
                    String sql4 = "select * from lotes where codigo_definido = " + resul.getInt("codigo_definido");

                    //intancia sql y el statement
                    statement3 = conexion.prepareStatement(sql4);
                    resul3 = statement3.executeQuery();

                    while (resul3.next()) {
                        System.out.println("color " + color + " espcie " + especie + " variedad " + vairedad);
                        if (color > 0) {
                            sql2 = sql2 + " id_color = " + color;
                            confi2 = 1;
                        }
                        if (especie > 0) {
                            if (confi2 == 1) {
                                sql2 = sql2 + " and";
                            }
                            sql2 = sql2 + " id_flor = " + especie;
                            confi2 = 1;
                        }
                        if (vairedad > 0) {
                            if (confi2 == 1) {
                                sql2 = sql2 + " and";
                            }
                            sql2 = sql2 + " id_variedad = " + vairedad;
                            confi2 = 1;
                        }
                        if (confi2 == 1) {
                            if (confi2 == 1) {
                                sql2 = sql2 + " and";
                            }
                            sql2 = sql2 + " id_contenido = " + resul3.getInt("id_contenido");
                            confi2 = 1;

                        }
                        System.out.println(confi2 + "-????????????sql2 " + sql2);
                        if (confi2 == 1) {

                            //intancia sql y el statement
                            statement2 = conexion.prepareStatement(sql2);
                            resul2 = statement2.executeQuery();
                            if (resul2.next()) {

                                contador = contador + 1;
                                confi3 = 1;
                                break;

                            } else {
                                confi2 = 0;
                            }
                        }

                        confi2 = 0;
                        sql2 = "select * from contenido where";

                    }
                    sql2 = "select * from contenido where";
                    int codigoLote = resul.getInt("codigo_definido");
                    String fech = resul.getString("fecha");
                    String fechaestimada = "";
                    String fica = resul.getString("fincas.nombre_finca");
                    String proveedo = "";
                    int remision = resul.getInt("remision");

                    int idLote = 0;
                    if (confi2 == 1) {
                        if (confi3 == 1) {
                            FloresModelo loteTEM = new FloresModelo(codigoLote, fech, fechaestimada, fica, proveedo, remision, idLote);
                            Lotes.add(loteTEM);
                            contador2 = contador2 + 1;
                        }
                    }
                    if (confi == 1) {
                        FloresModelo loteTEM = new FloresModelo(codigoLote, fech, fechaestimada, fica, proveedo, remision, idLote);
                        Lotes.add(loteTEM);
                    }
                    confi3 = 0;
                    confi2 = 0;
                }
            } catch (Exception e) {
                System.out.println("eroor " + e);
            }
            return Lotes;
        } finally {
            try{
            conexion.close();
            statement.close();
            conexion2.close();
            conexion3.close();
            statement2.close();
            statement3.close();
            if (confi2 == 1) {

                statement2.close();
            }
            }catch(Exception e){
                
            }

        }

    }

    public List<FloresModelo> traerContenidos(int lote) throws SQLException {
        List<FloresModelo> Contenidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select id_contenido, proveedor from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, lote);
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int codigoContenido = resul.getInt("id_contenido");
                    int proveedor = resul.getInt("proveedor");
                    FloresModelo loteTEM = new FloresModelo(codigoContenido, proveedor);
                    Contenidos.add(loteTEM);

                }
            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return Contenidos;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<FloresModelo> traerContenidosDescripcion(int id) throws SQLException {
        List<FloresModelo> Contenidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from contenido where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, id);
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int codigo = resul.getInt("id_contenido");
                    float canastillas = resul.getFloat("numero_canastilla");
                    int referencia = resul.getInt("id_degradado");
                    int calibre = resul.getInt("id_calibre");
                    int bulbuos = resul.getInt("cantidad_bulbos");
                    int area = 0;
                    int flor = resul.getInt("id_flor");
                    int variedad = resul.getInt("id_variedad");
                    int densidad = resul.getInt("densidad");
                    int color = resul.getInt("id_color");

                    int Int = 0;
                    String string = "";

                    FloresModelo contenidoTEM = new FloresModelo(string, color, variedad, calibre, referencia, codigo, flor, Int, densidad, Int, bulbuos, canastillas, Int, string, Int);
                    Contenidos.add(contenidoTEM);

                }
            } catch (Exception e) {
                System.out.println("el error es ---° " + e);
            }
            return Contenidos;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public int[][] traerContenidosArray(int CogiodeLoted, int cantidad) throws SQLException {
        int contenidos[][] = new int[cantidad][cantidad];
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select id_contenido, proveedor from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, CogiodeLoted);
                resul = statement.executeQuery();
                int contador = 0;
                //recorrido resulset
                while (resul.next()) {
                    contenidos[contador][0] = resul.getInt("id_contenido");
                    contenidos[contador][1] = resul.getInt("proveedor");
                    contador++;

                }
            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return contenidos;
        } finally {
            conexion.close();
            statement.close();

        }
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

    public String traerFechaEs(int CogiodeLoted, int idContenido) throws SQLException {
        List<FloresModelo> Contenidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String Fecha = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where codigo_definido = ? and id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, CogiodeLoted);
                statement.setInt(2, idContenido);
                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                    Fecha = resul.getString("estimado");

                }

            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return Fecha;
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

    public String editarLotes(FloresModelo editarLotesEnBD) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        String sql2 = "", sql3;
        ResultSet resul = null;
        String estadoProceso = "";
        String respuesta = "0";
        int codigoContenido = 0;
        List<FloresModelo> contenidos;
        try {
            try {

                conexion = datos.getConnection();
                String sql = "update contenido set id_degradado=?, id_calibre=?, cantidad_bulbos=?, area =?, id_flor=?, id_variedad =?, densidad=?, id_color=?, numero_canastilla=?  where id_contenido = ?; ";
                sql3 = "update lotes set codigo_definido=?, fecha=?, id_finca=?, proveedor=?, estimado=?, remision=? where id_contenido = ?;";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, editarLotesEnBD.getId_referencia());
                statement.setInt(2, editarLotesEnBD.getId_calibre());
                statement.setInt(3, editarLotesEnBD.getBulbos());
                statement.setFloat(4, editarLotesEnBD.getArea());
                statement.setInt(5, editarLotesEnBD.getId_flor());

                statement.setInt(6, editarLotesEnBD.getId_variedad());
                statement.setInt(7, editarLotesEnBD.getDesidad());
                statement.setInt(8, editarLotesEnBD.getId_color());
                statement.setFloat(9, editarLotesEnBD.getCanastilla());
                statement.setInt(10, editarLotesEnBD.getParametro());
                statement.execute();
                statement.close();

                statement3 = conexion.prepareStatement(sql3);
                statement3.setInt(1, editarLotesEnBD.getId_codigo());
                statement3.setString(2, editarLotesEnBD.getNombre());
                statement3.setInt(3, editarLotesEnBD.getFinca());
                System.out.println(".............____Proveedor " + editarLotesEnBD.getProveedor());
                statement3.setInt(4, editarLotesEnBD.getProveedor());
                statement3.setString(5, editarLotesEnBD.getFechaE());
                statement3.setInt(6, editarLotesEnBD.getRemision());
                statement3.setInt(7, editarLotesEnBD.getParametro());
                statement3.execute();
                statement3.close();
                respuesta = "1";

            } catch (Exception e) {
                System.out.println(",.,.----------------------------" + e);
                respuesta = "1";
            }
        } finally {
            conexion.close();

        }
        return respuesta;
    }

    public void eliminarContenido(FloresModelo codigoEliminar) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        try {
            conexion = datos.getConnection();
            String sql = "delete from lotes where id_contenido = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, codigoEliminar.getId());
            statement.execute();
            sql = "delete from contenido where id_contenido = ?";
            statement2 = conexion.prepareStatement(sql);
            statement2.setInt(1, codigoEliminar.getId());
            statement2.execute();
        } finally {
            conexion.close();
            statement.close();
            statement2.close();
        }

    }

    public List<FloresModelo> buscarLotesSemanas(int semanaStard, int semanaEnd) throws SQLException {
        
        List<FloresModelo> lotes = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where  semana_estimada_corte between ? and ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, semanaStard);
                statement.setInt(2, semanaEnd);
                
                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                    int codigoLote = resul.getInt("codigo_definido");
                    String fecha = resul.getString("fecha");
                    String fechaestimada = resul.getString("estimado");
                    String fica = resul.getString("fincas.nombre_finca");
                    String proveedor = resul.getString("proveedores.nombre_proveedor");
                    int remision = resul.getInt("remision");
                    int weeks = resul.getInt("semana_estimada_corte");

                    FloresModelo loteTEM = new FloresModelo(codigoLote, fecha, fechaestimada, fica, proveedor, remision, weeks);
                    lotes.add(loteTEM);
                }

            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return lotes;
        } finally {
            conexion.close();
            statement.close();
        }
    }
}
