package project.pack.logic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import project.pack.domain.Categoria;
import project.pack.utilities.CategoriaProperties;

public class ManejoCategoria {

    public Set obtenerPalabrasDelTexto(String descripcion) {

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
        CategoriaProperties categoriaProperties = new CategoriaProperties();
        Categoria categoria = new Categoria();

        Map<Categoria,Set<String>> palabrasClaves = categoriaProperties.obtenerPalabrasClaves();

        for (Map.Entry<Categoria,Set<String>> e: palabrasClaves.entrySet()) {
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
}
