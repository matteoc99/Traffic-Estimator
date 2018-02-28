package logic.vehicles.driving;

import NeuralNetworkLibrary.src.network.Network;
import logic.vehicles.Vehicle;

/**
 * @author Matteo Cosi
 * @since 21.02.2018
 */
public abstract class VehicleDriving implements DrivingInterface {

    private Action currentAction = Action.NO_ACTION;
    private Vehicle vehicle = null;

    /**
     * how much someone cares about the others 0-1
     * 1 very considerative
     */
    private double considerative;

    /**
     * how stressed someone acts while driving 0-1
     */
    private double agressivity;

    /**
     * desired speed 0-1
     * 0.5--> respect the limits
     * <0.5 drive slower
     * >0.5 drive faster
     */
    private double desiredSpeed;

    /**
     * cautiousness 0-1
     * 0-->  very slow and respective
     * 1--> reckless
     *
     * Difference to considerative: Affects behavior when no other vehicles around
     * Difference to agressivity: Currently none
     */
    @Deprecated
    private double cautiousness;

    /**
     * knowledge of the city 0-1
     * 0--> tourist
     * 1--> taxi driver that knows every street
     */
    private double knowledge;

    /**
     * frames until the driver reacts to not expected or sudden things
     */
    private int reactionTime;


    /**
     * inputs: 8
     * considerative
     * desiredSpeed
     * agressivity
     * distance car behinde(0-1) 0--> close
     * distance car infront(0-1) 0--> close
     * isLeftlaneFree 0 oder 1
     * isRightLaneFree 0 oder 1
     * ampel state 0->red  1-> green
     *
     *
     * hidden: 400
     *
     * output: 5
     * pro action one(except for ABORT and NO ACTION)
     */
    Network network;


    public VehicleDriving(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public Action getNextAction() {
       if(network == null) {
           DataSets dataSets = new DataSets();
           dataSets.generateDataSets();
           network = new Network(8,5,1,new int[]{400});
           network.train(dataSets.getDataset());
       }
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

    /**
     * Method returns a specific value, fitting the drivers traits, for this action
     * Range: 0 to 3
     * @return a value to multiply the points with
     */
    private double getSurpassMultiplier() {
        double ret = 0;

        double specificDesiredSpeed = vehicle.getLane().getParent().getMaxSpeed()*desiredSpeed;
        double differenceFactor = specificDesiredSpeed / vehicle.getCurrentSpeed();

        if (differenceFactor <= 1) // no need to surpass as we are fine with the speed
            return 0;
        if (differenceFactor <= 1.1)
            ret += 0.33;
        else if (differenceFactor <= 1.5)
            ret += 0.8;
        else
            ret += 1.5;

        ret += agressivity*0.5;

        return ret;
    }

    /**
     * Method returns a specific value, fitting the drivers traits, for this action
     * Range: 0 to 3
     * @return a value to multiply the points with
     */
    private double getStepAsideMultiplier() {
        return 0;
    }

    /**
     * Method returns a specific value, fitting the drivers traits, for this action
     * Range: 0 to 3
     * @return a value to multiply the points with
     */
    private double getFollowLaneMultiplier() {
        return 0;
    }

    /**
     * Method returns a specific value, fitting the drivers traits, for this action
     * Range: 0 to 3
     * @return a value to multiply the points with
     */
    private double getSwitchLaneMultiplier() {
        return 0;
    }

    /**
     * Method returns a specific value, fitting the drivers traits, for this action
     * Range: 0 to 3
     * @return a value to multiply the points with
     */
    private double getAdjustSpeedMultiplier() {
        return 0;
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

    public VehicleDriving setCautiousness(double cautiousness) {
        this.cautiousness = cautiousness;
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

    public double getCautiousness() {
        return cautiousness;
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