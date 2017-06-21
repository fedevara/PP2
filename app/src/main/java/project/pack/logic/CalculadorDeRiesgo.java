package project.pack.logic;

import project.pack.domain.interfaz.IUbicable;

/**
 * Created by sgarcete on 5/16/17.
 */

public class CalculadorDeRiesgo {
    private Double porcentajeDeRiesgo = 0.0;

    public CalculadorDeRiesgo(IUbicable ubicacion){
        calcularRiesgo(ubicacion);
    }

    public String getRiesgo() {

        if (porcentajeDeRiesgo <= 0.25) {
            return "MUY BAJO";
        } else if (porcentajeDeRiesgo > 0.25 && porcentajeDeRiesgo <= 0.60) {
            return "BAJO";
        } else if (porcentajeDeRiesgo > 0.60 && porcentajeDeRiesgo <= 1.00) {
            return "MEDIO";
        } else if (porcentajeDeRiesgo > 1.00 && porcentajeDeRiesgo <= 2.00) {
            return "ALTO";
        } else if (porcentajeDeRiesgo > 2.00) {
            return "MUY ALTO";
        } else {
            return "ERROR";
        }
    }

    private void calcularRiesgo(IUbicable ubicacion) {

        ContadorDeIncidentes contador = new ContadorDeIncidentes(ubicacion);
        this.porcentajeDeRiesgo = ((contador.getIncidentesRiesgoAlto()) * 100 + (contador.getIncidentesRiesgoMedio()) * 10 + (contador.getIncidentesRiesgoBajo()) * 5 + (contador.getCantidadIncidentes()) * 1) / 100;
    }






}