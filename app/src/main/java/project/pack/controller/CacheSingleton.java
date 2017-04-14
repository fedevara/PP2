package project.pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    }

    public static CacheSingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CacheSingleton();
        return INSTANCE;
    }


    private long tiempoDeVida;
    private Map<K, T> CacheMap;

    // Constructor
    private CacheSingleton(long TiempoDeVida, final long IntervaloTiempo) {
        this.tiempoDeVida = TiempoDeVida * 1000;

        CacheMap = new HashMap();

        if (tiempoDeVida > 0 && IntervaloTiempo > 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(IntervaloTiempo * 1000);
                        } catch (InterruptedException ex) {
                        }
                        LimpiarCache(false);
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    public T get(K key) {
        // Evita deadlock
        synchronized (CacheMap) {
            ObjetoCache c = (ObjetoCache) CacheMap.get(key);

            if (c == null)
                return null;
            else {
                //c.UltimoUso = System.currentTimeMillis();
                return (T) c.getValue();
            }
        }
    }

    public void put(K key, T value) {
        synchronized (CacheMap) {
            CacheMap.put(key, (T) new ObjetoCache(value));
        }
    }

    public void remove(K key) {
        synchronized (CacheMap) {
            CacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (CacheMap) {
            return CacheMap.size();
        }
    }

    public void LimpiarCache() {
        LimpiarCache(true);
    }

    private void LimpiarCache(boolean forzarLimpiar) {

        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;

        synchronized (CacheMap) {
            deleteKey = new ArrayList<K>((CacheMap.size() / 2) + 1);
            K key = null;
            ObjetoCache co = null;

            for (Map.Entry<K, T> eMap : CacheMap.entrySet()) {

                co = (ObjetoCache) eMap.getValue();
                if ((co != null && (now > (tiempoDeVida + co.UltimoUso()))) || forzarLimpiar) {
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
        }

    }

}
