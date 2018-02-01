package gui.city;

import logic.city.City;

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

    private ArrayList<JNode> jNodes;


    public JCity(City city) {
        jNodes = new ArrayList<>();
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
        for (int i = 0; i < city.getNodes().size(); i++) {
            JNode jNode = new JNode(city.getNodes().get(i), this);
            jNodes.add(jNode);
        }
    }

    public ArrayList<JStreet> getJStreets() {
        ArrayList<JStreet> ret = new ArrayList<>();
        for (JNode jNode : jNodes) {
            ArrayList<JStreet>streets =jNode.getjStreets();
            for (JStreet street : streets) {
                if (!ret.contains(street)) {
                    ret.add(street);
                }
            }
        }
        return ret;
    }


    public void reposition() {
        for (JNode jNode : jNodes) {
            jNode.reposition();
        }
        ArrayList<JStreet>streets=getJStreets();
        for (JStreet street : streets) {
            street.reposition();
        }
    }
}
