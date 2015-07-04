package ru.hh.assignments.task1;

import java.util.*;

/**
 * Улучшенная версия первого задания. O(log(n) * n)
 */
public class Task1V2 {

    public static void main(String[] args) {
        final Random rand = new Random();
        final List<PointD> data = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            data.add(
                    new PointD(rand.nextDouble(), rand.nextDouble())
            );
        }
        Collections.sort(data, (a, b) -> {
            if (a.x > b.x) return 1;
            else if (a.x == b.x && a.y > b.y) return 1;
            else if (a.x == b.x && a.y == b.y) return 0;
            else return -1;
        });

        final long startTime = System.nanoTime();
        // final double result1 = getMinBruteForce(data);
        final long time1 = System.nanoTime();
        final double result2 = getMinLen(data);
        final long time2 = System.nanoTime();
        System.out.println(time1 - startTime);
        System.out.println(time2 - time1);
        System.out.println();

        // System.out.println(result1);
        System.out.println(result2);
    }

    private static double len(PointD p1, PointD p2) {
        return Math.sqrt(
                Math.pow(p1.x - p2.x, 2) +
                        Math.pow(p1.y - p2.y, 2)
        );
    }

    private static double min(PointD p1, PointD p2, PointD p3) {
        double len1 = len(p1, p2);
        double len2 = len(p1, p3);
        double len3 = len(p2, p3);
        return Math.min(
                Math.min(len1, len2), len3
        );
    }

    private static List<PointD> getBound(
            List<PointD> pie1,
            List<PointD> pie2,
            double currentMinLen,
            double midX) {
        int i = 0;
        int j = 0;
        final List<PointD> acc = new ArrayList<>();

        while (true) {
            if (i < pie1.size()) {
                final PointD p1 = pie1.get(i);
                final boolean isP1MoreMin = !isXLessThenMinLen(midX, currentMinLen, p1);
                if (j < pie2.size()) {
                    final PointD p2 = pie2.get(j);
                    final boolean isP2MoreMin = !isXLessThenMinLen(midX, currentMinLen, p2);

                    if (isP1MoreMin && isP2MoreMin) {
                        i++;
                        j++;
                    } else if (isP1MoreMin) {
                        i++;
                    } else if (isP2MoreMin) {
                        j++;
                    } else if (p1.y < p2.y) {
                        i++;
                        acc.add(p1);
                    } else {
                        j++;
                        acc.add(p2);
                    }
                } else {
                    i++;
                    if (!isP1MoreMin) {
                        acc.add(p1);
                    }
                }
            } else {
                if (j < pie2.size()) {
                    final PointD p2 = pie2.get(j);
                    final boolean isP2MoreMin = !isXLessThenMinLen(midX, currentMinLen, p2);

                    j++;
                    if (!isP2MoreMin) {
                        acc.add(p2);
                    }
                } else {
                    return acc;
                }
            }
        }
    }

    private static double getMinLenWithBound(
            List<PointD> bound,
            double minLen) {
        double result = minLen;
        int i = 1;
        while (i < bound.size()) {
            final PointD p1 = bound.get(i);
            int j = 0;
            while (j < i) {
                final PointD p2 = bound.get(j);
                final double length = len(p1, p2);
                if (length < result) {
                    result = length;
                }
                j++;
            }
            i++;
        }
        return result;
    }

    private static boolean isXLessThenMinLen(
            double midX,
            double currentMinLen,
            PointD p) {
        return Math.abs(midX - p.x) < currentMinLen;
    }

    private static double getMinLen(List<PointD> data) {
        final int size = data.size();
        final int mid = size / 2;
        switch (size) {
            case 3:
                return min(data.get(0), data.get(1), data.get(2));
            case 2:
                return len(data.get(0), data.get(1));
            default:
                final double midX = data.get(mid).x;
                final List<PointD> pie1 = data.subList(0, mid);
                final List<PointD> pie2 = data.subList(mid, size);
                final double minLen1 = getMinLen(pie1);
                final double minLen2 = getMinLen(pie2);
                final double minLenWithoutBound = Math.min(minLen1, minLen2);
                final List<PointD> bound = getBound(pie1, pie2, midX, minLenWithoutBound);
                return getMinLenWithBound(bound, minLenWithoutBound);
        }
    }

    private static double getMinBruteForce(List<PointD> data) {
        double minLen = Double.MAX_VALUE;
        int i = 0;
        while (i < data.size()) {
            final PointD p1 = data.get(i);
            int j = 0;
            while (j < i) {
                final PointD p2 = data.get(j);
                final double localLen = len(p1, p2);
                if (localLen < minLen) {
                    minLen = localLen;
                }
                j++;
            }
            i++;
        }
        return minLen;
    }
}

class PointD {
    double x;
    double y;
    public PointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}