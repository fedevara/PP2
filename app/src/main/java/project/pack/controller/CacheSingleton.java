package project.pack.controller;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Incidente;
import project.pack.domain.ObjetoCache;
import project.pack.facade.Facade;
import project.pack.persistence.DAOImpl.ConectionFile;

/**
 * Created by lukas on 11/04/2017.
 */

public class CacheSingleton<K, T> {
    private static CacheSingleton<?, ?> INSTANCE;
    private Map<String, T> CacheMap = null;
    // Parametros
    private Integer limitItems = 256;
    private String CacheFile = "cache.ch";

    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definición de la clase)
    private CacheSingleton() {
        //CacheMap = Deserializar();
        if(CacheMap == null)
            CacheMap = new HashMap();
    }

    public static CacheSingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CacheSingleton();
        return INSTANCE;
    }

    public T get(Integer id, Class tipo) {
        if(tipo!=null && id!=null) {
            String NomClase = tipo.getName();
            String KEY_ITEM = NomClase + id; // ejemplo "Incidente31"


            if (CacheMap.size() > 0) {
                ObjetoCache c = (ObjetoCache) CacheMap.get(KEY_ITEM);
                if(c!=null)
                    return (T) c.getValue();
            }
        }
        return null;
    }

    public void put(T value, Integer id) {
        if(value!=null && id!=null) {

            // Buscar si ya existe el item en el cache
            String KEY_ITEM = findKeyItem(value, id);

            // Si existe en el cache, entocnes eliminalo para agregar el "actualizado"
            if (KEY_ITEM != null) {
                remove(KEY_ITEM);
            }
            // Si no existe, entonces crea la key
            else {
                String NomClase = value.getClass().getName().toString();
                KEY_ITEM = NomClase + id; // ejemplo "Incidente31"
            }

            // Si la cache esta llena
            if( isFull() ) {
                // Borra un item viejo
                removeOldItem();
            }
            // Agrega el value a la cache, dentro de un ObjetoCache (Memoria)
            CacheMap.put(KEY_ITEM, (T) new ObjetoCache<T>(value));

            // Persistencia
            Serializar();
        }
    }

    private void remove(String key) {
        // Remover en memoria
        CacheMap.remove(key);
        // Persistencia
        Serializar();
    }

    public Integer size() {
        return CacheMap.size();
    }

    /** Recorre el cache en busqueda de todos los
     * elementos que sean de la clase especificada
     * @return lista del objeto que se solicito*/
    public ArrayList<Object> obtenerLista(Class<?> obj) {

        ArrayList<Object> lista = new ArrayList<>();

        if(CacheMap!=null){
            for (Map.Entry<String, T> eMap : CacheMap.entrySet()) {

                ObjetoCache oc = ((ObjetoCache) eMap.getValue());

                if (oc.getValue().getClass().equals(obj)) {
                    Object item = oc.getValue();
                    lista.add(item);
                }
            }

        }


        return lista;
    }

    /**
     * Elimina todos los datos de la cache
     */
    public void limpiarCache() {
        CacheMap = new HashMap();
        Serializar();
    }

    /**
     * Indica si la cache esta llena
     */
    public boolean isFull(){
        return this.size()>=limitItems;
    }

    /**
     * Elimina el elemento mas viejo
     */
    private void removeOldItem() {
        String keyMasViejo = "";
        long creadoMasViejo = 0;
        boolean firstTime = true;
        if(CacheMap.size()>0){
            for (Map.Entry<String, T> eMap : CacheMap.entrySet()) {
                ObjetoCache oc = ((ObjetoCache) eMap.getValue());

                if(firstTime){
                    creadoMasViejo = oc.getCreado();
                    keyMasViejo = eMap.getKey();
                    firstTime = false;
                }

                // Busco el objetoCache menos accedido en comparacion de los demas
                if(creadoMasViejo > oc.getCreado()){
                    keyMasViejo = eMap.getKey();
                    creadoMasViejo = oc.getCreado();
                }
            }
            // Me quedo con el la key del objetoCache mas viejo para eliminarlo
            remove(keyMasViejo);
        }
    }

    public void setLimitItems(Integer limit){
        if(limit>0)
            this.limitItems = limit;
    }

    public Integer getLimitItems(){
        return limitItems;
    }

    private String findKeyItem(T item, Integer id){
        String ID_ITEM = null;
        if(CacheMap.size()>0){
            for (Map.Entry<String, T> eMap : CacheMap.entrySet()) {
                ObjetoCache oc = ((ObjetoCache) eMap.getValue());
                if(item.getClass().equals(oc.getClass())){
                    item.equals(oc);
                    String NomClase = item.getClass().getName();
                    ID_ITEM = NomClase + id; // Incidente31
                    return ID_ITEM;
                }
            }
        }
        return ID_ITEM;
    }

    public void Serializar(){
/*
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        Facade fac = Facade.getInstance();
        Context context = fac.getContext();

        try {
            fos = context.openFileOutput(CacheFile, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(CacheMap);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        finally {
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {e.printStackTrace();}
            try {
                if(oos!=null)
                    oos.close();
            } catch (IOException e) {e.printStackTrace();}
        }*/
    }
/*
    public Map<String, T> Deserializar(){

        Map<String, T> item = null;
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        Facade fac = Facade.getInstance();
        Context context = fac.getContext();

        try {
            fis = context.openFileInput(CacheFile);
            ois = new ObjectInputStream(fis);
            item = (Map<String, T>) ois.readObject();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        finally {
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {e.printStackTrace();}
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException e) {e.printStackTrace();}

        }
        return item;
    }
*/
}//-->FIN CLASE