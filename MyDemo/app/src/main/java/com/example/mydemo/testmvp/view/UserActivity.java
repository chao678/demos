package com.example.mydemo.testmvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.login.view.LoginActivity;
import com.example.mydemo.testmvp.presenter.UserPresenter;
import com.example.mydemo.util.SPUtil;
import com.example.mydemo.util.SharedPreferencesUtils;

public class UserActivity extends AppCompatActivity implements IUserView, View.OnClickListener {

    private UserPresenter presenter;

    private EditText id;
    private EditText first;
    private EditText last;
    private Button save;
    private Button load;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        id = findViewById(R.id.et_id);
        first = findViewById(R.id.et_first);
        last = findViewById(R.id.et_last);
        save = findViewById(R.id.save);
        load = findViewById(R.id.load);
        text1 = findViewById(R.id.text1);

        save.setOnClickListener(this);
        load.setOnClickListener(this);

        presenter = new UserPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                presenter.saveUser(getID(), getFristName(), getLastName());
                break;
            case R.id.load:
                presenter.loadUser(getID());
                break;
        }
    }

    @Override
    public int getID() {
        try {
            return Integer.parseInt(id.getText().toString().trim());
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public String getFristName() {
        return first.getText().toString();
    }

    @Override
    public String getLastName() {
        return last.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        first.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        last.setText(lastName);
    }

    @Override
    public void saveSP() {
        SharedPreferencesUtils.setParam(UserActivity.this, "id", getID());
        SharedPreferencesUtils.setParam(UserActivity.this, "frist", getFristName());
        SharedPreferencesUtils.setParam(UserActivity.this, "last", getLastName());
    }

    @Override
    public void getSP() {
        int id = (int) SharedPreferencesUtils.getParam(UserActivity.this, "id", 1);
        String f = (String) SharedPreferencesUtils.getParam(UserActivity.this, "frist", "");
        String l = (String) SharedPreferencesUtils.getParam(UserActivity.this, "last", "");
        text1.setText("id="+id+";frist="+f+";last="+l);
    }

}
