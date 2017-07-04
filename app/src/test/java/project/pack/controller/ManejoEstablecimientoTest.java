package project.pack.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.domain.Punto;
import project.pack.facade.Facade;
import project.pack.logic.CalculadorDeRiesgo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/*
 * Created by Federico Vara on 14/4/2017.
 */
public class ManejoEstablecimientoTest {

    @Before
    public void setUp() throws Exception {
        Facade.getInstance().eliminarCache();
        Facade.getInstance().eliminarBDFacade();
    }

    @After
    public void tearDown() throws Exception {
        Facade.getInstance().eliminarCache();
        Facade.getInstance().eliminarBDFacade();
    }



    @Test
    public void calcularRiesgo() throws Exception {
        Categoria bajo = new Categoria(1, "Reclamo");
        Categoria medio = new Categoria(2, "Transito");


        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), bajo, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidente2", "incidente2", Calendar.getInstance().getTime(), medio, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente3", "incidente3", Calendar.getInstance().getTime(), medio, new Coordenada(10.5, 10.5));

        Establecimiento establecimiento = Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3, 10.3));

        // Calbular riesgo
        CalculadorDeRiesgo cdr = new CalculadorDeRiesgo(establecimiento);
        String riesgo = cdr.getRiesgo();

        Assert.assertEquals("BAJO", riesgo);
    }

    @Test
    public void crearEstablecimiento() throws Exception {
        Establecimiento establecimiento = Facade.getInstance().crearEstablecimiento("Establecimiento Pepe", null, new Coordenada(10.1, 10.1));
        assertNotNull(establecimiento);
    }

    @Test
    public void guardarEstablecimiento() throws Exception {
        //Escribir
        Establecimiento e1 = Facade.getInstance().crearEstablecimiento("Establecimiento Pepe", null, new Coordenada(10.1, 10.1));
        // obtengo el incidente guardado
        Establecimiento e2 = Facade.getInstance().obtenerEstablecimiento(e1.getId());
        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        assertTrue(e2.getId() == e1.getId());
    }

    @Test
    public void getEstablecimiento() throws Exception {

        // Escribir
        Establecimiento e1 = Facade.getInstance().crearEstablecimiento("Establecimiento Pepe", null, new Coordenada(10.1, 10.1));

        // obtengo el incidente guardado
        Establecimiento e2 = Facade.getInstance().obtenerEstablecimiento(e1.getId());

        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        assertTrue(e2.getId() == e1.getId() && e2.getCategoria() == null);
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
    public void getPuntoCoordenada() throws Exception {

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        Punto punto2 = new Punto(new Coordenada(10.3,10.3));

        Double distancia = punto.getDistancia(punto2);

        Assert.assertNotNull(distancia);
    }

    @Test
    public void testcoordenadasIncidentes() throws Exception {

        Categoria bajo = new Categoria(1, "Reclamo");
        Categoria medio = new Categoria(2, "Transito");

        Incidente incidente1 = new Incidente(new Coordenada(10.0, 10.0),"incidente1", "incidente1", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(), bajo);
        Incidente incidente2 = new Incidente(new Coordenada(10.0, 10.0),"incidente2", "incidente2", Calendar.getInstance().getTime(),Calendar.getInstance().getTime(), bajo);

        Double distancia = incidente1.getDistancia(incidente2);

        Assert.assertNotNull(distancia);
    }

}