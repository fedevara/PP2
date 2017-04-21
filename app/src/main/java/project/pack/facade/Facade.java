package project.pack.facade;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import project.pack.controller.CacheSingleton;
import project.pack.controller.ManejoEstablecimiento;
import project.pack.controller.ManejoIncidente;
import project.pack.utilities.ManejoProperties;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Facade {

    private ManejoIncidente manejoIncidente;
    private ManejoEstablecimiento manejoEstablecimiento;
    private ManejoProperties manejoProperties;

    private static Facade INSTANCE;

    public static Facade getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Facade();
        return INSTANCE;
    }

    private Facade() {
        manejoIncidente = new ManejoIncidente();
        manejoEstablecimiento = new ManejoEstablecimiento();
        manejoProperties = ManejoProperties.getInstance();
    }

    /**
     * Crea un incidente y lo guarda en la memoria cache
     *
     * @param id          Es el id con el que se va a guardar en la base de datos
     * @param titulo      Titulo del incidente
     * @param descripcion Descripcion del incidente
     * @param fecha       Fecha en la que sucesio el incidente, puede ser distinta a la actual
     * @param categoria   Categoria del incidente
     * @param lugar       Lugar donde ocurrio el incidente
     * @return Devuelve el objeto creado y guardado en la base
     */
    public Incidente crearIncidente(Integer id, String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada lugar) {
        Integer idIncidente = id;
        Incidente incidente = manejoIncidente.crearIncidente(idIncidente, titulo, descripcion, fecha, categoria, lugar);
        manejoIncidente.guardarIncidente(incidente);
        return incidente;
    }

    public Incidente obtenerIncidente(Integer id1) {
        return manejoIncidente.getIncidente(id1);
    }

    public void crearEstablecimiento(String nombre, Categoria categoria, Coordenada lugar) {
        Establecimiento establecimiento = manejoEstablecimiento.crearEstacimiento(nombre, categoria, lugar);
        manejoEstablecimiento.guardarEstablecimiento(establecimiento);
    }

    public Establecimiento obtenerEstablecimiento(Integer id1) {
        return manejoEstablecimiento.getEstablecimiento(id1);
    }

    public List<Incidente> obtenerListaIncidentes() {
        List<Incidente> listaIncidentes = manejoIncidente.getListaIncidentes();
        return listaIncidentes;
    }

    public List<Incidente> getListaIncidentesCercanos(Coordenada coordenada) {
        return manejoIncidente.getListaIncidentesConCoordenada(coordenada);
    }

    public List<Establecimiento> getListaEstablecimientosCercanos(Coordenada coordenada) {
        return manejoEstablecimiento.getListaEstablecimientosConCoordenada(coordenada);
    }

    public void eliminarCache() {
        CacheSingleton.getInstance().limpiarCache();
    }

    public ArrayList<Categoria> getCategorias() {
        return manejoProperties.getCategorias();
    }

    public Map<String, ArrayList<Categoria>> getSubCategorias() {
        return manejoProperties.getSubCategorias();
    }

    public void initProperties(Context context) {
        manejoProperties.initProperties(context);
    }

    public int getDiasMaximo() {
        return manejoProperties.getInstance().getDiasmax();
    }

    public List<Establecimiento> obtenerListaEstablecimientos() {
        List<Establecimiento> listaEstablecimientos = manejoEstablecimiento.getListaEstablecimientos();
        return listaEstablecimientos;
    }


}
