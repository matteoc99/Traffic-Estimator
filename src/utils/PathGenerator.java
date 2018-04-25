package utils;

import logic.city.City;
import logic.city.Path;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 16.02.2018
 */
//TODO BUG: write paths in .pth only after all are genratet. Otherwise .pth is corrupted
public class PathGenerator extends Thread {

    private static ArrayList<Path> paths = new ArrayList<>();

    static int preferredAmount = 30000;

    static City city = null;

    private static void generatePaths(City city) {
        generatePaths(city, preferredAmount);
    }

    private static void generatePaths(City city, int count) {
        File pth = new File("C:\\TrafficEstimator\\Paths\\" + city.getName() + ".pth");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pth);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        System.out.println("Path:" + new Timestamp(System.currentTimeMillis()) + " Generating Paths...");
        for (int i = 0; i < count; i++) {
            Path path = city.getRandomPath(0);
            try {
                if (path != null && path.isValid() && !contains(path)) {
                    bw.write(path.toString());
                    bw.newLine();
                    paths.add(path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i % (count / 10) == 0)
                System.out.println(i + "von" + count);
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean contains(Path path) {
        for (Path p : paths) {
            if (p.equals(path)) {
                return true;
            }
        }
        return false;
    }

    public static Path getRandomPath(City city) {
        //check if paths are already generated
        File pth = new File("C:\\TrafficEstimator\\Paths\\" + city.getName() + ".pth");
        if (pth.exists()) {
            if (paths.isEmpty()) {
                try {
                    FileReader fileReader = new FileReader(pth);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    System.out.println("Path:" + new Timestamp(System.currentTimeMillis()) + " Loading Paths...");
                    int count = 0;
                    while ((line = bufferedReader.readLine()) != null) {
                        try {
                            Path p = Path.pathFromString(line);
                            paths.add(p);
                            count++;
                            if (count % (preferredAmount / 10) == 0)
                                System.out.println((double) count / preferredAmount * 100 + "%");
                        }catch (Exception e){
                            bufferedReader.close();
                            pth.delete();
                            System.out.println("Path:" + new Timestamp(System.currentTimeMillis()) + " Delete Corrupted Paths...1");
                            return getRandomPath(city);
                        }

                    }
                    if(paths.size()<preferredAmount-10){
                        bufferedReader.close();
                        pth.delete();
                        System.out.println("Path:" + new Timestamp(System.currentTimeMillis()) + " Delete Corrupted Paths...2");
                        return getRandomPath(city);
                    }
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int randIndex = (int) (Math.random() * paths.size());
            if (paths.size() == 0) {
                System.out.println("ALARM");
                getRandomPath(city);
            }
            return paths.get(randIndex);
        } else {
            generatePaths(city);
            return getRandomPath(city);
        }
    }

    @Override
    public void run() {
        getRandomPath(PathGenerator.city);
    }

    public static void start(City city) {
        PathGenerator.city = city;
        new PathGenerator().start();
        makedir();
    }


    private static void makedir() {
        File theDir = new File("C:\\TrafficEstimator");
        checkDir(theDir);
        theDir = new File("C:\\TrafficEstimator\\Paths");
        checkDir(theDir);
    }

    private static void checkDir(File theDir) {
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (SecurityException se) {
                se.printStackTrace();
            }

        }
    }
}
