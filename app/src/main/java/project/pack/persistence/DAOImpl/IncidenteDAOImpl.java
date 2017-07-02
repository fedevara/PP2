package project.pack.persistence.DAOImpl;

import java.util.ArrayList;

import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.persistence.DAO.IncidenteDAO;
import project.pack.utilities.ConnectionUtilities;

/**
 * Created by lukas on 14/05/2017.
 */

public class IncidenteDAOImpl implements IncidenteDAO {

    private ConectionFile conex = new ConectionFile();
    private ArrayList<Incidente> lista;
    private Integer ID = 0;
    private String File = "Incidentes";

    public IncidenteDAOImpl(){
        lista = (ArrayList<Incidente>) conex.Leer(File);
        if(lista==null)
            lista = new ArrayList<>();
    }

    @Override
    public ArrayList<Incidente> getListItem() {

        if( ConnectionUtilities.estaConectado(Facade.getInstance().getContext())) {
            return lista;
        }
        return null;
    }

    @Override
    public Incidente add(Incidente item) {

        if( ConnectionUtilities.estaConectado(Facade.getInstance().getContext())) {

            lista.add(item);
            ID++;
            item.setId(ID);
            conex.Escribir(lista, File);
            return item;
        }
        return null;
    }

    @Override
    public void vaciarBD() {
        lista = new ArrayList<>();
        ID = 0;
        conex.Escribir(lista, File);
    }

}
