package gui.city.Overlay;

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

    private static Cursor blankCursor;

    private Point onClickP = new Point(0, 0);
    private int xPaintOffset, yPaintOffset = 0;

    private final double initialLat, initialLon;

    private int currentZoomLevel;

    private HashMap<Integer, Point> initialList = new HashMap<>();

    // offset is based on max zoom (19)
    private int xTileOffset, yTileOffset;

    private ArrayList<ArrayList<JLabel>> labels = new ArrayList<>();

    private TileManager tileManager = new TileManager(this);

    private JFrame jFrame;

    static {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
    }

    public Overlay(double initialLat, double initialLon, int zoom) {
        this.initialLat = initialLat;
        this.initialLon = initialLon;

        loadInitialList();

        this.setLayout(null);

        setCurrentZoomLevel(zoom);
        arrangeLabelArray();
        fillLabels();

        this.addComponentListener(new OverlayComponentAdapter());
        this.addMouseListener(new OverlayMouseAdapter());
        this.addMouseMotionListener(new OverlayMouseMotionAdapter());
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
                initialList.put(zoom, tileManager.getTilePoint(initialLat, initialLon, zoom));
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
        tileManager.tileBuffer.clearLabelsToBuffer();
        tileManager.tileBuffer.clearTilesToBuffer();
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
        tileManager.tileBuffer.clearLabelsToBuffer();
        fillLabels();
        bufferRequest();
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
                }
                trySettingIcon(xIndex, yIndex, label);
                newRow.add(yIndex, label);
            }

            newLabelList.add(newRow);
        }

        labels = newLabelList;

        layoutLabels();
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
        Component currentComponent = Overlay.this;
        while (jFrame == null) {
            Component parent = currentComponent.getParent();
            if (parent == null)
                break;
            if (parent instanceof JFrame)
                jFrame = ((JFrame) parent);
            currentComponent = parent;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.translate(xPaintOffset, yPaintOffset);
        super.paint(g);
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

        Point centerPoint = getTilePointWithExtraOffset(labels.size()/2, labels.get(0).size()/2, zoom);

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                if (Math.sqrt(x * x + y * y) <= radius)
                    tileManager.tileBuffer.putTileToBuffer(
                            zoom, new Point(
                                    centerPoint.x + x,
                                    centerPoint.y + y
                    ));
            }
        }
        tileManager.tileBuffer.putTileToBuffer(zoom, new Point());
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
            int xDiff = (int) (raw.getX() - onClickP.getX());
            int yDiff = (int) (raw.getY() - onClickP.getY());

            if (Math.abs(xDiff) <= 1 && Math.abs(yDiff) <= 1)
                return;

            xPaintOffset += xDiff;
            yPaintOffset += yDiff;


            if (xPaintOffset > 256) {
                int c = xPaintOffset / 256;
                moveHorizontal(-c);
                xPaintOffset -= 256 * c;
            } else if (xPaintOffset < -256) {
                int c = xPaintOffset / -256;
                moveHorizontal(c);
                xPaintOffset += 256 * c;
            } else if (yPaintOffset > 256) {
                int c = yPaintOffset / 256;
                moveVertical(-c);
                yPaintOffset -= 256 * c;
            } else if (yPaintOffset < -256) {
                int c = yPaintOffset / -256;
                moveVertical(c);
                yPaintOffset += 256 * c;
            }

            robot.mouseMove(onClickP.x, onClickP.y);

            repaint();
        }
    }
}