package ru.hh.assignments;

public class Util {
    public static double len(Point p1, Point p2) {
        long xLen = (p1.getX() - p2.getX());
        long yLen = (p1.getY() - p2.getY());
        return Math.sqrt(xLen * xLen + yLen * yLen);
    }
    
    public static double min(double[] elements) {
        double minEl = elements[0];
        for (double el : elements) {
            if (el < minEl) minEl = el;
        }
        return minEl;
    }
    
    public static boolean contains(int[] els, int value) {
        for (int el : els) {
            if (el == value) return true;
        }
        return false;
    }
    
    public static long sum(int[] elements) {
        long result = 0;
        for (int el : elements) {
            result += el;
        }
        return result;
    }
}