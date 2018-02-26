package logic.city;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Streetlight {

    public enum state{
        RED,GREEN,YELLOW
    }

    /**
     * 0:Red
     * 1:Green
     */
    private state currentState;

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


    public Streetlight(state currentState, Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this.currentState = currentState;    this.parentLane = parentLane;
        this.parentLogic = parentLogic;
        this.direction = direction;
    }

    public Streetlight(Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this(Streetlight.state.RED,parentLane,parentLogic,direction);
    }

    public enum Direction {
        STRAIGHT, RIGHT, LEFT
    }


    public Streetlight.state getState() {
        return currentState;
    }

    public void setState(Streetlight.state currentState) {
        this.currentState = currentState;
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
