package logic.city;

import utils.math.Position;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class MultiConnection extends Node {

    /**
     * Street crossing contains a StreetlightLogic system
     */
    private StreetlightLogic logic=null;


    public MultiConnection(City parent, Position position, double fame, String id, boolean hasTrafficSignals) {
        super(parent, position, fame, id);
        if (hasTrafficSignals)
            addStreetLightLogic();

    }

    private void addStreetLightLogic() {
        logic = new StreetlightLogic(getId(), this);
        logic.start();
    }

    public void setLogic(StreetlightLogic logic) {
        this.logic = logic;
    }

    public StreetlightLogic getLogic() {
        return logic;
    }

}
