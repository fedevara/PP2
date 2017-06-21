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
            MemoriaAlternativaSingleton.getInstance().put(item, item.getId());

            return Est_guardado;
        }
        // Si hay o no hay conexion, no se puede agregar
        return null;
    }

    public ArrayList<Establecimiento> getListaEstablecimiento(){

        ArrayList<Establecimiento> lista;

        // Si hay conexion, consulta la bd
        if(ConnectionUtilities.estaConectado(Facade.getInstance().getContext())){
            // Busca en la BD
            lista = persistenciaDAO.getListItem();

            // Si hay datos para sincronizar, se actualiza la cache.
            if(lista==null || lista.size()==0) {

                // Actualiza la cache
                for (int i = 0; i < lista.size(); i++) {
                    MemoriaAlternativaSingleton.getInstance().put(lista.get(i), lista.get(i).getId());
                }
            }
        }

        // Si no hay conexion, tiene que interactuar con el cache
        else{
            lista = MemoriaAlternativaSingleton.getInstance().obtenerLista(Establecimiento.class);
        }

        return lista;
    }

    public void vaciarBD(){
        persistenciaDAO.vaciarBD();
    }

}
