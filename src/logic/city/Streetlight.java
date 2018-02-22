package logic.city;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Streetlight {

    /**
     * 0:Red
     * 1:Green
     */
    private int state;

    /**
     * Reference to the Lane this Streetlight is part of
     */
    private Lane parentLane;

    /**
     * Reference to it's logic
     */
    private StreetlightLogic parentLogic;

    /**
     * Direction for which this Streetlight is responsible, it can only be responsible for a single direction
     */
    private Direction direction;


    public Streetlight(int state, Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this.state = state;
        this.parentLane = parentLane;
        this.parentLogic = parentLogic;
        this.direction = direction;
    }

    public Streetlight(Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this(0, parentLane, parentLogic, direction);
    }

    public enum Direction {
        STRAIGHT, RIGHT, LEFT
    }

    /**
     * Change state
     */
    public void toggle() {
        state = (state == 1) ? 0 : 1;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public Lane getParentLane() {
        return parentLane;
    }

    public StreetlightLogic getParentLogic() {
        return parentLogic;
    }

    public Direction getDirection() {
        return direction;
    }
}
