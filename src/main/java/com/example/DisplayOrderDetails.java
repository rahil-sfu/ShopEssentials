package com.example;

public class DisplayOrderDetails {
    public Order order_detail; 
    public PersonInfo person_detail; 
    public String Ordertime ;
    public String getOrdertime() {
        return Ordertime;
    }
    public void setOrdertime(String ordertime) {
        Ordertime = ordertime;
    }

    public Order getOrder_detail() {
        return order_detail;
    }
    public PersonInfo getPerson_detail() {
        return person_detail;
    }
    public void setOrder_detail(Order order_detail) {
        this.order_detail = order_detail;
    }
    public void setPerson_detail(PersonInfo person_detail) {
        this.person_detail = person_detail;
    }

}
