package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model;

import java.io.IOException;

import retrofit2.Callback;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;

public interface WeatherRepository {
    String OPEN_WEATHER_END_POINT = "http://api.openweathermap.org/data/2.5/";
    String OPEN_WEATHER_APPID = "351f44cbb248066a357099486d17cab8";

    void loadWeather(GPSLocationListener pLocation, Callback<WeatherResponse> pCallback) throws IOException;
    void loadWeather(String pCity, Callback<WeatherResponse> pCallback) throws IOException;
}
