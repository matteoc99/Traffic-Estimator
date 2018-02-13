package tests;

import logic.city.City;
import logic.city.Node;
import logic.city.Path;
import logic.vehicles.Vehicle;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 24.12.2017
 */
public class Tester {
    public static void main(String[] args) {
        City city = City.createCityFromJson(new java.io.File("C:\\Users\\matte\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\lana.json"));

        Vehicle test = new Vehicle(1000, 50, city.getRandomPath(0), 1, 1);

        while (true) {
            city.calcCity();
        }
    }
}
