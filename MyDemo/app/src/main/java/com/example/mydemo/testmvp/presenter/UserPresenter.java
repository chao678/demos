package com.example.mydemo.testmvp.presenter;

import com.example.mydemo.testmvp.bean.UserBean;
import com.example.mydemo.testmvp.model.IUserModel;
import com.example.mydemo.testmvp.model.UserModel;
import com.example.mydemo.testmvp.view.IUserView;

public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    public void saveUser( int id, String firstName, String lastName) {
        mUserModel.setID(id);
        mUserModel.setFirstName(firstName);
        mUserModel.setLastName(lastName);
        mUserView.saveSP();
    }

    public void loadUser( int id) {
        UserBean user = mUserModel.load(id);
        mUserView.setFirstName(user.getFirstName()); // 通过调用IUserView的方法来更新显示
        mUserView.setLastName(user.getLastName());
        mUserView.getSP();
    }
}
