package ru.hh.assignments;

public abstract class AbstractTaskVisitor implements TaskVisitor {
    protected int[] inputData;
    protected long sumOfAllElements;
    protected boolean endFlag;
    protected String result;
    
    public AbstractTaskVisitor(int[] inputData) {
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