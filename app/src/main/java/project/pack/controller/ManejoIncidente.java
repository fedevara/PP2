package project.pack.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;

/**
 * Created by Familia Vara on 9/4/2017.
 */

public class ManejoIncidente {

    public ManejoIncidente(){

    }

    public Incidente crearIncidente(Integer id, String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada lugar) {
        Incidente incidente = new Incidente();
        incidente.setId(id);
        incidente.setTitulo(titulo);
        incidente.setDescripcion(descripcion);
        if(fecha == null){
            incidente.setFecha(Calendar.getInstance().getTime());
        }else{
            incidente.setFecha(fecha);}
        incidente.setFechaCreacion(Calendar.getInstance().getTime());
        incidente.setCategoria(categoria);
        incidente.setLugar(lugar);

        return incidente;
    }

    public void guardarIncidente(Incidente incidente){
        System.out.print("guardar incidente");

        CacheSingleton.getInstance().put(incidente);
        Integer size = CacheSingleton.getInstance().size();
    }

    public Incidente getIncidente(Integer id1) {
        return (Incidente) CacheSingleton.getInstance().get(id1);
    }

    public List<Incidente> getListaIncidentes(){

        List<Incidente> listaIncidentes = CacheSingleton.getInstance().obtenerListaIncidentes();
        return  listaIncidentes;
    }
}
