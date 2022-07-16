
package Modelo;

public class variedadModelo {
    private int id;
    private String nombre;
    private int variedad;

    @Override
    public String toString() {
        return "variedadModelo{" + "id=" + id + ", nombre=" + nombre + ", variedad=" + variedad + '}';
    }

    public variedadModelo(int id, String nombre, int variedad) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
    }

    public variedadModelo(String nombre) {
        this.nombre = nombre;
    }

    public variedadModelo(int id) {
        this.id = id;
    }
    

    public variedadModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public int getVariedad() {
        return variedad;
    }

    public void setVariedad(int variedad) {
        this.variedad = variedad;
    }
    
}
