package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class StreetlightLogic extends Thread {
    private MultiConnection parent;
    private ArrayList<Streetlight> streetlights;


    public StreetlightLogic(String name, MultiConnection parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public void run() {
        // TODO: 29.01.2018
        super.run();
    }

    public void addStreetlight(Streetlight streetlight) {
        if (!contains(streetlight)) {
            streetlights.add(streetlight);
        } else {
            throw new RuntimeException("already contains light");
        }
    }

    public void removeStreetlight(Streetlight streetlight) {
        if (contains(streetlight)) {
            streetlights.remove(streetlight);
        } else {
            throw new RuntimeException("NOTHING TO REMOVE LOGIC 30");
        }
    }

    public boolean contains(Streetlight streetlight) {
        for (Streetlight light : streetlights) {
            if (streetlight.equals(light)) {
                return true;
            }
        }
        return false;
    }

    public MultiConnection getParent() {
        return parent;
    }

    public ArrayList<Streetlight> getStreetlights() {
        return streetlights;
    }
}
