/**
  *    Дан набор из N точек на плоскости (для простоты можно считать, 
  *    что у всех точек целочисленные координаты). 
  *    Найдите минимальное расстояние между двумя точками из этого набора.
  *    
  *    Пример входных данных:
  *    10 10
  *    20 10
  *    20 15
  *    
  *    Пример выходных данных:
  *    5
  */
package ru.hh.assignments.task1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import ru.hh.assignments.Util;

public class Task1 implements Serializable {
    private static final long serialVersionUID = -6836187127547323610L;
    private static final int MIN_NUM_OF_INPUT_ELEMENTS = 4;
    private static final String INTEGER_PATTERN = "(.*)\\.0$";
    private static final Comparator<Point> pointComporatorForX = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1 == null || p2 == null) throw new NullPointerException();
            if (p1.getX() > p2.getX()) return 1;
            else if (p1.getX() == p2.getX()) return 0;
            return -1;
        }
    };
    private static final Comparator<Point> pointComporatorForY = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1 == null || p2 == null) throw new NullPointerException();
            if (p1.getY() > p2.getY()) return 1;
            else if (p1.getY() == p2.getY()) return 0;
            return -1;
        }
    };
    
    public static void main(String[] args) {
        String minEl = doTask(args);
        System.out.println(minEl);
    }
    
    public static String doTask(String[] args) {
        if (args.length < MIN_NUM_OF_INPUT_ELEMENTS) {
            throw new IllegalArgumentException("Number of arguments can't be less than 4.");
        }
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("The number of arguments must be even.");
        }
        
        try {
            points = Arrays.asList(getPoints(args));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in input date.", e);
        }
        
        Collections.sort(points, pointComporatorForX);
        search(0, points.size() - 1);
        
//        double[] lengthsOfSegments = getLengthsOfSegments(points);
        String result = String.valueOf(Math.sqrt(minSq));
        return result.replaceAll(INTEGER_PATTERN, "$1");
    }
    
    private static double minSq = Double.MAX_VALUE;
    private static List<Point> points;
    
    private static void checkMinLen(int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                double len = Util.sqLen(points.get(i), points.get(j));
                if (len < minSq) minSq = len;
            }
        }
    }
    
    private static void search(int lo, int hi) {
        if (hi - lo <= 3) {
            checkMinLen(lo, hi);
            for (int i = lo; i <= hi; i++) {
                for (int j = i + 1; j <= hi; j++) {
                    double len = Util.sqLen(points.get(i), points.get(j));
                    if (len < minSq) minSq = len;
                }
            }
            return;
        }
        int mid = (hi - lo) >> 1;
        Point midPoint = points.get(mid);
        search(lo, mid);
        search(mid + 1, hi);
        
        TreeSet<Point> clP = new TreeSet<Point>(pointComporatorForY);
        for (int i = lo; i >= 0; i--) {
            long xDist = midPoint.getX() - points.get(i).getX();
            if (xDist < mid) {
//                long yDist = midPoint.getY() - points.get(i).getY();
                clP.add(points.get(i));
            } else {
                break;
            }
        }
        
        for (int i = mid + 1; i <= hi; i++) {
            if (points.get(i).getX() - midPoint.getX() < mid) {
                clP.add(points.get(i));
            } else {
                break;
            }
        }
        
        for (Point point : clP) {
            double len = Util.sqLen(midPoint, point);
            if (len < minSq) minSq = len;
        }
    }
    
    private static Point[] getPoints(String[] args) throws NumberFormatException {
        Point[] result = new Point[args.length / 2];

        for (int i = 0; i < args.length; i += 2) {
            long xEl = Long.parseLong(args[i]);
            long yEl = Long.parseLong(args[i + 1]);
            result[i / 2] = new Point(xEl, yEl);
        }
        return result;
    }

}
