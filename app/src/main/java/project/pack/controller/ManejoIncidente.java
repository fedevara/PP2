package project.pack.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.persistence.PersistenciaIncidente;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class ManejoIncidente {

    private PersistenciaIncidente Persistencia = new PersistenciaIncidente();

    public ManejoIncidente() {}

    public Incidente crearIncidente(Coordenada coordenada,String titulo, String descripcion, Date fecha, Categoria categoria) {

        Incidente incidente = new Incidente(coordenada, titulo, descripcion, fecha, new Date(), categoria);

        return incidente;
    }

    public Incidente guardarIncidente(Incidente incidente) {
        incidente = Persistencia.addIncidente(incidente);
        return incidente;
    }

    public Incidente getIncidente(int id) {
        List<Incidente> List = getListaIncidentes();
        if(List!=null && List.size()>0){
            for (int i = 0; i < List.size(); i++){
                if(List.get(i).getId() == id){
                    return List.get(i);
                }
            }
        }
        return null;
    }

    public List<Incidente> getListaIncidentes() {
        List<Incidente> listaIncidentes = Persistencia.getListaIncidente();
        return listaIncidentes;
    }

    /**
     * Dada una coordenada, devuelvo los incidentes que corresponden al rango de dicha coordenada.
     *
     * @param coordenada que se le envia.
     * @return devuelvo lista que cumple el rango.
     */
    public List<Incidente> getListaIncidentesConCoordenada(Coordenada coordenada) {
        List<Incidente> listaIncidentes = getListaIncidentes();
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
    public void eliminarBD(){
        Persistencia.eliminarBDPersistencia();
    }
}