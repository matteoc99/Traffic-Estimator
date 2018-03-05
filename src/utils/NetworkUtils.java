package utils;

import NeuralNetworkLibrary.src.network.Network;
import logic.vehicles.driving.DataSets;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Matteo Cosi
 * @since 03.03.2018
 */
public class NetworkUtils {


    private static ArrayList<Network> networks= new ArrayList<>();

    private static void generateNetworks() {
        generateNetworks(1000);
    }

    private static void generateNetworks(int count) {
        File pth = new File(System.getProperty("user.dir") + "/src/logic/vehicles/driving/network.net");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pth);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fos != null;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        System.out.println("generating networks");
        for (int i = 0; i < count; i++) {
            if(i%10 ==0)
            System.out.println(i/10);
            DataSets dataSets = new DataSets();
            dataSets.generateDataSets();
            Network network = new Network(7, 4, 1, new int[]{6});
            network.train(dataSets.getDataset());
            try {
                    bw.write(Arrays.toString(network.getDescriptor()));
                    bw.newLine();
                    networks.add(network);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean contains(Network network) {
        for (Network network1: networks) {
            if (network.equals(network1)) {
                return true;
            }
        }
        return false;
    }

    public static Network getRandomNetwork() {
        //check if paths are already generated
        File pth = new File(System.getProperty("user.dir") + "/src/logic/vehicles/driving/network.net");
        if (pth.exists()) {
            if (networks.isEmpty()) {
                try {
                    FileReader fileReader = new FileReader(pth);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        Network network = new Network(fromString(line));
                        networks.add(network);
                    }
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int randIndex = (int) (Math.random() * networks.size());
            if(networks.size()==0){
                System.out.println("ALARM");
                getRandomNetwork();
            }
            return networks.get(randIndex);
        } else {
            generateNetworks();
            return getRandomNetwork();
        }
    }
    private static double[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        double result[] = new double[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Double.parseDouble(strings[i]);
        }
        return result;
    }

}
