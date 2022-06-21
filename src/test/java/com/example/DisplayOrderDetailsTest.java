package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class DisplayOrderDetailsTest {
    static DisplayOrderDetails o; 
    @BeforeAll
    static void setUp()
    {
        o = new DisplayOrderDetails();
        
        Order order = new Order();
     
        order.setTotalCost("27 dollars");
        order.setAddress("Hornby St");
        order.setCharges("3 dollars");
        order.setDistance("5km");
        order.setTime("2 hours");
        order.setDiscounts("20 percent");
        order.setSubtotal("30 dollars");
        order.setTax("2 dollars");

        PersonInfo p = new PersonInfo();
     
        p.setFirstName("Steve");
        p.setLastName("Jobs");
        p.setPhNumber("123456789");
        p.setEmail("steve@apple.com");

        o.setOrdertime("tuesday");
        o.setOrder_detail(order);
        o.setPerson_detail(p); 
       
    }

    @Test
    public void DisplayOrderDetailsTesting()
    {
        assertEquals("tuesday", o.getOrdertime());

        assertEquals("27 dollars", o.getOrder_detail().getTotalCost());
        assertEquals("Hornby St", o.getOrder_detail().getAddress());
        assertEquals("3 dollars", o.getOrder_detail().getCharges());
        assertEquals("5km", o.getOrder_detail().getDistance());
        assertEquals("2 hours", o.getOrder_detail().getTime());
        assertEquals("20 percent", o.getOrder_detail().getDiscounts());
        assertEquals("30 dollars", o.getOrder_detail().getSubtotal());
        assertEquals("2 dollars", o.getOrder_detail().getTax());

        assertEquals("Steve", o.getPerson_detail().getFirstName());
        assertEquals("Jobs", o.getPerson_detail().getLastName());
        assertEquals("123456789", o.getPerson_detail().getPhNumber());
        assertEquals("steve@apple.com", o.getPerson_detail().getEmail());
        
    }
}
