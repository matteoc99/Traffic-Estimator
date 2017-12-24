package logic.city;

import logic.PositionableComponent;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Congestion extends PositionableComponent {
    Street affectedStreet;
    ArrayList<Lane> affectedLanes;

    /**
     * flowAmount from 0-100%
     * 100 Max
     */
    int flowAmount;

    /**
     * time in min needed to clean the Congestion
     */
    int clearanceTime;

}
