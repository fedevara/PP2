package project.pack.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Incidente extends AbstractUbicacion{

    private int id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private Date fechaCreacion;
    private Categoria categoria;


    public Incidente(Coordenada coordenada, int id, String titulo, String descripcion, Date fecha, Date fechaCreacion, Categoria categoria) {
        super(coordenada);
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.categoria = categoria;

        if (fecha == null) {
            this.fecha = Calendar.getInstance().getTime();
        } else {
            this.fecha = fecha;
        }
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCoordenada(Coordenada coordenada){
        super.setCoordenada(coordenada);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Incidente)) return false;

        Incidente incidente = (Incidente) o;

        if (id != incidente.id)
            return false;
        if (titulo != null ? !titulo.equals(incidente.titulo) : incidente.titulo != null)
            return false;
        if (descripcion != null ? !descripcion.equals(incidente.descripcion) : incidente.descripcion != null)
            return false;
        if (fecha != null ? !fecha.equals(incidente.fecha) : incidente.fecha != null)
            return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(incidente.fechaCreacion) : incidente.fechaCreacion != null)
            return false;
        return categoria != null ? categoria.equals(incidente.categoria) : incidente.categoria == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }
}
