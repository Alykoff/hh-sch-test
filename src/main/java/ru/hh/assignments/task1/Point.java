package ru.hh.assignments.task1;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = 1832418215504598793L;
    
    private final long x, y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "Point(" + x + "; " + y + ")";
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}