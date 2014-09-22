package ru.hh.assignments.task2;

import java.util.Arrays;

import ru.hh.assignments.Util;

public class BalanceVisitor extends AbstractTaskVisitor {
    public BalanceVisitor(int[] inputData) {
        super(inputData);
        result = "";
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