package project.pack.logic;

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

    public void getRiesgo(Object object) {
        IRiesgoStrategy.getRiesgo(object);
    }

}
