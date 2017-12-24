package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Street extends StreetComponent {
    /**
     * All lanes that have to be drawn
     * cars on lanes have to be drawn to
     * <p>
     * The lane with the smallest index is the one nearest to the center of the street
     */
    private ArrayList<Lane> streetDirectionLanes;
    private ArrayList<Lane> reverseDirectionLanes;

    /**
     * from where to where the street goes
     */
    private StreetIntersection from;
    private StreetIntersection to;

    /**
     * Max and Min speed that is allowed
     */
    private int maxSpeed;
    private int minSpeed;

    /**
     * how famous this street is 0-1
     * 1 max => very famous
     */
    private double fame;

    /**
     * Congestions on this street
     */
    ArrayList<Congestion> congestions;

    public Street(StreetIntersection from, StreetIntersection to, int maxSpeed, int minSpeed, double fame) {
        super(from.getX(), to.getX(), from.getY(), to.getY());
        this.from = from;
        this.to = to;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.fame = fame;
        from.addStreet(this);
        to.addStreet(this);
    }

    public Street(StreetIntersection from, StreetIntersection to) {
        this(from, to, 80, 50, 1);
    }


    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public double getFame() {
        return fame;
    }

    public void setFame(double fame) {
        this.fame = fame;
    }

    public void moveCars() {
        // TODO: 15.12.2017
    }

    public void killStreet() {
        from.addStreet(this);
        to.addStreet(this);
    }

    /**
     * Adds a lane
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addLane(Lane lane) {
        if (contains(lane))
            return false;
        if (lane.isReverse())
            reverseDirectionLanes.add(lane);
        else
            streetDirectionLanes.add(lane);
        return true;
    }

    /**
     * Removes a lane
     *
     * @return true if it was successfully removes, otherwise false
     */
    public boolean removeLane(Lane lane) {
        if (!contains(lane))
            return false;
        if (lane.isReverse()) {
            reverseDirectionLanes.remove(lane);

        } else {
            streetDirectionLanes.remove(lane);
        }
        return true;
    }

    /**
     * Checks if this city contains a certain lane
     *
     * @param lane the lane to check
     * @return true if it contains the lane, otherwise false
     */
    public boolean contains(Lane lane) {
        for (Lane l : streetDirectionLanes) {
            if (lane.equals(l))
                return true;
        }
        for (Lane l : reverseDirectionLanes) {
            if (lane.equals(l))
                return true;
        }
        return false;
    }

    /**
     * gets the neighbors of a lane
     * @param lane to check
     * @return zero, one or two neighbors. null->error
     *
     */
    public ArrayList<Lane> getNeighborLanes(Lane lane) {
        ArrayList<Lane> ret = new ArrayList<>();
        if (!contains(lane))
            return null;
        int index = streetDirectionLanes.indexOf(lane);
        if (index < 0) {
            index = reverseDirectionLanes.indexOf(lane);
            if(index-1>=0){
                ret.add(reverseDirectionLanes.get(index-1));
            }
            if(index+1<reverseDirectionLanes.size()){
                ret.add(reverseDirectionLanes.get(index+1));
            }
        }else{
            if(index-1>=0){
                ret.add(streetDirectionLanes.get(index-1));
            }
            if(index+1<streetDirectionLanes.size()){
                ret.add(streetDirectionLanes.get(index+1));
            }
        }
        return ret;
    }
}
