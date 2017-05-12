package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model;


import com.android.volley.RequestQueue;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter.WeatherPresenter;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;

public class WeatherRepositoryImpl implements WeatherRepository {
    private RequestQueue mRequestQueue;
    private WeatherResponse mWeather = null;
    private WeatherPresenter mCallback;
    private Retrofit mRetrofit;
    private final GsonConverterFactory mGsonConverterFactory = GsonConverterFactory.create();
    private final OkHttpClient mHttpClient = new OkHttpClient();
    private OpenWeatherAPIService mService;

    public WeatherRepositoryImpl(WeatherPresenter pCallback) {
        mCallback = pCallback;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(WeatherRepository.OPEN_WEATHER_END_POINT)
                .client(mHttpClient)
                .addConverterFactory(mGsonConverterFactory)
                .build();

        mService = mRetrofit.create(OpenWeatherAPIService.class);
    }

    @Override
    public void loadWeather(GPSLocationListener pLocation, Callback<WeatherResponse> pCallback) throws IOException {
        Call<WeatherResponse> vCall = mService.getWeatherByLatLong(
                String.valueOf(pLocation.getLatitude()),
                String.valueOf(pLocation.getLongitude()),
                WeatherRepository.OPEN_WEATHER_APPID);

        vCall.enqueue(pCallback);
    }

    @Override
    public void loadWeather(String pCity, Callback<WeatherResponse> pCallback) throws IOException {
        Call<WeatherResponse> vCall = mService.getWeatherByCity(pCity,
                WeatherRepository.OPEN_WEATHER_APPID);

        vCall.enqueue(pCallback);
    }
}
