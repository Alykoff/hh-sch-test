package ru.hh.assignments.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.hh.assignments.SumVisitor;
import ru.hh.assignments.Task2;

public class Task2Test {

    @Test
    public void testWithOneEl() {
        String[] args = new String[] {"1"};
        String result = new Task2().doTask(args);
        String ouput = "\n" + SumVisitor.NO_ANSWER;
        assertEquals(result, ouput);
    }
    
    @Test
    public void testSimple() {
        String[] args = "2 4 3 6 5".split(" ");
        String result = new Task2().doTask(args);
        String ouput = "[2, 3, 5] - [4, 6]\n" + SumVisitor.NO_ANSWER;
        assertEquals(result, ouput);
    }

}
