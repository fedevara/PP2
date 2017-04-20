package project.pack.logic;

import java.util.ArrayList;

import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.utilities.DateUtils;

/**
 * Created by sgarcete on 4/16/17.
 */

public class RiesgoEstablecimientoStrategy implements IRiesgoStrategy {

    Double incidentesRiesgoAlto;
    Double incidentesRiesgoMedio;
    Double incidentesRiesgoBajo;
    Double establecimientsRiesgoAlto;
    Double establecimientosRiesgoMedio;

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
            return "";
        }


    }

    @Override
    public Double calcularRiesgo(Object object) {

        Double cantidadRiesgos = this.incidentesRiesgoAlto + this.incidentesRiesgoMedio + this.incidentesRiesgoBajo;

        Double porcentajeDeRiesgo = ((this.incidentesRiesgoAlto) * 100 + (this.incidentesRiesgoMedio) * 10 + (this.incidentesRiesgoBajo) * 5 + (cantidadRiesgos) * 1 + (this.establecimientsRiesgoAlto) * 10 + (this.establecimientosRiesgoMedio) * 5) / 100;

        return porcentajeDeRiesgo;

    }

    public void categorizarIncidentes() {

        ArrayList<Incidente> incidentes = (ArrayList<Incidente>) Facade.getInstance().obtenerListaIncidentes();

        for (int i = 0; i < incidentes.size(); i++) {
            Incidente incidenteActual = incidentes.get(i);

            if (DateUtils.getInstance().getDiferenciaPorDiaFechaActual(incidenteActual.getFechaCreacion()) <= 30) { //Acá los días se sacan del properties
                categorizarIncidente(incidenteActual);
            }
        }

    }

    public void categorizarEstablecimientos() {

        ArrayList<Incidente> establecimientos = (ArrayList<Incidente>) Facade.getInstance().obtenerListaIncidentes(); //Deberian ser establecimientos

        for (int i = 0; i < establecimientos.size(); i++) {

            Incidente establecimientoActual = establecimientos.get(i); //TODO tienen que ser establecimientos

            categorizarIncidente(establecimientoActual);

        }

    }


    public void categorizarIncidente(Incidente incidente) {

        String riesgoCategoria = "ALTA"; //Hardcodeado momentaneamente
        //String riesgoCategoria = incidente.getCategoria().getRiesgo() //Lo implementa Fede

        switch (riesgoCategoria) {
            case "ALTA":
                this.incidentesRiesgoAlto += 1;
                break;
            case "MEDIA":
                this.incidentesRiesgoMedio += 1;
                break;
            case "BAJA":
                this.incidentesRiesgoBajo += 1;
                break;
            default:
                break;
        }
    }

    public void categorizarEstablecimiento(Establecimiento establecimiento) {

        String riesgoEstablecimiento = "ALTA"; //Hardcodeado momentaneamente

        //String riesgoCategoria = incidente.getCategoria().getRiesgo() //Lo implementa Fede

        switch (riesgoEstablecimiento) {
            case "MUY ALTA":
                this.establecimientsRiesgoAlto += 1;
                break;
            case "ALTA":
                this.establecimientosRiesgoMedio += 1;
                break;
            default:
                break;

        }

    }


}
