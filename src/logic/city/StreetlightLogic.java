package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 29.01.2018
 */
public class StreetlightLogic extends Thread {
    private MultiConnection parent;
    private ArrayList<Streetlight> streetlights;

    private static final int cycle = 10;

    public StreetlightLogic(String name, MultiConnection parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public void run() {
        //setGreen -> Yellow
        ArrayList<Streetlight> green=getGreenStreetLights();
        for (int i = 0; i < green.size(); i++) {
            green.get(i).setState(Streetlight.state.YELLOW);
        }

        //setYellow -> Red
        ArrayList<Streetlight> yellow=getYellowStreetLights();
        for (int i = 0; i < green.size(); i++) {
            green.get(i).setState(Streetlight.state.YELLOW);
        }

        // TODO: 23.02.2018


        try {
            Thread.sleep(cycle*1000/streetlights.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Streetlight> getGreenStreetLights(){
        ArrayList<Streetlight> ret = new ArrayList<>();
        for (Streetlight streetlight: streetlights) {
            if(streetlight.getState()== Streetlight.state.GREEN)
                ret.add(streetlight);
        }
        return ret;
    }
    public ArrayList<Streetlight> getRedStreetLights(){
        ArrayList<Streetlight> ret = new ArrayList<>();
        for (Streetlight streetlight: streetlights) {
            if(streetlight.getState()== Streetlight.state.RED)
                ret.add(streetlight);
        }
        return ret;
    }
    public ArrayList<Streetlight> getYellowStreetLights(){
        ArrayList<Streetlight> ret = new ArrayList<>();
        for (Streetlight streetlight: streetlights) {
            if(streetlight.getState()== Streetlight.state.YELLOW)
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
