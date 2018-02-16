package logic.vehicles;

import logic.city.City;
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


    public Bus(int weight, int maxSpeed, Path path, int streetKnowledge, int speeder, City city) {
        super(weight, maxSpeed, path, streetKnowledge, speeder, city);
    }
    // TODO: 16.02.2018 set line& length
}
