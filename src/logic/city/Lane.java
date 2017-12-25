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
        vehicles = new ArrayList<>();
        this.street = street;
        this.reverse=reverse;
        street.addLane(this);
    }

    /**
     * Adds a Vehicle
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addVehicle(Vehicle vehicle) {
        if(contains(vehicle)) {
            System.out.println("Vehicle 47");
            return false;
        }
        vehicles.add(vehicle);
        return true;
    }

    /**
     * Removes a Vehicle
     *
     * @return true if it was successfully removed, otherwise false
     */
    public boolean removeVehicle(Vehicle vehicle) {
        if(!contains(vehicle)) {
            System.out.println("Vehicle 61");
            return false;
        }
        vehicles.remove(vehicle);
        return true;
    }

    /**
     * Checks weather the lane contains a Vehicle
     * @param vehicle the street to check
     * @return true if the lane contains the Vehicle, otherwise false
     */
    public boolean contains(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if(vehicle.equals(v))
                return true;
        }
        return false;
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

    public Street getParent(){
        return street;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
