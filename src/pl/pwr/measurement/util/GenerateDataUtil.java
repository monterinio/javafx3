package pl.pwr.measurement.util;

import java.util.Random;

public class GenerateDataUtil {
    private GenerateDataUtil() {        }

    public static double generateCurrent() {
        Random rand = new Random();
        return 2*rand.nextDouble()+3;
    }

    public static double generatePressure() {
        Random rand = new Random();
        return 5*rand.nextDouble()+5;
    }
}
