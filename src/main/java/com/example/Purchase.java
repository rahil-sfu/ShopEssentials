package com.example;

public class Purchase {
    float subtotal; 
    float tax ; 
    float discount; 
    float deliveryCharge; 
    int delivery_time ;
    int distance; 
    String TotalCost; 
   
    public float getDeliveryCharge() {
        return deliveryCharge;
    }
    public int getDelivery_time() {
        return delivery_time;
    }
    public float getDiscount() {
        return discount;
    }
    public int getDistance() {
        return distance;
    }
    public float getSubtotal() {
        return subtotal;
    }
    public float getTax() {
        return tax;
    }
    public String getTotalCost() {
        return TotalCost;
    }
  
    public void setDeliveryCharge(float deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }
    public void setDelivery_time(int delivery_time) {
        this.delivery_time = delivery_time;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    public void setTax(float tax) {
        this.tax = tax;
    }
    public void setTotalCost(String totalCost) {
        TotalCost = totalCost;
    }
}
