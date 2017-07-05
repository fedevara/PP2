package project.pack.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.pack.domain.Categoria;

/*
 * Created by Federico Vara on 16/4/2017.
 */

public class ManejoProperties {

    public ManejoProperties() {
    }

    /* PROPERTIES DE CATEGORIAS */

    public ArrayList<Categoria> getCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        for (int i = 0; i < CategoriaProperties.CATEGORIAS.length; i++){
            categorias.add(new Categoria(i, CategoriaProperties.CATEGORIAS[i]));
        }
        return categorias;
    }

    public Map<String, ArrayList<Categoria>> getSubCategorias() {
        Map<String, ArrayList<Categoria>> subcategorias = new HashMap<>();
        CategoriaProperties catProperties = new CategoriaProperties();
        for (int i = 0; i < CategoriaProperties.CATEGORIAS.length; i++){
            subcategorias.put(CategoriaProperties.CATEGORIAS[i], catProperties.obtenerCategorias(CategoriaProperties.CATEGORIAS[i]));
        }
        return subcategorias;
    }

}