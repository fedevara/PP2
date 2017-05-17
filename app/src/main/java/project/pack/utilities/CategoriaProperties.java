package project.pack.utilities;

/*
 * Created by Federico Vara on 6/5/2017.
 */

import java.util.ArrayList;

import project.pack.domain.Categoria;

public class CategoriaProperties {

    /* LISTA DE CATEGORIAS GENERALES */
    public static final String[] CATEGORIAS = new String[]{"Transito", "Robo", "Reclamo"};

    /* INCIDENTES DE TRANSITO */
    public static final String[] PALABRAS_CLAVES_TRANSITO= new String[]{"trafico", "recorrido", "muerte","transporte"};
    public final static Categoria TRANSITO = new Categoria(0, "Transito", "10",PALABRAS_CLAVES_TRANSITO);

    /* INCIDENTES DE INSEGURIDAD */
    public static final String[] PALABRAS_CLAVES_ROBO = new String[]{"robo","hurto","saqueo", "arrebatamiento"};
    public final static Categoria ROBO = new Categoria(0, "Robo", "10",PALABRAS_CLAVES_ROBO);

    /* INCIDENTES DE RECLAMOS */
    public static final String[] PALABRAS_CLAVES_RECLAMO = new String[]{"reclamo", "queja", "protesta"};
    public final static Categoria RECLAMO = new Categoria(0, "Reclamo", "10",PALABRAS_CLAVES_RECLAMO);


    /* LISTAS DE TODOS LOS INCIDENTES DE CADA TIPO */
    public static final ArrayList<Categoria> LISTA_TRANSITO = obtenerTransito();
    public static final ArrayList<Categoria> LISTA_INSEGURIDAD = obtenerInseguridad();
    public static final ArrayList<Categoria> LISTA_RECLAMOS = obtenerReclamos();

    /* LISTA DE TODOS LOS INCIDENTES */
    public static final ArrayList<Categoria> LISTA_CATEGORIAS = obtenerCategorias();


    /* METODOS PRIVADOS */

    private static ArrayList<Categoria> obtenerTransito() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.add(TRANSITO);
        return list;
    }

    private static ArrayList<Categoria> obtenerInseguridad() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.add(ROBO);
        return list;
    }

    private static ArrayList<Categoria> obtenerReclamos() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.add(RECLAMO);
        return list;
    }

    private static ArrayList<Categoria> obtenerCategorias() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.addAll(LISTA_TRANSITO);
        list.addAll(LISTA_INSEGURIDAD);
        list.addAll(LISTA_RECLAMOS);
        return list;
    }

    public ArrayList<Categoria> obtenerCategorias(String categoria) {
        if(categoria.equals("Transito")) {
            return LISTA_TRANSITO;
        }
        if(categoria.equals("Robo")) {
            return LISTA_INSEGURIDAD;
        }
        if(categoria.equals("Reclamo")) {
            return LISTA_RECLAMOS;
        }
        return new ArrayList<>();
    }
}
