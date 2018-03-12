package utils.math;

import static utils.math.Degrees.simplifyDeg;

/**
 * @author Maximilian Estfelller
 * @since 01.08.2017
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Function {

    /** Slope */
    private double k;

    /** Y-Offset */
    public double d;

    public Function(double k, double d) {
        setK(k);
        this.d = d;
    }

    public Function(double k) {
        this(k, 0);
    }

    public Function() {
        this(0);
    }

    public Function(FunctionData data) {
        this(data.getK(), data.getD());
    }

    /**
     * Method return the equivalent k-value for a given degree value
     * @param deg to calculate with
     * @return k
     */
    public static double calcSlopeByDeg(double deg) {
        // deg has to be in range [0, 90] or ]270, 360[
        deg = simplifyDeg(deg);

        if (deg == 0) return Values.K_MIN;
        if (deg == 90) return Values.K_MAX;

        return Math.tan(Math.toRadians(deg));

    }

    /**
     * Method is used for calculating the x-value for a given y-value
     * without creating a FunctionObject
     * @param y to calculate with
     * @param data storing data to calc with
     * @return x-value
     */
    public static double calcXRaw(double y, FunctionData data) {
        return (y-data.getD())/data.getK();
    }

    /**
     * checks for k not to be to close to 0, as this could cause problems
     * ex: calculating 1/k given k = 0;
     *
     * k has to be in range of [0.0001, 10000]
     *
     * @param k to set
     */
    public void setK(double k) {
        if (Math.abs(k) > Values.K_MIN)
            this.k = k;
        else if (k > 0)
            this.k = Values.K_MIN;
        else
            this.k = -Values.K_MIN;

        if (k > Values.K_MAX) this.k = Values.K_MAX;
    }

    public double getK() {
        return k;
    }

    /**
     * Calculates the collision of two functions
     * @param f to calculate with
     * @return Position of the collision
     */
    public Position collides(Function f) {
        return collidesForceBase(f);
    }

    /**
     * Calculates the collision of this Function with a circle
     * @param c to calculate with
     * @return Position[] of both collision points
     */
    public Position[] collides(Circle c) {
        return c.collides(this);
    }

    /**
     * Method can be used by LineFunctions, that DO NOT want to call their override-method
     * @param f to calculate with
     * @return Position of the collision
     */
    final Position collidesForceBase(Function f) {
        if (k - f.k == 0)
            return null;
        double x = ((f.d - d) / (k - f.k));
        return new Position(x, calcY(x));
    }

    /**
     * Returns the closest Position, of this Function, to the given Position
     * @param pos to calc with
     * @return closest Position
     */
    public Position closestTo(Position pos) {
        Function fV = new Function(-1/getK());
        fV.translateToHit(pos);

        return collides(fV);
    }

    /**
     * calculates result of the function given x
     * @param x to fill in the function
     * @return result y
     */
    public double calcY(double x) {
        return k*x+d;
    }

    /**
     * calculates result of the function given y
     * @param y to fill in the function
     * @return result x
     */
    public double calcX(double y) {
        return (y-d)/k;
    }

    /**
     * Translates the function on the y-Axis so that it collides with Position p
     * Changes var:d
     * @param p to collide with
     */
    public void translateToHit(Position p) {
        d = p.getY()-k*p.getX();
    }

    /**
     * Rotates the Function in Position(0, d), so that it collides with Position p
     * changes var:k
     * @param p to collide with
     */
    public void rotateToHit(Position p) {
        // TODO: 01.08.2017
    }

    public FunctionData getFunctionData() {
        return new FunctionData().setFunction(this);
    }

    @Override
    public String toString() {
        return "Function{" +
                "k=" + k +
                ", d=" + d +
                "}:\n" +
                "y=" + getK() + "*x+" + d;
    }
}
