package project.pack.logic;

import project.pack.domain.interfaz.IUbicable;

/**
 * Created by sgarcete on 4/16/17.
 */

public interface IRiesgoStrategy {

    String getRiesgo(IUbicable ubicacion);

    Double calcularRiesgo(IUbicable ubicacion);

}
