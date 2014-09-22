package ru.hh.assignments;

public class SumVisitor extends AbstractTaskVisitor {
    public static final String YES_ANSWER = "yes";
    public static final String NO_ANSWER = "no";
    public final int searchSum;
    
    public SumVisitor(int[] inputData, int sum) {
        super(inputData);
        searchSum = sum;
        result = NO_ANSWER;
    }
    @Override
    public void visit(int[] set) {
        if (endFlag) return;
        
        long sumSelectedEls = 0;
        for (int elOfSet : set) {
            sumSelectedEls += inputData[elOfSet];
        }
        
        if (sumSelectedEls == searchSum
                || (sumOfAllElements - sumSelectedEls) == searchSum) {
            result = YES_ANSWER;
            endFlag = true;
        }
        
    }
}