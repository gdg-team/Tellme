package com.gdgteam.tellme.android.locationprovider;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

/**
 * 
 * @author Andrey Pereverzin
 */
public class  LocationProvider {
    private static final String TAG = LocationProvider.class.getSimpleName();

    private String bestProvider;
    private boolean bestProviderDefined = false;
    private LocationManager locationManager;
    
    public Location getLocation(Context context, LocationListener locationListener) {
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        if(!bestProviderDefined) {
            defineBestProvider(context);
        }
        
        Log.d(TAG, "----- " + bestProvider);
        Location l = locationManager.getLastKnownLocation(bestProvider);
        
        locationManager.requestLocationUpdates(bestProvider, 2000, 10, locationListener);

        return l;
    }
    
    private void defineBestProvider(Context context) {
        Criteria criteria = createCriteria();
        
        bestProvider = locationManager.getBestProvider(criteria, true);
        
        bestProviderDefined = true;
    }
    
    private Criteria createCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        return criteria;
    }
}
