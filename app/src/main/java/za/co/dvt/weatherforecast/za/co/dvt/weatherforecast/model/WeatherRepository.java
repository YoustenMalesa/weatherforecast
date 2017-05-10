package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model;

import com.android.volley.RequestQueue;

/**
 * Created by YMalesa on 2017/05/09.
 */

public interface WeatherRepository {
    String OPEN_WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?lat=\"+mLocationListener.getLatitude()+\n"+
            "                        \"&lon=\"+mLocationListener.getLongitude()\n"+
            "                        +\"&appid=351f44cbb248066a357099486d17cab8\"";

    void loadWeather(GPSLocationListener pLocation, RequestQueue pRequestQueue);
    void loadWeather(String pCity, RequestQueue pRequestQueue);
}
