package com.example;

//import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class PersonInfoTest {
    static PersonInfo p = new PersonInfo(); 
    @BeforeAll
    static void setUp()
    {
        p = new PersonInfo();
     
        p.setFirstName("Steve");
        p.setLastName("Jobs");
        p.setPhNumber("123456789");
        p.setEmail("steve@apple.com");

    }

    @Test
    public void PersonInfoTesting()
    {
        assertEquals("Steve", p.getFirstName());
        assertEquals("Jobs", p.getLastName());
        assertEquals("123456789", p.getPhNumber());
        assertEquals("steve@apple.com", p.getEmail());
    }

}
