package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import za.co.dvt.weatherforecast.R;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.GPSLocationListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.model.dto.WeatherResponse;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter.WeatherPresenter;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.presenter.WeatherPresenterImpl;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.DegreesConverter;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    SearchView mSearchView;
    @Bind(R.id.txtDate)TextView txtDate;
    @Bind(R.id.txtMinTemp)TextView txtMinTemp;
    @Bind(R.id.txtMaxTemp) TextView txtMaxTemp;
    @Bind(R.id.txtLocation)TextView txtLocation;
    @Bind(R.id.txtDescription)TextView txtDescription;
    @Bind(R.id.imgWeatherIcon) ImageView imgWeatherIcon;
    @Bind(R.id.pgbProgress) ProgressBar pgProgress;

    private LocationManager mLocationManager;
    private static GPSLocationListener mLocationListener;

    private static final String[] FINE_LOCATION_PERMISSION = {Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int FINE_LOATION_REQUEST = 1337;

    private RequestQueue mRequestQueue;
    private static final String OPEN_WEATHER_IMG_URL = "http://openweathermap.org/img/w/";

    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        mWeatherPresenter = new WeatherPresenterImpl(this);
        mLocationListener = new GPSLocationListener(this);
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        requestPermissions(FINE_LOCATION_PERMISSION, FINE_LOATION_REQUEST);
        pgProgress.setVisibility(View.VISIBLE);

        try {
            if(!checkIsPermissionGranted()) return;
            getCurrentLocation();

        } catch (SecurityException ex) {
            Toast.makeText(getApplicationContext(), "Failed to obtain current location. Allow permissions.", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationManager.removeUpdates(mLocationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!checkIsPermissionGranted()) return;
        getCurrentLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        pgProgress.setVisibility(View.VISIBLE);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mSearchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mWeatherPresenter.loadWeather(query, mRequestQueue);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onLocationUpdate(GPSLocationListener pLocation) {
       mWeatherPresenter.loadWeather(pLocation, mRequestQueue);

    }

    @Override
    public void showWeatherError() {
        pgProgress.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "Failed to load weather", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWeather(WeatherResponse pWeather) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        pgProgress.setVisibility(View.INVISIBLE);

        txtMaxTemp.setText("max " + DegreesConverter.toCelcius(Double.parseDouble(pWeather.getMain().getTemp_max())) + " °C");
        txtMinTemp.setText("min " + DegreesConverter.toCelcius(Double.parseDouble(pWeather.getMain().getTemp_min())) + " °C");
        txtLocation.setText(pWeather.getName() +", "+  pWeather.getSys().getCountry());
        txtDate.setText(dateFormat.format(new Date()));
        txtDescription.setText(pWeather.getWeather()[0].getDescription());
        Picasso.with(getApplicationContext()).load(OPEN_WEATHER_IMG_URL + pWeather.getWeather()[0].getIcon() + ".png").into(imgWeatherIcon);
    }

    @Override
    public void getCurrentLocation() throws SecurityException{
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(new Criteria(), false),
                1000 * 60, 50, mLocationListener);

    }

    @Override
    public boolean checkIsPermissionGranted() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }
}
