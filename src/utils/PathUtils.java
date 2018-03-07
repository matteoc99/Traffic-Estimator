package utils;

import logic.city.City;
import logic.city.Path;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Matteo Cosi
 * @since 16.02.2018
 */
public class PathUtils {

    private static ArrayList<Path> paths = new ArrayList<>();

    private static void generatePaths(City city) {
        generatePaths(city, 1000);
    }

    private static void generatePaths(City city, int count) {
        File pth = new File(System.getProperty("user.dir") + "/src/logic/paths/" + city.getName() + ".pth");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pth);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        System.out.println("generating");
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
            if(i%(count/10)==0)
                System.out.println(i+"von"+count);
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
        File pth = new File(System.getProperty("user.dir") + "/src/logic/paths/" + city.getName() + ".pth");
        if (pth.exists()) {
            if (paths.isEmpty()) {
                try {
                    FileReader fileReader = new FileReader(pth);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        Path p = Path.pathFromString(line);
                        paths.add(p);
                    }
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int randIndex = (int) (Math.random() * paths.size());
            if(paths.size()==0){
                System.out.println("ALARM");
                getRandomPath(city);
            }
            return paths.get(randIndex);
        } else {
            generatePaths(city);
            return getRandomPath(city);
        }
    }

}
