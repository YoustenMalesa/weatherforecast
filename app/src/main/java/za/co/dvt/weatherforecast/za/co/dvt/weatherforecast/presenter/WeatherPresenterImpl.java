package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter;

import com.android.volley.RequestQueue;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.GPSLocationListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.WeatherRepository;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.WeatherRepositoryImpl;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.view.WeatherView;

/**
 * Created by YMalesa on 2017/05/09.
 */

public class WeatherPresenterImpl implements WeatherPresenter {
    private WeatherView mView;
    private WeatherRepository mWeatherRepository;

    public WeatherPresenterImpl(WeatherView pView) {
        mView = pView;
        mWeatherRepository = new WeatherRepositoryImpl(this);
    }

    @Override
    public void loadWeather(GPSLocationListener pLocation, RequestQueue pRequestQueue) {
        mWeatherRepository.loadWeather(pLocation, pRequestQueue);
    }

    @Override
    public void loadWeather(String pCity, RequestQueue pRequestQueue) {
        mWeatherRepository.loadWeather(pCity, pRequestQueue);
    }

    @Override
    public void onWeatherUpdate(WeatherResponse pWeather) {
        if(pWeather == null) mView.showWeatherError(); else mView.showWeather(pWeather);
    }
}
