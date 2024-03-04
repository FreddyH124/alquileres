package org.arso.services;

import org.arso.factory.FactoriaRepositorios;
import org.arso.interfaces.services.IServicioAlquileres;
import org.arso.interfaces.services.IServicioEstaciones;
import org.arso.model.Alquiler;
import org.arso.model.Bicicleta;
import org.arso.model.Reserva;
import org.arso.model.Usuario;
import org.arso.repository.EntidadNoEncontrada;
import org.arso.repository.NotAllowedException;
import org.arso.factory.FactoriaServicios;
import org.arso.repository.RepositorioUsuarioMemoria;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioAlquileres implements IServicioAlquileres {

    public Usuario getUsuario(String idUsuario){
        //TODO obtener usuario
        RepositorioUsuarioMemoria repositorio = FactoriaRepositorios.getRepositorio(Usuario.class);

        try {
            return repositorio.getById(idUsuario);
        } catch (EntidadNoEncontrada e) {
            Usuario usuario = new Usuario();
            repositorio.add(usuario);
            return usuario;
        }
    }
    @Override
    public void reservarBicicleta(String idUsuario, String idBicicleta) throws NotAllowedException {
        Usuario usuario = getUsuario(idUsuario);

        if(usuario.getReservaActiva() == null || usuario.getAlquilerActivo() == null){
            throw new NotAllowedException("El usuario ya tiene una reserva o alquiler en estado activo");
        }

        if(usuario.isBloqueado() || usuario.superaTiempoUso()){
            throw new NotAllowedException("El usuario esta bloqueado o supera el tiempo de uso");
        }

        Reserva reserva = new Reserva();
        reserva.setIdBicicleta(idBicicleta);
        reserva.setCreada(LocalDateTime.now());
        reserva.setCaducidad(LocalDateTime.now().plusMinutes(30));

        usuario.addReserva(reserva);

        //Todo actualizar usuario en la bbdd
    }

    @Override
    public void confirmarReserva(String idUsuario) throws NotAllowedException {
        Usuario usuario = getUsuario(idUsuario);

        Reserva reserva = usuario.getReservaActiva();

        if(reserva == null){
            throw new NotAllowedException("El usuario no una reserva pendientes de confirmar");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setIdBicicleta(reserva.getIdBicicleta());
        alquiler.setInicio(LocalDateTime.now());

        usuario.addAlquiler(alquiler);
        usuario.removeReserva(reserva);

        //Todo actualizar usuario en la bbdd
    }

    @Override
    public void alquilarBicicleta(String idUsuario, String idBicicleta) throws NotAllowedException {
        Usuario usuario = getUsuario(idUsuario);

        if(usuario.getReservaActiva() == null || usuario.getAlquilerActivo() == null){
            throw new NotAllowedException("El usuario ya tiene una reserva o alquiler en estado activo");
        }

        if(usuario.isBloqueado() || usuario.superaTiempoUso()){
            throw new NotAllowedException("El usuario esta bloqueado o supera el tiempo de uso");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setIdBicicleta(idBicicleta);
        alquiler.setInicio(LocalDateTime.now());

        usuario.addAlquiler(alquiler);

        //Todo actualizar usuario en la bbdd
    }

    @Override
    public Usuario historialUsuario(String idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        return usuario;
    }

    @Override
    public void dejarBicicleta(String idUsuario, String idEstacion) throws NotAllowedException {
        Usuario usuario = getUsuario(idUsuario);

        Alquiler alquiler = usuario.getAlquilerActivo();

        if(alquiler == null){
            throw new NotAllowedException("El usuario no una reserva pendientes de confirmar");
        }

        IServicioEstaciones servicio = FactoriaServicios.getServicio(IServicioEstaciones.class);

        if(!servicio.hayHueco(idEstacion)){
            throw new NotAllowedException("No se puede dejar la bicicleta en la estacion solicitada, no hay espacio");
        }

        //Todo obtener la bicicleta
        Bicicleta bicicleta = new Bicicleta();
        servicio.estacionarBicicleta(idEstacion, bicicleta);

        alquiler.setFin(LocalDateTime.now());

        //Todo actualizar usuario en la bbdd
    }

    @Override
    public void liberarBloqueo(String idUsuario) {
        Usuario usuario = getUsuario(idUsuario);

        List<Reserva> lista = usuario.getReservas().stream()
                                .filter(Reserva::isCaducada)
                                .collect(Collectors.toList());

        for(Reserva r : lista){
            usuario.removeReserva(r);
        }
    }
}
