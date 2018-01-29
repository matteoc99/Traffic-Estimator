package logic.city;

import logic.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Lane extends StreetComponent {
    private String id;
    private Street parent;
    private boolean reverse;
    private int index;
    private ArrayList<Streetlight> streetlights;
    private ArrayList<Vehicle> vehicles; //TODO add remove contains

    public Lane(String id, Street parent, boolean reverse, int index) {
        super(parent.xFrom, parent.xTo, parent.yFrom, parent.yTo);
        this.id = id;
        this.parent = parent;
        this.reverse = reverse;
        this.index = index;
    }

    public ArrayList<Lane> getNeighbourLanes() {
        // TODO: 29.01.2018
        return null;
    }

    public Vehicle getNextVehicle(int position) {
        // TODO: 29.01.2018
        return null;
    }


    public void addStreetlight(Streetlight streetlight) {
        if (!contains(streetlight))
            streetlights.add(streetlight);
    }

    public void removeStreetlight(Streetlight streetlight) {
        if (contains(streetlight))
            streetlights.remove(streetlight);
        else
            System.out.println("NO THING TO REMOVE");
    }

    public boolean contains(Streetlight streetlight) {
        for (Streetlight streetlight1 : streetlights) {
            if (streetlight1.equals(streetlight)) {
                System.out.println("ALREADY CONTAINS STREET LIGHT 44");
                return true;
            }
        }
        return false;
    }

    public void addVehicle(Vehicle vehicle) {
        if (!contains(vehicle))
            vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        if (contains(vehicle))
            vehicles.remove(vehicle);
        else
            System.out.println("NO THING TO REMOVE 69");
    }

    public boolean contains(Vehicle vehicle) {
        for (Vehicle vehicle1 : vehicles) {
            if (vehicle1.equals(vehicle)) {
                System.out.println("ALREADY CONTAINS Vehicle 44");
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

    public boolean isReverse() {
        return reverse;
    }
}
