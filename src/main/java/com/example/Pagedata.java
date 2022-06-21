package com.example;

import java.util.ArrayList;

public class Pagedata {
    public String username; 
    public ArrayList<Products> product = new ArrayList<Products>();
    
    public ArrayList<Products> getProduct() {
        return product;
    }
    public void setProduct(ArrayList<Products> product) {
        this.product = product;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
}
