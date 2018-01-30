package tests;

import logic.city.City;
import logic.city.Node;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 24.12.2017
 */
public class Tester {
    public static void main(String[] args) {
        City city = new City("Bozen");
        for (int i = 0; i < 3; i++) {
            city.addNode(new Node(city,new Point(0,0),Math.random(),""+i));
        }
        for (int i = 0; i < city.getNodes().size(); i++) {
            System.out.println(city.getNodes().get(i).getId()+":"+city.getNodes().get(i).getFame());
        }
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''''''");
        int[] counter = new int[city.getNodes().size()];
        for (int i = 0; i < 10000; i++) {
            Node node= city.getRandomNode();
            counter[Integer.parseInt(node.getId())]++;
        }
        for (int i = 0; i < counter.length; i++) {
            System.out.println(i+"::"+counter[i]);
        }
    }
}
