
package Modelo;

public class referenciaModelo {
    private int id;
    private String nombre;
    private int color;
    private int variedad;

    @Override
    public String toString() {
        return "referenciaModelo{" + "id=" + id + ", nombre=" + nombre + ", color=" + color + ", variedad=" + variedad + '}';
    }

    public referenciaModelo(String nombre, int color, int variedad) {
        this.nombre = nombre;
        this.color = color;
        this.variedad = variedad;
    }

    public referenciaModelo(int id) {
        this.id = id;
    }
    

    public referenciaModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public referenciaModelo(int id, String nombre, int color) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public referenciaModelo(int color, int variedad) {
        this.color = color;
        this.variedad = variedad;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getVariedad() {
        return variedad;
    }

    public void setVariedad(int variedad) {
        this.variedad = variedad;
    }
    
}
