package project.pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

    public Map getCategoriasMejorado(){
        Map<Categoria,Set<String>> palabrasClaves = new HashMap<>();

        Categoria categoriaTransito= new Categoria(1, "Transito");
        Set<String> palabrasTransito = new HashSet<>();

        palabrasTransito.add("trafico");
        palabrasTransito.add("recorrido");
        palabrasTransito.add("muerte");
        palabrasTransito.add("transporte");

        palabrasClaves.put(categoriaTransito, palabrasTransito);

        Categoria categoriaReclamo= new Categoria(2, "Reclamo");
        Set<String> palabrasReclamo = new HashSet<>();

        palabrasReclamo.add("reclamo");
        palabrasReclamo.add("queja");
        palabrasReclamo.add("protesta");

        palabrasClaves.put(categoriaReclamo, palabrasReclamo);

        Categoria categoriaRobo = new Categoria(3, "Robo");
        Set<String> palabrasRobo = new HashSet<>();

        palabrasRobo.add("robo");
        palabrasRobo.add("hurto");
        palabrasRobo.add("saqueo");
        palabrasRobo.add("arrebatamiento");

        palabrasClaves.put(categoriaRobo, palabrasRobo);

        return palabrasClaves;
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

    public Categoria buscarCategoria(Set<String> palabrasDelTexto) {

        Categoria nuevaCategoria = null;

        for (String palabra: palabrasDelTexto) {
                nuevaCategoria = buscarCategoriaPorPalabraClave(palabra);
                if(nuevaCategoria.getId()!=null)
                    break;
                else
                    continue;
        }
        return nuevaCategoria;
    }

    public Categoria buscarCategoriaPorPalabraClave(String palabraClave) {

        Categoria categoria = new Categoria();
        Map<Categoria,Set<String>> categorias = getCategoriasMejorado();

        for (Map.Entry<Categoria,Set<String>> e: categorias.entrySet()) {

            Iterator<String> iterator =((HashSet<String>) e.getValue()).iterator();
            String palabraClaveValue= null;
            while(iterator.hasNext()){
                palabraClaveValue = iterator.next();
                if(palabraClaveValue.equals(palabraClave)){
                    categoria = e.getKey();
                }
            }
        }
        return categoria;
    }

    public Categoria buscarCategoriaMejorado(String descripcion) {

        Set<String> palabrasDelTexto = getPalabrasDelTexto(descripcion);

        Categoria categoria  = buscarCategoria(palabrasDelTexto);

        return categoria;
    }

    public static void main (String [ ] args) {
        ManejoCategoria manejoCategoria = new ManejoCategoria();
        Categoria categoria = manejoCategoria.buscarCategoriaMejorado("notifico robo una");

    }
}
