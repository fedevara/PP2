package project.pack.domain;

import java.util.Calendar;
import java.util.Date;

import project.pack.domain.interfaz.IUbicable;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Incidente implements IUbicable {

    private int id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private Date fechaCreacion;
    private Categoria categoria;
    private Coordenada coordenada;

    public Incidente(Coordenada coordenada, int id, String titulo, String descripcion, Date fecha, Date fechaCreacion, Categoria categoria) {
        this.coordenada = coordenada;
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

    public Incidente(Coordenada coordenada, String titulo, String descripcion, Date fecha, Date fechaCreacion, Categoria categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.coordenada = coordenada;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public Coordenada getCoordenada() {return this.coordenada;}

    public Double getDistancia(IUbicable ubicacion) {
        return Math.hypot(ubicacion.getCoordenada().getLatitud()- this.getCoordenada().getLatitud(), ubicacion.getCoordenada().getLongitud() - this.getCoordenada().getLongitud());
    }

    @Override
    public String toString(){
        return descripcion + " - " + getCategoria().getNombre();
    }

}
