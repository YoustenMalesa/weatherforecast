package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;

public interface WeatherPresenter {
    void loadWeather(GPSLocationListener pLocation);
    void loadWeather(String pCity);

}
