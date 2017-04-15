package project.pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.pack.domain.Incidente;
import project.pack.domain.ObjetoCache;

/**
 * Created by lukas on 11/04/2017.
 */

public class CacheSingleton<K, T> {
    private static CacheSingleton INSTANCE;

    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definición de la clase)
    private CacheSingleton() {
        CacheMap = new HashMap();
        id=0;
    }

    public static CacheSingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CacheSingleton();
        return INSTANCE;
    }


    private long tiempoDeVida;
    private Map<Integer, T> CacheMap;
    private static Integer id;

    // Constructor
    private CacheSingleton(long TiempoDeVida, final long IntervaloTiempo) {
        this.tiempoDeVida = TiempoDeVida * 1000;

        CacheMap = new HashMap();
        id=0;
        if (tiempoDeVida > 0 && IntervaloTiempo > 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(IntervaloTiempo * 1000);
                        } catch (InterruptedException ex) {
                        }
                        limpiarCache(false);
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    public T get(Integer key) {
        // Evita deadlock
        synchronized (CacheMap) {
            ObjetoCache c = (ObjetoCache) CacheMap.get(key);
           // return CacheMap.entrySet().toArray().getValue().getValue;

            if (c == null)
                return null;
            else {
                //c.UltimoUso = System.currentTimeMillis();
                return (T) c.getValue();
            }
        }
    }

    public void put(T value) {
        synchronized (CacheMap) {
            id++;
            CacheMap.put(id, (T) new ObjetoCache(value));

        }
    }

    public void remove(Integer key) {
        synchronized (CacheMap) {
            CacheMap.remove(key);
        }
    }

    public Integer size() {
        synchronized (CacheMap) {
            return CacheMap.size();
        }
    }

    /**
     * Metodo que se encarga de recorrer la cache y si un elemento es un incidente, lo agrego a mi lista.
     *
     * @return lista de incidentes
     */
    public ArrayList<Incidente> obtenerListaIncidentes(){

        ArrayList<Incidente> lista = new ArrayList<Incidente>();

        for (Map.Entry<Integer, T> eMap : CacheMap.entrySet()) {

            if ( ( (ObjetoCache) eMap.getValue() ).getValue() instanceof Incidente){
                Incidente incidente =(Incidente) ((ObjetoCache) eMap.getValue()).getValue();
                lista.add(incidente);
            }
        }
        return lista;
    }

    public void limpiarCache() {
        id = 0;
        CacheMap = new HashMap();
    }

    private void limpiarCache(boolean forzarLimpiar) {
        id = 0;
        CacheMap = new HashMap();

       /* long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;

        synchronized (CacheMap) {
            deleteKey = new ArrayList<K>((CacheMap.size() / 2) + 1);
            K key = null;
            ObjetoCache cacheObject = null;

            for (Map.Entry<Integer, T> eMap : CacheMap.entrySet()) {

                cacheObject = (ObjetoCache) eMap.getValue();
                if ((cacheObject != null && (now > (tiempoDeVida + cacheObject.UltimoUso()))) || forzarLimpiar) {
                    deleteKey.add(eMap.getKey());
                }
            }
        }

        for (K key : deleteKey) {
            synchronized (CacheMap) {
                CacheMap.remove(key);
            }
            //es para que le cedas el procesador a otro hilo
            Thread.yield();
        }*/

    }

}
