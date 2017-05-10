package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter;

import com.android.volley.RequestQueue;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;

public interface WeatherPresenter {
    void loadWeather(GPSLocationListener pLocation, RequestQueue mRequestQueue);
    void loadWeather(String pCity, RequestQueue pRequestQueue);
    void onWeatherUpdate(WeatherResponse pWeather);

}
