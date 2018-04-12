package gui.city.Overlay;

import gui.city.CityMap;
import utils.math.Position;

import javax.swing.*;

public class CityOverlay extends Overlay {

    private CityMap cityMap;

    public CityOverlay(JFrame jFrame, double initialLon, double initialLat, int zoom, CityMap cityMap) {
        super(jFrame, initialLon, initialLat, zoom);
        this.cityMap = cityMap;
    }

    public static Position transformPositionByZoom(Position pos, int fromZoom, int toZoom) {
        Position ret = new Position(0, 0);
        int power = fromZoom - toZoom;
        if (power == 0)
            return pos.clone();
        ret.setX(pos.getX() / Math.pow(2, power));
        ret.setY(pos.getY() / Math.pow(2, power));
        return ret;
    }
}
