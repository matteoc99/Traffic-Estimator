package tests;

import logic.PathUtils;
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

        Path path = PathUtils.getRandomPath(city);
        System.out.println(path.toString());

/*
        Vehicle test = new Vehicle(1000, 50, PathUtils.getRandomPath(city), 1, 1);

        while (true) {
            city.calcCity();
        }

  */
    }
}
