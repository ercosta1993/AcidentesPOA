package model;

import org.jxmapviewer.viewer.GeoPosition;

public class Acidente {
    private int id;
    private String log1;
    private String log2;
    private Tipo_acidente tipo;
    private Dia_semana dia_sem;
    private Dia_noite dia_noite;
    private Tempo tempo;
    private int fx_hora;
    private GeoPosition geo;

    public Acidente(int id, String log1, String log2, int fx_hora, GeoPosition geo, Dia_semana d) {
        this.id = id;
        this.log1 = log1;
        this.log2 = log2;
        this.fx_hora = fx_hora;
        this.geo = geo;
        this.dia_sem = d;
    }

    public int getId() {
        return id;
    }

    public String getLog1() {
        return log1;
    }

    public String getLog2() {
        return log2;
    }

    public GeoPosition getGeo() {
        return geo;
    }
    
    public Tipo_acidente getTipo(){
        return tipo;
    }
    
    public int getFx_hora() {
        return fx_hora;
    }
    
    public Dia_semana getDia_sem(){
        return dia_sem;
    }
    
    public Dia_noite getDia_noite(){
        return dia_noite;
    }
    
    public Tempo getTempo(){
        return tempo;
    }
    
    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append(id).append(" - ").append(log1).append(" - ").append(log2).append(" - ").append(fx_hora).append(" - ").append(geo);
        return msg.toString();
    }
}
