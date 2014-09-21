package ru.hh.assignments;

public interface Visitor<T> {
    public void visit(T t);
    public boolean isEnd();
    public String getResult();
}
