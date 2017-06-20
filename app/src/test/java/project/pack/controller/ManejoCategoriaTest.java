package project.pack.controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import project.pack.facade.Facade;
import project.pack.logic.CategoriaLogic;
import project.pack.logicImp.CategoriaLogicImp;

import static org.junit.Assert.*;

/**
 * Created by Administrador on 20/06/2017.
 */
public class ManejoCategoriaTest {
    @Test
    public void getPalabrasDelTexto() throws Exception {

        String descipcion = "notifico un robo";

        List<String> palabrasDelTexto = new ArrayList<>();

        palabrasDelTexto = (List<String>) Facade.getInstance().getCagoriaPorDescripcion(descipcion);

        assertEquals(palabrasDelTexto.size(),3);
    }

    @Test
    public void buscarCategoria() throws Exception {

    }

    @Test
    public void buscarCategoriaPorPalabraClave() throws Exception {

    }

}