package logic.city;

import utils.math.Position;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Connection extends Node {

    /**
     * Street crossing contains a StreetlightLogic system
     */
    private StreetlightLogic logic=null;

    public Connection(City parent, Position position, double fame, String id, boolean hasTrafficSignals) {
        super(parent, position, fame, id);
        if (hasTrafficSignals)
            addStreetLightLogic();

    }

    /**
     * call before parse streets in xml
     */
    public void addStreetLightLogic() {
        logic = new StreetlightLogic(getId(), this);
        logic.start();
    }


    /**
     * can add only two streets, because this node is a connection
     * connections are used to implement turns
     *
     * @param street the two streets that are connected together
     */
    @Override
    public void addStreet(Street street) {
        if (getStreetSize() < 2)
            super.addStreet(street);
        else
            throw new RuntimeException("TO MANY ADDED");
    }

    public StreetlightLogic getLogic() {
        return logic;
    }
}
