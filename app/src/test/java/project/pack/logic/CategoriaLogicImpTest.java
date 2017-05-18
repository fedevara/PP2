package project.pack.logic;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import project.pack.domain.Categoria;
import project.pack.logicImp.CategoriaLogicImp;
import project.pack.utilities.CategoriaProperties;

import static org.junit.Assert.*;

/**
 * Created by Administrador on 10/05/2017.
 */
public class CategoriaLogicImpTest {
    @Test
    public void searchCategoria() throws Exception {

        CategoriaLogic categoriaLogic;
        categoriaLogic = new CategoriaLogicImp();

        String[] palabrasClaves = {"robo","hurto"};
        Categoria categoria = new Categoria(1, "Robo", "riesgo",palabrasClaves);

        Categoria categoriaReturn = null;

        //TEST 1
        String descripcion = "hurto";
        categoriaReturn = categoriaLogic.searchCategoria(categoria,descripcion);
        assertNotNull(categoriaReturn);
        assertEquals("Robo", categoriaReturn.getNombre());

        // TEST 2
        String descripcion2 = "hola";
        Categoria categoriaReturnTest2 = null;
        categoriaReturnTest2 = categoriaLogic.searchCategoria(categoria,descripcion2);
        assertNull(categoriaReturnTest2.getId());

    }

    @Test
    public void searchWorldTest() throws Exception {

        CategoriaLogic categoriaLogic;
        categoriaLogic = new CategoriaLogicImp();

        String[] arrayPalabrasDelTexto = {"notifico","un","robo"};
        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;
        Categoria categoria = categoriaLogic.searchWorld(arrayPalabrasDelTexto,categorias);

        assertEquals(categoria.getNombre(),"Robo");

        //Detecto la primer categoria encontrada:
        String[] arrayPalabrasDelTexto2 = {"notifico","una","queja", "de","robo"};
        Categoria categoria2 = categoriaLogic.searchWorld(arrayPalabrasDelTexto2,categorias);

        assertEquals(categoria2.getNombre(),"Reclamo");

    }

    @Test
    public void getCategorias() throws Exception {
        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;
        //Valido que no sea NULO
        assertNotNull(categorias);
        //Valido que el tamaño sea mayor a 0
        assertTrue(categorias.size()>0);
    }

    @Test
    public void getArrayTest() throws Exception {

        CategoriaLogic categoriaLogic;
        categoriaLogic = new CategoriaLogicImp();

        String texto = "notifico un robo";
        String[] arrayPalabrasDelTexto = categoriaLogic.getArray(texto);

        assertEquals(arrayPalabrasDelTexto.length,3);

    }

}