package parsing;

// org.json:json:20171018
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
import java.sql.Timestamp;

public final class OsmToJSonParser {

    private static JSONObject jsonRoot;

    public static void main(String[] args) {
        parse(System.getProperty("user.dir")+"\\src\\parsing\\smallBZ.osm",
                System.getProperty("user.dir")+"\\src\\parsing\\smallbz.json");
    }

    public static void parse(String osmFilePath, String jsonFilePath) {

        System.out.println("OsmToJsonParser:Parsing from .osm to .json:\n    " +
                osmFilePath+"\n    "+jsonFilePath);

        File fXmlFile = new File(osmFilePath);
        File json = new File(jsonFilePath);

        jsonRoot = new JSONObject();

        // https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            // optional, but recommended
            // read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // nodes
            System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) +
                    " Reading nodes...");

            NodeList nNodeList = doc.getElementsByTagName("node");
            JSONArray jNodes = new JSONArray();
            createNodes(nNodeList, jNodes);

            jsonRoot.put("nodes", jNodes);

            // ways
            System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) +
                    " Reading ways...");

            NodeList nWayList = doc.getElementsByTagName("way");
            JSONArray jStreets = new JSONArray();
            JSONArray jLanes = new JSONArray();
            createStreetsAndLanes(nWayList, jStreets, jLanes);

            jsonRoot.put("streets", jStreets);
            jsonRoot.put("lanes", jLanes);

            System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) +
                    " Assigning types to nodes...");
            assignTypeToNodes();

            jsonRoot.put("streetlight", new JSONArray());

            System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) +
                    " Printing...");
            PrintWriter printWriter = new PrintWriter(json);
            printWriter.print(jsonRoot.toString());
            printWriter.close();

            System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) +
                    " Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createNodes(NodeList nNodeList, JSONArray jNodes) {
        double smallestX = Integer.MAX_VALUE;
        double smallestY = Integer.MAX_VALUE;

        for (int i = 0; i < nNodeList.getLength(); i++) {

            Node nNode = nNodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                JSONObject jNode = new JSONObject();
                jNode.put("id", eElement.getAttribute("id"));

                double x = Double.parseDouble(eElement.getAttribute("lon"));
                x += 180;
                x *= 10000000;
                double y = Double.parseDouble(eElement.getAttribute("lat"));
                y += 90;
                y = 180-y;
                y *= 10000000;

                if ((int)x < smallestX) smallestX = (int)x;
                if ((int)y < smallestY) smallestY = (int)y;

                jNode.put("x", (int)(x)); //lon
                jNode.put("y", (int)(y)); //lat

                jNode.put("fame", 0.2);
                jNode.put("type", "NotClassified");

                jNodes.put(jNode);
            }
        }

        // decrease x/y-Values
        for (int i = 0; i < jNodes.length(); i++) {
            JSONObject node = jNodes.getJSONObject(i);
            int orgX = node.getInt("x");
            int orgY = node.getInt("y");
            node.put("x", orgX-smallestX);
            node.put("y", orgY-smallestY);
        }

    }

    private static void createStreetsAndLanes(NodeList nWayList, JSONArray jStreets, JSONArray jLanes) {
        for (int i = 0; i < nWayList.getLength(); i++) {
            Node nWay = nWayList.item(i);

            if (nWay.getNodeType() == Node.ELEMENT_NODE) {

                Element eWayElement = (Element) nWay;

                NodeList ndsList = eWayElement.getElementsByTagName("nd");

                JSONObject lastNode = null;

                for (int j = 0; j < ndsList.getLength(); j++) {
                    Node nNds = ndsList.item(j);

                    if (nNds.getNodeType() == Node.ELEMENT_NODE) {
                        Element eNdsElement = (Element) nNds;

                        String nodeRef = eNdsElement.getAttribute("ref");
                        JSONObject jNode = getNodeById(nodeRef);

                        if (lastNode == null)
                            lastNode  = jNode;
                        else {
                            JSONObject jStreet = new JSONObject();

                            String jStreetID = eWayElement.getAttribute("id")+"_"+j;

                            jStreet.put("id", jStreetID);
                            jStreet.put("from", lastNode.getString("id"));
                            jStreet.put("to", jNode.getString("id"));
                            jStreet.put("maxSpeed", 1);
                            jStreet.put("prominence", 1);

                            lastNode = jNode;

                            jStreets.put(jStreet);

                            // TODO: 02.02.2018 always 2 lanes on street
                            JSONObject jLane = new JSONObject();
                            JSONObject jLaneR = new JSONObject();

                            jLane.put("id", jStreetID+"_f_0");
                            jLane.put("parent", jStreetID);
                            jLane.put("index", 0);
                            jLane.put("reversed", false);

                            jLaneR.put("id", jStreetID+"_t_0");
                            jLaneR.put("parent", jStreetID);
                            jLaneR.put("index", 0);
                            jLaneR.put("reversed", true);

                            jLanes.put(jLane);
                            jLanes.put(jLaneR);
                        }
                    }
                }
            }
        }
    }

    private static void assignTypeToNodes() {
        JSONArray nodes = jsonRoot.getJSONArray("nodes");
        for (int i = 0; i < nodes.length(); i++) {
            JSONObject jNode = nodes.getJSONObject(i);
            String nodeId = jNode.getString("id");

            int streetCount = 0;

            JSONArray streets = jsonRoot.getJSONArray("streets");
            for (int j = 0; j < streets.length(); j++) {
                JSONObject jStreet = streets.getJSONObject(j);

                if (jStreet.getString("from").equals(nodeId) ||
                        jStreet.getString("to").equals(nodeId))
                    streetCount++;
            }

            switch (streetCount) {
                case 0:
                    jNode.put("type", "NotConnected");
                    break;
                case 1:
                    jNode.put("type", "DeadEnd");
                    break;
                case 2:
                    jNode.put("type", "Connection");
                    break;
                default:
                    jNode.put("type", "MultiConnection");
            }
        }
    }

    private static JSONObject getNodeById(String id) {
        JSONArray nodes = jsonRoot.getJSONArray("nodes");

        for (int i = 0; i < nodes.length(); i++) {
            JSONObject node = nodes.getJSONObject(i);
            if (id.equals(node.getString("id")))
                return node;
        }

        throw new RuntimeException("NodeNotFound:"+id);
    }

    private OsmToJSonParser() {}
}
