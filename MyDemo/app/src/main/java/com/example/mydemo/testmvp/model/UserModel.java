package com.example.mydemo.testmvp.model;

import com.example.mydemo.login.view.LoginActivity;
import com.example.mydemo.testmvp.bean.UserBean;
import com.example.mydemo.util.SPUtil;

public class UserModel implements IUserModel {
    @Override
    public void setID(int id) {

    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public UserBean load(int id) {
        return new UserBean("111", "哈哈哈");
    }
}
