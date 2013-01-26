package com.gdgteam.tellme.android.activity;

import java.util.List;

import android.accounts.Account;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gdgteam.tellme.R;
import com.gdgteam.tellme.android.ApplManager;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

/**
 * 
 * @author Andrey Pereverzin
 */
public class MainMapActivity extends MapActivity {
    private static final String TAG = MainMapActivity.class.getSimpleName();

    private static final int EMAIL_ACCOUNT_MENU_ITEM = Menu.FIRST;
    private static final int CONTACTS_TO_TRACK_MENU_ITEM = Menu.FIRST + 1;
    private static final int CONTACTS_TO_INFORM_MENU_ITEM = Menu.FIRST + 2;

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    private MapController mapController;

    private LocationsOverlay locationsOverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map);
        MapView mapView = (MapView) findViewById(R.id.mapView);
        mapController = mapView.getController();
        mapView.setSatellite(false);
        mapView.setBuiltInZoomControls(true);
        mapController.setZoom(17);

        locationsOverlay = new LocationsOverlay(10);
        List<Overlay> overlays = mapView.getOverlays();
        overlays.add(locationsOverlay);
        mapView.postInvalidate();
        
        emulateLocations();
        
        Location l = ApplManager.getInstance().getLocationProvider().getLocation(this, locationListener);
//        Account googleAccount = ApplManager.getInstance().getAccountProvider().getGoogleAccount(this);

        updateWithNewLocation(l);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        Log.i(TAG, "onCreateOptionsMenu() called");
//
//        menu.add(0, EMAIL_ACCOUNT_MENU_ITEM, 0, R.string.edit_email_account);
//        menu.add(0, CONTACTS_TO_TRACK_MENU_ITEM, 0, R.string.to_track);
//        menu.add(0, CONTACTS_TO_INFORM_MENU_ITEM, 0, R.string.to_inform);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent intent;
//        switch (item.getItemId()) {
//        case EMAIL_ACCOUNT_MENU_ITEM:
//            intent = new Intent(MainMapActivity.this, EmailAccountEditActivity.class);
//            startActivity(intent);
//            return true;
//        case CONTACTS_TO_TRACK_MENU_ITEM:
//            intent = new Intent(MainMapActivity.this, EmailAccountEditActivity.class);
//            startActivity(intent);
//            return true;
//        case CONTACTS_TO_INFORM_MENU_ITEM:
//            intent = new Intent(MainMapActivity.this, EmailAccountEditActivity.class);
//            startActivity(intent);
//            return true;
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }

    private void updateWithNewLocation(Location location) {
        if (location != null) {
            locationsOverlay.setMyLocation(location);
            Double geoLat = location.getLatitude() * 1E6;
            Double geoLng = location.getLongitude() * 1E6;
            GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng.intValue());
            mapController.animateTo(point);
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private final LocationListener dummyLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private void emulateLocations() {
        Location l = ApplManager.getInstance().getLocationProvider().getLocation(this, dummyLocationListener);
        l.setLatitude(51.488224);
        l.setLongitude(-0.054932);
        locationsOverlay.addLocation("@abcd", l);
    }
}
