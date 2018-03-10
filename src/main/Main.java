package main;

import gui.ControlPanel;
import gui.CustomSlider;
import gui.city.JCity;
import logic.city.City;
import logic.city.Node;
import logic.vehicles.Vehicle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.util.Iterator;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Main extends JFrame {

    //fps stuff
    public static int FPS = 20;
    public static long zeitvorsleep;


    private static JCity jCity;
    private ControlPanel controlPanel;
    private Container c;
    City city;

    int effizienz = 0;

    //moving modes
    public static boolean hovermode = false;
    public static Point hoverpoint = null;


    /**
     * Used to drag & drop the contacts
     */
    private Point fromCords;

    public Main(City city) {
        setupWindow();
        this.city = city;
        c = getContentPane();
        controlPanel = setUpControlPanel();
        jCity = setUpCity(city);
        c.add(controlPanel);
        c.add(jCity);
        setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //getNodesPos average
        while (getAvgNodePosition() > getHeight() / 2) {
            jCity.zoomOut();
        }
        while (getAvgNodePosition() < getHeight() / 4) {
            jCity.zoomIn();
        }
        jCity.setLocation(0, 0);

        repaint();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_PLUS:
                        //zoom++
                        jCity.zoomIn();
                        break;
                    case KeyEvent.VK_MINUS:
                        jCity.zoomOut();

                        break;
                    case KeyEvent.VK_LEFT:
                        jCity.setLocation(jCity.getX() + 10, jCity.getY());
                        break;
                    case KeyEvent.VK_RIGHT:
                        jCity.setLocation(jCity.getX() - 10, jCity.getY());
                        break;
                    case KeyEvent.VK_UP:
                        jCity.setLocation(jCity.getX(), jCity.getY() + 10);
                        break;
                    case KeyEvent.VK_DOWN:
                        jCity.setLocation(jCity.getX(), jCity.getY() - 10);
                        break;
                    case KeyEvent.VK_H:
                        if (hoverpoint == null) {
                            hovermode = true;
                            hoverpoint = MouseInfo.getPointerInfo().getLocation();
                        } else {
                            int xoff = hoverpoint.x - MouseInfo.getPointerInfo().getLocation().x;
                            int yoff = hoverpoint.y - MouseInfo.getPointerInfo().getLocation().y;
                            xoff /= 4;
                            yoff /= 4;
                            jCity.setLocation(jCity.getX() - xoff, jCity.getY() - yoff);
                        }
                        break;
                }
                    jCity.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_H:
                        hovermode = false;
                        hoverpoint = null;
                        break;
                    case KeyEvent.VK_L:
                        jCity.toggleShowLights();
                        break;
                    case KeyEvent.VK_S:
                        jCity.toggleShowStreets();
                        break;
                    case KeyEvent.VK_V:
                        jCity.toggleShowCars();
                        break;
                    case KeyEvent.VK_R:
                        while (getAvgNodePosition() > getHeight() / 2) {
                            jCity.zoomOut();
                        }
                        while (getAvgNodePosition() < getHeight() / 4) {
                            jCity.zoomIn();
                        }
                        jCity.setLocation(0, 0);
                        repaint();
                        break;
                    case KeyEvent.VK_P:
                        jCity.setLocation(0, 0);
                        repaint();
                        break;
                }
            }
        });
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    //zoom++
                    jCity.zoomIn();
                } else {
                    //zoom--
                    jCity.zoomOut();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                double x = e.getX() - jCity.getX();
                double y = e.getY() - jCity.getY();
                x /= JCity.getZoom();
                y /= JCity.getZoom();
                System.out.println(city.getStreetByPoint(new Point((int) x, (int) y)));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fromCords = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point toCords = e.getPoint();
                int offsetX = toCords.x - fromCords.x;
                int offsetY = toCords.y - fromCords.y;
                jCity.setLocation(jCity.getX() + offsetX, jCity.getY() + offsetY);
                fromCords = toCords;
            }
        };

        addMouseWheelListener(mouseAdapter);

        addMouseMotionListener(mouseAdapter);

        addMouseListener(mouseAdapter);


        requestFocus();

        city.start();

        while (true) {
            zeitvorsleep = System.currentTimeMillis();
            repaint();
            long zeitvergangen = (long) (System.currentTimeMillis() - zeitvorsleep);
            if (zeitvergangen < 1000.0 / FPS) {
                try {
                    Thread.sleep((long) (1000.0 / FPS - zeitvergangen));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * get avg node pos relative to zoom
     */
    private int getAvgNodePosition() {
        int sumX = 0, sumY = 0;
        Iterator<Node> nodes = city.getNodeIterator();
        while (nodes.hasNext()) {
            Node node = nodes.next();
            sumX += node.getX() * JCity.getZoom();
            sumY += node.getY() * JCity.getZoom();
        }

        return Integer.min(sumX / city.getNodeSize(), sumY / city.getNodeSize());
    }

    private void setupWindow() {
        setTitle("Traffic Estimation");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JCity setUpCity(City city) {
        JCity ret = new JCity(city,this);
        return ret;
    }

    private ControlPanel setUpControlPanel() {
        ControlPanel ret = new ControlPanel();
        ret.setBackground(new Color(86, 90, 200));
        ret.setBounds(getWidth() - getWidth() / 6, 0, getWidth() / 6, getHeight());

        CustomSlider speed = new CustomSlider(JSlider.HORIZONTAL, 0, 1500, City.SPEED);
        CustomSlider traffic = new CustomSlider(JSlider.HORIZONTAL, 0, 10000, City.VEHICLE_AMOUNT);
        CustomSlider fps = new CustomSlider(JSlider.HORIZONTAL, 0, 80, FPS);


        int xOff = ret.getWidth() / 4;

        JLabel[] labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(20, (ret.getHeight() / 12) * i, xOff - 10, ret.getHeight() / 10);
            labels[i].setFont(new Font("Times New Roman", 0, 20));
            ret.add(labels[i]);
        }

        labels[0].setText("SPEED");
        labels[1].setText("Traffic");
        labels[2].setText("FPS");


        speed.setBounds(xOff, 10 + (ret.getHeight() / 12) * 0, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        traffic.setBounds(xOff, 10 + (ret.getHeight() / 12) * 1, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        fps.setBounds(xOff, 10 + (ret.getHeight() / 12) * 2, ret.getWidth() - xOff - 20, ret.getHeight() / 10);

        speed.setMajorTickSpacing(500);
        traffic.setMajorTickSpacing(5000);
        fps.setMajorTickSpacing(20);

        speed.setMinorTickSpacing(250);
        traffic.setMinorTickSpacing(1000);
        fps.setMinorTickSpacing(5);
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                City.SPEED = speed.getValue() + 1;
                requestFocus();
            }
        });

        traffic.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                City.VEHICLE_AMOUNT = traffic.getValue() + 1;
                requestFocus();
            }
        });
        fps.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                FPS = fps.getValue() + 1;
                requestFocus();
            }
        });
        ret.add(traffic);
        ret.add(speed);
        ret.add(fps);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Creating City from .json...");
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\res\\testcity.json"));
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        Main main = new Main(city);
    }

}
