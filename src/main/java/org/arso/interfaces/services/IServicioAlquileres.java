package org.arso.interfaces.services;

import org.arso.model.Usuario;
import org.arso.repository.NotAllowedException;

public interface IServicioAlquileres {
	Usuario getUsuario(String idUsuario);
    void reservarBicicleta(String idUsuario, String idBicicleta) throws IllegalStateException;
    void confirmarReserva(String idUsuario) throws IllegalStateException;
    void alquilarBicicleta(String idUsuario, String idBicicleta) throws IllegalStateException;

    Usuario historialUsuario(String idUsuario);

    void dejarBicicleta(String idUsuario, String idEstacion) throws IllegalStateException;
    void liberarBloqueo(String idUsuario);
}
