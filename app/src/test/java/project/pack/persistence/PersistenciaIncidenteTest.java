package project.pack.persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;

import static org.junit.Assert.*;

/**
 * Created by lukas on 29/06/2017.
 */
public class PersistenciaIncidenteTest {

    private PersistenciaIncidente pIncidente;

    @Before
    public void setUp() throws Exception {
        // Instancio y borro la cache.
        pIncidente = PersistenciaIncidente.getInstance();
        pIncidente.vaciarBD();
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(PersistenciaIncidente.getInstance().equals(pIncidente));
    }

    @Test
    public void addIncidente() throws Exception {
        Categoria categoria = new Categoria();
        Incidente in1 = new Incidente(null, "titulo", "descripcion", null, null, categoria);
        // no tiene ID
        Assert.assertEquals(in1.getId(),0);
        // Ahora que lo guardo, tiene ID
        Incidente recuIn1 = pIncidente.addIncidente(in1);
        // Entonces como es el primero objeto, tiene id 1
        Assert.assertEquals(recuIn1.getId(),1);
    }

    @Test
    public void getListaIncidente() throws Exception {

        Categoria cat = new Categoria();
        Incidente in1 = new Incidente(null, "titulo", "descripcion", null, null, cat);
        Incidente in2 = new Incidente(null, "titulo", "descripcion", null, null, cat);

        pIncidente.addIncidente(in1);
        pIncidente.addIncidente(in2);

        ArrayList<Incidente> lista = pIncidente.getListaIncidente();

        Assert.assertEquals(lista.size() , 2);
    }

}