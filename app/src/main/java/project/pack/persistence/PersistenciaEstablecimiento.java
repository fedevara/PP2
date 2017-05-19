package project.pack.persistence;

import java.util.ArrayList;

import project.pack.controller.CacheSingleton;
import project.pack.domain.Establecimiento;
import project.pack.facade.Facade;
import project.pack.persistence.DAO.EstablecimientoDAO;
import project.pack.persistence.DAOImpl.ConectionFile;
import project.pack.persistence.DAOImpl.EstablecimientoDAOImpl;
import project.pack.utilities.ConnectionUtilities;

/**
 * Created by lukas on 14/05/2017.
 */

public class PersistenciaEstablecimiento {
    private EstablecimientoDAO persistenciaDAO = new EstablecimientoDAOImpl();
    //private CacheSingleton cache = CacheSingleton.getInstance();

    public boolean addEstablecimiento(Establecimiento item){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if(hayConexion()){
            Boolean guardado = persistenciaDAO.add(item);
            if(guardado){
                CacheSingleton.getInstance().put(item, item.getId());
            }
            return guardado;
        }
        // Si hay o no hay conexion, no se puede agregar
        return false;
    }

    public ArrayList<Establecimiento> getListaEstablecimiento(){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if(hayConexion()){
            return persistenciaDAO.getListItem();
        }
        // Si no hay conexion, tiene que interactuar con el cache
        else{
           return CacheSingleton.getInstance().obtenerLista(Establecimiento.class);
        }
    }

    private boolean hayConexion(){
        return ConnectionUtilities.estaConectado(Facade.getInstance().getContext());
    }

}
