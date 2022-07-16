package Modelo;

public class loteModelo {

    private int id;
    private String nombre;
    private String fecha;
    private int especie;
    private int variedad;
    private int color;
    private int referencia;
    private int calibre;
    private int proveedor;
    private int densidad;
    private float canastillas;
    private int bulbos;
    private float area;
    private int id_lote;
    private int codigo_defininido;
    private int finca;
    private int persona;
    private int id_contenido;
    private String estimado;
    private int remision;
    private String fechaEstimada;
    private int semanas;

    @Override
    public String toString() {
        return "loteModelo{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", especie=" + especie + ", variedad=" + variedad + ", color=" + color + ", referencia=" + referencia + ", calibre=" + calibre + ", proveedor=" + proveedor + ", densidad=" + densidad + ", canastillas=" + canastillas + ", bulbos=" + bulbos + ", area=" + area + ", id_lote=" + id_lote + ", codigo_defininido=" + codigo_defininido + ", finca=" + finca + ", persona=" + persona + ", id_contenido=" + id_contenido + ", estimado=" + estimado + ", remision=" + remision + ", fechaEstimada=" + fechaEstimada + ", semanas=" + semanas + '}';
    }

    

    public loteModelo(String fecha, int color, int variedad, int calibre, int referencia, int codigo_defininido, int especie, int proveedor, int densidad, float area, int bulbos, float canastillas, int finca, String fechaEstimada, int remision) {
        this.fecha = fecha;
        this.especie = especie;
        this.variedad = variedad;
        this.color = color;
        this.referencia = referencia;
        this.calibre = calibre;
        this.proveedor = proveedor;
        this.densidad = densidad;
        this.canastillas = canastillas;
        this.bulbos = bulbos;
        this.area = area;
        this.codigo_defininido = codigo_defininido;
        this.finca = finca;
        this.fechaEstimada = fechaEstimada;
        this.remision = remision;
    }
        
    public loteModelo(int color, int variedad, int calibre, int referencia, int codigo_defininido, int especie,  int densidad, int bulbos, float canastillas) {
        this.especie = especie;
        this.variedad = variedad;
        this.color = color;
        this.referencia = referencia;
        this.calibre = calibre;
        this.densidad = densidad;
        this.canastillas = canastillas;
        this.bulbos = bulbos;
        this.codigo_defininido = codigo_defininido;
    }

    public loteModelo(int id_lote, int codigo_defininido, int id_contenido, int semanas, int finca) {
        this.id_lote = id_lote;
        this.codigo_defininido = codigo_defininido;
        this.id_contenido = id_contenido;
        this.semanas = semanas;
        this.finca = finca;
    }

    public loteModelo(float canastillas, int bulbos, int id_lote) {
        this.canastillas = canastillas;
        this.bulbos = bulbos;
        this.id_lote = id_lote;
    }
    
    
    public loteModelo(int proveedor, int id_contenido) {
        this.proveedor = proveedor;
        this.id_contenido = id_contenido;
    }

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }


    public loteModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(String fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public loteModelo(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }

    public int getVariedad() {
        return variedad;
    }

    public void setVariedad(int variedad) {
        this.variedad = variedad;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public float getCanastillas() {
        return canastillas;
    }

    public void setCanastillas(float canastillas) {
        this.canastillas = canastillas;
    }

    public int getBulbos() {
        return bulbos;
    }

    public void setBulbos(int bulbos) {
        this.bulbos = bulbos;
    }

    public float getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getCodigo_defininido() {
        return codigo_defininido;
    }

    public void setCodigo_defininido(int codigo_defininido) {
        this.codigo_defininido = codigo_defininido;
    }

    public int getFinca() {
        return finca;
    }

    public void setFinca(int finca) {
        this.finca = finca;
    }

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

    public int getId_contenido() {
        return id_contenido;
    }

    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }

    public String getEstimado() {
        return estimado;
    }

    public void setEstimado(String estimado) {
        this.estimado = estimado;
    }

    public int getRemision() {
        return remision;
    }

    public void setRemision(int remision) {
        this.remision = remision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
