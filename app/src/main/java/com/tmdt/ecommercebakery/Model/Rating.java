package com.tmdt.ecommercebakery.Model;

public class Rating {
    private String userPhone;
    private String productID;
    private String rateValue;
    private String comment;

    public Rating() {

    }

    public Rating(String userPhone, String productID, String rateValue, String comment) {
        this.userPhone = userPhone;
        this.productID = productID;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
