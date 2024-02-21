package org.arso.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Alquiler {
    private String idBicicleta;
    private LocalDateTime inicio;
    private LocalDateTime fin;

    public Alquiler(){

    }

    public String getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(String idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public boolean isActivo(){
        return fin == null;
    }

    public long getTiempoAlquiler(){
        if (fin == null) {
            return Duration.between(inicio, LocalDateTime.now()).toMinutes();
        }

        return Duration.between(inicio, fin).toMinutes();
    }
}
