package gui.city;

import logic.city.Lane;
import logic.city.Street;
import logic.vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JStreet extends JPanel {

    /**
     * Reference
     */
    Street street;


    public JStreet(Street street) {
        setLayout(null);
        this.street = street;
        setBounds(street.getBounds());
        setBackground(Color.ORANGE);
        repaint();
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //street
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.cyan);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRect(0, 0, getWidth(), getHeight());
    }
}
