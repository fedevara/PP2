package project.pack.persistence;

import java.util.ArrayList;

import project.pack.domain.Establecimiento;
import project.pack.facade.Facade;
import project.pack.persistence.DAO.EstablecimientoDAO;
import project.pack.persistence.DAOImpl.EstablecimientoDAOImpl;
import project.pack.utilities.ConnectionUtilities;

/**
 * Created by lukas on 14/05/2017.
 */

public class PersistenciaEstablecimiento {

    private static PersistenciaEstablecimiento INSTANCE;

    private PersistenciaEstablecimiento() { }

    public static PersistenciaEstablecimiento getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PersistenciaEstablecimiento();
        return INSTANCE;
    }

    private EstablecimientoDAO persistenciaDAO = new EstablecimientoDAOImpl();

    public Establecimiento addEstablecimiento(Establecimiento item){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if(ConnectionUtilities.estaConectado(Facade.getInstance().getContext())){
            // Guarda en la BD
            Establecimiento Est_guardado = persistenciaDAO.add(item);
            // Sincronizarlo en la cache
            CacheSingleton.getInstance().put(item, item.getId());

            return Est_guardado;
        }
        // Si hay o no hay conexion, no se puede agregar
        return null;
    }

    public ArrayList<Establecimiento> getListaEstablecimiento(){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if(ConnectionUtilities.estaConectado(Facade.getInstance().getContext())){
            return persistenciaDAO.getListItem();
        }
        // Si no hay conexion, tiene que interactuar con el cache
        else{
            return CacheSingleton.getInstance().obtenerLista(Establecimiento.class);
        }
    }

    public void vaciarBD(){
        persistenciaDAO.vaciarBD();
    }

}
