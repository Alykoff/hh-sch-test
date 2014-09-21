package ru.hh.assignments;

public class VisitorCalcSum100 extends SimpleVisitorAbstract {
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