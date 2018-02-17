package gui;

import com.sun.deploy.util.ArrayUtil;
import com.sun.org.apache.regexp.internal.RE;
import gui.city.JCity;
import logic.PathUtils;
import logic.city.City;
import logic.city.Node;
import logic.city.Path;
import logic.city.Street;
import logic.vehicles.Vehicle;
import utils.Stopwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Main extends JFrame {

    private static JCity jCity;
    private ControlPanel controlPanel;
    private Container c;
    City city;

    int effizienz=0;


    public static double zoom = 1;
    public static boolean finezoom = false;
    public static boolean hovermode= false;

    public static final int FPS = 25;
    public static long zeitvorsleep;


    public Main(City city) {
        setupWindow();
        this.city = city;
        c = getContentPane();
        controlPanel = setUpControlPanel();
        jCity = setUpCity(city);
        jCity.setBackground(Color.RED);
        c.add(controlPanel);
        c.add(jCity);

        //getNodesPos durchschnitt
        while (getAvgNodePosition() > getHeight()/2) {
            System.out.println(getAvgNodePosition());
            zoom /= 1.5;
            repaint();
        }
        while (getAvgNodePosition() < getHeight()/4) {
            System.out.println(getAvgNodePosition());
            zoom *=1.5;
            repaint();
        }


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                boolean recalc = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_PLUS:
                        zoom += Math.log(1 + zoom);
                        recalc = true;
                        break;
                    case KeyEvent.VK_MINUS:
                        zoom -= Math.log(1 + zoom);
                        if (zoom < 0.00000000001)
                            zoom = 0.0000001;
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
                        finezoom = true;
                        break;
                    case KeyEvent.VK_H:
                        hovermode = true;
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
                        finezoom = false;
                        break;
                    case KeyEvent.VK_H:
                        hovermode =false;
                        break;
                }
            }
        });

    }

    /**
     * get avg node pos relative to zoom
     *
     * @return
     */
    private int getAvgNodePosition() {
        int sumx = 0, sumy = 0;
        for (Node node : city.getNodes()) {
            sumx += node.getX() * zoom;
            sumy += node.getY() * zoom;
        }
        return Integer.min(sumx / city.getNodes().size(), (int) (sumy / city.getNodes().size()));
    }

    private void setupWindow() {
        setTitle("Traffic Estimation");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
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
        ret.setBounds(getWidth()-getWidth()/5, 0, getWidth()/5, getHeight());
        return ret;
    }

    public static void main(String[] args) {
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\testcity.json"));


        Main main = new Main(city);


        while (true) {
            zeitvorsleep = System.currentTimeMillis();
            main.calcCity();
            if (city.getVehicles().size() < 30) {
               Vehicle vehicle = new Vehicle(1000, 60, PathUtils.getRandomPath(city), 1, 1, city);
            }
            long zeitvergangen = (long) (System.currentTimeMillis() - zeitvorsleep);
            if (zeitvergangen < 1000.0 / FPS) {
                try {
                    Thread.sleep((long) (1000.0 / FPS - zeitvergangen));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("FPS:"+(long) (1000.0/FPS - zeitvergangen));


        }

    }

    private void calcCity() {
        Stopwatch timer = new Stopwatch().start();

        System.out.println(zoom);
        city.calcCity();
        timer.printAndReset("Main_BDG_ME: 1: ");
        repaint();
        timer.printAndReset("Main_BDG_ME: 2: ");
        jCity.repaint();
        timer.printAndReset("Main_BDG_ME: 3: ");
    }
}
