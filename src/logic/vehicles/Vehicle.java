package logic.vehicles;

import logic.city.*;
import utils.Stopwatch;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Vehicle {

    /**
     * lane where the car is currently driving
     */
    private Lane lane;

    private int weight;
    private int width;
    private int height;

    private int maxSpeed;
    private int currentSpeed;

    /**
     * lane.length
     */
    private int maxProgress;

    private Path path;

    /**
     * 0-1 knowledge of all the streets in BZ
     */
    private double streetKnowledge;

    /**
     * 0-1 character of the driver. 1 if he is really fast and careless. 0  : safe driver
     */
    private double speeder;

    private int progressInLane;

    private Node currentGoal;
    private Node prevGoal;


    public int safetyDistance = 10;

    public Color color=Color.RED;

    public Vehicle(int weight, int maxSpeed, Path path, double streetKnowledge, double speeder, City city) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.path = path;
        this.streetKnowledge = streetKnowledge;
        this.speeder = speeder;
        progressInLane = 0;
        currentSpeed = 2;
       calcSafetyDistance(currentSpeed);

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
    }

    private void calcSafetyDistance(int currentSpeed) {
        safetyDistance=2;
        safetyDistance*=speeder/currentSpeed;
        safetyDistance+=4;
    }


    public Vehicle(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


    public void move() {
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
    }


    /**
     * @return the current driving lane
     */
    public Lane getParent() {
        return lane;
    }

    /**
     * Change the driving lane
     *
     * @param newLane the new Lane where the car goes
     */
    public void changeLane(Lane newLane) {
        if (this.lane != null)
            lane.removeVehicle(this);
        if (newLane != null)
            newLane.addVehicle(this);
        lane = newLane;
    }

    public Point currentPointOnLane() {
        Point ret = new Point();
        int deltax = lane.getFromNode().getX() - lane.getToNode().getX();
        int deltay = lane.getFromNode().getY() - lane.getToNode().getY();
        double x = lane.getFromNode().getX() - deltax * (progressInLane / lane.getLength());
        double y = lane.getFromNode().getY() - deltay * (progressInLane / lane.getLength());
        ret.setLocation(x, y);
        return ret;

        /*

        int fromX = lane.getFromNode().getX();
        int fromY = lane.getFromNode().getY();
        int toX = lane.getToNode().getX();
        int toY = lane.getToNode().getY();
        int x = (toX - fromX) * progressInLane + fromX;
        int y = (toY - fromY) * progressInLane + fromY;

        return new Point(x, y);
*/
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public ArrayList<Lane> getNeighbourLanes() {
        return lane.getNeighbourLanes();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getStreetKnowledge() {
        return streetKnowledge;
    }

    public void setStreetKnowledge(double streetKnowledge) {
        this.streetKnowledge = streetKnowledge;
    }

    public double getSpeeder() {
        return speeder;
    }

    public void setSpeeder(double speeder) {
        this.speeder = speeder;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getProgressInLane() {
        return progressInLane;
    }

    public void setProgressInLane(int progressInLane) {
        this.progressInLane = progressInLane;
    }

    public void incrementProgrssInLane(int inc) {
        progressInLane += inc / 10;//// TODO: 17.02.2018 trimm
    }

    public Lane getLane() {
        return lane;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
