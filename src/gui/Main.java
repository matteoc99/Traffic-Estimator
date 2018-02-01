package gui;

import com.sun.deploy.util.ArrayUtil;
import gui.city.JCity;
import logic.city.City;
import logic.city.Street;

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


    public static double zoom = 1;
    public static int xOffset = 0;
    public static int yOffset = 0;


    public Main(City city) {
        setupWindow();

        c = getContentPane();
        controlPanel = setUpControlPanel();
        jCity = setUpCity(city);
        c.add(controlPanel);
        c.add(jCity);

        repaint();


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                boolean doReposition=false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_PLUS:
                        zoom+=0.5;
                        doReposition=true;
                        break;
                    case KeyEvent.VK_MINUS:
                        if(zoom>0.1) {
                            zoom -= 0.1;
                            doReposition=true;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        xOffset-=10;
                        doReposition=true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        xOffset+=10;
                        doReposition=true;
                        break;
                    case KeyEvent.VK_UP:
                        yOffset-=10;
                        doReposition=true;
                        break;
                    case KeyEvent.VK_DOWN:
                        yOffset+=10;
                        doReposition=true;
                        break;
                }
                if(doReposition){
                jCity.reposition();
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
        ret.setBounds(0, 0, 980, 720);
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
                new File("C:\\Users\\matte\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\sumo.json"));
        new Main(city);

    }
}
