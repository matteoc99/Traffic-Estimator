package logic.vehicles;

import logic.PositionableComponent;
import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Vehicle extends PositionableComponent{

    /**
     * lane where the car is currently driving
     */
    private Lane lane;

    private int weight;
    private int maxSpeed;
    /**
     * 0-lane.length
     * position on lane
     */
    private int progressInLane;
    /**
     * lane.length
     */
    private int maxProgress;

    private Path path;

    /**
     * 0-1 knowledge of all the streets in BZ
     */
    int streetKnowledge;

    /**
     * 0-1 character of the driver. 1 if he is really fast and careless. 0  : safe driver
     */
    int speeder;

}
