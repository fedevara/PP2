package project.pack.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.pack.domain.Incidente;
import project.pack.domain.interfaz.IUbicable;
import project.pack.facade.Facade;
import project.pack.utilities.CategoriaProperties;
import project.pack.utilities.DateUtils;

/**
 * Created by sgarcete on 5/16/17.
 */

public class ContadorDeIncidentes {

    private IUbicable ubicable;
    private Double incidentesRiesgoAlto = 0.0;
    private Double incidentesRiesgoMedio = 0.0;
    private Double incidentesRiesgoBajo = 0.0;

    ContadorDeIncidentes(IUbicable ubicable) {
        this.ubicable = ubicable;
        categorizarLista(ubicable);
    }

    public Double getIncidentesRiesgoAlto() {
        return incidentesRiesgoAlto;
    }

    public Double getIncidentesRiesgoMedio() {
        return incidentesRiesgoMedio;
    }

    public Double getIncidentesRiesgoBajo() {
        return incidentesRiesgoBajo;
    }

    public Double getCantidadIncidentes(){
        return incidentesRiesgoAlto + incidentesRiesgoBajo + incidentesRiesgoMedio;
    }

    public void categorizarLista(IUbicable ubicable) {

        ArrayList<Incidente> incidentesCercanos = (ArrayList<Incidente>) Facade.getInstance().getListaIncidentesCercanos(ubicable.getCoordenada());

        for (int i = 0; i < incidentesCercanos.size(); i++) {
            Incidente incidenteActual = incidentesCercanos.get(i);

            if (DateUtils.getInstance().getDiferenciaPorDiaFechaActual(incidenteActual.getFecha()) <= CategoriaProperties.DiasMaximoDifIncidente) {
                categorizar(incidenteActual);
            }
        }
    }

    public void categorizar(Incidente incidente) {

        String entrada = incidente.getCategoria().getNombre();

        ArrayList<String> CATEGORIAS_BAJAS = CategoriaProperties.CATEGORIAS_BAJAS;
        ArrayList<String> CATEGORIAS_MEDIAS = CategoriaProperties.CATEGORIAS_MEDIAS;
        ArrayList<String> CATEGORIAS_ALTAS = CategoriaProperties.CATEGORIAS_ALTAS;

        if(CATEGORIAS_BAJAS.indexOf(entrada) != -1){
            incidentesRiesgoBajo++;
        }
        else if(CATEGORIAS_MEDIAS.indexOf(entrada) != -1){
            incidentesRiesgoMedio++;
        }
        else if(CATEGORIAS_ALTAS.indexOf(entrada) != -1){
            incidentesRiesgoAlto++;
        }

    }

}
