
package Modelo;

public class corteModelo {
    private int id;
    private String fecha;
    private int semana;
    private int canastillas;
    private int persona;
    private int referencia;
    private int color;
    private int diasHasElCorte;
    private int clasificacion;
    private int nacional;
    private int bajas;
    private int tallos;
    private int lote;
    private int id_contenido_corte;

    @Override
    public String toString() {
        return "corteModelo{" + "id=" + id + ", fecha=" + fecha + ", semana=" + semana + ", canastillas=" + canastillas + ", persona=" + persona + ", referencia=" + referencia + ", color=" + color + ", diasHasElCorte=" + diasHasElCorte + ", clasificacion=" + clasificacion + ", nacional=" + nacional + ", bajas=" + bajas + ", tallos=" + tallos + ", lote=" + lote + ", id_contenido_corte=" + id_contenido_corte + '}';
    }

    public corteModelo(int id, int lote) {
        this.id = id;
        this.lote = lote;
    }

    public corteModelo(int id,String fecha, int referencia, int color, int diasHasElCorte, int clasificacion, int tallos, int nacional, int bajas) {
        this.fecha = fecha;
        this.referencia = referencia;
        this.color = color;
        this.diasHasElCorte = diasHasElCorte;
        this.clasificacion = clasificacion;
        this.nacional = nacional;
        this.bajas = bajas;
        this.tallos = tallos;
        this.id = id;
    }

    public corteModelo(int id, String fecha, int referencia, int color, int diasHasElCorte, int id_contenido_corte, int nacionales, int bajas) {
        this.id = id;
        this.fecha = fecha;
        this.referencia = referencia;
        this.color = color;
        this.diasHasElCorte = diasHasElCorte;
        this.id_contenido_corte = id_contenido_corte;
        this.nacional = nacionales;
        this.bajas = bajas;
    }

        
    public corteModelo(int id_contenido_corte, int clasificacion, int tallos) {
        this.clasificacion = clasificacion;
        this.tallos = tallos;
        this.id_contenido_corte = id_contenido_corte;
       
    }
public corteModelo(String fecha) {
        this.fecha = fecha;
    }

    public corteModelo(int semana) {
        this.semana = semana;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getCanastillas() {
        return canastillas;
    }

    public void setCanastillas(int canastillas) {
        this.canastillas = canastillas;
    }

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDiasHasElCorte() {
        return diasHasElCorte;
    }

    public void setDiasHasElCorte(int diasHasElCorte) {
        this.diasHasElCorte = diasHasElCorte;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getNacional() {
        return nacional;
    }

    public void setNacional(int nacional) {
        this.nacional = nacional;
    }

    public int getBajas() {
        return bajas;
    }

    public void setBajas(int bajas) {
        this.bajas = bajas;
    }

    public int getTallos() {
        return tallos;
    }

    public void setTallos(int tallos) {
        this.tallos = tallos;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getId_contenido_corte() {
        return id_contenido_corte;
    }

    public void setId_contenido_corte(int id_contenido_corte) {
        this.id_contenido_corte = id_contenido_corte;
    }
    
    
}
