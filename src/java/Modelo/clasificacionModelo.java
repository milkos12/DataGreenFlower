
package Modelo;

public class clasificacionModelo {
    private int id;
    private String nombre;

    @Override
    public String toString() {
        return "clasificacionModelo{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    public clasificacionModelo(String nombre) {
        this.nombre = nombre;
    }

    public clasificacionModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public clasificacionModelo(int id) {
        this.id = id;
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
