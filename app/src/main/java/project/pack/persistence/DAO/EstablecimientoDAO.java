package project.pack.persistence.DAO;

import java.util.ArrayList;

import project.pack.domain.Establecimiento;

/**
 * Created by lukas on 14/05/2017.
 */
    public interface EstablecimientoDAO {

        ArrayList<Establecimiento> getListItem();

        Establecimiento add(Establecimiento item);

        void vaciarBD();

    }