package project.pack.controller;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.persistence.CacheSingleton;

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
        // Escribir
        Incidente inGuardar = Facade.getInstance().crearIncidente("Robo", "Paso tal cosa", new Date(), null, new Coordenada(-30.0,-40.0));

        // obtengo el incidente guardado
        Incidente IncidenteGuardado = (Incidente) CacheSingleton.getInstance().get(inGuardar.getId(),Incidente.class);

        // compruebo que lo que se guardo sea lo mismo que mande a guardar
        assertTrue(inGuardar.equals(IncidenteGuardado));

    }

    @Test
    public void getIncidente() throws Exception {

        Incidente inGuardar = Facade.getInstance().crearIncidente("Robo1", "Paso tal cosa 1", new Date(), null, new Coordenada(-31.0,-40.0));

        Incidente incidenteObt = Facade.getInstance().obtenerIncidente(inGuardar.getId());

        assertTrue(inGuardar.equals(incidenteObt));
    }

    @Test
    public void getListaIncidentes() throws Exception {
        // Escribir
        Incidente inGuardar1 = Facade.getInstance().crearIncidente("Robo1", "Paso tal cosa 1", new Date(), null, new Coordenada(-31.0,-40.0));
        Incidente inGuardar2 = Facade.getInstance().crearIncidente("Robo2", "Paso tal cosa 2", new Date(), null, new Coordenada(-30.0,-40.0));

        // obtengo lista de incidentes guardados
        List<Incidente> lista = Facade.getInstance().obtenerListaIncidentes();

        Incidente IncObt1 = null;
        Incidente IncObt2 = null;

        // Busca en la lista que los incidentes creados estan
        for (int i = 0; i < lista.size(); i++) {
            int ID = lista.get(i).getId();
            if(ID == inGuardar1.getId())
                IncObt1 = lista.get(i);
            else if(ID == inGuardar2.getId())
                IncObt2 = lista.get(i);
        }
        // compruebo que lo que se guardo sea lo mismo que mande a guardar

        assertTrue(inGuardar1.equals(IncObt1));
        assertTrue(inGuardar2.equals(IncObt2));
    }

    @Test
    public void getListaIncidentesConCoordenada() throws Exception {

        Facade.getInstance().eliminarBDFacade();
        Categoria categoria = new Categoria(1,"Robo",null);

        Incidente in1 = Facade.getInstance().crearIncidente("Robo1", "Paso tal cosa ", new Date(), categoria, new Coordenada(10.1, 10.1));
        Incidente in2 = Facade.getInstance().crearIncidente("Robo2", "Paso tal cosa ", new Date(), categoria, new Coordenada(10.2, 10.2));
        Incidente in3 = Facade.getInstance().crearIncidente("Robo4", "Paso tal cosa ", new Date(), categoria, new Coordenada(9.9, 9.9));
        Incidente in4 = Facade.getInstance().crearIncidente("Robo5", "Paso tal cosa ", new Date(), categoria, new Coordenada(80.5, 104.3));
        Incidente in5 = Facade.getInstance().crearIncidente("Robo6", "Paso tal cosa ", new Date(), categoria, new Coordenada(210.89, 170.5));
        Incidente in6 = Facade.getInstance().crearIncidente("Robo7", "Paso tal cosa ", new Date(), categoria, new Coordenada(10.0, 10.0));

        Incidente in = Facade.getInstance().obtenerIncidente(in6.getId());
        List<Incidente> lista = Facade.getInstance().getListaIncidentesCercanos(in.getCoordenada());
        int incidentes = lista.size();

        Assert.assertEquals(4, incidentes);
    }

}