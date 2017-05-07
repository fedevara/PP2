package project.pack.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class ManejoIncidente {

    public ManejoIncidente() {

    }

    public Incidente crearIncidente(Integer id, String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada lugar) {
        Incidente incidente = new Incidente();
        incidente.setId(id);
        incidente.setTitulo(titulo);
        incidente.setDescripcion(descripcion);
        if (fecha == null) {
            incidente.setFecha(Calendar.getInstance().getTime());
        } else {
            incidente.setFecha(fecha);
        }
        incidente.setFechaCreacion(Calendar.getInstance().getTime());
        incidente.setCategoria(categoria);
        incidente.setCoordenada(lugar);

        return incidente;
    }

    public void guardarIncidente(Incidente incidente) {
        System.out.println("ManejoIncidente.guardar incidente ID: "+ incidente.getId());
        CacheSingleton.getInstance().put(incidente);
    }

    public Incidente getIncidente(Integer id) {
        return (Incidente) CacheSingleton.getInstance().get(id);
    }

    public List<Incidente> getListaIncidentes() {

        List<Incidente> listaIncidentes = CacheSingleton.getInstance().obtenerListaIncidentes();
        return listaIncidentes;
    }

    /**
     * Dada una coordenada, devuelvo los incidentes que corresponden al rango de dicha coordenada.
     *
     * @param coordenada que se le envia.
     * @return devuelvo lista que cumple el rango.
     */
    public List<Incidente> getListaIncidentesConCoordenada(Coordenada coordenada) {

        List<Incidente> listaIncidentes = CacheSingleton.getInstance().obtenerListaIncidentes();
        List<Incidente> incidentesAprobados = new ArrayList<>();

        Double distanciaMaxima = 10.5;

        for (int i = 0; i < listaIncidentes.size(); i++) {
            //Obtengo la distancia entre las coordenadas del Incidente
            Double distanciaIncidente = listaIncidentes.get(i).getCoordenada().getDistancia(coordenada);

            if (distanciaIncidente <= distanciaMaxima) {
                incidentesAprobados.add(listaIncidentes.get(i));
            }
        }
        return incidentesAprobados;
    }
}