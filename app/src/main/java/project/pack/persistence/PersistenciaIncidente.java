package project.pack.persistence;

import java.util.ArrayList;

import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.persistence.DAO.IncidenteDAO;
import project.pack.persistence.DAOImpl.IncidenteDAOImpl;
import project.pack.utilities.ConnectionUtilities;

/**
 * Created by lukas on 14/05/2017.
 */

public class PersistenciaIncidente {

    private static PersistenciaIncidente INSTANCE;

    private PersistenciaIncidente() { }

    public static PersistenciaIncidente getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PersistenciaIncidente();
        return INSTANCE;
    }

    private IncidenteDAO persistenciaDAO = new IncidenteDAOImpl();

    public Incidente addIncidente(Incidente item){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if( ConnectionUtilities.estaConectado(Facade.getInstance().getContext())){
            // Guarda en la BD
            Incidente In_guardado = persistenciaDAO.add(item);
            // Sincronizarlo en la cache
            CacheSingleton.getInstance().put(item, item.getId());

            return In_guardado;
        }
        // Si hay o no hay conexion, no se puede agregar
        return null;
    }

    public ArrayList<Incidente> getListaIncidente(){

        ArrayList<Incidente> lista;

        // Primero intenta obtener los datos desde el IdentityMap (Cache)
        lista = CacheSingleton.getInstance().obtenerLista(Incidente.class);

        // Si no encuentra ahi (return null), entonces lo va a traer de la bd
        if(lista==null || lista.size()==0){
            lista = persistenciaDAO.getListItem();

            // Actualiza la cache
            for (int i = 0; i < lista.size(); i++) {
                CacheSingleton.getInstance().put(lista.get(i), lista.get(i).getId());
            }

        }

        return lista;
    }




    public void vaciarBD(){
        persistenciaDAO.vaciarBD();
    }

}
