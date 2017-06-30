package project.pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import project.pack.domain.Categoria;
import project.pack.facade.Facade;
import project.pack.utilities.CategoriaProperties;

/**
 * Created by Administrador on 20/06/2017.
 */

public class ManejoCategoria {

    public ArrayList<Categoria> getCategorias(){
        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;
        return categorias;
    }

    public Set getPalabrasDelTexto(String descripcion) {

        String textoEnMinusculas = descripcion.toLowerCase();

        String[] palabras = textoEnMinusculas.split(" ");

        Set<String> palabrasDelTexto = new HashSet<>();

        for (String palabra: palabras){
            palabrasDelTexto.add(palabra);
        }

        return palabrasDelTexto;
    }

    public Categoria buscarCategoria(Set<String> palabrasDelTexto, ArrayList<Categoria> categorias) {

        Categoria nuevaCategoria = null;

        for (String palabra: palabrasDelTexto) {

            for (Categoria categoria: categorias) {
                nuevaCategoria = buscarCategoriaPorPalabraClave(categoria,palabra);
                if(nuevaCategoria.getId()!=null)
                    break;
                else
                    continue;
            }
            if(nuevaCategoria.getId()!=null)
                break;
            else
                continue;
        }
        return nuevaCategoria;
    }

    public Categoria buscarCategoriaPorPalabraClave(Categoria cat, String palabraClave) {

        Categoria categoria = new Categoria();

        for (int i=0; i<cat.getPalabrasClaves().length;i++){
            if(cat.getPalabrasClaves()[i].equals(palabraClave)){
                categoria = cat;
            }
        }
        return categoria;
    }

    public Categoria buscarCategoriaMejorado(String descripcion) {

        ArrayList<Categoria> categorias = getCategorias();

        Set<String> palabrasDelTexto = getPalabrasDelTexto(descripcion);

        Categoria categoria  = buscarCategoria(palabrasDelTexto,categorias);

        return categoria;
    }

}
