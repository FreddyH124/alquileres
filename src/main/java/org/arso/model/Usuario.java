package org.arso.model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Usuario {
    private String id;
    private List<Reserva> reservas;
    private List<Alquiler> alquileres;

    public Usuario(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    public long getreservasCaducadas(){
        return reservas.stream()
                .filter(Reserva::isCaducada)
                .count();
    }

    public long getTiempoUsoHoy(){
        return alquileres.stream()
                .filter(alquiler -> alquiler.getInicio().equals(LocalDateTime.now()))
                .mapToLong(Alquiler::getTiempoAlquiler)
                .sum();
    }

    public long getTiempoUsoSemanal(){
        LocalDateTime haceUnaSemana = LocalDateTime.now().minusDays(6);
        LocalDateTime manana = LocalDateTime.now().plusDays(1);
        return alquileres.stream()
                .filter(alquiler -> alquiler.getInicio().isAfter(haceUnaSemana) && alquiler.getInicio().isBefore(manana))
                .mapToLong(Alquiler::getTiempoAlquiler)
                .sum();
    }

    public boolean superaTiempoUso(){
        return getTiempoUsoHoy() >= 60 || getTiempoUsoSemanal() >= 180;
    }

    public Reserva getReservaActiva(){
        Optional<Reserva> ultima = reservas.stream()
                .filter(Reserva::isActiva)
                .max(Comparator.comparing(Reserva::getCreada));

        return ultima.orElse(null);
    }

    public Alquiler getAlquilerActivo(){
        Optional<Alquiler> ultimo = alquileres.stream()
                .filter(Alquiler::isActivo)
                .max(Comparator.comparing(Alquiler::getInicio));

        return ultimo.orElse(null);
    }

    public boolean isBloqueado(){
        long count = reservas.stream()
                .filter(Reserva::isCaducada)
                .count();

        return count >= 3;
    }
}