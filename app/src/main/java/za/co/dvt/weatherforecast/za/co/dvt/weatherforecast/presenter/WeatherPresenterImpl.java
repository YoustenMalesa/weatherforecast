package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.WeatherRepository;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.WeatherRepositoryImpl;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.view.WeatherView;

public class WeatherPresenterImpl implements WeatherPresenter {
    private final String TAG = "WeatherPresenterImpl";
    private WeatherView mView;
    private WeatherRepository mWeatherRepository;

    public WeatherPresenterImpl(WeatherView pView) {
        mView = pView;
        mWeatherRepository = new WeatherRepositoryImpl();
    }

    @Override
    public void loadWeather(GPSLocationListener pLocation) {
        try {
            mWeatherRepository.loadWeather(pLocation, new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    Log.d(TAG, "onResponse::" + response.toString());
                    if (response.isSuccessful()) {
                        mView.showWeather(response.body());
                    }else {
                        mView.showWeatherError();
                    }
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure:: " + t);
                    mView.showWeatherError();
                }
            });
        }catch (IOException ex) {
            Log.e(TAG, "onFailure:: " + ex);
            mView.showWeatherError();
        }

    }

    @Override
    public void loadWeather(String pCity) {
        try {
            mWeatherRepository.loadWeather(pCity, new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    Log.d(TAG, "onResponse::" + response.toString());
                    if (response.isSuccessful()) {
                        mView.showWeather(response.body());
                    }else {
                        mView.showWeatherError();
                    }
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure:: " + t);
                    mView.showWeatherError();
                }
            });
        }catch (IOException ex) {
            Log.e(TAG, "onFailure:: " + ex);
            mView.showWeatherError();
        }

    }

}
