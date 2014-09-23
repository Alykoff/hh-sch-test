package ru.hh.assignments.task2;

import java.io.Serializable;

import ru.hh.assignments.Util;

public abstract class AbstractTaskVisitor implements TaskVisitor, Serializable {
    private static final long serialVersionUID = 1330171707488331489L;

    protected int[] inputData;
    protected long sumOfAllElements;
    protected boolean endFlag;
    protected String result;
    
    public AbstractTaskVisitor(int[] inputData) {
        this.inputData = inputData;
        this.sumOfAllElements = Util.sum(inputData);
    }
    
    public boolean isEnd() {
        return endFlag;
    }
    
    public String getResult() {
        return result;
    }
}