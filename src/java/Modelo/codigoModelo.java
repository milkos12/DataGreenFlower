package Modelo;

public class codigoModelo {

    private int id;
    private int codigoDefinido;
    private int color;
    private int referencia;
    private int especie;
    private int variedad;
    private int calibre;

    @Override
    public String toString() {
        return "codigoModelo{" + "id=" + id + ", codigoDefinido=" + codigoDefinido + ", color=" + color + ", referencia=" + referencia + ", especie=" + especie + ", variedad=" + variedad + ", calibre=" + calibre + '}';
    }

    public codigoModelo(int color, int variedad, int calibre, int referencia, int especie, int codigoDefinido) {
        this.codigoDefinido = codigoDefinido;
        this.color = color;
        this.referencia = referencia;
        this.especie = especie;
        this.variedad = variedad;
        this.calibre = calibre;
    }

    public codigoModelo(int color, int referencia, int especie, int variedad) {
        this.color = color;
        this.referencia = referencia;
        this.especie = especie;
        this.variedad = variedad;
    }

    public codigoModelo(int color, int variedad, int calibre, int referencia, int especie) {
        this.color = color;
        this.referencia = referencia;
        this.especie = especie;
        this.variedad = variedad;
        this.calibre = calibre;
    }

    public codigoModelo(int color, int variedad, int calibre, int referencia, int id, int especie, int codigoDefinido) {
        this.id = id;
        this.codigoDefinido = codigoDefinido;
        this.color = color;
        this.referencia = referencia;
        this.especie = especie;
        this.variedad = variedad;
        this.calibre = calibre;
    }

    public codigoModelo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoDefinido() {
        return codigoDefinido;
    }

    public void setCodigoDefinido(int codigoDefinido) {
        this.codigoDefinido = codigoDefinido;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
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

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

}
