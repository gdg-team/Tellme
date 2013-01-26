package com.gdgteam.tellme.android.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gdgteam.tellme.R;
import com.gdgteam.tellme.android.ApplManager;
import com.gdgteam.tellme.android.twitter.Twitter;
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

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private MapController mapController;
	private LocationsOverlay locationsOverlay;
	private Dialog tweetDialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map);
		MapView mapView = (MapView) findViewById(R.id.mapView);
		if(mapView == null) {
			Log.w("gdg-team", "MapView is null - check API key");
			return;
		}
		mapController = mapView.getController();
		mapView.setSatellite(false);
		mapView.setBuiltInZoomControls(true);
		mapController.setZoom(17);

		locationsOverlay = new LocationsOverlay(this, 10);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.add(locationsOverlay);
		mapView.postInvalidate();

		emulateLocations();

		Location l = ApplManager.getInstance().getLocationProvider().getLocation(this, locationListener);

		updateWithNewLocation(l);
	}

	public void showHideSearchControls(View view) {
		View searchLayout = findViewById(R.id.searchLayout);
		int visibility = searchLayout.getVisibility();
		if(visibility == View.VISIBLE) searchLayout.setVisibility(View.GONE);
		else searchLayout.setVisibility(View.VISIBLE);
	}

	public void addTweetButtonPressed(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.tweet_dialog, null));
		tweetDialog = builder.create();
		tweetDialog.show();
	}

	public void cancelButtonPressed(View view) {
		tweetDialog.dismiss();
	}

	public void tweetButtonPressed(View view) {
		EditText tweetTextEntry =
				(EditText) tweetDialog.findViewById(R.id.tweetTextEntry);
		final String text = tweetTextEntry.getText().toString();
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					Twitter.tweet(text, "51.0", "1.0");
				} catch(Exception e) {
					Log.w("gdg-team", e);
				}
				return null;
			}
		}.execute();
		tweetDialog.dismiss();
		Location l = ApplManager.getInstance().getLocationProvider().getLocation(this, locationListener);		
		locationsOverlay.addLocation("me", l, text);
	}

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
        Location l1 = ApplManager.getInstance().getLocationProvider().getLocation(this, dummyLocationListener);
        if(l1 == null) {
            Log.w("gdg-team", "Location is null - check API key");
            return;
        }
        l1.setLatitude(51.521255);
        l1.setLongitude(-0.089285);
        locationsOverlay.addLocation("@sorhed", l1, "Just got here");
        Location l2 = ApplManager.getInstance().getLocationProvider().getLocation(this, dummyLocationListener);
        if(l2 == null) {
            Log.w("gdg-team", "Location is null - check API key");
            return;
        }
        l2.setLatitude(51.519973);
        l2.setLongitude(-0.083814);
        locationsOverlay.addLocation("@kuklev", l2, "Nice weather");
    }
}
