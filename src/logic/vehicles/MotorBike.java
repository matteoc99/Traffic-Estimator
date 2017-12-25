package logic.vehicles;

import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class MotorBike extends PrivateVehicle {
    public MotorBike(Lane lane, int weight, int maxSpeed, Path path, int streetKnowledge, int speeder) {
        super(lane, weight, maxSpeed, path, streetKnowledge, speeder);
    }

    public MotorBike(Lane lane, int weight, int maxSpeed, int streetKnowledge, int speeder) {
        super(lane, weight, maxSpeed, streetKnowledge, speeder);
    }

    public MotorBike(Lane lane, int maxSpeed, int streetKnowledge, int speeder) {
        super(lane, maxSpeed, streetKnowledge, speeder);
    }
}
