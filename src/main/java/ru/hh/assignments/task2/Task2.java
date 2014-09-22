/**
 *    Баланс весов
 *    Дана конечная последовательность натуральных чисел.
 *    Считая их массами имеющихся в наличии предметов, определить, 
 *    можно ли все эти предметы положить на весы так, чтобы весы 
 *    находились в равновесии. Вывести вариант расположения.
 *    Определить, можно ли из них отобрать какое-то количество предметов 
 *    с суммарным весом 100 (вывести yes или no, в зависимости от результата).
 *    
 *    Пример входных данных:
 *    2 4 3 6 5
 *    
 *    Пример выходных данных:
 *    2 3 5 - 4 6
 *    no
 */
package ru.hh.assignments.task2;

import java.io.Serializable;

public class Task2 implements Serializable {
    private static final long serialVersionUID = -8679609024840591334L;
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

    protected void iterateCombinationWithNEls(int[] elements,
            TaskVisitor[] visitors, int numOfPointers) {
        int numOfEls = elements.length;
        int[] selectedEls = new int[numOfPointers];
        for (int i = 0; i < numOfPointers; i++) {
            selectedEls[i] = i;
        }

        final int maxPosition = numOfEls - 1;
        final int minPointer = 0;
        final int maxPointer = numOfPointers - 1;
        int pointer = maxPointer;
        while (pointer >= minPointer) {
            int currentPosition = selectedEls[pointer];

            if (maxPointer - pointer == maxPosition - currentPosition) {
                if (pointer == minPointer) {
                    handleVisitors(visitors, selectedEls);
                    return;
                }
                pointer--;
                continue;
            }

            handleVisitors(visitors, selectedEls);
            if (isEndForVisitors(visitors)) {
                return;
            }

            selectedEls[pointer] = currentPosition + 1;
            for (int j = 1; j <= maxPointer - pointer; j++) {
                selectedEls[pointer + j] = selectedEls[pointer] + j;
            }
            pointer = maxPointer;
        }
    }

    protected void handleVisitors(TaskVisitor[] visitors, int[] pointers) {
        for (TaskVisitor visitor : visitors) {
            visitor.visit(pointers);
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