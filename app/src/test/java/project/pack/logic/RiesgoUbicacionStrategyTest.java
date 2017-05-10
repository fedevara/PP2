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
    }

    @After
    public void tearDown() throws Exception {
        Facade.getInstance().eliminarCache();
    }

    /**
     * El segundo incidente no influye en el riesgo por que está ubicado fuere del territorio establecido del establecimiento
     * @throws Exception
     */
    @Test
    public void calcularRiesgoAltoPorDistanciaTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria", "8");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria, new Coordenada(30.0, 30.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("ALTO", riesgo);
    }

    @Test
    public void calcularRiesgoAltoPorFechaTest() throws Exception{

        Date fechaCreacion = new Date("09/04/2017");
        Categoria categoria = new Categoria(1, "categoria", "8");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", fechaCreacion, categoria, new Coordenada(30.0, 30.0));
        Facade.getInstance().crearIncidente(2,"incidente3", "incidente3", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("ALTO", riesgo);
    }

    @Test
    public void calcularRiesgoAltoPorCantidadTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria", "8");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria, new Coordenada(30.0, 30.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("ALTO", riesgo);
    }

    @Test
    public void calcularRiesgoBajoTest() throws Exception {
        Categoria categoria = new Categoria(1, "categoria1", "3");
        Categoria categoria2 = new Categoria(2, "categoria2", "5");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.1, 10.1));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.2, 10.2));
        Facade.getInstance().crearIncidente(1,"incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.3, 10.3));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("BAJO", riesgo);
    }

    @Test
    public void calcularRiesgoMuyBajoTest() throws Exception{
        Categoria categoria = new Categoria(1, "categoria1", "3");
        Categoria categoria2 = new Categoria(2, "categoria2", "5");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MUY BAJO", riesgo);
    }

    @Test
    public void calcularRiesgoMedioTest() throws Exception{
        Categoria categoria2 = new Categoria(2, "categoria2", "5");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(2,"incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(3,"incidente4", "incidente4", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(4,"incidente5", "incidente5", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(5,"incidente6", "incidente6", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(6,"incidente7", "incidente7", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MEDIO", riesgo);
    }

    @Test
    public void calcularRiesgoAltoTest() throws Exception{
        Categoria categoria = new Categoria(1, "categoria", "8");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("ALTO", riesgo);
    }

    @Test
    public void calcularRiesgoMuyAltoTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria", "8");

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));

        Punto punto = new Punto(new Coordenada(10.3,10.3));
        String riesgo = punto.calcularRiesgo();

        Assert.assertEquals("MUY ALTO", riesgo);
    }
}