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
    private final RectF additionalRect;
    private final Point additionalPoint;

    public AnnotatedLocationImage(RectF oval, RectF backRect, Point annotationPoint, RectF additionalRect, Point additionalPoint) {
        this.oval = oval;
        this.backRect = backRect;
        this.annotationPoint = annotationPoint;
        this.additionalRect = additionalRect;
        this.additionalPoint = additionalPoint;
    }

    public RectF getOval() {
        return oval;
    }

    public RectF getAnnotationRect() {
        return backRect;
    }

    public Point getAnnotationPoint() {
        return annotationPoint;
    }

    public RectF getAdditionalRect() {
        return additionalRect;
    }

    public Point getAdditionalPoint() {
        return additionalPoint;
    }
}
