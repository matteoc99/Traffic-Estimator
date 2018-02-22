package logic.city;

import utils.Utils;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetComponent {
    /**
     * bounds of the lane
     */
    private int xFrom;
    private int xTo;
    private int yFrom;
    private int yTo;
    /**
     * the degrees of the lane
     */
    private double degrees = -1;

    /**
     * the bounds of this street component
     */
    private Rectangle bounds;


    /**
     * automatically sets xFrom xTo yFrom yTo and automatically calculates the degrees
     */
    public StreetComponent(int xFrom, int xTo, int yFrom, int yTo) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        calcDegrees();
    }

    /**
     * Calculates the degrees from the "From x,y" to the "To x,y"
     */
    protected void calcDegrees() {
        this.degrees = Utils.calcDegreesBetweenTwoPoint(getxFrom(), getyFrom(), getxTo(), getyTo());
    }


    /**
     * Distance formula: sqrt{(x_2 -x_1)^2 + (y_2- y_1)^2}
     *
     * @return the legth of the line
     */
    public double getLength() {

        return Utils.calcDistanceBetweenPoints(new Point(xFrom, yFrom), new Point(xTo, yTo));
    }

    public double getDegrees() {
        return degrees;
    }

    /**
     * refreshes the degrees in case that the x,y changes
     */
    public void refreshDegrees() {
        calcDegrees();
    }

    public int getxTo() {
        return xTo;
    }

    public void setxTo(int xTo) {
        this.xTo = xTo;
    }

    public int getyFrom() {
        return yFrom;
    }

    public void setyFrom(int yFrom) {
        this.yFrom = yFrom;
    }

    public int getyTo() {
        return yTo;
    }

    public void setyTo(int yTo) {
        this.yTo = yTo;
    }

    public int getxFrom() {
        return xFrom;
    }

    public void setxFrom(int xFrom) {
        this.xFrom = xFrom;
    }


    /**
     * calculates the bounds of the street component
     *
     * @return the bounds
     */
    public Rectangle getBounds() {
        int x = Integer.min(xTo, xFrom);
        int y = Integer.min(yTo, yFrom);
        int width = Integer.max(xTo, xFrom) - Integer.min(xTo, xFrom);
        int height = Integer.max(yTo, yFrom) - Integer.min(yTo, yFrom);
        if (height == 0)
            height = 1;
        if (width == 0)
            width = 1;
        if (bounds == null)
            return bounds = new Rectangle(x, y, width, height);
        else
            return bounds;
    }
}
