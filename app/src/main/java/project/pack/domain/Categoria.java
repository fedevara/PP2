package project.pack.domain;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class Categoria implements Serializable {

    private Integer id;
    private String nombre;
    private String[] palabrasClaves;

    public Categoria(){

    }
    public Categoria(Integer id, String nombre) {
        setId(id);
        setNombre(nombre);
    }
    public Categoria(Integer id, String nombre, String[] palabrasClaves) {
        setId(id);
        setNombre(nombre);
        setPalabrasClaves(palabrasClaves);
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

    public String[] getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(String[] palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

}
