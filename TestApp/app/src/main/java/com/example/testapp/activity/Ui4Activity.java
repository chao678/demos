package com.example.testapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.FirstEvent2;
import com.example.testapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Ui4Activity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui4);
        EventBus.getDefault().register(this);
        initViews();
    }

    private void initViews() {
        text = findViewById(R.id.text);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(this, Ui3Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread2(FirstEvent2 data) {

        String msg = "UI4收到了消息：" + data.getMsg();
        text.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
