package project.pack.domain;

/**
 * Created by sgarcete on 5/7/17.
 */

public abstract class AbstractUbicacion {

    private Coordenada coordenada;

    public AbstractUbicacion(Coordenada coordenada) {

        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Double getDistancia(AbstractUbicacion ubicacion) {
        return Math.hypot(ubicacion.getCoordenada().getLatitud()- this.getCoordenada().getLatitud(), ubicacion.getCoordenada().getLongitud() - this.getCoordenada().getLongitud());
    }
}
