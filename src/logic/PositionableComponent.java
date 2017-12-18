package logic;

import java.awt.*;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class PositionableComponent {

    private int x;
    private int y;
    private int width;
    private int height;

    public PositionableComponent() {

    }

    public PositionableComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setSize(Dimension size) {
        setWidth(size.width);
        setHeight(size.height);
    }

    public void setLocation(Point location) {
        setX(location.x);
        setY(location.y);
    }

    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

    public Point getLocation() {
        return new Point(getX(), getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
