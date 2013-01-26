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

    public AnnotatedLocationImage createAnnotatedLocationImage(MapView mapView, Location location, int radius, float rectLength) {
        Projection projection = mapView.getProjection();
        Double latitude = location.getLatitude() * 1E6;
        Double longitude = location.getLongitude() * 1E6;
        GeoPoint geoPoint;
        geoPoint = new GeoPoint(latitude.intValue(), longitude.intValue());

        // Convert the myLocation to screen pixels
        Point point = new Point();
        projection.toPixels(geoPoint, point);
        
        RectF oval = new RectF(point.x - radius, point.y - radius, point.x + radius, point.y + radius);

        RectF annotationBackRect = new RectF(point.x + 2 + radius, point.y - 3 * radius, point.x - (2 + radius) + rectLength, point.y + radius);
        
        Point annotationPoint = new Point(point.x + 2 * radius, point.y);
        
        RectF additionalBackRect = new RectF(point.x + 2 + radius, point.y + 2 * radius, point.x - (2 + radius) + rectLength * 2, point.y + 6 * radius);
        
        Point additionalPoint = new Point(point.x + 2 * radius, point.y + radius * 5);
        
        return new AnnotatedLocationImage(oval, annotationBackRect, annotationPoint, additionalBackRect, additionalPoint);
   }
}
