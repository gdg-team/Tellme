package com.gdgteam.tellme.android.colors;

/**
 * 
 * @author Andrey Pereverzin
 */
public class AnnotatedLocationColors {
    private final PaintColor locationColor;
    private final PaintColor annotationForegroundColor;
    private final PaintColor annotationBackgroundColor;
 
    public AnnotatedLocationColors(PaintColor locationColor, PaintColor annotationForegroundColor, PaintColor annotationBackgroundColor) {
        this.locationColor = locationColor;
        this.annotationForegroundColor = annotationForegroundColor;
        this.annotationBackgroundColor = annotationBackgroundColor;
    }

    public PaintColor getLocationColor() {
        return locationColor;
    }

    public PaintColor getAnnotationForegroundColor() {
        return annotationForegroundColor;
    }

    public PaintColor getAnnotationBackgroundColor() {
        return annotationBackgroundColor;
    }
}
