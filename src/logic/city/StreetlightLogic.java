package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class StreetlightLogic extends Thread {
    private MultiConnection parent;
    private ArrayList<Streetlight> streetlights;

    //circular selection
    private static final int cycle = 10;


    public StreetlightLogic(String name, MultiConnection parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public void run() {

        //set one red -> green
        getNextGreen().toggle();

        //setGreen -> Red
        getFirstGreenStreetLight().toggle();



        try {
            Thread.sleep(cycle * 1000 / streetlights.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Streetlight getNextGreen() {
        for (int i = 0; i < streetlights.size(); i++) {
            int gIndex = streetlights.indexOf(getFirstGreenStreetLight());
            if (gIndex == -1)
                return null;
            if (gIndex < streetlights.size() - 1) {
                return streetlights.get(gIndex + 1);
            } else {
                if (gIndex == streetlights.size() - 1)
                    return streetlights.get(0);
                else
                    throw new RuntimeException("WTF?? should not be happening");
            }
        }
        return null;
    }

    public Streetlight getFirstGreenStreetLight() {
        for (Streetlight streetlight : streetlights) {
            if (streetlight.getState() == 1)
                return streetlight;
        }
        return null;
    }

    public ArrayList<Streetlight> getGreenStreetLights() {
        ArrayList<Streetlight> ret = new ArrayList<>();
        for (Streetlight streetlight : streetlights) {
            if (streetlight.getState() == 1)
                ret.add(streetlight);
        }
        return ret;
    }

    public ArrayList<Streetlight> getRedStreetLights() {
        ArrayList<Streetlight> ret = new ArrayList<>();
        for (Streetlight streetlight : streetlights) {
            if (streetlight.getState() == 0)
                ret.add(streetlight);
        }
        return ret;
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
