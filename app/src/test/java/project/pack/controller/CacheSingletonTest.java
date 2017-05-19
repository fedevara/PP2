package project.pack.controller;

import android.test.InstrumentationTestCase;

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
import project.pack.utilities.ConnectionUtilities;

import static org.junit.Assert.*;

/**
 * Created by Federico Vara on 14/4/2017.
 */
public class CacheSingletonTest {
    private CacheSingleton cache;

    //AndroidTestCase()

    // ANTES DEL TEST
    @Before
    public void setUp() throws Exception {
        // Instancio y borro la cache.
        cache = CacheSingleton.getInstance();
        cache.limpiarCache();
        cache.setLimitItems(256);

        // guardo 2 elementos en la cache, la lista debe ser de longitud 2.
        Incidente i1 = new Incidente(new Coordenada(), 1, "titulo1", "Robo de auto", new Date(), new Date(), new Categoria());
        Incidente i2 = new Incidente(new Coordenada(), 2, "titulo2", "Robo de auto", new Date(), new Date(), new Categoria());
        Categoria c1 = new Categoria(1, "categoria", "1", null);
        Categoria c2 = new Categoria(2, "categoria", "5", null);
        Establecimiento e1 = new Establecimiento(1, new Coordenada(10.3,10.3), "establecimiento1", null);
        Establecimiento e2 = new Establecimiento(2, new Coordenada(10.3,10.3), "establecimiento1", null);
        Establecimiento e3 = new Establecimiento(3, new Coordenada(10.3,10.3), "establecimiento1", null);
        Establecimiento e4 = new Establecimiento(4, new Coordenada(10.3,10.3), "establecimiento1", null);

        cache.put(i1,i1.getId());
        cache.put(i2,i2.getId());
        cache.put(c1,c1.getId());
        cache.put(c2,c2.getId());
        cache.put(e1,e1.getId());
        cache.put(e2,e2.getId());
        cache.put(e3,e3.getId());
        cache.put(e4,e4.getId());
    }

    private void agregarIncidentes(Integer cant){
        for (int i = 1; i <= cant; i++) {
            Incidente inc = new Incidente(new Coordenada(), i, "titulo "+ i, "Robo de auto", new Date(), new Date(), new Categoria());
            cache.put(inc, inc.getId());
        }
    }

    private void agregarEstablecimiento(Integer cant){
        for (int i = 1; i <= cant; i++) {
            Establecimiento est = new Establecimiento(i, new Coordenada(10.3,10.3), "establecimiento "+i, null);
            cache.put(est, est.getId());
        }
    }

    private void agregarCategoria(Integer cant){
        for (int i = 1; i <= cant; i++) {
            Categoria cat = new Categoria(i, "categoria "+i, "1", null);
            cache.put(cat, cat.getId());
        }
    }

    // DESPUES DEL TEST
    @After
    public void tearDown() throws Exception {
        // Instancio y borro la cache.
        cache.getInstance().limpiarCache();
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(CacheSingleton.getInstance().equals(cache));
    }

    // No se deben perder datos al almacenar
    @Test
    public void get() throws Exception {

        // vaciar cache
        cache.limpiarCache();

        // Guardar
        Incidente i1 = new Incidente(new Coordenada(), 1, "titulo1", "Robo de auto", new Date(), new Date(), new Categoria());
        Incidente i2 = new Incidente(new Coordenada(), 2, "titulo2", "Robo de auto", new Date(), new Date(), new Categoria());

        //Guardo dos elementos en la cache
        cache.put(i1, i1.getId());
        cache.put(i2, i2.getId());

        // Recuperar
        Incidente incidente1 = (Incidente) cache.get(i1.getId(), Incidente.class);
        Incidente incidente2 = (Incidente) cache.get(i2.getId(), Incidente.class);

        // Comparar elemento guardado con el recuperado
        assertTrue(incidente1.equals(i1));
        assertTrue(incidente2.equals(i2));
    }

    @Test
    public void size() throws Exception {

        // vaciar cache
        cache.limpiarCache();

        // Agrego 10 elementos a la cache
        agregarCategoria(3);
        agregarIncidentes(4);
        agregarEstablecimiento(3);

        // la longitud de la misma debe ser 10.
        assertEquals(new Integer(10), cache.size());
    }

    @Test
    public void limpiarCache() throws Exception {

        // Agrego elementos a la cache,
        agregarIncidentes(10);

        // vaciar cache
        cache.limpiarCache();

        //luego la limpio y finalmente el tamñano debe ser 0 .
        assertEquals(new Integer(0), cache.size());

    }

    /* Criterio de aceptacion:
    La caché debe desechar un elemento viejo cuando si esta
    se llena para poder agregar un nuevo elemento */
    @Test
    public void cacheLlena(){
        // vaciar cache
        cache.limpiarCache();

        // Setea el limite de items del cache
        Integer limit = 5;
        cache.setLimitItems(limit);

        // Agrego 4 items
        agregarCategoria(4);
        // Hay 4 items en la cache, no esta llena
        assertFalse(cache.isFull());

        // Agrego uno mas para que se llene
        agregarIncidentes(1);
        // La cache esta llena
        assertTrue(cache.isFull());

        // Agrego dos items mas, se deben agregar igual
        agregarEstablecimiento(2);

        // Los más viejos se descartan, por lo tanto al intentar
        // obtenerlos debe devolver null
        assertNull(cache.get(1, Categoria.class));
        assertNull(cache.get(2, Categoria.class));
        // los más recientes se mantienen, no pueden ser null
        assertNotNull(cache.get(3, Categoria.class));
        assertNotNull(cache.get(4, Categoria.class));
        assertNotNull(cache.get(1, Incidente.class));
        assertNotNull(cache.get(1, Establecimiento.class));
        assertNotNull(cache.get(2, Establecimiento.class));

    }

    /* Criterio de aceptacion:
    Se debe poder obtener una lista de objetos del mismo tipo ingresado,
    que está almacenado en la caché     */
    @Test
    public void obtenerObjetosDelCache() throws Exception {

        // vaciar cache
        cache.limpiarCache();

        // guardo 6 elementos en la cache, la lista debe ser de longitud 8.
        agregarIncidentes(2);
        agregarEstablecimiento(4);

        // Obtener lista de items
        ArrayList listEstablecimientos = cache.obtenerLista(Establecimiento.class);
        ArrayList listIncidentes = cache.obtenerLista(Incidente.class);

        // Comprobar
        Assert.assertEquals(2, listIncidentes.size());
        Assert.assertEquals(4, listEstablecimientos.size());
    }











}//--> FIN