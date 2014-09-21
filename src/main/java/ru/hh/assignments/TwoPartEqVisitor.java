package ru.hh.assignments;

import java.util.Arrays;

public class TwoPartEqVisitor extends SimpleVisitorAbstract {
    public TwoPartEqVisitor(int[] inputData) {
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
