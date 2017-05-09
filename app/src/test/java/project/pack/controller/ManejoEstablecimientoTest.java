package project.pack.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.facade.Facade;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/*
 * Created by Federico Vara on 14/4/2017.
 */
public class ManejoEstablecimientoTest {

    @Before
    public void setUp() throws Exception {
        Facade.getInstance().eliminarCache();
    }

    @After
    public void tearDown() throws Exception {
        Facade.getInstance().eliminarCache();
    }

    @Test
    public void getListaIncidentesConCoordenada() throws Exception {

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.1, 10.1));
        Facade.getInstance().crearEstablecimiento("establecimiento2", null, new Coordenada(10.2, 10.2));
        Facade.getInstance().crearEstablecimiento("establecimiento3", null, new Coordenada(9.9, 9.9));
        Facade.getInstance().crearEstablecimiento("establecimiento4", null, new Coordenada(80.5, 104.3));
        Facade.getInstance().crearEstablecimiento("establecimiento5", null, new Coordenada(210.89, 170.5));
        Facade.getInstance().crearEstablecimiento("establecimiento6", null, new Coordenada(10.0, 10.0));

        Establecimiento e = Facade.getInstance().obtenerEstablecimiento(6);
        int establecimientos = Facade.getInstance().getListaEstablecimientosCercanos(e.getCoordenada()).size();

        Assert.assertEquals(3, establecimientos);
    }

    @Test
    public void calcularRiesgo() throws Exception {
        Categoria categoria = new Categoria(1, "categoria1", "3",null);
        Categoria categoria2 = new Categoria(2, "categoria2", "5",null);

        Facade.getInstance().crearIncidente(0, "incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1, "incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(1, "incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3, 10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(4);

        Assert.assertEquals(establecimiento.getRiesgo(), "BAJO");
    }

    @Test
    public void crearEstablecimiento() throws Exception {
        Establecimiento establecimiento = new Establecimiento(null,"Establecimiento",null);

        establecimiento.setRiesgo("10");

        assertNotNull(establecimiento);
    }

    @Test
    public void guardarEstablecimiento() throws Exception {
        // Guardar
        Facade.getInstance().crearEstablecimiento("Establecimiento Pepe", null, null);
        // obtengo el incidente guardado
        Establecimiento e = Facade.getInstance().obtenerEstablecimiento(1);
        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        assertTrue(e.getNombre().equals("Establecimiento Pepe") && e.getCategoria() == null && e.getCoordenada() == null);
    }

    @Test
    public void getEstablecimiento() throws Exception {
        // Guardar
        Facade.getInstance().crearEstablecimiento("Establecimiento Pepe", null, null);
        // obtengo el incidente guardado
        Establecimiento e = Facade.getInstance().obtenerEstablecimiento(1);
        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        assertTrue(e.getNombre().equals("Establecimiento Pepe") && e.getCategoria() == null && e.getCoordenada() == null);
    }

}