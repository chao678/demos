package com.example.testapp.bean;

public class PhoneListBean {
    private String name;
    private String phone;

    public PhoneListBean() {
        this.name = "";
        this.phone = "";
    }

    public PhoneListBean(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
