package logic.vehicles;

import logic.PositionableComponent;
import logic.city.City;
import logic.city.Lane;
import logic.city.Path;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Vehicle extends PositionableComponent{

    /**
     * lane where the car is currently driving
     */
    private Lane lane;

    private int weight;
    private int maxSpeed;
    /**
     * 0-lane.length
     * position on lane
     */
    private int progressInLane;
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


    public Vehicle(Lane lane, int weight, int maxSpeed, Path path, int streetKnowledge, int speeder) {
        this.lane = lane;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.path = path;
        this.streetKnowledge = streetKnowledge;
        this.speeder = speeder;
        lane.addVehicle(this);

    }
    public Vehicle(Lane lane, int weight, int maxSpeed, int streetKnowledge, int speeder) {
        this(lane,weight, maxSpeed,null, streetKnowledge, speeder);
        this.path = City.doRandomDijkstra(this);
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
}
