package tests;

import gui.city.Overlay;
import gui.city.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author ${User}
 * @since ${Date}
 */
public class OverlayTest {

    static Overlay overlay;
    public static void main(String[] args) {
        overlay = new Overlay(46.6138900, 11.1524583, 19);
        System.out.println(overlay.test());

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new GridLayout());
        frame.setBounds(100, 50, 768, 768);
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
