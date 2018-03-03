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

    static private int xOff, yOff = 0;

    public static void main(String[] args) {
        overlay = new Overlay(46.6138900, 11.1524583, 19) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.translate(xOff, yOff);
            }
        };

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new GridLayout());
        frame.setBounds(100, 50, 1200, 850);
        overlay.setPreferredSize(new Dimension(768, 768));
        overlay.setMinimumSize(new Dimension(256, 256));
        frame.getContentPane().add(overlay, Component.CENTER_ALIGNMENT);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'd':
                        overlay.moveHorizontal(1);
                        break;
                    case 'a':
                        overlay.moveHorizontal(-1);
                        break;
                    case 'w':
                        overlay.moveVertical(-1);
                        break;
                    case 's':
                        overlay.moveVertical(1);
                        break;
                    case 'e':
                        overlay.increaseCurrentZoom();
                        break;
                    case 'q':
                        overlay.decreaseCurrentZoom();
                        break;
                }
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
