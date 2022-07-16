
package Modelo;

public class proveedorModelo {
    private int id;
    private String nombre;
    private String documento;
    private String telefono;

    @Override
    public String toString() {
        return "proveedorModelo{" + "id=" + id + ", nombre=" + nombre + ", documento=" + documento + ", telefono=" + telefono + '}';
    }

    public proveedorModelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
                            
    public proveedorModelo(int id, String nombre, String telefono, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public proveedorModelo(String nombre, String telefono, String documento) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public proveedorModelo(int id) {
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
