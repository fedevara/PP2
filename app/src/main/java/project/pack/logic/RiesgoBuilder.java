package project.pack.logic;

import project.pack.domain.AbstractUbicacion;

/**
 * Created by sgarcete on 4/16/17.
 */

public class RiesgoBuilder {

    IRiesgoStrategy IRiesgoStrategy;

    public RiesgoBuilder(IRiesgoStrategy IRiesgoStrategy) {
        this.IRiesgoStrategy = IRiesgoStrategy;
    }

    public void setStrategy(IRiesgoStrategy IRiesgoStrategy) {
        this.IRiesgoStrategy = IRiesgoStrategy;
    }

    public String getRiesgo(AbstractUbicacion ubicacion) {
        return IRiesgoStrategy.getRiesgo(ubicacion);
    }

}
