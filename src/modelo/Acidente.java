package modelo;

import java.time.LocalDateTime;
import org.jxmapviewer.viewer.GeoPosition;

public class Acidente {
    private int id;
    private LocalDateTime data;
    private String log1;
    private String log2;
    private Tipo_acidente tipo;
    private Dia_semana dia_sem;
    private Dia_noite dia_noite;
    private Tempo tempo;
    private int fx_hora;
    private GeoPosition geo;

    public Acidente(int id, LocalDateTime data, String log1, String log2, Tipo_acidente tipo, Dia_semana dia_sem, Dia_noite dia_noite, Tempo tempo, int fx_hora, GeoPosition geo) {
        this.id = id;
        this.data = data;
        this.log1 = log1;
        this.log2 = log2;
        this.tipo = tipo;
        this.dia_sem = dia_sem;
        this.dia_noite = dia_noite;
        this.tempo = tempo;
        this.fx_hora = fx_hora;
        this.geo = geo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tipo_acidente getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_acidente tipo) {
        this.tipo = tipo;
    }

    
    
    public String getLog1() {
        return log1;
    }

    public void setLog1(String log1) {
        this.log1 = log1;
    }

    public String getLog2() {
        return log2;
    }

    public void setLog2(String log2) {
        this.log2 = log2;
    }

    public Dia_semana getDia_sem() {
        return dia_sem;
    }

    public void setDia_sem(Dia_semana dia_sem) {
        this.dia_sem = dia_sem;
    }

    public Dia_noite getDia_noite() {
        return dia_noite;
    }

    public void setDia_noite(Dia_noite dia_noite) {
        this.dia_noite = dia_noite;
    }

    public Tempo getTempo() {
        return tempo;
    }

    public void setTempo(Tempo tempo) {
        this.tempo = tempo;
    }

    public int getFx_hora() {
        return fx_hora;
    }

    public void setFx_hora(int fx_hora) {
        this.fx_hora = fx_hora;
    }

    public GeoPosition getGeo() {
        return geo;
    }

    public void setGeo(GeoPosition geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Acidente {" + id + " - " + data + " - " + log1 + " - " + log2 + " - " + dia_sem + " - " + dia_noite + " - " + tempo + " - " + fx_hora + " - " + geo + "}";
    }
}
