package com.tmdt.ecommercebakery.Model;

public class Cart {

    private String pname, price, pid, date, time, quantity, discount;

    public Cart() {

    }

    public Cart(String pname, String price, String pid, String date, String time, String quantity, String discount) {
        this.pname = pname;
        this.price = price;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.discount = discount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
