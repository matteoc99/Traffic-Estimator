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

    public static void main(String[] args) {
        parse("C:\\Users\\User\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\map.osm",
                "C:\\Users\\User\\IdeaProjects\\Traffic-Estimator\\src\\parsing\\lana.json");
    }

    public static void parse(String osmFilePath, String jsonFilePath) {
        File fXmlFile = new File(osmFilePath);
        File json = new File(jsonFilePath);

        JSONObject jsonRoot = new JSONObject();

        double minLon = 0;
        double minLat = 0;

        // https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList boundsList = doc.getElementsByTagName("bounds");
            Node boundsNode = boundsList.item(0);
            if (boundsNode.getNodeType() == Node.ELEMENT_NODE) {
                Element boundsElement = (Element) boundsNode;
                minLat = Double.parseDouble(boundsElement.getAttribute("minlat"));
                minLon = Double.parseDouble(boundsElement.getAttribute("minlon"));

                // no digits and positive numbers only, starting top left
                minLon += 180;
                minLon *= 10000000;

                minLat += 90;
                minLat = 180-minLat;
                minLat *= 10000000;

            } else
                throw new RuntimeException("OsmJoJSonParser:Bounds not found");

            NodeList nList = doc.getElementsByTagName("node");

            JSONArray jNodes = new JSONArray();

            for (int i = 0; i < nList.getLength() && i < 50; i++) {

                Node nNode = nList.item(i);

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

                    jNode.put("x", (int)(x-minLon)); //lon
                    jNode.put("y", (int)(y-minLat)); //lat

                    jNode.put("fame", 0.2);
                    jNode.put("type", "MultiConnection");

                    jNodes.put(jNode);
                }
            }

            jsonRoot.put("nodes", jNodes);

            PrintWriter printWriter = new PrintWriter(json);
            printWriter.write(jsonRoot.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OsmToJSonParser() {}
}
