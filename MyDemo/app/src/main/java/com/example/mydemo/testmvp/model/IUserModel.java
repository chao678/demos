package com.example.mydemo.testmvp.model;

import com.example.mydemo.testmvp.bean.UserBean;

public interface IUserModel {
    void setID(int id);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    int getID();

    UserBean load(int id);// 通过id读取user信息,返回一个UserBean
}
