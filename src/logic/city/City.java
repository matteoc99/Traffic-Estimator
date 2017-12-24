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

    private String name;

    public City(String name) {
        this.name = name;
    }

    public static Path doDijkstra(StreetIntersection from, StreetIntersection to, Vehicle vehicle) {
        return new Path(); // TODO: 15.12.2017
    }

    /**
     * Calculate a Random path trough the city
     * Considers the Fame of Streets and StreetIntersections
     *
     * @param vehicle to knows how well the car knows the city
     * @return A Path
     */
    public static Path doRandomDijkstra(Vehicle vehicle) {
        return new Path(); // TODO: 15.12.2017
    }

    /**
     * Adds a Street intersection
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addStreetIntersection(StreetIntersection streetIntersection) {
        if(contains(streetIntersection)) {
            System.out.println("CITY 45");
            return false;
        }
        streetIntersections.add(streetIntersection);
        streetIntersection.addUIS(streetIntersection.getUIS());
        return true;
    }

    /**
     * Removes a Street intersection
     *
     * @return true if it was successfully removed, otherwise false
     */
    public boolean removeStreetIntersection(StreetIntersection streetIntersection) {
        if(!contains(streetIntersection)) {
            System.out.println("CITY 59");
            return false;
        }
        streetIntersections.remove(streetIntersection);
        streetIntersection.removeUIS(streetIntersection.getUIS());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
