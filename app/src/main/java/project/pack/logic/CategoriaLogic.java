package project.pack.logic;

import java.util.ArrayList;

import project.pack.domain.Categoria;

/**
 * Created by Administrador on 08/05/2017.
 */

public interface CategoriaLogic {

    Categoria searchCategoria(Categoria cat, String palabra);

    Categoria searchWorld(String[] arrayPalabrasDelTexto, ArrayList<Categoria> categorias);

    String[] getArray(String texto);

}
