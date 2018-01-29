package logic.city;

import logic.vehicles.Vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class City {

    private ArrayList<Node> nodes;

    private String name;

    public City(String name) {
        nodes = new ArrayList<>();
        this.name = name;
    }

    public static City createCityFromJson(File json){
        return new City("Bozen"); // TODO: 29.01.2018
    }


    public static Path doDijkstra(Node from, Node to, Vehicle vehicle) {
        return new Path(); // TODO: 15.12.2017
    }

    public static Path doRandomDijkstra(Vehicle vehicle) {
        return new Path(); // TODO: 15.12.2017
    }


    public void addNode(Node node){
        if(!contains(node)){
            nodes.add(node);
        }
    }

    public void removeNode(Node node){
        if(contains(node)){
            nodes.remove(node);
        }else{
            System.out.println("NO NODE TO REMOVE");
        }
    }

    public boolean contains(Node node){
        for (Node node1:nodes) {
            if(node.getId().equals(node1.getId())) {
                System.out.println("CITY NODE ALREADY ADDED");
                return true;
            }
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
