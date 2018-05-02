package main;

import gui.ControlPanel;
import gui.CustomSlider;
import gui.city.CityMap;
import gui.city.JCity;
import logic.city.City;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Main extends JFrame {

    //fps stuff
    public static int FPS = 120;
    public static int SPEED_TRIM= 600;
    public static int SAFETY_KONST= 8000;


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
        cityMap.add(controlPanel,0);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        city.start();
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

        CustomSlider speed = new CustomSlider(JSlider.HORIZONTAL, 1, 800, City.SPEED);
        CustomSlider traffic = new CustomSlider(JSlider.HORIZONTAL, 0, cityMap.jCity.getCity().getNodeSize()/2, City.VEHICLE_AMOUNT);
        CustomSlider fps = new CustomSlider(JSlider.HORIZONTAL, 1, 150, FPS);
        CustomSlider speedTrim= new CustomSlider(JSlider.HORIZONTAL, 1, 6000, SPEED_TRIM);
        CustomSlider safety= new CustomSlider(JSlider.HORIZONTAL, 1, 20000, SAFETY_KONST);


        int xOff = ret.getWidth() / 4;

        JLabel[] labels = new JLabel[5];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(20, (ret.getHeight() / 12) * i, xOff - 10, ret.getHeight() / 10);
            labels[i].setFont(new Font("Times New Roman", 0, 20));
            ret.add(labels[i]);
        }

        labels[0].setText("SPEED");
        labels[1].setText("Traffic");
        labels[2].setText("FPS");
        labels[3].setText("Speed-Trimm");
        labels[4].setText("Safety-Dist");


        speed.setBounds(xOff, 10 + (ret.getHeight() / 12) * 0, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        traffic.setBounds(xOff, 10 + (ret.getHeight() / 12) * 1, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        fps.setBounds(xOff, 10 + (ret.getHeight() / 12) * 2, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        speedTrim.setBounds(xOff, 10 + (ret.getHeight() / 12) * 3, ret.getWidth() - xOff - 20, ret.getHeight() / 10);
        safety.setBounds(xOff, 10 + (ret.getHeight() / 12) * 4, ret.getWidth() - xOff - 20, ret.getHeight() / 10);

        speed.setMajorTickSpacing(500);
        traffic.setMajorTickSpacing(cityMap.jCity.getCity().getNodeSize()/4);
        fps.setMajorTickSpacing(40);
        speedTrim.setMajorTickSpacing(1200);
        safety.setMajorTickSpacing(10000);

        speed.setMinorTickSpacing(250);
        traffic.setMinorTickSpacing(cityMap.jCity.getCity().getNodeSize()/10);
        fps.setMinorTickSpacing(10);
        speedTrim.setMinorTickSpacing(120);
        safety.setMinorTickSpacing(1000);
        speed.addChangeListener(e -> {
            City.SPEED = speed.getValue() ;
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
            SAFETY_KONST= safety.getValue();
            cityMap.repaint();
            requestFocus();
        });

        ret.add(traffic);
        ret.add(speed);
        ret.add(fps);
        ret.add(speedTrim);
        ret.add(safety);
        return ret;
    }

    @Deprecated
    public static void main(String[] args) {
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Creating City from .json...");
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\res\\bozen.json"));
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        Main main = new Main(city);
    }

    public static void start(City cityFromJson) {

        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        Main main = new Main(cityFromJson);

    }
}
