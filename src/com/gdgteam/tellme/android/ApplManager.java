package com.gdgteam.tellme.android;

import com.gdgteam.tellme.android.locationprovider.LocationProvider;

import android.util.Log;

/**
 * 
 * @author Andrey Pereverzin
 */
public class ApplManager {
    private static final String TAG = ApplManager.class.getSimpleName();
    
    private static ApplManager instance = new ApplManager();
    private LocationProvider locationProvider = new LocationProvider();

    private ApplManager() {
        //
    }

    public static ApplManager getInstance() {
        return instance;
    }

    public LocationProvider getLocationProvider() {
        return locationProvider;
    }
}
