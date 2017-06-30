package project.pack.utilities;

/*
 * Created by Federico Vara on 6/5/2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import project.pack.domain.Categoria;

public class CategoriaProperties {

    /* LISTA DE CATEGORIAS GENERALES */
    public static final String[] CATEGORIAS = new String[]{"Transito", "Robo", "Reclamo"};

    /* INCIDENTES DE TRANSITO */
    public static final String[] PALABRAS_CLAVES_TRANSITO= new String[]{"trafico", "recorrido", "muerte","transporte"};
    public final static Categoria TRANSITO = new Categoria(1, "Transito", PALABRAS_CLAVES_TRANSITO);

    /* INCIDENTES DE INSEGURIDAD */
    public static final String[] PALABRAS_CLAVES_ROBO = new String[]{"robo","hurto","saqueo", "arrebatamiento"};
    public final static Categoria ROBO = new Categoria(2, "Robo", PALABRAS_CLAVES_ROBO);

    /* INCIDENTES DE RECLAMOS */
    public static final String[] PALABRAS_CLAVES_RECLAMO = new String[]{"reclamo", "queja", "protesta"};
    public final static Categoria RECLAMO = new Categoria(3, "Reclamo", PALABRAS_CLAVES_RECLAMO);


    /* LISTAS DE TODOS LOS INCIDENTES DE CADA TIPO */
    public static final ArrayList<Categoria> LISTA_TRANSITO = obtenerTransito();
    public static final ArrayList<Categoria> LISTA_INSEGURIDAD = obtenerInseguridad();
    public static final ArrayList<Categoria> LISTA_RECLAMOS = obtenerReclamos();

    /* LISTA DE TODOS LOS INCIDENTES */
    public static final ArrayList<Categoria> LISTA_CATEGORIAS = obtenerCategorias();

    /* LISTAS DE TODOS LOS INCIDENTES DE CADA TIPO */
    public static final String[] LISTA_CATEGORIAS_BAJAS = new String[]{"Reclamo"};
    public static final String[] LISTA_CATEGORIAS_MEDIAS = new String[]{"Transito"};
    public static final String[] LISTA_CATEGORIAS_ALTAS = new String[]{"Robo"};

    /* Indica la diferencia maximo entre incidentes para que se cuenten */
    public static final int DiasMaximoDifIncidente = 30;

    public static final ArrayList<String> CATEGORIAS_BAJAS = new ArrayList<String>(Arrays.asList(LISTA_CATEGORIAS_BAJAS));
    public static final ArrayList<String> CATEGORIAS_MEDIAS = new ArrayList<String>(Arrays.asList(LISTA_CATEGORIAS_MEDIAS));
    public static final ArrayList<String> CATEGORIAS_ALTAS = new ArrayList<String>(Arrays.asList(LISTA_CATEGORIAS_ALTAS));


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
