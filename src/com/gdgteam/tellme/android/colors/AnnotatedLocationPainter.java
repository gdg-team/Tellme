package com.gdgteam.tellme.android.colors;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author Andrey Pereverzin
 */
public class AnnotatedLocationPainter {
    public void drawAnnotatedLocation(Canvas canvas, AnnotatedLocationImage locationImage, String annotationText, String additionalText, AnnotatedLocationColors colors) {
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

        Paint additionalForegroundPaint = new Paint();
        additionalForegroundPaint.setARGB(annotationForegroungColor.getA(), annotationForegroungColor.getR(), annotationForegroungColor.getG(), annotationForegroungColor.getB());
        additionalForegroundPaint.setAntiAlias(true);
        additionalForegroundPaint.setFakeBoldText(true);

        Paint additionalBackgroundPaint = new Paint();
        additionalBackgroundPaint.setARGB(annotationBackgroundColor.getA(), annotationBackgroundColor.getR(), annotationBackgroundColor.getG(), annotationBackgroundColor.getB());
        additionalBackgroundPaint.setAntiAlias(true);

        canvas.drawOval(locationImage.getOval(), locationPaint);
        canvas.drawRoundRect(locationImage.getAnnotationRect(), 5, 5, annotationBackgroundPaint);
        canvas.drawText(annotationText, locationImage.getAnnotationPoint().x, locationImage.getAnnotationPoint().y, annotationForegroundPaint);
        canvas.drawRoundRect(locationImage.getAdditionalRect(), 5, 5, additionalBackgroundPaint);
        canvas.drawText(additionalText, locationImage.getAdditionalPoint().x, locationImage.getAdditionalPoint().y, additionalForegroundPaint);
    }
}
