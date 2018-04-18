package gui.city.Overlay;

import utils.Utils;
import utils.math.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Maximilian Estfeller
 * @since 26.02.2017
 */
public class Overlay extends JPanel {

    private int xPaintOffset, yPaintOffset = 0;

    private final double initialLat, initialLon;

    protected int currentZoomLevel;

    private HashMap<Integer, Point> initialList = new HashMap<>();

    // offset is based on max zoom (19)
    private int xTileOffset, yTileOffset;

    private ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();

    private TileManager tileManager = new OSMTileManager(this);

    private JFrame jFrame;

    /**
     * Attention: Always creates a new Position
     *
     * @param pos old Position
     * @param fromZoom old zoom
     * @param toZoom new zoom
     */
    public static Position transformPositionByZoom(Position pos, int fromZoom, int toZoom) {
        Position ret = new Position(0, 0);
        int power = fromZoom - toZoom;
        if (power == 0)
            return pos.clone();
        ret.setX(pos.getX() / Math.pow(2, power));
        ret.setY(pos.getY() / Math.pow(2, power));
        return ret;
    }

    public Overlay(JFrame jFrame, double initialLon, double initialLat, int zoom) {
        this.initialLat = initialLat;
        this.initialLon = initialLon;
        this.jFrame = jFrame;

        loadInitialList();

        this.setLayout(null);

        setCurrentZoomLevel(zoom);
        arrangeLabelArray();

        // already sets an paintOffset so that initialLat and initialLon is exactly topLeft
        setupInitLoc();

        fillLabels();

        this.addComponentListener(new OverlayComponentAdapter());

        jFrame.addKeyListener(new OverlayKeyListener());
    }

    private void loadInitialList() {
        int zoom = 0;

        // set zoom to min
        while (!validateZoom(zoom))
            zoom++;

        // do until zoom = max
        while (validateZoom(zoom)) {
            if (!initialList.containsKey(zoom))
                initialList.put(zoom, getTilePoint(initialLat, initialLon, zoom));
            zoom++;
        }
    }

    /**
     * Moves everything in a way so that the tile which contains the initialCoordinates is visible in the top/left corner
     */
    private void setupInitLoc() {
        double xOrg = Utils.getOsmTileX(initialLon, currentZoomLevel);
        double yOrg = Utils.getOsmTileY(initialLat, currentZoomLevel);

        double xDigits = xOrg - (int) xOrg;
        double yDigits = yOrg - (int) yOrg;

        xPaintOffset -= xDigits * 256;
        yPaintOffset -= yDigits * 256;

        moveOffset(-1, -1);
    }

    public void moveCordsVisibleAt(double lon, double lat, int visibleAtX, int visibleAtY) {
        double x = Utils.getOsmTileX(lon, currentZoomLevel);
        double y = Utils.getOsmTileY(lat, currentZoomLevel);

        movePositionVisibleAt(new Position(x, y), 500, 500);
    }

    public void movePositionVisibleAt(Position position, int visibleAtX, int visibleAtY) {
        if (position == null) return;
        Point currentTopLeftTile = getTilePointWithExtraOffset(0, 0, currentZoomLevel);

        double difX = position.getX() - currentTopLeftTile.getX();
        double difY = position.getY() - currentTopLeftTile.getY();

        double xDigits = difX - (int) difX;
        double yDigits = difY - (int) difY;

        moveOffset((int) difX - 1, (int) difY - 1);

        xPaintOffset = -(int) (xDigits * 256);
        yPaintOffset = -(int) (yDigits * 256);

        movePixels(visibleAtX, visibleAtY);

        repaint();
    }

    /**
     * Returns the Position(Tile.x, Tile.y) of the Tile that is visible at the following location with digits.
     * x and y are relative to the top/left visible point
     *
     * @param x-pixel
     * @param y-pixel
     * @return the exact Position(osm)
     */
    public Position getTilePositionAtVisible(int x, int y) {
        Position ret;

        x -= xPaintOffset;
        y -= yPaintOffset;

        Point currentTopLeftTile = getTilePointWithExtraOffset(0, 0, currentZoomLevel);
        ret = new Position(currentTopLeftTile);
        ret.setX(ret.getX() + x / 256.0 + 1);
        ret.setY(ret.getY() + y / 256.0 + 1);

        return ret;
    }

    private void setCurrentZoomLevel(int zoom) {
        if (validateZoom(zoom))
            this.currentZoomLevel = zoom;
    }

