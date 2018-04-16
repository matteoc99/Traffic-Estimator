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
}
