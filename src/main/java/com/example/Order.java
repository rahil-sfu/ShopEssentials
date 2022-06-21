package com.example;

import java.util.Set;

public class Order {
   public String address ; 
   public String distance ; 
   public String time ; 
   public String charges;
   public String subtotal;
   public String tax; 
   public String discounts; 
   public String totalCost;  

public void setTotalCost(String totalCost) {
    this.totalCost = totalCost;
}
public String getTotalCost() {
    return totalCost;
}

    public String getAddress() {
        return address;
    }
    public String getCharges() {
        return charges;
    }
    public String getDistance() {
        return distance;
    }
    public String getTime() {
        return time;
    }
public String getDiscounts() {
    return discounts;
}
public String getSubtotal() {
    return subtotal;
}
public String getTax() {
    return tax;
}    
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCharges(String charges) {
        this.charges = charges;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }
    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
    public void setTax(String tax) {
        this.tax = tax;
    }

}
