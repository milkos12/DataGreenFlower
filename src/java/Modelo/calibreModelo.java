
package Modelo;

public class calibreModelo {
    private int id;
    private String nombre;

    @Override
    public String toString() {
        return "calibreModelo{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    public calibreModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public calibreModelo(int id) {
        this.id = id;
    }

    public calibreModelo(String nombre) {
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
    
}
