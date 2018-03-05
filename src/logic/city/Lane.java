package logic.city;

import logic.vehicles.Vehicle;

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
     * all streetlights on this lane
     */
    private ArrayList<Streetlight> streetlights;
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
        parent.addLane(this);
        vehicles = new ArrayList<>();
        streetlights = new ArrayList<>();
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
    public Vehicle getNextVehicle(int position) {
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
    public Vehicle getPrevVehicle(int position) {
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


    public void addStreetlight(Streetlight streetlight) {
        if (!contains(streetlight))
            streetlights.add(streetlight);
        else
            throw new RuntimeException("ALREADY CONTAINS STREET LIGHT 44");

    }

    public void removeStreetlight(Streetlight streetlight) {
        if (contains(streetlight))
            streetlights.remove(streetlight);
        else
            throw new RuntimeException("NO THING TO REMOVE");
    }

    public boolean contains(Streetlight streetlight) {
        for (Streetlight streetlight1 : streetlights) {
            if (streetlight1.equals(streetlight)) {
                return true;
            }
        }
        return false;
    }

    public void addVehicle(Vehicle vehicle) {
        if (!contains(vehicle))
            vehicles.add(vehicle);
        else
            throw new RuntimeException("ALREADY CONTAINS Vehicle 44");

    }

    public void removeVehicle(Vehicle vehicle) {
        if (contains(vehicle))
            vehicles.remove(vehicle);
        else
            throw new RuntimeException("NO THING TO REMOVE 69");
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

    public Node getFromNode() {
        Node ret;
        if (isReversed()) {
            ret = parent.getTo();
        } else {
            ret = parent.getFrom();
        }
        return ret;
    }

    public Node getToNode() {
        Node ret;
        if (isReversed()) {
            ret = parent.getFrom();
        } else {
            ret = parent.getTo();
        }
        return ret;
    }


    public double getLength() {
        if (length == -1)
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
        for (Vehicle vehicle : getVehicles()) {
            avgSpeed += vehicle.getCurrentSpeed();
        }
        if (getVehicles().size() > 0) {
            avgSpeed = avgSpeed / getVehicles().size();
            if (avgSpeed < 50) {
                traffic += 0.05;
            } else if (avgSpeed < 40) {
                traffic += 0.15;
            } else if (avgSpeed < 30) {
                traffic += 0.25;
            } else if (avgSpeed < 20) {
                traffic += 0.45;
            } else if (avgSpeed < 10) {
                traffic += 0.65;
            }
            traffic += getVehicles().size() / getLength() * 10;
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
        } else
            color = new Color(24, 0, 0);
        return color;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ArrayList<Streetlight> getStreetlights() {
        return streetlights;
    }

    public void setStreetlights(ArrayList<Streetlight> streetlights) {
        this.streetlights = streetlights;
    }
}
