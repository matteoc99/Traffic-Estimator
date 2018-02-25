package logic.vehicles.driving;

import logic.vehicles.Vehicle;

/**
 * @author Matteo Cosi
 * @since 21.02.2018
 */
public abstract class VehicleDriving implements DrivingInterface {

    private Action currentAction = Action.NO_ACTION;
    private Vehicle vehicle = null;

    /**
     * how much somone cares about the others 0-1
     * 1 very considerative
     */
    private double considerative;
    /**
     * how much some one acts stressed while driving 0-1
     */
    private double agressivity;
    /**
     * desired speed 0-2
     * 1--> respect the limits
     * <1 drive slower
     * >1 drive faster
     */
    private double desiredSpeed;

    /**
     * knowledge of the city 0-1
     * 0--> tourist
     * 1--> taxi driver that knows every street
     */
    private double knowledge;
    /**
     * cautiousness 0-1
     * 0-->  very slow and respective
     * 1--> reckless
     */
    private double coutiousness;

    /**
     * reaction time frames till react
     */
    private int reactionTime;


    public VehicleDriving(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public Action getNextAction() {
        return null;
    }

    @Override
    public Action evaluateAction(Action action) {
        return null;
    }

    @Override
    public void performMove() {
        if (currentAction == Action.NO_ACTION) {
            currentAction = getNextAction();
        }
        currentAction = evaluateAction(currentAction);
        switch (currentAction) {
            case ABORT:
                abortAction();
                break;
            case SURPASS:
                surpassAction();
                break;
            case STEP_ASIDE:
                stepAsideAction();
                break;
            case FOLLOW_LANE:
                followLaneAction();
                break;
            case SWITCH_LANE:
                switchLaneAction();
                break;
            case ADJUST_SPEED:
                adjustSpeedAction();
                break;
        }
    }

    public abstract void generateBehaviour();

    @Override
    public void actionCompleted() {
        currentAction = Action.NO_ACTION;
    }

    public void setUp() {
        // TODO: 21.02.2018 setup variables, create path for vehicle
        /*
        old get path
        if (path != null && path.isValid()) {
            path.resetProgress();
            prevGoal = city.getNodeById(path.getGoalAndIncrement());
            currentGoal = city.getNodeById(path.getGoalAndIncrement());

            if (currentGoal != null && prevGoal != null) {
                Lane lane = prevGoal.getLaneTo(currentGoal);
                changeLane(lane);
            } else {
                System.out.println("BAD PATH");
            }
        } else
            System.out.println("DIE2");
         */
    }

    private void abortAction() {

    }

    private void surpassAction() {

    }

    private void stepAsideAction() {

    }

    private void followLaneAction() {

    }

    private void switchLaneAction() {

    }

    private void adjustSpeedAction() {

    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleDriving setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
        return this;
    }

    public VehicleDriving setConsiderative(double considerative) {
        this.considerative = considerative;
        return this;
    }

    public VehicleDriving setAgressivity(double agressivity) {
        this.agressivity = agressivity;
        return this;
    }

    public VehicleDriving setDesiredSpeed(double desiredSpeed) {
        this.desiredSpeed = desiredSpeed;
        return this;
    }

    public VehicleDriving setKnowledge(double knowledge) {
        this.knowledge = knowledge;
        return this;
    }

    public VehicleDriving setCoutiousness(double coutiousness) {
        this.coutiousness = coutiousness;
        return this;
    }

    public VehicleDriving setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
        return this;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getConsiderative() {
        return considerative;
    }

    public double getAgressivity() {
        return agressivity;
    }

    public double getDesiredSpeed() {
        return desiredSpeed;
    }

    public double getKnowledge() {
        return knowledge;
    }

    public double getCoutiousness() {
        return coutiousness;
    }

    public int getReactionTime() {
        return reactionTime;
    }

    //old move
    /*
    Stopwatch timer = new Stopwatch().start();

        if (progressInLane / lane.getLength() > 0.98) {

        //change lane or die if path end is reached
        if(lane.equals(prevGoal.getLaneTo(currentGoal))) {
            prevGoal = currentGoal;
            currentGoal = getLane().getParent().getParent().getNodeById(path.getGoalAndIncrement());
        }
        if (currentGoal == null)
            lane.removeVehicle(this);
        else {
            boolean canGo = prevGoal.register(this, currentGoal);
            if (canGo) {
                Lane lane = prevGoal.getLaneTo(currentGoal);
                changeLane(lane);
                progressInLane = 0;
                //current speed bleibt bei einfachen kurven erhalten
                if (!(prevGoal instanceof Connection))
                    currentSpeed = maxSpeed / 8;
            }else {
                // me have to wait :(
            }
        }
        timer.print("Vehicles_DBG_ME: 1: ");
    } else {
        if (progressInLane / lane.getLength() > 0.80) {
            //goto better lane for dir change
        }
        //move
        //if car in front
        if (lane.getNextVehicle(progressInLane) != null) {
            //check car calcDistance
            if (progressInLane + safetyDistance + currentSpeed/4 < lane.getNextVehicle(progressInLane).progressInLane) {
                //move
                if (currentSpeed < maxSpeed) {
                    if (currentSpeed > 1)
                        currentSpeed += 1 + Math.log(currentSpeed);
                    else
                        currentSpeed++;
                } else {
                    currentSpeed--;
                }
                incrementProgrssInLane(currentSpeed);
                timer.print("Vehicles_DBG_ME: 2.1.1: ");
            } else {
                //slow down
                int c = 0;
                int nextVehiclesProgress = lane.getNextVehicle(progressInLane).progressInLane;
                while (progressInLane + safetyDistance + currentSpeed/4 > nextVehiclesProgress) {
                    currentSpeed -= 5;
                    c++;
                    if (currentSpeed < 0) {
                        currentSpeed = 0;
                        break;
                    }
                }
                incrementProgrssInLane(currentSpeed);
                timer.print("Vehicles_DBG_ME: 2.1.2: ");
            }
            timer.print("Vehicles_DBG_ME: 2.1: ");
        } else {
            //just move
            if (currentSpeed < maxSpeed) {
                currentSpeed += 1 + Math.log(currentSpeed);
            } else {
                currentSpeed--;
            }
            incrementProgrssInLane(currentSpeed);
            if (progressInLane > lane.getLength())
                progressInLane = (int) lane.getLength();
            timer.printAndReset("Vehicles_DBG_ME: 2.2: ");
        }
        timer.print("Vehicles_DBG_ME: 2: ");
    }
    */

}
