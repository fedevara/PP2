package project.pack.logicImp;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import project.pack.domain.Categoria;
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
    public void searchWorld() throws Exception {

        //CASO 1
        String[] arrayPalabrasDelTexto = {"notifico","un","robo"};
        Categoria nuevaCategoria = null;
        ArrayList<Categoria> categorias = CategoriaProperties.LISTA_CATEGORIAS;

        for (String palabra: arrayPalabrasDelTexto) {

            for (Categoria categoria: categorias) {
                nuevaCategoria = searchCategoria(categoria,palabra);
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
        assertNotNull(nuevaCategoria);

        //CASO 2
        String[] arrayPalabrasDelTextoCaso2 = {"notifico","un","roVo"};
        Categoria nuevaCategoriaCaso2 = null;
        ArrayList<Categoria> categoriasCaso2 = CategoriaProperties.LISTA_CATEGORIAS;

        for (String palabra: arrayPalabrasDelTextoCaso2) {

            for (Categoria categoria: categoriasCaso2) {
                nuevaCategoriaCaso2 = searchCategoria(categoria,palabra);
                if(nuevaCategoriaCaso2.getId()!=null)
                    break;
                else
                    continue;
            }
            if(nuevaCategoriaCaso2.getId()!=null)
                break;
            else
                continue;
        }
        assertNull(nuevaCategoriaCaso2.getId());
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
    public void getArray() throws Exception {

        String texto = "Notifico un RoBo";

        String textoEnMinusculas = texto.toLowerCase();

        String[] arrayPalabrasDelTexto = textoEnMinusculas.split(" ");

        //Comparo el tamaño del arreglo con la cantidad de palabras del texto.
        Assert.assertEquals(arrayPalabrasDelTexto.length,3);
        //comparo el resultado convertido en minusculas con un texto igual.
        assertTrue(textoEnMinusculas.equals("notifico un robo"));
    }

}