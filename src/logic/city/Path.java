package logic.city;

import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class Path {

    ArrayList<Node> nodes;

    /**
     * progress index on the street intersection
     */
    private int progress;


    public Path() {
        progress = 0;
        nodes = new ArrayList<>();
    }

    public Node getNextGoal() {
        progress++;
        return nodes.get(progress - 1);
    }

    /**
     * Adds a Street intersection
     *
     * @return true if it was successfully added, otherwise false
     */
    public void addNode(Node node) {
        if (!contains(node)) {
            nodes.add(node);
        }else{
            throw new RuntimeException("Path 45");
        }
    }

    /**
     * Removes a Street intersection
     *
     * @return true if it was successfully removed, otherwise false
     */
    public void removeNode(Node node) {
        if (contains(node)) {
            nodes.remove(node);
        }else{
            throw new RuntimeException("Path");
        }
    }

    /**
     * Checks if this city contains a certain Street Intersection
     *
     * @param node the intersection to check
     * @return true if it contains the intersection, otherwise false
     */
    public boolean contains(Node node) {
        for (Node intersection : nodes) {
            if (node.getId() == intersection.getId())
                return true;
        }
        return false;
    }
}
