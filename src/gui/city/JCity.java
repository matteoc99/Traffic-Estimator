package gui.city;

import logic.city.City;
import logic.city.Lane;
import logic.city.Street;
import logic.vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import static main.Main.preciseZoom;
import static main.Main.zoom;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JCity extends JPanel {

    /**
     * reference
     */
    private City city;

    boolean firsttime = true;

    public JCity(City city) {
        this.city = city;

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

        g2.setColor(new Color(55, 55, 55));
        g2.fillRect(0, 0, getWidth(), getHeight());
        //drawStreets
        Iterator<Street> streets = city.getStreetIterator();
        while (streets.hasNext()) {
            Street street = streets.next();
            Iterator<Lane> forwardLanes = street.getForwardLanesIterator();
            for (int i = 0; forwardLanes.hasNext(); i++) {
                drawLaneAndCars(forwardLanes.next(), g2, i, false);
            }
            Iterator<Lane> backwardLanes = street.getBackwardLanesIterator();
            for (int i = 0; backwardLanes.hasNext(); i++) {
                drawLaneAndCars(backwardLanes.next(), g2, i, true);
            }
        }

        int newWidth = (int) (city.getBounds().width * zoom);
        int newHeight = (int) (city.getBounds().height * zoom);
        if (preciseZoom) {
            setLocation(getX() + (getWidth() - newWidth) / 2, getY() + (getHeight() - newHeight) / 2);
        }
        setSize(newWidth, newHeight);
        if (firsttime) {
            firsttime = false;
            System.out.println("painting done");
        }
    }

    private void drawLaneAndCars(Lane lane, Graphics2D g2, int i, boolean isBackward) {
        int dir = 1;
        if (isBackward)
            dir = -1;
        //drawlanes
        g2.setColor(lane.getColorByTraffic());
        int stroke = (int) (2 * lane.getTraffic());
        // if(stroke<1)
        stroke = 1;
        g2.setStroke(new BasicStroke((float) (stroke * zoom >= 1 ? stroke * zoom : 1), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (zoom >= 1) {

            g2.drawLine((int) ((int) (lane.getParent().getxFrom() * zoom) - (zoom + i * zoom * 2) * dir),
                    (int) ((int) (lane.getParent().getyFrom() * zoom) - (zoom + i * zoom * 2) * dir),
                    (int) ((int) (lane.getParent().getxTo() * zoom) - (zoom + i * zoom * 2) * dir),
                    (int) ((int) (lane.getParent().getyTo() * zoom) - (zoom + i * zoom * 2) * dir));
        } else {
            g2.drawLine((int) ((int) (lane.getParent().getxFrom() * zoom) - (1 + i * 2) * dir),
                    (int) ((int) (lane.getParent().getyFrom() * zoom) - (1 + i * 2) * dir),
                    (int) ((int) (lane.getParent().getxTo() * zoom) - (1 + i * 2) * dir),
                    (int) ((int) (lane.getParent().getyTo() * zoom) - (1 + i * 2) * dir));
        }
        ArrayList<Vehicle> vehicles = lane.getVehicles();
        //anti duplicate
        for (int j = 0; j < vehicles.size(); j++) {
            Vehicle vehicle = vehicles.get(j);
            g2.setColor(vehicle.getColor());
            // draw cars
            g2.fillOval((int) ((int) ((vehicle.currentPointOnLane().x) * zoom) - (4 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                    (int) ((int) ((vehicle.currentPointOnLane().y) * zoom) - (4 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                    4 * (int) (zoom), 4 * (int) (zoom));

        }
    }
}
