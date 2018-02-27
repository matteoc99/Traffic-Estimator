package gui.city;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * @author Maximilian Estfeller
 * @since 26.02.2017
 */
public class TileManager {

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

    public ArrayList<BufferedImage> getTiles(int x, int y, int width, int height, int zoomLevel) {
        return null;
    }

    /**
     * Returns the image from OpenStreetMap on the given position and zoom
     * Creates a black image if it can't find the right one
     *
     * @param point position (index-like)
     * @param zoom 1-19
     * @return BufferedImage
     */
    public static BufferedImage getTileImage(Point point, int zoom) {
        /*
        if (true) { // testing
            BufferedImage bufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawString(point.toString(), 10, 128);
            return bufferedImage;
        }
        */
        try {
            return ImageIO.read(new URL(getTileLink(point, zoom)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(256, 256,
                BufferedImage.TYPE_INT_RGB);
    }

    public static String getTileLink(Point point, int zoom) {
        return "https://tile.openstreetmap.org/" + zoom + "/" + point.x + "/" + point.y + ".png";
    }

    public static Point getTilePoint(double lat, double lon, int zoom) {
        int xTile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
        int yTile = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1 << zoom));
        if (xTile < 0)
            xTile = 0;
        if (xTile >= (1 << zoom))
            xTile = ((1 << zoom) - 1);
        if (yTile < 0)
            yTile = 0;
        if (yTile >= (1 << zoom))
            yTile = ((1 << zoom) - 1);

        return new Point(xTile, yTile);
    }
}
