package logic.city;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Street extends StreetComponent {

    /**
     * Id of the Street, unique in each JsonFile
     */
    private String id;

    /**
     * Reference to the City, which contains this Street
     */
    private City parent;

    /**
     * Nodes in which this Street starts or ends
     */
    private Node from;
    private Node to;

    /**
     * The maximum speed allowed on this Street, actual speed of the car might be higher
     */
    private double maxSpeed = 50;

    /**
     * The higher the less known a street is. 0 very known
     */
    private double unProminence;

    /**
     * Lists containing the Lanes on both sides of the Street
     * Forward means that the vehicles on the Lane drive away from the startNode and towards the endNode
     */
    private ArrayList<Lane> forwardLanes;
    private ArrayList<Lane> backwardLanes;

    /**
     * List of all Congestions happening on this Street
     */
    private ArrayList<Congestion> congestions;


    private boolean active = true;



    public Street(String id, City parent, Node from, Node to, double maxSpeed, double unProminence) {
        super(from.getX(), to.getX(), from.getY(), to.getY());
        this.id = id;
        this.parent = parent;
        this.from = from;
        this.to = to;
        this.maxSpeed = maxSpeed;
        this.unProminence = unProminence;


        forwardLanes = new ArrayList<>();
        backwardLanes = new ArrayList<>();
        congestions = new ArrayList<>();

        from.addStreet(this);
        to.addStreet(this);

    }


    public void addCongestion(Congestion congestion) {
        if (!contains(congestion)) {
            congestions.add(congestion);
        } else
            throw new RuntimeException("CONTAINS Congestion ");

    }

    public void removeCongestion(Congestion congestion) {
        if (contains(congestion)) {
            congestions.remove(congestion);
        } else {
            throw new RuntimeException("Nothing to remove 49");
        }
    }

    public boolean contains(Congestion congestion) {
        for (Congestion congestion1 : congestions) {
            if (congestion1.equals(congestion)) {
                return true;
            }
        }
        return false;
    }


    public Lane getLaneById(String id) {
        for (int i = 0; i < forwardLanes.size(); i++) {
            if (forwardLanes.get(i).getId().equals(id))
                return forwardLanes.get(i);
        }
        for (int i = 0; i < backwardLanes.size(); i++) {
            if (backwardLanes.get(i).getId().equals(id))
                return backwardLanes.get(i);
        }
        return null;
    }

    public void addLane(Lane lane) {
        if (!contains(lane)) {
            if (lane.isReversed()) {
                backwardLanes.add(lane);
                if (getFrom() instanceof MultiConnection) {
                    if (((MultiConnection) getFrom()).getLogic() != null) {
                        Streetlight streetlight = new Streetlight(lane, ((MultiConnection) getFrom()).getLogic(), Streetlight.Direction.STRAIGHT);
                        ((MultiConnection) getFrom()).getLogic().addStreetlight(streetlight);
                    }
                } else if (getFrom() instanceof Connection) {
                    if (((Connection) getFrom()).getLogic() != null) {
                        Streetlight streetlight = new Streetlight(lane, ((Connection) getFrom()).getLogic(), Streetlight.Direction.STRAIGHT);
                        ((Connection) getFrom()).getLogic().addStreetlight(streetlight);
                    }
                }
            } else {
                forwardLanes.add(lane);
                if (getTo() instanceof MultiConnection) {
                    if (((MultiConnection) getTo()).getLogic() != null) {
                        Streetlight streetlight = new Streetlight(lane, ((MultiConnection) getTo()).getLogic(), Streetlight.Direction.STRAIGHT);
                        ((MultiConnection) getTo()).getLogic().addStreetlight(streetlight);
                    }
                } else if (getTo() instanceof Connection) {
                    if (((Connection) getTo()).getLogic() != null) {
                        Streetlight streetlight = new Streetlight(lane, ((Connection) getTo()).getLogic(), Streetlight.Direction.STRAIGHT);
                        ((Connection) getTo()).getLogic().addStreetlight(streetlight);
                    }
                }
            }
        } else {
            throw new RuntimeException("CONTAINS LANE 2");
        }
        parent.add(lane);
    }

    public void removeLane(Lane lane) {
        if (contains(lane)) {
            if (lane.isReversed()) {
                backwardLanes.remove(lane);
            } else {
                forwardLanes.remove(lane);
            }
        } else {
            throw new RuntimeException("NO REMOVE LANE 2");
        }
        parent.remove(lane);
    }

    public boolean contains(Lane lane) {
        for (Lane lane1 : backwardLanes) {
            if (lane1.equals(lane)) {
                return true;
            }
        }
        for (Lane lane1 : forwardLanes) {
            if (lane1.equals(lane)) {
                return true;
            }
        }
        return false;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void toggleActive(){
        this.active= !this.active;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public City getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getUnProminence() {
        return unProminence;
    }

    public void setUnProminence(double unProminence) {
        this.unProminence = unProminence;
    }

    @Deprecated
    public ArrayList<Lane> getForwardLanes() {
        return forwardLanes;
    }

    public Lane getForwardLane(int i) {
        return forwardLanes.get(i);
    }

    public Iterator<Lane> getForwardLanesIterator() {
        return forwardLanes.iterator();
    }

    public int getForwardLanesSize() {
        return forwardLanes.size();
    }

    public void clearForwardLanes() {
        for (Lane forwardLane : forwardLanes) {
            parent.remove(forwardLane);
        }
        forwardLanes.clear();
    }

    @Deprecated
    public ArrayList<Lane> getBackwardLanes() {
        return backwardLanes;
    }

    public Lane getBackwardLane(int i) {
        return backwardLanes.get(i);
    }

    public Iterator<Lane> getBackwardLanesIterator() {
        return backwardLanes.iterator();
    }

    public int getBackwardLanesSize() {
        return backwardLanes.size();
    }

    public void clearBackwardLanes() {
        for (Lane backwardLane : backwardLanes) {
            parent.remove(backwardLane);
        }
        backwardLanes.clear();
    }

    public Lane getBackwardLaneByIndex(int index) {
        Lane lane = null;
        for (Lane backwardLane : backwardLanes) {
            if (backwardLane.getIndex() == index)
                lane = backwardLane;
        }
        return lane;
    }

    public Lane getForwardLaneByIndex(int index) {
        Lane lane = null;
        for (Lane forwardLane : forwardLanes) {
            if (forwardLane.getIndex() == index)
                lane = forwardLane;
        }
        return lane;
    }

    /**
     * @param lane to start from
     * @return the Lanes left and right of the given Lane
     */
    public ArrayList<Lane> getNeighbourLanes(Lane lane) {
        ArrayList<Lane> ret = new ArrayList<>();
        if (lane.isReversed()) {
            if (lane.getIndex() > 0) {
                ret.add(getBackwardLaneByIndex(lane.getIndex() - 1));
            }
            if (lane.getIndex() < backwardLanes.size() - 1) {
                ret.add(getBackwardLaneByIndex(lane.getIndex() + 1));
            }
        } else {
            if (lane.getIndex() > 0) {
                ret.add(getForwardLaneByIndex(lane.getIndex() - 1));
            }
            if (lane.getIndex() < forwardLanes.size() - 1) {
                ret.add(getForwardLaneByIndex(lane.getIndex() + 1));
            }
        }
        return ret;
    }

    /**
     * for dijkstra
     */
    public double getTotalCost(double autoSpeed) {
        return getLength() / Double.min(autoSpeed, maxSpeed);
    }

    /**
     * {@link City#calcCity()}
     */
    public void calcStreet() {
        for (Lane backwardLane : backwardLanes) {
            backwardLane.calcLane();
        }
        for (Lane forwardLane : forwardLanes) {
            forwardLane.calcLane();
        }
    }

    @Override
    public String toString() {
        return "Street{" +
                "id='" + id + '\'' +
                '}';
    }



}
