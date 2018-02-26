package NeuralNetworkLibrary.src.network_gui;



import NeuralNetworkLibrary.src.network.Layer;
import NeuralNetworkLibrary.src.network.Neuron;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Maximilian Estfelller
 * @since 19.05.2017
 */
class NetworkPanelNeuron extends MovableComponent implements NetworkGUIComponent{

    enum FocusState {
        NONE,
        ALL,
        AXONS,
        DENDRITES
    }


    private boolean developerMode = false;

    private FocusState focusState = FocusState.ALL;

    /**
     * A network.Neuron is drawn with two different colors, depending on their type
     * The outerColor is the color of the border
     */
    private Color innerColor;
    private Color outerColor;
    private RadialGradientPaint paint;

    private Layer.LayerType type;

    private Object equivalent;

    private ArrayList<NetworkPanelConnection> axons = new ArrayList<>();
    private ArrayList<NetworkPanelConnection> dendrites = new ArrayList<>();

    NetworkPanelNeuron(Neuron equivalent, Layer.LayerType type) {
        this.setEquivalent(equivalent);
        this.type = type;
        this.addMouseListener(new NPNMouseListener());
        switch (type) {
            case IN:
                innerColor = new Color(250,250,0).brighter();
                outerColor = new Color(250,250,0);
                break;
            case HIDDEN:
                innerColor = new Color(125,250,0).brighter();
                outerColor = new Color(125,250,0);
                break;
            case OUT:
                innerColor = new Color(250,125,0).brighter();
                outerColor = new Color(250,125,0);
                break;
        }
    }

    void registerAsAxon(NetworkPanelConnection axon) {
        this.axons.add(axon);
    }

    void registerAsDendrite(NetworkPanelConnection dendrite) {
        this.dendrites.add(dendrite);
    }

    public Layer.LayerType getLayerType() {
        return this.type;
    }

    @Override
    public Object getEquivalent() {
        return this.equivalent;
    }

    @Override
    public void setEquivalent(Object equivalent) {
        this.equivalent = equivalent;
    }

    @Override
    public void toggleDeveloperMode() {
        developerMode=!developerMode;
        if (developerMode)
            this.setBorder(new LineBorder(Color.RED, 1));
        else
            this.setBorder(null);
        repaint();
    }

    private void changeFocusState() {
        if (((NetworkPanel) getParent()).isFocusMode()) {
            // changes the State of this network.Neuron only
            if (focusState != FocusState.values()[FocusState.values().length-1])
                focusState = FocusState.values()[focusState.ordinal() + 1];
            else
                focusState = FocusState.values()[0];
            if (focusState == FocusState.NONE)
                ((NetworkPanel) getParent()).tryDeactivateFocusMode();
            // repaint the Neuron and all affected Connections
            repaint();
            axons.forEach(Component::repaint);
            dendrites.forEach(Component::repaint);
        } else {
            // activates the focusMode of the NetworkPanel
            // and sets the focusState of all Neurons within the networkPanel to NONE
            // except for this one
            ((NetworkPanel) getParent()).setFocusMode(true);
            for (ArrayList<NetworkPanelNeuron> networkPanelNeurons : ((NetworkPanel) getParent()).getNeuronLayers())
                for (NetworkPanelNeuron networkPanelNeuron : networkPanelNeurons)
                    networkPanelNeuron.setFocusState(FocusState.NONE);
            this.setFocusState(FocusState.ALL);
            // repaints all components of this the NetworkPanel
            getParent().repaint();
        }
    }

    void setFocusState(FocusState state) {
        this.focusState = state;
    }

    FocusState getFocusState() {
        return this.focusState;
    }

    @Override
    public NetworkGUIComponentType getNetworkGUIComponentType() {
        return NetworkGUIComponentType.NEURON;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int outRad = (getWidth()-getWidth()/10)/2;
        int inRad = outRad-getWidth()/10;

        float[] dist = {0.1f, 1f};
        paint = new RadialGradientPaint(getWidth()/2, getHeight()/2, inRad+1, dist,
                new Color[]{innerColor, outerColor}, MultipleGradientPaint.CycleMethod.REPEAT);

        Graphics2D gAlia = (Graphics2D) g;

        gAlia.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gAlia.setPaint(outerColor);
        gAlia.fillOval(getWidth()/2-outRad, getWidth()/2-outRad, outRad*2, outRad*2);
        gAlia.setPaint(Color.BLACK);
        gAlia.drawOval(getWidth()/2-outRad, getWidth()/2-outRad, outRad*2, outRad*2);
        gAlia.setPaint(paint);
        gAlia.fillOval(getWidth()/2-inRad, getWidth()/2-inRad, inRad*2, inRad*2);
        if (developerMode) {
            gAlia.setPaint(Color.BLACK);
            gAlia.drawString(focusState.ordinal() + "", 0, 10);
        }
    }

    private class NPNMouseListener extends MouseAdapter {

        private Point onClick;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (onClick.equals(e.getLocationOnScreen()))
                changeFocusState();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            onClick = e.getLocationOnScreen();
        }
    }

    @Override
    public void refresh() {
        // nothing to refresh yet
    }

    public void reset() {
        focusState = FocusState.ALL;
    }
}
