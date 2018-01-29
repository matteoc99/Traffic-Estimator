package logic.city;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class DeadEnd extends Node {


    public DeadEnd(City parent, Point position, double fame, String id) {
        super(parent, position, fame, id);
    }

    @Override
    public void addStreet(Street street) {
        if (getStreets().isEmpty())
            super.addStreet(street);
        else
            System.out.println("TO MANY ADDED");
    }
}
