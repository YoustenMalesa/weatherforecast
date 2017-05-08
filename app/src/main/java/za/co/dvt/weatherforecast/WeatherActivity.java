package za.co.dvt.weatherforecast;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.gpslocation.GPSLocationListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.gpslocation.OnLocationUpdateListener;
import za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.util.DegreesConverter;

public class WeatherActivity extends AppCompatActivity implements OnLocationUpdateListener {

    private SearchView mSearchView;
    private TextView txtDate, txtMinTemp, txtMaxTemp, txtLocation, txtDescription;
    private ImageView imgWeatherIcon;
    private ProgressBar pgProgress;

    private LocationManager mLocationManager;
    private static GPSLocationListener mLocationListener;

    private static final String[] FINE_LOCATION_PERMISSION = {Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int FINE_LOATION_REQUEST = 1337;

    private RequestQueue mRequestQueue;
    private static final String OPEN_WEATHER_IMG_URL = "http://openweathermap.org/img/w/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initComponents();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        requestPermissions(FINE_LOCATION_PERMISSION, FINE_LOATION_REQUEST);
        pgProgress.setVisibility(View.VISIBLE);

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Access to your location denied. Please use the search feature", Toast.LENGTH_LONG).show();
                return;
            }

            mLocationListener = new GPSLocationListener(this);
            mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(new Criteria(), false),
                    1000 * 60, 50, mLocationListener);

        } catch (SecurityException ex) {
            Toast.makeText(getApplicationContext(), "Failed to obtain current location. Allow permissions.", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onStop() {
        mLocationManager.removeUpdates(mLocationListener);
        super.onStop();
    }

    @Override
    protected void onResume() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Access to your location denied. Please use the search feature", Toast.LENGTH_LONG).show();
            return;
        }
        mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(new Criteria(), false),
                1000 * 60, 50, mLocationListener);
        super.onResume();
    }

    public void initComponents() {
        txtDate     = (TextView)findViewById(R.id.txtDate);
        txtMinTemp  =  (TextView)findViewById(R.id.txtMinTemp);
        txtMaxTemp  =  (TextView)findViewById(R.id.txtMaxTemp);
        txtLocation = (TextView)findViewById(R.id.txtLocation);
        txtDescription = (TextView)findViewById(R.id.txtDescription);
        imgWeatherIcon = (ImageView)findViewById(R.id.imgWeatherIcon);
        pgProgress = (ProgressBar)findViewById(R.id.pgbProgress);
    }

    public void setData(String pMinTem, String pMaxTemp, String pLocation, String pWeatherIcon, String pDate, String pDescription) {
        txtMaxTemp.setText("max " + DegreesConverter.toCelcius(Double.parseDouble(pMaxTemp)) + " °C");
        txtMinTemp.setText("min " + DegreesConverter.toCelcius(Double.parseDouble(pMinTem)) + " °C");
        txtLocation.setText(pLocation);
        txtDate.setText(pDate);
        txtDescription.setText(pDescription);
        Picasso.with(getApplicationContext()).load(OPEN_WEATHER_IMG_URL + pWeatherIcon + ".png").into(imgWeatherIcon);
    }

    public void getData(JSONObject response) throws JSONException {
        JSONObject vWeather = response.getJSONArray("weather").getJSONObject(0);
        JSONObject vMain = response.getJSONObject("main");
        JSONObject vSys = response.getJSONObject("sys");

        String vIcon = vWeather.getString("icon");
        String vDescription = vWeather.getString("description");
        String vMinTemp = vMain.getString("temp_min");
        String vMaxTemp = vMain.getString("temp_max");
        String vCountry = vSys.getString("country");
        String vCity = response.getString("name");

        String vLocation = vCity.toUpperCase() +", "+ vCountry.toUpperCase();

        DateFormat vFormatter = DateFormat.getDateInstance();
        String vDate = vFormatter.format(new Date());

        setData(vMinTemp, vMaxTemp, vLocation, vIcon, vDate, vDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        pgProgress.setVisibility(View.VISIBLE);
        getMenuInflater().inflate(R.menu.menu_search, menu);

        mSearchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                JsonObjectRequest vJsonRequest = new JsonObjectRequest(Request.Method.GET,
                        "http://api.openweathermap.org/data/2.5/weather?q=" + query + "&appid=351f44cbb248066a357099486d17cab8",
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            pgProgress.setVisibility(View.INVISIBLE);
                            getData(response);
                        }catch (JSONException ex) {
                            pgProgress.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "OOPS! An error occured.", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pgProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "API call failed", Toast.LENGTH_LONG).show();
                    }
                });

                mRequestQueue.add(vJsonRequest);
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
    public void onUpdate() {
        JsonObjectRequest vJsonRequest = new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?lat="+mLocationListener.getLatitude()+
                        "&lon="+mLocationListener.getLongitude()
                        +"&appid=351f44cbb248066a357099486d17cab8"

                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pgProgress.setVisibility(View.INVISIBLE);
                    getData(response);
                }catch (JSONException ex) {
                    pgProgress.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "OOPS! An error occured.", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pgProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "API call failed", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(vJsonRequest);

    }
}
