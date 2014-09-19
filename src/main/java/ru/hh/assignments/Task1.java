/**
 Дан набор из N точек на плоскости (для простоты можно считать, 
 что у всех точек целочисленные координаты). 
 Найдите минимальное расстояние между двумя точками из этого набора.
 
 Пример входных данных:
 10 10
 20 10
 20 15
 
 Пример выходных данных:
 5
 */
package ru.hh.assignments;

import java.io.Serializable;
import java.util.Arrays;

public class Task1 {
    private static final int MIN_NUMBER_OF_INPUT_ELEMENTS = 4;

    public static void main(String[] args) {
        meth1(args);
    }

    private static Point[] getPoints(String[] args)
            throws NumberFormatException {
        Point[] result = new Point[args.length / 2];

        for (int i = 0; i < args.length; i += 2) {
            long xEl = Long.parseLong(args[i]);
            long yEl = Long.parseLong(args[i + 1]);
            result[i / 2] = new Point(xEl, yEl);
        }
        return result;
    }

    private static double meth1(String[] args) {
        if (args.length < MIN_NUMBER_OF_INPUT_ELEMENTS) {
            throw new IllegalArgumentException();
        }
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException();
        }

        Point[] points = null;
        try {
            points = getPoints(args);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        int len = points.length;
        double[] lengths = new double[len * (len - 1) / 2];
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                lengths[count++] = len(points[i], points[j]);
            }
        }

        Arrays.sort(lengths);
        System.out.println(Arrays.toString(lengths));
        return lengths[0];
    }

    private static double len(Point p1, Point p2) {
        long xLen = (p1.getX() - p2.getX());
        long yLen = (p1.getY() - p2.getY());
        return Math.sqrt(xLen * xLen + yLen * yLen);
    }
}

class Point implements Serializable {
    private static final long serialVersionUID = 1832418215504598793L;
    
    private final long x, y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public String toString() {
        return "Point(" + x + "; " + y + ")";
    }
}
