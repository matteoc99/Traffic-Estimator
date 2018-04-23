package tests;

/**
 * @author Matteo Cosi
 * @since 24.12.2017
 */
public class Tester {
    public static void main(String[] args) throws InterruptedException {
        double zoom = 1;
        int level = 19;
        while (level > 8) {
            zoom /= 2;
            level--;
        }
        System.out.println(zoom);
    }
}
