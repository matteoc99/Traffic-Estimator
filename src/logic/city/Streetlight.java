package logic.city;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class Streetlight {
    private int state;
    private Lane parentLane;
    private StreetlightLogic parentLogic;

    private Direction direction;


    public Streetlight(int state, Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this.state = state;
        this.parentLane = parentLane;
        this.parentLogic = parentLogic;
        this.direction = direction;
    }

    public Streetlight(Lane parentLane, StreetlightLogic parentLogic, Direction direction) {
        this(0,parentLane,parentLogic,direction);
    }

    public enum Direction{
        STRAIGHT,RIGHT,LEFT
    }

    public void toggle(){
        // TODO: 29.01.2018
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
