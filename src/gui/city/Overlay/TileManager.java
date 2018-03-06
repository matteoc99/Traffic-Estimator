package gui.city.Overlay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public interface TileManager {

    void buffer(int zoom, Point point);

    void buffer(int zoom, Point point, JLabel jLabel);

    void clearTilesToBuffer();

    void clearLabelsToBuffer();

    BufferedImage getTileImage(Point point, int zoom);
}
