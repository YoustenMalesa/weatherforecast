package za.co.dvt.weatherforecast.za.co.dvt.weatherforecast.gpslocation;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class GPSLocationListener implements LocationListener {
    private double mLatitude;
    private double mLongitude;
    private OnLocationUpdateListener mLocationUpdateListener;

    public GPSLocationListener(OnLocationUpdateListener pOnLocationListener) {
        mLocationUpdateListener = pOnLocationListener;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null) {
            Log.d("GPSLocationListener", "onLocationChanged::Latitude: " + location.getLatitude());
            Log.d("GPSLocationListener", "onLocationChanged::Longitude: " + location.getLongitude());

            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();

            mLocationUpdateListener.onUpdate();

        } else {
            Log.d("GPSLocationListener", "No location available");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }
}
