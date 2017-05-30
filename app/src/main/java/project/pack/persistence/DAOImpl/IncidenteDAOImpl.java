package project.pack.persistence.DAOImpl;

import java.util.ArrayList;

import project.pack.domain.Incidente;
import project.pack.persistence.DAO.IncidenteDAO;

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
        return lista;
    }

    @Override
    public Incidente add(Incidente item) {
        lista.add(item);
        ID++;
        item.setId(ID);
        conex.Escribir(lista, File);
        return item;
    }

    @Override
    public boolean remove(Incidente item) {
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getId()==item.getId()) {
                lista.remove(i);
                conex.Escribir(lista, File);
                return true;
            }
        }
        return false;
    }

    @Override
    public void vaciarBD() {
        lista = new ArrayList<>();
        ID = 0;
        conex.Escribir(lista, File);
    }

}
