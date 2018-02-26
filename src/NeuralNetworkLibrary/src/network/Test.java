package NeuralNetworkLibrary.src.network;

/**
 * @author Matteo Cosi
 * @since 16.09.2017
 */
public class Test {

    public static void main(String[] args) {
        Network net = new Network(2,3,2,new int[]{3,4});
        double[] result = net.processData(new double[]{0.5,0.9});
        net.mutateSoft(2,0.2);
    }
}
