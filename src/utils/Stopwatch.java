package utils;

/**
 * @author Maximilian Estfeller
 * @since 16.02.2018
 */
public class Stopwatch {

    private long startTime;
    public static boolean dbg = true;

    public Stopwatch() {
        startTime =  System.nanoTime();
    }

    public Stopwatch start() {
        startTime = System.nanoTime();
        return this;
    }

    public long getElapsedTime() {
        return (System.nanoTime() - startTime);
    }

    public void reset() {
        startTime = System.nanoTime();
    }

    public long getAndReset() {
        long ret = getElapsedTime();
        reset();
        return ret;
    }

    public void print(String string) {
        if (dbg) {
            System.out.println(string + getElapsedTime());
        }
    }

    public void printAndReset(String string) {
        if (dbg) {
            System.out.println(string + getAndReset());
        }
    }
}
