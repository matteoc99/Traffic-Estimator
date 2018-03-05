package gui.city.Overlay;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Vector;

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

    private OSMTileManager OSMTileManager;

    /**
     * More threads do not increase bandwidth, however a second thread might have some influence.
     * More than 3 threads are not recommended
     * @param OSMTileManager reference
     * @param threadCount amount of threads working
     */
    TileBuffer(OSMTileManager OSMTileManager, int threadCount) {
        this.OSMTileManager = OSMTileManager;
        for (int i = 0; i < threadCount; i++) {
            new Thread(this).start();
        }
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
            if (lB != null) {
                bufferLabel(lB);
            }
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
            BufferedImage bufferedImage = ImageIO.read(new URL(OSMTileManager.getTileLink(point, zoom)));
            OSMTileManager.addImage(bufferedImage, zoom, point);
            OSMTileManager.overlay.fillLabel(input.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bufferTile(Pair<Integer, Point> input) {
        try {
            int zoom = input.getKey();
            Point point = input.getValue();
            BufferedImage bufferedImage = ImageIO.read(new URL(OSMTileManager.getTileLink(point, zoom)));
            OSMTileManager.addImage(bufferedImage, zoom, point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void putLabelToBuffer(JLabel label, int zoom, Point point) {
        for (Pair<JLabel, Pair<Integer, Point>> jLabelPairPair : labelsToBuffer) {
            if (jLabelPairPair.getKey().equals(label)) {
                labelsToBuffer.removeElement(jLabelPairPair);
                break;
            }
        }

        labelsToBuffer.add(new Pair<>(label, new Pair<>(zoom, point)));
        notifyAll();
    }

    public synchronized void putTileToBuffer(int zoom, Point point) {
        // Job already listed
        for (Pair<Integer, Point> pair : tilesToBuffer) {
            if (pair.getValue().equals(point) && pair.getKey() == zoom)
                return;
        }

        // Tile already buffered
        if (OSMTileManager.getTileImage(point, zoom) != null)
            return;

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

        Pair<JLabel, Pair<Integer, Point>> ret = labelsToBuffer.lastElement();
        labelsToBuffer.removeElement(ret);
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
