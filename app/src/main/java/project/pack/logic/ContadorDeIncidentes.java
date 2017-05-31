package project.pack.logic;

import java.util.ArrayList;

import project.pack.domain.Incidente;
import project.pack.domain.interfaz.IUbicable;
import project.pack.facade.Facade;
import project.pack.utilities.DateUtils;

/**
 * Created by sgarcete on 5/16/17.
 */

public class ContadorDeIncidentes {

    IUbicable ubicable;
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

    public void categorizarLista(IUbicable ubicable) {

        ArrayList<Incidente> incidentesCercanos = (ArrayList<Incidente>) Facade.getInstance().getListaIncidentesCercanos(ubicable.getCoordenada());

        for (int i = 0; i < incidentesCercanos.size(); i++) {
            Incidente incidenteActual = incidentesCercanos.get(i);

            if (DateUtils.getInstance().getDiferenciaPorDiaFechaActual(incidenteActual.getFecha()) <= 30) { //Acá los días se sacan del properties
                categorizar(incidenteActual);
            }
        }
    }

    public void categorizar(Incidente incidente) {

        Double riesgoCategoria = Double.valueOf(incidente.getCategoria().getRiesgo()); //Hardcodeado momentaneamente

        if (riesgoCategoria > 0 && riesgoCategoria <= 3) {
            this.incidentesRiesgoBajo += 1;
        } else if (riesgoCategoria > 3 && riesgoCategoria <= 7) {
            this.incidentesRiesgoMedio += 1;
        } else {
            this.incidentesRiesgoAlto += 1;
        }


    }

}
