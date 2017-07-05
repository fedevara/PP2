package project.pack.controller;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import project.pack.domain.Categoria;
import project.pack.facade.Facade;
import project.pack.logic.ManejoCategoria;

import static org.junit.Assert.*;

/**
 * Created by Administrador on 20/06/2017.
 */
public class ManejoCategoriaTest {
    @Test
    public void getPalabrasDelTexto() throws Exception {

        ManejoCategoria manejoCategoria = new ManejoCategoria();

        String descipcion = "notifico un robo";

        Set<String> palabrasDelTexto = new HashSet<>();

        palabrasDelTexto = manejoCategoria.obtenerPalabrasDelTexto(descipcion);

        assertEquals(palabrasDelTexto.size(),3);
    }

    @Test
    public void buscarCategoria() throws Exception {

        String descripcion = "notifico un robo";

        Categoria categoria = Facade.getInstance().obtenerCagoriaPorDescripcion(descripcion);

        assertEquals(categoria.getNombre(),"Robo");
    }

    @Test
    public void buscarCategoriaPorPalabraClave() throws Exception {

        ManejoCategoria  manejoCategoria= new ManejoCategoria();
        String[] palabrasClaves = {"robo","hurto"};

        Categoria categoriaRobo = new Categoria();
        categoriaRobo.setNombre("Robo");

        Categoria categoriaNueva = manejoCategoria.buscarCategoriaPorPalabraClave("hurto");

        assertEquals(categoriaNueva.getNombre(), categoriaRobo.getNombre());
    }

}