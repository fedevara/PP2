package project.pack.persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

import static org.junit.Assert.*;

/**
 * Created by lukas on 06/07/2017.
 */
public class PersistenciaEstablecimientoTest {
    private PersistenciaEstablecimiento pEstablecimiento;

    @Before
    public void setUp() throws Exception {
        // Instancio y borro la cache.
        pEstablecimiento = PersistenciaEstablecimiento.getInstance();
        pEstablecimiento.vaciarBD();
    }


    @Test
    public void getInstance() throws Exception {
        assertTrue(PersistenciaEstablecimiento.getInstance().equals(pEstablecimiento));
    }

    @Test
    public void addEstablecimiento() throws Exception {
        Establecimiento e1 = new Establecimiento(null, "nombre", null);
        // no tiene ID
        Assert.assertEquals(e1.getId(),0);
        // Ahora que lo guardo, tiene ID
        Establecimiento recuE1 = pEstablecimiento.addEstablecimiento(e1);
        // Entonces como es el primero objeto, tiene id 1
        Assert.assertEquals(recuE1.getId(),1);
        // Y el objeto enviado es el mismo que se devuelve
        Assert.assertEquals(e1,recuE1);
    }

    @Test
    public void getListaEstablecimiento() throws Exception {

        pEstablecimiento.vaciarBD();

        Establecimiento e1 = new Establecimiento(null, "nombre", null);
        Establecimiento e2 = new Establecimiento(null, "nombre", null);

        pEstablecimiento.addEstablecimiento(e1);
        pEstablecimiento.addEstablecimiento(e2);

        ArrayList<Establecimiento> lista = pEstablecimiento.getListaEstablecimiento();

        Assert.assertEquals(lista.size() , 2);
    }

}