package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util;

/**
 * Created by YMalesa on 2017/05/04.
 */

public class DegreesConverter {

    public static double toCelcius(double pDegrees) {
        double vDegrees = pDegrees - 273.15D;

        return (int)vDegrees;
    }
}
