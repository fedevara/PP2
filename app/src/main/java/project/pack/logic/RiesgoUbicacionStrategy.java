package project.pack.logic;

import java.util.ArrayList;

import project.pack.domain.interfaz.IUbicable;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.utilities.DateUtils;

/*
 * Created by sgarcete on 4/16/17.
 */

public class RiesgoUbicacionStrategy implements IRiesgoStrategy {

    private Double incidentesRiesgoAlto = 0.0;
    private Double incidentesRiesgoMedio = 0.0;
    private Double incidentesRiesgoBajo = 0.0;

    @Override
    public String getRiesgo(IUbicable ubicacion) {

        Double riesgo = calcularRiesgo(ubicacion);

        if (riesgo <= 0.25) {
            return "MUY BAJO";
        } else if (riesgo > 0.25 && riesgo <= 0.60) {
            return "BAJO";
        } else if (riesgo > 0.60 && riesgo <= 1.00) {
            return "MEDIO";
        } else if (riesgo > 1.00 && riesgo <= 2.00) {
            return "ALTO";
        } else if (riesgo > 2.00) {
            return "MUY ALTO";
        } else {
            return "ERROR";
        }
    }

    @Override
    public Double calcularRiesgo(IUbicable ubicacion) {


        categorizarLista(ubicacion.getCoordenada());

        Double cantidadRiesgos = this.incidentesRiesgoAlto + this.incidentesRiesgoMedio + this.incidentesRiesgoBajo;

        Double porcentajeDeRiesgo = ((this.incidentesRiesgoAlto) * 100 + (this.incidentesRiesgoMedio) * 10 + (this.incidentesRiesgoBajo) * 5 + (cantidadRiesgos) * 1) / 100;

        System.out.println(porcentajeDeRiesgo);

        return porcentajeDeRiesgo;

    }

    public void categorizarLista(Coordenada coordenada) {

        ArrayList<Incidente> incidentesCercanos = (ArrayList<Incidente>) Facade.getInstance().getListaIncidentesCercanos(coordenada);

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
