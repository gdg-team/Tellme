package com.gdgteam.tellme.android.colors;

import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;

/**
 * 
 * @author Andrey Pereverzin
 */
public class LocationImageFactory {

    public AnnotatedLocationImage createAnnotatedLocationImage(MapView mapView, Location location, int radius) {
        Projection projection = mapView.getProjection();
        Double latitude = location.getLatitude() * 1E6;
        Double longitude = location.getLongitude() * 1E6;
        GeoPoint geoPoint;
        geoPoint = new GeoPoint(latitude.intValue(), longitude.intValue());

        // Convert the location to screen pixels
        Point point = new Point();
        projection.toPixels(geoPoint, point);
        
        RectF oval = new RectF(point.x - radius, point.y - radius, point.x + radius, point.y + radius);

        RectF backRect = new RectF(point.x + 2 + radius, point.y - 3 * radius, point.x + 65, point.y + radius);
        
        Point annotationPoint = new Point(point.x + 2 * radius, point.y);
        
        return new AnnotatedLocationImage(oval, backRect, annotationPoint);
   }
}
