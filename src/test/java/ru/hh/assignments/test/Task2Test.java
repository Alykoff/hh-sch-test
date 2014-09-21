package ru.hh.assignments.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.hh.assignments.Task2;

public class Task2Test {

    @Test
    public void test() {
        int[] els = new int[] {
           2, 4, 3, 6, 5
        };
        
        String result = "";//Task2.meth1(els);
        assertEquals(result, "2 3 5 - 4 6");
    }

}
