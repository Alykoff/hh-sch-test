package ru.hh.assignments.task2;

public class SumVisitor extends AbstractTaskVisitor {
    private static final long serialVersionUID = 5260600030736418679L;
    public static final String YES_ANSWER = "yes";
    public static final String NO_ANSWER = "no";

    public final int searchSum;
    
    public SumVisitor(int[] inputData, int sum) {
        super(inputData);
        searchSum = sum;
        result = NO_ANSWER;
    }
    
    public void visit(int[] set) {
        if (endFlag) return;
        
        long sumSelectedEls = 0;
        for (int elOfSet : set) {
            // Если элемент X превышает
            // заданное число М, то его сумма с другими натуральными
            // числами заведомо превышает число M.
            if (inputData[elOfSet] > searchSum) return;
            sumSelectedEls += inputData[elOfSet];
        }
        if (sumSelectedEls == searchSum
                || (sumOfAllElements - sumSelectedEls) == searchSum) {
            result = YES_ANSWER;
            endFlag = true;
        }
    }
}