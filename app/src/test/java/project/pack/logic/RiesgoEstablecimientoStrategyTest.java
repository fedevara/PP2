package project.pack.logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.facade.Facade;

/*
 * Created by sgarcete on 4/20/17.
 */
public class RiesgoEstablecimientoStrategyTest {
    @Before
    public void setUp() throws Exception {
        Facade.getInstance().eliminarCache();
    }

    @After
    public void tearDown() throws Exception {
        Facade.getInstance().eliminarCache();
    }

    @Test
    public void calcularRiesgoBajoTest() throws Exception {

        Categoria categoria = new Categoria(1, "categoria1", "3",null);
        Categoria categoria2 = new Categoria(2, "categoria2", "5",null);

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.1, 10.1));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.2, 10.2));
        Facade.getInstance().crearIncidente(1,"incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.3, 10.3));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(4);

        String riesgo = establecimiento.getRiesgo();

        Assert.assertEquals(riesgo, "BAJO");

    }

    @Test
    public void calcularRiesgoMuyBajoTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria1", "3",null);
        Categoria categoria2 = new Categoria(2, "categoria2", "5",null);

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(3);

        String riesgo = establecimiento.getRiesgo();

        Assert.assertEquals(riesgo, "MUY BAJO");

    }

    @Test
    public void calcularRiesgoMedioTest() throws Exception{

        Categoria categoria2 = new Categoria(2, "categoria2", "5",null);

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(2,"incidente3", "incidente3", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(3,"incidente4", "incidente4", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(4,"incidente5", "incidente5", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(5,"incidente6", "incidente6", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));
        Facade.getInstance().crearIncidente(6,"incidente7", "incidente7", Calendar.getInstance().getTime(), categoria2, new Coordenada(10.5, 10.5));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(8);

        String riesgo = establecimiento.getRiesgo();

        Assert.assertEquals(riesgo, "MEDIO");

    }

    @Test
    public void calcularRiesgoAltoTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria", "8",null);

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(2);

        String riesgo = establecimiento.getRiesgo();

        Assert.assertEquals(riesgo, "ALTO");

    }

    @Test
    public void calcularRiesgoMuyAltoTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria", "8",null);

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(3);

        String riesgo = establecimiento.getRiesgo();

        Assert.assertEquals(riesgo, "MUY ALTO");

    }

    /**
     * El segundo incidente no influye en el riesgo por que está ubicado fuere del territorio establecido del establecimiento
     * @throws Exception
     */

    @Test
    public void calcularRiesgoAltoPorDistanciaTest() throws Exception{

        Categoria categoria = new Categoria(1, "categoria", "8",null);

        Facade.getInstance().crearIncidente(0,"incidente1", "incidente1", Calendar.getInstance().getTime(), categoria, new Coordenada(10.0, 10.0));
        Facade.getInstance().crearIncidente(1,"incidente2", "incidente2", Calendar.getInstance().getTime(), categoria, new Coordenada(30.0, 30.0));

        Facade.getInstance().crearEstablecimiento("establecimiento1", null, new Coordenada(10.3,10.3));

        Establecimiento establecimiento = Facade.getInstance().obtenerEstablecimiento(3);

        String riesgo = establecimiento.getRiesgo();

        Assert.assertEquals(riesgo, "ALTO");

    }



}