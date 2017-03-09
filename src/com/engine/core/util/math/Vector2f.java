package com.engine.core.util.math;

/**
 * Created by Blake on 3/6/2017.
 */
public class Vector2f {
    public static final Vector2f ZERO = new Vector2f(0, 0);
    public static final Vector2f UP = new Vector2f(0, -1);
    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f normalise() {
        double length = length();
        if (length != 0) {
            x /= length;
            y /= length;
        }
        return this;
    }

    public double getDegree() {
        return Math.toDegrees(Math.atan2(y, x));
    }

    public Vector2f rotate(float angle) {
        double theta = Math.toRadians(angle);
        double cs = Math.cos(theta);
        double sn = Math.sin(theta);
        double x_ = x * cs - y * sn;
        double y_ = x * sn + y * cs;
        return new Vector2f((float) x_, (float) y_);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f vec) {
        return x * vec.x + y * vec.y;
    }

    public Vector2f add(Vector2f vec) {
        return new Vector2f(vec.x + x, vec.y + y);
    }

    public Vector2f add(float x_, float y_) {
        return new Vector2f(x_ + x, y_ + y);
    }

    public Vector2f mul(Vector2f vec) {
        return new Vector2f(x * vec.x, y * vec.y);
    }

    public Vector2f mul(float scalar) {
        return new Vector2f(x * scalar, y * scalar);
    }

    public Vector2f sub(float x_, float y_) {
        return new Vector2f(x - x_, y - y_);
    }

    public Vector2f sub(Vector2f vec) {
        return new Vector2f(x - vec.x, y - vec.y);
    }

    public Vector2f div(Vector2f vec) {
        return new Vector2f(x / vec.x, y / vec.y);
    }


    public Vector2f div(float scalar) {
        return new Vector2f(x / scalar, y / scalar);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "<" + (int) x + ", " + (int) y + ">";
    }

    @Override
    public boolean equals(Object obj) {
        return x == ((Vector2f) obj).x && y == ((Vector2f) obj).y;
    }
}
