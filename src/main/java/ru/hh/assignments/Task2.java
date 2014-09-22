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

import java.util.Arrays;

public class Task2 {
    public static final int SEARCH_SUM = 100;
    
    public static void main(String[] args) {
        System.out.println(new Task2().doTask(args));
    }
    
    public String doTask(String[] args) {
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
        
        TaskVisitor[] visitors = new TaskVisitor[2];
        visitors[0] = new BalanceVisitor(els);
        visitors[1] = new SumVisitor(els, SEARCH_SUM);
        iterateAllCombinations(els, visitors);
        return visitors[0].getResult() + "\n" + visitors[1].getResult();
    }
    
    protected void iterateAllCombinations(int[] els, TaskVisitor[] visitors) {
        int maxNumInSet = els.length / 2;
        for (int i = 1; i <= maxNumInSet; i++) {
            iterateCombinationWithNEls(els, visitors, i);
        }
    }
    
    protected void iterateCombinationWithNEls(int[] els, TaskVisitor[] visitors, int numOfKeys) {
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
            handleVisitors(visitors, keys);
            if (isEndForVisitors(visitors)) {
                return;
            }
            
            keys[i] = keyPosition + 1;
            for (int j = 1; j <= maxKey - i; j++) {
                keys[i + j] = keys[i] + j;
            }
            i = maxKey;
        }
    }
    
    protected void handleVisitors(TaskVisitor[] visitors, int[] keys) {
        for (TaskVisitor visitor : visitors) {
            visitor.visit(keys);
        }
    }
    
    protected boolean isEndForVisitors(TaskVisitor[] visitors) {
        for (TaskVisitor visitor : visitors) {
            if (!visitor.isEnd()) {
                return false;
            }
        }
        return true;
    }

}