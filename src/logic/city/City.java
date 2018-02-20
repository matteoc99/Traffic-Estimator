package logic.city;

import logic.vehicles.Vehicle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class City {

    public static void main(String[] args) {
        createCityFromJson(new File(System.getProperty("user.dir") + "\\src\\parsing\\sumo.json"));
    }

    private ArrayList<Node> nodes;
    private boolean nodesSorted = false;

    private ArrayList<Street> streets;
    private boolean streetsSorted = false;

    private ArrayList<Lane> lanes;
    private boolean lanesSorted = false;

    private String name;

    private City() {
        nodes = new ArrayList<>();
        streets = new ArrayList<>();
        lanes = new ArrayList<>();
    }

    public City(String name) {
        this();
        this.name = name;
    }

    public static City createCityFromJson(File jsonFile) {

        City city = new City(jsonFile.getName().replace(".json", ""));
        JSONTokener root;
        try {
            root = new JSONTokener(new FileReader(jsonFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        JSONObject topNode = new JSONObject(root);

        System.out.println("City:" + new Timestamp(System.currentTimeMillis()) + " Creating Nodes...");
        JSONArray jNodes = topNode.getJSONArray("nodes");
        for (int i = 0; i < jNodes.length(); i++) {
            // id(String), x(number), y(number), fame(number), type(String)
            JSONObject nodeEntry = jNodes.getJSONObject(i);
            String id = nodeEntry.getString("id");
            int x = nodeEntry.getInt("x");
            int y = nodeEntry.getInt("y");
            double fame = nodeEntry.getDouble("fame");
            String type = nodeEntry.getString("type");

            createNodeByClassName(type, city, new Point(x, y), fame, id);
        }
        city.sortNodes();

        System.out.println("City:" + new Timestamp(System.currentTimeMillis()) + " Creating Streets...");
        JSONArray jStreets = topNode.getJSONArray("streets");
        for (int i = 0; i < jStreets.length(); i++) {
            JSONObject streetEntry = jStreets.getJSONObject(i);
            String id = streetEntry.getString("id");
            String from = streetEntry.getString("from");
            String to = streetEntry.getString("to");
            double maxSpeed = streetEntry.getDouble("maxSpeed");
            double prominence = streetEntry.getDouble("prominence");

            Node fromNode = city.getNodeById(from);
            Node toNode = city.getNodeById(to);


            new Street(id, city, fromNode, toNode, maxSpeed, prominence);
        }
        city.sortStreets();

        System.out.println("City:" + new Timestamp(System.currentTimeMillis()) + " Creating Lanes...");
        JSONArray jLanes = topNode.getJSONArray("lanes");
        for (int i = 0; i < jLanes.length(); i++) {
            JSONObject laneEntry = jLanes.getJSONObject(i);
            String id = laneEntry.getString("id");
            String parentStreet = laneEntry.getString("parent");
            int index = laneEntry.getInt("index");
            boolean reversed = laneEntry.getBoolean("reversed");

            Street parent = city.getStreetById(parentStreet);

            new Lane(id, parent, reversed, index);
        }
        city.sortLanes();

        // delete lonely nodes
        // TODO: 18.02.2018 deactivated, as there shouldn't be any nodes without a single street
        //city.nodes.removeIf(node -> !node.getStreetIterator().hasNext());

        return city;
    }

    private static void createNodeByClassName(String className,
                                              City city,
                                              Point point,
                                              double fame,
                                              String id) {
        switch (className) {
            case "Connection":
                new Connection(city, point, fame, id);
                break;
            case "MultiConnection":
                new MultiConnection(city, point, fame, id);
                break;
            case "DeadEnd":
                new DeadEnd(city, point, fame, id);
                break;
        }
    }

    private Path doDijkstra(Node from, Node to) {
        return new Path(); // TODO: 15.12.2017
    }

    public Path doAStern(Node from, Node to, Vehicle vehicle) {
        Path ret = new Path();
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();

        for (Node node : nodes) {
            node.startPathCalculation(to);
        }

        from.setWalkedCost(0);
        open.add(from);


        int counter = 0;
        while (!open.isEmpty() && counter < 10000) {
            double lowestCost = Double.MAX_VALUE;
            int index = 0;
            //get lowest cost
            for (int i = 0; i < open.size(); i++) {
                if (open.get(i).getFieldCost() < lowestCost) {
                    lowestCost = open.get(i).getFieldCost();
                    index = i;
                }
            }
            Node current = open.get(index);
            open.remove(current);
            closed.add(current);
            if (current.equals(to)) {
                break;
            }

            Iterator<Street> streets = current.getStreetIterator();
            while (streets.hasNext()) {
                Street currentStreet = streets.next();

                Node neighbour;
                boolean isReachable = true;
                if (currentStreet.getFrom().equals(current)) {
                    neighbour = currentStreet.getTo();
                    if (currentStreet.getForwardLanesSize() == 0) {
                        isReachable = false;
                    }
                } else {
                    neighbour = currentStreet.getFrom();
                    if (currentStreet.getBackwardLanesSize() == 0) {
                        isReachable = false;
                    }
                }
                if (closed.contains(neighbour))
                    isReachable = false;
                if (vehicle == null)
                    vehicle = new Vehicle(50);
                if (isReachable) {
                    if (!(open.contains(neighbour)) ||
                            current.getWalkedCost() + currentStreet.getTotalCost(vehicle.getMaxSpeed()) < neighbour.getWalkedCost()) {
                        neighbour.setWalkedCost(current.getWalkedCost() + currentStreet.getTotalCost(vehicle.getMaxSpeed()));
                        neighbour.setPreviousNode(current);
                        if (!(open.contains(neighbour))) {
                            open.add(neighbour);
                        }
                    }
                }
            }
            counter++;
        }

        Node prevNode = to;
        while (prevNode != null) {
            ret.addNodeIdAtIndex(prevNode.getId(), 0);
            prevNode = prevNode.getPreviousNode();
        }
        return ret;
    }

    public Path getRandomPath(int breaking) {
        if (breaking > 30) {
            return null;
        }
        //get two random nodes based on fame
        Node from = getRandomNode();
        Node to = getRandomNode();
        while (to.equals(from)) {
            to = getRandomNode();
        }
        Path path = doAStern(from, to, null);
        if (!path.isValid()) {
            path = getRandomPath(breaking + 1);
        }
        return path;
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


    public void add(Node node) {
        if (!contains(node)) {
            nodes.add(node);
            nodesSorted = false;
        } else {
            throw new RuntimeException("CITY NODE ALREADY ADDED");
        }
    }

    public void remove(Node node) {
        if (contains(node)) {
            nodes.remove(node);
        } else {
            throw new RuntimeException("NO NODE TO REMOVE");
        }
    }

    public boolean contains(Node node) {
        if (nodesSorted)
            return getNodeById(node.getId()) != null;
        else
            return nodes.contains(node);
    }

    public void sortNodes() {
        nodes.sort(Comparator.comparing(Node::getId));
        nodesSorted = true;
    }

    public Node getNodeById(String id) {
        if (id == null) return null;
        if (!nodesSorted)
            sortNodes();
        return getNodeById(id, 0, nodes.size() - 1);
    }

    private Node getNodeById(String id, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) >>> 1;
        if (nodes.get(mid).getId().equals(id))
            return nodes.get(mid);
        else
            return (nodes.get(mid).getId().compareTo(id) > 0) ?
                    getNodeById(id, left, mid - 1) :
                    getNodeById(id, mid + 1, right);
    }

    public void add(Street street) {
        if (!contains(street)) {
            streets.add(street);
            streetsSorted = false;
        }
    }

    public void remove(Street street) {
        if (contains(street))
            streets.remove(street);
    }

    public boolean contains(Street street) {
        if (streetsSorted)
            return getStreetById(street.getId()) != null;
        else
            return streets.contains(street);
    }

    public void sortStreets() {
        streets.sort(Comparator.comparing(Street::getId));
        streetsSorted = true;
    }

    public Street getStreetById(String id) {
        if (id == null) return null;
        if (!streetsSorted)
            sortNodes();
        return getStreetById(id, 0, streets.size() - 1);
    }

    public Street getStreetById(String id, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) >>> 1;
        if (streets.get(mid).getId().equals(id))
            return streets.get(mid);
        else
            return (streets.get(mid).getId().compareTo(id) > 0) ?
                    getStreetById(id, left, mid - 1) :
                    getStreetById(id, mid + 1, right);
    }

    public void sortLanes() {
        lanes.sort(Comparator.comparing(Lane::getId));
        lanesSorted = true;
    }

    public Lane getLaneById(String id) {
        if (id == null) return null;
        if (!lanesSorted)
            sortLanes();
        return getLaneById(id, 0, lanes.size() - 1);
    }

    public Lane getLaneById(String id, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) >>> 1;
        if (lanes.get(mid).getId().equals(id))
            return lanes.get(mid);
        else
            return (lanes.get(mid).getId().compareTo(id) > 0) ?
                    getLaneById(id, left, mid - 1) :
                    getLaneById(id, mid + 1, right);
    }

    public boolean contains(Lane lane) {
        return lanes.contains(lane);
    }

    public void add(Lane lane) {
        if (!contains(lane)) {
            lanes.add(lane);
            lanesSorted = false;
        }
    }

    public void remove(Lane lane) {
        if (contains(lane))
            lanes.remove(lane);
    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> ret = new ArrayList<>();
        for (Node node : nodes) {
            Iterator<Street> streets = node.getStreetIterator();
            while (streets.hasNext()) {
                Street street = streets.next();
                Iterator<Lane> bLanes = street.getBackwardLanesIterator();
                while (bLanes.hasNext()) {
                    Lane lane = bLanes.next();
                    extractVehicles(ret, lane);
                }
                Iterator<Lane> fLanes = street.getForwardLanesIterator();
                while (fLanes.hasNext()) {
                    Lane lane = fLanes.next();
                    extractVehicles(ret, lane);
                }
            }
        }
        return ret;
    }

    private void extractVehicles(ArrayList<Vehicle> ret, Lane lane) {
        for (Vehicle vehicle : lane.getVehicles()) {
            if (!ret.contains(vehicle))
                ret.add(vehicle);
        }
    }

    public Street getStreetByPoint(Point c) {
        int bestIndex = -1;
        double bestVal = Integer.MAX_VALUE;

        ArrayList<Street> streets1 = getStreets();
        for (int i = 0; i < streets1.size(); i++) {
            Street street = streets1.get(i);
            Point a = street.getFrom().getPosition();
            Point b = street.getTo().getPosition();
            double val = (distance(a, c) + distance(b, c)) - distance(a, b);
            val = Math.abs(val);
            if (val < bestVal) {
                bestVal = val;
                bestIndex=i;
            }
        }
        if (bestIndex == -1)
            return null;
        else
            return getStreets().get(bestIndex);

    }

    public double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
    }


    public void calcCity() {
        for (Street street : streets) {
            // TODO: 20.02.2018 calc by ordererd lane.priority. higher priority = first evaluation
            street.calcStreet();
        }
        for (Node node: nodes) {
            node.reset();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNodeSize() {
        return nodes.size();
    }

    public Iterator<Node> getNodeIterator() {
        return nodes.iterator();
    }

    public int getStreetSize() {
        return streets.size();
    }

    public Iterator<Street> getStreetIterator() {
        return streets.iterator();
    }

    public int getLaneSize() {
        return lanes.size();
    }

    public Iterator<Lane> getLaneIterator() {
        return lanes.iterator();
    }

    public Rectangle getBounds() {
        return new Rectangle(0, 0, getMaxWidth(), getMaxHeight());
    }

    private int getMaxHeight() {
        int ret = 0;
        for (Node node : nodes) {
            if (node.getX() > ret)
                ret = node.getX();
        }
        return ret;
    }

    private int getMaxWidth() {
        int ret = 0;
        for (Node node : nodes) {
            if (node.getY() > ret)
                ret = node.getY();
        }
        return ret;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Street> getStreets() {
        return streets;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }
}
