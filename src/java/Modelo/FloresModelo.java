/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class FloresModelo {

    private int id;
    private int parametro;
    private String nombre;
    private String variedad;
    private String color;
    private String degradado;
    private String calibre;
    private int semanas;
    private int finca;
    private String documeto;
    private String telefono;
    private int id_color;
    private int id_variedad;
    private int id_calibre;
    private int id_referencia;
    private int id_codigo;
    private int id_flor;
    private int codigo_difinido;
    private String contrasena;
    private int proveedor;
    private int desidad;
    private float area;
    private int bulbos;
    private float canastilla;
    private String fechaE;
    private int remision;

    @Override
    public String toString() {
        return "FloresModelo{" + "id=" + id + ", parametro=" + parametro + ", nombre=" + nombre + ", variedad=" + variedad + ", color=" + color + ", degradado=" + degradado + ", calibre=" + calibre + ", semanas=" + semanas + ", finca=" + finca + ", documeto=" + documeto + ", telefono=" + telefono + ", id_color=" + id_color + ", id_variedad=" + id_variedad + ", id_calibre=" + id_calibre + ", id_referencia=" + id_referencia + ", id_codigo=" + id_codigo + ", id_flor=" + id_flor + ", codigo_difinido=" + codigo_difinido + ", contrasena=" + contrasena + ", proveedor=" + proveedor + ", desidad=" + desidad + ", area=" + area + ", bulbos=" + bulbos + ", canastilla=" + canastilla + ", fechaE=" + fechaE + ", remision=" + remision + '}';
    }

    public FloresModelo(int id, String fechaE, String degradado, String telefono, String nombre, int parametro, int remision) {
        this.id = id;
        this.parametro = parametro;
        this.nombre = nombre;
        this.degradado = degradado;
        this.telefono = telefono;
        this.fechaE = fechaE;
        this.remision = remision;
    }
    
    
    public FloresModelo(int id_color, int id_variedad, int id_calibre, int id_referencia, int id_codigo, int id_flor, int codigo_difinido) {
        this.id_color = id_color;
        this.id_variedad = id_variedad;
        this.id_calibre = id_calibre;
        this.id_referencia = id_referencia;
        this.id_codigo = id_codigo;
        this.id_flor = id_flor;
        this.codigo_difinido = codigo_difinido;
    }

    public FloresModelo(String nombre, int id_color, int id_variedad, int id_calibre, int id_referencia, int id_codigo, int id_flor, int proveedor, int desidad, float area, int bulbos, float canastilla, int finca, String fechaE, int remision) {
        this.nombre = nombre;
        this.id_color = id_color;
        this.id_variedad = id_variedad;
        this.id_calibre = id_calibre;
        this.id_referencia = id_referencia;
        this.id_codigo = id_codigo;
        this.id_flor = id_flor;
        this.proveedor = proveedor;
        this.desidad = desidad;
        this.area = area;
        this.bulbos = bulbos;
        this.canastilla = canastilla;
        this.finca = finca;
        this.fechaE = fechaE;
        this.remision = remision;
        
    }
public FloresModelo(String nombre, int id_color, int id_variedad, int id_calibre, int id_referencia, int id_codigo, int id_flor, int proveedor, int desidad, float area, int bulbos, float canastilla, int finca, String fechaE, int remision, int parametro) {
        this.nombre = nombre;
        this.id_color = id_color;
        this.id_variedad = id_variedad;
        this.id_calibre = id_calibre;
        this.id_referencia = id_referencia;
        this.id_codigo = id_codigo;
        this.id_flor = id_flor;
        this.proveedor = proveedor;
        this.desidad = desidad;
        this.area = area;
        this.bulbos = bulbos;
        this.canastilla = canastilla;
        this.finca = finca;
        this.fechaE = fechaE;
        this.remision = remision;
        this.parametro = parametro;
        
    }
    
    public FloresModelo(int id, int semanas, int finca) {
        this.id = id;
        this.semanas = semanas;
        this.finca = finca;
    }

    public FloresModelo(int id, String nombre, String telefono,String documeto, String contransena) {
        this.id = id;
        this.nombre = nombre;
        this.documeto = documeto;
        this.telefono = telefono;
        this.contrasena = contransena;
    }

    public FloresModelo(int id_color, int id_variedad, int id_calibre, int id_referencia, int id_flor) {
        this.id_color = id_color;
        this.id_variedad = id_variedad;
        this.id_calibre = id_calibre;
        this.id_referencia = id_referencia;
        this.id_flor = id_flor;
    }
    

    public FloresModelo(int id, int finca) {
        this.id = id;
        this.finca = finca;
    }
public FloresModelo(int id, String nombre, int parametro, int finca){
        this.id = id;
        this.nombre = nombre;
        this.parametro = parametro;
        this.finca = finca;
    }
    public FloresModelo(int id, String nombre, int parametro) {
        this.id = id;
        this.parametro = parametro;
        this.nombre = nombre;
    }

    public FloresModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
//contructor agregar degradados

    public FloresModelo(int id, int parametro, String nombre, int semanas) {
        this.id = id;
        this.parametro = parametro;
        this.nombre = nombre;
        this.semanas = semanas;
    }
    
   
    public FloresModelo(String nombre) {
        this.nombre = nombre;
    }

    public FloresModelo(float area, int id) {
        this.id = id;
        this.area = area;
    }

    public FloresModelo(float area) {
        this.area = area;
    }

    public FloresModelo(int id) {
        this.id = id;
    }
    

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDegradado() {
        return degradado;
    }

    public void setDegradado(String degradado) {
        this.degradado = degradado;
    }

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParametro() {
        return parametro;
    }

    public void setParametro(int parametro) {
        this.parametro = parametro;
    }

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    public int getFinca() {
        return finca;
    }

    public void setFinca(int finca) {
        this.finca = finca;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public int getId_variedad() {
        return id_variedad;
    }

    public void setId_variedad(int id_variedad) {
        this.id_variedad = id_variedad;
    }

    public int getId_calibre() {
        return id_calibre;
    }

    public void setId_calibre(int id_calibre) {
        this.id_calibre = id_calibre;
    }

    public int getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(int id_referencia) {
        this.id_referencia = id_referencia;
    }

    public int getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(int id_codigo) {
        this.id_codigo = id_codigo;
    }

    public int getId_flor() {
        return id_flor;
    }

    public void setId_flor(int id_flor) {
        this.id_flor = id_flor;
    }

    public int getCodigo_difinido() {
        return codigo_difinido;
    }

    public void setCodigo_difinido(int codigo_difinido) {
        this.codigo_difinido = codigo_difinido;
    }

    public String getDocumeto() {
        return documeto;
    }

    public void setDocumeto(String documeto) {
        this.documeto = documeto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public int getDesidad() {
        return desidad;
    }

    public void setDesidad(int desidad) {
        this.desidad = desidad;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getBulbos() {
        return bulbos;
    }

    public void setBulbos(int bulbos) {
        this.bulbos = bulbos;
    }

    public float getCanastilla() {
        return canastilla;
    }

    public void setCanastilla(float canastilla) {
        this.canastilla = canastilla;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    public int getRemision() {
        return remision;
    }

    public void setRemision(int remision) {
        this.remision = remision;
    }
    
}
