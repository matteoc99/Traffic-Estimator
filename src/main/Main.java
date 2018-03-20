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

    // FIXME: 13.03.2018 yep; effizienz gleich 0 xD ... nana jk
    int effizienz = 0;

    public Main(City city) {
        setupWindow();
        c = getContentPane();
        controlPanel = setUpControlPanel();
        CityMap cityMap = new CityMap(this, city);
        cityMap.setBounds(0, 0, getWidth()-getWidth()/6, getHeight());
        c.add(cityMap);
        c.add(controlPanel);

        setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        repaint();

        requestFocus();

        city.start();

        while (true) {
            zeitvorsleep = System.currentTimeMillis();
            repaint();
            long zeitvergangen = (System.currentTimeMillis() - zeitvorsleep);
            if (zeitvergangen < 1000.0 / FPS) {
                try {
                    Thread.sleep((long) (1000.0 / FPS - zeitvergangen));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
        speed.addChangeListener(e -> {
            City.SPEED = speed.getValue() + 1;
            requestFocus();
        });

        traffic.addChangeListener(e -> {
            City.VEHICLE_AMOUNT = traffic.getValue() + 1;
            requestFocus();
        });
        fps.addChangeListener(e -> {
            FPS = fps.getValue() + 1;
            requestFocus();
        });
        ret.add(traffic);
        ret.add(speed);
        ret.add(fps);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Creating City from .json...");
        City city = City.createCityFromJson(
                new File(System.getProperty("user.dir") + "\\src\\parsing\\res\\lana_NewXY.json"));
        System.out.println("Main:" + new Timestamp(System.currentTimeMillis()) + " Starting GUI...");
        Main main = new Main(city);
    }

}
