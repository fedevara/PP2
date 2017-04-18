package project.pack.domain;

import java.io.Serializable;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Coordenada implements Serializable {

    private Double latitud;
    private Double longitud;

    public Coordenada() {

    }

    public Coordenada(Double a, Double b) {
        setLatitud(a);
        setLongitud(b);
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getDistancia(Coordenada coordenada) {
        return Math.hypot(coordenada.latitud - this.latitud, coordenada.longitud - this.longitud);
    }
}
