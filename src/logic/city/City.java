package logic.city;

import logic.vehicles.Vehicle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class City {

    public static void main(String[] args) {
        createCityFromJson(new File("C:\\Users\\User\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\testcity.json"));
    }

    private ArrayList<Node> nodes;

    private String name;

    public City() {
        nodes = new ArrayList<>();
    }

    public City(String name) {
        this();
        this.name = name;
    }

    public static City createCityFromJson(File jsonFile) {
        City city = new City("Blogland");
        JSONTokener root;
        try {
            root = new JSONTokener(new FileReader(jsonFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        JSONObject topNode = new JSONObject(root);

        JSONArray nodes = topNode.getJSONArray("nodes");
        for (int i = 0; i < nodes.length(); i++) {
            // id(String), x(number), y(number), fame(number), type(String)
            JSONObject nodeEntry = nodes.getJSONObject(i);
            String id = nodeEntry.getString("id");
            int x = nodeEntry.getInt("x");
            int y = nodeEntry.getInt("y");
            double fame = nodeEntry.getDouble("fame");
            String type = nodeEntry.getString("type");

            createNodeByClassName(type, city, new Point(x, y), fame, id);
        }

        JSONArray streets = topNode.getJSONArray("streets");
        for (int i = 0; i < streets.length(); i++) {
            JSONObject streetEntry = streets.getJSONObject(i);
            String id = streetEntry.getString("id");
            String from = streetEntry.getString("from");
            String to = streetEntry.getString("to");
            double maxSpeed = streetEntry.getDouble("maxSpeed");
            double prominence = streetEntry.getDouble("prominence");

            Node fromNode = city.getNodeById(from);
            Node toNode = city.getNodeById(to);

            new Street(id, city, fromNode, toNode, maxSpeed, prominence);
        }

        JSONArray lanes = topNode.getJSONArray("lanes");
        for (int i = 0; i < lanes.length(); i++) {
            JSONObject laneEntry = lanes.getJSONObject(i);
            String id = laneEntry.getString("id");
            String parentStreet = laneEntry.getString("parent");
            int index = laneEntry.getInt("index");
            boolean reversed = laneEntry.getBoolean("reversed");

            Street parent = city.getStreetById(parentStreet);

            new Lane(id, parent, reversed, index);
        }

        return city;
    }

    private static Node createNodeByClassName(String className,
                                              City city,
                                              Point point,
                                              double fame,
                                              String id) {
        Node ret = null;
        switch (className) {
            case "Connection":
                ret = new Connection(city, point, fame, id);
                break;
            case "MultiConnection":
                ret = new MultiConnection(city, point, fame, id);
                break;
            case "DeadEnd":
                ret = new DeadEnd(city, point, fame, id);
                break;
        }

        return ret;
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
        }else{
            throw new RuntimeException("CITY NODE ALREADY ADDED");
        }
    }

    public void removeNode(Node node) {
        if (contains(node)) {
            nodes.remove(node);
        } else {
            throw new RuntimeException("NO NODE TO REMOVE");
        }
    }

    public boolean contains(Node node) {
        for (Node node1 : nodes) {
            if (node.getId().equals(node1.getId())) {
                return true;
            }
        }
        return false;
    }

    public Node getNodeById(String id) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getId().equals(id))
                return nodes.get(i);
        }
        return null;
    }


    public Street getStreetById(String id) {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getStreets().size(); j++) {
                if (nodes.get(i).getStreets().get(j).getId().equals(id)) {
                    return nodes.get(i).getStreets().get(j);
                }
            }
        }
        return null;
    }

    public Lane getLaneById(String id) {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getStreets().size(); j++) {
                for (int k = 0; k <nodes.get(i).getStreets().get(j).getBackwardLanes().size(); k++) {
                    if (nodes.get(i).getStreets().get(j).getBackwardLanes().get(k).getId().equals(id))
                        return nodes.get(i).getStreets().get(j).getBackwardLanes().get(k);
                }
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getStreets().size(); j++) {
                for (int k = 0; k <nodes.get(i).getStreets().get(j).getForwardLanes().size(); k++) {
                    if (nodes.get(i).getStreets().get(j).getForwardLanes().get(k).getId().equals(id))
                        return nodes.get(i).getStreets().get(j).getForwardLanes().get(k);
                }
            }
        }
        return null;
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
