package logic.vehicles;

import logic.city.City;
import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class PrivateVehicle extends Vehicle {

    public PrivateVehicle(int weight, int maxSpeed, Path path, int streetKnowledge, int speeder, City city) {
        super(weight, maxSpeed, path, streetKnowledge, speeder, city);
    }
}
