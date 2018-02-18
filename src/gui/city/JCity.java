package gui.city;

import logic.Utils;
import logic.city.*;
import logic.vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import static gui.Main.preciseZoom;
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

    boolean firsttime= true;

    public JCity(City city) {
        this.city = city;
        streets = city.getStreets();

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

        g2.setColor(new Color(213, 187, 255));
        g2.fillRect(0, 0, getWidth(), getHeight());
        //drawStreets
        for (Street street : streets) {

            ArrayList<Lane> forwardLanes = street.getForwardLanes();
            for (int i = 0; i < forwardLanes.size(); i++) {
                Lane lane = forwardLanes.get(i);
                g2.setColor(lane.getColorByTraffic());
                int stroke= (int) (5*lane.getTraffic());
                if(stroke<1)
                    stroke=1;
                g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine((int) (lane.getParent().getxFrom() * zoom) + (1 + i * 2),
                        (int) (lane.getParent().getyFrom() * zoom) + (1 + i * 2),
                        (int) (lane.getParent().getxTo() * zoom) + (1 + i * 2),
                        (int) (lane.getParent().getyTo() * zoom) + (1 + i * 2));
                //drawlanes
                for (Vehicle vehicle : lane.getVehicles()) {
                    //drawcars
                    g2.setColor(Color.RED);
                    g.fillOval((int) ((vehicle.getPointOnLane().x) * zoom) - 2 + (1 + i * 2),
                            (int) ((vehicle.getPointOnLane().y) * zoom) - 2 + (1 + i * 2),
                            4 + (int) (zoom), 4 + (int) (zoom));
                }
            }
            ArrayList<Lane> backwardLanes = street.getBackwardLanes();
            for (int i = 0; i < backwardLanes.size(); i++) {
                Lane lane = backwardLanes.get(i);
                //drawlanes
                g2.setColor(lane.getColorByTraffic());
                int stroke= (int) (5*lane.getTraffic());
                if(stroke<1)
                    stroke=1;
                g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine((int) (lane.getParent().getxFrom() * zoom) - (1 + i * 2),
                        (int) (lane.getParent().getyFrom() * zoom) - (1 + i * 2),
                        (int) (lane.getParent().getxTo() * zoom) - (1 + i * 2),
                        (int) (lane.getParent().getyTo() * zoom) - (1 + i * 2));
                ArrayList<Vehicle> vehicles = lane.getVehicles();
                //anti duplicate
                for (int j = 0; j < vehicles.size(); j++) {
                    g2.setColor(Color.RED);
                    Vehicle vehicle = vehicles.get(j);
                    //drawcars
                    g.fillOval((int) ((vehicle.getPointOnLane().x) * zoom) - 2 - (1 + i * 2),
                            (int) ((vehicle.getPointOnLane().y) * zoom) - 2 - (1 + i * 2),
                            4 + (int) (zoom), 4 + (int) (zoom));

                }
            }
        }

        int newWidth= (int) (city.getBounds().width * zoom);
        int newHeight= (int) (city.getBounds().height * zoom);
        if (preciseZoom){

            setLocation(getX()+(getWidth()-newWidth)/2, getY()+(getHeight()-newHeight)/2);
        }
        setSize(newWidth,newHeight);
        if(firsttime) {
            firsttime=false;
            System.out.println("painting done");
        }
    }
}
