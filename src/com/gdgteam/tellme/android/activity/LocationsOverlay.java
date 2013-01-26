package com.gdgteam.tellme.android.activity;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Canvas;
import android.location.Location;
import android.util.Log;

import com.gdgteam.tellme.android.colors.AnnotatedLocationColors;
import com.gdgteam.tellme.android.colors.AnnotatedLocationImage;
import com.gdgteam.tellme.android.colors.AnnotatedLocationPainter;
import com.gdgteam.tellme.android.colors.LocationImageFactory;
import com.gdgteam.tellme.android.colors.PaintColor;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

/**
 * 
 * @author Andrey Pereverzin
 */
public class LocationsOverlay extends Overlay {
    private static final String TAG = LocationsOverlay.class.getSimpleName();
    
    private final int maxLocations;
    private AnnotatedLocationColors myColors;
    
    private final LocationImageFactory locationImageFactory = new LocationImageFactory();
    private final AnnotatedLocationPainter painter = new AnnotatedLocationPainter();
    private AnnotatedLocationColors[] predefinedColors;
    private Map<String, Integer> locationIdColors = new HashMap<String, Integer>();
    private Map<String, Location> locations = new HashMap<String, Location>();
    private int colorInd = 0;

    private static final int RADIUS = 5;

    Location myLocation;

    public LocationsOverlay(int maxLocations) {
        this.maxLocations = maxLocations;
        
        generateMyColors();
        generateColors();
    }

    private void generateColors() {
        int maxColor = 255;
        int rColor = maxColor;
        int gColor = maxColor;
        int bColor = maxColor;
        int step = 50;
        
        predefinedColors = new AnnotatedLocationColors[maxLocations];
        
        for(int i = 0; i < maxLocations; i++) {
            if(rColor - step < 0) {
                if(gColor - step < 0) {
                    if(bColor - step < 0) {
                        rColor = maxColor - step;
                        gColor = maxColor;
                        bColor = maxColor;
                    } else {
                        bColor -= step;
                    }
                } else {
                    gColor -= step;
                }
            } else {
                rColor -= step;
            }
            PaintColor locationColor = new PaintColor(250, rColor, gColor, bColor);
            PaintColor annotationForegroundColor = new PaintColor(250, rColor, gColor, bColor);
            PaintColor annotationBackgroundColor = new PaintColor(175, 50, 50, 50);
            predefinedColors[i] = new AnnotatedLocationColors(locationColor, annotationForegroundColor, annotationBackgroundColor);
        }
    }

    private void generateMyColors() {
        PaintColor locationColor = new PaintColor(250, 255, 255, 255);
        PaintColor annotationForegroundColor = new PaintColor(250, 255, 255, 255);
        PaintColor annotationBackgroundColor = new PaintColor(175, 50, 50, 50);
        myColors = new AnnotatedLocationColors(locationColor, annotationForegroundColor, annotationBackgroundColor);
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }
    
    public void addLocation(String id, Location location) {
        if(!locationIdColors.containsKey(id)) {
            if (colorInd == maxLocations - 1) {
                colorInd = 0;
            } else {
                colorInd++;
            }
            locationIdColors.put(id, colorInd);
        }
        
        if(!locations.containsKey(id)) {
            // remove some id if there are too many locations
        }
        locations.put(id, location);
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        if (shadow == false) {
            if(myLocation != null) {
                AnnotatedLocationImage locationImage = locationImageFactory.createAnnotatedLocationImage(mapView, myLocation, RADIUS);
                painter.drawAnnotatedLocation(canvas, locationImage, "Me", myColors);
            }

            for(String id: locationIdColors.keySet()) {
                AnnotatedLocationImage locationImage = locationImageFactory.createAnnotatedLocationImage(mapView, locations.get(id), RADIUS);
                painter.drawAnnotatedLocation(canvas, locationImage, id, predefinedColors[locationIdColors.get(id)]);
            }
        }
        
        super.draw(canvas, mapView, shadow);
    }

    @Override
    public boolean onTap(GeoPoint point, MapView mapView) {
        return false;
    }
}
