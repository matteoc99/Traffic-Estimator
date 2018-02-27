package gui.city;

import utils.Stopwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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

    private ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();

    TileManager tileManager = new TileManager(this);

    public Overlay(double initialLat, double initialLon, int zoom) {
        this.initialLat = initialLat;
        this.initialLon = initialLon;

        this.addComponentListener(new OverlayComponentAdapter());

        this.setLayout(null);

        setCurrentZoomLevel(zoom);
        arrangeLabelArray();
        fillLabels();
    }

    private void layoutLabels() {
        this.removeAll();
        for (int x = 0; x < labels.size(); x++) {
            ArrayList<JLabel> labelRow = labels.get(x);
            for (int y = 0; y < labelRow.size(); y++) {
                JLabel label = labelRow.get(y);
                label.setBounds((x-1)*256, (y-1)*256, 256, 256);
                this.add(label);
            }
        }
    }

    private void setCurrentZoomLevel(int zoom) {
        if (zoom <= 19 && zoom > 5) {
            if (!initialList.containsKey(zoom))
                initialList.put(zoom, tileManager.getTilePoint(initialLat, initialLon, zoom));
            this.currentZoomLevel = zoom;
        }
    }

    public void increaseCurrentZoom() {
        setCurrentZoomLevel(currentZoomLevel+1);
        fillLabels();
    }

    public void decreaseCurrentZoom() {
        setCurrentZoomLevel(currentZoomLevel-1);
        fillLabels();
    }

    /**
     * Positive: right
     * negative: left
     * @param distance amount of tiles (based on zoom 19)
     */
    public void moveHorizontal(int distance) {
        int power = (19 - currentZoomLevel);
        xOffset += distance * Math.pow(2, power);
        fillLabels();
    }

    /**
     * Positive: up
     * negative: down
     * @param distance amount of tiles (based on zoom 19)
     */
    public void moveVertical(int distance) {
        int power = (19 - currentZoomLevel);
        yOffset += distance * Math.pow(2, power);
        fillLabels();
    }

    private void fillLabels() {
        for (int x = 0; x < labels.size(); x++) {
            ArrayList<JLabel> labelRow = labels.get(x);
            for (int y = 0; y < labelRow.size(); y++) {
                JLabel label = labelRow.get(y);

                trySettingIcon(x, y, label);
            }
        }
    }

    public void fillLabel (JLabel label) {
        for (int x = 0; x < labels.size(); x++) {
            ArrayList<JLabel> labelRow = labels.get(x);
            for (int y = 0; y < labelRow.size(); y++) {
                JLabel l = labelRow.get(y);
                if (label.equals(l))
                    trySettingIcon(x, y, l);
            }
        }
    }

    private void arrangeLabelArray() {
        int visibleX = (int) Math.ceil(this.getWidth() / 256.0);
        int visibleY = (int) Math.ceil(this.getHeight() / 256.0);

        if (labels.size() == visibleX+2 && labels.get(0).size() == visibleY+2)
            return;

        ArrayList<ArrayList<JLabel>> newLabelList = new ArrayList<>();

        for (int xIndex = 0; xIndex < visibleX+2; xIndex++) {
            ArrayList<JLabel> newRow = new ArrayList<>();

            ArrayList<JLabel> row;
            try {
                row = labels.get(xIndex);
            } catch (Exception e) {
                row = new ArrayList<>();
            }

            for (int yIndex = 0; yIndex < visibleY+2; yIndex++) {
                JLabel label;
                try {
                    label = row.get(yIndex);
                } catch (Exception e) {
                    label = new JLabel();
                }
                trySettingIcon(xIndex, yIndex, label);
                newRow.add(yIndex, label);
            }

            newLabelList.add(newRow);
        }

        labels = newLabelList;

        layoutLabels();

        System.out.println(this.getBounds().toString() +" : " + visibleX + "/" + visibleY);
        System.out.println(labels.size() + "*" + labels.get(0).size());
    }

    private void trySettingIcon(int xIndex, int yIndex, JLabel label) {
        BufferedImage bi = getTileWithExtraOffset(xIndex, yIndex);
        if (bi != null)
            label.setIcon(new ImageIcon(bi));
        else {
            Point p = getTilePointWithExtraOffset(xIndex, yIndex);
            tileManager.tileBuffer.putLabelToBuffer(label, currentZoomLevel, p);
            BufferedImage tempBI = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
            tempBI.getGraphics().drawString(p.toString(), 10, 128);
            label.setIcon(new ImageIcon(tempBI));
        }
        label.repaint();
    }

    public BufferedImage getTileWithExtraOffset(int extraX, int extraY) {
        Point point = getTilePointWithExtraOffset(extraX, extraY);
        return tileManager.getTileImage(point, currentZoomLevel);
    }

    public Point getTilePointWithExtraOffset(int extraX, int extraY) {
        int power = (19 - currentZoomLevel);
        Point point = ((Point) initialList.get(currentZoomLevel).clone());
        point.setLocation(
                point.getX()+(xOffset/Math.pow(2, power))+extraX,
                point.getY()+(yOffset/Math.pow(2, power))+extraY
        );
        return point;
    }

    private class OverlayComponentAdapter extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);
            arrangeLabelArray();
        }
    }
}
