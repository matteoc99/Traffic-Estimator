package logic.vehicles;

import NeuralNetworkLibrary.src.network.Network;
import logic.city.City;
import logic.city.Lane;
import logic.city.Node;
import logic.city.Path;
import logic.vehicles.driving.Character;
import logic.vehicles.driving.VehicleDriving;
import utils.math.Position;

import java.awt.*;

import static main.Main.SPEED_TRIM;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Vehicle {

    /**
     * lane where the car is currently driving
     */
    private Lane lane;

    /**
     * width of the car
     */
    private int weight;

    /**
     * length of the car
     */
    private int length;


    /**
     * maximum speed that the car can drive
     */
    private double vehicleMaxSpeed;

    /**
     * the actual speed right now
     */
    private double currentSpeed = 0;


    /**
     * desired path of the car
     */
    private Path path;

    /**
     * how much the car has driven on this lane
     */
    private double progressInLane = 0;

    /**
     * where I want to
     */
    private Node currentGoal;
    /**
     * where I came from
     */
    private Node prevGoal;

    /**
     * paintwork of the car
     */
    private Color color = Color.RED;

    /**
     * driving behaviour of the car
     */
    private VehicleDriving vehicleDriving;

    /**
     * The city I drive into
     */
    private City city;


    public Vehicle(City city) {
        this.city = city;
        vehicleDriving = new VehicleDriving(this);
    }


    /**
     * move method called every frame
     */
    public void move() {
        if (vehicleDriving == null)
            throw new RuntimeException("NO DRIVING BEHAVIOUR");
        vehicleDriving.performMove();

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


    /**
     * Get the Point relative to the progressInLane and lane position
     *
     * @return the point where to draw the car
     */
    public Position currentPositionOnLane() {
        if (lane != null)
            return lane.getPointByProgress(progressInLane);
        return null;
    }


    public Lane getLane() {
        return lane;
    }


    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getVehicleMaxSpeed() {
        return vehicleMaxSpeed;
    }

    public void setVehicleMaxSpeed(double vehicleMaxSpeed) {
        this.vehicleMaxSpeed = vehicleMaxSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public double getProgressInLane() {
        return progressInLane;
    }

    public void setProgressInLane(double progressInLane) {
        this.progressInLane = progressInLane;
    }

    public Node getCurrentGoal() {
        return currentGoal;
    }

    public void setCurrentGoal(Node currentGoal) {
        this.currentGoal = currentGoal;
    }

    public Node getPrevGoal() {
        return prevGoal;
    }

    public void setPrevGoal(Node prevGoal) {
        this.prevGoal = prevGoal;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VehicleDriving getVehicleDriving() {
        return vehicleDriving;
    }

    public void setVehicleDriving(VehicleDriving vehicleDriving) {
        this.vehicleDriving = vehicleDriving;
    }

    public void incrementCurrentSpeed(double v) {
        currentSpeed += v;
        if (currentSpeed < 0)
            currentSpeed = 0;
    }

    public void incrementProgressInLane(double currentSpeed) {
        progressInLane += currentSpeed / SPEED_TRIM;
    }

    public City getCity() {
        return city;
    }

    public Network getNetwork() {
        return vehicleDriving.getNetwork();
    }
}
