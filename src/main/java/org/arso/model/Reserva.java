package org.arso.model;

import java.time.LocalDateTime;

public class Reserva {
    private String idBicicleta;
    private LocalDateTime creada;
    private LocalDateTime caducidad;


    public Reserva(){

    }

    public String getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(String idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public LocalDateTime getCreada() {
        return creada;
    }

    public void setCreada(LocalDateTime creada) {
        this.creada = creada;
    }

    public LocalDateTime getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDateTime caducidad) {
        this.caducidad = caducidad;
    }

    public boolean isCaducada(){
        return LocalDateTime.now().isAfter(caducidad);
    }

    public boolean isActiva(){
        return !isCaducada();
    }

}