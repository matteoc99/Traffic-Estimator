package logic.city;

import logic.PositionableComponent;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetIntersection extends PositionableComponent {
    /**
     * Connects Street
     */
    private ArrayList<Street> streets;

    /**
     * fame of this Intersection as Path start or goal
     * 0-endless
     */
    private int fame;

    /**
     * UNIQUE IDENTIFICATION STRING
     */
    private String UIS;


    public String getUIS() {
        return UIS;
    }

    public void setUIS(String UIS) {
        this.UIS = UIS;
    }

    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    /**
     * Adds a Street
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addStreet(Street street) {
        if(contains(street))
            return false;
        streets.add(street);
        return true;
    }

    /**
     * Removes a Street
     *
     * @return true if it was successfully removes, otherwise false
     */
    public boolean removeStreet(Street street) {
        if(!contains(street))
            return false;
        streets.remove(street);
        return true;
    }

    /**
     * Checks if this city contains a certain Street
     * @param street the street to check
     * @return true if it contains the street, otherwise false
     */
    public boolean contains(Street street) {
        for (Street s : streets) {
            if(street.equals(s))
                return true;
        }
        return false;
    }

}
