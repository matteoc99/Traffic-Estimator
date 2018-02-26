package NeuralNetworkLibrary.src.network;

/**
 * This Network doesn´t contain objects
 * Just pure math
 *
 * @author Matteo Cosi
 * @since 16.09.2017
 */
public class OptimizedNetwork {

    //connections
    private double W[][][];

    //neuron values
    private double Z[][];


    Function activation = val -> 1 / (1 + Math.exp(-val));

    /**
     * create a random network
     *
     * @param inputs  number of input Neurons
     * @param outputs number of output Neurons
     * @param hidden  number of hidden Neurons
     */
    public OptimizedNetwork(int inputs, int outputs, int[] hidden) {
        W = new double[hidden.length + 1][][];

        for (int i = 0; i < W.length; i++) {
            if (hidden.length == 0) {
                W[i] = new double[inputs][outputs];
            } else {
                if (i == 0) {
                    W[i] = new double[inputs][hidden[0]];
                } else {
                    if (i == W.length - 1) {
                        W[i] = new double[hidden[i - 1]][outputs];
                    } else {
                        W[i] = new double[hidden[i - 1]][hidden[i]];
                    }
                }
            }
        }
        for (int i = 0; i < W.length; i++) {
            for (int j = 0; j < W[i].length; j++) {
                for (int k = 0; k < W[i][j].length; k++) {
                    W[i][j][k] = Math.random() * 2 - 1;
                }
            }
        }

        Z = new double[hidden.length + 2][];
        if (hidden.length == 0) {
            Z[0] = new double[inputs];
            Z[1] = new double[outputs];
        } else {
            Z[0] = new double[inputs];
            for (int i = 0; i < hidden.length; i++) {
                Z[i+i]=new double[hidden[i]];
            }
            Z[Z.length-1] = new double[outputs];
        }
    }

    public void feedForward(int[] data) {
        if (data.length !=Z[0].length)
            System.out.println("Err");

    }

    private double[][] multiply(double[][] m1, double[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length
        double[][] mResult = new double[mRRowLength][mRColLength];
        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }

}
