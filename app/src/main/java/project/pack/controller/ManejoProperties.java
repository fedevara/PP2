package project.pack.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import project.pack.domain.Categoria;
import project.pack.utilities.PropertyReader;

/**
 * Created by Federico Vara on 16/4/2017.
 */

public class ManejoProperties {

    private PropertyReader propertyReader;
    private HashMap<String, Properties> properties;

    private static ManejoProperties INSTANCE;

    public static ManejoProperties getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ManejoProperties();
        return INSTANCE;
    }

    private ManejoProperties() {
    }

    public void initProperties(Context context) {

        // Ejemplo de uso de properties
        //properties.getProperty("Name")
        //properties.getProperty("Designation")

        propertyReader = new PropertyReader(context);
        properties = new HashMap<String, Properties>();

        properties.put("Categoria", propertyReader.getMyProperties("categoriaProperties.properties"));
    }

    /**
     * Devuelve el conntenido del archivo de properties solicitado.
     * Las Key de las properties guardadas son:
     * -Categoria
     *
     * @param property es el String por el cual busca las properties
     * @return El objeto {@link Properties} con los datos solicitados
     */
    private Properties getProperties(String property) {
        return properties.get(property);
    }

    public ArrayList<Categoria> getCategorias() {
        Properties properties = getProperties("Categoria");
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        Integer index = 0;
        for (Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iterator.next();
            String property = (String) entry.getKey();
            String[] separated = property.split("\\.");
            if (separated[0].equals("incidente") && separated[1].equals("categoria")) {
                categorias.add(new Categoria(index, (String) entry.getValue(), (String) entry.getKey()));
            }
            index++;
        }
        return categorias;
    }

    public Map<String, ArrayList<Categoria>> getSubCategorias() {
        Properties properties = getProperties("Categoria");
        Map<String, ArrayList<Categoria>> subcategorias = new HashMap<String, ArrayList<Categoria>>();
        ArrayList<Categoria> transito = new ArrayList<Categoria>();
        ArrayList<Categoria> robo = new ArrayList<Categoria>();
        ArrayList<Categoria> reclamo = new ArrayList<Categoria>();
        int index = 0;
        for (Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iterator.next();
            String property = (String) entry.getKey();
            String[] separated = property.split("\\.");
            if (separated[0].equals("incidente") && separated[1].equals("transito")) {
                Categoria aux = new Categoria(null, null, null);
                String[] separatedAux = ((String) entry.getValue()).split("\\.");
                aux.setId(index);
                aux.setNombre("Transito");
                aux.setSubCategoria(separatedAux[0]);
                aux.setPropertyCode((String) entry.getKey());
                aux.setRiesgo(separatedAux[1]);
                transito.add(aux);
                index++;
            } else if (separated[0].equals("incidente") && separated[1].equals("robo")) {
                Categoria aux = new Categoria(null, null, null);
                String[] separatedAux = ((String) entry.getValue()).split("\\.");
                aux.setId(index);
                aux.setNombre("Robo");
                aux.setSubCategoria(separatedAux[0]);
                aux.setPropertyCode((String) entry.getKey());
                aux.setRiesgo(separatedAux[1]);
                robo.add(aux);
                index++;
            } else if (separated[0].equals("incidente") && separated[1].equals("reclamo")) {
                Categoria aux = new Categoria(null, null, null);
                String[] separatedAux = ((String) entry.getValue()).split("\\.");
                aux.setId(index);
                aux.setNombre("Reclamo");
                aux.setSubCategoria(separatedAux[0]);
                aux.setPropertyCode((String) entry.getKey());
                aux.setRiesgo(separatedAux[1]);
                reclamo.add(aux);
                index++;
            }
        }
        subcategorias.put("Transito", transito);
        subcategorias.put("Robo", robo);
        subcategorias.put("Reclamo", reclamo);
        return subcategorias;
    }
}