package logic;

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
        generatePaths(city, city.getNodeSize() / 6);
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
            System.out.println("progress:"+i+" of "+city.getNodeSize() * 3);
            Path path = city.getRandomPath(0);
            try {
                if (path != null && path.isValid()) {
                    // TODO: 18.02.2018 check to add only paths that are ! equal
                    bw.write(path.toString());
                    bw.newLine();
                }
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
            return paths.get(randIndex);
        } else {
            generatePaths(city);
            return getRandomPath(city);
        }
    }

}
