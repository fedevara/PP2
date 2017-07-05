package project.pack.domain;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class Categoria implements Serializable {

    private Integer id;
    private String nombre;

    public Categoria(){}

    public Categoria(Integer id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}
