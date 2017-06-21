package project.pack.domain;

/*
 * Created by Federico Vara on 14/4/2017.
 */
import project.pack.domain.interfaz.IUbicable;

public class Establecimiento implements IUbicable {

    private String nombre;
    private Categoria categoria;
    private Coordenada coordenada;
    private int id;

    public Establecimiento(Coordenada coordenada, String nombre, Categoria categoria) {
        this.coordenada = coordenada;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Establecimiento(int id, Coordenada coordenada, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
