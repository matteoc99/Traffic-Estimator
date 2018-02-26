package tests;

import gui.city.Overlay;
import gui.city.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author ${User}
 * @since ${Date}
 */
public class OverlayTest {
    public static void main(String[] args) {
        System.out.println(TileManager.getTileLink(TileManager.getTilePoint(
                46.6138900, 11.1524583, 14), 14));
        System.out.println(TileManager.getTileLink(TileManager.getTilePoint(
                46.6138900, 11.1524583, 15), 15));
        System.out.println(TileManager.getTileLink(TileManager.getTilePoint(
                46.6138900, 11.1524583, 16), 16));
        System.out.println(TileManager.getTileLink(TileManager.getTilePoint(
                46.6138900, 11.1524583, 17), 17));
        System.out.println(TileManager.getTileLink(TileManager.getTilePoint(
                46.6138900, 11.1524583, 18), 18));
        System.out.println(TileManager.getTileLink(TileManager.getTilePoint(
                46.6138900, 11.1524583, 19), 19));

        Overlay overlay = new Overlay(46.6138900, 11.1524583, 19);
        System.out.println(overlay.test());

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'd':
                        overlay.moveHorizontal(1);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case 'a':
                        overlay.moveHorizontal(-1);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case 'w':
                        overlay.moveVertical(1);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case 's':
                        overlay.moveVertical(-1);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case 'e':
                        overlay.increaseCurrentZoom();
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case 'q':
                        overlay.decreaseCurrentZoom();
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new JLabel(new ImageIcon(overlay.testGetTile())));
                        frame.revalidate();
                        frame.repaint();
                        break;
                }
            }
        });
    }
}
