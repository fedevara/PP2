package project.pack.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Incidente implements Parcelable {

    private int id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private Date fechaCreacion;
    private Categoria categoria;
    private Coordenada coordenada;

    public Incidente(){

    }

    public int getId(){ return id; }

    public void setId(int id){ this.id=id; }

    public String getTitulo() { return titulo; }

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

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incidente incidente = (Incidente) o;

        if (id != incidente.id) return false;
        if (titulo != null ? !titulo.equals(incidente.titulo) : incidente.titulo != null)
            return false;
        if (descripcion != null ? !descripcion.equals(incidente.descripcion) : incidente.descripcion != null)
            return false;
        if (fecha != null ? !fecha.equals(incidente.fecha) : incidente.fecha != null) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(incidente.fechaCreacion) : incidente.fechaCreacion != null)
            return false;
        if (categoria != null ? !categoria.equals(incidente.categoria) : incidente.categoria != null)
            return false;
        return lugar != null ? lugar.equals(incidente.lugar) : incidente.lugar == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (lugar != null ? lugar.hashCode() : 0);
        return result;
    }

    // SECCION QUE IMPLEMENTA METODOS NECESARIOS PARA EL ENVIO DE UN INCIDENTE DE UN ACTIVITY A OTRO

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    /**
     * Esta clase se usa para poder guardar el objeto cuando se envia de una activity a otra
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(id);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeLong(fecha.getTime());
        dest.writeLong(fechaCreacion.getTime());
        dest.writeSerializable(categoria);
        dest.writeSerializable(coordenada);
    }

    /**
     * Esta clase se usa para poder leer el objeto cuando se recibe de una activity
     * Las asignaciones de variables tienen que estar en el mismo orden que en el write
     */
    private void readFromParcel(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        descripcion = in.readString();
        fecha = new Date(in.readLong());
        fechaCreacion = new Date(in.readLong());
        categoria = (Categoria) in.readSerializable();
        coordenada = (Coordenada) in.readSerializable();
    }

    public static final Creator<Incidente> CREATOR = new Creator<Incidente>() {
        public Incidente createFromParcel(Parcel in) {
            return new Incidente(in);
        }

        public Incidente[] newArray(int size) {
            return new Incidente[size];
        }
    };

    public Incidente(Parcel in) {
        readFromParcel(in);
    }
}
