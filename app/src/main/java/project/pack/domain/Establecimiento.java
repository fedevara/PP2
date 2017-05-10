package project.pack.domain;

/*
 * Created by Federico Vara on 14/4/2017.
 */

public class Establecimiento extends AbstractUbicacion {

    private String nombre;
    private Categoria categoria;
    private String riesgo;

    public Establecimiento(Coordenada coordenada, String nombre, Categoria categoria) {
        super(coordenada);
        this.nombre = nombre;
        this.categoria = categoria;
    }

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

    public String getRiesgo() {return riesgo;}

    public void setRiesgo(String riesgo) {this.riesgo = riesgo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establecimiento)) return false;

        Establecimiento that = (Establecimiento) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null)
            return false;
        return riesgo != null ? riesgo.equals(that.riesgo) : that.riesgo == null;

    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (riesgo != null ? riesgo.hashCode() : 0);
        return result;
    }

    public void generarRiesgo(){
        riesgo = super.calcularRiesgo();
    }
}
