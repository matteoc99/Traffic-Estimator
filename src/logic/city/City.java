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
    ArrayList<StreetIntersection> streetIntersections;

    public Path doDijkstra(StreetIntersection from, StreetIntersection to, Vehicle vehicle){
        return new Path(); // TODO: 15.12.2017
    }

    /**
     * Calculate a Random path trough the city
     * Considers the Fame of Streets and StreetIntersections
     * @param vehicle to knows how well the car knows the city
     * @return A Path
     */
    public Path doRandomDijkstra(Vehicle vehicle){
        return new Path(); // TODO: 15.12.2017
    }
}
