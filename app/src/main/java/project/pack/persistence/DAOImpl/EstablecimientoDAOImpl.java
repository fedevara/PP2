package project.pack.persistence.DAOImpl;

import java.util.ArrayList;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.persistence.DAO.EstablecimientoDAO;

/**
 * Created by lukas on 14/05/2017.
 */

public class EstablecimientoDAOImpl implements EstablecimientoDAO {

    @Override
    public ArrayList<Establecimiento> getListItem() {
        ArrayList<Establecimiento> lista = new ArrayList<>();
        Establecimiento e1 = new Establecimiento(1, null, "Establecimiento 1", null);
        Establecimiento e2 = new Establecimiento(2, null, "Establecimiento 2", null);
        Establecimiento e3 = new Establecimiento(3, null, "Establecimiento 3", null);
        Establecimiento e4 = new Establecimiento(4, null, "Establecimiento 4", null);
        Establecimiento e5 = new Establecimiento(5, null, "Establecimiento 5", null);
        Establecimiento e6 = new Establecimiento(6, null, "Establecimiento 6", null);
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        lista.add(e4);
        lista.add(e5);
        lista.add(e6);
        return lista;
    }

    @Override
    public boolean add(Establecimiento item) {
        return false;
    }

    @Override
    public boolean remove(Establecimiento id) {
        return false;
    }

    @Override
    public boolean update(Establecimiento item) {
        return false;
    }

    @Override
    public Establecimiento getItem(Integer id) {
        return null;
    }
}
