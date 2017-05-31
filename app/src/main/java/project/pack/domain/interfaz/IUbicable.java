package project.pack.domain.interfaz;

/*
 * Created by sgarcete on 5/7/17.
 */

import project.pack.domain.Coordenada;


public interface IUbicable {

    public Coordenada getCoordenada();

    public Double getDistancia(IUbicable ubicacion);

}
