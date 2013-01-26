package com.gdgteam.tellme.android.colors;

/**
 * 
 * @author Andrey Pereverzin
 */
public class PaintColor {
    private final int a;
    private final int r;
    private final int g;
    private final int b;
    
    public PaintColor(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
