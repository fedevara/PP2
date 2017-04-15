package project.pack.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import project.pack.domain.Incidente;
import project.pack.facade.Facade;

import static org.junit.Assert.*;

/**
 * Created by Federico Vara on 14/4/2017.
 */
public class CacheSingletonTest {

    @Before
    public void setUp() throws Exception {
        // Instancio y borro la cache.
        CacheSingleton cacheSingleton = CacheSingleton.getInstance();
        cacheSingleton.limpiarCache();
    }

    @After
    public void tearDown() throws Exception {
        // Instancio y borro la cache.
        CacheSingleton cacheSingleton = CacheSingleton.getInstance();
        cacheSingleton.limpiarCache();
    }

    @Test
    public void obtenerListaIncidentes() throws Exception {
        // guardo 2 elementos en la cache, la lista debe ser de longitud 2.
        CacheSingleton cacheSingleton = CacheSingleton.getInstance();

        Facade facade = Facade.getInstance();

        facade.crearIncidente(1, "Robo de auto", "En Moron", new Date(), null, null);
        facade.crearIncidente(2, "Robo de celular", "En Hurlingham", new Date(), null, null);
        facade.crearEstablecimiento("Colegio Aleman", null, null);

        int size = facade.obtenerListaIncidentes().size();

        Assert.assertEquals(2, size);

    }

    @Test
    public void getInstance() throws Exception {

    }

    @Test
    public void get() throws Exception {
        //Guardo dos elementos en la cache, recupero el elemento nº 2, y comparo su id con el nº 2.
        CacheSingleton cacheSingleton = CacheSingleton.getInstance();

        Facade facade = Facade.getInstance();

        facade.crearIncidente(1, "Robo de celular", "En Hurlingham", new Date(), null, null);
        facade.crearIncidente(2, "Robo de auto", "En Moron", new Date(), null, null);

        Incidente incidente = (Incidente) cacheSingleton.get(1);
        assertEquals(1, incidente.getId());
    }

    @Test
    public void put() throws Exception {
        // Agrego dos elementos a la cache, si se agregaron correctamente, la longitud debe ser 2
        CacheSingleton cache = CacheSingleton.getInstance();

        Facade facade = Facade.getInstance();

        facade.crearIncidente(1, "Robo de celular", "En Hurlingham", new Date(), null, null);
        facade.crearIncidente(2, "Robo de auto", "En Moron", new Date(), null, null);

        int cantidadElementos = cache.size();

        assertEquals(2, cantidadElementos);
    }

    /*
        @Test
        public void remove() throws Exception {
            // Creo una memoria cache.
            // tiempo de vida de un cache = 200 segundos
            // Tiempo Intervalo de actualizacion  = 60 segundos
            CacheSingleton cache = CacheSingleton.getInstance();
            // Agrego items del cualquier tipo
            cache.put("Portal", 123);
            cache.put("P1", "Hola mundo");
            cache.put("Pizza", "Muzza");
            cache.put("zapato", "azul");
            cache.put("Animal", "Perro");
            cache.put("Cofre", "Moneda");
            cache.put(123, "Dying Light");
            assertEquals(7, cache.size());

            // Pruebo remover un item
            cache.remove("zapato");
            cache.remove("Cofre");
            // Deben quedar 5 Cache objects
            assertEquals(5, cache.size());

            // Al intentar obtenerlos debe dar null
            assertNull(cache.get("zapato"));
            assertNull(cache.get("Cofre"));
        }
    */
    @Test
    public void size() throws Exception {
        // Agrego 3 elementos a la cache, y la longitud de la misma debe ser 3.
        CacheSingleton cache = CacheSingleton.getInstance();

        String saludo = "hola";
        Incidente incidente = new Incidente();
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
        Incidente incidente = new Incidente();
        Integer numero = 4;

        cache.put(saludo);
        cache.put(incidente);
        cache.put(numero);

        cache.limpiarCache();
        assertEquals(new Integer(0), cache.size());
    }

}