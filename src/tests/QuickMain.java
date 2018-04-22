package tests;

import gui.city.JCity;
import gui.city.Overlay.CityOverlay;
import utils.math.Position;

public class QuickMain {
    public static void main(String[] args) {
        System.out.println(JCity.getXYByOsmTileXY(50.342));
        System.out.println(JCity.getOSMTileXYByXY(12887));
    }
}
