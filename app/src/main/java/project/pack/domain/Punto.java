package project.pack.domain;
/*
 * Created by Federico Vara on 9/5/2017.
 */

import project.pack.domain.interfaz.IUbicable;
import project.pack.logic.IRiesgoStrategy;
import project.pack.logic.RiesgoBuilder;
import project.pack.logic.RiesgoUbicacionStrategy;

public class Punto implements IUbicable {

    Coordenada coordenada;

    public Punto(Coordenada coordenada) {

        this.coordenada = coordenada;

    }

    @Override
    public Coordenada getCoordenada() {
        return this.coordenada;
    }

    @Override
    public Double getDistancia(IUbicable ubicacion) {
        return Math.hypot(ubicacion.getCoordenada().getLatitud()- this.getCoordenada().getLatitud(), ubicacion.getCoordenada().getLongitud() - this.getCoordenada().getLongitud());
    }

    public String calcularRiesgo(){
            IRiesgoStrategy riesgoStrategy = new RiesgoUbicacionStrategy();
            RiesgoBuilder riesgoBuilder = new RiesgoBuilder(riesgoStrategy);
            return riesgoBuilder.getRiesgo(this);
    }
}
