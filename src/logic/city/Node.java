package logic.city;

import utils.Utils;
import logic.vehicles.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Node {

    private City parent;
    private Point position;
    private double fame;
    private ArrayList<Street> streets;
    private String id;
    private double distanceCost; // calcDistance to goal node
    private double walkedCost; // the cost of the path walked until now

    // for pathfinder
    private Node previousNode;

    //provisory
    int letCarTrough = 10;


    public Node(City parent, Point position, double fame, String id) {
        this.parent = parent;
        this.position = position;
        this.fame = fame;
        this.id = id;
        this.streets = new ArrayList<>();
        parent.add(this);
    }

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


    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public City getParent() {
        return parent;
    }

    public Point getPosition() {
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


    public void startPathCalculation(Node goal) {
        setDistanceCost(goal);
        setWalkedCost(Double.MAX_VALUE / 2);
        setPreviousNode(null);
    }

    public void setDistanceCost(Node goal) {
        this.distanceCost = Utils.calcDistanceBetweenPoints(getPosition(), goal.getPosition());
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
            for (int j = 0; bLanes.hasNext(); j++) {
                Lane lane = bLanes.next();
                if (lane.getToNode().equals(currentGoal)) {
                    return lane;
                }
            }
            Iterator<Lane> fLanes = street.getForwardLanesIterator();
            for (int j = 0; fLanes.hasNext(); j++) {
                Lane lane = fLanes.next();
                if (lane.getToNode().equals(currentGoal)) {
                    return lane;
                }
            }
        }
        return null;
    }

    public boolean register(Vehicle vehicle, Node nextNode) {
        // TODO: 17.02.2018 für maxi ret true wenn er fahren darf
        if (letCarTrough == 10) {
            letCarTrough = 0;
            return true;
        } else {
            return false;
        }
    }

    /**
     * sets default
     */
    public void reset() {
        if (letCarTrough > 10)
            letCarTrough = 0;
        else letCarTrough++;
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
