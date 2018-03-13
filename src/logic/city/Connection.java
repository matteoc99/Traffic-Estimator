package logic.city;

import utils.math.Position;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Connection extends Node {

    public Connection(Node node) {
        this(node.getParent(), node.getPosition(), node.getFame(), node.getId());
    }

    public Connection(City parent, Position position, double fame, String id) {
        super(parent, position, fame, id);
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
}
