package logic.city;

import java.util.ArrayList;

import static logic.Utils.calcDegreesBetweenTwoPoint;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Street extends StreetComponent {


    private String id;
    private City parent;
    private Node from;
    private Node to;
    private double maxSpeed;
    private double prominence;

    private ArrayList<Lane> forwardLanes;
    private ArrayList<Lane> backwardLanes;
    private ArrayList<Congestion> congestions;


    public Street(String id, City parent, Node from, Node to, double maxSpeed, double prominence) {
        super(from.getX(), to.getX(), from.getY(), to.getY());
        this.id = id;
        this.parent = parent;
        this.from = from;
        this.to = to;
        this.maxSpeed = maxSpeed;
        this.prominence = prominence;

        forwardLanes = new ArrayList<>();
        backwardLanes = new ArrayList<>();
        congestions = new ArrayList<>();

        from.addStreet(this);
        to.addStreet(this);
    }


    public void addCongestion(Congestion congestion) {
        if (!contains(congestion)) {
            congestions.add(congestion);
        }
        else
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


    public Lane getLaneById(String id){
        for (int i = 0; i < forwardLanes.size(); i++) {
            if(forwardLanes.get(i).getId().equals(id))
                return forwardLanes.get(i);
        }
        for (int i = 0; i < backwardLanes.size(); i++) {
            if(backwardLanes.get(i).getId().equals(id))
                return backwardLanes.get(i);
        }
        return null;
    }

    public void addLane(Lane lane) {
        if (!contains(lane)) {
            if (lane.isReversed()) {
                backwardLanes.add(lane);
            } else {
                forwardLanes.add(lane);
            }
        }else{
            throw new RuntimeException("CONTAINS LANE 2");
        }
    }

    public void removeLane(Lane lane) {
        if (contains(lane)) {
            if (lane.isReversed()) {
                backwardLanes.remove(lane);
            } else {
                forwardLanes.remove(lane);
            }
        }else{
            throw new RuntimeException("NO REMOVE LANE 2");
        }
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

    public double getProminence() {
        return prominence;
    }

    public void setProminence(double prominence) {
        this.prominence = prominence;
    }

    public ArrayList<Lane> getForwardLanes() {
        return forwardLanes;
    }

    public void setForwardLanes(ArrayList<Lane> forwardLanes) {
        this.forwardLanes = forwardLanes;
    }

    public ArrayList<Lane> getBackwardLanes() {
        return backwardLanes;
    }

    public void setBackwardLanes(ArrayList<Lane> backwardLanes) {
        this.backwardLanes = backwardLanes;
    }

    public Lane getBackwardLaneByIndex(int index) {
        Lane lane = null;
        for (int i = 0; i < backwardLanes.size(); i++) {
            if(backwardLanes.get(i).getIndex() == index)
                lane= backwardLanes.get(i);
        }
        return lane;
    }
    public Lane getForwardLaneByIndex(int index) {
        Lane lane = null;
        for (int i = 0; i < forwardLanes.size(); i++) {
            if(forwardLanes.get(i).getIndex() == index)
                lane= forwardLanes.get(i);
        }
        return lane;
    }

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
    public double getGesamtKosten(double autoSpeed){
        return getLength()/Double.min(autoSpeed,maxSpeed);
    }


    public void calcStreet() {
        for (int i = 0; i < backwardLanes.size(); i++) {
            backwardLanes.get(i).calcLane();
        }
        for (int i = 0; i < forwardLanes.size(); i++) {
            forwardLanes.get(i).calcLane();
        }
    }
}
