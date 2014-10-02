package ru.hh.assignments.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.hh.assignments.task1.Task1;

public class Task1Test {
    
    private String[] rand100Points;
    
    @Before
    public void beforeTest() {
        Random rand = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            long x = rand.nextInt(100_000);
            long y = rand.nextInt(100_000);
            builder.append(x)
                .append(" ")
                .append(y)
                .append(" ");
        }
        rand100Points = builder.toString().trim().split(" ");
    }
    
    @Test(timeout=1000)
    public void test100Points() {
        Task1.doTask(rand100Points);
    }
    
    @Test
    public void testSearchMinLengths() {
        String[] args = "10 10 20 10 20 15".split(" ");
        String minEl = Task1.doTask(args);
        assertEquals(minEl, "5");
    }
    
    @Test
    public void testWithNoIntAnsw() {
        String[] args = "10 10 20 10 20 15 11 11".split(" ");
        String minEl = Task1.doTask(args);
        String answer = String.valueOf(Math.sqrt(2D));
        assertEquals(minEl, answer);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testWithBadInput() {
        String[] args = new String[]{"1.1 1 2 3"};
        Task1.doTask(args);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testWithWrongNumOfElements() {
        String[] args = new String[]{"1 2 3"};
        Task1.doTask(args);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testWithoutInputData() {
        String[] args = new String[]{};
        Task1.doTask(args);
    }
}
