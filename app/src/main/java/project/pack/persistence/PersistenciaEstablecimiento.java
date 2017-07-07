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
    private EstablecimientoDAO persistenciaDAO = new EstablecimientoDAOImpl();

    private PersistenciaEstablecimiento() { }

    public static PersistenciaEstablecimiento getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PersistenciaEstablecimiento();
        return INSTANCE;
    }

    public Establecimiento addEstablecimiento(Establecimiento item){
        // Intenta guardar en la bd, si lo hace, encontces sincroniza la cache, sino devuelve null
        Establecimiento Est_guardado = persistenciaDAO.add(item);
        if (Est_guardado != null) {
            // Sincronizarlo en la cache
            CacheSingleton.getInstance().put(Est_guardado, Est_guardado.getId());
        }
        return Est_guardado;
    }

    public ArrayList<Establecimiento> getListaEstablecimiento(){

        ArrayList<Establecimiento> lista;

        // Primero intenta obtener los datos desde la caché (IdentityMap)
        lista = CacheSingleton.getInstance().obtenerLista(Establecimiento.class);

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

    public void vaciarBD(){
        persistenciaDAO.vaciarBD();
    }

}
