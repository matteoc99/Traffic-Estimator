package logic.city;

import logic.vehicles.Vehicle;
import utils.Utils;
import utils.math.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Node {

    /**
     * A reference to he city where this lane is contained
     */
    private City parent;

    /**
     * graphical position of the Node
     */
    private Position position;

    /**
     * How likely this place is chosen as a goal/start
     */
    private double fame;
    /**
     * all the streets that are connected to this Node
     */
    private ArrayList<Street> streets;
    /**
     * Unique identifier of the Street
     */
    private String id;

    /**
     * used for the A* Algorithm
     */
    private double distanceCost; // calcDistance to goal node
    /**
     * used for the A* Algorithm
     */
    private double walkedCost; // the cost of the path walked until now
    /**
     * used for the A* Algorithm
     * to get the whole path
     */
    private Node previousNode;


    /**
     * creates a node and adds it to the city
     */
    public Node(City parent, Position position, double fame, String id) {
        this.parent = parent;
        this.position = position;
        this.fame = fame;
        this.id = id;
        this.streets = new ArrayList<>();
        parent.add(this);
    }

    /**
     * returns a street based on the Id
     *
     * @param id the needed id
     * @return the requested street, otherwise null
     */
    public Street getStreetById(String id) {
        for (int i = 0; i < streets.size(); i++) {
            if (streets.get(i).getId().equals(id))
                return streets.get(i);
        }
        return null;
    }


    public void addStreet(Street street) {
        if (!contains(street)) {
            streets.add(street);
        } else {
            throw new RuntimeException("ALREADY CONTAINS STREET");
        }
        parent.add(street);
    }

    public void removeStreet(Street street) {
        if (contains(street)) {
            streets.remove(street);
        } else {
            throw new RuntimeException("NO STREET TO REMOVE");
        }
        parent.remove(street);
    }

    public boolean contains(Street street) {
        for (Street street1 : streets) {
            if (street.getId().equals(street1.getId())) {
                return true;
            }
        }
        return false;
    }


    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public City getParent() {
        return parent;
    }

    public Position getPosition() {
        return position;
    }

    public double getFame() {
        return fame;
    }

    @Deprecated
    public ArrayList<Street> getStreets() {
        return streets;
    }

    public Street getStreet(int index) {
        return streets.get(index);
    }

    public Iterator<Street> getStreetIterator() {
        return streets.iterator();
    }

    public int getStreetSize() {
        return streets.size();
    }

    public void clearStreets() {
        // TODO: 18.02.2018
        System.out.println("Error: Streets cannot be cleared yet");
    }

    public String getId() {
        return id;
    }


    /**
     * used for A* Algorithm
     */
    public void startPathCalculation(Node goal) {
        setDistanceCost(goal);
        setWalkedCost(Double.MAX_VALUE / 2);
        setPreviousNode(null);
    }

    public void setDistanceCost(Node goal) {
        // TODO: 12.03.2018 test :P
        this.distanceCost = getPosition().distanceTo(goal.getPosition());
    }

    public void setWalkedCost(double walkedCost) {
        this.walkedCost = walkedCost;
    }

    public double getFieldCost() {
        return walkedCost + distanceCost;
    }

    public double getDistanceCost() {
        return distanceCost;
    }

    public double getWalkedCost() {
        return walkedCost;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * returns the lane for a new goal
     */
    public Lane getLaneTo(Node currentGoal) {
        for (Street street : streets) {
            Iterator<Lane> bLanes = street.getBackwardLanesIterator();
            Lane lane =iterateLanesForNextLane(currentGoal, street, bLanes);
            if(lane!=null)
                return lane;
            Iterator<Lane> fLanes = street.getForwardLanesIterator();
            lane= iterateLanesForNextLane(currentGoal, street, fLanes);
            if(lane!=null)
                return lane;
        }
        return null;
    }

    private Lane iterateLanesForNextLane(Node currentGoal, Street street, Iterator<Lane> fLanes) {
        for (int j = 0; fLanes.hasNext(); j++) {
            Lane lane = fLanes.next();
            if (lane.getToNode().equals(currentGoal)) {
                if (street.isActive())
                    return lane;
                else
                    return new Lane("deactivated", null, false, 0);
            }
        }
        return null;
    }

    /**
     * This method is called when a Vehicle wants to drive over this node to another Node
     *
     * @param vehicle  the vehicle that does the request
     * @param nextNode the desiered goal
     * @return true if the vehicle can drive, otherwise false
     */
    public boolean register(Vehicle vehicle, Node nextNode) {
        if (vehicle.getLane().getStreetlight() == null)
            return true;
        return vehicle.getLane().getStreetlight().getState() != 0;
    }

    /**
     * sets default
     */
    public void reset() {
    }


    @Override
    public String toString() {
        return "Node(" + this.getClass().toString() +
                "){" +
                "parent=" + parent +
                ", position=" + position +
                ", fame=" + fame +
                ", streets=" + streets +
                ", id='" + id + '\'' +
                '}';
    }


}
