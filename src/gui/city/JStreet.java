package gui.city;

import logic.city.Street;

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

    private JPanel parent;


    public JStreet(Street street, JCity parent) {
        setLayout(null);
        this.street = street;
        Rectangle bounds = street.getBounds();
        setBounds(bounds.x,bounds.y,bounds.width,bounds.height); // bound verdoppelung
        this.parent = parent;
        parent.add(this);
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
        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.setColor(Color.BLACK);

        if (street.getDegrees()>=0&&street.getDegrees()<90){
            g2d.drawLine(0,0, getWidth(), getHeight());
        }else if (street.getDegrees()>=90&&street.getDegrees()<180){
            g2d.drawLine(0,0, getWidth(), getHeight());
        }else if (street.getDegrees()>=180&&street.getDegrees()<270){
            g2d.drawLine(0,getHeight(), getWidth(),0);
        }else{
            g2d.drawLine(0,getHeight(), getWidth(), 0);
        }

    }

    @Override
    public JPanel getParent() {
        return parent;
    }

    public void setParent(JPanel parent) {
        this.parent = parent;
    }


}
