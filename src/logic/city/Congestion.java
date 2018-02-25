package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Congestion {

    private Street affectedStreet;
    private ArrayList<Lane> affectedLanes;

    /**
     * flowAmount from 0-100%
     * 100 Max
     */
    private int flowAmount;

    /**
     * time in min needed to clean the Congestion
     */
    private int clearanceTime;

    /**
     * 0-lane.length
     * position on lane
     */
    private int progressInLane;


    public Congestion(Street affectedStreet, int flowAmount, int clearanceTime, int progressInLane) {
        this.affectedStreet = affectedStreet;
        this.flowAmount = flowAmount;
        this.clearanceTime = clearanceTime;
        this.progressInLane = progressInLane;
        affectedStreet.addCongestion(this);
        //TODO affected lanes???
    }


    public void kill() {
        affectedStreet.removeCongestion(this);
    }


    public Street getAffectedStreet() {
        return affectedStreet;
    }


    public void changeAffectedStreet(Street affectedStreet) {
        this.affectedStreet.removeCongestion(this);
        affectedStreet.addCongestion(this);
        this.affectedStreet = affectedStreet;

    }

    public int getProgressInLane() {
        return progressInLane;
    }

    public void setProgressInLane(int progressInLane) {
        this.progressInLane = progressInLane;
    }

    public int getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(int flowAmount) {
        this.flowAmount = flowAmount;
    }

    public int getClearanceTime() {
        return clearanceTime;
    }

    public void setClearanceTime(int clearanceTime) {
        this.clearanceTime = clearanceTime;
    }
}
