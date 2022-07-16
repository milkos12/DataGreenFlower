
package Modelo;

public class siembreModelo {
    private int lote;
    private String fecha;
    private int usuario;

    @Override
    public String toString() {
        return "siembreModelo{" + "lote=" + lote + ", fecha=" + fecha + ", usuario=" + usuario + '}';
    }

    public siembreModelo(String fecha) {
        this.fecha = fecha;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
}
