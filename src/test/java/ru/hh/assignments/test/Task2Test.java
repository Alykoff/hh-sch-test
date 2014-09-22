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
    public void testWithEqSum() {
        String[] args = new String[] {"1", "2", "3", "99"};
        String result = new Task2().doTask(args);
        String ouput = "\n" + SumVisitor.YES_ANSWER;
        assertEquals(result, ouput);
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testBothPass() {
        String[] args = new String[] {"1", "97", "3", "99"};
        String result = new Task2().doTask(args);
        String output1 = "1 99 - 3 97\n" + SumVisitor.YES_ANSWER;
        String output2 = "99 1 - 3 97\n" + SumVisitor.YES_ANSWER;
        String output3 = "1 99 - 97 3\n" + SumVisitor.YES_ANSWER;
        String output4 = "99 1 - 97 3\n" + SumVisitor.YES_ANSWER;
        assertThat(result, anyOf(is(output1), is(output2), is(output3), is(output4)));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testSimple() {
        String[] args = "2 4 3 6 5".split(" ");
        String result = new Task2().doTask(args);
        String output1 = "4 6 - 2 3 5\n" + SumVisitor.NO_ANSWER;
        String output2 = "6 4 - 2 3 5\n" + SumVisitor.NO_ANSWER;
        
        String output3 = "4 6 - 3 2 5\n" + SumVisitor.NO_ANSWER;
        String output4 = "6 4 - 3 2 5\n" + SumVisitor.NO_ANSWER;
        
        String output5 = "4 6 - 2 5 3\n" + SumVisitor.NO_ANSWER;
        String output6 = "6 4 - 2 5 3\n" + SumVisitor.NO_ANSWER;

        String output7 = "4 6 - 5 3 2\n" + SumVisitor.NO_ANSWER;
        String output8 = "6 4 - 5 3 2\n" + SumVisitor.NO_ANSWER;
        
        String output9 = "4 6 - 3 5 2\n" + SumVisitor.NO_ANSWER;
        String output10 = "6 4 - 3 5 2\n" + SumVisitor.NO_ANSWER;

        String output11 = "4 6 - 5 2 3\n" + SumVisitor.NO_ANSWER;
        String output12 = "6 4 - 5 2 3\n" + SumVisitor.NO_ANSWER;

        String output13 = "2 3 5 - 4 6\n" + SumVisitor.NO_ANSWER;
        String output14 = "2 3 5 - 6 4\n" + SumVisitor.NO_ANSWER;
        
        String output15 = "3 2 5 - 4 6\n" + SumVisitor.NO_ANSWER;
        String output16 = "3 2 5 - 6 4\n" + SumVisitor.NO_ANSWER;
        
        String output17 = "2 5 3 - 4 6\n" + SumVisitor.NO_ANSWER;
        String output18 = "2 5 3 - 6 4\n" + SumVisitor.NO_ANSWER;

        String output19 = "5 3 2 - 4 6\n" + SumVisitor.NO_ANSWER;
        String output20 = "5 3 2 - 6 4\n" + SumVisitor.NO_ANSWER;
        
        String output21 = "3 5 2 - 4 6\n" + SumVisitor.NO_ANSWER;
        String output22 = "3 5 2 - 6 4\n" + SumVisitor.NO_ANSWER;

        String output23 = "5 2 3 - 4 6\n" + SumVisitor.NO_ANSWER;
        String output24 = "5 2 3 - 6 4\n" + SumVisitor.NO_ANSWER;
        
        assertThat(
            result, 
            anyOf(is(output1), is(output2), is(output3), is(output4), 
                is(output5), is(output6), is(output7), is(output8),
                is(output9), is(output10), is(output11), is(output12), 
                is(output13), is(output14), is(output15), is(output16),
                is(output17), is(output18), is(output19), is(output20), 
                is(output21), is(output22), is(output23), is(output24))
        );
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
