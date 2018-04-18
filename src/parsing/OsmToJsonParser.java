package parsing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public final class OsmToJsonParser {

    /**
     * Reference to the root of the JsonFile
     */
    private static JSONObject jsonRoot;

    /**
     * Map to keep track of the amount of Streets connected to a Node
     */
    private static Map<String, Integer> streetsOnNode = new HashMap<>();

    public static void main(String[] args) {
        parse(System.getProperty("user.dir") + "\\src\\parsing\\res\\topleft.osm",
                System.getProperty("user.dir") + "\\src\\parsing\\res\\topleft.json");
    }

    /**
     * Method parses a OsmFile, exported from OpenStreetMap, to a JsonFile,
     * which can then be read in the City-class to create a City-Object
     *
     * @param osmFilePath  File to read from
     * @param jsonFilePath File to print to
     */
    public static void parse(String osmFilePath, String jsonFilePath) {

        System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) + " Parsing from .osm to .json:\n    " +
                osmFilePath + "\n    " + jsonFilePath);

        File fXmlFile = new File(osmFilePath);
        File json = new File(jsonFilePath);

        jsonRoot = new JSONObject();

        // https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
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

            System.out.println("OsmToJsonParser:" + new Timestamp(System.currentTimeMillis()) +
                    " Adjusting positions of nodes...");
            adjustNodePositions();

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

    /**
     * Handles the parsing of the node-tags from the OsmFile to Nodes
     *
     * @param nNodeList to read
     * @param jNodes    to print
     */
    private static void createNodes(NodeList nNodeList, JSONArray jNodes) {
        int length = nNodeList.getLength();
        for (int i = 0; i < length; i++) {

            Node nNode = nNodeList.item(i);

            if (nNode.getNodeType() != Node.ELEMENT_NODE)
                continue;

            Element eElement = (Element) nNode;

            JSONObject jNode = new JSONObject();
            jNode.put("id", eElement.getAttribute("id"));

            jNode.put("x", eElement.getAttribute("lon")); //lon
            jNode.put("y", eElement.getAttribute("lat")); //lat

            jNode.put("fame", 0.2);
            jNode.put("type", "NotClassified");

            jNodes.put(jNode);
        }
    }

    /**
     * Handles the parsing of the way-tags from the OsmFile to Streets and Lanes
     *
     * @param nWayList to read
     * @param jStreets to print
     * @param jLanes   to print
     */
    private static void createStreetsAndLanes(NodeList nWayList, JSONArray jStreets, JSONArray jLanes) {
        int wayLength = nWayList.getLength();
        for (int i = 0; i < wayLength; i++) {
            Node nWay = nWayList.item(i);

            if (nWay.getNodeType() != Node.ELEMENT_NODE)
                continue;

            Element eWayElement = (Element) nWay;

            if (!validateWay(eWayElement))
                continue;

            NodeList ndsList = eWayElement.getElementsByTagName("nd");

            String lastNodeId = null;

            int ndLength = ndsList.getLength();
            for (int j = 0; j < ndLength; j++) {
                Node nNds = ndsList.item(j);

                if (nNds.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element eNdsElement = (Element) nNds;

                String nodeRef = eNdsElement.getAttribute("ref");

                if (lastNodeId == null) {
                    lastNodeId = nodeRef;
                    continue;
                }

                JSONObject jStreet = new JSONObject();

                String jStreetID = eWayElement.getAttribute("id") + "_" + j;

                jStreet.put("id", jStreetID);
                jStreet.put("from", lastNodeId);
                jStreet.put("to", nodeRef);
                jStreet.put("maxSpeed", 1);
                jStreet.put("prominence", 1);

                jStreets.put(jStreet);

                // register street in map
                streetsOnNode.put(lastNodeId, streetsOnNode.getOrDefault(lastNodeId, 0) + 1);
                streetsOnNode.put(nodeRef, streetsOnNode.getOrDefault(nodeRef, 0) + 1);

                lastNodeId = nodeRef;

                // TODO: 02.02.2018 always 2 lanes on street
                JSONObject jLane = new JSONObject();
                JSONObject jLaneR = new JSONObject();

                jLane.put("id", jStreetID + "_f_0");
                jLane.put("parent", jStreetID);
                jLane.put("index", 0);
                jLane.put("reversed", false);

                jLaneR.put("id", jStreetID + "_t_0");
                jLaneR.put("parent", jStreetID);
                jLaneR.put("index", 0);
                jLaneR.put("reversed", true);

                jLanes.put(jLane);
                jLanes.put(jLaneR);
            }
        }
    }

    /**
     * Method reruns over all Nodes to correctly assign the type
     * The type depends on the amount of Streets connected to the Node
     */
    private static void assignTypeToNodes() {
        JSONArray nodes = jsonRoot.getJSONArray("nodes");
        for (int pointer = 0; pointer < nodes.length(); pointer++) {
            JSONObject jNode = nodes.getJSONObject(pointer);
            String nodeId = jNode.getString("id");

            int streetCount = streetsOnNode.getOrDefault(nodeId, 0);

            switch (streetCount) {
                case 0:
                    nodes.remove(pointer);
                    pointer--;
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

    /**
     * Method reruns over all Nodes to adjust the positions
     */
    private static void adjustNodePositions() {
        JSONArray nodes = jsonRoot.getJSONArray("nodes");

        double minLat = Double.MAX_VALUE;
        double minLon = Double.MAX_VALUE;
        double maxLat = Double.MIN_VALUE;
        double maxLon = Double.MIN_VALUE;

        for (int i = 0; i < nodes.length(); i++) {
            JSONObject jNode = nodes.getJSONObject(i);
            double x = Double.parseDouble(jNode.getString("x"));
            minLon = minLon < x ? minLon : x;
            maxLon = maxLon > x ? maxLon : x;
            x = Utils.getOsmTileX(x, 19);

            double y = Double.parseDouble(jNode.getString("y"));
            minLat = minLat < y ? minLat : y;
            maxLat = maxLat > y ? maxLat : y;
            y = Utils.getOsmTileY(y, 19);

            jNode.put("x", x);
            jNode.put("y", y);
        }

        JSONObject jBounds = new JSONObject();
        jBounds.put("minLat", minLat);
        jBounds.put("minLon", minLon);
        jBounds.put("maxLat" ,maxLat);
        jBounds.put("maxLon", maxLon);
        jsonRoot.put("bounds", jBounds);
    }

    /**
     * Method decides whether the given way is useful to the JsonFile
     *
     * @param way to check
     * @return validation
     */
    private static boolean validateWay(Element way) {
        NodeList attrList = way.getElementsByTagName("tag");
        for (int i = 0; i < attrList.getLength(); i++) {
            Node node = attrList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = ((Element) node);

                String key = element.getAttribute("k");
                String value = element.getAttribute("v");

                if (key.equals("highway")) {
                    switch (value) {
                        case "footway":
                            return false;
                        default:
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private OsmToJsonParser() {
        // TODO: 11.04.2018 ampel
        //  <tag k="crossing" v="traffic_signals"/>
        //  <tag k="highway" v="traffic_signals"/>
    }
}