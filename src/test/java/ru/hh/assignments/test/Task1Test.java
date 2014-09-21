package ru.hh.assignments.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ru.hh.assignments.Task1;

public class Task1Test {
    @Test
    public void testSearchMinLengths() {
        String[] args = "10 10 20 10 20 15".split(" ");
        String minEl = Task1.doTask(args);
        assertEquals(minEl, "5");
    }

}
