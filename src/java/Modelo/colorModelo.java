
package Modelo;

public class colorModelo {
    private String nombre;
    private int id;
    private int referencia;

    @Override
    public String toString() {
        return "colorModelo{" + "nombre=" + nombre + ", id=" + id + ", referencia=" + referencia + '}';
    }

    public colorModelo(String nombre) {
        this.nombre = nombre;
    }

    public colorModelo(int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public colorModelo(int id, String nombre, int referencia) {
        this.nombre = nombre;
        this.id = id;
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public colorModelo(int id) {
        this.id = id;
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

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
   
    
}
