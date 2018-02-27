package main;

import gui.ControlPanel;
import gui.CustomSlider;
import gui.city.JCity;
import logic.city.City;
import logic.city.Node;
import logic.vehicles.Vehicle;
import utils.Stopwatch;

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

    private static JCity jCity;
    private ControlPanel controlPanel;
    private Container c;
    City city;

    int effizienz = 0;


    public static double zoom = 1;
    //zoom modes
    public static boolean fineZoom = false;
    public static boolean superFineZoom = false;
    public static boolean preciseZoom = true; //zoom to mouse point

    //moving modes
    public static boolean hovermode = false;
    public static Point hoverpoint = null;

    //fps stuff
    public static int FPS = 25;
    public static long zeitvorsleep;


    //Slider controlls
    public static int VEHICLE_AMOUNT = 0;


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
        jCity.setBackground(Color.RED);
        c.add(controlPanel);
        c.add(jCity);
        setResizable(false);

        //getNodesPos average
        while (getAvgNodePosition() > getHeight() / 2) {
            zoom /= 1.5;
        }
        while (getAvgNodePosition() < getHeight() / 4) {
            zoom *= 1.5;
        }
        jCity.setLocation(0, 0);

        repaint();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                boolean recalc = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_PLUS:
                        //zoom++
                        if (fineZoom) {
                            zoom += 0.01;
                        } else if (superFineZoom) {
                            zoom += 0.0005;
                        } else {
                            zoom += 0.5;
                        }
                        recalc = true;
                        break;
                    case KeyEvent.VK_MINUS:
                        if (fineZoom) {
                            if (zoom > 0.01)
                                zoom -= 0.01;
                        } else if (superFineZoom) {
                            if (zoom > 0.0005)
                                zoom -= 0.0005;
                        } else {
                            if (zoom > 0.5)
                                zoom -= 0.5;

                        }
                        recalc = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        jCity.setLocation(jCity.getX() + 10, jCity.getY());
                        recalc = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        jCity.setLocation(jCity.getX() - 10, jCity.getY());
                        recalc = true;

                        break;
                    case KeyEvent.VK_UP:
                        jCity.setLocation(jCity.getX(), jCity.getY() + 10);
                        recalc = true;

                        break;
                    case KeyEvent.VK_DOWN:
                        recalc = true;
                        jCity.setLocation(jCity.getX(), jCity.getY() - 10);
                        break;
                    case KeyEvent.VK_F:
                        fineZoom = true;
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
                    case KeyEvent.VK_S:
                        superFineZoom = true;
                        break;
                }
                if (recalc) {
                    jCity.repaint();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F:
                        fineZoom = false;
                        break;
                    case KeyEvent.VK_H:
                        hovermode = false;
                        hoverpoint = null;
                        break;
                    case KeyEvent.VK_S:
                        superFineZoom = false;
                        break;
                    case KeyEvent.VK_P:
                        preciseZoom = !preciseZoom;
                        break;
                    case KeyEvent.VK_R:
                        while (getAvgNodePosition() > getHeight() / 2) {
                            zoom /= 1.5;
                        }
                        while (getAvgNodePosition() < getHeight() / 4) {
                            zoom *= 1.5;
                        }
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
                    if (preciseZoom) {
                        //// TODO: 17.02.2018 kp wie
                    }
                    if (fineZoom) {
                        zoom += 0.01;
                    } else if (superFineZoom) {
                        zoom += 0.0005;
                    } else {
                        zoom += 0.5;
                    }

                } else {
                    //zoom--
                    if (preciseZoom) {
                        // TODO: 17.02.2018 kp wie
                    }
                    if (fineZoom) {
                        if (zoom > 0.01)
                            zoom -= 0.01;
                    } else if (superFineZoom) {
                        if (zoom > 0.0005)
                            zoom -= 0.0005;
                    } else {
                        if (zoom > 0.5)
                            zoom -= 0.5;

                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                double x = e.getX() - jCity.getX();
                double y = e.getY() - jCity.getY();
                x /= zoom;
                y /= zoom;
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

    }


    /**
     * get avg node pos relative to zoom
     */
    private int getAvgNodePosition() {
        int sumX = 0, sumY = 0;
        Iterator<Node> nodes = city.getNodeIterator();
        while (nodes.hasNext()) {
            Node node = nodes.next();
            sumX += node.getX() * zoom;
            sumY += node.getY() * zoom;
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
        JCity ret = new JCity(city);
        ret.setBackground(new Color(123, 200, 126));
        return ret;
    }

    private ControlPanel setUpControlPanel() {
        ControlPanel ret = new ControlPanel();
        ret.setBackground(new Color(86, 90, 200));
        ret.setBounds(getWidth() - getWidth() / 6, 0, getWidth() / 6, getHeight());

        CustomSlider fps = new CustomSlider(JSlider.HORIZONTAL, 0, 1000, FPS);
        CustomSlider traffic = new CustomSlider(JSlider.HORIZONTAL, 0, 1000, VEHICLE_AMOUNT);


        int xOff = ret.getWidth() / 4;

        JLabel[] labels = new JLabel[4];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(20, (ret.getHeight() / 12) * i, xOff - 10, ret.getHeight() / 10);
            labels[i].setFont(new Font("Times New Roman", 0, 20));
            ret.add(labels[i]);
        }

        labels[0].setText("FPS");
        labels[1].setText("Traffic");


        fps.setBounds(xOff, 10 + (ret.getHeight() / 12) * 0, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        traffic.setBounds(xOff, 10 + (ret.getHeight() / 12) * 1, ret.getWidth() - xOff - 20, ret.getHeight() / 10);

        System.out.println(fps.getBounds());
        fps.setMajorTickSpacing(500);
        traffic.setMajorTickSpacing(500);

        fps.setMinorTickSpacing(100);
        traffic.setMinorTickSpacing(100);
        fps.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                FPS = fps.getValue() + 1;
                requestFocus();
            }
        });

        traffic.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                VEHICLE_AMOUNT = traffic.getValue() + 1;
                requestFocus();
            }
        });
        ret.add(traffic);
        ret.add(fps);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Creating City from .json...");
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\res\\bozenLarge.json"));

        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        Main main = new Main(city);

        while (true) {
            zeitvorsleep = System.currentTimeMillis();
            main.calcCity();
            if (city.getVehicles().size() < VEHICLE_AMOUNT) {
                Vehicle vehicle = new Vehicle();

                vehicle.setColor(new Color((int) ((Math.random() * 200) + 50), (int) (Math.random() * 200) + 50, (int) (Math.random() * 200) + 50));
            }
            long zeitvergangen = (long) (System.currentTimeMillis() - zeitvorsleep);
            if (zeitvergangen < 1000.0 / FPS) {
                try {
                    Thread.sleep((long) (1000.0 / FPS - zeitvergangen));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // System.out.println("FPS:"+(long) (1000.0/FPS - zeitvergangen));


        }

    }

    private void calcCity() {
        city.calcCity();
        repaint();
        jCity.repaint();
    }
}
