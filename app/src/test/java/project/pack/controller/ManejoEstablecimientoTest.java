package project.pack.controller;

import android.content.Context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

import project.pack.R;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.facade.Facade;

/**
 * Created by Federico Vara on 14/4/2017.
 */
@RunWith(MockitoJUnitRunner.class)
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
    public void calcularRiesgo() throws Exception {
        Categoria categoria = new Categoria(1, "categoria1", "subcategoria1", null, "3");
        Categoria categoria2 = new Categoria(2, "categoria2", "subcategoria2", null, "5");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(1,"incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(4);

        Assert.assertEquals(establecimiento.getRiesgo(), "BAJO");

    }

    @Test
    public void crearEstacimiento() throws Exception {

    }

    @Test
    public void guardarEstablecimiento() throws Exception {

    }

    @Test
    public void getEstablecimiento() throws Exception {

    }

}