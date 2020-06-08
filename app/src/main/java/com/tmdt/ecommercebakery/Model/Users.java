package com.tmdt.ecommercebakery.Model;

public class Users {
    private String email, name, pass, phone, image;

    public Users() {

    }

    public Users(String email, String name, String pass, String phone, String image) {
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
