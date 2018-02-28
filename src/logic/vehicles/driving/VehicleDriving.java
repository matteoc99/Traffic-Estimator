package logic.vehicles.driving;

import NeuralNetworkLibrary.src.network.Network;
import logic.city.Connection;
import logic.city.Lane;
import logic.city.Path;
import logic.city.Streetlight;
import logic.vehicles.Vehicle;
import utils.PathUtils;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 21.02.2018
 */
public class VehicleDriving implements DrivingInterface {

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
     * knowledge of the city 0-1
     * 0--> tourist
     * 1--> taxi driver that knows every street
     */
    private double knowledge;

    /**
     * frames until the driver reacts to not expected or sudden things
     */
    private int reactionTime;
    private int reactionTimePassed = 0;


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
     * <p>
     * <p>
     * hidden: 400
     * <p>
     * output: 4
     * pro action one(except for ABORT ,STEP_ASIDE and NO ACTION)
     */
    Network network;


    double safetyDistance = 0;

    public VehicleDriving(Vehicle vehicle) {
        this.vehicle = vehicle;
        setUp();
        generateRandomBehaviour();
    }

    public VehicleDriving(Vehicle vehicle, Character character) {
        this(vehicle);
        setParamsForCharacter(character);
    }

    private void setParamsForCharacter(Character character) {
        switch (character) {
            case OLD:
                considerative = 0.4;
                agressivity = 0.2;
                desiredSpeed = 0.2;
                reactionTime = 20;
                break;
            case YOUNG:
                considerative = 0.1;
                agressivity = 0.3;
                desiredSpeed = 0.5;
                reactionTime = 5;
                break;
            case KIND:
                considerative = 1.0;
                agressivity = 0.1;
                desiredSpeed = 0.6;
                reactionTime = 10;
                break;
            case STRESSED:
                considerative = 0.0;
                agressivity = 1.0;
                desiredSpeed = 1;
                reactionTime = 10;
                break;
            case SPEERER:
                considerative = 0.2;
                agressivity = 0.5;
                desiredSpeed = 1;
                reactionTime = 13;
                break;
            case BUDDHA:
                considerative = 1;
                agressivity = 0;
                desiredSpeed = 0.5;
                reactionTime = 2;
                break;
        }
    }

