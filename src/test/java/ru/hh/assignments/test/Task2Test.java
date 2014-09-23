package ru.hh.assignments.test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import ru.hh.assignments.task2.BalanceVisitor;
import ru.hh.assignments.task2.SumVisitor;
import ru.hh.assignments.task2.Task2;

public class Task2Test {
    private static final String NEGATIVE_BALANCE_PATTERN = "^(.*)\n" + SumVisitor.NO_ANSWER + "$";
    
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
    public void testOneElWithPositive() {
        String[] args = new String[] {"100"};
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
    public void testSimple() {
        String[] args = "2 4 3 6 5".split(" ");
        String result = new Task2().doTask(args);
        assertEquals(result.endsWith(SumVisitor.NO_ANSWER), true);
        
        String[] balanceAnswer = "[2, 3, 5] - [4, 6]".split(BalanceVisitor.DELIMITER);
        String[] balance = result.replaceAll(NEGATIVE_BALANCE_PATTERN, "$1")
                    .split(BalanceVisitor.DELIMITER);
        for (int i = 0; i < balance.length; i++) {
            String[] elsBalance = balance[i].split(" ");
            Arrays.sort(elsBalance);
            balance[i] = Arrays.toString(elsBalance);
        }
        Arrays.sort(balance);
        assertArrayEquals(balance, balanceAnswer);
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
