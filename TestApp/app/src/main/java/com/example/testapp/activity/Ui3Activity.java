package com.example.testapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapp.FirstEvent;
import com.example.testapp.FirstEvent2;
import com.example.testapp.R;

import org.greenrobot.eventbus.EventBus;

public class Ui3Activity extends AppCompatActivity implements View.OnClickListener{
    private TextView text1;
    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui3);

        initViews();
    }

    private void initViews() {
        text1 = findViewById(R.id.text1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:

                EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));

                text1.setText("FirstEvent btn clicked");
                break;
            case R.id.button2:

                EventBus.getDefault().post(new FirstEvent2("你好!"));
                text1.setText("你好!");
                break;
        }
    }


}
