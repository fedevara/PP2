package project.pack.domain;

/**
 * Created by Federico Vara on 14/4/2017.
 */

public class Establecimiento {

    private String nombre;
    private Categoria categoria;
    private Coordenada coordenada;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
}
