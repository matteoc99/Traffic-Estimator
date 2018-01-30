package gui.city;

import logic.city.Congestion;
import logic.city.Lane;
import logic.city.Street;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JCongestion extends JComponent{

    /**
     * Congestion Reference
     */
    Congestion congestion;


    public JCongestion(Congestion congestion) {
        setLayout(null);
        this.congestion = congestion;
    }

    public Congestion getCongestion() {
        return congestion;
    }

    public void setCongestion(Congestion congestion) {
        this.congestion = congestion;
    }
}
