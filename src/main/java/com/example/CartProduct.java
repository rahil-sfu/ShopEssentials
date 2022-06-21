package com.example;

public class CartProduct {
    String quantity; 
    Products product; 
  
    public Products getProduct() {
        return product;
    }
    public void setProduct(Products product) {
        this.product = product;
    }
public String getQuantity() {
    return quantity;
}
public void setQuantity(String quantity) {
    this.quantity = quantity;
}
}
