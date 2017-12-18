package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Path {

    ArrayList<StreetIntersection> streetIntersections;

    /**
     * progress index on the street intersection
     */
    private int progress;
    
    public StreetIntersection getNextGoal(){
        return streetIntersections.get(progress); // TODO: 15.12.2017  
    }
    
}
