package junk;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SumoToJSonParser {

    private static JSONObject jsonRoot;

    private static ArrayList<Type> types = new ArrayList<>();

    public static void main(String[] args) {
        parse(new File(System.getProperty("user.dir") + "\\src\\parsing\\map.net.xml"),
                System.getProperty("user.dir") + "\\src\\parsing\\sumo.json");
    }

    private static void parse(File fXmlFile, String jsonPath) {
        File json = new File(jsonPath);

        jsonRoot = new JSONObject();

        // https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList typeList = doc.getElementsByTagName("type");

            for (int i = 0; i < typeList.getLength(); i++) {
                Node type = typeList.item(i);

                Element eElement = (Element) type;


                types.add(new Type()
                        .setId(eElement.getAttribute("id"))
                        .setNumLanes(Integer.parseInt(eElement.getAttribute("numLanes")))
                        .setPriority(Integer.parseInt(eElement.getAttribute("priority")))
                        .setSpeed(Double.parseDouble(eElement.getAttribute("speed"))));
            }

            NodeList junctionList = doc.getElementsByTagName("junction");

            JSONArray jNodes = new JSONArray();

            for (int i = 0; i < junctionList.getLength(); i++) {
                Node junction = junctionList.item(i);

                Element eElement = (Element) junction;


                JSONObject jNode = new JSONObject();
                jNode.put("id", eElement.getAttribute("id"));
                jNode.put("x", (int) (Double.parseDouble(eElement.getAttribute("x")) * 100));
                jNode.put("y", (int) (Double.parseDouble(eElement.getAttribute("y")) * 100));
                jNode.put("fame", 0.2);
                jNode.put("type", "MultiConnection");

                jNodes.put(jNode);

            }

            NodeList edgeList = doc.getElementsByTagName("edge");

            JSONArray jStreets = new JSONArray();

            for (int i = 0; i < edgeList.getLength(); i++) {
                Node edge = edgeList.item(i);

                Element eElement = (Element) edge;

                if (eElement.hasAttribute("function") &&
                        eElement.getAttribute("function").equals("internal"))
                    continue;

                if (!Type.useful(eElement.getAttribute("type"))) {
                    System.out.println("cut:" + eElement.getAttribute("type"));
                    continue;
                }

                JSONObject jStreet = new JSONObject();
                jStreet.put("id", eElement.getAttribute("id"));
                jStreet.put("from", eElement.getAttribute("from"));
                jStreet.put("to", eElement.getAttribute("to"));
                Type type = getTypeByString(eElement.getAttribute("type"));
                jStreet.put("maxSpeed", type.getSpeed());
                jStreet.put("prominence", 1);

                jStreets.put(jStreet);
            }

            jsonRoot.put("nodes", jNodes);
            jsonRoot.put("streets", jStreets);

            PrintWriter printWriter = new PrintWriter(json);
            printWriter.print(jsonRoot.toString());
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write(String path) {
        /*
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", "pla");
        jsonObject.accumulate("test2", "pla2");
        JSONArray jsonArray = new JSONArray("[pla, pli, plu]");
        jsonObject.append("testArray", "pla");
        */

        JSONObject jsonObject = new JSONObject();

        JSONArray nodes = new JSONArray();

        JSONObject nodes_0 = new JSONObject();
        nodes_0.put("id", "0");
        nodes_0.put("x", 2);

        JSONObject nodes_1 = new JSONObject();
        nodes_1.put("id", "2");
        nodes_1.put("x", 2);

        nodes.put(nodes_0);
        nodes.put(nodes_1);

        jsonObject.put("nodes", nodes);

        System.out.println(jsonObject.toString());
    }

    public static Type getTypeByString(String sType) {
        for (Type type : types) {
            if (type.getId().equals(sType))
                return type;
        }

        throw new RuntimeException("Unknown Type:" + sType);
    }

    private static class Type {
        private String id;
        private int priority;
        private int numLanes;
        private double speed;
        private boolean oneway;

        public String getId() {
            return id;
        }

        public Type setId(String id) {
            this.id = id;
            return this;
        }

        public int getPriority() {
            return priority;
        }

        public Type setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public int getNumLanes() {
            return numLanes;
        }

        public Type setNumLanes(int numLanes) {
            this.numLanes = numLanes;
            return this;
        }

        public double getSpeed() {
            return speed;
        }

        public Type setSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public boolean isOneway() {
            return oneway;
        }

        public Type setOneway(boolean oneway) {
            this.oneway = oneway;
            return this;
        }

        public static boolean useful(String id) {
            switch (id) {
                case "highway.living_street":
                case "highway.motorway":
                case "highway.motorway_link":
                case "highway.primary":
                case "highway.primary_link":
                case "highway.residential":
                case "highway.secondary":
                case "highway.secondary_link":
                case "highway.service":
                case "highway.services":
                case "highway.tertiary":
                case "highway.tertiary_link":
                case "highway.track":
                case "highway.trunk":
                case "highway.trunk_link":
                case "highway.unclassified":
                case "highway.unsurfaced":
                    return true;
                case "highway.bridleway":
                case "highway.bus_guideway":
                case "highway.cycleway":
                case "highway.footway":
                case "highway.ford":
                case "highway.path":
                case "highway.pedestrian":
                case "highway.raceway":
                case "highway.stairs":
                case "highway.step":
                case "highway.steps":
                case "railway.light_rail":
                case "railway.preserved":
                case "railway.rail":
                case "railway.subway":
                case "railway.tram":
                    return false;
                default:
                    System.out.println("unknown:" + id);
                    return true;
            }
        }
    }
}
