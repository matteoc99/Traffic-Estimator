package gui.city;

import logic.Utils;
import logic.city.*;
import logic.vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static gui.Main.prevZoom;
import static gui.Main.zoom;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JCity extends JPanel {

    /**
     * reference
     */
    private City city;
    ArrayList<Street> streets;
    ArrayList<Node> nodes;


    public JCity(City city) {
        this.city = city;
        streets = city.getStreets();
        nodes = city.getNodes();

        setLayout(null);
        setBounds(city.getBounds());
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    protected void paintComponent(Graphics g) {

        //drawNodes
      /*  for (Node node : nodes) {
            g.setColor(Color.BLUE);
            g.drawOval(node.getX(),node.getY(),8,8);
        }*/

        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        //drawStreets
        for (Street street : streets) {
            g2.setColor(Color.BLACK);
            g2.drawLine((int) (street.getxFrom() * zoom), (int) (street.getyFrom() * zoom), (int) (street.getxTo() * zoom), (int) (street.getyTo() * zoom));
            g2.setColor(Color.RED);
            //drawcars
            for (Lane lane : street.getForwardLanes()) {
                for (Vehicle vehicle : lane.getVehicles()) {
                    g.fillOval((int) ((vehicle.getPointOnLane().x)*zoom)-10,
                            (int) ((vehicle.getPointOnLane().y)*zoom)-10,
                            20+(int)(zoom), 20+(int)(zoom));
                }
            }
            //drawcars
            for (Lane lane : street.getBackwardLanes()) {
                for (Vehicle vehicle : lane.getVehicles()) {
                    g.fillOval((int) ((vehicle.getPointOnLane().x)*zoom)-10,
                            (int) ((vehicle.getPointOnLane().y)*zoom)-10,
                            20+(int)(zoom), 20+(int)(zoom));

                }
            }
        }

        setSize((int) (city.getBounds().width * zoom), (int) (city.getBounds().height * zoom));
    }
}
