package utils;

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

/**
 * @author Matteo Cosi
 * @since 25.12.2017
 */
public class Utils {

    /*
      Handshake for https files
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

    public static double calcDistanceBetweenPoints(Point a, Point b) {
        return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
    }

    public static double calcDegreesBetweenTwoPoint(Point from, Point to) {
        return calcDegreesBetweenTwoPoint(from.x, from.y, to.x, to.y);
    }

    public static double calcDegreesBetweenTwoPoint(int fromX, int fromY, int toX, int toY) {
        double angle = Math.toDegrees(Math.atan2(toY - fromY, toX - fromX));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Returns the image from OpenStreetMap on the given position and zoom
     * Creates a black image if it can't find the right one
     *
     * @param lat  latitude
     * @param lon  longitude
     * @param zoom 1-19
     * @return BufferedImage
     */
    public static BufferedImage getTileImage(double lat, double lon, int zoom) {
        try {
            return ImageIO.read(new URL(getTileLink(lat, lon, zoom)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(256, 256,
                BufferedImage.TYPE_INT_RGB);
    }

    private static String getTileLink(double lat, double lon, int zoom) {
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
        return "https://tile.openstreetmap.org/" + zoom + "/" + xTile + "/" + yTile + ".png";
    }
}
