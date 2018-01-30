package logic.vehicles;

import logic.city.City;
import logic.city.Lane;
import logic.city.Path;

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


    public Vehicle(Lane lane, int weight, int maxSpeed, Path path, int streetKnowledge, int speeder) {
        this.lane = lane;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.path = path;
        this.streetKnowledge = streetKnowledge;
        this.speeder = speeder;
        lane.addVehicle(this);
        progressInLane=0;
    }
    public Vehicle(Lane lane, int weight, int maxSpeed, int streetKnowledge, int speeder) {
        this(lane,weight, maxSpeed, null, streetKnowledge, speeder);
        this.path = getParent().getParent().getParent().doRandomPathFinding(this);
    }
    public Vehicle(Lane lane, int maxSpeed, int streetKnowledge, int speeder) {
       this(lane,1500,maxSpeed,streetKnowledge,speeder);
    }


    /**
     * @return the current driving lane
     */
    public Lane getParent(){
        return lane;
    }

    /**
     * Change the driving lane
     * @param newLane the new Lane where the car goes
     */
    public void changeLane(Lane newLane){
        lane.removeVehicle(this);
        newLane.addVehicle(this);
        lane=newLane;
    }

    public ArrayList<Lane> getNeighbourLanes(){
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
