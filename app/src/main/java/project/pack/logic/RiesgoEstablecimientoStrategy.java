package project.pack.logic;

import java.util.ArrayList;

import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.utilities.DateUtils;

/**
 * Created by sgarcete on 4/16/17.
 */

public class RiesgoEstablecimientoStrategy implements IRiesgoStrategy {

    Double incidentesRiesgoAlto = 0.0;
    Double incidentesRiesgoMedio = 0.0;
    Double incidentesRiesgoBajo = 0.0;
    Double establecimientosRiesgoAlto = 0.0;
    Double establecimientosRiesgoMedio = 0.0;
    Coordenada coordenada;

    @Override
    public String getRiesgo(Object object) {

        Double riesgo = calcularRiesgo(object);

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
    public Double calcularRiesgo(Object object) {

        Establecimiento establecimiento = (Establecimiento) object;

        //categorizarEstablecimientos();

        categorizarIncidentes(establecimiento.getCoordenada());

        Double cantidadRiesgos = this.incidentesRiesgoAlto + this.incidentesRiesgoMedio + this.incidentesRiesgoBajo;

        Double porcentajeDeRiesgo = ((this.incidentesRiesgoAlto) * 100 + (this.incidentesRiesgoMedio) * 10 + (this.incidentesRiesgoBajo) * 5 + (cantidadRiesgos) * 1 + (this.establecimientosRiesgoAlto) * 10 + (this.establecimientosRiesgoMedio) * 5) / 100;

        System.out.println(porcentajeDeRiesgo);

        return porcentajeDeRiesgo;

    }

    public void categorizarIncidentes(Coordenada coordenada) {

        ArrayList<Incidente> incidentesCercanos = (ArrayList<Incidente>) Facade.getInstance().getListaIncidentesCercanos(coordenada);

        for (int i = 0; i < incidentesCercanos.size(); i++) {
            Incidente incidenteActual = incidentesCercanos.get(i);

            if (DateUtils.getInstance().getDiferenciaPorDiaFechaActual(incidenteActual.getFechaCreacion()) <= 30) { //Acá los días se sacan del properties
                categorizarIncidente(incidenteActual);
            }
        }
    }

    public void categorizarEstablecimientos() {

        ArrayList<Establecimiento> establecimientos = (ArrayList<Establecimiento>) Facade.getInstance().obtenerListaEstablecimientos(); //Deberian ser establecimientos

        for (int i = 0; i < establecimientos.size(); i++) {

            Establecimiento establecimientoActual = establecimientos.get(i); //TODO tienen que ser establecimientos

            categorizarEstablecimiento(establecimientoActual);
        }
    }


    public void categorizarIncidente(Incidente incidente) {

        Double riesgoCategoria = Double.valueOf(incidente.getCategoria().getRiesgo()); //Hardcodeado momentaneamente

        if (riesgoCategoria > 0 && riesgoCategoria <= 3) {
            this.incidentesRiesgoBajo += 1;
        } else if (riesgoCategoria > 3 && riesgoCategoria <= 7) {
            this.incidentesRiesgoMedio += 1;
        } else {
            this.incidentesRiesgoAlto += 1;
        }

    }

    public void categorizarEstablecimiento(Establecimiento establecimiento) {

        String riesgoEstablecimiento = establecimiento.getCategoria().getRiesgo();

        switch (riesgoEstablecimiento) {
            case "MUY ALTA":
                this.establecimientosRiesgoAlto += 1;
                break;
            case "ALTA":
                this.establecimientosRiesgoMedio += 1;
                break;
            default:
                break;

        }

    }


}
