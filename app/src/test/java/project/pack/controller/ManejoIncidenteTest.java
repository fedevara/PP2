package project.pack.controller;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;

import static org.junit.Assert.*;

public class ManejoIncidenteTest {


    @Before
    public void setUp() throws Exception {
        // borra la cache.
        CacheSingleton.getInstance().limpiarCache();
    }

    @After
    public void tearDown() throws Exception {
        // borra la cache.
        CacheSingleton.getInstance().limpiarCache();
    }

    @Test
    public void crearIncidente() throws Exception {

        Incidente incidente = new Incidente(null,1, "robo de auto", "bla blab la", new Date(), null,null);

        incidente.setId(1);
        incidente.setTitulo("Robo de auto");
        incidente.setFecha(new Date());
        incidente.setDescripcion("Me robaron el auto en Vergara 2500 a las 20 hs.");

        assertNotNull(incidente);
    }

    @Test
    public void guardarIncidente() throws Exception {
        // Guardar
        Incidente inGuardar = Facade.getInstance().crearIncidente(1 , "Robo", "Paso tal cosa", new Date(), null, new Coordenada(-30.0,-40.0));
        // obtengo el incidente guardado
        Incidente IncidenteGuardado = (Incidente) CacheSingleton.getInstance().get(1,Incidente.class);
        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        assertTrue(inGuardar.equals(IncidenteGuardado));

    }

    @Test
    public void getIncidente() throws Exception {
        //guardarIncidente();
        Incidente inGuardar = Facade.getInstance().crearIncidente(1 , "Robo1", "Paso tal cosa 1", new Date(), null, new Coordenada(-31.0,-40.0));
        Incidente incidenteObt = Facade.getInstance().obtenerIncidente(1);
        assertTrue(inGuardar.equals(incidenteObt));
    }

    @Test
    public void getListaIncidentes() throws Exception {
        // Guardar
        Incidente inGuardar1 = Facade.getInstance().crearIncidente(1 , "Robo1", "Paso tal cosa 1", new Date(), null, new Coordenada(-31.0,-40.0));
        Incidente inGuardar2 = Facade.getInstance().crearIncidente(2 , "Robo2", "Paso tal cosa 2", new Date(), null, new Coordenada(-30.0,-40.0));

        // obtengo lista de incidentes guardados
        List<Incidente> lista = Facade.getInstance().obtenerListaIncidentes();

        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        Incidente IncObt1 = lista.get(0);
        Incidente IncObt2 = lista.get(1);
        assertTrue(inGuardar1.equals(IncObt1));
        assertTrue(inGuardar2.equals(IncObt2));
    }

    @Test
    public void getListaIncidentesConCoordenada() throws Exception {
        Facade.getInstance().crearIncidente(1 , "Robo1", "Paso tal cosa ", new Date(), null, new Coordenada(10.1, 10.1));
        Facade.getInstance().crearIncidente(2 , "Robo2", "Paso tal cosa ", new Date(), null, new Coordenada(10.2, 10.2));
        Facade.getInstance().crearIncidente(3 , "Robo4", "Paso tal cosa ", new Date(), null, new Coordenada(9.9, 9.9));
        Facade.getInstance().crearIncidente(4 , "Robo5", "Paso tal cosa ", new Date(), null, new Coordenada(80.5, 104.3));
        Facade.getInstance().crearIncidente(5 , "Robo6", "Paso tal cosa ", new Date(), null, new Coordenada(210.89, 170.5));
        Facade.getInstance().crearIncidente(6 , "Robo7", "Paso tal cosa ", new Date(), null, new Coordenada(10.0, 10.0));

        Incidente in = Facade.getInstance().obtenerIncidente(6);
        int incidentes = Facade.getInstance().getListaIncidentesCercanos(in.getCoordenada()).size();

        Assert.assertEquals(4, incidentes);
    }

}