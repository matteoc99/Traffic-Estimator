package gui.city.Overlay;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maximilian Estfeller
 * @since 26.02.2017
 */
public class OSMTileManager implements TileManager {

    // TODO: 03.03.2018 Theoretically infinite RAM-usage;

    // Images are stored in Maps, keyOrder: zoom, x, y
    private Map<Integer, Map<Integer, Map<Integer, BufferedImage>>> images = new HashMap<>();

    private TileBuffer tileBuffer = new TileBuffer(this, 3);

    public Overlay overlay;

    /*
    Handshake for https files
    https://stackoverflow.com/a/18576728
    */
    static {
        final TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null; // Not relevant.
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing. Just allow them all.
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing. Just allow them all.
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCertificates, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public OSMTileManager(Overlay overlay) {
        this.overlay = overlay;
    }

    public String getTileLink(Point point, int zoom) {
        return "https://tile.openstreetmap.org/" + zoom + "/" + point.x + "/" + point.y + ".png";
    }

    public synchronized void addImage(BufferedImage image, int zoom, Point point) {
        images.putIfAbsent(zoom, new HashMap<>());
        images.get(zoom).putIfAbsent(point.x, new HashMap<>());
        images.get(zoom).get(point.x).putIfAbsent(point.y, image);
    }

    /**
     * Returns the image from OpenStreetMap on the given position and zoom
     *
     * @param point position (index-like)
     * @param zoom  1-19
     * @return BufferedImage or null if it isn't stored yet
     */
    @Override
    public synchronized BufferedImage getTileImage(Point point, int zoom) {
        try {
            return images.get(zoom).get(point.x).get(point.y);
        } catch (Exception ignored) {
        }

        return null;
    }

    @Override
    public void buffer(int zoom, Point point) {
        tileBuffer.putTileToBuffer(zoom, point);
    }

    @Override
    public void buffer(int zoom, Point point, JLabel jLabel) {
        tileBuffer.putLabelToBuffer(jLabel, zoom, point);
    }

    @Override
    public void clearTilesToBuffer() {
        tileBuffer.clearTilesToBuffer();
    }

    @Override
    public void clearLabelsToBuffer() {
        tileBuffer.clearLabelsToBuffer();
    }
}
