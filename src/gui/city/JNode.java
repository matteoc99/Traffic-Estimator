package gui.city;

import logic.city.Node;
import logic.city.Street;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static gui.Main.xOffset;
import static gui.Main.yOffset;
import static gui.Main.zoom;


/**
 * @author Matteo Cosi
 * @since 15.12.2017
 */
public class JNode extends JPanel {


    /**
     * Reference
     */
    private Node node;

    private ArrayList<JStreet> jStreets;

    private JPanel parent;



    public JNode(Node node, JPanel parent) {
        jStreets = new ArrayList<>();
        setLayout(null);
        this.node = node;
        this.parent = parent;
        setBackground(Color.GREEN);
        setBounds(node.getX(), node.getY(), 1, 1);
        parent.add(this);
        create();
    }

    private void create() {
        for (int i = 0; i < node.getStreets().size(); i++) {
            Street street = node.getStreets().get(i);
            JStreet jStreet = new JStreet(street, (JCity) parent);
            jStreets.add(jStreet);
        }
    }


    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public ArrayList<JStreet> getjStreets() {
        return jStreets;
    }

    public void setjStreets(ArrayList<JStreet> jStreets) {
        this.jStreets = jStreets;
    }

    @Override
    public JPanel getParent() {
        return parent;
    }

    public void setParent(JPanel parent) {
        this.parent = parent;
    }

    public void reposition() {
        setBounds((int) ((node.getX()+xOffset)*zoom), (int) ((node.getY()+yOffset)*zoom), 1, 1);
    }
}
