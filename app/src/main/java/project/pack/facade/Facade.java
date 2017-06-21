package project.pack.facade;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import project.pack.controller.ManejoCategoria;
import project.pack.persistence.CacheSingleton;
import project.pack.controller.ManejoEstablecimiento;
import project.pack.controller.ManejoIncidente;
import project.pack.utilities.CategoriaProperties;
import project.pack.utilities.ManejoProperties;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class Facade {

    private ManejoIncidente manejoIncidente;
    private ManejoEstablecimiento manejoEstablecimiento;
    private ManejoProperties manejoProperties;
    private ManejoCategoria manejoCategoria;
    private Context context;

    private static Facade INSTANCE;

    public static Facade getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Facade();
        return INSTANCE;
    }

    private Facade() {
        manejoIncidente = new ManejoIncidente();
        manejoEstablecimiento = new ManejoEstablecimiento();
        manejoProperties = new ManejoProperties();
        manejoCategoria = new ManejoCategoria();
    }

    /**
    * INICIO METODOS CORRESPONDIENTES A INCIDENTES
    */

    /**
     * Crea un incidente y lo guarda en la memoria cache
     *
     * @param titulo      Titulo del incidente
     * @param descripcion Descripcion del incidente
     * @param fecha       Fecha en la que sucesio el incidente, puede ser distinta a la actual
     * @param categoria   Categoria del incidente
     * @param coordenada       Lugar donde ocurrio el incidente
     * @return Devuelve el objeto creado y guardado en la base
     */
    public Incidente crearIncidente(String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada coordenada) {
        Incidente incidente = manejoIncidente.crearIncidente(coordenada, titulo, descripcion, fecha, categoria);
        incidente = manejoIncidente.guardarIncidente(incidente);
        return incidente;
    }

    public Incidente obtenerIncidente(Integer id1) {
        return manejoIncidente.getIncidente(id1);
    }

    public List<Incidente> obtenerListaIncidentes() {
        return manejoIncidente.getListaIncidentes();
    }

    public List<Incidente> getListaIncidentesCercanos(Coordenada coordenada) {
        return manejoIncidente.getListaIncidentesConCoordenada(coordenada);
    }

    /**
     * INICIO METODOS CORRESPONDIENTES A ESTABLECIMIENTOS
     */

    public Establecimiento crearEstablecimiento(String nombre, Categoria categoria, Coordenada coordenada) {

        Establecimiento establecimiento = manejoEstablecimiento.crearEstablecimiento(coordenada, nombre, categoria);

        establecimiento = manejoEstablecimiento.guardarEstablecimiento(establecimiento);

        return establecimiento;
    }

    public Establecimiento obtenerEstablecimiento(Integer id1) {
        return manejoEstablecimiento.getEstablecimiento(id1);
    }

    public List<Establecimiento> obtenerListaEstablecimientos() {
        return manejoEstablecimiento.getListaEstablecimientos();
    }

    public List<Establecimiento> getListaEstablecimientosCercanos(Coordenada coordenada) {
        return manejoEstablecimiento.getListaEstablecimientosConCoordenada(coordenada);
    }

    /**
     * INICIO METODOS CORRESPONDIENTES A CACHE
     */

    public void eliminarCache() {
        CacheSingleton.getInstance().limpiarCache();
    }

    /**
     * INICIO METODOS CORRESPONDIENTES A CATEGORIAS
     */

    public ArrayList<Categoria> getCategorias() {
        return manejoProperties.getCategorias();
    }

    public Map<String, ArrayList<Categoria>> getSubCategorias() {
        return manejoProperties.getSubCategorias();
    }


    public Categoria getCagoriaPorDescripcion(String descripcion){

        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;
        List<String> palabrasDelTexto  = manejoCategoria.getPalabrasDelTexto(descripcion);
        Categoria categoria  = manejoCategoria.buscarCategoria(palabrasDelTexto,categorias);

        return categoria;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    public void eliminarBDFacade(){
        manejoEstablecimiento.eliminar();
        manejoIncidente.eliminarBD();
    }
}
