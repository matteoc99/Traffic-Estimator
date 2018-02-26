package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 06.08.2017
 */
public class CustomSlider extends JSlider {

    public CustomSlider(int orientation, int min, int max, int value) {
        super(orientation, min, max, value);
        setPaintLabels(true);
        setBackground(Color.white);
        setPaintTicks(true);
        JSliderUI ui = new JSliderUI(this);
        setUI(ui);
    }
}
