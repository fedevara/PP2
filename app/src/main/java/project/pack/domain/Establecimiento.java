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

    @Override
    public Coordenada getCoordenada() {
        return this.coordenada;
    }

    @Override
    public Double getDistancia(IUbicable ubicacion) {
        return Math.hypot(ubicacion.getCoordenada().getLatitud()- this.getCoordenada().getLatitud(), ubicacion.getCoordenada().getLongitud() - this.getCoordenada().getLongitud());
    }
}
