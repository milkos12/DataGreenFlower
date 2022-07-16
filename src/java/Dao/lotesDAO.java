package Dao;

import Modelo.loteModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.sql.DataSource;

public class lotesDAO {

    private DataSource datos;

    public lotesDAO(DataSource datos) {
        this.datos = datos;
    }

    public String agregarLotes(loteModelo agregarLotesEnBD) throws SQLException {
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
        try {
            try {

                conexion = datos.getConnection();
                String sql = "insert into contenido values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                sql2 = "SELECT @@identity AS id;";
                sql3 = "insert into lotes values (default, ?, ?, ?,2, ?, ?,?,?,week(?), curdate());";
                //id_contenido numero_canastilla id_degradado id_calibre cantidad_bulbos area id_flor id_variedad densidad id_color
                statement = conexion.prepareStatement(sql);
                statement.setFloat(1, agregarLotesEnBD.getCanastillas());
                statement.setInt(2, agregarLotesEnBD.getReferencia());
                statement.setInt(3, agregarLotesEnBD.getCalibre());
                statement.setInt(4, agregarLotesEnBD.getBulbos());
                statement.setFloat(5, agregarLotesEnBD.getArea());
                statement.setInt(6, agregarLotesEnBD.getEspecie());
                statement.setInt(7, agregarLotesEnBD.getVariedad());
                statement.setInt(8, agregarLotesEnBD.getDensidad());
                statement.setInt(9, agregarLotesEnBD.getColor());
                statement.execute();
                statement.close();
                conexion.close();
                conexion = datos.getConnection();
                statement2 = conexion.prepareStatement(sql2);
                resul = statement2.executeQuery();
                if (resul.next()) {
                    codigoContenido = resul.getInt("id");
                }
                //id_lote codigo_definido fecha id_finca id_usuario id_contenido proveedor estimado remision 

                statement2.close();
                statement3 = conexion.prepareStatement(sql3);
                statement3.setInt(1, agregarLotesEnBD.getCodigo_defininido());
                statement3.setString(2, agregarLotesEnBD.getFecha());
                statement3.setInt(3, agregarLotesEnBD.getFinca());
                statement3.setInt(4, codigoContenido);
                statement3.setInt(5, agregarLotesEnBD.getProveedor());
                statement3.setString(6, agregarLotesEnBD.getFechaEstimada());
                statement3.setInt(7, agregarLotesEnBD.getRemision());
                statement3.setString(8, agregarLotesEnBD.getFechaEstimada());
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

    public String BuscarFechaLote(loteModelo buscarLotes) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String Fecha = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select distinct codigo_definido, fecha from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, buscarLotes.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    Fecha = resul.getString("fecha");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Fecha;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String BuscarFincaLote(loteModelo buscarLotes) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String Finca = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select distinct codigo_definido, id_finca from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, buscarLotes.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    Finca = resul.getString("id_finca");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Finca;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String BuscarRemisionLote(loteModelo buscarLotes) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String Finca = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select distinct codigo_definido, remision from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, buscarLotes.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    Finca = resul.getString("remision");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Finca;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<loteModelo> traerContenidos(int lote) throws SQLException {
        List<loteModelo> Contenidos = new ArrayList<>();
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
                    loteModelo loteTEM = new loteModelo(proveedor, codigoContenido);
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

    public List<loteModelo> traerContenidosDescripcion(int id) throws SQLException {
        List<loteModelo> Contenidos = new ArrayList<>();
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
                    int flor = resul.getInt("id_flor");
                    int variedad = resul.getInt("id_variedad");
                    int densidad = resul.getInt("densidad");
                    int color = resul.getInt("id_color");

                    loteModelo contenidoTEM = new loteModelo(color, variedad, calibre, referencia, codigo, flor, densidad, bulbuos, canastillas);
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

    public String buscarFincaLoteNombre(loteModelo buscarLotes) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String Finca = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select distinct fincas.nombre_finca from lotes inner join fincas on fincas.id_finca = lotes.id_finca where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, buscarLotes.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    Finca = resul.getString("nombre_finca");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return Finca;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String buscarEspecieLoteNombre(loteModelo buscarLotes) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String especie = "";
        int contenido = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select id_contenido from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, buscarLotes.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    contenido = resul.getInt("id_contenido");
                }

                sql = "select flores.nombre_flor from contenido inner join flores on flores.id_flor = contenido.id_flor where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, contenido);
                resul = statement.executeQuery();
                if (resul.next()) {
                    especie = resul.getString("flores.nombre_flor");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return especie;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<loteModelo> traerContenidosDescripcionUnique(int id_contenido) throws SQLException {
        List<loteModelo> Contenidos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select id_flor, id_variedad, id_color, id_degradado, cantidad_bulbos, numero_canastilla  from contenido where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, id_contenido);
                resul = statement.executeQuery();

                //recorrido resulset
                while (resul.next()) {
                    int codigo = 0;
                    float canastillas = resul.getFloat("numero_canastilla");
                    int referencia = resul.getInt("id_degradado");
                    int calibre = 0;
                    int bulbuos = resul.getInt("cantidad_bulbos");
                    int flor = resul.getInt("id_flor");
                    int variedad = resul.getInt("id_variedad");
                    int densidad = 0;
                    int color = resul.getInt("id_color");

                    loteModelo contenidoTEM = new loteModelo(color, variedad, calibre, referencia, codigo, flor, densidad, bulbuos, canastillas);
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

    public String buscarFechaDeSiembra(loteModelo buscarLotes) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String fecha = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select fecha from lotes where codigo_definido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, buscarLotes.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    fecha = resul.getString("fecha");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return fecha;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public String colorContenido(loteModelo consultarContenidos) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        String color = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select colores.nombre_color  from contenido inner join colores on colores.id_color = contenido.id_color where id_contenido = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, consultarContenidos.getId());
                resul = statement.executeQuery();

                //recorrido resulset
                if (resul.next()) {
                    color = resul.getString("colores.nombre_color");
                }
            } catch (Exception e) {
                System.out.println("el error es --- " + e);
            }
            return color;
        } finally {
            conexion.close();
            statement.close();

        }
    }

    public List<loteModelo> buscarLotesSemanas(int week, int id_finca) throws SQLException {
        List<loteModelo> lotes = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where  semana_estimada_corte = ? and id_finca = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, week);
                statement.setInt(2, id_finca);

                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {

                    int id_lote = resul.getInt("id_lote");
                    int codigo_definido = resul.getInt("codigo_definido");
                    int id_contenido = resul.getInt("id_contenido");
                    int numero_semanas = resul.getInt("semana_estimada_corte");
                    int id_fica = resul.getInt("id_finca");

                    loteModelo loteTEM = new loteModelo(id_lote, codigo_definido, id_contenido, numero_semanas, id_fica);
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

    public List<loteModelo> buscarContenidosLotes(List<loteModelo> lotes, int color) throws SQLException {
        List<loteModelo> contenidos = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        //conexion 
        int numero_bulbos = 0;
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                for (loteModelo listLote : lotes) {

                    String sql = "select * from contenido where id_color = ? and id_contenido = ?";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, color);
                    statement.setInt(2, listLote.getId_contenido());

                    resul = statement.executeQuery();

                    //recorrido resulset
                    while (resul.next()) {

                        float numero_castillas = resul.getFloat("numero_canastilla");
                        float numero_bulbos_tem = resul.getFloat("cantidad_bulbos");
                        numero_bulbos += numero_bulbos_tem * numero_castillas;

                    }
                    if(color == 12 && listLote.getId_contenido() == 206){
                        System.out.println("$$$$$$$$$$$$$${{{{{{{{{{{{{ " + buscarIdContiedoCorte(listLote.getId_contenido(), color) );
                    }
                                            

                    numero_bulbos = numero_bulbos - buscarIdContiedoCorte(listLote.getId_contenido(), color);
                    
                }
                loteModelo loteTEM = new loteModelo(0, numero_bulbos, 0);
                    contenidos.add(loteTEM);
                    

            } finally {
                conexion.close();
                statement.close();
            }
        } catch (Exception e) {
            loteModelo loteTEM = new loteModelo(0, 0, 0);
            contenidos.add(loteTEM);
            //System.out.println("el error es ---| " + e);
        }

        return contenidos;
    }
    
