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

    public static double zoom = 1;
    public static double prevZoom = 1;


    public Main(City city) {
        setupWindow();
        this.city = city;
        c = getContentPane();
        controlPanel = setUpControlPanel();
        jCity = setUpCity(city);
        jCity.setBackground(Color.RED);
        c.add(controlPanel);
        c.add(jCity);


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                boolean recalc = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_PLUS:
                        prevZoom = zoom;
                        zoom += Math.log(1 + zoom);
                        recalc = true;
                        break;
                    case KeyEvent.VK_MINUS:
                        prevZoom = zoom;
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
                }
                if (recalc) {
                    jCity.repaint();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    private void setupWindow() {
        setTitle("Traffic Estimation");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 1280, 720);
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
        ret.setBounds(980, 0, 300, 720);
        return ret;
    }

    public static void main(String[] args) {
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\testcity.json"));


        Main main = new Main(city);
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            main.calcCity();

            if (city.getVehicles().size() < 20) {
                Vehicle vehicle = new Vehicle(1000, 60, PathUtils.getRandomPath(city), 1, 1, city);
            }


        }

    }

    private void calcCity() {
        Stopwatch timer = new Stopwatch().start();
        city.calcCity();
        timer.printAndReset("Main_BDG_ME: 1: ");
        repaint();
        timer.printAndReset("Main_BDG_ME: 2: ");
        jCity.repaint();
        timer.printAndReset("Main_BDG_ME: 3: ");
    }
}
