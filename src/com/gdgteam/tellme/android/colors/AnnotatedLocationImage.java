package com.gdgteam.tellme.android.colors;

import android.graphics.Point;
import android.graphics.RectF;

/**
 * 
 * @author Andrey Pereverzin
 */
public class AnnotatedLocationImage {
    private final RectF oval;
    private final RectF backRect;
    private final Point annotationPoint;

    public AnnotatedLocationImage(RectF oval, RectF backRect, Point annotationPoint) {
        this.oval = oval;
        this.backRect = backRect;
        this.annotationPoint = annotationPoint;
    }

    public RectF getOval() {
        return oval;
    }

    public RectF getBackRect() {
        return backRect;
    }

    public Point getAnnotationPoint() {
        return annotationPoint;
    }
}
