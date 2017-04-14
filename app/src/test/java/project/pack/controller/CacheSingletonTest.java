package project.pack.controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Federico Vara on 14/4/2017.
 */
public class CacheSingletonTest {
    @Test
    public void getInstance() throws Exception {

    }

    @Test
    public void get() throws Exception {
        // Creo una memoria cache.
        // tiempo de vida de un cache = 200 segundos
        // Tiempo Intervalo de actualizacion  = 60 segundos
        CacheSingleton<Object, Object> cache = new CacheSingleton<Object, Object>(200, 60);

        // Agrego item
        cache.put("Pizza", "Muzza");
        assertEquals("Muzza", cache.get("Pizza"));
    }

    @Test
    public void put() throws Exception {
        // Creo una memoria cache.
        // tiempo de vida de un cache = 200 segundos
        // Tiempo Intervalo de actualizacion  = 60 segundos
        CacheSingleton cache = new CacheSingleton(200, 60);
        // Agrego item
        cache.put("Pizza", "Muzza");
        assertEquals("Muzza", cache.get("Pizza"));
    }

    @Test
    public void remove() throws Exception {
        // Creo una memoria cache.
        // tiempo de vida de un cache = 200 segundos
        // Tiempo Intervalo de actualizacion  = 60 segundos
        CacheSingleton<Object, Object> cache = new CacheSingleton<Object, Object>(200, 60);
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

    @Test
    public void size() throws Exception {
        // Creo una memoria cache.
        // tiempo de vida de un cache = 200 segundos
        // Tiempo Intervalo de actualizacion  = 60 segundos
        CacheSingleton<Object, Object> cache = new CacheSingleton<Object, Object>(200, 60);
        // Agrego 5 items del cualquier tipo
        cache.put("Portal", 123);
        cache.put("P1", "Hola mundo");
        cache.put("Pizza", "Muzza");
        cache.put("zapato", "azul");
        cache.put("Animal", "Perro");
        // Espero tener un tamaño de 5 items
        assertEquals(5,cache.size());
    }

    @Test
    public void limpiarCache() throws Exception {
        // Creo una memoria cache.
        // tiempo de vida = 1 segundos
        // Tiempo Intervalo de actualizacion  = 1 segundos
        CacheSingleton<Object, Object> cache = new CacheSingleton<Object, Object>(1, 1);
        // Agrego 5 items cada medio segundo
        int size = 6;
        for (int i = 0; i < size; i++) {
            String value = "Cache de prueba "+i;
            cache.put(value, value);
            Thread.sleep(500);
        }

        // Limpiar del cache de todos los objetos (forzado), no espera hasta que expiren
        cache.LimpiarCache();
        assertEquals(0,cache.size());
    }

}