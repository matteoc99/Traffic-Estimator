package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Path {

    /**
     * pth file constants
     */
    public static String HEADER = "p";
    public static String OPEN_PROPS = "{";
    public static String CLOSE_PROPS = "}";
    public static String ATTR_MIN_KNOWLEGE = "m";
    public static String ATTR_NODE = "i";
    public static String NODE_DELIMITER = ">";


    /**
     * contains the ids of all the nodes contained in this path
     */
    ArrayList<String> ids;

    /**
     * progress index on the street intersection
     */
    private int progress;


    /**
     * tells what knowledge is needed to drive on this path
     */
    int minKnowledge;


    /**
     * sets the defaults
     */
    public Path() {
        progress = 0;
        ids = new ArrayList<>();
        minKnowledge = 0;
    }

    /**
     * create a path with a minimum knowledge
     */
    public Path(int minKnowledge) {
        this();
        this.minKnowledge = minKnowledge;
    }


    /**
     * returns the next Node id on this Path that we want to reach
     *
     * @return the next nOde
     */
    public String getGoal() {
        if (progress >= ids.size())
            return null;
        return ids.get(progress);
    }


    /**
     * returns the next Node id on this Path that we want to reach
     * also increments the progress on this path
     *
     * @return the next node
     */
    public String getGoalAndIncrement() {
        if (progress >= ids.size()) {
            return null;
        }
        String ret = ids.get(progress);
        progress++;
        return ret;
    }

    public String getFrom() {
        return ids.get(0);
    }

    public String getTo() {
        return ids.get(ids.size() - 1);
    }

    /**
     * Adds a Street intersection
     *
     * @return true if it was successfully added, otherwise false
     */
    public void addNodeIdAtIndex(String nodeId, int index) {
        if (!contains(nodeId)) {
            ids.add(index, nodeId);
        } else {
            throw new RuntimeException("Path 45");
        }
    }


    public void addNodeId(String nodeId) {
        if (!contains(nodeId)) {
            ids.add(nodeId);
        } else {
            throw new RuntimeException("Path 45");
        }
    }

    /**
     * Removes a Street intersection
     *
     * @return true if it was successfully removed, otherwise false
     */
    public void removeNode(String nodeId) {
        if (contains(nodeId)) {
            ids.remove(nodeId);
        } else {
            throw new RuntimeException("Path");
        }
    }

    /**
     * Checks if this city contains a certain Street Intersection
     *
     * @param nodeId the intersection to check
     * @return true if it contains the intersection, otherwise false
     */
    public boolean contains(String nodeId) {
        return ids.contains(nodeId);
    }

    /**
     * transforms the path to a String compatible with a *.pth file
     *
     * @return a string for this path
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(HEADER).append(OPEN_PROPS);

        ret.append(ATTR_MIN_KNOWLEGE).append(":").append(getMinKnowledge()).append(";");

        ret.append(ATTR_NODE).append(":");
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1) {
                ret.append(ids.get(i));
            } else {
                ret.append(ids.get(i)).append(NODE_DELIMITER);
            }
        }
        ret.append(";");
        ret.append(CLOSE_PROPS);
        return ret.toString();
    }

    /**
     * checks if the path is valid
     *
     * @return true if the path is valid, otherwise false
     */
    public boolean isValid() {
        if (ids.size() >= 3) {
            return true;
        }
        return false;
    }

    public int getProgress() {
        return progress;
    }

    public int getMinKnowledge() {
        return minKnowledge;
    }

    /**
     * Ceates a Path from a string and returns it
     *
     * @param path the string with which we want to create
     * @return a new Path
     */
    public static Path pathFromString(String path) {
        String props = extractProps(path);
        Path ret = new Path(Integer.parseInt(getProp(ATTR_MIN_KNOWLEGE, props)));
        String nodes = getProp(ATTR_NODE, props);

        while (nodes.length() > 0) {
            int endindex = nodes.indexOf(NODE_DELIMITER);
            if (endindex == -1) {
                ret.addNodeId(nodes);
                nodes = "";
            } else {
                ret.addNodeId(nodes.substring(0, endindex));
                nodes = nodes.substring(endindex + NODE_DELIMITER.length());
            }
        }
        ret.resetProgress();
        return ret;
    }

    /**
     * searches for the requested attribute in all the properties
     *
     * @param attr  teh requested attribute
     * @param props the properties of a path use extractProps on a path to get the props
     * @return the value of the attribute
     */
    private static String getProp(String attr, String props) {
        int index = props.indexOf(attr)
                + attr.length()
                + ":".length();
        return props.substring(index, props.indexOf(";", index));
    }

    /**
     * extracts the properties of a path string
     *
     * @param pth the path to string
     * @return the properties of the path
     */
    private static String extractProps(String pth) {
        return pth.substring(pth.indexOf(OPEN_PROPS) + 1, pth.indexOf(CLOSE_PROPS));
    }

    /**
     * extracts the properties of a path
     *
     * @param pth the path
     * @return the properties of the path
     */
    public static String extractProps(Path pth) {
        return pth.toString().substring(pth.toString().indexOf(OPEN_PROPS) + 1, pth.toString().indexOf(CLOSE_PROPS));
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    /**
     * resets the progress to zero
     */
    public void resetProgress() {
        progress = 0;
    }

    /**
     * checks if this path equals to another path
     *
     * @param path the path to check
     * @return true if the paths equal each other, otherwise false
     */
    public boolean equals(Path path) {
        if (path.ids.size() != this.ids.size())
            return false;
        for (int i = 0; i < ids.size(); i++) {
            if (!path.ids.get(i).equals(this.ids.get(i)))
                return false;
        }
        return true;
    }
}
