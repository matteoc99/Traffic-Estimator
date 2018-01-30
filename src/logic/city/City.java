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

    public City() {
        nodes = new ArrayList<>();
    }
    public City(String name) {
        this();
        this.name = name;
    }

    public static City createCityFromJson(File json) {
        return new City("Bozen"); // TODO: 29.01.2018
    }


    private Path doDijkstra(Node from, Node to) {
        return new Path(); // TODO: 15.12.2017
    }

    private Path doAStern(Node from, Node to) {
        return new Path(); // TODO: 15.12.2017
    }

    public Path doRandomPathFinding(Vehicle vehicle) {
        //get two random nodes based on fame
        Node from = getRandomNode();
        Node to = getRandomNode();
        while (to.equals(from)) {
            to = getRandomNode();
        }
        return doAStern(from, to);
    }

    public Node getRandomNode() {
        Node ret = null;
        ArrayList<Double> fames = new ArrayList<>(); // TODO: 30.01.2018 nicht immer neu rechnen
        double sum = 0;
        for (int i = 0; i < nodes.size(); i++) {
            fames.add(nodes.get(i).getFame());
            sum += fames.get(i);
        }
        double random = Math.random() * sum;
        sum = 0;
        for (int i = 0; i < fames.size(); i++) {
            sum += fames.get(i);
            if (!(sum <= random)) {
                return nodes.get(i);
            }
        }
        return ret;
    }


    public void addNode(Node node) {
        if (!contains(node)) {
            nodes.add(node);
        }
    }

    public void removeNode(Node node) {
        if (contains(node)) {
            nodes.remove(node);
        } else {
            System.out.println("NO NODE TO REMOVE");
        }
    }

    public boolean contains(Node node) {
        for (Node node1 : nodes) {
            if (node.getId().equals(node1.getId())) {
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
