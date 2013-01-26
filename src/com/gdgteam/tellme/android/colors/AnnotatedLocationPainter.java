package com.gdgteam.tellme.android.colors;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author Andrey Pereverzin
 */
public class AnnotatedLocationPainter {
    public void drawAnnotatedLocation(Canvas canvas, AnnotatedLocationImage locationImage, String annotationText, AnnotatedLocationColors colors) {
        PaintColor locationColor = colors.getLocationColor();
        PaintColor annotationForegroungColor = colors.getAnnotationForegroundColor();
        PaintColor annotationBackgroundColor = colors.getAnnotationBackgroundColor();

        Paint locationPaint = new Paint();
        locationPaint.setARGB(locationColor.getA(), locationColor.getR(), locationColor.getG(), locationColor.getB());
        locationPaint.setAntiAlias(true);
        locationPaint.setFakeBoldText(true);

        Paint annotationForegroundPaint = new Paint();
        annotationForegroundPaint.setARGB(annotationForegroungColor.getA(), annotationForegroungColor.getR(), annotationForegroungColor.getG(), annotationForegroungColor.getB());
        annotationForegroundPaint.setAntiAlias(true);
        annotationForegroundPaint.setFakeBoldText(true);

        Paint annotationBackgroundPaint = new Paint();
        annotationBackgroundPaint.setARGB(annotationBackgroundColor.getA(), annotationBackgroundColor.getR(), annotationBackgroundColor.getG(), annotationBackgroundColor.getB());
        annotationBackgroundPaint.setAntiAlias(true);

        canvas.drawOval(locationImage.getOval(), locationPaint);
        canvas.drawRoundRect(locationImage.getBackRect(), 5, 5, annotationBackgroundPaint);
        canvas.drawText(annotationText, locationImage.getAnnotationPoint().x, locationImage.getAnnotationPoint().y, annotationForegroundPaint);
    }
}
