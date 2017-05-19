package project.pack.persistence.DAOImpl;

import java.util.ArrayList;

import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.persistence.DAO.IncidenteDAO;

/**
 * Created by lukas on 14/05/2017.
 */

public class IncidenteDAOImpl implements IncidenteDAO {
    private ArrayList<Incidente> lista = new ArrayList<>();
    private Integer ID = 0;

    public IncidenteDAOImpl(){
        init();
    }

    private void init(){
        Incidente i1 = new Incidente(new Coordenada(210.89, 170.5), 1, "Incidente BD 1", "descripcion", null, null, null);
        Incidente i2 = new Incidente(new Coordenada(210.89, 170.5), 2, "Incidente BD 2", "descripcion", null, null, null);
        Incidente i3 = new Incidente(new Coordenada(210.89, 170.5), 3, "Incidente BD 3", "descripcion", null, null, null);
        Incidente i4 = new Incidente(new Coordenada(210.89, 170.5), 4, "Incidente BD 4", "descripcion", null, null, null);
        Incidente i5 = new Incidente(new Coordenada(210.89, 170.5), 5, "Incidente BD 5", "descripcion", null, null, null);
        Incidente i6 = new Incidente(new Coordenada(210.89, 170.5), 6, "Incidente BD 6", "descripcion", null, null, null);
        lista.add(i1);
        lista.add(i2);
        lista.add(i3);
        lista.add(i4);
        lista.add(i5);
        lista.add(i6);
        ID = 6;
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
        return item;
    }

    @Override
    public boolean remove(Incidente id) {
        return false;
    }

    @Override
    public boolean update(Incidente item) {
        return false;
    }

    @Override
    public Incidente getItem(Integer id) {
        return null;
    }
}
