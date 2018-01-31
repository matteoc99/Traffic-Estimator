package parsing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SumoToJSonParser {

    public static void main(String[] args) {
        write("");
        //parse(null, "C:\\Users\\User\\Desktop\\Parser\\Pattern.json");
    }

    private static void parse(File xmlFile, String JSonPath) {
        JSONTokener jsonTokener;
        try {
            jsonTokener = new JSONTokener(new FileReader(JSonPath));
            JSONObject jsonObject = new JSONObject(jsonTokener);
            JSONArray nodes = ((JSONArray) jsonObject.get("nodes"));
            JSONObject nodeEntry0 = nodes.getJSONObject(0);
            String type = nodeEntry0.getString("type");

            System.out.println(type);
            System.out.println(nodeEntry0.toString());
            System.out.println(nodes.toString());


            //nodeEntry0.put("type", "Put");
            //nodeEntry0.append("type", "Append");
            //nodeEntry0.accumulate("type", "Accumulate");

        } catch (FileNotFoundException e) {
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
}
