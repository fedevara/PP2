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

        String[] palabrasClaves = {"robo","hurto"};
        Categoria categoria = new Categoria(1, "Robo", "riesgo",palabrasClaves);
        Categoria categoriaReturn = null;

        //TEST 1
        String palabraTest = "hurto";
        for (int i=0; i<categoria.getPalabrasClaves().length;i++){
            if(categoria.getPalabrasClaves()[i].equals(palabraTest)){
                categoriaReturn = categoria;
            }
        }
        assertNotNull(categoriaReturn);

        // TEST 2
        String palabraTest2 = "hola";
        Categoria categoriaReturnTest2 = null;

        for (int i=0; i<categoria.getPalabrasClaves().length;i++){
            if(categoria.getPalabrasClaves()[i].equals(palabraTest2)){
                categoriaReturnTest2 = categoria;
            }
        }
        assertNull(categoriaReturnTest2);

    }

    @Test
    public void searchWorldTest() throws Exception {

        CategoriaLogic categoriaLogic;
        categoriaLogic = new CategoriaLogicImp();

        String[] arrayPalabrasDelTexto = {"notifico","un","robo"};
        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;
        Categoria categoria = categoriaLogic.searchWorld(arrayPalabrasDelTexto,categorias);

        assertEquals(categoria.getNombre(),"Robo");

    }

    // Meotodo que me devuelve la categoria, necesario para el metodo serchWorld
    private Categoria searchCategoria(Categoria cat, String palabra) {
        Categoria categoria = new Categoria();

        for (int i=0; i<cat.getPalabrasClaves().length;i++){
            if(cat.getPalabrasClaves()[i].equals(palabra)){
                categoria = cat;
            }
        }
        return categoria;
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