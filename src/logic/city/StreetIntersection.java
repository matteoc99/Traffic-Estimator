package logic.city;

import logic.PositionableComponent;
import logic.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetIntersection extends PositionableComponent {

    private static ArrayList<String> uiss= new ArrayList<>();
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


    public StreetIntersection(PositionableComponent positionableComponent, int fame, String UIS) {
        super(positionableComponent.getX(), positionableComponent.getY(),
                positionableComponent.getWidth(), positionableComponent.getHeight());
        streets = new ArrayList<>();
        this.fame = fame;
        this.UIS = UIS;
        if(alreadyExistsUIS(UIS))
            System.out.println("ERROR:UID ALREADY EXISTS1");
    }

    public StreetIntersection(int x, int y, int width, int height, int fame, String UIS) {
        super(x, y, width, height);
        streets = new ArrayList<>();
        this.fame = fame;
        this.UIS = UIS;
        if(alreadyExistsUIS(UIS))
            System.out.println("ERROR:UID ALREADY EXISTS2");
    }

    /**
     * Adds a Street
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addStreet(Street street) {
        if(contains(street)) {
            System.out.println("StreetIntersection 45");
            return false;
        }
        streets.add(street);
        return true;
    }

    /**
     * Removes a Street
     *
     * @return true if it was successfully removed, otherwise false
     */
    public boolean removeStreet(Street street) {
        if(!contains(street)) {
            System.out.println("StreetIntersection 62");
            return false;
        }
        streets.remove(street);
        return true;
    }

    /**
     * Checks if this Intersection contains a certain Street
     * @param street the street to check
     * @return true if it contains the street, otherwise false
     */
    public boolean contains(Street street) {
        for (Street s : streets) {
            if(street.getFrom().equals(s.getFrom())
                    &&street.getTo().equals(s.getTo())
                    &&street.getFame()==street.getFame()
                    &&street.getMaxSpeed()==s.getMaxSpeed()) {
                System.out.println("Contains 90");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the UIS exists
     * @param uis the uis to check
     * @return true if the uis exists, otherwise false
     */
    public boolean alreadyExistsUIS(String uis){
        for (String s: uiss) {
            if(s.equals(uis))
                return true;
        }
        return false;
    }

    /**
     * Adds a UIS
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addUIS(String s) {
        if(alreadyExistsUIS(s)) {
            System.out.println("StreetIntersection 117");
            return false;
        }
        uiss.add(s);
        return true;
    }

    /**
     * Removes a UIS
     *
     * @return true if it was successfully removed, otherwise false
     */
    public boolean removeUIS(String s) {
        if(!alreadyExistsUIS(s)) {
            System.out.println("StreetIntersection 131");
            return false;
        }
        uiss.remove(s);
        return true;
    }

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

    public ArrayList<Street> getStreets() {
        return streets;
    }
}
