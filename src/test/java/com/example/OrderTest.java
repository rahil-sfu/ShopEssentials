package com.example;

//import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class OrderTest {
    static Order o = new Order(); 
    @BeforeAll
    static void setUp()
    {
        o = new Order();
     
        o.setTotalCost("27 dollars");
        o.setAddress("Hornby St");
        o.setCharges("3 dollars");
        o.setDistance("5km");
        o.setTime("2 hours");
        o.setDiscounts("20 percent");
        o.setSubtotal("30 dollars");
        o.setTax("2 dollars");

    }

    @Test
    public void OrderTesting()
    {
        assertEquals("27 dollars", o.getTotalCost());
        assertEquals("Hornby St", o.getAddress());
        assertEquals("3 dollars", o.getCharges());
        assertEquals("5km", o.getDistance());
        assertEquals("2 hours", o.getTime());
        assertEquals("20 percent", o.getDiscounts());
        assertEquals("30 dollars", o.getSubtotal());
        assertEquals("2 dollars", o.getTax());
    }

}
