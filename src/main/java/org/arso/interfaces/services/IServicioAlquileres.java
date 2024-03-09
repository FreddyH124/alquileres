package org.arso.interfaces.services;

import org.arso.model.Usuario;
import org.arso.repository.NotAllowedException;
import org.arso.repository.RepositorioException;

public interface IServicioAlquileres {
    void reservarBicicleta(String idUsuario, String idBicicleta) throws NotAllowedException, RepositorioException;
    void confirmarReserva(String idUsuario) throws NotAllowedException, RepositorioException;
    void alquilarBicicleta(String idUsuario, String idBicicleta) throws NotAllowedException, RepositorioException;

    Usuario historialUsuario(String idUsuario) throws RepositorioException;

    void dejarBicicleta(String idUsuario, String idEstacion) throws NotAllowedException, RepositorioException;
    void liberarBloqueo(String idUsuario) throws RepositorioException;
}
