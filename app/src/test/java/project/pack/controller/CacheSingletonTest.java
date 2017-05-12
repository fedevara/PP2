package project.pack.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

import static org.junit.Assert.*;

/**
 * Created by Federico Vara on 14/4/2017.
 */
public class CacheSingletonTest {
    private CacheSingleton cache;

    @Before
    public void setUp() throws Exception {
        // Instancio y borro la cache.
        cache = CacheSingleton.getInstance();
        cache.limpiarCache();
        cache.setLimitItems(256);
    }

    @After
    public void tearDown() throws Exception {
        // Instancio y borro la cache.
        cache.getInstance().limpiarCache();
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(CacheSingleton.getInstance().equals(cache));
    }

    @Test
    public void obtenerObjetosDelCache() throws Exception {
        // guardo 2 elementos en la cache, la lista debe ser de longitud 2.
        cache.put(new Incidente(new Coordenada(), 1, "titulo1", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Incidente(new Coordenada(), 2, "titulo2", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Categoria(1, "categoria", "1", null));
        cache.put(new Categoria(1, "categoria", "5", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento1", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento2", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento3", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento4", null));

        ArrayList listEstablecimientos = cache.obtenerLista(Establecimiento.class);
        ArrayList listIncidentes = cache.obtenerLista(Incidente.class);

        Assert.assertEquals(2, listIncidentes.size());
        Assert.assertEquals(4, listEstablecimientos.size());
    }

    // No se deben perder datos al almacenar
    @Test
    public void get() throws Exception {
        //Guardo dos elementos en la cache, recupero el elemento nº 2, y comparo su id con el nº 2.
        CacheSingleton cache = CacheSingleton.getInstance();

        // Guardar
        Incidente i1 = new Incidente(new Coordenada(), 1, "titulo", "Robo de auto", new Date(), new Date(), new Categoria());
        Incidente i2 = new Incidente(new Coordenada(), 1, "titulo", "Robo de auto", new Date(), new Date(), new Categoria());

        cache.put(i1);
        cache.put(i2);

        // Recuperar
        Incidente incidente1 = (Incidente) cache.get(1);
        Incidente incidente2 = (Incidente) cache.get(2);

        // Comparar guardado con el recuperado
        assertTrue(incidente1.equals(i1));
        assertTrue(incidente2.equals(i2));

    }

    @Test
    public void remove() throws Exception {
        CacheSingleton cache = CacheSingleton.getInstance();

        // Agrego items del cualquier tipo
        cache.put(123);
        cache.put("Hola mundo");
        cache.put("Muzza");
        cache.put(new Incidente(new Coordenada(), 2, "titulo2", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento4", null));

        assertEquals(new Integer(5), cache.size());

        // Pruebo remover dos items
        cache.remove(1);
        cache.remove(2);

        // Deben quedar 5 Cache objects
        assertEquals(new Integer(3), cache.size());

        // Al intentar obtenerlos debe dar null
        assertNull(cache.get(1));
        assertNull(cache.get(2));
    }

    @Test
    public void size() throws Exception {
        // Agrego 3 elementos a la cache, y la longitud de la misma debe ser 3.
        CacheSingleton cache = CacheSingleton.getInstance();

        cache.put(new Incidente(new Coordenada(), 1, "titulo1", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Incidente(new Coordenada(), 2, "titulo2", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Categoria(1, "categoria", "1", null));
        cache.put(new Categoria(1, "categoria", "5", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento1", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento2", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento3", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento4", null));

        assertEquals(new Integer(8), cache.size());
    }

    @Test
    public void limpiarCache() throws Exception {
        // Agrego elementos a la cache, luego la limpio y finalmente el tamñano debe ser 0 .
        CacheSingleton cache = CacheSingleton.getInstance();

        cache.put(new Incidente(new Coordenada(), 1, "titulo1", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Incidente(new Coordenada(), 2, "titulo2", "Robo de auto", new Date(), new Date(), new Categoria()));
        cache.put(new Categoria(1, "categoria", "1", null));
        cache.put(new Categoria(1, "categoria", "5", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento1", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento2", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento3", null));
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento4", null));

        cache.limpiarCache();
        assertEquals(new Integer(0), cache.size());

    }

    /* Criterio de aceptacion:
     Cuando se debe agregar un elemento a la caché pero ésta está llena,
     entonces se debe desechar un elemento viejo para poder ingresar uno nuevo */
    @Test
    public void cacheLlena(){
        CacheSingleton cache = CacheSingleton.getInstance();
        // Setea el limite de items del cache
        Integer limit = 5;
        cache.setLimitItems(limit);

        // Agrego items del cualquier tipo
        cache.put("Hola mundo");
        cache.put("Muzza");
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento4", null));
        cache.put(new Incidente(new Coordenada(), 1, "titulo1", "Robo de auto", new Date(), new Date(), new Categoria()));

        // Hay 4 items en la cache, no esta llena
        assertFalse(cache.isFull());
        cache.put(new Categoria(1, "categoria", "1", null));
        // La cache esta llena
        assertTrue(cache.isFull());

        // Agrego dos items mas, se deben agregar igual
        cache.put("Moneda");
        cache.put(new Establecimiento(new Coordenada(10.3,10.3), "establecimiento1", null));


        // Los más viejos se descartan, por lo tanto al intentar
        // obtenerlos debe devolver null
        assertNull(cache.get(1));
        assertNull(cache.get(2));

        // los más recientes se mantienen, no pueden ser null
        assertNotNull(cache.get(3));
        assertNotNull(cache.get(4));
        assertNotNull(cache.get(5));
        assertNotNull(cache.get(6));
        assertNotNull(cache.get(7));
    }

    // Cuando cargue los datos almacenados, deben ser los mismos que los que se guardaron
    // Si falla al cargar, se muestra un mensaje amigable diciendo “no se logró cargar“
    // Si falla al guardar, se muestra un mensaje amigable diciendo “no se logró guardar”



}//--> FIN