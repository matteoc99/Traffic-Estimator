package main;

import gui.ControlPanel;
import gui.CustomSlider;
import gui.city.CityMap;
import gui.city.JCity;
import logic.city.City;
import release.beta.Beta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Timestamp;

import static logic.city.StreetlightLogic.CYCLE;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Main extends JFrame {

    //fps stuff
    public static int FPS = 120;
    public static int SPEED_TRIM = 600;
    public static int SAFETY_KONST = 400;


    private ControlPanel controlPanel;
    private Container c;

    CityMap cityMap;


    public Main(City city) {
        setupWindow();
        c = getContentPane();


        cityMap = new CityMap(this, city);
        cityMap.setBounds(0, 0, getWidth()-getWidth()/6, getHeight());
        c.add(cityMap);
        controlPanel = setUpControlPanel();
        cityMap.add(controlPanel, 0);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        city.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Beta();
                city.die();
            }
        });


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
        JCity ret = new JCity(city, this);
        return ret;
    }

    private ControlPanel setUpControlPanel() {
        ControlPanel ret = new ControlPanel();
        ret.setBackground(new Color(86, 90, 200));
        ret.setBounds(getWidth() - getWidth() / 5, 0, getWidth() / 5, getHeight());

        CustomSlider speed = new CustomSlider(JSlider.HORIZONTAL, 1, 800, City.SPEED);
        CustomSlider traffic = new CustomSlider(JSlider.HORIZONTAL, 0, cityMap.jCity.getCity().getNodeSize(), City.VEHICLE_AMOUNT);
        CustomSlider fps = new CustomSlider(JSlider.HORIZONTAL, 1, 150, FPS);
        CustomSlider speedTrim = new CustomSlider(JSlider.HORIZONTAL, 1, 1000, SPEED_TRIM);
        CustomSlider safety = new CustomSlider(JSlider.HORIZONTAL, 1, 2000, SAFETY_KONST);
        CustomSlider ampel= new CustomSlider(JSlider.HORIZONTAL, 1, 30, CYCLE);


        int xOff = ret.getWidth() / 3;

        JLabel[] labels = new JLabel[6];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(20, (ret.getHeight() / 12) * i, xOff - 10, ret.getHeight() / 10);
            labels[i].setFont(new Font("Times New Roman", 0, 18));
            ret.add(labels[i]);
        }

        labels[0].setText("SPEED");
        labels[1].setText("Cars");
        labels[2].setText("FPS");
        labels[3].setText("Precision");
        labels[4].setText("Safety-Dist");
        labels[5].setText("Cycle");


        speed.setBounds(xOff, 10 + (ret.getHeight() / 12) * 0, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        traffic.setBounds(xOff, 10 + (ret.getHeight() / 12) * 1, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        fps.setBounds(xOff, 10 + (ret.getHeight() / 12) * 2, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        speedTrim.setBounds(xOff, 10 + (ret.getHeight() / 12) * 3, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        safety.setBounds(xOff, 10 + (ret.getHeight() / 12) * 4, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        ampel.setBounds(xOff, 10 + (ret.getHeight() / 12) * 5, ret.getWidth() - xOff - 20, ret.getHeight() / 10);

        speed.setMajorTickSpacing(500);
        traffic.setMajorTickSpacing(cityMap.jCity.getCity().getNodeSize() / 2);
        fps.setMajorTickSpacing(40);
        speedTrim.setMajorTickSpacing(400);
        safety.setMajorTickSpacing(5000);
        ampel.setMajorTickSpacing(5);

        speed.setMinorTickSpacing(250);
        traffic.setMinorTickSpacing(cityMap.jCity.getCity().getNodeSize() / 5);
        fps.setMinorTickSpacing(10);
        speedTrim.setMinorTickSpacing(100);
        safety.setMinorTickSpacing(500);
        ampel.setMinorTickSpacing(1);

        speed.addChangeListener(e -> {
            City.SPEED = speed.getValue();
            cityMap.repaint();
            requestFocus();
        });

        traffic.addChangeListener(e -> {
            City.VEHICLE_AMOUNT = traffic.getValue();
            cityMap.repaint();
            requestFocus();
        });
        fps.addChangeListener(e -> {
            FPS = fps.getValue();
            cityMap.repaint();
            requestFocus();
        });
        speedTrim.addChangeListener(e -> {
            SPEED_TRIM = speedTrim.getValue();
            cityMap.repaint();
            requestFocus();
        });
        safety.addChangeListener(e -> {
            SAFETY_KONST = safety.getValue();
            cityMap.repaint();
            requestFocus();
        });

        ampel.addChangeListener(e -> {
            CYCLE = ampel.getValue();
            cityMap.repaint();
            requestFocus();
        });

        ret.add(traffic);
        ret.add(speed);
        ret.add(fps);
        ret.add(speedTrim);
        ret.add(safety);
        ret.add(ampel);
        return ret;
    }

    @Deprecated
    public static void main(String[] args) {
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Creating City from .json...");
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\res\\bozenLarge.json"));
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        new Main(city);
    }

    public static void start(City cityFromJson) {

        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        Main main = new Main(cityFromJson);

    }
}
