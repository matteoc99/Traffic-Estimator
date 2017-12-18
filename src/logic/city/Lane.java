package logic.city;

import logic.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Lane extends StreetComponent{
    ArrayList<Vehicle> vehicles;

    enum LaneDirection{
        LEFT,RIGHT,FORWARD,LEFT_FORWARD,LEFT_RIGHT,RIGHT_FORWARD,ALL
    }

    /**
     * permitted directions
     */
    LaneDirection laneDirection;
}
