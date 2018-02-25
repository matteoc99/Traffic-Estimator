package utils;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 25.12.2017
 */
public class Utils {
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

    public static String getTileLink(final double lat, final double lon, final int zoom) {
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
