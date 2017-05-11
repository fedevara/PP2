package project.pack.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;

import static org.junit.Assert.*;

/**
 * Created by Federico Vara on 14/4/2017.
 */
public class CacheSingletonTest {
    CacheSingleton cacheSingleton;

    @Before
    public void setUp() throws Exception {
        // Instancio y borro la cache.
        cacheSingleton = CacheSingleton.getInstance();
        cacheSingleton.limpiarCache();
    }

    @After
    public void tearDown() throws Exception {
        // Instancio y borro la cache.
        CacheSingleton.getInstance().limpiarCache();
    }

    @Test
    public void obtenerListaIncidentes() throws Exception {
        // guardo 2 elementos en la cache, la lista debe ser de longitud 2.

        Facade facade = Facade.getInstance();

        facade.crearIncidente(1, "Robo de auto", "En Moron", new Date(), null, null);
        facade.crearIncidente(2, "Robo de celular", "En Hurlingham", new Date(), null, null);
        facade.crearEstablecimiento("Colegio Aleman", null, null);

        int size = facade.obtenerListaIncidentes().size();

        Assert.assertEquals(2, size);

    }

    @Test
    public void obtenerListaEstablecimientos() throws Exception {
        // guardo 2 elementos en la cache, la lista debe ser de longitud 2.

        Facade facade = Facade.getInstance();

        facade.crearIncidente(1, "Robo de auto", "En Moron", new Date(), null, null);
        facade.crearEstablecimiento("Hospital de Hurlingham", null, null);
        facade.crearEstablecimiento("Colegio Aleman", null, null);

        int size = facade.obtenerListaEstablecimientos().size();

        Assert.assertEquals(2, size);

    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(CacheSingleton.getInstance().equals(cacheSingleton));
    }

    @Test
    public void get() throws Exception {
        //Guardo dos elementos en la cache, recupero el elemento nº 2, y comparo su id con el nº 2.
        CacheSingleton cacheSingleton = CacheSingleton.getInstance();
        Facade facade = Facade.getInstance();

        // Guardar incidente
        Incidente inGuardar1 = facade.crearIncidente(1, "Robo de celular", "En Hurlingham", new Date(), null, null);
        Incidente inGuardar2 = facade.crearIncidente(2, "Robo de auto", "En Moron", new Date(), null, null);

        // Recuperar incidente
        Incidente incidente1 = (Incidente) cacheSingleton.get(1);
        Incidente incidente2 = (Incidente) cacheSingleton.get(2);

        // Comparar incidente guardado con el recuperado
        assertTrue(incidente1.equals(inGuardar1));
        assertTrue(incidente2.equals(inGuardar2));

    }

    @Test
    public void put() throws Exception {
        // Agrego dos elementos a la cache, si se agregaron correctamente, la longitud debe ser 2
        CacheSingleton cache = CacheSingleton.getInstance();
        cache.limpiarCache();

        Facade facade = Facade.getInstance();

        facade.crearIncidente(1, "Robo de celular", "En Hurlingham", new Date(), null, null);
        facade.crearIncidente(2, "Robo de auto", "En Moron", new Date(), null, null);

        int cantidadElementos = cache.size();
        assertEquals(2, cantidadElementos);
    }

    @Test
    public void remove() throws Exception {
        CacheSingleton cache = CacheSingleton.getInstance();

        // Agrego items del cualquier tipo
        cache.put(123);
        cache.put("Hola mundo");
        cache.put("Muzza");
        cache.put("azul");
        cache.put("Perro");
        cache.put("Moneda");
        cache.put("Dying Light");
        assertEquals(new Integer(7), cache.size());

        // Pruebo remover un item
        cache.remove(1);
        cache.remove(2);

        // Deben quedar 5 Cache objects
        assertEquals(new Integer(5), cache.size());

        // Al intentar obtenerlos debe dar null
        assertNull(cache.get(1));
        assertNull(cache.get(2));
    }

    @Test
    public void size() throws Exception {
        // Agrego 3 elementos a la cache, y la longitud de la misma debe ser 3.
        CacheSingleton cache = CacheSingleton.getInstance();

        String saludo = "hola";
        Incidente incidente = null;
        Integer numero = 4;

        cache.put(saludo);
        cache.put(incidente);
        cache.put(numero);

        assertEquals(new Integer(3), cache.size());
    }

    @Test
    public void limpiarCache() throws Exception {
        // Agrego elementos a la cache, luego la limpio y finalmente el tamñano debe ser 0 .
        CacheSingleton cache = CacheSingleton.getInstance();

        String saludo = "hola";
        Incidente incidente = null;
        Integer numero = 4;

        cache.put(saludo);
        cache.put(incidente);
        cache.put(numero);

        cache.limpiarCache();
        assertEquals(new Integer(0), cache.size());
    }
}