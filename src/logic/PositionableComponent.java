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

    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    public void setBounds(Rectangle rectangle){
        setX(rectangle.x);
        setY(rectangle.y);
        setWidth(rectangle.width);
        setHeight(rectangle.height);
    }


    public void setBounds(int x, int y, int width, int height){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void setSize(Dimension size) {
        setWidth(size.width);
        setHeight(size.height);
    }
    public void setSize(int width,int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setLocation(Point location) {
        setX(location.x);
        setY(location.y);
    }
    public void setLocation(int x,int y) {
        setX(x);
        setY(y);
    }

    /**
     * Moves the object relative to the current point
     * @param dx move on the x coordinate
     * @param dy move on the y coordinate
     */
    public void translate(int dx,int dy){
        setLocation(this.x+dx,this.y+dy);
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
