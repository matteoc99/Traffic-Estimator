package logic.vehicles;

import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Bus extends PublicVehicle{
    /**
     * line of the bus
     */
    int line;
    /**
     * length of the bus
     */
    int length;

    public Bus(int weight, int maxSpeed, Path path, int streetKnowledge, int speeder, int line, int length) {
        super(weight, maxSpeed, path, streetKnowledge, speeder);
        this.line = line;
        this.length = length;
    }


}
