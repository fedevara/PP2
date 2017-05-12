package project.pack.controller;

import java.util.ArrayList;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.logic.IRiesgoStrategy;
import project.pack.logic.RiesgoBuilder;
import project.pack.logic.RiesgoUbicacionStrategy;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class ManejoEstablecimiento {

    public ManejoEstablecimiento() {

    }

    public Establecimiento crearEstablecimiento(Coordenada coordenada, String nombre, Categoria categoria) {

        Establecimiento establecimiento = new Establecimiento(coordenada, nombre, categoria);

        return establecimiento;
    }

    public void guardarEstablecimiento(Establecimiento establecimiento) {
        CacheSingleton.getInstance().put(establecimiento);
    }

    public Establecimiento getEstablecimiento(int id1) {
        Establecimiento establecimiento = (Establecimiento) CacheSingleton.getInstance().get(id1);
        establecimiento.generarRiesgo();
        return establecimiento;
    }

    /**
     * Dada una coordenada, devuelvo los establecimientos que corresponden al rango de dicha coordenada.
     *
     * @param coordenada que se le envia.
     * @return devuelvo lista que cumple el rango.
     */
    public List<Establecimiento> getListaEstablecimientosConCoordenada(Coordenada coordenada) {

        List<Establecimiento> listaEstablecimientos = CacheSingleton.getInstance().obtenerLista(Establecimiento.class);
        List<Establecimiento> establecimientosAprobados = new ArrayList<Establecimiento>();

        Double distanciaMaxima = 10.5;

        for (int i = 0; i < listaEstablecimientos.size(); i++) {
            //Obtengo la distancia entre las coordenadas del Establecimiento
            Double distanciaEstablecimientos = listaEstablecimientos.get(i).getCoordenada().getDistancia(coordenada);

            if (distanciaEstablecimientos != 0.0 && distanciaEstablecimientos <= distanciaMaxima) {
                establecimientosAprobados.add(listaEstablecimientos.get(i));
            }
        }
        return establecimientosAprobados;
    }

    /**
     * Método que se encargar de devolver una lista de establecimientos.
     *
     * @return Devuelve una lista de establecimientos.
     */
    public List<Establecimiento> getListaEstablecimientos() {
        List<Establecimiento> Establecimiento = CacheSingleton.getInstance().obtenerLista(Establecimiento.class);
        return Establecimiento;
    }

}
