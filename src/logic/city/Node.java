package logic.city;

import logic.Utils;
import logic.vehicles.Vehicle;

import java.awt.*;
import java.util.ArrayList;

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
    private double distanceCost; // distannce to goal node
    private double walkedCost; //the cost of the path walked till now

    //for pathfinder
    private Node previousNode;


    public Node(City parent, Point position, double fame, String id) {
        this.parent = parent;
        this.position = position;
        this.fame = fame;
        this.id = id;
        this.streets = new ArrayList<>();
        parent.addNode(this);
    }

    public Street getStreetById(String id){
        for (int i = 0; i < streets.size(); i++) {
            if(streets.get(i).getId().equals(id))
                return streets.get(i);
        }
        return null;
    }


    public void addStreet(Street street){
        if(!contains(street)){
            streets.add(street);
        }else{
            throw new RuntimeException("ALREADY CONTAINS STREET");
        }
    }
    public void removeStreet(Street street){
        if(contains(street)){
            streets.remove(street);
        }else {
            throw new RuntimeException("NO STREET TO REMOVE");
        }
    }

    public boolean contains(Street street){
        for (Street street1: streets) {
            if (street.getId().equals(street1.getId())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Lane> getIncLanes(){
        // TODO: 29.01.2018
        return null;
    }
    public ArrayList<Lane> getOutLanes(){
        // TODO: 29.01.2018
        return null;
    }
    public ArrayList<Lane> getLanesTo(Node toNode){
        // TODO: 29.01.2018
        return null;
    }

    public int getX(){
        return position.x;
    }

    public int getY(){
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

    public ArrayList<Street> getStreets() {
        return streets;
    }

    public String getId() {
        return id;
    }


    public void startPathCalculation(Node goal){
        setDistanceCost(goal);
        setWalkedCost(Double.MAX_VALUE/2);
        setPreviousNode(null);
    }

    public void setDistanceCost(Node goal) {
        this.distanceCost = Utils.calcDistanceBetweenPoints(getPosition(),goal.getPosition());
    }

    public void setWalkedCost(double walkedCost) {
        this.walkedCost = walkedCost;
    }

    public double getFieldCost() {
        return walkedCost+distanceCost;
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

    @Override
    public String toString() {
        return "Node(" + this.getClass().toString()+
                "){" +
                "parent=" + parent +
                ", position=" + position +
                ", fame=" + fame +
                ", streets=" + streets +
                ", id='" + id + '\'' +
                '}';
    }

    /**
     * returns the lane for a new goal
     */
    public Lane setOnLaneTo(Node currentGoal) {
        for (Street street : streets) {
            for (int j = 0; j < street.getBackwardLanes().size(); j++) {
                if (street.getBackwardLanes().get(j).getToNode().equals(currentGoal)) {
                   return  street.getBackwardLanes().get(j);
                }
            }
            for (int j = 0; j < street.getForwardLanes().size(); j++) {
                if (street.getForwardLanes().get(j).getToNode().equals(currentGoal)) {
                    return  street.getForwardLanes().get(j);
                }
            }
        }
        return null;
    }

    public void request(Vehicle vehicle) {
        // TODO: 31.01.2018

    }
}
