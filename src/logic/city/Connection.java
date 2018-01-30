package logic.city;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Connection extends Node {
    public Connection(City parent, Point position, double fame, String id) {
        super(parent, position, fame, id);
    }

    @Override
    public void addStreet(Street street) {
        if(getStreets().size()<2)
            super.addStreet(street);
        else
            throw new RuntimeException("TO MANY ADDED");
    }
}
