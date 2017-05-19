package project.pack.domain;

/*
 * Created by Federico Vara on 14/4/2017.
 */

import project.pack.domain.interfaz.IUbicable;
import project.pack.logic.IRiesgoStrategy;
import project.pack.logic.RiesgoBuilder;
import project.pack.logic.RiesgoUbicacionStrategy;

public class Establecimiento implements IUbicable {

    private String nombre;
    private Categoria categoria;
    private Coordenada coordenada;
    private String riesgo;

    public Establecimiento(Coordenada coordenada, String nombre, Categoria categoria) {
        this.coordenada = coordenada;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getRiesgo() {return riesgo;}

    public void setRiesgo(String riesgo) {this.riesgo = riesgo;}

    public void generarRiesgo(){
        IRiesgoStrategy riesgoStrategy = new RiesgoUbicacionStrategy();
        RiesgoBuilder riesgoBuilder = new RiesgoBuilder(riesgoStrategy);
        this.riesgo = riesgoBuilder.getRiesgo(this);
    }

    @Override
    public Coordenada getCoordenada() {
        return this.coordenada;
    }

    @Override
    public Double getDistancia(IUbicable ubicacion) {
        return Math.hypot(ubicacion.getCoordenada().getLatitud()- this.getCoordenada().getLatitud(), ubicacion.getCoordenada().getLongitud() - this.getCoordenada().getLongitud());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establecimiento)) return false;

        Establecimiento that = (Establecimiento) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null)
            return false;
        return coordenada != null ? coordenada.equals(that.coordenada) : that.coordenada == null;

    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (coordenada != null ? coordenada.hashCode() : 0);
        return result;
    }
}
