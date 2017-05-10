package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter.WeatherPresenter;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.GPSLocationListener;

/**
 * Created by YMalesa on 2017/05/09.
 */

public class WeatherRepositoryImpl implements WeatherRepository {
    private RequestQueue mRequestQueue;
    private WeatherResponse mWeather = null;
    private WeatherPresenter mCallback;

    public WeatherRepositoryImpl(WeatherPresenter pCallback) {
        mCallback = pCallback;
    }
    @Override
    public void loadWeather(GPSLocationListener pLocation, RequestQueue pRequestQueue) {
        mRequestQueue = pRequestQueue;
        JsonObjectRequest vJsonRequest = new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?lat="+pLocation.getLatitude()+
                        "&lon="+pLocation.getLongitude()
                        +"&appid=351f44cbb248066a357099486d17cab8"

                , new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson vGson = new Gson();

                    mWeather = vGson.fromJson(response.toString(), WeatherResponse.class);

                    mCallback.onWeatherUpdate(mWeather);
                    Log.d("WeatherRepositoryImpl", "loadWeather:: Success " + mWeather.toString());
                }catch (Exception ex) {
                    Log.d("WeatherRepositoryImpl", "loadWeather::" + ex);
                    mCallback.onWeatherUpdate(mWeather);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("WeatherRepositoryImpl", "loadWeather::" + error);
                mCallback.onWeatherUpdate(mWeather);
            }
        });

        mRequestQueue.add(vJsonRequest);
    }

    @Override
    public void loadWeather(String pCity, RequestQueue pRequestQueue) {
        mRequestQueue = pRequestQueue;
        JsonObjectRequest vJsonRequest = new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q="
                        + pCity +"&appid=351f44cbb248066a357099486d17cab8"

                , new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson vGson = new Gson();
                    mWeather = vGson.fromJson(response.toString(), WeatherResponse.class);
                    mCallback.onWeatherUpdate(mWeather);
                    Log.d("WeatherRepositoryImpl", "loadWeather:: Success " + mWeather.toString());
                }catch (Exception ex) {
                    Log.d("WeatherRepositoryImpl", "loadWeather::" + ex);
                    mCallback.onWeatherUpdate(mWeather);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("WeatherRepositoryImpl", "loadWeather::" + error);
                mCallback.onWeatherUpdate(mWeather);
            }
        });

        mRequestQueue.add(vJsonRequest);
    }
}
