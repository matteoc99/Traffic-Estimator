package gui.city;

import gui.city.Overlay.Overlay;
import logic.city.City;
import utils.math.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class CityMap extends JPanel {

    private static Cursor blankCursor;

    private Overlay overlay;
    private JCity jCity;

    private JFrame jFrame;

    static {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
    }

    public CityMap(JFrame parent, City city) {
        if (parent == null)
            throw new RuntimeException("CityMap_ERR:Parent can't be null");
        this.jFrame = parent;

        this.setLayout(null);
        overlay = new Overlay(parent, city.getMinLon(), city.getMaxLat(), 19);
        overlay.setLocation(0, 0);
        jCity = new JCity(city, parent);

        this.add(jCity);
        this.add(overlay);
        this.addComponentListener(new CityMapComponentListener());

        CityMapMouseListener cmml = new CityMapMouseListener();

        overlay.addMouseListener(cmml);
        overlay.addMouseMotionListener(cmml);
        overlay.addMouseWheelListener(cmml);

        jCity.addMouseListener(cmml);
        jCity.addMouseMotionListener(cmml);
        jCity.addMouseWheelListener(cmml);
    }

    private class CityMapComponentListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            overlay.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
        }
    }

    private class CityMapMouseListener extends MouseAdapter {

        private Robot robot;

        private Point onClickP = new Point(0, 0);

        {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            Point mousePositionRvOverlay = new Point(mousePos.x - overlay.getLocationOnScreen().x,
                    mousePos.y - overlay.getLocationOnScreen().y);
            Point mousePositionRvCity = new Point(mousePos.x - jCity.getLocationOnScreen().x,
                    mousePos.y - jCity.getLocationOnScreen().y);

            Position osmPos = overlay.getTilePositionAtVisible(mousePositionRvOverlay.x, mousePositionRvOverlay.y);

            int oldZoom = overlay.getCurrentZoomLevel();

            if (e.getWheelRotation() < 0) {
                overlay.increaseCurrentZoom();
                int newZoom = overlay.getCurrentZoomLevel();
                osmPos = Overlay.transformPositionByZoom(osmPos, oldZoom, newZoom);
                overlay.movePositionVisibleAt(osmPos, mousePositionRvOverlay.x, mousePositionRvOverlay.y);

                jCity.zoomIn();
                jCity.moveVisibleAt(new Point(JCity.getXYByOsmTileXY(osmPos.getX()),
                        JCity.getXYByOsmTileXY(osmPos.getY())), mousePositionRvOverlay.x, mousePositionRvOverlay.y);
            } else {
                overlay.decreaseCurrentZoom();
                int newZoom = overlay.getCurrentZoomLevel();
                osmPos = Overlay.transformPositionByZoom(osmPos, oldZoom, newZoom);
                overlay.movePositionVisibleAt(osmPos, mousePositionRvOverlay.x, mousePositionRvOverlay.y);

                jCity.zoomOut();
                jCity.moveVisibleAt(new Point(JCity.getXYByOsmTileXY(osmPos.getX()),
                        JCity.getXYByOsmTileXY(osmPos.getY())), mousePositionRvOverlay.x, mousePositionRvOverlay.y);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            onClickP = e.getLocationOnScreen();

            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            Point mousePositionRvCity = new Point(mousePos.x - jCity.getLocationOnScreen().x,
                    mousePos.y - jCity.getLocationOnScreen().y);
            System.out.println(mousePositionRvCity);

            jFrame.setCursor(blankCursor);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            jFrame.setCursor(Cursor.getDefaultCursor());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point raw = e.getLocationOnScreen();
            int xDiff = (int) (raw.getX() - onClickP.x);
            int yDiff = (int) (raw.getY() - onClickP.y);

            if (Math.abs(xDiff) <= 1 && Math.abs(yDiff) <= 1) {
                return;
            }

            jCity.setLocation(jCity.getX() + xDiff, jCity.getY() + yDiff);
            overlay.movePixels(xDiff, yDiff);

            robot.mouseMove(onClickP.x, onClickP.y);
        }
    }
}
