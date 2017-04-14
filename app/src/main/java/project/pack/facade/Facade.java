package project.pack.facade;

import java.util.Date;

import project.pack.controller.ManejoIncidente;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Facade {

    private ManejoIncidente manejoIncidente;

    public Facade() {

        manejoIncidente = new ManejoIncidente();

    }

    public Incidente crearIncidente(String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada lugar){
        Incidente incidente = manejoIncidente.crearIncidente(titulo, descripcion, fecha, categoria, lugar);
        manejoIncidente.guardarIncidente(incidente);
        return incidente;
    }

    public Incidente obtenerIncidente(String id1) {
        return manejoIncidente.getIncidente(id1);
    }
}
