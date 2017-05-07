package project.pack.domain;

/*
 * Created by Federico Vara on 14/4/2017.
 */

public class Establecimiento {

    private String nombre;
    private Categoria categoria;
    private Coordenada coordenada;
    private String riesgo;

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

    public String getRiesgo() {return riesgo;}

    public void setRiesgo(String riesgo) {this.riesgo = riesgo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

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
