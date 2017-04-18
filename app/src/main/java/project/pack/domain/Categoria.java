package project.pack.domain;

import java.io.Serializable;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Categoria implements Serializable {

    private Integer id;
    private String nombre;
    private String subCategoria;
    private String propCode;
    private String riesgo;

    public Categoria(Integer id, String nombre, String propCode) {
        setId(id);
        setNombre(nombre);
        setPropertyCode(propCode);
    }

    public Categoria(Integer id, String nombre, String subCategoria, String propCode, String riesgo) {
        setId(id);
        setNombre(nombre);
        setSubCategoria(subCategoria);
        setPropertyCode(propCode);
        setRiesgo(riesgo);
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

    public String getPropertyCode() {
        return propCode;
    }

    public void setPropertyCode(String javaCode) {
        this.propCode = javaCode;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String toString() {
        return nombre;
    }
}
