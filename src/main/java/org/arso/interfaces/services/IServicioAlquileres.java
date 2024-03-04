package org.arso.interfaces.services;

import org.arso.model.Usuario;
import org.arso.repository.NotAllowedException;

public interface IServicioAlquileres {
    void reservarBicicleta(String idUsuario, String idBicicleta) throws NotAllowedException;
    void confirmarReserva(String idUsuario) throws NotAllowedException;
    void alquilarBicicleta(String idUsuario, String idBicicleta) throws NotAllowedException;

    Usuario historialUsuario(String idUsuario);

    void dejarBicicleta(String idUsuario, String idEstacion) throws NotAllowedException;
    void liberarBloqueo(String idUsuario);
}
