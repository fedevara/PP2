package project.pack.logicImp;

import java.util.ArrayList;

import project.pack.domain.Categoria;
import project.pack.logic.CategoriaLogic;
import project.pack.utilities.CategoriaProperties;

/**
 * Created by Administrador on 08/05/2017.
 */

public class CategoriaLogicImp implements CategoriaLogic{

    @Override
    public Categoria searchCategoria(Categoria cat, String palabra) {

        Categoria categoria = new Categoria();

        for (int i=0; i<cat.getPalabrasClaves().length;i++){
            if(cat.getPalabrasClaves()[i].equals(palabra)){
                categoria = cat;
            }
        }
        return categoria;
    }

    @Override
    public Categoria searchWorld(String[] arrayPalabrasDelTexto, ArrayList<Categoria> categorias) {

        Categoria nuevaCategoria = new Categoria();

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
        return nuevaCategoria;
    }

    public ArrayList<Categoria> getCategorias(){

        //CATEGORIAS
        String[] palabrasClavesRobo = new String[]{"robo", "hurto", "arrebatamiento","saqueo"};
        Categoria categoriaRobo = new Categoria(1,"Robo", "riegos",palabrasClavesRobo);


        String[] palabrasClavesReclamo = new String[]{"reclamo", "queja", "protesta"};
        Categoria categoriaReclamo = new Categoria(2,"Reclamo", "riego", palabrasClavesReclamo);

        //Array de Categorias
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        categorias.add(categoriaRobo);
        categorias.add(categoriaReclamo);

        return categorias;
    }

    @Override
    public String[] getArray(String texto) {

        String textoEnMinusculas = texto.toLowerCase();

        String[] arrayPalabrasDelTexto = textoEnMinusculas.split(" ");

        return arrayPalabrasDelTexto;
    }

}
