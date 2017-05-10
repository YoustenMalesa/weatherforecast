package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.view;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;

/**
 * Created by YMalesa on 2017/05/09.
 */

public interface WeatherView {
    void showWeatherError();
    void showWeather(WeatherResponse pWeather);

    void getCurrentLocation() throws SecurityException;
    boolean checkIsPermissionGranted();

    void onLocationUpdate(GPSLocationListener pLocation);

}
