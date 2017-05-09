package project.pack.domain;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class Categoria implements Serializable {

    private Integer id;
    private String nombre;
    private String riesgo;
    private String[] palabrasClaves;

    public Categoria(){

    }

    public Categoria(Integer id, String nombre, String riesgo, String[] palabrasClaves) {
        setId(id);
        setNombre(nombre);
        setRiesgo(riesgo);
        setPalabrasClaves(palabrasClaves);
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getRiesgo() {
        return riesgo;
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
