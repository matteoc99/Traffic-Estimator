package logic.city;

import logic.vehicles.Vehicle;
import utils.Utils;
import utils.math.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Lane {

    /**
     * Unique identifier of the lane
     */
    private String id;

    /**
     * The street where the lane is contained
     */
    private Street parent;
    /**
     * tells if the lane goes in street direction or in lane direction
     */
    private boolean reversed;

    /**
     * index of lane if there are multiple lanes of the same direction
     */
    private int index;

    /**
     * the streetlight on this lane
     */
    private Streetlight streetlight;
    /**
     * all vehicles driving on this lane
     */
    private ArrayList<Vehicle> vehicles;

    /**
     * length of this lane
     */
    private double length = -1;

    /**
     * priority of the lane. used to implement the right of way
     * the higher the priority is, the more likely this lane is to be calced first
     */
    // TODO: 20.02.2018 add priority
    int priority = 0;

    /**
     * setup the Lane and add the lane to the street
     */
    public Lane(String id, Street parent, boolean reversed, int index) {
        this.id = id;
        this.parent = parent;
        this.reversed = reversed;
        this.index = index;
        vehicles = new ArrayList<>();
        if (parent != null) {
            parent.addLane(this);
        }
    }

    /**
     * returns the neighbour lanes if there are multiple in the same direction
     *
     * @return the neighbour lanes
     */
    public ArrayList<Lane> getNeighbourLanes() {
        return parent.getNeighbourLanes(this);
    }


    /**
     * returns the next vehicle relative to a position on this lane
     *
     * @param position the position to check
     * @return the next vehicle
     */
    public Vehicle getNextVehicle(double position) {
        double smallest = Integer.MAX_VALUE;
        int index = -1;
        int i = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getProgressInLane() > position && vehicle.getProgressInLane() < smallest) {
                index = i;
                smallest = vehicle.getProgressInLane();
            }
            i++;
        }
        if (index > -1)
            return vehicles.get(index);
        else
            return null;
    }

    /**
     * returns the previous vehicle relative to a position on this lane
     *
     * @param position the position to check
     * @return the previous vehicle
     */
    public Vehicle getPrevVehicle(double position) {
        double biggest = -1;
        int index = -1;
        int i = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getProgressInLane() < position && vehicle.getProgressInLane() > biggest) {
                index = i;
                biggest = vehicle.getProgressInLane();
            }
            i++;
        }
        if (index > -1)
            return vehicles.get(index);
        else
            return null;
    }


    public void addVehicle(Vehicle vehicle) {
        if (!contains(vehicle))
            vehicles.add(vehicle);
        else
            throw new RuntimeException("ALREADY CONTAINS Vehicle 130");

    }

    public void removeVehicle(Vehicle vehicle) {
        if (contains(vehicle))
            vehicles.remove(vehicle);
        else
            System.out.println("NO THING TO REMOVE 138");
    }

    public boolean contains(Vehicle vehicle) {
        for (Vehicle vehicle1 : vehicles) {
            if (vehicle1.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public Street getParent() {
        return parent;
    }

    public int getIndex() {
        return index;
    }

    public boolean isReversed() {
        return reversed;
    }

    /**
     * get the node that lays at the beginning of this lane
     *
     * @return the node at the beginning of this lane
     */
    public Node getFromNode() {
        Node ret;
        if (isReversed()) {
            ret = parent.getTo();
        } else {
            ret = parent.getFrom();
        }
        return ret;
    }

    /**
     * get the node that lays at the end of this lane
     *
     * @return the node at the end of this lane
     */
    public Node getToNode() {
        Node ret;
        if (isReversed()) {
            ret = parent.getFrom();
        } else {
            ret = parent.getTo();
        }
        return ret;
    }


    /**
     * calculates the length of this lane
     *
     * @return the length of this lane
     */
    public double getLength() {
        if (length == -1)
            if (parent == null) {
                System.out.println("hopefully == 0:" + vehicles.size());
                length = 1;
            } else
                length = parent.getLength();
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }


    /**
     * calc lane is called every frame
     * and calls the move of every car
     */
    @Deprecated
    void calcLane() {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            vehicle.move();
        }
    }

    @Override
    public String toString() {
        return "Lane{" +
                "id='" + id + '\'' +
                ", parent=" + parent +
                '}';
    }

    /**
     * calculates the amount of traffic from 0-1
     *
     * @return traffic
     */
    public double getTraffic() {
        double traffic = 0;
        double avgSpeed = 0;
        ArrayList<Vehicle> vehiclesList = new ArrayList<>(getVehicles());
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle != null)
                avgSpeed += vehicle.getCurrentSpeed();
            else
                getVehicles().remove(vehicle);
        }
        if (getVehicles().size() > 0) {
            try {
                traffic += getVehicles().size() * (getVehicles() == null || getVehicles().isEmpty() ? 0 : getVehicles().get(0).getVehicleDriving().getSafetyDist()) / (getLength()*2);
            } catch (NullPointerException e) {
                //TODO ignored
            }
        }
        return (traffic);
    }

    /**
     * returns a color based on the traffic of the lanes
     *
     * @return a color based on the traffic
     */
    public Color getColorByTraffic() {
        double traffic = getTraffic();
        Color color = null;
        if (traffic < 0.1) {
            color = new Color(0, 255, 0);
        } else if (traffic < 0.4) {
            color = new Color(255, 239, 12);
        } else if (traffic < 0.6) {
            color = new Color(255, 179, 6);
        } else if (traffic < 0.8) {
            color = new Color(255, 75, 7);
        } else if (traffic < 0.9) {
            color = new Color(255, 17, 0);
        } else if (traffic < 1.1) {
            color = new Color(91, 3, 0);
        } else if (traffic < 2) {
            color = new Color(46, 2, 0);
        } else if (traffic < 4)
            color = new Color(24, 0, 0);
        else
            color = new Color(0, 0, 0);
        if (parent.isActive())
            return color;
        else
            return Color.GRAY;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Streetlight getStreetlight() {
        return streetlight;
    }

    public void setStreetlight(Streetlight streetlight) {
        this.streetlight = streetlight;
    }

    public Position getPointByProgress(double progressInLane) {
        Position ret = new Position(0, 0);
        double deltaY = getFromNode().getY() - getToNode().getY();
        double deltaX = getFromNode().getX() - getToNode().getX();
        double x = getFromNode().getX() - deltaX * (progressInLane / getLength());
        double y = getFromNode().getY() - deltaY * (progressInLane / getLength());
        ret.setX(x);
        ret.setY(y);
        return ret;
    }

    public double getDegrees() {
        return Utils.calcDegreesBetweenTwoPoint(
                (int) Math.round(getFromNode().getX()),
                (int) Math.round(getFromNode().getY()),
                (int) Math.round(getToNode().getX()),
                (int) Math.round(getToNode().getY())
        );
    }

    public boolean isFull() {

        try {
            if (vehicles.isEmpty() || vehicles.size() < 3)
                return false;
            if (vehicles.get(0).getVehicleDriving().getSafetyDist() * vehicles.size() > getLength()) {
                return true;
            } else return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
