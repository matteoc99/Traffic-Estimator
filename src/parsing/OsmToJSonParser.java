package parsing;

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

public final class OsmToJSonParser {

    private static JSONObject jsonRoot;

    public static void main(String[] args) {
        parse("C:\\Users\\User\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\map.osm",
                "C:\\Users\\User\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\lana.json");
    }

    public static void parse(String osmFilePath, String jsonFilePath) {
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

            NodeList nNodeList = doc.getElementsByTagName("node");

            JSONArray jNodes = new JSONArray();

            double smallestX = Integer.MAX_VALUE;
            double smallestY = Integer.MAX_VALUE;

            for (int i = 0; i < nNodeList.getLength() && i < 50; i++) {

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
                    jNode.put("type", "MultiConnection");

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

            // ways

            NodeList nWayList = doc.getElementsByTagName("way");

            JSONArray jStreets = new JSONArray();

            for (int i = 0; i < nWayList.getLength(); i++) {
                Node nWay = nWayList.item(i);

                if (nWay.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nWay;
                    JSONObject jStreet = new JSONObject();


                }
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

    private JSONObject getNodeById(String id) {
        JSONArray nodes = jsonRoot.getJSONArray("nodes");

        for (int i = 0; i < nodes.length(); i++) {
            JSONObject node = nodes.getJSONObject(i);
            if (id.equals(node.getString("id")))
                return node;
        }

        return null;
    }

    private OsmToJSonParser() {}
}
