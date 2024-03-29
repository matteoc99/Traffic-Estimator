package gui.city;

import gui.city.Overlay.Overlay;
import logic.city.City;
import main.Main;
import utils.math.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class CityMap extends JPanel {

    private static Cursor blankCursor;

    public Overlay overlay;
    public JCity jCity;

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

        // TODO: 27.06.2018 Remove me, just here because of bug.
        overlay.readTileManagerImages();

        parent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p')
                    overlay.printTileManagerImages();
                if (e.getKeyChar() == 'r') {
                    System.out.println("Reading images");
                    overlay.readTileManagerImages();
                }
            }
        });



        jCity.setLocation(jCity.getX() - 20000, jCity.getY() - 20000);
        overlay.movePixels(-20000, -20000);


        jCity.addMouseListener(cmml);
        jCity.addMouseMotionListener(cmml);
        jCity.addMouseWheelListener(cmml);

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

            { // TODO: 22.04.2018 Debug only
                Point mousePos = MouseInfo.getPointerInfo().getLocation();
                Point mousePositionRvCity = new Point(mousePos.x - jCity.getLocationOnScreen().x,
                        mousePos.y - jCity.getLocationOnScreen().y);

                Position osmPosition = new Position(
                        JCity.getOSMTileXYByXY((int) (mousePositionRvCity.getX() / JCity.zoom)),
                        JCity.getOSMTileXYByXY((int) (mousePositionRvCity.getY() / JCity.zoom))
                );

                if (e.getButton() == 3) {
                    System.out.println(jCity.getCity().getStreetByPoint(osmPosition));
                    jCity.getCity().getStreetByPoint(osmPosition).toggleActive();
                }
            }

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
