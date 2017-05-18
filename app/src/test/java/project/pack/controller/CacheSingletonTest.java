package project.pack.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

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


        Incidente i1 = new Incidente();
        i1.setId(1);
        i1.setTitulo("Robo de auto");
        i1.setDescripcion("En Moron");
        i1.setFecha(new Date());

        Incidente i2 = new Incidente();
        i2.setId(2);
        i2.setTitulo("Robo de celular");
        i2.setDescripcion("En Hurlingham");
        i2.setFecha(new Date());

        Establecimiento es2 = new Establecimiento();
        es2.setNombre("Colegio Aleman");

        //int size = facade.obtenerListaIncidentes().size();

       // Assert.assertEquals(2, size);

    }

    @Test
    public void obtenerListaEstablecimientos() throws Exception {
        // guardo 2 elementos en la cache, la lista debe ser de longitud 2.

        Incidente i1 = new Incidente();
        i1.setId(1);
        i1.setTitulo("Robo de auto");
        i1.setDescripcion("En Moron");
        i1.setFecha(new Date());

        Establecimiento es1 = new Establecimiento();
        es1.setNombre("Hospital de Hurlingham");

        Establecimiento es2 = new Establecimiento();
        es2.setNombre("Colegio Aleman");

        Establecimiento es3 = new Establecimiento();
        es3.setNombre("Colegio Italiano");

        cacheSingleton.getInstance().put(i1);
        cacheSingleton.getInstance().put(es1);
        cacheSingleton.getInstance().put(es2);
        cacheSingleton.getInstance().put(es3);



        ArrayList listEstablecimientosOLD = cacheSingleton.getInstance().obtenerListaEstablecimientos();
        Assert.assertEquals(3, listEstablecimientosOLD.size());

        ArrayList listEstablecimientos = cacheSingleton.getInstance().obtenerLista(new Establecimiento());
        Assert.assertEquals(3, listEstablecimientos.size());

        ArrayList listIncidentes = cacheSingleton.getInstance().obtenerLista(new Incidente());
        Assert.assertEquals(1, listIncidentes.size());

    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(CacheSingleton.getInstance().equals(cacheSingleton));
    }

    @Test
    public void get() throws Exception {
        //Guardo dos elementos en la cache, recupero el elemento nº 2, y comparo su id con el nº 2.
        CacheSingleton cache = CacheSingleton.getInstance();

        // Guardar incidente
        Incidente i1 = new Incidente();
        i1.setId(1);
        i1.setTitulo("Robo de auto");
        i1.setDescripcion("En Moron");
        i1.setFecha(new Date());

        Incidente i2 = new Incidente();
        i2.setId(2);
        i2.setTitulo("Robo de celular");
        i2.setDescripcion("En Hurlingham");
        i2.setFecha(new Date());

        cache.put(i1);
        cache.put(i2);

        // Recuperar incidente
        Incidente incidente1 = (Incidente) cache.get(1);
        Incidente incidente2 = (Incidente) cache.get(2);

        // Comparar incidente guardado con el recuperado
        assertTrue(incidente1.equals(i1));
        assertTrue(incidente2.equals(i2));

    }

    @Test
    public void put() throws Exception {
        // Agrego dos elementos a la cache, si se agregaron correctamente, la longitud debe ser 2
        CacheSingleton cache = CacheSingleton.getInstance();
        cache.limpiarCache();


        Incidente i1 = new Incidente();
        i1.setId(1);
        i1.setTitulo("Robo de auto");
        i1.setDescripcion("En Moron");
        i1.setFecha(new Date());

        Incidente i2 = new Incidente();
        i2.setId(2);
        i2.setTitulo("Robo de celular");
        i2.setDescripcion("En Hurlingham");
        i2.setFecha(new Date());

        cache.put(i1);
        cache.put(i2);

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
        cache.put(new Incidente());
        cache.put(new Establecimiento());
        assertEquals(new Integer(5), cache.size());

        // Pruebo remover un item
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

    /* Criterio de aceptacion:
     Cuando se debe agregar un elemento a la caché pero ésta está llena,
     entonces se debe desechar un elemento viejo para poder ingresar uno nuevo */
    @Test
    public void cacheLlena(){
        CacheSingleton cache = CacheSingleton.getInstance();
        // Setea el limite de items del cache
        Integer limit = 5;
        cache.setLimitItems(limit);
        Integer LimitItems = cache.getLimitItems();
        assertEquals(LimitItems, limit);

        // Agrego items del cualquier tipo
        cache.put(123);
        cache.put("Hola mundo");
        cache.put("Muzza");
        cache.put(new Incidente());
        // Hay 4 items en la cache, no esta llena
        assertFalse(cache.isFull());
        cache.put(new Establecimiento());
        // La cache esta llena
        assertTrue(cache.isFull());
        // Agrego dos items mas, se deben agregar igual
        cache.put("Moneda");
        cache.put("Dying Light");

        // Los más viejos se descartan
        assertNull(cache.get(1));
        assertNull(cache.get(2));

        // los más recientes se mantienen
        assertNotNull(cache.get(3));
        assertNotNull(cache.get(4));
        assertNotNull(cache.get(5));
        assertNotNull(cache.get(6));
        assertNotNull(cache.get(7));
    }



    // Cuando cargue los datos almacenados, deben ser los mismos que los que se guardaron
    // Si falla al cargar, se muestra un mensaje amigable diciendo “no se logró cargar“
    // Si falla al guardar, se muestra un mensaje amigable diciendo “no se logró guardar”
    // No se deben perder datos al almacenar
























}//--> FIN