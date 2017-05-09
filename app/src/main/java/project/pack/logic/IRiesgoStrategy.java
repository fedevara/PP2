package project.pack.logic;

import project.pack.domain.AbstractUbicacion;

/**
 * Created by sgarcete on 4/16/17.
 */

public interface IRiesgoStrategy {

    String getRiesgo(AbstractUbicacion ubicacion);

    Double calcularRiesgo(AbstractUbicacion ubicacion);

}
