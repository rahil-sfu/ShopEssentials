package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class PurchaseTest {
    static Purchase p = new Purchase(); 
    @BeforeAll
    static void setUp()
    {
        float subtotal = 206.8F; 
        float tax = 20.78F; 
        float discount = 56.19F; 
        float deliveryCharge = 5.99F; 
        int delivery_time = 2;
        int distance = 9; 

        p = new Purchase();
        p.setSubtotal(subtotal);
        p.setTax(tax);
        p.setDiscount(discount);
        p.setDeliveryCharge(deliveryCharge);
        p.setDelivery_time(delivery_time);
        p.setDistance(distance);
        p.setTotalCost("108.59 dollars");
    
    }

    @Test
    public void PurchaseTesting()
    {
        float subtotal = 206.8F; 
        float tax = 20.78F; 
        float discount = 56.19F; 
        float deliveryCharge = 5.99F; 
        int delivery_time = 2;
        int distance = 9; 

        assertEquals(subtotal, p.getSubtotal());
        assertEquals(tax, p.getTax());
        assertEquals(discount, p.getDiscount());
        assertEquals(deliveryCharge, p.getDeliveryCharge());
        assertEquals(delivery_time, p.getDelivery_time());
        assertEquals(distance, p.getDistance());
        assertEquals("108.59 dollars", p.getTotalCost());
        
    }
}
