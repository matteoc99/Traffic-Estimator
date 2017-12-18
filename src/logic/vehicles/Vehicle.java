package logic.vehicles;

import logic.PositionableComponent;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Vehicle extends PositionableComponent{

    private int weight;
    private int maxSpeed;
    /**
     * 0-lane.length
     * position on lane
     */
    private int progressInLane;
    private Path path;

    /**
     * 0-1 knowlege of all the streets in BZ
     */
    int knowlege;

    /**
     * 0-1 character of the driver. 1 if he is really fast and careless. 0  : safe driver
     */
    int speeder;

}
