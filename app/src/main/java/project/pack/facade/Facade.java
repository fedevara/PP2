package project.pack.facade;

import java.util.Date;

import project.pack.controller.ManejoEstablecimiento;
import project.pack.controller.ManejoIncidente;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Facade {

    private ManejoIncidente manejoIncidente;
    private ManejoEstablecimiento manejoEstablecimiento;

    public Facade() {
        manejoIncidente = new ManejoIncidente();
        manejoEstablecimiento = new ManejoEstablecimiento();
    }

    public Incidente crearIncidente(String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada lugar){
        Incidente incidente = manejoIncidente.crearIncidente(titulo, descripcion, fecha, categoria, lugar);
        manejoIncidente.guardarIncidente(incidente);
        return incidente;
    }

    public Incidente obtenerIncidente(String id1) {
        return manejoIncidente.getIncidente(id1);
    }

    public void crearEstablecimiento(String nombre, Categoria categoria, Coordenada lugar) {
        Establecimiento establecimiento = manejoEstablecimiento.crearEstacimiento(nombre, categoria, lugar);
        manejoEstablecimiento.guardarEstablecimiento(establecimiento);
    }
}
