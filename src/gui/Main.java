package gui;

import com.sun.deploy.util.ArrayUtil;
import gui.city.JCity;
import logic.city.City;
import logic.city.Street;

import javax.swing.*;
import java.awt.*;
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


    public Main(City city) {
        //setup window
        setupWindow();

        //adding components
        c = getContentPane();
        controlPanel = setUpControlPanel();
        jCity = setUpCity(city);
        c.add(controlPanel);
        c.add(jCity);
        //  city.add(loadingProgress);
        repaint();
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
        ret.setBackground(Color.BLUE);
        ret.setBounds(0, 0, 980, 720);
        return ret;
    }

    private ControlPanel setUpControlPanel() {
        ControlPanel ret = new ControlPanel();
        ret.setBackground(Color.RED);
        ret.setBounds(980, 0, 300, 720);
        return ret;
    }

    public static void main(String[] args) {
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir")+"\\src\\parsing\\testcity.json"));
        new Main(city);

    }
}
