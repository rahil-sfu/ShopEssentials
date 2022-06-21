package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class SearchTest {
    static Search s = new Search(); 
    @BeforeAll
    static void setUp()
    {

        s = new Search();
        s.setKeyword("books");
    }

    @Test
    public void SearchTesting()
    {
        assertEquals("books", s.getKeyword());
    }
}
