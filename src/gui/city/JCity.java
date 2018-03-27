package gui.city;

import logic.city.City;
import logic.city.Lane;
import logic.city.Node;
import logic.city.Street;
import logic.vehicles.Vehicle;
import main.Main;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    JFrame container;

    public JCity(City city, JFrame container) {
        this.city = city;
        this.container = container;
        setLayout(null);
        setBounds(0,0, getXYByTileXY(city.getMaxWidth()), getXYByTileXY(city.getMaxHeight()));

        container.addKeyListener(new JCityKeyListener());

        //getNodesPos average
        // FIXME: 13.03.2018 endless
        /*
        while (getAvgNodePosition() > getHeight() / 2) zoomOut();
        while (getAvgNodePosition() < getHeight() / 4) zoomIn();
        */
        new Thread(() -> {
            while (true) {
                long zeitvorsleep = System.currentTimeMillis();
                repaint();
                long zeitvergangen = (System.currentTimeMillis() - zeitvorsleep);
                if (zeitvergangen < 1000.0 / Main.FPS) {
                    try {
                        Thread.sleep((long) (1000.0 / Main.FPS - zeitvergangen));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        setLocation(-getXYByTileXY(city.getMinWidth()), -getXYByTileXY(city.getMinHeight()));

        /*
        {
            Node node = city.getRandomNode();
            for (Node nodess : city.getNodes()) {
                if (nodess.getY() < node.getY())
                    node = nodess;
            }

            setLocation(-getXYByTileXY(node.getX()), -getXYByTileXY(node.getY()));

            System.out.println(getLocation());
            System.out.println(node);
            System.out.println(getXYByTileXY(node.getX()));
            System.out.println(getXYByTileXY(node.getY()));
        }
        {
            Node node = city.getRandomNode();
            for (Node nodess : city.getNodes()) {
                if (nodess.getX() < node.getX())
                    node = nodess;
            }

            setLocation(-getXYByTileXY(node.getX()), -getXYByTileXY(node.getY()));

            System.out.println(getLocation());
            System.out.println(node);
            System.out.println(getXYByTileXY(node.getX()));
            System.out.println(getXYByTileXY(node.getY()));
        }
        */
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
        int newWidth = (int) (getXYByTileXY(city.getMaxWidth()) * zoom);
        int newHeight = (int) (getXYByTileXY(city.getMaxHeight()) * zoom);

        setSize(newWidth, newHeight);
        if (firstPaint) {
            firstPaint = false;
        }
    }

    private void drawLaneAndCars(Lane lane, Graphics2D g2, int i, boolean isBackward) {
        int dir = isBackward? -1:1;
        //draw lanes
        g2.setColor(lane.getColorByTraffic());
        int stroke = 1;
        g2.setStroke(new BasicStroke((float) (stroke * zoom >= 1 ? stroke * zoom : 1), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (showStreets)
            if (zoom >= 1) {
                g2.drawLine((int) ((int) (getXYByTileXY(lane.getParent().getxFrom()) * zoom) - (zoom + i * zoom * 2) * dir),
                        (int) ((int) (getXYByTileXY(lane.getParent().getyFrom()) * zoom) - (zoom + i * zoom * 2) * dir),
                        (int) ((int) (getXYByTileXY(lane.getParent().getxTo()) * zoom) - (zoom + i * zoom * 2) * dir),
                        (int) ((int) (getXYByTileXY(lane.getParent().getyTo()) * zoom) - (zoom + i * zoom * 2) * dir));
            } else {
                g2.drawLine((int) ((int) (getXYByTileXY(lane.getParent().getxFrom()) * zoom) - (1 + i * 2) * dir),
                        (int) ((int) (getXYByTileXY(lane.getParent().getyFrom()) * zoom) - (1 + i * 2) * dir),
                        (int) ((int) (getXYByTileXY(lane.getParent().getxTo()) * zoom) - (1 + i * 2) * dir),
                        (int) ((int) (getXYByTileXY(lane.getParent().getyTo()) * zoom) - (1 + i * 2) * dir));

            }
        ArrayList<Vehicle> vehicles = lane.getVehicles();
        //anti duplicate
        if (showCars)
            for (int j = 0; j < vehicles.size(); j++) {
                Vehicle vehicle = vehicles.get(j);
                g2.setColor(vehicle.getColor());
                // draw cars
                if (zoom < 1) {
                    g2.fillOval((int) ((int) ((getXYByTileXY(vehicle.currentPositionOnLane().getX())) * zoom) - (8) / 2 - (1 + i * 2) * dir),
                            (int) ((int) ((getXYByTileXY(vehicle.currentPositionOnLane().getY())) * zoom) - (8) / 2 - (1 + i * 2) * dir),
                            8, 8);
                } else {
                    g2.fillOval((int) ((int) ((getXYByTileXY(vehicle.currentPositionOnLane().getX())) * zoom) - (8 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                            (int) ((int) ((getXYByTileXY(vehicle.currentPositionOnLane().getY())) * zoom) - (8 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                            8 * (int) (zoom), 8 * (int) (zoom));
                }

            }
        //draw streetlights
        if (showLights) {
            if (zoom < 1) {
                if (lane.getStreetlight() != null) {
                    if (lane.getStreetlight().getState() == 0)
                        g2.setColor(Color.RED);
                    else
                        g2.setColor(Color.GREEN);
                    g2.fillRect((int) (getXYByTileXY(lane.getPointByProgress(lane.getLength()).getX()) * zoom) - (8) / 2 - (1 + i * 2) * dir,
                            (int) (getXYByTileXY(lane.getPointByProgress(lane.getLength()).getY()) * zoom) - (8) / 2 - (1 + i * 2) * dir,
                            8, 8);
                }
            } else {
                if (lane.getStreetlight() != null) {
                    if (lane.getStreetlight().getState() == 0)
                        g2.setColor(Color.RED);
                    else
                        g2.setColor(Color.GREEN);
                    g2.fillOval((int) ((getXYByTileXY(lane.getPointByProgress(lane.getLength()).getX()) * zoom) - (8 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                            (int) ((getXYByTileXY(lane.getPointByProgress(lane.getLength()).getY()) * zoom) - (8 * (int) (zoom)) / 2 - (zoom + i * zoom * 2) * dir),
                            8 * (int) (zoom), 8 * (int) (zoom));
                }
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
        System.out.println("JCity: In "+zoom);
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePoint.x = mousePoint.x - container.getX() - getX();
        mousePoint.y = mousePoint.y - container.getY() - getY();
        zoom *= 2;
        //repositionAfterZoom(mousePoint, true);
    }

    public void zoomOut() {
        System.out.println("JCity: Out "+zoom);
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mousePoint.x -= container.getX() + getX();
        mousePoint.y -= container.getY() + getY();

        zoom /= 2;
        //repositionAfterZoom(mousePoint, false);
    }

    private void repositionAfterZoom(Point mousePoint, boolean zoomIn) {
        if (mousePoint.x > 0 && mousePoint.y > 0) {
            // TODO: 13.03.2018 Most likely Values didn't change, inefficient to reiterate all nodes
            int newWidth = (int) (getXYByTileXY(city.getMaxWidth()) * zoom);
            int newHeight = (int) (getXYByTileXY(city.getMaxHeight()) * zoom);
            setSize(newWidth, newHeight);

            if (zoomIn) {
                mousePoint.x *= 2;
                mousePoint.y *= 2;
            } else {
                mousePoint.x /= 2;
                mousePoint.y /= 2;
            }
            setLocation(container.getWidth()/2-mousePoint.x,container.getHeight()/2-mousePoint.y);
            try {
                Robot r = new Robot();
                r.mouseMove(container.getX()+container.getWidth()/2,container.getY()+container.getHeight()/2);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no mouse pointed");
        }
    }

    private static int getXYByTileXY(double tileXY) {
        int ret;
        int raw = (int) tileXY;

        ret = raw * 256;
        ret += (tileXY - raw) * 256;
        return ret;
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

        public boolean hoverMode = false;
        public Point hoverPoint = null;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_PLUS:
                    //zoom++
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
