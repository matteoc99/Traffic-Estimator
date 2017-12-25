package gui.city;

import gui.Main;
import logic.city.Street;
import logic.city.StreetIntersection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JStreetIntersection extends JPanel {


    /**
     * Reference
     */
    private StreetIntersection streetIntersection;

    private ArrayList<JStreet> jStreets;

    private JPanel parent;

    public JStreetIntersection(StreetIntersection streetIntersection,JPanel parent) {
        jStreets= new ArrayList<>();
        setLayout(null);
        this.streetIntersection = streetIntersection;
        this.parent=parent;
        setBackground(Color.GREEN);
        setBounds(streetIntersection.getBounds());
        create();

    }

    private void create() {
        for (int i = 0; i < streetIntersection.getStreets().size(); i++) {
            Street street = streetIntersection.getStreets().get(i);
            JStreet jStreet= new JStreet(street);
            addJStreet(jStreet);
        }
    }

    /**
     * Adds a JStreet
     *
     * @return true if it was successfully added, otherwise false
     */
    public boolean addJStreet(JStreet jStreet) {
        if(contains(jStreet)) {
            System.out.println("JStreetIntersection 48");
            return false;
        }
        jStreets.add(jStreet);
        getParent().add(jStreet);
        getParent().repaint();
        return true;
    }

    /**
     * Removes a JStreet
     *
     * @return true if it was successfully removed, otherwise false
     */
    public boolean removeJStreet(JStreet jStreet) {
        if(!contains(jStreet)) {
            System.out.println("JStreetIntersection 62");
            return false;
        }
        jStreets.remove(jStreet);
        getParent().add(jStreet);
        getParent().repaint();
        return true;
    }

    /**
     * Checks if this {@link JStreetIntersection} contains a certain JStreet
     * @param jStreet the street to check
     * @return true if it contains the street, otherwise false
     */
    public boolean contains(JStreet jStreet) {
        for (JStreet s : jStreets) {
            if(jStreet.equals(s)) {
                System.out.println("JStreetIntersection 77");
                return true;
            }
        }
        return false;
    }

    public StreetIntersection getStreetIntersection() {
        return streetIntersection;
    }

    public void setStreetIntersection(StreetIntersection streetIntersection) {
        this.streetIntersection = streetIntersection;
    }

    @Override
    public JPanel getParent() {
        return parent;
    }

    public void setParent(JPanel parent) {
        this.parent = parent;
    }
}
