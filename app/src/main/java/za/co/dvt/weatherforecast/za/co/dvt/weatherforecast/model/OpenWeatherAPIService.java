package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;

public interface OpenWeatherAPIService {

    @GET("weather?")
    Call<WeatherResponse> getWeatherByCity(@Query("q") String pCity,
                                           @Query("appid") String pAppId);

    @GET("weather?")
    Call<WeatherResponse> getWeatherByLatLong(@Query("lat") String pLatitude,
                                              @Query("lon") String pLongitude,
                                              @Query("appid") String pAppId);

}
