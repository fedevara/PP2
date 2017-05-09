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
    public final static Categoria CHOQUE = new Categoria(0, "Choque", "10",null);

    public final static Categoria CORTE_CALLE = new Categoria(1, "Corte de Calle", "10",null);

    public final static Categoria CALLE_ROTA = new Categoria(2, "Calle rota", "10",null);

    /* INCIDENTES DE INSEGURIDAD */
    public static final ArrayList<String> PALABRAS_CLAVES = new ArrayList<String>(){};
    public final static Categoria ROBO_ARMADO = new Categoria(0, "Robo a mano armada", "10",null);
    public final static Categoria ARREBATO = new Categoria(1, "Arrebato", "10",null);
    public final static Categoria ROBO_VEHICULO = new Categoria(2, "Robo de vehiculo", "10",null);

    /* INCIDENTES DE RECLAMOS */
    public final static Categoria CORTE_LUZ = new Categoria(0, "Corte de luz", "10",null);
    public final static Categoria ANEGAMIENTO = new Categoria(1, "Anegamientos", "10",null);
    public final static Categoria POZO = new Categoria(2, "Pozo", "10",null);
    public final static Categoria VEHICULO_ABANDONADO = new Categoria(3, "Vehiculo abandonado", "10",null);
    public final static Categoria CAIDA_ARBOL = new Categoria(4, "Caida de arbol", "10",null);

    /* LISTAS DE TODOS LOS INCIDENTES DE CADA TIPO */
    public static final ArrayList<Categoria> LISTA_TRANSITO = obtenerTransito();
    public static final ArrayList<Categoria> LISTA_INSEGURIDAD = obtenerInseguridad();
    public static final ArrayList<Categoria> LISTA_RECLAMOS = obtenerReclamos();

    /* LISTA DE TODOS LOS INCIDENTES */
    public static final ArrayList<Categoria> LISTA_CATEGORIAS = obtenerCategorias();


    /* METODOS PRIVADOS */

    private static ArrayList<Categoria> obtenerTransito() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.add(CHOQUE);
        list.add(CORTE_CALLE);
        list.add(CALLE_ROTA);
        return list;
    }

    private static ArrayList<Categoria> obtenerInseguridad() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.add(ROBO_ARMADO);
        list.add(ARREBATO);
        list.add(ROBO_VEHICULO);
        return list;
    }

    private static ArrayList<Categoria> obtenerReclamos() {
        ArrayList<Categoria> list = new ArrayList<>();
        list.add(CORTE_LUZ);
        list.add(ANEGAMIENTO);
        list.add(POZO);
        list.add(VEHICULO_ABANDONADO);
        list.add(CAIDA_ARBOL);
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
