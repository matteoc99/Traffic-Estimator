package logic.city;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class MultiConnection extends Node{

    /**
     * Street crossing contains a StreetlightLogic system
     */
    private StreetlightLogic logic;


    public MultiConnection(City parent, Point position, double fame, String id, StreetlightLogic logic) {
        this(parent, position, fame, id);
        this.logic = logic;
    }

    public MultiConnection(Node node, StreetlightLogic logic) {
        this(node.getParent(), node.getPosition(), node.getFame(), node.getId());
        this.logic = logic;
    }

    public MultiConnection(City parent, Point position, double fame, String id) {
        super(parent, position, fame, id);
    }

    public void setLogic(StreetlightLogic logic) {
        this.logic = logic;
    }

    public StreetlightLogic getLogic() {
        return logic;
    }
}
