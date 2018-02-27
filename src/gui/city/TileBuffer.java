package gui.city;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Vector;

import static gui.city.TileManager.*;

/**
 * @author Maximilian Estfeller
 * @since 27.02.2018
 */
public class TileBuffer implements Runnable {

    /**
     * JLabels have higher priority and get repainted when tile has been buffered
     */
    private Vector<Pair<JLabel, Pair<Integer, Point>>> labelsToBuffer = new Vector<>();

    private Vector<Pair<Integer, Point>> tilesToBuffer = new Vector<>();

    private TileManager tileManager;

    TileBuffer(TileManager tileManager) {
        this.tileManager = tileManager;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (labelsToBuffer.size() == 0 && tilesToBuffer.size() == 0)
                    try {
                        wait();
                    } catch (InterruptedException ignored) {}
            }
            Pair<JLabel, Pair<Integer, Point>> lB = getNextLabelToBuffer();
            if (lB != null)
                bufferLabel(lB);
            else {
                Pair<Integer, Point> tB = getNextTileToBuffer();
                if (tB != null)
                    bufferTile(tB);
            }
        }
    }

    private void bufferLabel(Pair<JLabel, Pair<Integer, Point>> input) {
        try {
            int zoom = input.getValue().getKey();
            Point point = input.getValue().getValue();
            BufferedImage bufferedImage = ImageIO.read(new URL(tileManager.getTileLink(point, zoom)));
            // FIXME: 27.02.2018 This shouldn't set the image of the jlabel to the new one;
            // FIXME: 27.02.2018 Better tell the jlabel to retry getting the right image
            tileManager.addImage(bufferedImage, zoom, point);
            tileManager.overlay.fillLabel(input.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bufferTile(Pair<Integer, Point> input) {
        try {
            int zoom = input.getKey();
            Point point = input.getValue();
            BufferedImage bufferedImage = ImageIO.read(new URL(tileManager.getTileLink(point, zoom)));
            tileManager.addImage(bufferedImage, zoom, point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void putLabelToBuffer(JLabel label, int zoom, Point point) {
        // TODO: 27.02.2018 Maybe remove old entry when a new one with the same label gets in
        labelsToBuffer.add(new Pair<>(label, new Pair<>(zoom, point)));
        notifyAll();
    }

    public synchronized void putTileToBuffer(int zoom, Point point) {
        // TODO: 27.02.2018 Remove old entry when a new one, with the same Tile comes in
        tilesToBuffer.add(new Pair<>(zoom, point));
        notifyAll();
    }

    public synchronized void clearLabelsToBuffer() {
        labelsToBuffer.clear();
    }

    public synchronized void clearTilesToBuffer() {
        tilesToBuffer.clear();
    }

    private synchronized Pair<JLabel, Pair<Integer, Point>> getNextLabelToBuffer() {
        if (labelsToBuffer.size() == 0)
            return null;

        Pair<JLabel, Pair<Integer, Point>> ret = labelsToBuffer.get(0);
        labelsToBuffer.remove(0);
        return ret;
    }

    private synchronized Pair<Integer, Point> getNextTileToBuffer() {
        if (tilesToBuffer.size() == 0)
            return null;

        Pair<Integer, Point> ret = tilesToBuffer.get(0);
        tilesToBuffer.remove(0);
        return ret;
    }
}
