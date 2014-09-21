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
    public static void main(String[] args) {
        // TODO task2
        int[] els = new int[] {
            1, 2, 3, 4
        };
        VisitorArrInt[] visitors = new VisitorArrInt[2];
        visitors[0] = new TwoPartEqVisitor(els);
        visitors[1] = new VisitorCalcSum100(els);
        iterateAll(els, visitors);
        System.out.println(visitors[0].getResult());
        System.out.println(visitors[1].getResult());
    }

    public static void iterateAll(int[] els, VisitorArrInt[] visitors) {
        int maxNumInSet = els.length / 2;
        for (int i = 1; i <= maxNumInSet; i++) {
            iterate(els, visitors, i);
        }
    }
    
    private static void iterate(int[] els, VisitorArrInt[] visitors, int numOfKeys) {
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

interface Visitor<T> {
    public void visit(T t);
    public boolean isEnd();
    public String getResult();
}

interface VisitorArrInt extends Visitor<int[]> {
    
}

abstract class SimpleVisitorAbstract implements VisitorArrInt {
    protected int[] inputData;
    protected long sumOfAllElements;
    protected boolean endFlag;
    protected String result;
    
    public SimpleVisitorAbstract(int[] inputData) {
        this.inputData = inputData;
        this.sumOfAllElements = Util.sum(inputData);
    }
    
    @Override
    public boolean isEnd() {
        return endFlag;
    }
    @Override
    public String getResult() {
        return result;
    }
}

class TwoPartEqVisitor extends SimpleVisitorAbstract {
    public TwoPartEqVisitor(int[] inputData) {
        super(inputData);
    }
    
    @Override
    public void visit(int[] set) {
        if (endFlag) return;
        
        long sumSelectedEls = 0;
        for (int elOfSet : set) {
            sumSelectedEls += inputData[elOfSet];
        }
        
        if (sumSelectedEls == sumOfAllElements >> 1) {
            int countOne = 0;
            int countTwo = 0;
            int[] elsOne = new int[set.length];
            int[] elsTwo = new int[inputData.length - set.length];
            for (int i = 0; i < inputData.length; i++) {
                if (Util.contains(set, i)) {
                    elsOne[countOne++] = inputData[i];
                } else {
                    elsTwo[countTwo++] = inputData[i];
                }
            }
            result = Arrays.toString(elsOne) + " - " + Arrays.toString(elsTwo);
            endFlag = true;
        }
    }
}

class VisitorCalcSum100 extends SimpleVisitorAbstract {
    public static final int MAIN_SUM = 100;
    public static final String YES_ANSWER = "yes";
    public static final String NO_ANSWER = "no";
    
    public VisitorCalcSum100(int[] inputData) {
        super(inputData);
        result = NO_ANSWER;
    }
    @Override
    public void visit(int[] set) {
        if (endFlag) return;
        
        long sumSelectedEls = 0;
        for (int elOfSet : set) {
            sumSelectedEls += inputData[elOfSet];
        }
        
        if (sumSelectedEls == MAIN_SUM 
                || (sumOfAllElements - sumSelectedEls) == MAIN_SUM) {
            result = YES_ANSWER;
            endFlag = true;
        }
        
    }
}

class Util {
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