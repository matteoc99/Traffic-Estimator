package utils.math;

/**
 * @author Maximilian Estfelller
 * @since 01.08.2017
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Position implements Cloneable{

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the distance from this Position to the given Position
     *
     * @param pos Position to calculate with
     * @return the distance
     */
    public double distanceTo(Position pos) {
        double pX = pos.getX() - getX();
        double pY = pos.getY() - getY();

        return Math.sqrt(pX * pX + pY * pY);
    }

    /**
     * Translates this point in a given direction for a given distance
     * @param k direction
     * @param distance distance
     */
    public void translateOnLine(double k, double distance) {
        double normDistance = Math.sqrt(Math.pow(k, 2) + 1);
        double factor = distance/normDistance;

        this.x+=factor;
        this.y+=factor*k;
    }

    public void translateTowards(double deg, double distance) {
        if (deg > 90 && deg <= 270) distance=-distance;
        translateOnLine(Function.calcSlopeByDeg(deg), distance);
    }

    public double getSlopeTo(Position pos) {
        if (this.y == pos.y) return Values.K_MIN;
        if (this.x == pos.x) return Values.K_MAX;

        return (this.y - pos.y) / (this.x - pos.x);
    }

    @Override
    public Position clone() {
        try {
            return (Position)super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
