package logic.city;

import java.util.ArrayList;

import static logic.city.City.SPEED;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class StreetlightLogic extends Thread {
    private Node parent;
    private ArrayList<Streetlight> streetlights = new ArrayList<>();

    //circular selection
    private static final int cycle = 10;


    public StreetlightLogic(String name, Node parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public void run() {

        while (true) {
            if (streetlights.size() > 0) {
                //set one red -> green and green -> red
                getNextGreen().toggle();

                try {
                    Thread.sleep(cycle * 1000 / streetlights.size()-(SPEED-20));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                // TODO: 06.03.2018 effizienter mit wait & notify
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Streetlight getNextGreen() {
        int gIndex = streetlights.indexOf(getFirstGreenStreetLight());
        if (gIndex == -1) {
            if (streetlights.size() > 0) {
                return streetlights.get(0);
            } else {
                return null;
            }
        }else {
            streetlights.get(gIndex).setState(0);
            if (gIndex < streetlights.size() - 1) {
                return streetlights.get(gIndex + 1);
            } else {
                if (gIndex == streetlights.size() - 1)
                    return streetlights.get(0);
                else
                    throw new RuntimeException("WTF?? should not be happening");
            }
        }
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

    public Node getParent() {
        return parent;
    }

    public ArrayList<Streetlight> getStreetlights() {
        return streetlights;
    }
}
