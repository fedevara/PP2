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
    private Coordenada lugar;

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

    public Coordenada getLugar() {
        return lugar;
    }

    public void setLugar(Coordenada lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        dest.writeSerializable(lugar);
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
        lugar = (Coordenada) in.readSerializable();
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
