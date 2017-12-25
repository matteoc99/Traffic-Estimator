package gui.city;

import logic.city.City;
import logic.city.StreetIntersection;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JCity extends JPanel {

    /**
     * reference
     */
    City city;

    private ArrayList<JStreetIntersection> jStreetIntersections;


    public JCity(City city) {
        jStreetIntersections = new ArrayList<>();
        setLayout(null);
        this.city = city;
        create();
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    private void create() {
        for (int i = 0; i < city.getStreetIntersections().size(); i++) {
            JStreetIntersection jStreetIntersection = new JStreetIntersection(city.getStreetIntersections().get(i),this);
            addJStreetIntersection(jStreetIntersection);
        }
    }

    /**
     * Adds a JStreetIntersection
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addJStreetIntersection(JStreetIntersection jStreetIntersection) {
        if(contains(jStreetIntersection)) {
            System.out.println("JCITY 49");
            return false;
        }
        jStreetIntersections.add(jStreetIntersection);
        add(jStreetIntersection);
        return true;
    }

    /**
     * Removes a JStreetIntersection
     *
     * @return true if it was successfully removed, otherwise false
     */
    public boolean removeJStreetIntersection(JStreetIntersection jStreetIntersection) {
        if(!contains(jStreetIntersection)) {
            System.out.println("CITY 59");
            return false;
        }
        jStreetIntersections.remove(jStreetIntersection);
        remove(jStreetIntersection);
        return true;
    }

    /**
     * Checks if this jcity contains a certain JStreetIntersection
     * @param jStreetIntersection the intersection to check
     * @return true if it contains the intersection, otherwise false
     */
    public boolean contains(JStreetIntersection jStreetIntersection) {
        for (JStreetIntersection intersection : jStreetIntersections) {
            if(jStreetIntersection.equals(intersection))
                return true;
        }
        return false;
    }

}
