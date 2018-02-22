package logic.vehicles.driving;

/**
 * @author Matteo Cosi
 * @since 21.02.2018
 */
public interface DrivingInterface {
    Action getNextAction();
    Action evaluateAction(Action action);
    void performMove();
    void actionCompleted();
}