    private boolean validateZoom(int zoom) {
        return zoom <= 19 && zoom > 8;
    }

    public void increaseCurrentZoom() {
        changeCurrentZoom(1);
    }

    public void decreaseCurrentZoom() {
        changeCurrentZoom(-1);
    }

    private void changeCurrentZoom(int change) {
        setCurrentZoomLevel(currentZoomLevel + change);
        tileManager.clearLabelsToBuffer();
        tileManager.clearTilesToBuffer();
        fillLabels();
        bufferRequest();
    }

    public void movePixels(int xDiff, int yDiff) {
        xPaintOffset += xDiff;
        yPaintOffset += yDiff;


        while (xPaintOffset > 0) {
            int c = xPaintOffset / 256 + 1;
            xPaintOffset -= 256 * c;
            moveHorizontal(-c);
        }
        while (xPaintOffset < -256) {
            int c = xPaintOffset / -256;
            xPaintOffset += 256 * c;
            moveHorizontal(c);
        }
        while (yPaintOffset > 0) {
            int c = yPaintOffset / 256 + 1;
            yPaintOffset -= 256 * c;
            moveVertical(-c);
        }
        while (yPaintOffset < -256) {
            int c = yPaintOffset / -256;
            yPaintOffset += 256 * c;
            moveVertical(c);
        }
        repaint();
    }

    /**
     * Positive: right
     * negative: left
     *
     * @param distance amount of tiles (based on zoom 19)
     */
    private void moveHorizontal(int distance) {
        moveOffset(distance, 0);
    }

    /**
     * Positive: down
     * negative: up
     *
     * @param distance amount of tiles (based on zoom 19)
     */
    private void moveVertical(int distance) {
        moveOffset(0, distance);
    }

    private void moveOffset(int hDis, int vDis) {
        int power = (19 - currentZoomLevel);
        if (hDis != 0) xTileOffset += hDis * Math.pow(2, power);
        if (vDis != 0) yTileOffset += vDis * Math.pow(2, power);
        tileManager.clearLabelsToBuffer();
        fillLabels();
        bufferRequest();
    }

    public void fillLabels() {
        for (int x = 0; x < labels.size(); x++) {
            ArrayList<JLabel> labelRow = labels.get(x);
            for (int y = 0; y < labelRow.size(); y++) {
                JLabel label = labelRow.get(y);

                trySettingIcon(x, y, label);
            }
        }
    }

