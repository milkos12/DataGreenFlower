package Modelo;

public class usuarioModelo {

    private int id;
    private String nombre;
    private String documento;
    private String telefono;
    private String contrasena;

    @Override
    public String toString() {
        return "usuarioModelo{" + "id=" + id + ", nombre=" + nombre + ", documento=" + documento + ", telefono=" + telefono + ", contrasena=" + contrasena + '}';
    }

    public usuarioModelo(int id, String nombre, String telefono, String documento, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public usuarioModelo(String nombre, String telefono, String documento, String contrasena) {

        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public usuarioModelo(int id) {
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
