package project.pack.controller;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Date;

import project.pack.domain.Incidente;

/**
 * Created by Federico Vara on 14/4/2017.
 */
public class ManejoIncidenteTest {
    @Test
    public void crearIncidente() throws Exception {

        Incidente incidente = new Incidente();

        incidente.setId(1);
        incidente.setTitulo("Robo de auto");
        incidente.setFecha(new Date());
        incidente.setDescripcion("Me robaron el auto en Vergara 2500 a las 20 hs.");

        Assert.assertNotNull(incidente);
    }

    @Test
    public void guardarIncidente() throws Exception {

    }

    @Test
    public void getIncidente() throws Exception {

    }

}