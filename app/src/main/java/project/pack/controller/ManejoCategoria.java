package project.pack.controller;

import java.util.ArrayList;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.utilities.CategoriaProperties;

/**
 * Created by Administrador on 20/06/2017.
 */

public class ManejoCategoria {


    public List getPalabrasDelTexto(String descripcion) {

        String textoEnMinusculas = descripcion.toLowerCase();

        String[] palabras = textoEnMinusculas.split(" ");

        List<String> palabrasDelTexto = new ArrayList<>();

        for (String palabra: palabras){
            palabrasDelTexto.add(palabra);
        }

        return palabrasDelTexto;
    }

    public Categoria buscarCategoria(List<String> palabrasDelTexto, ArrayList<Categoria> categorias) {

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
/*
    public static void main (String [ ] args) {

        ManejoCategoria manejo = new ManejoCategoria();

        String descripcion = "notifico un rovo";

        List<String> arrayPalabrasDelTexto = manejo.getPalabrasDelTexto(descripcion);

        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;

        Categoria categoria  = manejo.buscarCategoria(arrayPalabrasDelTexto,categorias);
    }*/


}
