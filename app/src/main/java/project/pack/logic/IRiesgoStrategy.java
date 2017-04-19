package project.pack.logic;

/**
 * Created by sgarcete on 4/16/17.
 */

public interface IRiesgoStrategy {

    String getRiesgo(Object object);

    Double calcularRiesgo(Object object);

}
