package org.example.model;

import java.util.Locale;

public class Point {
    private final float x;
    private final float y;
    private final float r;
    private final String status;
    private final String time;
    private final long runtime;

    public Point(float x, float y, float r, String time, long runtime, String status) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.time = time;
        this.runtime = runtime;
        this.status = status;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public String getTime() {
        return time;
    }

    public long getRuntime() {
        return runtime;
    }

    public String getStatus() {
        return status;
    }

    public String toJSON(){
        return String.format(Locale.US,"{\"x\": %.3f, \"y\": %.3f, \"r\": %.3f, \"status\": \"%s\", \"time\": \"%s\", \"runtime\": %d}",x, y, r, status, time, runtime);
    }
}