package gui;

import com.sun.deploy.util.ArrayUtil;
import gui.city.JCity;
import logic.city.City;
import logic.city.Street;
import logic.city.StreetIntersection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Main extends JFrame {

    public static JCity jCity;
   ControlPanel controlPanel;
    Container c;
    //JProgressBar loadingProgress;


    public Main(City city) {
        //setup window
        setupWindow();

        //loading progress
        /*loadingProgress= new JProgressBar();
        loadingProgress.setMinimum(0);
        loadingProgress.setValue(0);
        loadingProgress.setMaximum(100);
        loadingProgress.setSize(200,40);
        loadingProgress.setLocation(440,330);*/
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
        City city = new City("Bozen");
        ArrayList<StreetIntersection> streetIntersections = new ArrayList<>();
        streetIntersections.add(new StreetIntersection(20, 20, 30, 30, 1, "Kreuzung1"));
        streetIntersections.add(new StreetIntersection(100, 20, 30, 30, 1, "Kreuzung2"));
        streetIntersections.add(new StreetIntersection(20, 100, 30, 30, 1, "Kreuzung3"));
        streetIntersections.add(new StreetIntersection(100, 100, 30, 30, 1, "Kreuzung4"));
        for (StreetIntersection intersection : streetIntersections) {
            city.addStreetIntersection(intersection);
        }
        Street street1 = new Street(streetIntersections.get(0),streetIntersections.get(1));
        Street street2 = new Street(streetIntersections.get(1),streetIntersections.get(2));
        Street street3 = new Street(streetIntersections.get(2),streetIntersections.get(3));
        new Main(city);

    }
}
