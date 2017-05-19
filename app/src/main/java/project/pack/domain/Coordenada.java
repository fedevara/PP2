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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordenada that = (Coordenada) o;

        if (this.latitud != null ? !this.latitud.equals(that.latitud) : that.latitud != null) return false;
        return this.longitud != null ? this.longitud.equals(that.longitud) : that.longitud == null;

    }

    @Override
    public int hashCode() {
        int result = latitud != null ? latitud.hashCode() : 0;
        result = 31 * result + (longitud != null ? longitud.hashCode() : 0);
        return result;
    }
}
