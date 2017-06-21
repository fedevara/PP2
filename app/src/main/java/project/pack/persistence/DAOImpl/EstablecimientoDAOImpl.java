package project.pack.persistence.DAOImpl;

import java.util.ArrayList;

import project.pack.domain.Establecimiento;
import project.pack.persistence.DAO.EstablecimientoDAO;

/**
 * Created by lukas on 14/05/2017.
 */

public class EstablecimientoDAOImpl implements EstablecimientoDAO {

    private ConectionFile conex = new ConectionFile();
    private ArrayList<Establecimiento> lista;
    private Integer ID = 0;
    private String File = "Establecimientos";

    public EstablecimientoDAOImpl(){
        lista = (ArrayList<Establecimiento>) conex.Leer(File);
        if(lista==null)
            lista = new ArrayList<>();
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
        conex.Escribir(lista, File);
        return item;
    }

    @Override
    public void vaciarBD() {
        lista = new ArrayList<>();
        ID = 0;
        conex.Escribir(lista, File);
    }

}
