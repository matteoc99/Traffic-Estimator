package logic.city;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Accident extends Congestion {

    public Accident(Street affectedStreet, int flowAmount, int clearanceTime, int progressInLane) {
        super(affectedStreet, flowAmount, clearanceTime, progressInLane);
    }
}
