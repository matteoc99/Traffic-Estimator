package logic.city;

import logic.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class City {
    /**
     * contains everything
     */
    private ArrayList<StreetIntersection> streetIntersections;

    public Path doDijkstra(StreetIntersection from, StreetIntersection to, Vehicle vehicle) {
        return new Path(); // TODO: 15.12.2017
    }

    /**
     * Calculate a Random path trough the city
     * Considers the Fame of Streets and StreetIntersections
     *
     * @param vehicle to knows how well the car knows the city
     * @return A Path
     */
    public Path doRandomDijkstra(Vehicle vehicle) {
        return new Path(); // TODO: 15.12.2017
    }

    /**
     * Adds a Street intersection
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addStreetIntersection(StreetIntersection streetIntersection) {
        if(contains(streetIntersection))
            return false;
        streetIntersections.add(streetIntersection);
        return true;
    }

    /**
     * Removes a Street intersection
     *
     * @return true if it was successfully removes, otherwise false
     */
    public boolean removeStreetIntersection(StreetIntersection streetIntersection) {
        if(!contains(streetIntersection))
            return false;
        streetIntersections.remove(streetIntersection);
        return true;
    }

    /**
     * Checks if this city contains a certain Street Intersection
     * @param streetIntersection the intersection to check
     * @return true if it contains the intersection, otherwise false
     */
    public boolean contains(StreetIntersection streetIntersection) {
        for (StreetIntersection intersection : streetIntersections) {
            if(streetIntersection.getUIS().equals(intersection.getUIS()))
                return true;
        }
        return false;
    }

}
