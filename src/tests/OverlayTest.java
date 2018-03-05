package tests;

import gui.city.Overlay.Overlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Maximilian Estfeller
 * @since 26.02.2017
 */
public class OverlayTest {

    static Overlay overlay;

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        overlay = new Overlay(frame, 46.6138900, 11.1524583, 19);

        frame.getContentPane().setLayout(new GridLayout());
        frame.setBounds(100, 50, 1200, 850);
        overlay.setPreferredSize(new Dimension(768, 768));
        overlay.setMinimumSize(new Dimension(256, 256));
        frame.getContentPane().add(overlay, Component.CENTER_ALIGNMENT);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
