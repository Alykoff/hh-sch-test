package ru.hh.assignments.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ru.hh.assignments.task2.SumVisitor;
import ru.hh.assignments.task2.Task2;

public class Task2Test {
    @Test
    public void testWithOneEl() {
        String[] args = new String[] {"1"};
        String result = new Task2().doTask(args);
        String ouput = "\n" + SumVisitor.NO_ANSWER;
        assertEquals(result, ouput);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testSimple() {
        String[] args = "2 4 3 6 5".split(" ");
        String result = new Task2().doTask(args);
        String ouput1 = "4 6 - 2 3 5\n" + SumVisitor.NO_ANSWER;
        String ouput2 = "2 3 5 - 4 6\n" + SumVisitor.NO_ANSWER;
        assertThat(result, anyOf(is(ouput1), is(ouput2)));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBadInputData() {
        String[] args = "12.12 23".split(" ");
        new Task2().doTask(args);
    }
    

    @Test(expected=IllegalArgumentException.class)
    public void testWithoutData() {
        String[] args = new String[] {};
        new Task2().doTask(args);
    }
}
