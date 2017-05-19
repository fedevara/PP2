package project.pack.persistence.DAOImpl;

import java.util.ArrayList;

import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.persistence.DAO.IncidenteDAO;

/**
 * Created by lukas on 14/05/2017.
 */

public class IncidenteDAOImpl implements IncidenteDAO {

    @Override
    public ArrayList<Incidente> getListItem() {
        ArrayList<Incidente> lista = new ArrayList<>();
        Incidente i1 = new Incidente(new Coordenada(), 1, "Persistencia 1", "descripcion", null, null, null);
        Incidente i2 = new Incidente(new Coordenada(), 2, "Persistencia 2", "descripcion", null, null, null);
        Incidente i3 = new Incidente(new Coordenada(), 3, "Persistencia 3", "descripcion", null, null, null);
        Incidente i4 = new Incidente(new Coordenada(), 4, "Persistencia 4", "descripcion", null, null, null);
        Incidente i5 = new Incidente(new Coordenada(), 5, "Persistencia 5", "descripcion", null, null, null);
        Incidente i6 = new Incidente(new Coordenada(), 6, "Persistencia 6", "descripcion", null, null, null);
        lista.add(i1);
        lista.add(i2);
        lista.add(i3);
        lista.add(i4);
        lista.add(i5);
        lista.add(i6);
        return lista;
    }

    @Override
    public boolean add(Incidente item) {
        return false;
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
