package project.pack.controller;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;

/**
 * Created by Familia Vara on 9/4/2017.
 */

public class ManejoEstablecimiento {

    public ManejoEstablecimiento(){

    }

    public Establecimiento crearEstacimiento(String nombre, Categoria categoria, Coordenada lugar) {
        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre(nombre);
        establecimiento.setCategoria(categoria);
        establecimiento.setCoordenada(lugar);

        return establecimiento;
    }

    public void guardarEstablecimiento(Establecimiento establecimiento){
        CacheSingleton.getInstance().put( "ID1", establecimiento);
    }

    public Establecimiento getEstablecimiento(String id1) {
        return (Establecimiento) CacheSingleton.getInstance().get("ID1");
    }
}
