/**
Баланс весов
Дана конечная последовательность натуральных чисел.
Считая их массами имеющихся в наличии предметов, определить, 
можно ли все эти предметы положить на весы так, чтобы весы 
находились в равновесии. Вывести вариант расположения.
Определить, можно ли из них отобрать какое-то количество предметов 
с суммарным весом 100 (вывести yes или no, в зависимости от результата).

Пример входных данных:
2 4 3 6 5

Пример выходных данных:
2 3 5 - 4 6
no
 */
package ru.hh.assignments;

public class Task2 {
    public static void main(String[] args) {
        doTask(args);
    }
    
    public static void doTask(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }
        int[] els = new int[args.length];
        try {
            for (int i = 0; i < args.length; i++) {
                els[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
        
        VisitorArrInt[] visitors = new VisitorArrInt[2];
        visitors[0] = new TwoPartEqVisitor(els);
        visitors[1] = new VisitorCalcSum100(els);
        iterateAllCombinations(els, visitors);
        System.out.println(visitors[0].getResult());
        System.out.println(visitors[1].getResult());
    }
    
    public static void iterateAllCombinations(int[] els, VisitorArrInt[] visitors) {
        int maxNumInSet = els.length / 2;
        for (int i = 1; i <= maxNumInSet; i++) {
            iterateCombinationWithNEls(els, visitors, i);
        }
    }
    
    private static void iterateCombinationWithNEls(int[] els, VisitorArrInt[] visitors, int numOfKeys) {
        int numOfEls = els.length;
        int[] keys = new int[numOfKeys];
        for (int i = 0; i < numOfKeys; i++) {
            keys[i] = i;
        }
        
        int maxKeyPosition = numOfEls - 1;
        int minKey = 0; 
        int maxKey = numOfKeys - 1;
        int i = maxKey;
        while (i >= minKey) {
            int keyPosition = keys[i];
            
            if (maxKey - i == maxKeyPosition - keyPosition) {
                if (i == minKey) {
                    handleVisitors(visitors, keys);
                    return;
                }
                i--;
                continue;
            }
            
            boolean isEnd = handleVisitors(visitors, keys);
            if (isEnd) {
                return;
            }
            
            keys[i] = keyPosition + 1;
            for (int j = 1; j <= maxKey - i; j++) {
                keys[i + j] = keys[i] + j;
            }
        }
    }
    
    private static boolean handleVisitors(VisitorArrInt[] visitors, int[] keys) {
        boolean isEnd = false;
        for (VisitorArrInt visitor : visitors) {
            visitor.visit(keys);
            isEnd &= visitor.isEnd();
        }
        return isEnd;
    }

}