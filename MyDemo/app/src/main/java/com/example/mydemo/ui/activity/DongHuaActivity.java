package com.example.mydemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.example.mydemo.R;

public class DongHuaActivity extends AppCompatActivity {
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_hua);

        initViews();
    }

    private void initViews() {
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);

        final TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        final TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);

        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    if (View.GONE == edit2.getVisibility()) {
                        edit2.startAnimation(mShowAction);
                        edit3.startAnimation(mShowAction);
                        edit2.setVisibility(View.VISIBLE);
                        edit3.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (View.VISIBLE == edit2.getVisibility()) {
                        edit2.startAnimation(mHiddenAction);
                        edit3.startAnimation(mHiddenAction);
                        edit2.setVisibility(View.GONE);
                        edit3.setVisibility(View.GONE);
                        edit2.setText("");
                        edit3.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
