package gui.city.Overlay;

import com.sun.java.swing.SwingUtilities3;

import javax.swing.*;
import javax.swing.border.Border;
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

    private static Cursor blankCursor;

    private Point onClickP = new Point(0, 0);
    private int xPaintOffset, yPaintOffset = 0;

    private final double initialLat, initialLon;

    private int currentZoomLevel;

    private HashMap<Integer, Point> initialList = new HashMap<>();

    // offset is based on max zoom (19)
    private int xTileOffset, yTileOffset;

    private ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();

    private TileManager tileManager = new OSMTileManager(this);

    private JFrame jFrame;

    static {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
    }

    public Overlay(JFrame jFrame, double initialLat, double initialLon, int zoom) {
        this.initialLat = initialLat;
        this.initialLon = initialLon;
        this.jFrame = jFrame;

        loadInitialList();

        this.setLayout(null);

        setCurrentZoomLevel(zoom);
        arrangeLabelArray();
        fillLabels();

        this.addComponentListener(new OverlayComponentAdapter());
        this.addMouseListener(new OverlayMouseAdapter());
        this.addMouseMotionListener(new OverlayMouseMotionAdapter());

        jFrame.addKeyListener(new OverlayKeyListener());
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

    /**
     * Positive: right
     * negative: left
     *
     * @param distance amount of tiles (based on zoom 19)
     */
    public void moveHorizontal(int distance) {
        moveOffset(distance, 0);
    }

    /**
     * Positive: up
     * negative: down
     *
     * @param distance amount of tiles (based on zoom 19)
     */
    public void moveVertical(int distance) {
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
            ArrayList<JLabel> newRow = new ArrayList<>();

            ArrayList<JLabel> row;
            try {
                row = labels.get(xIndex);
            } catch (Exception e) {
                row = new ArrayList<>();
            }

            for (int yIndex = 0; yIndex < visibleY + 2; yIndex++) {
                JLabel label;
                try {
                    label = row.get(yIndex);
                } catch (Exception e) {
                    label = new JLabel();
                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                trySettingIcon(xIndex, yIndex, label);
                newRow.add(yIndex, label);
            }

            newLabelList.add(newRow);
        }

        labels = newLabelList;

        layoutLabels();
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

    public BufferedImage getTileWithExtraOffset(int extraX, int extraY) {
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
            arrangeLabelArray();
        }
    }

    private class OverlayMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            onClickP = e.getLocationOnScreen();
            if (jFrame == null)
                Overlay.this.findParentFrame();
            if (jFrame != null)
                jFrame.setCursor(blankCursor);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (jFrame == null)
                Overlay.this.findParentFrame();
            if (jFrame != null)
                jFrame.setCursor(Cursor.getDefaultCursor());
        }
    }

    private class OverlayMouseMotionAdapter extends MouseMotionAdapter {
        Robot robot;

        {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point raw = e.getLocationOnScreen();
            int xDiff = (int) (raw.getX() - onClickP.x);
            int yDiff = (int) (raw.getY() - onClickP.y);

            if (Math.abs(xDiff) <= 1 && Math.abs(yDiff) <= 1) {
                return;
            }

            xPaintOffset += xDiff;
            yPaintOffset += yDiff;

                if (xPaintOffset > 256) {
                    int c = xPaintOffset / 256;
                    xPaintOffset -= 256 * c;
                    moveHorizontal(-c);
                } else if (xPaintOffset < -256) {
                    int c = xPaintOffset / -256;
                    xPaintOffset += 256 * c;
                    moveHorizontal(c);
                } else if (yPaintOffset > 256) {
                    int c = yPaintOffset / 256;
                    yPaintOffset -= 256 * c;
                    moveVertical(-c);
                } else if (yPaintOffset < -256) {
                    int c = yPaintOffset / -256;
                    yPaintOffset += 256 * c;
                    moveVertical(c);
                } else {
                    repaint();
                }

            robot.mouseMove(onClickP.x, onClickP.y);
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
            }
        }
    }
}