    @Override
    public Action getNextAction() {
        System.out.println("get next");
        if (network == null) {
            DataSets dataSets = new DataSets();
            dataSets.generateDataSets();
            network = new Network(7, 4, 1, new int[]{300});
            System.out.println("started training");
            network.train(dataSets.getDataset());
        }
        double[] data = new double[network.getLayerByIndex(0).getNeuronCount()];
        data[0] = considerative;
        data[1] = desiredSpeed;
        data[2] = agressivity;
        data[3] = getDistanceCarBehind();
        data[4] = getDistanceCarInfront();
        data[5] = isLeftLaneFree() ? 1 : 0;
        data[6] = isRightLaneFree() ? 1 : 0;
        double[] result = network.processData(data);
        double max = Double.MIN_VALUE;
        int indexMax = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) {
                indexMax = i;
                max = result[i];
            }
        }
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(Action.SURPASS);
        actions.add(Action.FOLLOW_LANE);
        actions.add(Action.SWITCH_LANE);
        actions.add(Action.ADJUST_SPEED);
        return actions.get(indexMax);
    }

    @Override
    public Action evaluateAction(Action action) {
        // TODO: 28.02.2018 evaluate
        return action;
    }

    @Override
    public void performMove() {
        if (currentAction == Action.NO_ACTION) {
            currentAction = getNextAction();
        }

        System.out.println(currentAction);
        // TODO: 28.02.2018 check if I need to step aside
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

    public void generateRandomBehaviour() {
        considerative = Math.random();
        agressivity = Math.random();
        desiredSpeed = Math.random();
        // TODO: 28.02.2018 make better
    }

    @Override
    public void actionCompleted() {
        currentAction = Action.NO_ACTION;
    }

    public void setUp() {
        vehicle.setProgressInLane(0);
        Path path = PathUtils.getRandomPath(vehicle.getCity());

        vehicle.setPath(path);
        if (vehicle.getPath() != null && vehicle.getPath().isValid()) {
            vehicle.getPath().resetProgress();
            vehicle.setPrevGoal(vehicle.getCity().getNodeById(vehicle.getPath().getGoalAndIncrement()));
            vehicle.setCurrentGoal(vehicle.getCity().getNodeById(vehicle.getPath().getGoalAndIncrement()));

            if (vehicle.getCurrentGoal() != null && vehicle.getPrevGoal() != null) {
                Lane lane = vehicle.getPrevGoal().getLaneTo(vehicle.getCurrentGoal());
                vehicle.changeLane(lane);
            } else {
                System.out.println("BAD PATH");
            }
        } else
            System.out.println("DIE2");


    }

    private void abortAction() {
        currentAction = getNextAction();
    }

    private void surpassAction() {
        ArrayList<Lane> neighbourLanes = vehicle.getLane().getNeighbourLanes();
        if (neighbourLanes.size() == 2) {
            if (neighbourLanes.get(0).getIndex() < vehicle.getLane().getIndex())
                vehicle.changeLane(neighbourLanes.get(0));
            else
                vehicle.changeLane(neighbourLanes.get(1));
        } else {
            vehicle.changeLane(neighbourLanes.get(0));
        }
        setCurrentAction(Action.FOLLOW_LANE);
    }

    private void stepAsideAction() {

    }

    private void followLaneAction() {

        //todo from time to time evaluate other actions because --> once follow lane u are stuck until lane change
        if (vehicle.getProgressInLane() / vehicle.getLane().getLength() > 0.98) {
            //change lane or die if path end is reached
            if (vehicle.getLane().equals(vehicle.getPrevGoal().getLaneTo(vehicle.getCurrentGoal()))) {
                vehicle.setPrevGoal(vehicle.getCurrentGoal());
                vehicle.setCurrentGoal(vehicle.getCity().getNodeById(vehicle.getPath().getGoalAndIncrement()));
            }
            if (vehicle.getCurrentGoal() == null) {
                vehicle.getLane().removeVehicle(vehicle);
            } else {
                setCurrentAction(Action.NO_ACTION);
                boolean canGo = vehicle.getPrevGoal().register(vehicle, vehicle.getCurrentGoal());
                if (canGo) {
                    Lane lane = vehicle.getPrevGoal().getLaneTo(vehicle.getCurrentGoal());
                    vehicle.changeLane(lane);
                    vehicle.setProgressInLane(0);
                   // // TODO: 28.02.2018 find bug why car stops at every node and not only at cconnections. even without the code below
                    // if (!(vehicle.getPrevGoal() instanceof Connection))
                   //     vehicle.setCurrentSpeed(vehicle.getVehicleMaxSpeed() / 8);
                } else {
                    // me have to wait :(
                }
            }
        } else {
            if (vehicle.getProgressInLane() / vehicle.getLane().getLength() > 0.80) {
                //goto better lane for dir change
            }
            adjustSpeedAction(); // always adjust speed a bit
            vehicle.incrementProgressInLane(vehicle.getCurrentSpeed());
            if (vehicle.getProgressInLane() > vehicle.getLane().getLength())
                vehicle.setProgressInLane(vehicle.getLane().getLength());

        }
    }

    private void switchLaneAction() {
        System.out.println("swich");
        ArrayList<Lane> neighbourLanes = vehicle.getLane().getNeighbourLanes();
        if (neighbourLanes.size() == 2) {
            // TODO: 28.02.2018 well, which one? I chose right one
            if (neighbourLanes.get(0).getIndex() > vehicle.getLane().getIndex())
                vehicle.changeLane(neighbourLanes.get(0));
            else
                vehicle.changeLane(neighbourLanes.get(1));
        } else {
            if (!neighbourLanes.isEmpty())
                vehicle.changeLane(neighbourLanes.get(0));
        }
        setCurrentAction(Action.FOLLOW_LANE);
    }

    private void adjustSpeedAction() {
        if (desiredSpeed <= 0.1)
            desiredSpeed = 0.1;
        double goalSpeed = desiredSpeed * vehicle.getLane().getParent().getMaxSpeed() * 2;
        Vehicle nextVehicle = vehicle.getLane().getNextVehicle((int) vehicle.getProgressInLane());
        if (vehicle.getCurrentSpeed() > goalSpeed&& !(nextVehicle.getProgressInLane() - vehicle.getCurrentSpeed() - safetyDistance > vehicle.getProgressInLane())) {
            vehicle.incrementCurrentSpeed(-Math.log(goalSpeed));
        } else {
            vehicle.incrementCurrentSpeed(Math.log(goalSpeed));
        }
        currentAction= Action.FOLLOW_LANE;
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


    public VehicleDriving setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
        return this;
    }


    private double getDistanceCarBehind() {
        Vehicle prevCar = vehicle.getLane().getPrevVehicle((int) vehicle.getProgressInLane());
        if (prevCar == null)
            return 1;
        else {
            double dist = Math.abs(vehicle.getProgressInLane() - prevCar.getProgressInLane());
            return dist / vehicle.getLane().getLength();
        }
    }

    private double getDistanceCarInfront() {
        Vehicle nextVehicle = vehicle.getLane().getNextVehicle((int) vehicle.getProgressInLane());
        if (nextVehicle == null)
            return 1;
        else {
            double dist = Math.abs(vehicle.getProgressInLane() - nextVehicle.getProgressInLane());
            return dist / vehicle.getLane().getLength();
        }
    }

    private boolean isLeftLaneFree() {
        ArrayList<Lane> lanes = vehicle.getLane().getNeighbourLanes();
        if (lanes.isEmpty())
            return false;
        for (Lane lane : lanes) {
            if (lane.getIndex() < vehicle.getLane().getIndex())//left lane exists
            {
                if (isLaneFree(lane)) return true;
            }
        }
        return false;
    }

    private boolean isRightLaneFree() {
        ArrayList<Lane> lanes = vehicle.getLane().getNeighbourLanes();
        if (lanes.isEmpty())
            return false;
        for (Lane lane : lanes) {
            if (lane.getIndex() > vehicle.getLane().getIndex())//right lane exists
            {
                if (isLaneFree(lane)) return true;
            }
        }
        return false;
    }

    private boolean isLaneFree(Lane lane) {
        Vehicle prev = lane.getPrevVehicle((int) vehicle.getProgressInLane());
        Vehicle next = lane.getNextVehicle((int) vehicle.getProgressInLane());
        if (prev.getProgressInLane() + vehicle.getCurrentSpeed() + safetyDistance < vehicle.getProgressInLane()
                && next.getProgressInLane() - vehicle.getCurrentSpeed() - safetyDistance > vehicle.getProgressInLane()) {
            return true;
        }
        return false;
    }

    public double getAmpelState() {
        if (vehicle.getLane().getStreetlights().isEmpty()) {
            return 1;
        } else {
            for (Streetlight streetlight : vehicle.getLane().getStreetlights()) {
                if (streetlight.getState() == 1)
                    return 1;
            }
        }
        return 0;
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