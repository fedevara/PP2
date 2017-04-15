package project.pack.facade;

import android.content.Context;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import project.pack.controller.ManejoEstablecimiento;
import project.pack.controller.ManejoIncidente;
import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.domain.Incidente;
import project.pack.utilities.PropertyReader;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Facade {

    private ManejoIncidente manejoIncidente;
    private ManejoEstablecimiento manejoEstablecimiento;

    private PropertyReader propertyReader;
    private Properties categorias;

    public Facade() {
        manejoIncidente = new ManejoIncidente();
        manejoEstablecimiento = new ManejoEstablecimiento();
    }

    public Incidente crearIncidente(Integer id, String titulo, String descripcion, Date fecha, Categoria categoria, Coordenada lugar){
        Integer idIncidente = id;
        Incidente incidente = manejoIncidente.crearIncidente(idIncidente,titulo, descripcion, fecha, categoria, lugar);
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

    public List<Incidente> obtenerListaIncidentes(){
        List<Incidente> listaIncidentes=manejoIncidente.getListaIncidentes();
        return listaIncidentes;
    }

    public void eliminarCache(){
        manejoIncidente.eliminarCache();
    }

    public void initProperties(Context context) {

        // Ejemplo de uso de properties
        //properties.getProperty("Name")
        //properties.getProperty("Designation")

        propertyReader = new PropertyReader(context);
        categorias = propertyReader.getMyProperties("categoriaProperties.properties");
    }
}
