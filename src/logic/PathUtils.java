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

    private static ArrayList<Path> paths = null;

    private static void generatePaths(City city) {
        File pth = new File(System.getProperty("user.dir") + "/src/logic/paths/" + city.getName() + ".pth");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pth);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        System.out.println("generating");
        for (int i = 0; i < city.getNodes().size() * 3; i++) {
            System.out.println("progress:"+i+" of "+city.getNodes().size() * 3);
            Path path = city.getRandomPath(0);
            try {
                if (path != null && path.isValid()) {
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
            if (paths == null) {
                paths = new ArrayList<>();
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
