package gui.city;

import logic.city.City;
import logic.city.Lane;
import logic.city.Node;
import logic.city.Street;
import logic.vehicles.Vehicle;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JCity extends JPanel {

    /**
     * reference
     */
    private City city;

    private boolean firstPaint = true;


    private boolean pslFlag = true;
    private boolean showLights = true;
    private boolean showStreets = true;
    private boolean showCars = true;

    //zoom
    public static double zoom = 1;
    public double maxZoom = 1;
    public double minZoom = 1 / Math.pow(2, 10);

    private int zoomLevel = 19;
    JFrame container;

    /**
     * CAREFUL: There is a slight inaccuracy due to converting to an int
     * Based on OsmZoom:19
     */
    public static int getXYByOsmTileXY(double osmTileXY) {
        int ret;
        int raw = (int) osmTileXY;

        ret = raw * 256;
        ret += (osmTileXY - raw) * 256;
        return ret;
    }

    public static double getOSMTileXYByXY(int xy) {
        return xy / 256.0;
    }

    public JCity(City city, JFrame container) {
        this.city = city;
        this.container = container;
        setLayout(null);
        setBounds(0, 0, getXYByOsmTileXY(city.getMaxWidth()), getXYByOsmTileXY(city.getMaxHeight()));

        container.addKeyListener(new JCityKeyListener());

        //getNodesPos average
        // FIXME: 13.03.2018 endless
        /*
        while (getAvgNodePosition() > getHeight() / 2) zoomOut();
        while (getAvgNodePosition() < getHeight() / 4) zoomIn();
        */

        moveVisibleAt(new Point(getXYByOsmTileXY(city.getMinWidth()), getXYByOsmTileXY(city.getMinHeight())), 0, 0);
    }

    public void moveVisibleAt(Point point, int visibleX, int visibleY) {
        setLocation(-point.x + visibleX, -point.y + visibleY);
    }

    public City getCity() {
        return city;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (pslFlag) {
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintStreetLines((Graphics2D) g);
        }

    }

    private void paintStreetLines(Graphics2D g) {
        //drawNodes
      /*  for (Node node : nodes) {
            g.setColor(Color.BLUE);
            g.drawOval(node.getX(),node.getY(),8,8);
        }*/

        Graphics2D g2 = g;
        // FIXME: 13.03.2018 don't recreate object
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

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

        // TODO: 13.03.2018 Did the bounds even change?
        int newWidth = (int) (getXYByOsmTileXY(city.getMaxWidth()) * zoom);
        int newHeight = (int) (getXYByOsmTileXY(city.getMaxHeight()) * zoom);

        setSize(newWidth, newHeight);
        if (firstPaint) {
            firstPaint = false;
        }
    }

    private void drawLaneAndCars(Lane lane, Graphics2D g2, int i, boolean isBackward) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int dir = isBackward ? -1 : 1;
        //draw lanes
        g2.setColor(lane.getColorByTraffic());
        if (showStreets) {
            g2.setStroke(new BasicStroke(zoomLevel / 8));
            if (zoomLevel <= 14) {
                if (lane.isFull()) {
                    g2.setStroke(new BasicStroke(zoomLevel / 8 + 5));
                } else if (lane.getTraffic() > 1.3) {
                    g2.setStroke(new BasicStroke(zoomLevel / 8 + 3));

                } else if (lane.getTraffic() > 0.8) {
                    g2.setStroke(new BasicStroke(zoomLevel / 8 + 2));

                }
            }
            g2.drawLine((int) (getXYByOsmTileXY(lane.getParent().getxFrom()) * zoom) - (zoomLevel / 8 + i * 2) * dir,
                    (int) (getXYByOsmTileXY(lane.getParent().getyFrom()) * zoom) - (zoomLevel / 8 + i * 2) * dir,
                    (int) (getXYByOsmTileXY(lane.getParent().getxTo()) * zoom) - (zoomLevel / 8 + i * 2) * dir,
                    (int) (getXYByOsmTileXY(lane.getParent().getyTo()) * zoom) - (zoomLevel / 8 + i * 2) * dir);

            g2.setStroke(new BasicStroke(1));
        }

        //draw cars
        ArrayList<Vehicle> vehicles = lane.getVehicles();
        if (showCars && zoomLevel > 14) {
            ArrayList<Vehicle> cars = new ArrayList<>(vehicles);
            for (Vehicle vehicle : cars) {
                if (vehicle != null) {
                    g2.setColor(vehicle.getColor());
                    g2.fillOval((int) ((getXYByOsmTileXY(vehicle.currentPositionOnLane().getX())) * zoom) - (zoomLevel / 2) / 2 - (1 + i * 2) * dir,
                            (int) ((getXYByOsmTileXY(vehicle.currentPositionOnLane().getY())) * zoom) - (zoomLevel / 2) / 2 - (1 + i * 2) * dir,
                            zoomLevel / 2, zoomLevel / 2);
                }
            }
        }
        if (showLights && zoomLevel > 14) {
            if (lane.getStreetlight() != null) {
                if (lane.getStreetlight().getState() == 0)
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.GREEN);
                if (dir < 0)
                    g2.fillRect((int) ((getXYByOsmTileXY(lane.getPointByProgress(lane.getLength()).getX() * zoom)) - (zoomLevel / 2 * 0.75)),
                            (int) ((getXYByOsmTileXY(lane.getPointByProgress(lane.getLength()).getY() * zoom)) - (zoomLevel / 2 * 0.75)),
                            zoomLevel / 2, zoomLevel / 2);
                else
                    g2.fillRect((getXYByOsmTileXY(lane.getPointByProgress(lane.getLength()).getX() * zoom)) + (zoomLevel / 2 / 4),
                            (getXYByOsmTileXY(lane.getPointByProgress(lane.getLength()).getY() * zoom)) + (zoomLevel / 2 / 4),
                            zoomLevel / 2, zoomLevel / 2);
            }
        }
    }

    public void setPSLFlag(boolean bool) {
        this.pslFlag = bool;
    }

    public boolean tooglePSLFlag() {
        pslFlag = !pslFlag;
        return pslFlag;
    }

    public boolean toggleShowLights() {
        showLights = !showLights;
        return showLights;
    }

    public boolean toggleShowStreets() {
        showStreets = !showStreets;
        return showStreets;
    }

    public boolean toggleShowCars() {
        showCars = !showCars;
        return showCars;
    }


    public boolean isShowStreets() {
        return showStreets;
    }

    public void setShowStreets(boolean showStreets) {
        this.showStreets = showStreets;
    }

    public boolean isShowLights() {
        return showLights;
    }

    public void setShowLights(boolean showLights) {
        this.showLights = showLights;
    }

    public boolean isShowCars() {
        return showCars;
    }

    public void setShowCars(boolean showCars) {
        this.showCars = showCars;
    }

    public boolean isFirstPaint() {
        return firstPaint;
    }

    public void setFirstPaint(boolean firstPaint) {
        this.firstPaint = firstPaint;
    }

    public void zoomIn() {
        if (zoom == maxZoom)
            return;
        zoomLevel++;
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePoint.x = mousePoint.x - container.getX() - getX();
        mousePoint.y = mousePoint.y - container.getY() - getY();
        zoom *= 2;
        resizeAfterZoom();

    }

    public void zoomOut() {
        if (zoom == minZoom)
            return;
        zoomLevel--;
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePoint.x -= container.getX() + getX();
        mousePoint.y -= container.getY() + getY();
        zoom /= 2;
        resizeAfterZoom();

    }

    public void moveCordsVisibleAt(double lon, double lat, int visibleAtX, int visibleAtY) {
        // TODO: 16.04.2018 Test this!
        double x = Utils.getOsmTileX(lon, 19);
        double y = Utils.getOsmTileY(lat, 19);

        // FIXME: 18.04.2018 Not sure about the multiplication with zoom
        int pX = (int) (getXYByOsmTileXY(x) * zoom);
        int pY = (int) (getXYByOsmTileXY(y) * zoom);

        moveVisibleAt(new Point(pX, pY), visibleAtX, visibleAtY);
    }

    private void resizeAfterZoom() {
        int newWidth = (int) (getXYByOsmTileXY(city.getMaxWidth()) * zoom);
        int newHeight = (int) (getXYByOsmTileXY(city.getMaxHeight()) * zoom);
        setSize(newWidth, newHeight);
    }

    public static double getZoom() {
        return zoom;
    }

    /**
     * get avg node pos relative to zoom
     */
    public int getAvgNodePosition() {
        int sumX = 0, sumY = 0;
        Iterator<Node> nodes = city.getNodeIterator();
        while (nodes.hasNext()) {
            Node node = nodes.next();
            sumX += node.getX() * JCity.getZoom();
            sumY += node.getY() * JCity.getZoom();
        }

        return Integer.min(sumX / city.getNodeSize(), sumY / city.getNodeSize());
    }

    private class JCityKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_L:
                    toggleShowLights();
                    break;
                case KeyEvent.VK_S:
                    toggleShowStreets();
                    break;
                case KeyEvent.VK_V:
                    toggleShowCars();
                    break;
            }
        }
    }
}