    public void fillLabel(JLabel label) {
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
        tileManager.clearLabelsToBuffer();
        tileManager.clearTilesToBuffer();

        int visibleX = (int) Math.ceil(this.getWidth() / 256.0);
        int visibleY = (int) Math.ceil(this.getHeight() / 256.0);

        if (labels.size() == visibleX + 2 && labels.get(0).size() == visibleY + 2)
            return;

        ArrayList<ArrayList<JLabel>> newLabelList = new ArrayList<>();

        for (int xIndex = 0; xIndex < visibleX + 2; xIndex++) {
            ArrayList<JLabel> newColumn = new ArrayList<>();

            ArrayList<JLabel> column;
            try {
                column = labels.get(xIndex);
            } catch (Exception e) {
                column = new ArrayList<>();
            }

            for (int yIndex = 0; yIndex < visibleY + 2; yIndex++) {
                JLabel label;
                try {
                    label = column.get(yIndex);
                } catch (Exception e) {
                    label = new JLabel();
                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                trySettingIcon(xIndex, yIndex, label);
                newColumn.add(yIndex, label);
            }

            newLabelList.add(newColumn);
        }

        labels = newLabelList;

        layoutLabels();
    }

    private void layoutLabels() {
        this.removeAll();
        for (int x = 0; x < labels.size(); x++) {
            ArrayList<JLabel> labelRow = labels.get(x);
            for (int y = 0; y < labelRow.size(); y++) {
                JLabel label = labelRow.get(y);
                label.setBounds((x - 1) * 256, (y - 1) * 256, 256, 256);
                this.add(label);
            }
        }
    }

    private synchronized void trySettingIcon(int xIndex, int yIndex, JLabel label) {
        BufferedImage bi = getTileWithExtraOffset(xIndex, yIndex);
        if (bi != null) {
            label.setIcon(new ImageIcon(bi));
        } else {
            Point p = getTilePointWithExtraOffset(xIndex, yIndex);
            BufferedImage tempBI = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
            tempBI.getGraphics().drawString(p.toString(), 10, 128);
            label.setIcon(new ImageIcon(tempBI));
            tileManager.buffer(currentZoomLevel, p, label);
        }
    }

    private BufferedImage getTileWithExtraOffset(int extraX, int extraY) {
        Point point = getTilePointWithExtraOffset(extraX, extraY);
        return tileManager.getTileImage(point, currentZoomLevel);
    }

    private Point getTilePointWithExtraOffset(int extraX, int extraY) {
        return getTilePointWithExtraOffset(extraX, extraY, currentZoomLevel);
    }

    private Point getTilePointWithExtraOffset(int extraX, int extraY, int zoom) {
        int power = (19 - zoom);
        Point point = ((Point) initialList.get(zoom).clone());
        point.setLocation(
                point.getX() + (xTileOffset / Math.pow(2, power)) + extraX,
                point.getY() + (yTileOffset / Math.pow(2, power)) + extraY
        );
        return point;
    }

    private void findParentFrame() {
        Component currentComponent = this;
        while (jFrame == null) {
            Component parent = currentComponent.getParent();
            if (parent == null)
                break;
            if (parent instanceof JFrame)
                jFrame = ((JFrame) parent);
            currentComponent = parent;
        }
    }

    public void setXPaintOffset(int xPaintOffset) {
        this.xPaintOffset = xPaintOffset;
    }

    public void setYPaintOffset(int yPaintOffset) {
        this.yPaintOffset = yPaintOffset;
    }

    private void bufferRequest() {
        bufferRequest(labels.size() > labels.get(0).size() ? labels.size() / 2 + 1 : labels.get(0).size() / 2 + 1, currentZoomLevel + 1);
        bufferRequest(labels.size() > labels.get(0).size() ? labels.size() / 2 + 1 : labels.get(0).size() / 2 + 1, currentZoomLevel - 1);
        bufferRequest(labels.size() > labels.get(0).size() ? labels.size() : labels.get(0).size(), currentZoomLevel);
    }

    private void bufferRequest(int radius, int zoom) {
        if (!validateZoom(zoom))
            return;

        Point centerPoint = getTilePointWithExtraOffset(labels.size() / 2, labels.get(0).size() / 2, zoom);

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                if (Math.sqrt(x * x + y * y) <= radius)
                    tileManager.buffer(
                            zoom, new Point(
                                    centerPoint.x + x,
                                    centerPoint.y + y));
            }
        }
    }

    private Point getTilePoint(double lat, double lon, int zoom) {
        int xTile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
        int yTile = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1 << zoom));
        if (xTile < 0)
            xTile = 0;
        if (xTile >= (1 << zoom))
            xTile = ((1 << zoom) - 1);
        if (yTile < 0)
            yTile = 0;
        if (yTile >= (1 << zoom))
            yTile = ((1 << zoom) - 1);

        return new Point(xTile, yTile);
    }

    public int getCurrentZoomLevel() {
        return currentZoomLevel;
    }

    @Override
    public void paint(Graphics g) {
        synchronized (this) {
            g.translate(xPaintOffset, yPaintOffset);
            super.paint(g);
        }
    }

    private class OverlayComponentAdapter extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);
            System.out.println("arrangeLabelArray() called");
            arrangeLabelArray();
        }
    }

    private class OverlayKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'd':
                    moveHorizontal(1);
                    break;
                case 'a':
                    moveHorizontal(-1);
                    break;
                case 'w':
                    moveVertical(-1);
                    break;
                case 's':
                    moveVertical(1);
                    break;
                case 'e':
                    increaseCurrentZoom();
                    break;
                case 'q':
                    decreaseCurrentZoom();
                    break;
                case 'i':
                    JFrame jFrame = new JFrame();
                    jFrame.setBounds(100, 100, 256, 256);
                    jFrame.setLayout(null);
                    jFrame.setResizable(false);
                    JLabel jLabel = new JLabel(labels.get(0).get(0).getIcon());
                    jLabel.setBounds(0, 0, 256, 256);
                    jFrame.getContentPane().add(jLabel);
                    jFrame.setVisible(true);
                    break;
                case 'l':
                    moveCordsVisibleAt(11.1181027, 46.6140000, 0, 0);
                    System.out.println();
                    break;
            }
        }
    }
}
