
package Modelo;

public class fincaModelo {
    private int id;
    private String nombre;
    private int semanas;
    private int extension;
    private float areaDisponible;

    @Override
    public String toString() {
        return "fincaModelo{" + "id=" + id + ", nombre=" + nombre + ", semanas=" + semanas + ", extension=" + extension + ", areaDisponible=" + areaDisponible + '}';
    }

    public fincaModelo(int id, String nombre, int extension) {
        this.id = id;
        this.nombre = nombre;
        this.extension = extension;
    }

    public fincaModelo(int id) {
        this.id = id;
    }

    public fincaModelo(int id, float areDisponible) {
        this.id = id;
        this.areaDisponible = areDisponible;
    }
 
    public fincaModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public fincaModelo(String nombre, int extension) {
        this.nombre = nombre;
        this.extension = extension;
    }

    public float getAreaDisponible() {
        return areaDisponible;
    }

    public void setAreaDisponible(float areaDisponible) {
        this.areaDisponible = areaDisponible;
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

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }
    
}
