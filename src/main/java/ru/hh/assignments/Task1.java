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
package ru.hh.assignments;

public class Task1 {
    private static final int MIN_NUM_OF_INPUT_ELEMENTS = 4;

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
        
        Point[] points = null;
        try {
            points = getPoints(args);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in input date.", e);
        }
        
        double[] lengthsOfSegments = getLengthsOfSegments(points);
        String result = String.valueOf(Util.min(lengthsOfSegments));
        return result.replaceAll("(.)*\\.0$", "$1");
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

    private static double[] getLengthsOfSegments(Point[] points) {
        int numOfPoints = points.length;
        int numOfLengthsOfSegments = numOfPoints * (numOfPoints - 1) / 2;
        double[] lengthsOfSegments = new double[numOfLengthsOfSegments];
        
        int count = 0;
        for (int i = 0; i < numOfPoints; i++) {
            for (int j = i + 1; j < numOfPoints; j++) {
                lengthsOfSegments[count++] = Util.len(points[i], points[j]);
            }
        }
        return lengthsOfSegments;
    }
    
}
