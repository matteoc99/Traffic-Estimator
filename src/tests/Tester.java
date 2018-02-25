package tests;

import logic.city.City;
import logic.city.Path;
import utils.PathUtils;

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
