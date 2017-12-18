package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Street extends StreetComponent{
    /**
     * All lanes that have to be drawn
     * cars on lanes have to be drawn to
     */
    ArrayList<Lane> lanes;

    StreetIntersection from;
    StreetIntersection to;


    int maxSpeed;
    int minSpeed;

    /**
     * how famous this street is 0-1
     * 1 max => very famous
     */
    int fame;

    /**
     * Congestions on this street
     */
    ArrayList<Congestion> congestions;



    public void moveCars(){
        // TODO: 15.12.2017
    }



    
}
