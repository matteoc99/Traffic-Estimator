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

        overlay = new Overlay(frame, 11.1325323, 46.6140000, 15);

        frame.getContentPane().setLayout(new GridLayout());
        frame.setBounds(100, 50, 1200, 850);
        frame.getContentPane().add(overlay, Component.CENTER_ALIGNMENT);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
