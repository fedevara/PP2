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
    private IncidenteDAO persistenciaDAO = new IncidenteDAOImpl();

    private PersistenciaIncidente() {
    }

    public static PersistenciaIncidente getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PersistenciaIncidente();
        return INSTANCE;
    }

    public Incidente addIncidente(Incidente item) {
        // Intenta guardar en la bd, si lo hace, encontces sincroniza la cache, sino devuelve null
        Incidente In_guardado = persistenciaDAO.add(item);
        if (In_guardado != null) {
            // Sincronizarlo en la cache
            CacheSingleton.getInstance().put(In_guardado, In_guardado.getId());
        }
        return In_guardado;
    }

    public ArrayList<Incidente> getListaIncidente() {

        ArrayList<Incidente> lista;

        // Primero intenta obtener los datos desde el IdentityMap (Cache)
        lista = CacheSingleton.getInstance().obtenerLista(Incidente.class);

        // Si no encuentra ahi (return null), entonces lo va a traer de la bd
        if (lista == null || lista.size() == 0) {
            lista = persistenciaDAO.getListItem();

            if (lista != null) {
                // Actualiza la cache
                for (int i = 0; i < lista.size(); i++) {
                    CacheSingleton.getInstance().put(lista.get(i), lista.get(i).getId());
                }
            }

        }
        return lista;
    }

    public void vaciarBD() {
        persistenciaDAO.vaciarBD();
    }

}
