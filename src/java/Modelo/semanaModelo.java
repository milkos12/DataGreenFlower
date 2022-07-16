
package Modelo;

public class semanaModelo {
    private int id;
    private String nombre;
    private String fecha;
    private int especie;
    private int variedad;
    private int finca;
    private int numeroSemnas;

    @Override
    public String toString() {
        return "semanaModelo{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", especie=" + especie + ", variedad=" + variedad + ", finca=" + finca + ", numeroSemnas=" + numeroSemnas + '}';
    }

   
    public semanaModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public semanaModelo(int id) {
        this.id = id;
    }

    public semanaModelo(int variedad, int finca) {
        this.variedad = variedad;
        this.finca = finca;
    }

    public semanaModelo(int variedad, int numeroSemnas, int finca) {
        this.variedad = variedad;
        this.finca = finca;
        this.numeroSemnas = numeroSemnas;
    }

    public semanaModelo(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getFinca() {
        return finca;
    }

    public void setFinca(int finca) {
        this.finca = finca;
    }

    public int getNumeroSemnas() {
        return numeroSemnas;
    }

    public void setNumeroSemnas(int numeroSemnas) {
        this.numeroSemnas = numeroSemnas;
    }
    
}
