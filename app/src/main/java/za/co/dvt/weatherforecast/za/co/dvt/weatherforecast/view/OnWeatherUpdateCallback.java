package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.view;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;


public interface OnWeatherUpdateCallback {
    void onLocationUpdate(GPSLocationListener pLocation);
}
