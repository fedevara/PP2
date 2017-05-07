package project.pack.domain;

import java.io.Serializable;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class Categoria implements Serializable {

    private Integer id;
    private String nombre;
    private String riesgo;

    public Categoria(Integer id, String nombre, String riesgo) {
        setId(id);
        setNombre(nombre);
        setRiesgo(riesgo);
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
}
