package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class filterTest {
    static filter f = new filter(); 
    @BeforeAll
    static void setUp()
    {
        int min = 30;
        int max = 100;
        f = new filter();
        f.setMin_price(min);
        f.setMax_price(max);
    }

    @Test
    public void filterTesting()
    {
        int min = 30;
        int max = 100;
        assertEquals(min, f.getMin_price());
        assertEquals(max, f.getMax_price());
        
    }
}
