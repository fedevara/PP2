package project.pack.logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Punto;
import project.pack.facade.Facade;

/*
 * Created by sgarcete on 4/20/17.
 */
public class RiesgoUbicacionStrategyTest {

    @Before
    public void setUp() throws Exception {
        Facade.getInstance().eliminarCache();
        Facade.getInstance().eliminarBDFacade();
    }

    @After
    public void tearDown() throws Exception {
        Facade.getInstance().eliminarCache();
    }


    /**
     * Calculo general para demostrar el valor del riesgo si los 3 incidentes son tomados en cuenta.
     * @throws Exception
     */
    @Test
    public void calcularRiesgoTest() throws Exception{

        //Agregamos 3 incidentes de niveles de riesgos diferentes en el contexto

        Categoria bajo = new Categoria(1, "categoria", "1", null);
        Categoria medio = new Categoria(2, "categoria", "6", null);
        Categoria alto = new Categoria(3, "categoria", "10", null);

        Facade.getInstance().crearIncidente("incidenteBajo", "incidenteBajo", Calendar.getInstance().getTime(), bajo, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidenteAlto", "incidenteAlto", Calendar.getInstance().getTime(), medio, new Coordenada(10.0, 10.0));

        Facade.getInstance().crearIncidente("incidenteMedio", "incidenteMedio", Calendar.getInstance().getTime(), alto, new Coordenada(10.0, 10.0));

        /*Ubicamos el punto en el mapa y calculamos el riesgo*/

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("ALTO", riesgo);
    }

    /**
     * El segundo incidente no influye en el riesgo por que está ubicado fuere del territorio establecido del establecimiento
     * @throws Exception
     */
    @Test
    public void calcularRiesgoAltoPorDistanciaTest() throws Exception{

        //Agregamos 3 incidentes de niveles de riesgo diferentes en el contexto.
        //El incidente de riesgo alto no va a ser tenido en cuenta por la distancia en la que se encuentra

        Categoria bajo = new Categoria(1, "categoria", "1", null);
        Categoria medio = new Categoria(2, "categoria", "6", null);
        Categoria alto = new Categoria(3, "categoria", "10", null);

        Facade.getInstance().crearIncidente("incidenteBajo", "incidenteBajo", Calendar.getInstance().getTime(), bajo, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidenteMedio", "incidenteMedio", Calendar.getInstance().getTime(), medio, new Coordenada(10.0, 10.0));

        Facade.getInstance().crearIncidente("incidenteAlto", "incidenteAlto", Calendar.getInstance().getTime(), alto, new Coordenada(30.0, 30.0));

        //Calculamos el riesgo

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        //Como el incidente de riesgo alto (el que más suma a la hora de calcular el riesgo) no influye en la fórmula por su distancia

        Assert.assertEquals("MUY BAJO", riesgo);
    }

    /**
     * El tercer incidente de riesgo alto no influye en el riesgo por que su fecha de creación supera los 30 días
     * @throws Exception
     */
    @Test
    public void calcularRiesgoAltoPorFechaTest() throws Exception{

        Date fechaCreacion = new Date("03/08/2017"); //fecha que supera los 30 días : 8 de Marzo de 2017

        Categoria bajo = new Categoria(1, "categoria", "1", null);
        Categoria medio = new Categoria(2, "categoria", "6", null);
        Categoria alto = new Categoria(3, "categoria", "10", null);

        Facade.getInstance().crearIncidente("incidenteBajo", "incidenteBajo", Calendar.getInstance().getTime(), bajo, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidenteMedio", "incidenteMedio", Calendar.getInstance().getTime(), medio, new Coordenada(10.0, 10.0));

        Facade.getInstance().crearIncidente("incidenteAlto", "incidenteAlto", fechaCreacion, alto, new Coordenada(10.0, 10.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MUY BAJO", riesgo);
    }

    /*Se tiene en cuenta la cantidad de riesgos:
    Muchos incidentes de rango bajo generan un riesgo medio*/

    @Test
    public void calcularRiesgoMedioPorCantidadTest() throws Exception{

        Categoria categoriaBaja = new Categoria(2, "categoriaBaja", "5", null);

        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidente2", "incidente2", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente3", "incidente3", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente4", "incidente4", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente5", "incidente5", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente6", "incidente6", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente7", "incidente7", Calendar.getInstance().getTime(), categoriaBaja, new Coordenada(10.5, 10.5));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MEDIO", riesgo);
    }

    /*Test de cada nivel de salida de riesgo*/

    @Test
    public void calcularRiesgoBajoTest() throws Exception {
        Categoria categoria = new Categoria(1, "categoria1", "3", null);
        Categoria categoria2 = new Categoria(2, "categoria2", "5", null);

        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.1, 10.1));
        Facade.getInstance().crearIncidente("incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.2, 10.2));
        Facade.getInstance().crearIncidente("incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.3, 10.3));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("BAJO", riesgo);
    }

    @Test
    public void calcularRiesgoMuyBajoTest() throws Exception{
        Categoria categoria = new Categoria(1, "categoria1", "3", null);
        Categoria categoria2 = new Categoria(2, "categoria2", "5", null);

        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MUY BAJO", riesgo);
    }

    @Test
    public void calcularRiesgoMedioTest() throws Exception{
        Categoria categoria2 = new Categoria(2, "categoria2", "5", null);

        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente4", "incidente4", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente5", "incidente5", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente6", "incidente6", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente("incidente7", "incidente7", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MEDIO", riesgo);
    }

    @Test
    public void calcularRiesgoAltoTest() throws Exception{
        Categoria categoria = new Categoria(1, "categoria", "8", null);

        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("ALTO", riesgo);
    }

    @Test
    public void calcularRiesgoMuyAltoTest() throws Exception{
        Categoria categoria = new Categoria(1, "categoria", "8", null);

        Facade.getInstance().crearIncidente("incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente("incidente2", "incidente2", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MUY ALTO", riesgo);
    }
}
