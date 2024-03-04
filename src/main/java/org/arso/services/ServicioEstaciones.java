package org.arso.services;

import org.arso.interfaces.services.IServicioEstaciones;
import org.arso.model.Bicicleta;

public class ServicioEstaciones implements IServicioEstaciones {
    @Override
    public boolean hayHueco(String idEstacion) {
        return true;
    }

    @Override
    public boolean estacionarBicicleta(String idEstacion, Bicicleta bicicleta) {
        return true;
    }
}
