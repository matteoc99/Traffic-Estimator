package logic.city;

import logic.PositionableComponent;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetIntersection extends PositionableComponent {
    /**
     * Connects Street
     */
    ArrayList<Street> streets;

    /**
     * fame of this Intersection as Path start or goal
     */
    int fame;

    /**
     * UNIQUE IDENTIFICATION STRING
     */
    String UIS;

    int index;

}
