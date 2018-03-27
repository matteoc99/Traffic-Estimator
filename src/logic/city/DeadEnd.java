package logic.city;

import utils.math.Position;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class DeadEnd extends Node {

    public DeadEnd(Node node) {
        this(node.getParent(), node.getPosition(), node.getFame(), node.getId());
    }

    public DeadEnd(City parent, Position position, double fame, String id) {
        super(parent, position, fame, id);
    }


    /**
     * can add only one street, because this node is a dead end
     */
    @Override
    public void addStreet(Street street) {
        if (getStreetSize() == 0)
            super.addStreet(street);
        else
            throw new RuntimeException("TO MANY ADDED");
    }
}
