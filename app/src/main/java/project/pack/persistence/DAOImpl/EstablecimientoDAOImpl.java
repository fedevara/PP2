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
    private ArrayList<Establecimiento> lista = new ArrayList<>();
    private Integer ID = 0;

    public EstablecimientoDAOImpl(){
       //init();
    }

    private void init(){
        Establecimiento e1 = new Establecimiento(1, null, "Establecimiento BD 1", null);
        Establecimiento e2 = new Establecimiento(2, null, "Establecimiento BD 2", null);
        Establecimiento e3 = new Establecimiento(3, null, "Establecimiento BD 3", null);
        Establecimiento e4 = new Establecimiento(4, null, "Establecimiento BD 4", null);
        Establecimiento e5 = new Establecimiento(5, null, "Establecimiento BD 5", null);
        Establecimiento e6 = new Establecimiento(6, null, "Establecimiento BD 6", null);
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        lista.add(e4);
        lista.add(e5);
        lista.add(e6);
        ID = 6;
    }


    @Override
    public ArrayList<Establecimiento> getListItem() {
        return lista;
    }

    @Override
    public Establecimiento add(Establecimiento item) {
        lista.add(item);
        ID++;
        item.setId(ID);
        return item;
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
    public void eliminarBD() {
        lista = new ArrayList<>();
    }

    @Override
    public Establecimiento getItem(Integer id) {
        return null;
    }


}
