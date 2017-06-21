package project.pack.controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.facade.Facade;
import project.pack.utilities.CategoriaProperties;

import static org.junit.Assert.*;

/**
 * Created by Administrador on 20/06/2017.
 */
public class ManejoCategoriaTest {
    @Test
    public void getPalabrasDelTexto() throws Exception {

        ManejoCategoria manejoCategoria = new ManejoCategoria();

        String descipcion = "notifico un robo";

        List<String> palabrasDelTexto = new ArrayList<>();

        palabrasDelTexto = manejoCategoria.getPalabrasDelTexto(descipcion);

        assertEquals(palabrasDelTexto.size(),3);
    }

    @Test
    public void buscarCategoria() throws Exception {

        ManejoCategoria manejoCategoria = new ManejoCategoria();

        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;

        List<String> palabrasDelTexto  = new ArrayList<>();
        palabrasDelTexto.add("notifico");
        palabrasDelTexto.add("un");
        palabrasDelTexto.add("robo");

        Categoria categoria = manejoCategoria.buscarCategoria(palabrasDelTexto,categorias);
        assertEquals(categoria.getNombre(),"Robo");
    }

    @Test
    public void buscarCategoriaPorPalabraClave() throws Exception {

        ManejoCategoria manejoCategoria = new ManejoCategoria();
        String[] palabrasClaves = {"robo","hurto"};

        Categoria categoriaRobo = new Categoria();
        categoriaRobo.setNombre("Robo");
        categoriaRobo.setPalabrasClaves(palabrasClaves);

        Categoria categoriaNueva = manejoCategoria.buscarCategoriaPorPalabraClave(categoriaRobo,"hurto");

        assertEquals(categoriaNueva.getNombre(), categoriaRobo.getNombre());
    }

}