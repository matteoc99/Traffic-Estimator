package logic.city;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetComponent {

    private int xFrom;
    private int xTo;
    private int yFrom;
    private int yTo;
    private double degrees=-1;

    public StreetComponent() {
    }

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
    private void calcDegrees() {
        double angle = (double) Math.toDegrees(Math.atan2(yTo - yFrom, xTo - xFrom));

        if(angle < 0){
            angle += 360;
        }
        this.degrees = angle;
        // TODO: 24.12.2017 Test
    }

    /**
     * Distance formula: sqrt{(x_2 -x_1)^2 + (y_2- y_1)^2}
     * @return the legth of the line
     */
    public double getLength() {
        return Math.abs(
                Math.sqrt(
                        Math.pow(xTo-xFrom,2)+
                        Math.pow(yTo-yFrom,2)
                    )
                );
        // TODO: 15.12.2017 test
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


}
