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
        if (pslFlag)
            paintStreetLines((Graphics2D) g);

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
        int dir = isBackward ? -1 : 1;
        //draw lanes
        g2.setColor(lane.getColorByTraffic());
        int stroke = 1;
        g2.setStroke(new BasicStroke((float) (stroke * zoom >= 1 ? stroke * zoom : 1), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (showStreets)
            if (zoom >= 1) {
                g2.drawLine((int) ((int) (getXYByOsmTileXY(lane.getParent().getxFrom()) * zoom) - (zoom + i * zoom * 2) * dir),
                        (int) ((int) (getXYByOsmTileXY(lane.getParent().getyFrom()) * zoom) - (zoom + i * zoom * 2) * dir),
                        (int) ((int) (getXYByOsmTileXY(lane.getParent().getxTo()) * zoom) - (zoom + i * zoom * 2) * dir),
                        (int) ((int) (getXYByOsmTileXY(lane.getParent().getyTo()) * zoom) - (zoom + i * zoom * 2) * dir));
            } else {
                g2.drawLine((int) ((int) (getXYByOsmTileXY(lane.getParent().getxFrom()) * zoom) - (1 + i * 2) * dir),
                        (int) ((int) (getXYByOsmTileXY(lane.getParent().getyFrom()) * zoom) - (1 + i * 2) * dir),
                        (int) ((int) (getXYByOsmTileXY(lane.getParent().getxTo()) * zoom) - (1 + i * 2) * dir),
                        (int) ((int) (getXYByOsmTileXY(lane.getParent().getyTo()) * zoom) - (1 + i * 2) * dir));

            }
        ArrayList<Vehicle> vehicles = lane.getVehicles();
        //anti duplicate
        if (showCars)
            for (int j = 0; j < vehicles.size(); j++) {
                Vehicle vehicle = vehicles.get(j);
                g2.setColor(vehicle.getColor());
                // FIXME: 22.04.2018 Vehicle can be null
                // draw cars
                if (zoom < 1) {
                    g2.fillOval((int) ((int) ((getXYByOsmTileXY(vehicle.currentPositionOnLane().getX())) * zoom) - (8) / 2 - (1 + i * 2) * dir),
                            (int) ((int) ((getXYByOsmTileXY(vehicle.currentPositionOnLane().getY())) * zoom) - (8) / 2 - (1 + i * 2) * dir),
                            8, 8);
                } else {
                    g2.fillOval((int) ((int) ((getXYByOsmTileXY(vehicle.currentPositionOnLane().getX())) * zoom) - (8 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                            (int) ((int) ((getXYByOsmTileXY(vehicle.currentPositionOnLane().getY())) * zoom) - (8 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                            8 * (int) (zoom), 8 * (int) (zoom));
                }

            }
        //draw streetlights
        if (showLights) {
            if (lane.getStreetlight() != null) {
                if (lane.getStreetlight().getState() == 0)
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.GREEN);
                g2.fillRect((int) (getXYByOsmTileXY(lane.getPointByProgress(lane.getLength()).getX()))  - (1 + i * 2) * dir,
                        (int) (getXYByOsmTileXY(lane.getPointByProgress(lane.getLength()).getY()))   - (1 + i * 2) * dir,
                        8, 8);
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
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePoint.x = mousePoint.x - container.getX() - getX();
        mousePoint.y = mousePoint.y - container.getY() - getY();
        zoom *= 2;
        resizeAfterZoom();

        System.out.println(zoom);
    }

    public void zoomOut() {
        System.out.println(minZoom);
        if (zoom == minZoom)
            return;
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePoint.x -= container.getX() + getX();
        mousePoint.y -= container.getY() + getY();
        zoom /= 2;
        resizeAfterZoom();

        System.out.println(zoom);
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

        private boolean hoverMode = false;
        private Point hoverPoint = null;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_PLUS:
                    zoomIn();
                    break;
                case KeyEvent.VK_MINUS:
                    zoomOut();
                    break;
                case KeyEvent.VK_LEFT:
                    setLocation(getX() + 10, getY());
                    break;
                case KeyEvent.VK_RIGHT:
                    setLocation(getX() - 10, getY());
                    break;
                case KeyEvent.VK_UP:
                    setLocation(getX(), getY() + 10);
                    break;
                case KeyEvent.VK_DOWN:
                    setLocation(getX(), getY() - 10);
                    break;
                case KeyEvent.VK_H:
                    if (hoverPoint == null) {
                        hoverMode = true;
                        hoverPoint = MouseInfo.getPointerInfo().getLocation();
                    } else {
                        int xOff = hoverPoint.x - MouseInfo.getPointerInfo().getLocation().x;
                        int yOff = hoverPoint.y - MouseInfo.getPointerInfo().getLocation().y;
                        xOff /= 4;
                        yOff /= 4;
                        setLocation(getX() - xOff, getY() - yOff);
                    }
                    break;
                case 'l':
                    moveCordsVisibleAt(11.1181027, 46.6140000, 0, 0);
                    break;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_H:
                    hoverMode = false;
                    hoverPoint = null;
                    break;
                case KeyEvent.VK_L:
                    toggleShowLights();
                    break;
                case KeyEvent.VK_S:
                    toggleShowStreets();
                    break;
                case KeyEvent.VK_V:
                    toggleShowCars();
                    break;
                case KeyEvent.VK_R:
                    while (getAvgNodePosition() > getHeight() / 2) {
                        zoomOut();
                    }
                    while (getAvgNodePosition() < getHeight() / 4) {
                        zoomIn();
                    }
                    setLocation(0, 0);
                    repaint();
                    break;
                case KeyEvent.VK_P:
                    setLocation(0, 0);
                    repaint();
                    break;
            }
        }
    }
}
