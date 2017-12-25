package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Path {

    ArrayList<StreetIntersection> streetIntersections;

    public Path() {
        streetIntersections = new ArrayList<>();
    }

    /**
     * progress index on the street intersection
     */
    private int progress;
    
    public StreetIntersection getNextGoal(){
        progress++;
        return streetIntersections.get(progress-1);
    }
    
}
