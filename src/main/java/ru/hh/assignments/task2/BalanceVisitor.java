package ru.hh.assignments.task2;

import ru.hh.assignments.Util;

public class BalanceVisitor extends AbstractTaskVisitor {
    private static final long serialVersionUID = -3014303396240828234L;
    public static final String DELIMITER = " - ";

    public BalanceVisitor(int[] inputData) {
        super(inputData);
        result = "";
        // невозможно разделить исходное множество (состоящих из натуральных чисел)
        // на два множества равных по сумме, если
        // сумма всех элементов нечетная.
        if (sumOfAllElements % 2 != 0) {
            endFlag = true;
        }
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
            result = Util.arrToString(elsOne) + DELIMITER + Util.arrToString(elsTwo);
            endFlag = true;
        }
    }
}
