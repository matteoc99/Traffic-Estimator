package logic.city;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class RoadWork extends Congestion {
    public RoadWork(Street affectedStreet, int flowAmount, int clearanceTime, int progressInLane) {
        super(affectedStreet, flowAmount, clearanceTime, progressInLane);
    }
}
