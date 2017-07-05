package project.pack.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import project.pack.domain.Categoria;
import project.pack.logic.ManejoCategoria;
import project.pack.utilities.CategoriaProperties;

/**
 * Created by Administrador on 20/06/2017.
 */

public class CategoriaController {

    public Categoria buscarCategoriaPorDescripcion(String descripcion) {
        ManejoCategoria manejoCategoria = new ManejoCategoria();

        Set<String> palabrasDelTexto = manejoCategoria.obtenerPalabrasDelTexto(descripcion);

        Categoria categoriaEncontrada  = manejoCategoria.buscarCategoria(palabrasDelTexto);

        return categoriaEncontrada;
    }
}