    public int buscarIdContiedoCorte (int codigoContenidoLote, int codColor) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        int cantidad = 0;
        int confirm = 0; 
        int catidad = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select id_corte from cortes where id_contenido_lote = ? and id_color = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigoContenidoLote);
                statement.setInt(2, codColor);
                resul = statement.executeQuery();
                //recorrido resulset
                int id = 0;
                while (resul.next()) {

                    if(codigoContenidoLote == 206){
                        System.out.println("--"+resul.getInt("id_corte"));
                    }
                    cantidad = cantidad + cantidadCortePorContenido(resul.getInt("id_corte"));

                   
                }


            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return cantidad;
        } finally {
            conexion.close();
            statement.close();
        }
    }
    
    
    public int cantidadCortePorContenido(int codigoContenidoLote) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        int cantidad = 0;
        int confirm = 0; 
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select contenido_cortes.id_corte, contenido_cortes.cantidad, contenido_cortes.name_clasificacion  from (select clasificaciones.nombre_clasificacion as name_clasificacion, id_corte, cantidad from contenidoscortes inner join clasificaciones on clasificaciones.id_clasificacion =  contenidoscortes.id_clasificacion where id_corte = ?) as contenido_cortes ";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigoContenidoLote);

                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                    
                    cantidad = cantidad + resul.getInt("contenido_cortes.cantidad");
                   
                }
                
                sql = "select * from cortes where id_corte = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigoContenidoLote);

                resul = statement.executeQuery();
                
                while (resul.next()) {
                    
                    cantidad = cantidad + resul.getInt("bajas") +  resul.getInt("nacionales");
                   
                }


            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return cantidad;
        } finally {
            conexion.close();
            statement.close();
        }
    }
    
    public String buscarCortesPorContenido(int codigoContenidoLote) throws SQLException {
        System.out.println(";;;;;;;;;;________ " + codigoContenidoLote);
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        String detalle = "[";
        int confirm = 0; 
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select contenido_cortes.id_corte, cortes.bajas, contenido_cortes.cantidad, cortes.nacionales, contenido_cortes.name_clasificacion  from (select clasificaciones.nombre_clasificacion as name_clasificacion, id_corte, cantidad from contenidoscortes inner join clasificaciones on clasificaciones.id_clasificacion =  contenidoscortes.id_clasificacion where id_corte = ?) as contenido_cortes, (select * from cortes where id_corte = ?) as cortes;";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigoContenidoLote);
                statement.setInt(2, codigoContenidoLote);

                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                    
                    detalle = detalle + "[" + resul.getString("contenido_cortes.id_corte") + "," + resul.getString("cortes.bajas") + "," + resul.getString("contenido_cortes.cantidad") + "," + resul.getString("cortes.nacionales") + ",&apos;" + resul.getString("contenido_cortes.name_clasificacion")+"&apos;]";
                    
                        detalle = detalle + ",";
                   
                   
                }
                
                detalle = detalle + "],";

            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return detalle;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public List<loteModelo> buscarLotesSemanasTotales(int week) throws SQLException {
        List<loteModelo> lotes = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select * from lotes where  semana_estimada_corte = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, week);

                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {

                    int id_lote = resul.getInt("id_lote");
                    int codigo_definido = resul.getInt("codigo_definido");
                    int id_contenido = resul.getInt("id_contenido");
                    int numero_semanas = resul.getInt("semana_estimada_corte");
                    int id_fica = resul.getInt("id_finca");

                    loteModelo loteTEM = new loteModelo(id_lote, codigo_definido, id_contenido, numero_semanas, id_fica);
                    lotes.add(loteTEM);
                }

            } catch (Exception e) {
                ///System.out.println("el error es ---| " + e);
            }
            return lotes;
        } finally {
            conexion.close();
            statement.close();
        }
    }

    public int buscarContenidosLotesTotales(List<loteModelo> lotes, int id) {

        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        int numero_bulbos = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                for (loteModelo listLote : lotes) {

                    String sql = "select * from contenido where id_color = ? and id_contenido = ?";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, id);
                    statement.setInt(2, listLote.getId_contenido());

                    resul = statement.executeQuery();

                    //recorrido resulset
                    while (resul.next()) {

                        float numero_castillas = resul.getFloat("numero_canastilla");
                        float numero_bulbos_tem = resul.getFloat("cantidad_bulbos");
                        numero_bulbos += numero_bulbos_tem * numero_castillas;
                        numero_bulbos = numero_bulbos - buscarIdContiedoCorte(resul.getInt("id_contenido"),id);

                    }

                }

            } finally {
                conexion.close();
                statement.close();
            }
        } catch (Exception e) {
            //System.out.println("el error es ---| " + e);
        }

        return numero_bulbos;
    }

    public String buscarContenidosDetallesLotesTotales(List<loteModelo> lotes, int id) {
         Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        int numero_bulbos = 0;
        String detalles = "["; 
        int confirm = 0;
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                for (loteModelo listLote : lotes) {

                    String sql = "select flores.nombre_flor, variedad.nombre_variedad, numero_canastilla, cantidad_bulbos, finca.nombre_finca from (select nombre_finca from fincas where id_finca = ?) as finca, contenido inner join variedad on variedad.id_variedad = contenido.id_variedad inner join flores on flores.id_flor = contenido.id_flor where id_color = ? and id_contenido =?";
                    statement = conexion.prepareStatement(sql);
                    statement.setInt(1, listLote.getFinca());
                    statement.setInt(2, id);
                    statement.setInt(3, listLote.getId_contenido());

                    resul = statement.executeQuery();

                    //recorrido resulset
                    while (resul.next()) {

                        float numero_castillas = resul.getFloat("numero_canastilla");
                        int numero_bulbos_tem = resul.getInt("cantidad_bulbos");
                        String especie = resul.getString("flores.nombre_flor");
                        String variedad = resul.getString("variedad.nombre_variedad");
                        String ficna = resul.getString("finca.nombre_finca");
                        numero_bulbos += numero_bulbos_tem * numero_castillas;
                        if(confirm == 1){
                            detalles = detalles + ",";
                            
                        }
                        
                        if(numero_bulbos > 0){
                            detalles = detalles + "[" + numero_bulbos_tem + ","  + numero_castillas + ",&apos;" + especie + "&apos;,&apos;" + variedad +"&apos;,"+listLote.getCodigo_defininido()+", &apos;"+ficna+"&apos;,"+buscarIdContiedoCorteDetails(listLote.getId_contenido())+"]"; 
                            confirm = 1;
                        }
                        
                        
                        numero_bulbos = 0;

                    }
                     

                }
                detalles = detalles + "]";

            } finally {
                conexion.close();
                statement.close();
            }
        } catch (Exception e) {
            System.out.println("el error es ---| " + e);
        }

        return detalles;
    }
    
    public String buscarIdContiedoCorteDetails (int codigoContenidoLote) throws SQLException {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resul = null;
        int codigo = 0;
        String cantidad = "";
        int confirm = 0; 
        String catidad = "";
        //conexion 
        try {
            try {
                conexion = datos.getConnection();
                //intancia sql y el statement
                String sql = "select id_corte from cortes where id_contenido_lote = ?";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, codigoContenidoLote);

                resul = statement.executeQuery();
                //recorrido resulset
                while (resul.next()) {
                
                    cantidad = cantidad + buscarCortesPorContenido(resul.getInt("id_corte"))+"";
                   
                }
                


            } catch (Exception e) {
                System.out.println("el error es ---| " + e);
            }
            return cantidad;
        } finally {
            conexion.close();
            statement.close();
        }
    }

}
