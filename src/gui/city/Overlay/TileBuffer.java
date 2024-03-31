package gui.city.Overlay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
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

    private OSMTileManager osmTileManager;

    /**
     * More threads do not increase bandwidth, however a second thread might have some influence.
     * More than 3 threads are not recommended
     *
     * @param osmTileManager reference
     * @param threadCount    amount of threads working
     */
    TileBuffer(OSMTileManager osmTileManager, int threadCount) {
        this.osmTileManager = osmTileManager;
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
            } else {
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
            URL url = new URL(osmTileManager.getTileLink(point, zoom));
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty(
                    "User-Agent",
                    "MCMETE/1.0");
            BufferedImage bufferedImage = ImageIO.read(connection.getInputStream());
            osmTileManager.addImage(bufferedImage, zoom, point);
            // TODO: 06.03.2018 Filling a single label does not work, instead all labels need to be repainted
            //osmTileManager.overlay.fillLabel(input.getKey());
            osmTileManager.overlay.fillLabels();
        } catch (Exception ignored) {
        }
    }

    private void bufferTile(Pair<Integer, Point> input) {
        try {
            int zoom = input.getKey();
            Point point = input.getValue();
            URL url = new URL(osmTileManager.getTileLink(point, zoom));
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty(
                    "User-Agent",
                    "MCMETE/1.0");
            BufferedImage bufferedImage = ImageIO.read(connection.getInputStream());
            osmTileManager.addImage(bufferedImage, zoom, point);
        } catch (Exception ignored) {
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
        if (osmTileManager.getTileImage(point, zoom) != null)
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

    /**
     * Quick and dirty fix to replace javafx Pair
     * ChatGPT code
     */
    private class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            if (!key.equals(pair.key)) return false;
            return value.equals(pair.value);
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }
}
