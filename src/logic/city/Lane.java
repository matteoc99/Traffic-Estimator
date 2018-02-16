package logic.city;

import logic.vehicles.Vehicle;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Lane {
    private String id;
    private Street parent;
    private boolean reversed;
    private int index;
    private ArrayList<Streetlight> streetlights;
    private ArrayList<Vehicle> vehicles;
    private double length = -1;

    public Lane(String id, Street parent, boolean reversed, int index) {
        this.id = id;
        this.parent = parent;
        this.reversed = reversed;
        this.index = index;
        parent.addLane(this);
        vehicles = new ArrayList<>();
    }

    public ArrayList<Lane> getNeighbourLanes() {
        return parent.getNeighbourLanes(this);
    }

    public Vehicle getNextVehicle(int position) {
        int smallest = Integer.MAX_VALUE;
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


    void calcLane() {
        //anti ConcurrentModificationException
        List<Vehicle> list = vehicles;
        for (int i = 0; i < list.size(); i++) {
            Vehicle vehicle = list.get(i);
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
     * @return traffic
     */
    public double getTraffic(){
        int spaceNeeded=0;
        for (Vehicle vehicle : getVehicles()) {
            spaceNeeded+=vehicle.safetyDistance;
        }
        return (spaceNeeded/getLength());
    }

    public Color getColorByTraffic() {
        double traffic = getTraffic();
        System.out.println(traffic);
        Color color = null;
        if(traffic<0.1){
            color= new Color(0,255,0);
        }else if(traffic<0.4){
            color= new Color(160,255, 20);
        }else if(traffic<0.6){
            color= new Color(250,255, 19);
        }else if(traffic<0.8){
            color= new Color(255, 228, 17);
        }else if(traffic<0.9){
            color= new Color(255, 143, 18);
        }else if(traffic<1.1){
            color= new Color(255, 67, 17);
        }else if(traffic<1.8){
            color= new Color(255, 14, 17);
        }else
            color= new Color(71, 0,0);
        return color;
    }
}
