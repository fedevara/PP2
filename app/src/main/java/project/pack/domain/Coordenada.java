package project.pack.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Federico Vara on 9/4/2017.
 */

public class Coordenada implements Serializable {

    private BigDecimal latitud;
    private BigDecimal longitud;

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }
}
