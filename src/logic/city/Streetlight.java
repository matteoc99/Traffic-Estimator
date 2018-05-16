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

    public static final int RED = 0;
    public static final int GREEN = 1;


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
        parentLane.setStreetlight(this);
    }

    public Streetlight(Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this(RED, parentLane, parentLogic, direction);
    }

    public enum Direction {
        STRAIGHT, RIGHT, LEFT
    }

    public void toggle() {
        state = (state == GREEN ? RED : GREEN);
    }

    public int getState() {
        return state;
    }

    public void setState(int currentState) {
        this.state = currentState;
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
