package logic.vehicles;

import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Car extends PrivateVehicle{
    public Car( int weight, int maxSpeed, Path path, int streetKnowledge, int speeder) {
        super(weight, maxSpeed, path, streetKnowledge, speeder);
    }

 }
