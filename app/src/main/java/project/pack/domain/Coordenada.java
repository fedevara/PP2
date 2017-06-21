package project.pack.domain;


/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Coordenada {

    private Double latitud;
    private Double longitud;

    public Coordenada() {

    }

    public Coordenada(Double latitud, Double longitud) {
        setLatitud(latitud);
        setLongitud(longitud);
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
