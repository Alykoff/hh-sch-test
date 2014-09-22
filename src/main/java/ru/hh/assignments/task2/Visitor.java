package ru.hh.assignments.task2;

public interface Visitor<T> {
    public void visit(T t);
    public boolean isEnd();
    public String getResult();
}
