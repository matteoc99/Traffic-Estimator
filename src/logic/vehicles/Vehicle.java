package logic.vehicles;

import logic.city.City;
import logic.city.Lane;
import logic.city.Node;
import logic.city.Path;

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
    private int streetKnowledge;

    /**
     * 0-1 character of the driver. 1 if he is really fast and careless. 0  : safe driver
     */
    private int speeder;


    private int progressInLane;

    private Node currentGoal;
    private Node prevGoal;

    private boolean isDead= false;

    public static int SICHERHEITS_ABSTAND = 30;

    public Vehicle(int weight, int maxSpeed, Path path, int streetKnowledge, int speeder) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.path = path;
        this.streetKnowledge = streetKnowledge;
        this.speeder = speeder;
        progressInLane = 0;
        currentSpeed = 0;
        addToPathStart();
        move();
    }

    public Vehicle(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    private void addToPathStart() {
        System.out.println(path);
        prevGoal = path.getGoalAndIncrement();
        currentGoal = path.getGoalAndIncrement();
        lane = prevGoal.setOnLaneTo(currentGoal);
        lane.addVehicle(this);
    }


    public void move() {
        System.out.println("MOVE: " + progressInLane);
        System.out.println("CURRENT:"+currentGoal.getId());
        System.out.println("Prev:"+prevGoal.getId());
        searchNewGoal();
        if(!isDead) {
            if (progressInLane / lane.getLength() >= 0.95) {
                System.out.println("changeLane()"+progressInLane+"::"+lane.getLength());
                currentGoal.request(this);
                changeLane(null);
                progressInLane=0;
            } else {
                if (progressInLane / lane.getLength() >= 80) {
                    //TODO change lane allowed
                }
                if (lane.getNextVehicle(progressInLane) == null || progressInLane + SICHERHEITS_ABSTAND + currentSpeed < lane.getNextVehicle(progressInLane).getProgressInLane()) {
                    if (currentSpeed < maxSpeed) {
                        currentSpeed ++;
                    } else {
                        currentSpeed--;
                    }
                } else {
                    while (currentSpeed > 0 || progressInLane + SICHERHEITS_ABSTAND + currentSpeed > lane.getNextVehicle(progressInLane).getProgressInLane()) {
                        currentSpeed--;
                    }
                }
                progressInLane += currentSpeed;
            }
        }
    }

    private void searchNewGoal() {
        if (lane == null || !lane.getToNode().equals(currentGoal)) {
            prevGoal = path.getGoalAndIncrement();
            if (prevGoal == null || prevGoal.equals(path.getTo()))
                die();
            else {
                currentGoal = path.getGoalAndIncrement();
                Lane newLane = prevGoal.setOnLaneTo(currentGoal);
                changeLane(newLane);
                progressInLane = 0;
                currentSpeed = 0;
            }
        }
    }

    private void die() {
        isDead=true;
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
        move();
    }

    public Point getPointOnLane(){
        Point ret = new Point();
        double x=progressInLane*Math.cos(lane.getParent().getDegrees());
        double y=progressInLane*Math.sin(lane.getParent().getDegrees());
        ret.setLocation(x,y);
        return ret;
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

    public int getStreetKnowledge() {
        return streetKnowledge;
    }

    public void setStreetKnowledge(int streetKnowledge) {
        this.streetKnowledge = streetKnowledge;
    }

    public int getSpeeder() {
        return speeder;
    }

    public void setSpeeder(int speeder) {
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
}
