package logic.vehicles.driving;

import NeuralNetworkLibrary.src.network.Network;
import logic.city.Connection;
import logic.city.Lane;
import logic.city.Path;
import logic.city.Streetlight;
import logic.vehicles.Vehicle;
import utils.NetworkUtils;
import utils.PathUtils;

import java.util.ArrayList;
import java.util.Arrays;

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



    public VehicleDriving(Vehicle vehicle) {
        this.vehicle = vehicle;
        setUp();
        generateRandomBehaviour();

        if (network == null) {
           /* DataSets dataSets = new DataSets();
            dataSets.generateDataSets();
            network = new Network(7, 4, 1, new int[]{6});
            System.out.println("started training");
            network.train(dataSets.getDataset());

            */
            network = NetworkUtils.getRandomNetwork();
        }
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

        return Action.FOLLOW_LANE;
    }

    @Override
    public Action evaluateAction(Action action) {
        switch (action) {
            case SURPASS:
                ArrayList<Lane> neighbourLanes = vehicle.getLane().getNeighbourLanes();
                if (!neighbourLanes.isEmpty() && (neighbourLanes.size() == 2 || neighbourLanes.get(0).getIndex() < vehicle.getLane().getIndex())) {
                    return action;
                } else {
                    return evaluateAction(Action.FOLLOW_LANE);
                }
            case FOLLOW_LANE:
                return action;

            case SWITCH_LANE:
                neighbourLanes = vehicle.getLane().getNeighbourLanes();
                if (!neighbourLanes.isEmpty() && (neighbourLanes.size() == 2 || neighbourLanes.get(0).getIndex() > vehicle.getLane().getIndex())) {
                    return action;
                } else {
                    return evaluateAction(Action.FOLLOW_LANE);
                }
            case ADJUST_SPEED:
                double goalSpeed = desiredSpeed * vehicle.getLane().getParent().getMaxSpeed() * 2;
                if (goalSpeed - 5 < vehicle.getCurrentSpeed() && goalSpeed + 5 > vehicle.getCurrentSpeed()) {
                    return evaluateAction(Action.FOLLOW_LANE);
                } else {
                    return action;
                }
        }
        return Action.NO_ACTION;
    }

    @Override
    public void performMove() {
        if (vehicle.getLane() != null) {

            if (currentAction == Action.NO_ACTION) {
                currentAction = getNextAction();
            }

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
        } else {
            vehicle.getCity().getVehicles().remove(vehicle);
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
            vehicle.getCity().getVehicles().add(vehicle);
            vehicle.getPath().resetProgress();
            vehicle.setPrevGoal(vehicle.getCity().getNodeById(vehicle.getPath().getGoalAndIncrement()));
            vehicle.setCurrentGoal(vehicle.getCity().getNodeById(vehicle.getPath().getGoalAndIncrement()));

            if (vehicle.getCurrentGoal() != null && vehicle.getPrevGoal() != null) {
                Lane lane = vehicle.getPrevGoal().getLaneTo(vehicle.getCurrentGoal());
                vehicle.changeLane(lane);
            } else {
                System.out.println("BAD PATH:");
                System.out.println("\t"+path);
                System.out.println();
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
        actionCompleted();
    }

    private void stepAsideAction() {
        actionCompleted();
    }

    private void followLaneAction() {

        //todo from time to time evaluate other actions because --> once follow lane u are stuck until lane change
        if (vehicle.getProgressInLane() / vehicle.getLane().getLength() > 0.95) {
            //change lane or die if path end is reached
            if (vehicle.getLane().equals(vehicle.getPrevGoal().getLaneTo(vehicle.getCurrentGoal()))) {
                vehicle.setPrevGoal(vehicle.getCurrentGoal());
                vehicle.setCurrentGoal(vehicle.getCity().getNodeById(vehicle.getPath().getGoalAndIncrement()));
            }
            if (vehicle.getCurrentGoal() == null) {
                vehicle.getLane().removeVehicle(vehicle);
                vehicle.getCity().getVehicles().remove(vehicle);
            } else {
                boolean canGo = vehicle.getPrevGoal().register(vehicle, vehicle.getCurrentGoal());
                if (canGo) {
                    Lane lane = vehicle.getPrevGoal().getLaneTo(vehicle.getCurrentGoal());
                    vehicle.changeLane(lane);
                    vehicle.setProgressInLane(0);
                    if (!(vehicle.getPrevGoal() instanceof Connection))
                        vehicle.setCurrentSpeed(vehicle.getVehicleMaxSpeed() / 8);
                } else {
                    // me have to wait :(
                }
            }
        } else {
            if (vehicle.getProgressInLane() / vehicle.getLane().getLength() > 0.80) {
                //goto better lane for dir change
            }
            adjustSpeedAction(); //todo remove once decision making is good again
            vehicle.incrementProgressInLane(vehicle.getCurrentSpeed());
            if (vehicle.getProgressInLane() > vehicle.getLane().getLength())
                vehicle.setProgressInLane(vehicle.getLane().getLength());

        }
        actionCompleted();
    }

    private void switchLaneAction() {
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

        actionCompleted();
    }

    private void adjustSpeedAction() {
        if (desiredSpeed <= 0.1)
            desiredSpeed = 0.1;
        double goalSpeed = desiredSpeed * vehicle.getLane().getParent().getMaxSpeed() * 2;
        Vehicle nextVehicle = vehicle.getLane().getNextVehicle(vehicle.getProgressInLane());
        double nextVehiclePos = nextVehicle == null ? vehicle.getLane().getLength() : nextVehicle.getProgressInLane();


        if (vehicle.getCurrentSpeed() > goalSpeed || nextVehiclePos < vehicle.getProgressInLane() + vehicle.getCurrentSpeed() + getSafetyDist() ) {
            while (vehicle.getCurrentSpeed() > goalSpeed || nextVehiclePos < vehicle.getProgressInLane() + vehicle.getCurrentSpeed() + getSafetyDist() ) {
                vehicle.incrementCurrentSpeed(-Math.log(goalSpeed));
                if(vehicle.getCurrentSpeed()==0)
                    break;
            }
        } else {
            vehicle.incrementCurrentSpeed(Math.log(goalSpeed));
        }
        actionCompleted();
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
        Vehicle prevCar = vehicle.getLane().getPrevVehicle(vehicle.getProgressInLane());
        if (prevCar == null)
            return 1;
        else {
            double dist = Math.abs(vehicle.getProgressInLane() - prevCar.getProgressInLane());
            return dist / vehicle.getLane().getLength();
        }
    }

    private double getDistanceCarInfront() {
        Vehicle nextVehicle = vehicle.getLane().getNextVehicle(vehicle.getProgressInLane());
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
        Vehicle prev = lane.getPrevVehicle(vehicle.getProgressInLane());
        Vehicle next = lane.getNextVehicle(vehicle.getProgressInLane());

        if (prev != null && prev.getProgressInLane() + vehicle.getCurrentSpeed() + getSafetyDist() < vehicle.getProgressInLane()
                || next != null && next.getProgressInLane() - vehicle.getCurrentSpeed() - getSafetyDist() > vehicle.getProgressInLane()) {
            return true;
        }
        return false;
    }

    public double getAmpelState() {
        if (vehicle.getLane().getStreetlight() == null) {
            return 1;
        } else {
            return vehicle.getLane().getStreetlight().getState();
        }
    }


    public Network getNetwork() {
        return network;
    }

    public int getSafetyDist(){
        int ret= (int) (vehicle.getCurrentSpeed()/6)+2;
        return ret;
    }
}