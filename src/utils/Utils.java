package utils;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 25.12.2017
 */
public class Utils {
    public static double calcDistanceBetweenPoints(Point a, Point b){
        return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
    }
    public static double calcDegreesBetweenTwoPoint(Point from, Point to){
        return calcDegreesBetweenTwoPoint(from.x,from.y,to.x,to.y);
    }
    public static double calcDegreesBetweenTwoPoint(int fromX,int fromY,int toX,int toY){
        double angle = (double) Math.toDegrees(Math.atan2(toY - fromY, toX- fromX));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }


}
