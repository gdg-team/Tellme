package com.gdgteam.tellme.android.activity;

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
    
    private LocationImageFactory locationImageFactory = new LocationImageFactory();
    private AnnotatedLocationPainter painter = new AnnotatedLocationPainter();

    private static final int RADIUS = 5;

    Location location;

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {

        if (shadow == false && location != null) {
            AnnotatedLocationImage locationImage = locationImageFactory.createAnnotatedLocationImage(mapView, location, RADIUS);
            PaintColor locationColor = new PaintColor(250, 255, 255, 255);
            PaintColor annotationForegroundColor = new PaintColor(250, 255, 255, 255);
            PaintColor annotationBackgroundColor = new PaintColor(175, 50, 50, 50);
            AnnotatedLocationColors colors = new AnnotatedLocationColors(locationColor, annotationForegroundColor, annotationBackgroundColor);
            painter.drawAnnotatedLocation(canvas, locationImage, "Here I Am", colors);
        }
        
        super.draw(canvas, mapView, shadow);
    }

    @Override
    public boolean onTap(GeoPoint point, MapView mapView) {
        return false;
    }
}
