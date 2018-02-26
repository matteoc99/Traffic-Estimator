package gui.city;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * @author Maximilian Estfeller
 * @since 26.02.2017
 */
public class Overlay extends JPanel {

    private final double initialLat;
    private final double initialLon;

    private int currentZoomLevel;

    private HashMap<Integer, Point> initialList = new HashMap<>();

    // offset is based on max zoom (19)
    private int xOffset;
    private int yOffset;

    public Overlay(double initialLat, double initialLon, int zoom) {
        this.initialLat = initialLat;
        this.initialLon = initialLon;

        setCurrentZoomLevel(zoom);
    }

    public void setCurrentZoomLevel(int zoom) {
        if (zoom <= 19 && zoom > 5) {
            if (!initialList.containsKey(zoom))
                initialList.put(zoom, TileManager.getTilePoint(initialLat, initialLon, zoom));
            this.currentZoomLevel = zoom;
        }
    }

    public void increaseCurrentZoom() {
        setCurrentZoomLevel(currentZoomLevel+1);
    }

    public void decreaseCurrentZoom() {
        setCurrentZoomLevel(currentZoomLevel-1);
    }

    /**
     * Positive: right
     * negative: left
     * @param distance amount of tiles (based on zoom 19)
     */
    public void moveHorizontal(int distance) {
        int power = (19 - currentZoomLevel);
        xOffset += distance * Math.pow(2, power);
    }

    /**
     * Positive: up
     * negative: down
     * @param distance amount of tiles (based on zoom 19)
     */
    public void moveVertical(int distance) {
        int power = (19 - currentZoomLevel);
        yOffset += distance * Math.pow(2, power);
    }

    public BufferedImage testGetTile() {
        int div = (20 - currentZoomLevel);
        Point point = ((Point) initialList.get(currentZoomLevel).clone());
        point.setLocation(
                point.getX()+(xOffset/div),
                point.getY()+(yOffset/div)
        );
        return TileManager.getTileImage(point, currentZoomLevel);
    }

    public Point test() {
        int div = (20 - currentZoomLevel);
        Point point = ((Point) initialList.get(currentZoomLevel).clone());
        point.setLocation(
                point.getX()+(xOffset/div),
                point.getY()+(yOffset/div)
        );
        return point;
    }
}
