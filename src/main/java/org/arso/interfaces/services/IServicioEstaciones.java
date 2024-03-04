package org.arso.interfaces.services;

import org.arso.model.Bicicleta;

public interface IServicioEstaciones {

    boolean hayHueco(String idEstacion);

    boolean estacionarBicicleta(String idEstacion, Bicicleta bicicleta);
}
