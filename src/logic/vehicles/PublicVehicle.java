package logic.vehicles;

import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class PublicVehicle extends Vehicle {

    /**
     * How much priority this vehicle has
     * 0-1
     * 1 max
     */
    int priorty;

    public PublicVehicle(int weight, int maxSpeed, Path path, int streetKnowledge, int speeder) {
        super( weight, maxSpeed, path, streetKnowledge, speeder);
    }

   }
