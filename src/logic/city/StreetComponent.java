package logic.city;

import utils.Utils;
import utils.math.Position;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetComponent {
    /**
     * bounds of the lane
     */
    private double xFrom;
    private double xTo;
    private double yFrom;
    private double yTo;


    /**
     * automatically sets xFrom xTo yFrom yTo and automatically calculates the degrees
     */
    public StreetComponent(double xFrom, double xTo, double yFrom, double yTo) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
    }


    /**
     * Distance formula: sqrt{(x_2 -x_1)^2 + (y_2- y_1)^2}
     *
     * @return the legth of the line
     */
    public double getLength() {
        return new Position(xFrom, yFrom).distanceTo(new Position(xTo, yTo));
    }

    public double getxTo() {
        return xTo;
    }

    public void setxTo(double xTo) {
        this.xTo = xTo;
    }

    public double getyFrom() {
        return yFrom;
    }

    public void setyFrom(double yFrom) {
        this.yFrom = yFrom;
    }

    public double getyTo() {
        return yTo;
    }

    public void setyTo(double yTo) {
        this.yTo = yTo;
    }

    public double getxFrom() {
        return xFrom;
    }

    public void setxFrom(double xFrom) {
        this.xFrom = xFrom;
    }
}
