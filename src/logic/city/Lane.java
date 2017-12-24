package logic.city;

import logic.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Lane extends StreetComponent{

    /**
     * street that contains the lane
     */
    private Street street;
    private ArrayList<Vehicle> vehicles;

    enum LaneDirection{
        LEFT,RIGHT,FORWARD,LEFT_FORWARD,LEFT_RIGHT,RIGHT_FORWARD,ALL
    }

    /**
     * permitted directions given by the parent object
     */
    LaneDirection laneDirection;

    /**
     * a lane is reverse if it goes the opposit direction of the street
     */
    private boolean reverse;


    public Lane(Street street,boolean reverse) {
        this.street = street;
        this.reverse=reverse;
        street.addLane(this);
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public ArrayList<Lane> getNeighborLanes(){
        return street.getNeighborLanes(this);
    }
}
