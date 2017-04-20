package project.pack.controller;

import java.util.ArrayList;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

/**
 * Created by Familia Vara on 9/4/2017.
 */

public class ManejoEstablecimiento {

    public ManejoEstablecimiento() {

    }

    public Establecimiento crearEstacimiento(String nombre, Categoria categoria, Coordenada lugar) {
        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre(nombre);
        establecimiento.setCategoria(categoria);
        establecimiento.setCoordenada(lugar);

        return establecimiento;
    }

    public void guardarEstablecimiento(Establecimiento establecimiento) {
        CacheSingleton.getInstance().put(establecimiento);
    }

    public Establecimiento getEstablecimiento(int id1) {
        return (Establecimiento) CacheSingleton.getInstance().get(id1);
    }

    /**
     * Dada una coordenada, devuelvo los establecimientos que corresponden al rango de dicha coordenada.
     *
     * @param coordenada que se le envia.
     * @return devuelvo lista que cumple el rango.
     */
    public List<Establecimiento> getListaIncidentesConCoordenada(Coordenada coordenada) {

        List<Establecimiento> listaEstablecimientos = CacheSingleton.getInstance().obtenerListaEstablecimientos();
        List<Establecimiento> establecimientosAprobados = new ArrayList<Establecimiento>();

        Double distanciaMaxima = 10.5;

        for (int i = 0; i < listaEstablecimientos.size(); i++) {
            //Obtengo la distancia entre las coordenadas del Incidente
            Double distanciaIncidente = listaEstablecimientos.get(i).getCoordenada().getDistancia(coordenada);

            if (distanciaIncidente <= distanciaMaxima) {
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
        List<Establecimiento> Establecimiento = CacheSingleton.getInstance().obtenerListaEstablecimientos();
        return Establecimiento;
    }
}
