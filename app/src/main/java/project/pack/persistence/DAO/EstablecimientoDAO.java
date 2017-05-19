package project.pack.persistence.DAO;

import java.util.ArrayList;

import project.pack.domain.Establecimiento;

/**
 * Created by lukas on 14/05/2017.
 */
    public interface EstablecimientoDAO {

        public Establecimiento getItem(Integer id);

        public ArrayList<Establecimiento> getListItem();

        public boolean add(Establecimiento item);

        public boolean remove(Establecimiento id);

        public boolean update(Establecimiento item);

    }