package logic.city;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class StreetComponent {

    private int xFrom;
    private int xTo;
    private int yFrom;
    private int yTo;

    public StreetComponent() {
    }

    public StreetComponent(int xFrom, int xTo, int yFrom, int yTo) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
    }

    public int getLength(){
        return 0; // TODO: 15.12.2017 pythagoras 
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
