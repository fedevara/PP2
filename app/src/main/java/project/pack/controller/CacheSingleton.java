package project.pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.domain.ObjetoCache;

/**
 * Created by lukas on 11/04/2017.
 */

public class CacheSingleton<K, T> {
    private static CacheSingleton<?, ?> INSTANCE;
    private Map<Integer, T> CacheMap;
    private static Integer id;
    private Integer limitItems = 5;
    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definición de la clase)
    private CacheSingleton() {
        CacheMap = new HashMap<Integer, T>();
        id = 0;
    }

    public static CacheSingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CacheSingleton();
        return INSTANCE;
    }

    public T get(Integer key) {
        ObjetoCache c = (ObjetoCache) CacheMap.get(key);
        if (c == null)
            return null;
        else {
            return (T) c.getValue();
        }
    }

    public void put(T value) {
        id++;
        if( isFull() ) {
            removeOldItem();
        }
        CacheMap.put(id, (T) new ObjetoCache<T>(value));
    }

    public void remove(Integer key) {
        CacheMap.remove(key);
    }

    public Integer size() {
        return CacheMap.size();
    }

    /** Metodo que se encarga de recorrer la cache y si un elemento es un incidente, lo agrego a mi lista.
     *
     * @return lista de incidentes     */
    public ArrayList<Incidente> obtenerListaIncidentes() {

        ArrayList<Incidente> lista = new ArrayList<Incidente>();

        for (Map.Entry<Integer, T> eMap : CacheMap.entrySet()) {

            if (((ObjetoCache) eMap.getValue()).getValue() instanceof Incidente) {
                Incidente incidente = (Incidente) ((ObjetoCache) eMap.getValue()).getValue();
                lista.add(incidente);
            }
        }
        return lista;
    }

    /** Metodo que se encarga de recorrer la cache y si un elemento es un Establecimiento, lo agrego a mi lista.
     *
     * @return lista de Establecimiento*/
    public ArrayList<Establecimiento> obtenerListaEstablecimientos() {

        ArrayList<Establecimiento> lista = new ArrayList<Establecimiento>();

        for (Map.Entry<Integer, T> eMap : CacheMap.entrySet()) {

            if (((ObjetoCache) eMap.getValue()).getValue() instanceof Establecimiento) {
                Establecimiento establecimiento = (Establecimiento) ((ObjetoCache) eMap.getValue()).getValue();
                lista.add(establecimiento);
            }
        }
        return lista;
    }

    public ArrayList<Object> obtenerLista(Object tipo) {

        ArrayList<Object> lista = new ArrayList<Object>();

        for (Map.Entry<Integer, T> eMap : CacheMap.entrySet()) {

            ObjetoCache oc = ((ObjetoCache) eMap.getValue());

            if (oc.getValue().getClass().equals(tipo.getClass())) {
                Object item = (Object) oc.getValue();
                lista.add(item);
            }
        }
        return lista;
    }

    public void limpiarCache() {
        id = 0;
        CacheMap = new HashMap();
    }

    public boolean isFull(){
        return this.size()>=limitItems;
    }

    private void removeOldItem() {
        Integer keyMasViejo = 0;
        long creadoMasViejo = 0;
        boolean firstTime = true;
        if(CacheMap.size()>0){
            for (Map.Entry<Integer, T> eMap : CacheMap.entrySet()) {
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
            System.out.print(keyMasViejo);
        }
    }

    public void setLimitItems(Integer limit){
        if(limit>0)
            this.limitItems = limit;
    }

    public Integer getLimitItems(){
        return limitItems;
    }


}//-->FIN CLASE